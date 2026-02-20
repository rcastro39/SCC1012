import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Nodo {
    private String estado;
    private Nodo padre;
    private int nivel;
    private int costo;
    private int costoTotal;

    public Nodo(String estado, Nodo padre, int nivel) {
        this.estado = estado;
        this.padre = padre;
        this.nivel = nivel;
    }

    public List<Nodo> generarSucesores() {
        var estadosHijos = Puzzle8.generarSucesores(estado);
        return Arrays.stream(estadosHijos)
            .map(estado -> new Nodo(estado, this, nivel + 1))
            .collect(Collectors.toList());
    }

    public String getEstado() {
        return estado;
    }

    public Nodo getPadre() {
        return padre;
    }

    public int getNivel() {
        return nivel;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public int getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(int costoTotal) {
        this.costoTotal = costoTotal;
    }

    @Override
    public String toString() {
        return String.format("%s\n%s\n%s", estado.substring(0, 3), estado.substring(3, 6), estado.substring(6));
    }

    public void imprimirCamino() {
        var stack = new Stack<Nodo>();
        var actual = this;
        while (actual.padre != null) {
            stack.push(actual);
            actual = actual.padre;
        }
        var i = 0;
        while (!stack.isEmpty()) {
            IO.println(stack.pop().toString());
            System.out.printf("Nivel %d\n", i++);
            IO.println("------------");
        }
    }
}
