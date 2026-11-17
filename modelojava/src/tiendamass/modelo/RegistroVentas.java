package tiendamass.modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Autor: Alessandra Condeso / Pretel Padilla Enrique / Angie Noemi Huaylinos / Karen Giron Faustino
 *
 * Clase RegistroVentas
 * Almacena todas las ventas realizadas en la tienda usando una lista enlazada.
 * Cada nodo representa una venta y se enlaza al siguiente en orden de registro.
 */
public class RegistroVentas {

    private NodoVentaRegistro inicio; // Primer nodo de la lista (venta más antigua)
    private NodoVentaRegistro fin;    // Último nodo de la lista (venta más reciente)

    // Constructor: inicializa la lista vacía
    public RegistroVentas() {
        this.inicio = null;
        this.fin = null;
    }

    /**
     * Registra una nueva venta al final de la lista enlazada.
     * @param venta Venta realizada
     */
    public void registrarVenta(Venta venta) {
        NodoVentaRegistro nuevo = new NodoVentaRegistro(venta);

        if (inicio == null) {
            inicio = nuevo;
            fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
    }

    /**
     * Verifica si el registro está vacío.
     * @return true si no hay ventas registradas
     */
    public boolean estaVacio() {
        return inicio == null;
    }

    /**
     * Devuelve todas las ventas registradas como texto.
     * @return String con el historial de ventas
     */
    public String mostrarVentas() {
        StringBuilder sb = new StringBuilder();
        NodoVentaRegistro actual = inicio;

        while (actual != null) {
            sb.append(actual.getVenta().toString()).append("\n");
            sb.append("---------------------------\n");
            actual = actual.getSiguiente();
        }

        return sb.toString();
    }

    /**
     * Genera un nuevo ID incremental para una venta.
     * @return ID siguiente disponible
     */
    public int generarNuevoId() {
        int id = 1;
        NodoVentaRegistro actual = inicio;
        while (actual != null) {
            id++;
            actual = actual.getSiguiente();
        }
        return id;
    }

    /**
     * Guarda el historial de ventas en un archivo de texto plano.
     * @param ruta Ruta del archivo donde se guardarán las ventas
     */
    public void guardarEnArchivo(String ruta) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            NodoVentaRegistro actual = inicio;
            while (actual != null) {
                writer.write(actual.getVenta().toString());
                writer.newLine();
                writer.write("---------------------------");
                writer.newLine();
                actual = actual.getSiguiente();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar ventas: " + e.getMessage());
        }
    }

    /**
     * Carga el historial de ventas desde un archivo como texto plano.
     * Este método no reconstruye los objetos Venta, solo devuelve el contenido para mostrar.
     * @param ruta Ruta del archivo de ventas
     * @return Texto con el historial de ventas
     */
    public String cargarDesdeArchivoComoTexto(String ruta) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                sb.append(linea).append("\n");
            }
        } catch (IOException e) {
            sb.append("No se pudo cargar el historial de ventas.");
        }
        return sb.toString();
    }

    /**
     * Guarda las ventas en un archivo de texto estructurado.
     * Cada venta se guarda como bloque con cliente, fecha y productos vendidos.
     * Formato:
     * VENTA
     * cliente
     * fecha
     * id,nombre,precio,cantidad
     * ...
     * FIN
     */
    public void guardarVentasEstructuradas(String ruta) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            NodoVentaRegistro actual = inicio;
            while (actual != null) {
                Venta venta = actual.getVenta();
                writer.write("VENTA");
                writer.newLine();
                writer.write(venta.getCliente());
                writer.newLine();
                writer.write(venta.getFecha());
                writer.newLine();

                NodoItemVenta nodo = venta.getProductosVendidos().getFrente();
                while (nodo != null) {
                    ItemVenta item = nodo.getItem();
                    Producto p = item.getProducto();
                    int cantidad = item.getCantidad();
                    writer.write(p.getId() + "," + p.getNombre() + "," + p.getPrecio() + "," + cantidad);
                    writer.newLine();
                    nodo = nodo.getSiguiente();
                }

                writer.write("FIN");
                writer.newLine();
                actual = actual.getSiguiente();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar ventas estructuradas: " + e.getMessage());
        }
    }

    /**
     * Carga ventas desde un archivo estructurado y reconstruye los objetos Venta.
     * Cada bloque debe comenzar con "VENTA" y terminar con "FIN".
     * @param ruta Ruta del archivo estructurado
     */
    public void cargarVentasDesdeArchivo(String ruta) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
            String linea;
            Venta ventaActual = null;

            while ((linea = reader.readLine()) != null) {
                if (linea.equals("VENTA")) {
                    ventaActual = new Venta("", "");
                } else if (ventaActual != null && ventaActual.getCliente().isEmpty()) {
                    ventaActual.setCliente(linea);
                } else if (ventaActual != null && ventaActual.getFecha().isEmpty()) {
                    ventaActual.setFecha(linea);
                } else if (linea.equals("FIN")) {
                    registrarVenta(ventaActual);
                    ventaActual = null;
                } else if (ventaActual != null) {
                    String[] partes = linea.split(",");
                    if (partes.length == 4) {
                        try {
                            int id = Integer.parseInt(partes[0]);
                            String nombre = partes[1];
                            double precio = Double.parseDouble(partes[2]);
                            int cantidad = Integer.parseInt(partes[3]);

                            Producto producto = new Producto(id, nombre, precio, "");
                            ventaActual.agregarProducto(producto, cantidad);
                        } catch (Exception e) {
                            System.out.println("Error al cargar producto de venta: " + linea);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo cargar ventas desde archivo: " + e.getMessage());
        }
    }

    // Getters y setters
    public NodoVentaRegistro getInicio() {
        return inicio;
    }

    public void setInicio(NodoVentaRegistro inicio) {
        this.inicio = inicio;
    }

    public NodoVentaRegistro getFin() {
        return fin;
    }

    public void setFin(NodoVentaRegistro fin) {
        this.fin = fin;
    }
}
