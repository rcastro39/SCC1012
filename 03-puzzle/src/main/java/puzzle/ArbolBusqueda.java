package puzzle;

import puzzle.utils.GeneradorSucesores;
import puzzle.utils.Heuristica;
import puzzle.utils.NodePriorityComparator;

import java.util.*;

public class ArbolBusqueda<T extends GeneradorSucesores<T>> {
    private final Nodo<T> raiz;
    private Heuristica<T> heuristica = Object::equals;

    public ArbolBusqueda(T raiz) {
        if (raiz == null) throw new IllegalArgumentException("raiz no puede ser nula");
        this.raiz = new Nodo<T>(raiz);
    }

    public ArbolBusqueda<T> usarHeuristica(Heuristica<T> heuristica) {
        this.heuristica = heuristica;
        return this;
    }

    public Nodo<T> breadthFirstSearch(T estadoObjetivo) {
        var visitados = new HashSet<T>();
        var cola = new LinkedList<Nodo<T>>();
        cola.add(raiz);

        Nodo<T> actual;
        while (!cola.isEmpty()) {
            actual = cola.poll();
            if (heuristica.equals(actual.getEstado(), estadoObjetivo)) {
                return actual;
            }

            visitados.add(actual.getEstado());
            for (Nodo<T> sucesor : actual.generarSucesores()) {
                if (visitados.contains(sucesor.getEstado())) continue;
                cola.add(sucesor);
            }
        }

        return null;
    }

    public Nodo<T> depthFirstSearch(T estadoObjetivo) {
        var visitados = new HashSet<T>();
        var stack = new Stack<Nodo<T>>();
        stack.add(raiz);

        Nodo<T> actual;
        while (!stack.isEmpty()) {
            actual = stack.pop();
            if (heuristica.equals(actual.getEstado(), estadoObjetivo)) {
                return actual;
            }

            visitados.add(actual.getEstado());
            for (Nodo<T> sucesor : actual.generarSucesores().reversed()) {
                if (visitados.contains(sucesor.getEstado())) continue;
                stack.push(sucesor);
            }
        }

        return null;
    }

    public Nodo<T> uniformCostSearch(T estadoObjetivo) {
        Set<T> stateSets = new HashSet<T>();
        int tiempo = 0;

        var priorityQueue = new PriorityQueue<Nodo<T>>(10, new NodePriorityComparator<>());
        priorityQueue.add(raiz);
        raiz.setCosto(0);

        Nodo<T> actual = raiz;
        while (actual != null && !heuristica.equals(actual.getEstado(), estadoObjetivo)) {
            stateSets.add(actual.getEstado());

            var sucesores = actual.generarSucesores();
            for (Nodo<T> sucesor : sucesores) {
                if (stateSets.contains(sucesor.getEstado())) continue;
                stateSets.add(sucesor.getEstado());

                //sucesor.setCosto();
                priorityQueue.add(sucesor);
            }

            actual = priorityQueue.poll();
            tiempo++;
        }

        return actual;
    }

    public Nodo<T> limitedDepthFirstSearch(T estadoObjetivo, int limiteDeProfundidad) {
        var visitados = new HashSet<T>();
        var stack = new Stack<Nodo<T>>();
        stack.add(raiz);

        Nodo<T> actual;
        while (!stack.isEmpty()) {
            actual = stack.pop();
            if (heuristica.equals(actual.getEstado(), estadoObjetivo)) {
                return actual;
            }

            if (actual.getNivel() > limiteDeProfundidad) continue;

            visitados.add(actual.getEstado());
            for (Nodo<T> sucesor : actual.generarSucesores().reversed()) {
                if (sucesor.getNivel() > limiteDeProfundidad) break;
                if (visitados.contains(sucesor.getEstado())) continue;
                stack.push(sucesor);
            }
        }

        return null;
    }

    public Nodo<T> iterativeDepthFirstSearch(T estadoObjetivo) {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            var actual = limitedDepthFirstSearch(estadoObjetivo, i);
            if (actual != null) {
                return actual;
            }
        }

        return null;
    }

    public Map<String, Resultado> realizarPruebasRendimiento(T estadoObjetivo) {
        var resultados = new HashMap<String, Resultado>();

        long inicio;
        long fin;

        inicio = System.nanoTime();
        var bfs = breadthFirstSearch(estadoObjetivo);
        fin = System.nanoTime() - inicio;
        resultados.put("Breadth First Search", new Resultado(bfs, fin));

        inicio = System.nanoTime();
        var dfs = depthFirstSearch(estadoObjetivo);
        fin = System.nanoTime() - inicio;
        resultados.put("Depth First Search", new Resultado(dfs, fin));

        inicio = System.nanoTime();
        var ucs = depthFirstSearch(estadoObjetivo);
        fin = System.nanoTime() - inicio;
        resultados.put("Uniform Cost Search", new Resultado(ucs, fin));

        inicio = System.nanoTime();
        var idfs = depthFirstSearch(estadoObjetivo);
        fin = System.nanoTime() - inicio;
        resultados.put("Iterative Depth First Search", new Resultado(idfs, fin));

        return resultados;
    }

    public class Resultado {
        private final Nodo<T> nodoResultado;
        private final float tiempoSegundos;

        protected Resultado(Nodo<T> nodoResultado, long tiempoNanosegundos) {
            this.nodoResultado = nodoResultado;
            this.tiempoSegundos = tiempoNanosegundos / 1_000_000_000.0f;
        }

        public Nodo<T> getNodoResultado() {
            return nodoResultado;
        }

        public float getTiempoSegundos() {
            return tiempoSegundos;
        }

        @Override
        public String toString() {
            return String.format("%s - %.2f", nodoResultado.toString(), tiempoSegundos);
        }
    }
}
