package tiendamass.modelo;

/**
 * Autor: Alessandra Condeso / Pretel Padilla Enrique / Angie Noemi Huaylinos / Karen Giron Faustino
 *
 * Clase ColaProducto
 * Representa una cola de productos.
 * Se utiliza para almacenar productos dentro de una categoría del árbol binario.
 * Implementada con nodos enlazados, sin usar estructuras prohibidas.
 */
public class ColaProducto {

    private NodoProducto frente; // Primer nodo de la cola
    private NodoProducto fin;    // Último nodo de la cola

    // Constructor: inicializa la cola vacía
    public ColaProducto() {
        this.frente = null;
        this.fin = null;
    }

    // Agrega un producto al final de la cola
    public void agregarProducto(Producto producto) {
        NodoProducto nuevo = new NodoProducto(producto);

        if (frente == null) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
    }

    // Elimina un producto por su ID
    public boolean eliminarProductoPorId(int id) {
        if (frente == null) return false;

        // Si el producto está al frente
        if (frente.getProducto().getId() == id) {
            frente = frente.getSiguiente();
            if (frente == null) fin = null;
            return true;
        }

        NodoProducto actual = frente;
        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getProducto().getId() == id) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                if (actual.getSiguiente() == null) fin = actual;
                return true;
            }
            actual = actual.getSiguiente();
        }

        return false;
    }

    // Cuenta cuántos productos hay en la cola
    public int contarProductos() {
        int contador = 0;
        NodoProducto actual = frente;
        while (actual != null) {
            contador++;
            actual = actual.getSiguiente();
        }
        return contador;
    }

    // Verifica si la cola está vacía
    public boolean estaVacia() {
        return frente == null;
    }

    // Muestra todos los productos en consola
    public void mostrarProductos() {
        NodoProducto actual = frente;
        while (actual != null) {
            System.out.println("   - " + actual.getProducto());
            actual = actual.getSiguiente();
        }
    }

    // Devuelve una representación en texto de la cola
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        NodoProducto actual = frente;

        while (actual != null) {
            sb.append("   - ").append(actual.getProducto().toString()).append("\n");
            actual = actual.getSiguiente();
        }

        return sb.toString();
    }

    // Getters y setters
    public NodoProducto getFrente() {
        return frente;
    }

    public void setFrente(NodoProducto frente) {
        this.frente = frente;
    }

    public NodoProducto getFin() {
        return fin;
    }

    public void setFin(NodoProducto fin) {
        this.fin = fin;
    }

    // Devuelve los productos ordenados por precio (ascendente) usando burbuja
    public Producto[] obtenerProductosOrdenadosPorPrecio() {
        int n = contarProductos();
        Producto[] productos = new Producto[n];

        NodoProducto actual = frente;
        int i = 0;
        while (actual != null) {
            productos[i++] = actual.getProducto();
            actual = actual.getSiguiente();
        }

        for (int j = 0; j < n - 1; j++) {
            for (int k = 0; k < n - j - 1; k++) {
                if (productos[k].getPrecio() > productos[k + 1].getPrecio()) {
                    Producto temp = productos[k];
                    productos[k] = productos[k + 1];
                    productos[k + 1] = temp;
                }
            }
        }

        return productos;
    }

    // Devuelve los productos ordenados por nombre (alfabéticamente)
    public Producto[] obtenerProductosOrdenadosPorNombre() {
        int n = contarProductos();
        Producto[] productos = new Producto[n];

        NodoProducto actual = frente;
        int i = 0;
        while (actual != null) {
            productos[i++] = actual.getProducto();
            actual = actual.getSiguiente();
        }

        for (int j = 0; j < n - 1; j++) {
            for (int k = 0; k < n - j - 1; k++) {
                String nombre1 = productos[k].getNombre().toLowerCase();
                String nombre2 = productos[k + 1].getNombre().toLowerCase();
                if (nombre1.compareTo(nombre2) > 0) {
                    Producto temp = productos[k];
                    productos[k] = productos[k + 1];
                    productos[k + 1] = temp;
                }
            }
        }

        return productos;
    }

    // Simula recorrido InOrden (igual al orden natural de la cola)
    public Producto[] obtenerProductosInOrden() {
        return copiarProductos();
    }

    // Simula recorrido PreOrden (igual al orden natural de la cola)
    public Producto[] obtenerProductosPreOrden() {
        return copiarProductos();
    }

    // Simula recorrido PostOrden (invirtiendo el orden de la cola)
    public Producto[] obtenerProductosPostOrden() {
        Producto[] originales = copiarProductos();
        int n = originales.length;

        for (int i = 0; i < n / 2; i++) {
            Producto temp = originales[i];
            originales[i] = originales[n - 1 - i];
            originales[n - 1 - i] = temp;
        }

        return originales;
    }

    // Copia los productos de la cola a un arreglo
    private Producto[] copiarProductos() {
        int n = contarProductos();
        Producto[] productos = new Producto[n];

        NodoProducto actual = frente;
        int i = 0;
        while (actual != null) {
            productos[i++] = actual.getProducto();
            actual = actual.getSiguiente();
        }

        return productos;
    }
}
