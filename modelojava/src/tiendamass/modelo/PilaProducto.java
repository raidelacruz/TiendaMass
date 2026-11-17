/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiendamass.modelo;
import tiendamass.modelo.Producto;

/**
 * Autor: Alessandra Condeso / Pretel Padilla Enrique/ Angie Noemi Huaylinos/ Karen Giron Faustino
 * Clase PilaProducto
 * Implementa una pila para almacenar productos eliminados.
 * Utiliza nodos enlazados para simular el comportamiento LIFO.
 */
public class PilaProducto {

    // Nodo superior de la pila
    private NodoPila cima;

    /**
     * Clase interna que representa cada nodo de la pila.
     */
    private static class NodoPila {
        Producto producto;
        NodoPila siguiente;

        NodoPila(Producto producto) {
            this.producto = producto;
        }
    }

    /**
     * Apila un producto en la cima.
     * @param producto Producto eliminado
     */
    public void apilar(Producto producto) {
        NodoPila nuevo = new NodoPila(producto);
        nuevo.siguiente = cima;
        cima = nuevo;
    }

    /**
     * Desapila el producto más reciente.
     * @return Producto eliminado más recientemente
     */
    public Producto desapilar() {
        if (cima == null) return null;
        Producto p = cima.producto;
        cima = cima.siguiente;
        return p;
    }

    /**
     * Verifica si la pila está vacía.
     * @return true si no hay productos
     */
    public boolean estaVacia() {
        return cima == null;
    }

    /**
     * Devuelve el último producto eliminado sin quitarlo.
     * @return Producto en la cima
     */
    public Producto verUltimoEliminado() {
        return cima != null ? cima.producto : null;
    }
}
