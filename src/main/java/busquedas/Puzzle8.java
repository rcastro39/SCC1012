package busquedas;

import java.util.ArrayList;

public class Puzzle8 {
    private static final String estadoInicial = "1238 4765";
    private static final String estadoObjetivo = "1234567 ";

    public static String[] generarSucesores(String estadoActual) {
        var sucesores = new ArrayList<String>();
        switch (estadoActual.indexOf(' ')) {
            /*
             * 0 1 2
             * 3 4 5
             * 6 7 8
             */
            case 0:
                sucesores.add(intercambiarCaracteres(estadoActual, 0, 1));
                sucesores.add(intercambiarCaracteres(estadoActual, 0, 3));
                break;
            /*
             * 1 0 2
             * 3 4 5
             * 6 7 8
             */
            case 1:
                sucesores.add(intercambiarCaracteres(estadoActual, 0, 1));
                sucesores.add(intercambiarCaracteres(estadoActual, 1, 2));
                sucesores.add(intercambiarCaracteres(estadoActual, 1, 4));
                break;
            /*
             * 1 2 0
             * 3 4 5
             * 6 7 8
             */
            case 2:
                sucesores.add(intercambiarCaracteres(estadoActual, 1, 2));
                sucesores.add(intercambiarCaracteres(estadoActual, 2, 5));
                break;
            /*
             * 1 2 3
             * 0 4 5
             * 6 7 8
             */
            case 3:
                sucesores.add(intercambiarCaracteres(estadoActual, 0, 3));
                sucesores.add(intercambiarCaracteres(estadoActual, 3, 4));
                sucesores.add(intercambiarCaracteres(estadoActual, 3, 6));
                break;
            /*
             * 1 4 2
             * 3 0 5
             * 6 7 8
             */
            case 4:
                sucesores.add(intercambiarCaracteres(estadoActual, 1, 4));
                sucesores.add(intercambiarCaracteres(estadoActual, 3, 4));
                sucesores.add(intercambiarCaracteres(estadoActual, 4, 5));
                sucesores.add(intercambiarCaracteres(estadoActual, 4, 7));
                break;
            /*
             * 1 5 2
             * 3 4 0
             * 6 7 8
             */
            case 5:
                sucesores.add(intercambiarCaracteres(estadoActual, 2, 5));
                sucesores.add(intercambiarCaracteres(estadoActual, 4, 5));
                sucesores.add(intercambiarCaracteres(estadoActual, 5, 8));
                break;
            /*
             * 1 6 2
             * 3 4 5
             * 0 7 8
             */
            case 6:
                sucesores.add(intercambiarCaracteres(estadoActual, 3, 6));
                sucesores.add(intercambiarCaracteres(estadoActual, 6, 7));
                break;
            /*
             * 1 7 2
             * 3 4 5
             * 6 0 8
             */
            case 7:
                sucesores.add(intercambiarCaracteres(estadoActual, 6, 7));
                sucesores.add(intercambiarCaracteres(estadoActual, 4, 7));
                sucesores.add(intercambiarCaracteres(estadoActual, 7, 8));
                break;
            /*
             * 1 8 2
             * 3 4 5
             * 6 7 0
             */
            case 8:
                sucesores.add(intercambiarCaracteres(estadoActual, 7, 8));
                sucesores.add(intercambiarCaracteres(estadoActual, 5, 8));
                break;
        }

        var arr = new String[sucesores.size()];
        for (var i = 0; i < arr.length; i++) {
            arr[i] = sucesores.get(i);
        }
        return arr;
    }

    //  bcde -> 0 2 -> cb de
    private static String intercambiarCaracteres(String s, int a, int b) {
        return s.substring(0, a) + s.charAt(b) + s.substring(a + 1, b) + s.charAt(a) + s.substring(b + 1);
    }

    static void main() {
        var sucesores = Puzzle8.generarSucesores(estadoInicial);
        for (String sucesor : sucesores) {
            int c = 0;
            for (var i = 0; i < 3; i++) {
                for (var j = 0; j < 3; j++, c++) {
                    IO.print(sucesor.charAt(c));
                }
                IO.println();
            }
            IO.println();
        }
    }
}
