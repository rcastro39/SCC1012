void main() {
    var arbol = new Arbol<String>();
    arbol.insertar("Hola");
    arbol.insertar("Mundo");
    arbol.insertar("Adios");
    arbol.insertar("ZZZ");
    arbol.insertar("S");
    arbol.buscarNodo("Hola");
    arbol.buscarNodo("S");
    arbol.buscarNodo("Mundo");
    arbol.imprimirArbol();
}