import java.util.*;

public class ArbolBusqueda {
    private Nodo raiz;

    public ArbolBusqueda(Nodo raiz) {
        this.raiz = raiz;
    }

    public Nodo breadthFirstSearch(String estadoObjetivo) {
        if (raiz == null) return null;

        var visitados = new HashSet<String>();
        var cola = new LinkedList<Nodo>();
        cola.add(raiz);

        Nodo actual;
        while (!cola.isEmpty()) {
            actual = cola.poll();
            if (actual.getEstado().equals(estadoObjetivo)) {
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
            if (actual.getEstado().equals(estadoObjetivo)) {
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
        while (!actual.getEstado().equals(estadoObjetivo)) {
            stateSets.add(actual.getEstado());

            var sucesores = actual.generarSucesores();
            for (Nodo sucesor : sucesores) {
                if (stateSets.contains(sucesor.getEstado())) continue;
                stateSets.add(sucesor.getEstado());

                sucesor.setCostoTotal(actual.getCostoTotal() + (int)sucesor.getEstado().charAt(sucesor.getEstado().indexOf(' ')));
                priorityQueue.add(sucesor);
            }

            actual = priorityQueue.poll();
            tiempo++;
        }

        return null;
    }

    public Nodo limitedDepthFirstSearch(String estadoObjetivo, int limiteDeProfundidad) {
        if (raiz == null) return null;

        var visitados = new HashSet<String>();
        var stack = new Stack<Nodo>();
        stack.add(raiz);

        Nodo actual;
        while (!stack.isEmpty()) {
            actual = stack.pop();
            if (actual.getEstado().equals(estadoObjetivo)) {
                return actual;
            }

            visitados.add(actual.getEstado());
            for (Nodo sucesor : actual.generarSucesores().reversed()) {
                if (visitados.contains(sucesor.getEstado())) continue;
                if (limiteDeProfundidad-- == 0) return null;
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
