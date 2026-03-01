package puzzle;

import puzzle.utils.GeneradorSucesores;

import java.util.*;

public class Puzzle implements GeneradorSucesores<Puzzle> {
    private static final String CHARSET = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final int size;
    private final char indicadorVacio;
    private final String estado;

    public Puzzle(int size, char indicadorVacio, String estado) {
        this.size = size;
        this.indicadorVacio = indicadorVacio;
        this.estado = estado;
    }

    @Override
    public List<Nodo<Puzzle>> generarSucesores() {
        return generarSucesoresAsString().stream().map(x -> {
            final var sucesor = new Puzzle(size, indicadorVacio, x);
            final var costo = Character.getNumericValue(sucesor.getEstado().charAt(estado.indexOf(' ')));
            return new Nodo<>(sucesor, costo);
        }).toList();
    }

    public List<String> generarSucesoresAsString() {
        final var i = estado.indexOf(indicadorVacio);
        final var estadoActual = estado;

        var arriba = i - size;
        var abajo = i + size;

        var sucesores = new ArrayList<String>();

        if (arriba >= 0) {
            sucesores.add(intercambiarCaracteres(estadoActual, i, arriba));
        }

        if (abajo < estadoActual.length()) {
            sucesores.add(intercambiarCaracteres(estadoActual, i, abajo));
        }

        if (i % size != 0) {
            sucesores.add(intercambiarCaracteres(estadoActual, i, i - 1));
        }

        if (i % size != size - 1) {
            sucesores.add(intercambiarCaracteres(estadoActual, i, i + 1));
        }

        return sucesores;
    }

    public String getEstado() {
        return estado;
    }

    public int getPosicionVacio() {
        return estado.indexOf(indicadorVacio);
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != Puzzle.class) {
            return false;
        }

        return ((Puzzle) obj).getEstado().equals(estado);
    }

    @Override
    public int hashCode() {
        return estado.hashCode();
    }

    @Override
    public String toString() {
        return estado;
    }

    private static String intercambiarCaracteres(String s, int a, int b) {
        if (a == b) {
            throw new IllegalArgumentException("No pueden ser iguales a y b");
        }

        if (a > b) {
            final var c = a;
            a = b;
            b = c;
        }

        return s.substring(0, a) + s.charAt(b) + s.substring(a + 1, b) + s.charAt(a) + s.substring(b + 1);
    }

    public static Puzzle generarAleatorio(int size) {
        if ((size * size) - 1 > CHARSET.length()) {
            throw new UnsupportedOperationException("Set de caracteres insuficiente.");
        }

        var caracteresPorUsar = CHARSET.substring(0, (size * size) - 1);
        var chars = new ArrayList<>(Arrays.stream(caracteresPorUsar.split("")).map(x -> x.charAt(0)).toList());
        chars.add(' ');
        Collections.shuffle(chars);

        var sb = new StringBuilder();
        for (Character character : chars) {
            sb.append(character);
        }
        return new Puzzle(size, ' ', sb.toString());
    }

    public static Puzzle generarFinalizado(int size) {
        if ((size * size) - 1 > CHARSET.length()) {
            throw new UnsupportedOperationException("Set de caracteres insuficiente.");
        }
        return new Puzzle(size, ' ', CHARSET.substring(0, (size * size) - 1) + " ");
    }
}
