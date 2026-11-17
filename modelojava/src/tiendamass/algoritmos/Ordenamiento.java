package tiendamass.algoritmos;

import tiendamass.modelo.*;

/**
 * Autor: Alessandra Condeso / Pretel Padilla Enrique / Angie Noemi Huaylinos / Karen Giron Faustino
 *
 * Clase Ordenamiento
 * Contiene algoritmos clásicos para ordenar productos en un arreglo.
 */

public class Ordenamiento {

    // Ordenamiento burbuja por precio (ascendente)
    public static void burbujaPorPrecio(Producto[] productos) {
        int n = productos.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (productos[j].getPrecio() > productos[j + 1].getPrecio()) {
                    Producto temp = productos[j];
                    productos[j] = productos[j + 1];
                    productos[j + 1] = temp;
                }
            }
        }
    }

    // QuickSort por nombre de producto (alfabético)
    public static void quickSortPorNombre(Producto[] productos, int inicio, int fin) {
        if (inicio < fin) {
            int indicePivote = particionar(productos, inicio, fin);
            quickSortPorNombre(productos, inicio, indicePivote - 1);
            quickSortPorNombre(productos, indicePivote + 1, fin);
        }
    }

    private static int particionar(Producto[] productos, int inicio, int fin) {
        Producto pivote = productos[fin];
        int i = inicio - 1;

        for (int j = inicio; j < fin; j++) {
            if (productos[j].getNombre().compareToIgnoreCase(pivote.getNombre()) <= 0) {
                i++;
                Producto temp = productos[i];
                productos[i] = productos[j];
                productos[j] = temp;
            }
        }

        Producto temp = productos[i + 1];
        productos[i + 1] = productos[fin];
        productos[fin] = temp;

        return i + 1;
    }
}

