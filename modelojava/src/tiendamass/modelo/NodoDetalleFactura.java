package tiendamass.modelo;

/**
 * Autor: Alessandra Condeso / Pretel Padilla Enrique / Angie Noemi Huaylinos / Karen Giron Faustino
 *
 * Clase NodoDetalleFactura
 * Representa un nodo en la lista de detalles de una factura.
 * Contiene un producto, la cantidad vendida y el enlace al siguiente nodo.
 */

public class NodoDetalleFactura {

    private Producto producto;
    private int cantidad;
    private NodoDetalleFactura siguiente;

    // Constructor
    public NodoDetalleFactura(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.siguiente = null;
    }

    // Getters
    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public NodoDetalleFactura getSiguiente() {
        return siguiente;
    }

    // Setters
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setSiguiente(NodoDetalleFactura siguiente) {
        this.siguiente = siguiente;
    }
}
