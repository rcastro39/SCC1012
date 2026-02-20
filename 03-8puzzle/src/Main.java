void main() {
    var raiz = new Nodo("5674 8321", null, 1);
    var arbol = new ArbolBusqueda(raiz);
    var estadoFinal = arbol.iterativeDepthFirstSearch("1238 4765");
    if (estadoFinal == null) {
        System.out.println("No se encontro el estado objetivo");
        return;
    }
    estadoFinal.imprimirCamino();
}
