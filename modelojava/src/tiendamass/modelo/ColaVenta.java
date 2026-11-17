package tiendamass.modelo;

/**
 * Autor: Alessandra Condeso / Pretel Padilla Enrique / Angie Noemi Huaylinos / Karen Giron Faustino
 *
 * Clase ColaVenta
 * Cola que almacena los productos vendidos en una venta.
 * Implementada con nodos enlazados, permite encolar productos vendidos y recorrerlos.
 */
public class ColaVenta {

    private NodoItemVenta frente; // Primer nodo de la cola
    private NodoItemVenta fin;    // Último nodo de la cola

    // Constructor: inicializa la cola vacía
    public ColaVenta() {
        frente = fin = null;
    }

    // Verifica si la cola está vacía
    public boolean estaVacia() {
        return frente == null;
    }

    // Agrega un nuevo item de venta al final de la cola
    public void encolar(ItemVenta item) {
        NodoItemVenta nuevo = new NodoItemVenta(item);
        if (estaVacia()) {
            frente = fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
    }

    // Devuelve el nodo al frente de la cola
    public NodoItemVenta getFrente() {
        return frente;
    }

    // Devuelve una representación en texto de todos los items en la cola
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        NodoItemVenta actual = frente;
        while (actual != null) {
            sb.append(actual.getItem().toString());
            actual = actual.getSiguiente();
        }
        return sb.toString();
    }
}
