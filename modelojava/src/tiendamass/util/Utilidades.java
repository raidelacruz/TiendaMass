/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiendamass.util;

import tiendamass.modelo.*;

/**
 * Clase Utilidades
 * Métodos auxiliares para trabajar con listas enlazadas y arreglos.
 */

public class Utilidades {

    // Convierte una ListaProductos a un arreglo de Producto[]
    public static Producto[] convertirAArreglo(ListaProductos lista) {
        int n = lista.contarProductos();
        Producto[] arreglo = new Producto[n];

        NodoProducto actual = lista.getCabeza();
        int i = 0;

        while (actual != null) {
            arreglo[i] = actual.getProducto();
            actual = actual.getSiguiente();
            i++;
        }

        return arreglo;
    }
}
