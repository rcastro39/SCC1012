package puzzle.heuristicas

import puzzle.Puzzle
import puzzle.utils.Heuristica
import kotlin.math.abs

class DistanciaManhattan(objetivo: Puzzle) : Heuristica {
    private val posEnObjetivo = Array<Pair<Int, Int>>(objetivo.estado.size) { tile ->
        Pair(
            objetivo.estado.indexOf(tile) / objetivo.size,
            objetivo.estado.indexOf(tile) % objetivo.size
        )
    }

    override fun estimar(actual: Puzzle): Int {
        var diferencia = 0
        for (i in 0..<actual.estado.size) {
            val origen = actual.estado[i]
            if (origen == 0) continue
            val (objetivoX, objetivoY) = posEnObjetivo[origen]
            diferencia += abs(i / actual.size - objetivoX) + abs(i % actual.size - objetivoY)
        }
        return diferencia
    }
}
