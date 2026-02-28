package puzzle.utils;

import puzzle.Nodo;

import java.util.List;

public interface GeneradorSucesores<T extends GeneradorSucesores<T>> {
    List<Nodo<T>> generarSucesores();
}
