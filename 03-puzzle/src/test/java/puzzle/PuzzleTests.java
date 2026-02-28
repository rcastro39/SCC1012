package puzzle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class PuzzleTests {
    @Test
    void esquinaIzquierdaSuperior() {
        final var sucesores = new Puzzle(3, ' ', " 12345678").generarSucesoresAsString().toArray();
        final var resultadoCorrecto = new String[]{
                "1 2345678",
                "312 45678"
        };
        assertArrayEquals(
                sucesores,
                resultadoCorrecto
        );
    }

    @Test
    void esquinaDerechaSuperior() {
        final var sucesores = new Puzzle(3, ' ', "12 345678").generarSucesoresAsString().toArray();
        final var resultadoCorrecto = new String[]{
                "1 2345678",
                "12534 678"
        };
        assertArrayEquals(
                sucesores,
                resultadoCorrecto
        );
    }

    @Test
    void esquinaInferiorIzquierda() {

        final var sucesores = new Puzzle(3, ' ', "123456 78").generarSucesoresAsString().toArray();
        final var resultadoCorrecto = new String[]{
                "123 56478",
                "1234567 8"
        };
        assertArrayEquals(
                sucesores,
                resultadoCorrecto
        );
    }


    @Test
    void esquinaInferiorDerecha() {

        final var sucesores = new Puzzle(3, ' ', "12345678 ").generarSucesoresAsString().toArray();
        final var resultadoCorrecto = new String[]{
                "12345 786",
                "1234567 8"
        };
        assertArrayEquals(
                sucesores,
                resultadoCorrecto
        );
    }

    @Test
    void ladoSuperior() {

        final var sucesores = new Puzzle(3, ' ', "1 2345678").generarSucesoresAsString().toArray();
        final var resultadoCorrecto = new String[]{
                " 12345678",
                "12 345678",
                "1423 5678",
        };
        assertArrayEquals(
                sucesores,
                resultadoCorrecto
        );
    }

    @Test
    void ladoIzquierdo() {

        final var sucesores = new Puzzle(3, ' ', "123 45678").generarSucesoresAsString().toArray();
        final var resultadoCorrecto = new String[]{
                " 23145678",
                "1234 5678",
                "123645 78",
        };
        assertArrayEquals(
                sucesores,
                resultadoCorrecto
        );
    }

    @Test
    void ladoInferior() {

        final var sucesores = new Puzzle(3, ' ', "1234567 8").generarSucesoresAsString().toArray();
        final var resultadoCorrecto = new String[]{
                "123456 78",
                "1234 6758",
                "12345678 ",
        };
        assertArrayEquals(
                sucesores,
                resultadoCorrecto
        );
    }

    @Test
    void ladoDerecho() {

        final var sucesores = new Puzzle(3, ' ', "12345 678").generarSucesoresAsString().toArray();
        final var resultadoCorrecto = new String[]{
                "12 453678",
                "1234 5678",
                "12345867 ",
        };
        assertArrayEquals(
                sucesores,
                resultadoCorrecto
        );
    }

    @Test
    void centro() {

        final var sucesores = new Puzzle(3, ' ', "1234 5678").generarSucesoresAsString().toArray();
        final var resultadoCorrecto = new String[]{
                "123 45678",
                "1 3425678",
                "12345 678",
                "1234756 8",
        };
        assertArrayEquals(
                sucesores,
                resultadoCorrecto
        );
    }
}
