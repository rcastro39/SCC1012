package puzzle.utils

fun IntArray.intercambiar(a: Int, b: Int): IntArray {
    var a = a
    var b = b
    if (a == b) {
        throw IllegalArgumentException("A y B no pueden ser iguales.")
    }

    if (a > b) {
        val c = a
        a = b
        b = c
    }

    val clone = this.clone()
    val aux = clone[a]
    clone[a] = clone[b]
    clone[b] = aux
    return clone
}