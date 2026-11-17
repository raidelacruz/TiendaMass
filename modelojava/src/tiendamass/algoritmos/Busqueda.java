package tiendamass.algoritmos;

import tiendamass.modelo.*;

/**
 * Autor: Alessandra Condeso / Pretel Padilla Enrique / Angie Noemi Huaylinos / Karen Giron Faustino
 *
 * Clase Busqueda
 * Contiene métodos para buscar productos en una lista enlazada o en un arreglo.
 */

public class Busqueda {

    // Búsqueda secuencial en lista enlazada por nombre
    public static Producto busquedaSecuencial(ListaProductos lista, String nombreBuscado) {
        NodoProducto actual = lista.getCabeza();

        while (actual != null) {
            if (actual.getProducto().getNombre().equalsIgnoreCase(nombreBuscado)) {
                return actual.getProducto();
            }
            actual = actual.getSiguiente();
        }

        return null;
    }

    // Búsqueda binaria en arreglo ordenado por ID
    public static Producto busquedaBinaria(Producto[] productos, int idBuscado) {
        int inicio = 0;
        int fin = productos.length - 1;

        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            int idActual = productos[medio].getId();

            if (idActual == idBuscado) {
                return productos[medio];
            } else if (idActual < idBuscado) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }

        return null;
    }
}

