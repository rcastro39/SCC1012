package puzzle.heuristicas

import puzzle.Puzzle
import puzzle.utils.Heuristica

class HeuristicaBase : Heuristica {
    override fun estimar(inicial: Puzzle): Int {
        return 0
    }
}