package puzzle.heuristicas;

import puzzle.Puzzle;
import puzzle.utils.Heuristica;

public class DistanciaManhattan implements Heuristica<Puzzle> {
    @Override
    public int calcular(Puzzle inicial, Puzzle objetivo) {
        var puntoObjetivo = objetivo.getPosicionVacio();

        var suma = 0;
        for (int i = 0; i < inicial.getEstado().length(); i++) {
            if (i == inicial.getPosicionVacio()) continue;

            var x0 = i % inicial.getSize();
            var y0 = i / inicial.getSize();

            var x1 = puntoObjetivo % objetivo.getSize();
            var y1 = puntoObjetivo / objetivo.getSize();

            suma += Math.abs(x0 - x1) + Math.abs(y0 - y1);
        }

        return suma;

//        var p0 = inicial.getPosicionVacio();
//        var x0 = p0 % inicial.getSize();
//        var y0 = p0 / inicial.getSize();
//
//        var p1 = objetivo.getPosicionVacio();
//        var x1 = p1 % objetivo.getSize();
//        var y1 = p1 / objetivo.getSize();
//
//        return Math.abs(x0 - x1) + Math.abs(y0 - y1);
    }
}
