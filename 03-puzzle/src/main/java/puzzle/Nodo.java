package puzzle;

import puzzle.utils.GeneradorSucesores;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Nodo<T extends GeneradorSucesores<T>> {
    private T estado;
    private Nodo<T> padre;
    private int nivel;
    private int costo;
    private int costoTotal = -1;

    public Nodo(T estado, Nodo<T> padre, int costo) {
        this.estado = estado;
        this.padre = padre;
        if (costo < 0) throw new IllegalArgumentException("Costo no puede ser -1");
        this.costo = costo;
        this.nivel = padre == null ? 1 : padre.getNivel() + 1;
    }

    public Nodo(T estado, Nodo<T> padre) {
        this(estado, padre, 0);
    }


    public Nodo(T estado, int costo) {
        this(estado, null, costo);
    }
    public Nodo(T estado) {
        this(estado, null);
    }

    public List<Nodo<T>> generarSucesores() {
        final var sucesores = estado.generarSucesores();
        for (Nodo<T> sucesor : sucesores) {
            sucesor.setPadre(this);
        }
        return sucesores;
    }

    public T getEstado() {
        return estado;
    }

    public Nodo<T> getPadre() {
        return padre;
    }

    public void setPadre(Nodo<T> padre) {
        this.padre = padre;
        this.costoTotal = -1;
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
        if (costoTotal > -1) return costoTotal;

        costoTotal = costo;
        Nodo<T> actual = padre;
        while (actual != null) {
            costoTotal += actual.getCosto();
            actual = actual.getPadre();
        }
        return costoTotal;
    }

    @Override
    public String toString() {
        return estado.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (estado.getClass() != obj.getClass()) return false;
        return estado.equals(obj);
    }

    public void imprimirCamino() {
        var stack = new Stack<Nodo>();
        var actual = this;
        while (actual != null) {
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
