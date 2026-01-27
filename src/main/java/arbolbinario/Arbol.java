package arbolbinario;

public class Arbol<T> {
    private Nodo<T> raiz;

    public boolean vacio() {
        return raiz == null;
    }

    public Nodo<T> buscarNodo(T dato) {
        if (raiz == null) {
            return null;
        }

        return buscarNodoRecursivo(raiz, dato);
    }

    private Nodo<T> buscarNodoRecursivo(Nodo<T> raiz, T dato) {
        if (raiz == null) {
            return null;
        }

        if (raiz.getDato().equals(dato)) {
            return raiz;
        }

        var izquierdo = buscarNodoRecursivo(raiz.getIzquierdo(), dato);
        if (izquierdo != null) {
            return izquierdo;
        }

        return buscarNodoRecursivo(raiz.getDerecho(), dato);
    }

    public void insertar(T dato) {
        if (raiz == null) {
            raiz = new Nodo<>(dato);
            return;
        }

        insertarRecursivo(raiz, dato);
    }

    private void insertarRecursivo(Nodo<T> raiz, T dato) {
        var datoComparable = (Comparable<T>) dato;
        if (datoComparable.compareTo(raiz.getDato()) < 0) {
            if (raiz.getIzquierdo() == null) {
                raiz.setIzquierdo(new Nodo<>(dato));
                return;
            }

            insertarRecursivo(raiz.getIzquierdo(), dato);
        } else {
            if (raiz.getDerecho() == null) {
                raiz.setDerecho(new Nodo<>(dato));
                return;
            }

            insertarRecursivo(raiz.getDerecho(), dato);
        }
    }

    public void imprimirArbol() {
        if (raiz == null) return;
        imprimirArbolRecursivo(raiz, "");
    }

    private void imprimirArbolRecursivo(Nodo<T> nodo, String prefijo) {
        System.out.printf("%s%s\n", prefijo, nodo.getDato().toString());
        prefijo += "  ";
        if (nodo.getIzquierdo() != null) {
            imprimirArbolRecursivo(nodo.getIzquierdo(), prefijo);
        }
        if (nodo.getDerecho() != null) {
            imprimirArbolRecursivo(nodo.getDerecho(), prefijo);
        }
    }
}
