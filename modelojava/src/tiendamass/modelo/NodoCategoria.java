package tiendamass.modelo;

/**
 * NodoCategoria representa una categoría de productos en un árbol binario.
 * Cada nodo contiene el nombre de la categoría, una cola de productos
 * pertenecientes a esa categoría, y referencias a sus subcategorías izquierda y derecha.
 */
public class NodoCategoria {

    private String nombreCategoria;
    private ColaProducto productos;
    private NodoCategoria izquierda;
    private NodoCategoria derecha;

    // Constructor
    public NodoCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
        this.productos = new ColaProducto();
        this.izquierda = null;
        this.derecha = null;
    }

    // Getter: nombre de la categoría
    public String getCategoria() {
        return nombreCategoria;
    }

    // Setter: nombre de la categoría
    public void setCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    // Getter: cola de productos
    public ColaProducto getProductos() {
        return productos;
    }

    // Setter: cola de productos
    public void setProductos(ColaProducto productos) {
        this.productos = productos;
    }

    // Getter: subcategoría izquierda
    public NodoCategoria getIzquierda() {
        return izquierda;
    }

    // Setter: subcategoría izquierda
    public void setIzquierda(NodoCategoria izquierda) {
        this.izquierda = izquierda;
    }

    // Getter: subcategoría derecha
    public NodoCategoria getDerecha() {
        return derecha;
    }

    // Setter: subcategoría derecha
    public void setDerecha(NodoCategoria derecha) {
        this.derecha = derecha;
    }
}
