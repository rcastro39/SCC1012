void main() {
    var raiz = new Nodo("5674 8321", null, 1);
    var estadoObjetivo = "1238 4765";

    var arbol = new ArbolBusqueda(raiz);

    Map<String, Resultado> resultadosDefault = realizarBenchmarks(arbol, estadoObjetivo);

    arbol.usarHeuristica(new MiHeuristica());
    Map<String, Resultado> resultadosMiHeuristica = realizarBenchmarks(arbol, estadoObjetivo);

    for (String key : resultadosDefault.keySet()) {
        System.out.printf("%s - %.2f - %.2f%n", key, resultadosDefault.get(key).segundos(), resultadosMiHeuristica.get(key).segundos());
    }
}

Map<String, Resultado> realizarBenchmarks(ArbolBusqueda arbol, String estadoObjetivo) {
    var resultados = new HashMap<String, Resultado>();
    resultados.put("BFS", benchmark(() -> arbol.breadthFirstSearch(estadoObjetivo)));
    resultados.put("DFS", benchmark(() -> arbol.depthFirstSearch(estadoObjetivo)));
    resultados.put("UCS", benchmark(() -> arbol.uniformCostSearch(estadoObjetivo)));
    resultados.put("IDFS", benchmark(() -> arbol.iterativeDepthFirstSearch(estadoObjetivo)));
    return resultados;
}

Resultado benchmark(NodoFn fn) {
    var inicio = System.nanoTime();
    var resultado = fn.fn();
    var ms = System.nanoTime() - inicio;
    return new Resultado(resultado, ms);
}

static class MiHeuristica implements Heuristica {

    @Override
    public boolean equals(String a, String b) {
        for (var i = 1; i < a.length(); i += 2) {
            if (a.charAt(i) != b.charAt(i)) return false;
        }
        return true;
    }

    @Override
    public int calcularDiferencia(String a, String b) {
        var diferencias = 0;
        for (var i = 1; i < a.length(); i += 2) {
            if (a.charAt(i) != b.charAt(i)) diferencias++;
        }
        return diferencias;
    }
}

interface NodoFn {
    Nodo fn();
}

record Resultado(Nodo estadoFinal, long nano) {
    float segundos() {
        return nano / 1_000_000_000.0f;
    }

    @Override
    public String toString() {
        return String.format("%s - %.2f segundos", estadoFinal.getEstado(), segundos());
    }
}

