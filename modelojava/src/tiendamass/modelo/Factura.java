package tiendamass.modelo;

/**
 * Autor: Alessandra Condeso / Pretel Padilla Enrique / Angie Noemi Huaylinos / Karen Giron Faustino
 *
 * Clase Factura
 * Representa una factura de venta dentro del sistema TiendaMass.
 * Cada factura contiene un identificador único, un cliente asociado,
 * una lista enlazada de productos vendidos con sus cantidades y el total de la venta.
 */

public class Factura {

    private int idFactura;
    private Cliente cliente;
    private NodoDetalleFactura cabeza;
    private double total;
    private String fecha;

    // Constructor vacío
    public Factura() {
        this.idFactura = 0;
        this.cliente = null;
        this.cabeza = null;
        this.total = 0;
        this.fecha = "";
    }

    // Constructor completo
    public Factura(int idFactura, Cliente cliente, String fecha) {
        this.idFactura = idFactura;
        this.cliente = cliente;
        this.fecha = fecha;
        this.cabeza = null;
        this.total = 0;
    }

    // Método para agregar un producto a la factura
    public void agregarProducto(Producto producto, int cantidad) {
        NodoDetalleFactura nuevo = new NodoDetalleFactura(producto, cantidad);

        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoDetalleFactura actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }

        total += producto.getPrecio() * cantidad;
    }

    // Método alternativo para compatibilidad con interfaz
    public void agregarDetalle(Producto producto, int cantidad) {
        agregarProducto(producto, cantidad);
    }

    // Setters
    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setCliente(String nombre) {
        this.cliente = new Cliente(0, nombre, "", "");
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    // Getters
    public int getIdFactura() {
        return idFactura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public NodoDetalleFactura getDetalles() {
        return cabeza;
    }

    public double getTotal() {
        return total;
    }

    public String getFecha() {
        return fecha;
    }

    // Mostrar detalles de la factura
    public void mostrarFactura() {
        System.out.println("Factura N° " + idFactura);
        System.out.println("Cliente: " + cliente.toString());
        System.out.println("Fecha: " + fecha);
        System.out.println("Productos:");

        NodoDetalleFactura actual = cabeza;
        while (actual != null) {
            Producto p = actual.getProducto();
            int cantidad = actual.getCantidad();
            double subtotal = p.getPrecio() * cantidad;

            System.out.println("- " + p.getNombre() + " x" + cantidad + " = S/ " + subtotal);
            actual = actual.getSiguiente();
        }

        System.out.println("Total: S/ " + total);
    }
}
