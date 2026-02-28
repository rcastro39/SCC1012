import puzzle.ArbolBusqueda;
import puzzle.Puzzle;

void main() {
    var inicio = Puzzle.generarAleatorio(3);
//    var inicio = new Puzzle(3, ' ', "5674 8321");
    var arbol = new ArbolBusqueda<>(inicio);
    var objetivo = Puzzle.generarFinalizado(3);

    var pruebasRendimiento = arbol.realizarPruebasRendimiento(objetivo);

    for (String key : pruebasRendimiento.keySet()) {
        System.out.printf("%s - %.2fs%n", key, pruebasRendimiento.get(key).getTiempoSegundos());
    }
    System.out.println();
}
