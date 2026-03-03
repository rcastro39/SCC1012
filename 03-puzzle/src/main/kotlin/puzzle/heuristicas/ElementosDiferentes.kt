package puzzle.heuristicas

import puzzle.Puzzle
import puzzle.utils.Heuristica

class ElementosDiferentes(private val objetivo: Puzzle) : Heuristica {
    override fun estimar(inicial: Puzzle): Int {
        var diferencias = 0
        for (i in 0..<inicial.estado.size) {
            if (inicial.estado[i] != objetivo.estado[i]) {
                diferencias++
            }
        }
        return diferencias
    }
}