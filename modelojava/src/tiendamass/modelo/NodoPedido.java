/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiendamass.modelo;

/**
 *
 * @author ANGIE
 */
public class NodoPedido {
    private Factura factura;
    private NodoPedido siguiente;

    // Constructor
    public NodoPedido(Factura factura) {
        this.factura = factura;
        this.siguiente = null;
    }

    // Setters

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public void setSiguiente(NodoPedido siguiente) {
        this.siguiente = siguiente;
    }

    // Getters

    public Factura getFactura() {
        return factura;
    }

    public NodoPedido getSiguiente() {
        return siguiente;
    }
}
