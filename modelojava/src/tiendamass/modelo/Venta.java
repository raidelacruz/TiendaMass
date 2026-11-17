package tiendamass.modelo;

/**
 * Autor: Alessandra Condeso / Pretel Padilla Enrique / Angie Noemi Huaylinos / Karen Giron Faustino
 *
 * Clase Venta
 * Representa una venta realizada en la tienda.
 * Contiene información del cliente, fecha, productos vendidos y el total de la venta.
 */
public class Venta {

    private String cliente;               // Nombre del cliente
    private String fecha;                 // Fecha de la venta (como texto)
    private ColaVenta productosVendidos;  // Cola de productos vendidos en esta venta
    private double total;                // Monto total de la venta

    // Constructor: inicializa la venta con cliente y fecha
    public Venta(String cliente, String fecha) {
        this.cliente = cliente;
        this.fecha = fecha;
        this.productosVendidos = new ColaVenta();
        this.total = 0;
    }

    // Agrega un producto a la venta y actualiza el total
    public void agregarProducto(Producto producto, int cantidad) {
        productosVendidos.encolar(new ItemVenta(producto, cantidad));
        total += producto.getPrecio() * cantidad;
    }

    // Getters y setters
    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public ColaVenta getProductosVendidos() {
        return productosVendidos;
    }

    // Devuelve una representación en texto de la venta
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente: ").append(cliente).append("\n");
        sb.append("Fecha: ").append(fecha).append("\n");
        sb.append("Productos:\n");
        sb.append(productosVendidos.toString());
        sb.append("Total: S/ ").append(total).append("\n");
        sb.append("--------------------------------------------------\n");
        return sb.toString();
    }
}
