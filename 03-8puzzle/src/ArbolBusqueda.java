import java.util.*;

public class ArbolBusqueda {
    private Nodo raiz;
    private Heuristica heuristica = new Heuristica() {
        @Override
        public boolean equals(String a, String b) {
            return a.equals(b);
        }

        @Override
        public int calcularDiferencia(String a, String b) {
            var diferencias = 0;
            for (var i = 0; i < a.length(); i++) {
                if (a.charAt(i) != b.charAt(i)) diferencias++;
            }
            return diferencias;
        }
    };

    public ArbolBusqueda(Nodo raiz) {
        this.raiz = raiz;
    }

    public ArbolBusqueda usarHeuristica(Heuristica heuristica) {
        this.heuristica = heuristica;
        return this;
    }

    public Nodo breadthFirstSearch(String estadoObjetivo) {
        if (raiz == null) return null;

        var visitados = new HashSet<String>();
        var cola = new LinkedList<Nodo>();
        cola.add(raiz);

        Nodo actual;
        while (!cola.isEmpty()) {
            actual = cola.poll();
            if (heuristica.equals(actual.getEstado(), estadoObjetivo)) {
                return actual;
            }

            visitados.add(actual.getEstado());
            for (Nodo sucesor : actual.generarSucesores()) {
                if (visitados.contains(sucesor.getEstado())) continue;
                cola.add(sucesor);
            }
        }

        return null;
    }

    public Nodo depthFirstSearch(String estadoObjetivo) {
        if (raiz == null) return null;

        var visitados = new HashSet<String>();
        var stack = new Stack<Nodo>();
        stack.add(raiz);

        Nodo actual;
        while (!stack.isEmpty()) {
            actual = stack.pop();
            if (heuristica.equals(actual.getEstado(), estadoObjetivo)) {
                return actual;
            }

            visitados.add(actual.getEstado());
            for (Nodo sucesor : actual.generarSucesores().reversed()) {
                if (visitados.contains(sucesor.getEstado())) continue;
                stack.push(sucesor);
            }
        }

        return null;
    }

    public Nodo uniformCostSearch(String estadoObjetivo) {
        if (raiz == null) return null;

        Set<String> stateSets = new HashSet<String>();
        int tiempo = 0;

        var priorityQueue = new PriorityQueue<Nodo>(10, new NodePriorityComparator());
        priorityQueue.add(raiz);
        raiz.setCosto(0);

        Nodo actual = raiz;
        while (actual != null && !heuristica.equals(actual.getEstado(), estadoObjetivo)) {
            stateSets.add(actual.getEstado());

            var sucesores = actual.generarSucesores();
            for (Nodo sucesor : sucesores) {
                if (stateSets.contains(sucesor.getEstado())) continue;
                stateSets.add(sucesor.getEstado());

                sucesor.setCostoTotal(actual.getCostoTotal() + Character.getNumericValue(sucesor.getEstado().charAt(actual.getEstado().indexOf(' '))));
                priorityQueue.add(sucesor);
            }

            actual = priorityQueue.poll();
            tiempo++;
        }

        return actual;
    }

    public Nodo limitedDepthFirstSearch(String estadoObjetivo, int limiteDeProfundidad) {
        if (raiz == null) return null;

        var visitados = new HashSet<String>();
        var stack = new Stack<Nodo>();
        stack.add(raiz);

        Nodo actual;
        while (!stack.isEmpty()) {
            actual = stack.pop();
            if (heuristica.equals(actual.getEstado(), estadoObjetivo)) {
                return actual;
            }

            if (actual.getNivel() > limiteDeProfundidad) continue;

            visitados.add(actual.getEstado());
            for (Nodo sucesor : actual.generarSucesores().reversed()) {
                if (sucesor.getNivel() > limiteDeProfundidad) break;
                if (visitados.contains(sucesor.getEstado())) continue;
                stack.push(sucesor);
            }
        }

        return null;
    }

    public Nodo iterativeDepthFirstSearch(String estadoObjetivo) {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            var actual = limitedDepthFirstSearch(estadoObjetivo, i);
            if (actual != null) {
                return actual;
            }
        }

        return null;
    }
}
