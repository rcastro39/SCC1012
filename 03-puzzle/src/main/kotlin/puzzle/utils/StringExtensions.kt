package puzzle.utils

fun String.intercambiar(a: Int, b: Int): String {
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

    return this.substring(0, a) + this[b] + this.substring(a + 1, b) + this[a] + this.substring(b + 1)
}