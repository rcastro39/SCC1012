package puzzle.utils;

import puzzle.Nodo;

import java.util.Comparator;

public class NodePriorityComparator<T extends GeneradorSucesores<T>> implements Comparator<Nodo<T>> {

    @Override
    public int compare(Nodo x, Nodo y) {
        if (x.getCostoTotal() < y.getCostoTotal()) {
            return -1;
        }
        if (x.getCostoTotal() > y.getCostoTotal()) {
            return 1;
        }
        return 0;
    }
}