/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiendamass.modelo;

/**
 * Clase Pedido
 * Representa un pedido realizado por un cliente.
 */

public class Pedido {

    private Cliente cliente;
    private Producto producto;
    private int cantidad;
    private String estado;

    // Constructor
    public Pedido(Cliente cliente, Producto producto, int cantidad, String estado) {
        this.cliente = cliente;
        this.producto = producto;
        this.cantidad = cantidad;
        this.estado = estado;
    }

    // Getters
    public Cliente getCliente() {
        return cliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getEstado() {
        return estado;
    }

    // Setters
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

