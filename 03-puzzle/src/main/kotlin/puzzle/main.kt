package puzzle

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import puzzle.heuristicas.ConflictoLineal
import puzzle.heuristicas.DistanciaManhattan
import puzzle.heuristicas.ElementosDiferentes

val PUZZLE_3 = Puzzle(intArrayOf(1, 5, 4, 2, 3, 6, 8, 7, 0))

val PUZZLE_5 = Puzzle(
    intArrayOf(
        1, 2, 3, 4, 5,
        6, 7, 8, 13, 10,
        11, 12, 9, 14, 15,
        20, 17, 18, 19, 16,
        21, 22, 23, 24, 0
    )
)

fun main() {
//    val res = ArbolBusqueda(PUZZLE_5).aStar(Puzzle.completo(5), DistanciaManhattan(Puzzle.completo(5)))
//    println(res)
//    return

    println("(1) 3x3")
    println("(2) 5x5")
    print("Opción: ")
    val opcion = readlnOrNull()

    val size: Int = when (opcion?.trim()) {
        "1" -> 3
        "2" -> 5
        else -> {
            println("Opción no válida")
            0
        }
    }

    if (size == 0) return

    println("(1) Aleatorio (2) Manual (3) Predeterminado")
    print("Opción: ")
    val modo = readlnOrNull()?.trim()
    when (modo) {
        "3" if size == 3 -> puzzle3x3(PUZZLE_3)
        "3" if size == 5 -> puzzle5x5(PUZZLE_5)
        "2" -> {
            println("Ingrese estado del puzzle separado por espacios")
            val estado = readlnOrNull()?.split(" ")?.map {
                it.toIntOrNull() ?: throw IllegalArgumentException("Estado inválido")
            } ?: throw IllegalArgumentException("Estado inválido")

            val puzzle = Puzzle(estado.toIntArray())
            when (puzzle.size) {
                3 -> puzzle3x3(puzzle)
                5 -> puzzle5x5(puzzle)
                else -> throw IllegalArgumentException("Tamaño de puzzle no válido")
            }
        }

        "1" if size == 3 -> puzzle3x3()
        "1" if size == 5 -> puzzle5x5()
        else -> {
            println("Opción no válida")
        }
    }
}

fun puzzle3x3(inicio: Puzzle = Puzzle.generarAleatorio(3)) {
    println(inicio)

    val objetivo = Puzzle.completo(3)

    val bfs = ArbolBusqueda(inicio).breadthFirstSearch(objetivo)
    val dfs = ArbolBusqueda(inicio).depthFirstSearch(objetivo)
    val ucs = ArbolBusqueda(inicio).uniformCostSearch(objetivo)
    val astar = ArbolBusqueda(inicio).aStar(objetivo, ElementosDiferentes(objetivo))
    val astarManhattan = ArbolBusqueda(inicio).aStar(objetivo, DistanciaManhattan(objetivo))
    val astarConflictoLineal = ArbolBusqueda(inicio).aStar(objetivo, ConflictoLineal(objetivo))
    val idas = ArbolBusqueda(inicio).iterativeDeepeningAStar(objetivo, ElementosDiferentes(objetivo))
    val idasManhattan = ArbolBusqueda(inicio).iterativeDeepeningAStar(objetivo, DistanciaManhattan(objetivo))
    val idasConflictoLineal = ArbolBusqueda(inicio).iterativeDeepeningAStar(objetivo, ConflictoLineal(objetivo))

    println("BFS: $bfs - ${bfs.nodo?.costoAcumulado ?: 0}")
    println("DFS: $dfs - ${dfs.nodo?.costoAcumulado ?: 0}")
    println("UCS: $ucs - ${ucs.nodo?.costoAcumulado ?: 0}")
    println("A* (Elementos Diferentes): $astar - ${astar.nodo?.costoAcumulado ?: 0}")
    println("A* (Distancia Manhattan): $astarManhattan - ${astarManhattan.nodo?.costoAcumulado ?: 0}")
    println("A* (Conflicto Lineal): $astarConflictoLineal - ${astarConflictoLineal.nodo?.costoAcumulado ?: 0}")
    println("IDA* (Elementos Diferentes): $idas - ${idas.nodo?.costoAcumulado ?: 0}")
    println("IDA* (Distancia Manhattan): $idasManhattan - ${idasManhattan.nodo?.costoAcumulado ?: 0}")
    println("IDA* (Conflicto Lineal): $idasConflictoLineal - ${idasConflictoLineal.nodo?.costoAcumulado ?: 0}")
}

fun puzzle5x5(inicio: Puzzle = Puzzle.generarAleatorio(5)) = runBlocking {
    println(inicio)

    val objetivo = Puzzle.completo(5)

//    async(Dispatchers.Default) {
//        val result = ArbolBusqueda(inicio).iterativeDeepeningAStar(objetivo, ElementosDiferentes(objetivo))
//        println("IDA* (Elementos Diferentes): $result - ${result.nodo?.costoAcumulado ?: 0}")
//    }
    async(Dispatchers.Default) {
        val result = ArbolBusqueda(inicio).iterativeDeepeningAStar(objetivo, DistanciaManhattan(objetivo))
        println("IDA* (Distancia Manhattan): $result - ${result.nodo?.costoAcumulado ?: 0}")
    }
    async(Dispatchers.Default) {
        val result = ArbolBusqueda(inicio).iterativeDeepeningAStar(objetivo, ConflictoLineal(objetivo))
        println("IDA* (Conflicto Lineal): $result - ${result.nodo?.costoAcumulado ?: 0}")
    }
}