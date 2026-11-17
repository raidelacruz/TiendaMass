package tiendamass.modelo;

/**
 * NodoProducto representa un nodo de la cola de productos.
 * Contiene un objeto Producto y una referencia al siguiente nodo.
 */
public class NodoProducto {

    private Producto producto;
    private NodoProducto siguiente;

    // Constructor
    public NodoProducto(Producto producto) {
        this.producto = producto;
        this.siguiente = null;
    }

    // Getter: producto
    public Producto getProducto() {
        return producto;
    }

    // Setter: producto
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    // Getter: siguiente nodo
    public NodoProducto getSiguiente() {
        return siguiente;
    }

    // Setter: siguiente nodo
    public void setSiguiente(NodoProducto siguiente) {
        this.siguiente = siguiente;
    }
}
