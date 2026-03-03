package puzzle

import puzzle.utils.intercambiar
import java.util.Collections
import kotlin.math.sqrt

data class Puzzle(
    val estado: IntArray,
) {
    val size: Int = sqrt(estado.size.toDouble()).toInt()

    fun generarSucesores(): List<IntArray> {
        val i = estado.indexOf(0)

        val arriba = i - size
        val abajo = i + size

        val sucesores = mutableListOf<IntArray>()

        if (arriba >= 0) {
            sucesores.add(estado.intercambiar(i, arriba))
        }

        if (abajo < estado.size) {
            sucesores.add(estado.intercambiar(i, abajo))
        }

        if (i % size != 0) {
            sucesores.add(estado.intercambiar(i, i - 1))
        }

        if (i % size != size - 1) {
            sucesores.add(estado.intercambiar(i, i + 1))
        }

        return sucesores
    }

    override fun toString(): String {
        val sb = StringBuilder()
        var c = 0
        for (i in 0..<size) {
            for (j in 0..<size) {
                sb.append(" ")
                sb.append(estado[c])
                sb.append(" |")
                c++
            }
            sb.append("\n")
        }
        return sb.toString()
    }

    override fun hashCode(): Int {
        return estado.contentHashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Puzzle) {
            throw IllegalArgumentException("otro debe ser puzzle")
        }
        return estado.contentEquals(other.estado)
    }

    companion object {
        fun generarAleatorio(size: Int): Puzzle {
            val completo = completo(size)
            completo.estado.shuffle()
            return completo
        }

        fun completo(size: Int): Puzzle {
            val arr = IntArray(size * size) { it + 1 }
            arr[arr.size - 1] = 0
            return Puzzle(arr)
        }
    }
}
