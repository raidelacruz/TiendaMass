/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiendamass.modelo;

/**
 *
 * @author ANGIE
 */
public class NodoItem {
    private ItemVenta item;
    private NodoItem siguiente;

    public NodoItem(ItemVenta item) {
        this.item = item;
        this.siguiente = null;
    }

    public ItemVenta getItem() {
        return item;
    }

    public NodoItem getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoItem siguiente) {
        this.siguiente = siguiente;
    }
}
