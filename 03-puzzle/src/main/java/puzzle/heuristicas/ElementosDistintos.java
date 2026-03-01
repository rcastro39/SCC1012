package puzzle.heuristicas;

import puzzle.Puzzle;
import puzzle.utils.Heuristica;

public class ElementosDistintos implements Heuristica<Puzzle> {
    @Override
    public int calcular(Puzzle a, Puzzle b) {
        var diferencias = 0;
        for (int i = 0; i < a.getEstado().length(); i++) {
            if (a.getEstado().charAt(i) != b.getEstado().charAt(i)) {
                diferencias++;
            }
        }
        return diferencias;
    }
}
