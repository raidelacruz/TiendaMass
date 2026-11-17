package tiendamass.modelo;

/**
 * Autor: Alessandra Condeso / Pretel Padilla Enrique / Angie Noemi Huaylinos / Karen Giron Faustino
 *
 * Clase Producto
 * Representa un producto dentro del sistema TiendaMass.
 */
public class Producto {

    private int id;
    private String nombre;
    private double precio;
    private String categoria;

    // Constructor completo
    public Producto(int id, String nombre, double precio, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    // Setter: ID del producto
    public void setId(int id) {
        this.id = id;
    }

    // Setter: nombre del producto
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Setter: precio del producto
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // Setter: categoría del producto
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // Getter: ID del producto
    public int getId() {
        return id;
    }

    // Getter: nombre del producto
    public String getNombre() {
        return nombre;
    }

    // Getter: precio del producto
    public double getPrecio() {
        return precio;
    }

    // Getter: categoría del producto
    public String getCategoria() {
        return categoria;
    }

    // Representación del producto como texto
    @Override
    public String toString() {
        return String.format("%-4d | %-14s | S/ %-6.2f | %s",
                id, nombre, precio, categoria);
    }

}
