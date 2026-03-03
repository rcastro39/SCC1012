package puzzle.utils

import puzzle.Nodo

class NodoCostoTotalComparator : Comparator<Nodo> {
    override fun compare(n1: Nodo, n2: Nodo): Int {
        if (n1.costoTotal < n2.costoTotal) return -1
        if (n1.costoTotal > n2.costoTotal) return 1
        return 0
    }
}