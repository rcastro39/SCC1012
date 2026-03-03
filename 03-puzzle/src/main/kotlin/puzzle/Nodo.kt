package puzzle

import java.util.Stack

data class Nodo(val puzzle: Puzzle) {
    var costo: Int = 1
        set(value) {
            field = value
            costoAcumulado = (padre?.costoAcumulado ?: 0) + value
        }

    var costoAcumulado = costo
        private set

    // h
    var costoEstimado: Int = 0

    val costoTotal: Int get() = costoAcumulado + costoEstimado

    var nivel: Int = 1
        private set

    var padre: Nodo? = null
        private set(value) {
            field = value
            nivel = (value?.nivel ?: 0) + 1
            costoAcumulado = (value?.costoAcumulado ?: 0) + costo
        }

    fun generarSucesores(): List<Nodo> {
        return puzzle.generarSucesores().map {
            Nodo(Puzzle(it)).also { sucesor -> sucesor.padre = this }
        }
    }

    fun nodoFuePadre(nodo: Nodo): Boolean {
        if (nodo == this) return true
        var actual = padre
        while (actual != null) {
            if (actual.puzzle.estado.contentEquals(nodo.puzzle.estado)) return true
            actual = actual.padre
        }
        return false
    }

    override fun toString(): String = puzzle.estado.joinToString(", ")

    fun imprimirCamino() {
        val stack = Stack<String>()
        var nodo: Nodo? = this
        while (nodo != null) {
            stack.add(nodo.puzzle.toString())
            nodo = nodo.padre
        }
        while (stack.isNotEmpty()) {
            println(stack.pop())
            println()
        }
    }
}
