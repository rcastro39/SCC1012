import puzzle.ArbolBusqueda;
import puzzle.Puzzle;
import puzzle.heuristicas.DistanciaManhattan;
import puzzle.heuristicas.ElementosDistintos;
import puzzle.utils.GeneradorSucesores;

void main() {
//    var inicio = Puzzle.generarAleatorio(3);
//    var inicio = new Puzzle(3, ' ', "1234567 8");
//    var objetivo = Puzzle.generarFinalizado(3);
//
    var inicio = new Puzzle(3, ' ', "5674 8321");
    var objetivo = new Puzzle(3, ' ', "1238 4765");

//    var inicio = Puzzle.generarAleatorio(3);
//    var objetivo = Puzzle.generarAleatorio(3);

    var t1 = new Thread(() -> {
        var pruebasRendimiento = new ArbolBusqueda<>(inicio)
                .usarHeuristica(new ElementosDistintos())
                .realizarPruebasRendimiento(objetivo);
        imprimirResultados("Elementos Distintos", pruebasRendimiento);
    });

    var t2 = new Thread(() -> {
        var pruebasRendimiento = new ArbolBusqueda<>(inicio)
                .usarHeuristica(new DistanciaManhattan())
                .realizarPruebasRendimiento(objetivo);
        imprimirResultados("Distancia Manhattan", pruebasRendimiento);
    });

    t1.start();
    t2.start();
}

synchronized <T extends GeneradorSucesores<T>> void imprimirResultados(String tipo, Map<String, ArbolBusqueda<T>.Resultado> pruebasRendimiento) {
    System.out.println("== " + tipo);
    for (String key : pruebasRendimiento.keySet()) {
        System.out.printf("%s - %.2fs%n", key, pruebasRendimiento.get(key).getTiempoSegundos());
    }
}
