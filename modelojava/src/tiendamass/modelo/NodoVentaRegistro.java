/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiendamass.modelo;

/**
 * NodoVentaRegistro representa un nodo de la lista de ventas.
 * Se usa en RegistroVentas para almacenar ventas en orden.
 */
public class NodoVentaRegistro {

    private Venta venta;
    private NodoVentaRegistro siguiente;

    // Constructor
    public NodoVentaRegistro(Venta venta) {
        this.venta = venta;
        this.siguiente = null;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public NodoVentaRegistro getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoVentaRegistro siguiente) {
        this.siguiente = siguiente;
    }
}
