package tiendamass.modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Autor: Alessandra Condeso / Pretel Padilla Enrique / Angie Noemi Huaylinos / Karen Giron Faustino
 *
 * Clase ColaPedidos
 * Representa una cola de pedidos en espera por falta de stock.
 * Implementada con nodos, permite encolar y desencolar facturas pendientes.
 */
public class ColaPedidos {

    private NodoPedido frente;
    private NodoPedido fin;

    // Devuelve el primer nodo de la cola
    public NodoPedido getFrente() {
        return frente;
    }

    // Constructor: inicializa la cola vacía
    public ColaPedidos() {
        this.frente = null;
        this.fin = null;
    }

    // Verifica si la cola está vacía
    public boolean estaVacia() {
        return frente == null;
    }

    // Cuenta cuántos pedidos hay en la cola
    public int contarPedidos() {
        int contador = 0;
        NodoPedido actual = frente;
        while (actual != null) {
            contador++;
            actual = actual.getSiguiente();
        }
        return contador;
    }

    // Agrega una nueva factura al final de la cola
    public void encolar(Factura factura) {
        NodoPedido nuevo = new NodoPedido(factura);
        if (estaVacia()) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
    }

    // Elimina y devuelve la factura al frente de la cola
    public Factura desencolar() {
        if (estaVacia()) {
            return null;
        }
        Factura factura = frente.getFactura();
        frente = frente.getSiguiente();
        if (frente == null) {
            fin = null;
        }
        return factura;
    }

    // Muestra todas las facturas en espera
    public void mostrarPedidos() {
        NodoPedido actual = frente;
        while (actual != null) {
            actual.getFactura().mostrarFactura();
            System.out.println("---------------------------");
            actual = actual.getSiguiente();
        }
    }

    // Guarda todos los pedidos en un archivo de texto
    public void guardarEnArchivo(String ruta) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            NodoPedido actual = frente;
            while (actual != null) {
                Factura factura = actual.getFactura();
                writer.write("PEDIDO");
                writer.newLine();

                Cliente c = factura.getCliente();
                writer.write(c.getId() + "," + c.getNombre() + "," + c.getApPaterno() + "," + c.getApMaterno());
                writer.newLine();

                writer.write(factura.getFecha());
                writer.newLine();

                NodoDetalleFactura nodo = factura.getDetalles();
                while (nodo != null) {
                    Producto p = nodo.getProducto();
                    int cantidad = nodo.getCantidad();
                    writer.write(p.getId() + "," + p.getNombre() + "," + p.getPrecio() + "," + cantidad);
                    writer.newLine();
                    nodo = nodo.getSiguiente();
                }

                writer.write("FIN");
                writer.newLine();
                actual = actual.getSiguiente();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar pedidos: " + e.getMessage());
        }
    }

    // Carga pedidos desde un archivo de texto y los reconstruye en la cola
    public void cargarDesdeArchivo(String ruta) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
            String linea;
            Factura facturaActual = null;

            while ((linea = reader.readLine()) != null) {
                if (linea.equals("PEDIDO")) {
                    facturaActual = new Factura();
                } else if (facturaActual != null && facturaActual.getCliente() == null) {
                    // Línea del cliente: id,nombre,apPaterno,apMaterno
                    String[] partes = linea.split(",");
                    if (partes.length == 4) {
                        try {
                            int id = Integer.parseInt(partes[0]);
                            String nombre = partes[1];
                            String apPaterno = partes[2];
                            String apMaterno = partes[3];
                            Cliente cliente = new Cliente(id, nombre, apPaterno, apMaterno);
                            facturaActual.setCliente(cliente);
                        } catch (Exception e) {
                            System.out.println("Error al cargar cliente del pedido: " + linea);
                        }
                    }
                } else if (facturaActual != null && facturaActual.getFecha() == null) {
                    // Línea de fecha
                    facturaActual.setFecha(linea);
                } else if (linea.equals("FIN")) {
                    // Fin de una factura, se encola
                    encolar(facturaActual);
                    facturaActual = null;
                } else if (facturaActual != null) {
                    // Línea de producto: id,nombre,precio,cantidad
                    String[] partes = linea.split(",");
                    if (partes.length == 4) {
                        try {
                            int id = Integer.parseInt(partes[0]);
                            String nombre = partes[1];
                            double precio = Double.parseDouble(partes[2]);
                            int cantidad = Integer.parseInt(partes[3]);

                            Producto producto = new Producto(id, nombre, precio, "");
                            facturaActual.agregarProducto(producto, cantidad);
                        } catch (Exception e) {
                            System.out.println("Error al cargar producto del pedido: " + linea);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo cargar pedidos desde archivo: " + e.getMessage());
        }
    }
}
