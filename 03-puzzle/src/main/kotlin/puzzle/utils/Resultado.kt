package puzzle.utils

import puzzle.Nodo
import kotlin.time.Duration

data class Resultado(
    val duracion: Duration,
    val nodo: Nodo?,
    val nodosExpandidos: Int
) {
    override fun toString(): String {
        return "$duracion - $nodosExpandidos nodos expandidos"
    }
}
