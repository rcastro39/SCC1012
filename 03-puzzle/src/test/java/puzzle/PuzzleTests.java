package puzzle;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class PuzzleTests {
    @Test
    void esquinaIzquierdaSuperior() {
        final var sucesores = new Puzzle(3, ' ', " 12345678").generarSucesoresAsString();
        final List<String> resultadoCorrecto = List.of(
                "1 2345678",
                "312 45678"
        );
        assert sucesores.size() == resultadoCorrecto.size() && sucesores.containsAll(resultadoCorrecto) && resultadoCorrecto.containsAll(sucesores);
    }

    @Test
    void esquinaDerechaSuperior() {
        final var sucesores = new Puzzle(3, ' ', "12 345678").generarSucesoresAsString();
        final var resultadoCorrecto = List.of(
                "1 2345678",
                "12534 678"
        );
        assert sucesores.size() == resultadoCorrecto.size() && sucesores.containsAll(resultadoCorrecto) && resultadoCorrecto.containsAll(sucesores);
    }

    @Test
    void esquinaInferiorIzquierda() {

        final var sucesores = new Puzzle(3, ' ', "123456 78").generarSucesoresAsString();
        final var resultadoCorrecto = List.of(
                "123 56478",
                "1234567 8"
        );
        assert sucesores.size() == resultadoCorrecto.size() && sucesores.containsAll(resultadoCorrecto) && resultadoCorrecto.containsAll(sucesores);
    }


    @Test
    void esquinaInferiorDerecha() {

        final var sucesores = new Puzzle(3, ' ', "12345678 ").generarSucesoresAsString();
        final var resultadoCorrecto = List.of(
                "12345 786",
                "1234567 8"
        );
        assert sucesores.size() == resultadoCorrecto.size() && sucesores.containsAll(resultadoCorrecto) && resultadoCorrecto.containsAll(sucesores);
    }

    @Test
    void ladoSuperior() {

        final var sucesores = new Puzzle(3, ' ', "1 2345678").generarSucesoresAsString();
        final var resultadoCorrecto = List.of(
                " 12345678",
                "12 345678",
                "1423 5678"
        );
        assert sucesores.size() == resultadoCorrecto.size() && sucesores.containsAll(resultadoCorrecto) && resultadoCorrecto.containsAll(sucesores);
    }

    @Test
    void ladoIzquierdo() {

        final var sucesores = new Puzzle(3, ' ', "123 45678").generarSucesoresAsString();
        final var resultadoCorrecto = List.of(
                " 23145678",
                "1234 5678",
                "123645 78"
        );
        assert sucesores.size() == resultadoCorrecto.size() && sucesores.containsAll(resultadoCorrecto) && resultadoCorrecto.containsAll(sucesores);
    }

    @Test
    void ladoInferior() {

        final var sucesores = new Puzzle(3, ' ', "1234567 8").generarSucesoresAsString();
        final var resultadoCorrecto = List.of(
                "123456 78",
                "1234 6758",
                "12345678 "
        );
        assert sucesores.size() == resultadoCorrecto.size() && sucesores.containsAll(resultadoCorrecto) && resultadoCorrecto.containsAll(sucesores);
    }

    @Test
    void ladoDerecho() {

        final var sucesores = new Puzzle(3, ' ', "12345 678").generarSucesoresAsString();
        final var resultadoCorrecto = List.of(
                "12 453678",
                "1234 5678",
                "12345867 "
        );
        assert sucesores.size() == resultadoCorrecto.size() && sucesores.containsAll(resultadoCorrecto) && resultadoCorrecto.containsAll(sucesores);
    }

    @Test
    void centro() {

        final var sucesores = new Puzzle(3, ' ', "1234 5678").generarSucesoresAsString();
        final var resultadoCorrecto = List.of(
                "123 45678",
                "1 3425678",
                "12345 678",
                "1234756 8"
        );
        assert sucesores.size() == resultadoCorrecto.size() && sucesores.containsAll(resultadoCorrecto) && resultadoCorrecto.containsAll(sucesores);
    }
}
