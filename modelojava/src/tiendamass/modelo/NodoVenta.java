/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiendamass.modelo;

/**
 *
 * @author ANGIE
 */
public class NodoVenta {
      private Venta venta;
    private NodoVenta anterior;

    // Constructor
    public NodoVenta(Venta venta) {
        this.venta = venta;
        this.anterior = null;
    }

    // Getter: venta almacenada
    public Venta getVenta() {
        return venta;
    }

    // Setter: venta
    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    // Getter: nodo anterior
    public NodoVenta getAnterior() {
        return anterior;
    }

    // Setter: nodo anterior
    public void setAnterior(NodoVenta anterior) {
        this.anterior = anterior;
    }
}
