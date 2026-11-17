package tiendamass.modelo;

import java.io.*;
import javax.swing.table.DefaultTableModel;

/**
 * Autor: Alessandra Condeso / Pretel Padilla Enrique / Angie Noemi Huaylinos / Karen Giron Faustino
 *
 * Clase ListaProductos
 * Representa una lista enlazada de productos.
 * Permite agregar, buscar, eliminar, mostrar y persistir productos sin usar estructuras prohibidas.
 */
public class ListaProductos {

    private NodoProducto cabeza; // Primer nodo de la lista enlazada

    // Constructor: carga los productos desde archivo al iniciar
    public ListaProductos() {
        cargarDesdeArchivo("src/recursos/productos.txt");
        System.out.println("Productos cargados: " + contarProductos());
    }

    // Agrega un producto al final de la lista enlazada
    public void agregarProducto(Producto producto) {
        NodoProducto nuevo = new NodoProducto(producto);

        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoProducto actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
    }

    // Busca un producto por su ID
    public Producto buscarProductoPorId(int id) {
        NodoProducto actual = cabeza;

        while (actual != null) {
            if (actual.getProducto().getId() == id) {
                return actual.getProducto();
            }
            actual = actual.getSiguiente();
        }

        return null;
    }

    // Elimina un producto de la lista por su ID
    public boolean eliminarProductoPorId(int id) {
        if (cabeza == null) return false;

        if (cabeza.getProducto().getId() == id) {
            cabeza = cabeza.getSiguiente();
            return true;
        }

        NodoProducto anterior = cabeza;
        NodoProducto actual = cabeza.getSiguiente();

        while (actual != null) {
            if (actual.getProducto().getId() == id) {
                anterior.setSiguiente(actual.getSiguiente());
                return true;
            }
            anterior = actual;
            actual = actual.getSiguiente();
        }

        return false;
    }

    // Devuelve el nodo cabeza de la lista
    public NodoProducto getCabeza() {
        return cabeza;
    }

    // Muestra todos los productos en consola
    public void mostrarProductos() {
        NodoProducto actual = cabeza;
        while (actual != null) {
            System.out.println(actual.getProducto());
            actual = actual.getSiguiente();
        }
    }

    // Cuenta la cantidad total de productos en la lista
    public int contarProductos() {
        int contador = 0;
        NodoProducto actual = cabeza;
        while (actual != null) {
            contador++;
            actual = actual.getSiguiente();
        }
        return contador;
    }

    // Carga productos desde un archivo de texto y los agrega a la lista
    public void cargarDesdeArchivo(String ruta) {
        String[] lineas = ArchivoUtil.leerArchivo(ruta);
        for (int i = 0; i < lineas.length; i++) {
            String linea = lineas[i];
            String[] partes = linea.split(",");
            if (partes.length >= 4) {
                try {
                    int id = Integer.parseInt(partes[0].trim());
                    String nombre = partes[1].trim();
                    double precio = Double.parseDouble(partes[2].trim());
                    String categoria = partes[3].trim();
                    agregarProducto(new Producto(id, nombre, precio, categoria));
                } catch (NumberFormatException e) {
                    System.out.println("Error al parsear línea: " + linea);
                }
            }
        }
    }

    // Guarda todos los productos de la lista en un archivo de texto
    public void guardarEnArchivo(String ruta) {
        int cantidad = contarProductos();
        String[] lineas = new String[cantidad];
        NodoProducto actual = cabeza;
        int i = 0;

        while (actual != null) {
            Producto p = actual.getProducto();
            lineas[i] = p.getId() + "," + p.getNombre() + "," + p.getPrecio() + "," + p.getCategoria();
            actual = actual.getSiguiente();
            i++;
        }

        ArchivoUtil.escribirArchivo(ruta, lineas);
    }

    // Llena un modelo de tabla con los productos de la lista (para interfaz gráfica)
    public void llenarTabla(DefaultTableModel modelo) {
        NodoProducto actual = cabeza;
        while (actual != null) {
            Producto p = actual.getProducto();
            modelo.addRow(new Object[]{
                p.getId(),
                p.getNombre(),
                p.getPrecio(),
                p.getCategoria()
            });
            actual = actual.getSiguiente();
        }
    }
}
