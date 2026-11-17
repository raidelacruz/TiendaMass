/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiendamass.modelo;

/**
 *
 * @author ANGIE
 */
public class NodoItemVenta {
    private ItemVenta item;
    private NodoItemVenta siguiente;

    public NodoItemVenta(ItemVenta item) {
        this.item = item;
        this.siguiente = null;
    }

    public ItemVenta getItem() {
        return item;
    }

    public NodoItemVenta getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoItemVenta siguiente) {
        this.siguiente = siguiente;
    }
}
