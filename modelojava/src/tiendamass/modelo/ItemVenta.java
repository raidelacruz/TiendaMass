package tiendamass.modelo;

/**
 * Autor: Alessandra Condeso / Pretel Padilla Enrique / Angie Noemi Huaylinos / Karen Giron Faustino
 *
 * Clase ItemVenta
 * Representa un producto vendido junto con la cantidad adquirida.
 * Se utiliza como parte de una venta para registrar los productos comprados.
 */
public class ItemVenta {

    private Producto producto; // Producto vendido
    private int cantidad;      // Cantidad vendida

    // Constructor: recibe el producto y la cantidad vendida
    public ItemVenta(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    // Devuelve el producto asociado al item
    public Producto getProducto() {
        return producto;
    }

    // Devuelve la cantidad vendida del producto
    public int getCantidad() {
        return cantidad;
    }

    // Devuelve una representación en texto del item de venta
    @Override
    public String toString() {
        return "- " + producto.getNombre() + " x" + cantidad + " - S/ " + (producto.getPrecio() * cantidad) + "\n";
    }
}