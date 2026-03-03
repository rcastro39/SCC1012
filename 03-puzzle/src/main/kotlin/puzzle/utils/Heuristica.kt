package puzzle.utils

import puzzle.Puzzle

interface Heuristica {
    fun estimar(inicial: Puzzle): Int
}