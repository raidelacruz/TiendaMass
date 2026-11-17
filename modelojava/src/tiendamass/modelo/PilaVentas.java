/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiendamass.modelo;

/**
 * Autor: Alessandra Condeso / Pretel Padilla Enrique / Angie Noemi Huaylinos / Karen Giron Faustino
 *
 * Clase PilaVentas
 * Representa una pila de ventas registradas (historial).
 * Implementada con nodos, permite apilar y desapilar ventas.
 */
public class PilaVentas {

    private NodoVenta cima;

    // Constructor
    public PilaVentas() {
        this.cima = null;
    }

    // Verifica si la pila está vacía
    public boolean estaVacia() {
        return cima == null;
    }

    // Apila una venta (push)
    public void apilar(Venta venta) {
        NodoVenta nuevo = new NodoVenta(venta);
        nuevo.setAnterior(cima);
        cima = nuevo;
    }

    // Desapila la última venta (pop)
    public Venta desapilar() {
        if (estaVacia()) {
            return null;
        }

        Venta venta = cima.getVenta();
        cima = cima.getAnterior();
        return venta;
    }

    // Devuelve la última venta sin eliminarla (peek)
    public Venta verUltimaVenta() {
        if (estaVacia()) {
            return null;
        }

        return cima.getVenta();
    }
    
public String clienteMasFrecuente() {
    String[] nombres = new String[100];
    int[] repeticiones = new int[100];
    int total = 0;

    NodoVenta actual = cima;
    while (actual != null) {
        String nombre = actual.getVenta().getCliente();
        boolean encontrado = false;

        for (int i = 0; i < total; i++) {
            if (nombres[i].equals(nombre)) {
                repeticiones[i]++;
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            nombres[total] = nombre;
            repeticiones[total] = 1;
            total++;
        }

        actual = actual.getAnterior();
    }

    int max = 0;
    String top = "Ninguno";
    for (int i = 0; i < total; i++) {
        if (repeticiones[i] > max) {
            max = repeticiones[i];
            top = nombres[i];
        }
    }

    return top;
}

    public String productoMasVendido() {
        String[] nombres = new String[200];
        int[] cantidades = new int[200];
        int total = 0;

        NodoVenta actual = cima;
        while (actual != null) {
            NodoItemVenta nodo = actual.getVenta().getProductosVendidos().getFrente();
            while (nodo != null) {
                String nombre = nodo.getItem().getProducto().getNombre();
                int cantidad = nodo.getItem().getCantidad();
                boolean encontrado = false;

                for (int i = 0; i < total; i++) {
                    if (nombres[i].equals(nombre)) {
                        cantidades[i] += cantidad;
                        encontrado = true;
                        break;
                    }
                }

                if (!encontrado) {
                    nombres[total] = nombre;
                    cantidades[total] = cantidad;
                    total++;
                }

                nodo = nodo.getSiguiente();
            }

            actual = actual.getAnterior();
        }

        int max = 0;
        String top = "Ninguno";
        for (int i = 0; i < total; i++) {
            if (cantidades[i] > max) {
                max = cantidades[i];
                top = nombres[i];
            }
        }

        return top;
    }

    public double totalVendido() {
        double total = 0;
        NodoVenta actual = cima;
        while (actual != null) {
            total += actual.getVenta().getTotal();
            actual = actual.getAnterior();
        }
        return total;
    }    

    // Muestra todas las ventas en la pila
    public String mostrarHistorial() {
        StringBuilder sb = new StringBuilder();
        NodoVenta actual = cima;

        while (actual != null) {
            sb.append(actual.getVenta().toString()).append("\n");
            sb.append("---------------------------\n");
            actual = actual.getAnterior();
        }

        return sb.toString();
    }

    // Cuenta cuántas ventas hay en la pila
    public int contarVentas() {
        int contador = 0;
        NodoVenta actual = cima;

        while (actual != null) {
            contador++;
            actual = actual.getAnterior();
        }

        return contador;
    }
    

    // Getter: cima de la pila
    public NodoVenta getCima() {
        return cima;
    }
    

    // Setter: cima de la pila
    public void setCima(NodoVenta cima) {
        this.cima = cima;
    }
}
