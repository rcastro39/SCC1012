package puzzle.heuristicas

import puzzle.Puzzle
import puzzle.utils.Heuristica

class ConflictoLineal(objetivo: Puzzle) : Heuristica {
    private val manhattan = DistanciaManhattan(objetivo)
    private val posEnObjetivo = IntArray(objetivo.estado.size) { tile ->
        objetivo.estado.indexOf(tile)
    }

    override fun estimar(inicial: Puzzle): Int {
        var diferencia = manhattan.estimar(inicial)

        val size = inicial.size

        // verificar filas
        for (fila in 0..<size) {
            val rangoFila = (fila * size)..<((fila + 1) * size)
            for (posTj in rangoFila) {
                val tj = inicial.estado[posTj]
                if (tj == 0) continue

                val posTjEnObjetivo = posEnObjetivo[tj]
                if (posTjEnObjetivo !in rangoFila) continue

                for (posTk in rangoFila) {
                    val tk = inicial.estado[posTk]
                    if (tk == 0) continue

                    val posTkEnObjetivo = posEnObjetivo[tk]
                    if (
                        posTkEnObjetivo in rangoFila
                        && posTj > posTk
                        && posTjEnObjetivo < posTkEnObjetivo
                    ) {
                        diferencia += 2
                    }
                }
            }
        }

        // verificar columnas
        for (columna in 0..<size) {
            for (posTj in columna..<size step size) {
                val tj = inicial.estado[posTj]
                if (tj == 0) continue

                val posTjEnObjetivo = posEnObjetivo[tj]
                if (posTjEnObjetivo % size != columna) continue

                for (posTk in columna..<inicial.estado.size step size) {
                    val tk = inicial.estado[posTk]
                    if (tk == 0) continue

                    val posTkEnObjetivo = posEnObjetivo[tk]
                    if (
                        posTkEnObjetivo % size == columna
                        && posTj > posTk
                        && posTjEnObjetivo < posTkEnObjetivo
                    ) {
                        diferencia += 2
                    }
                }
            }
        }

        return diferencia
    }
}