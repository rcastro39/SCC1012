package puzzle

import puzzle.heuristicas.HeuristicaBase
import puzzle.utils.Heuristica
import puzzle.utils.NodoCostoTotalComparator
import puzzle.utils.Resultado
import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Stack
import kotlin.time.measureTime

class ArbolBusqueda(
    inicial: Puzzle
) {
    val raiz = Nodo(inicial)

    fun breadthFirstSearch(objetivo: Puzzle): Resultado {
        var nodosExpandidos = 0

        val visitados = hashSetOf<Puzzle>()
        val cola = LinkedList<Nodo>()
        cola.add(raiz)

        var nodo: Nodo
        var nodoResultado: Nodo? = null

        val duracion = measureTime {
            while (cola.isNotEmpty()) {
                nodo = cola.poll()

                if (nodo.puzzle == objetivo) {
                    nodoResultado = nodo
                    break
                }

                visitados.add(nodo.puzzle)
                for (sucesor in nodo.generarSucesores()) {
                    if (visitados.contains(sucesor.puzzle)) continue
                    nodosExpandidos++
                    cola.add(sucesor)
                }
            }
        }

        return Resultado(duracion, nodoResultado, nodosExpandidos)
    }

    fun depthFirstSearch(objetivo: Puzzle): Resultado {
        var nodosExpandidos = 0

        val visitados = hashSetOf<Puzzle>()
        val cola = Stack<Nodo>()
        cola.add(raiz)

        var nodo: Nodo
        var nodoResultado: Nodo? = null

        val duracion = measureTime {
            while (cola.isNotEmpty()) {
                nodo = cola.pop()

                if (nodo.puzzle == objetivo) {
                    nodoResultado = nodo
                    break
                }

                visitados.add(nodo.puzzle)
                for (sucesor in nodo.generarSucesores()) {
                    if (visitados.contains(sucesor.puzzle)) continue
                    nodosExpandidos++
                    cola.add(sucesor)
                }
            }
        }

        return Resultado(duracion, nodoResultado, nodosExpandidos)
    }

    fun uniformCostSearch(objetivo: Puzzle): Resultado {
        var nodosExpandidos = 0

        val visitados = hashSetOf<Puzzle>()
        val colaPrioridad = PriorityQueue<Nodo>(10, NodoCostoTotalComparator())
        colaPrioridad.add(raiz)

        var nodo: Nodo
        var nodoResultado: Nodo? = null

        val duration = measureTime {
            while (colaPrioridad.isNotEmpty()) {
                nodo = colaPrioridad.poll()

                if (nodo.puzzle == objetivo) {
                    nodoResultado = nodo
                    break
                }

                visitados.add(nodo.puzzle)
                for (sucesor in nodo.generarSucesores()) {
                    if (visitados.contains(sucesor.puzzle)) continue
                    nodosExpandidos++
                    sucesor.costo = sucesor.puzzle.estado[nodo.puzzle.estado.indexOf(0)]
                    colaPrioridad.add(sucesor)
                }
            }
        }

        return Resultado(duration, nodoResultado, nodosExpandidos)
    }

    fun aStar(objetivo: Puzzle, heuristica: Heuristica = HeuristicaBase()): Resultado {
        var nodosExpandidos = 0
        val visitados = hashSetOf<Puzzle>()
        val colaPrioridad = PriorityQueue<Nodo>(10, NodoCostoTotalComparator())
        colaPrioridad.add(raiz)

        var nodo: Nodo
        var nodoResultado: Nodo? = null

        val duration = measureTime {
            while (colaPrioridad.isNotEmpty()) {
                nodo = colaPrioridad.poll()

                if (nodo.puzzle == objetivo) {
                    nodoResultado = nodo
                    break
                }

                visitados.add(nodo.puzzle)
                for (sucesor in nodo.generarSucesores()) {
                    if (visitados.contains(sucesor.puzzle)) continue
                    nodosExpandidos++

                    sucesor.costo = sucesor.puzzle.estado[nodo.puzzle.estado.indexOf(0)]
                    sucesor.costoEstimado = heuristica.estimar(sucesor.puzzle)

                    colaPrioridad.add(sucesor)
                }
            }
        }

        return Resultado(duration, nodoResultado, nodosExpandidos)
    }

    fun iterativeDeepeningAStar(objetivo: Puzzle, heuristica: Heuristica = HeuristicaBase()): Resultado {
        var nodosExpandidos = 0
        var limite = heuristica.estimar(raiz.puzzle)
        raiz.costoEstimado = limite

        var resultado: Nodo? = null
        val duration = measureTime {
            while (true) {
                val (t, nodoResultado) = idasSearch(raiz, objetivo, 0, limite, heuristica) {
                    nodosExpandidos++
                }
                if (nodoResultado != null) {
                    resultado = nodoResultado
                    break
                }
                if (t == Int.MAX_VALUE) {
                    break
                }
                limite = t
            }
        }
        return Resultado(duration, resultado, nodosExpandidos)
    }

    private fun idasSearch(
        nodo: Nodo,
        objetivo: Puzzle,
        g: Int,
        limite: Int,
        h: Heuristica,
        expandeNodos: () -> Unit
    ): Pair<Int, Nodo?> {
        val f = g + h.estimar(nodo.puzzle)
        if (f > limite) return Pair(f, null)
        if (nodo.puzzle.estado.contentEquals(objetivo.estado)) return Pair(0, nodo)
        var min = Int.MAX_VALUE
        for (sucesor in nodo.generarSucesores()) {
            if (nodo.nodoFuePadre(sucesor)) continue
            expandeNodos()
            val (t, nodoResultado) = idasSearch(
                sucesor,
                objetivo,
                g + 1,
                limite,
                h,
                expandeNodos
            )
            if (nodoResultado != null) return Pair(0, nodoResultado)
            if (t < min) min = t
        }
        return Pair(min, null)
    }
}