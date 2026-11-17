package tiendamass.vista;

import tiendamass.modelo.*;

import javax.swing.*;
import java.awt.*;

/**
 * Autor: Alessandra Condeso / Pretel Padilla Enrique / Angie Noemi Huaylinos / Karen Giron Faustino
 *
 * Clase VentanaHistorial
 * Interfaz gráfica para visualizar el historial de ventas en TiendaMass.
 * Permite filtrar por cliente, por fecha, y ver todas las ventas registradas.
 */
public class VentanaHistorial extends JFrame {

    private JTextArea areaHistorial;
    private JTextField txtBuscarCliente, txtBuscarFecha;
    private JButton btnActualizar, btnFiltrarCliente, btnFiltrarFecha;
    private PilaVentas historial;

    // Constructor: inicializa la ventana y sus componentes
    public VentanaHistorial(PilaVentas historialVentas) {
        this.historial = historialVentas;

        setTitle("Historial de Ventas - TiendaMass");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Colores institucionales
        Color massAzul = new Color(0, 51, 102);
        Color massAmarillo = new Color(255, 204, 0);

        // Encabezado con título
        JPanel encabezado = new JPanel(new BorderLayout());
        encabezado.setPreferredSize(new Dimension(800, 60));
        encabezado.setBackground(massAzul);

        JLabel titulo = new JLabel("Historial de Ventas", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        titulo.setForeground(Color.WHITE);
        encabezado.add(titulo, BorderLayout.CENTER);
        add(encabezado, BorderLayout.NORTH);

        // Panel de filtros
        JPanel panelFiltros = new JPanel(new GridLayout(2, 3, 10, 5));
        panelFiltros.setBorder(BorderFactory.createTitledBorder("Filtros de Búsqueda"));

        txtBuscarCliente = new JTextField();
        txtBuscarFecha = new JTextField();

        btnFiltrarCliente = new JButton("Filtrar por Cliente");
        btnFiltrarFecha = new JButton("Filtrar por Fecha");
        btnActualizar = new JButton("Ver Todo");

        for (JButton btn : new JButton[]{btnFiltrarCliente, btnFiltrarFecha, btnActualizar}) {
            btn.setFont(new Font("SansSerif", Font.BOLD, 12));
            btn.setBackground(massAmarillo);
            btn.setForeground(massAzul);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createLineBorder(massAzul, 2, true));
        }

        panelFiltros.add(new JLabel("Cliente:"));
        panelFiltros.add(txtBuscarCliente);
        panelFiltros.add(btnFiltrarCliente);
        panelFiltros.add(new JLabel("Fecha (dd/MM/yyyy):"));
        panelFiltros.add(txtBuscarFecha);
        panelFiltros.add(btnFiltrarFecha);
        add(panelFiltros, BorderLayout.CENTER);

        // Área de texto para mostrar historial
        areaHistorial = new JTextArea();
        areaHistorial.setEditable(false);
        areaHistorial.setFont(new Font("Monospaced", Font.PLAIN, 13));
        areaHistorial.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scroll = new JScrollPane(areaHistorial);
        scroll.setBorder(BorderFactory.createTitledBorder("Ventas Registradas"));
        add(scroll, BorderLayout.SOUTH);

        // Panel inferior con botón de actualización
        JPanel panelBoton = new JPanel(new BorderLayout());
        JPanel botonera = new JPanel(new FlowLayout(FlowLayout.CENTER));
        botonera.add(btnActualizar);
        panelBoton.add(botonera, BorderLayout.CENTER);

        JPanel bordeInferior = new JPanel();
        bordeInferior.setPreferredSize(new Dimension(800, 5));
        bordeInferior.setBackground(massAmarillo);
        panelBoton.add(bordeInferior, BorderLayout.SOUTH);
        add(panelBoton, BorderLayout.PAGE_END);

        // Acciones de los botones
        btnActualizar.addActionListener(e -> mostrarHistorial());
        btnFiltrarCliente.addActionListener(e -> filtrarPorCliente(txtBuscarCliente.getText().trim()));
        btnFiltrarFecha.addActionListener(e -> filtrarPorFecha(txtBuscarFecha.getText().trim()));

        // Mostrar historial al iniciar
        mostrarHistorial();
    }

    // Muestra todas las ventas registradas
    private void mostrarHistorial() {
        areaHistorial.setText("");
        NodoVenta actual = historial.getCima();

        if (actual == null) {
            areaHistorial.setText("No hay ventas registradas.");
            return;
        }

        int contador = 1;
        double totalGlobal = 0;

        while (actual != null) {
            Venta venta = actual.getVenta();
            areaHistorial.append("Venta #" + contador + "\n");
            areaHistorial.append("Cliente: " + venta.getCliente() + "\n");
            areaHistorial.append("Fecha: " + venta.getFecha() + "\n");
            areaHistorial.append("Productos:\n");

            NodoItemVenta nodo = venta.getProductosVendidos().getFrente();
            while (nodo != null) {
                ItemVenta item = nodo.getItem();
                Producto p = item.getProducto();
                int cantidad = item.getCantidad();
                double subtotal = p.getPrecio() * cantidad;

                areaHistorial.append(String.format("   • %-20s x%-3d = S/ %.2f\n", p.getNombre(), cantidad, subtotal));
                nodo = nodo.getSiguiente();
            }

            areaHistorial.append("Total: S/ " + String.format("%.2f", venta.getTotal()) + "\n");
            areaHistorial.append("────────────────────────────────────────────\n");

            totalGlobal += venta.getTotal();
            actual = actual.getAnterior();
            contador++;
        }

        areaHistorial.append("\nTotal acumulado de ventas: S/ " + String.format("%.2f", totalGlobal));
    }

    // Filtra ventas por nombre de cliente
    private void filtrarPorCliente(String nombre) {
        areaHistorial.setText("");

        if (nombre.isEmpty()) {
            areaHistorial.setText("Ingresa un nombre de cliente para filtrar.");
            return;
        }

        NodoVenta actual = historial.getCima();
        int contador = 1;
        double totalCliente = 0;
        boolean encontrado = false;

        while (actual != null) {
            Venta venta = actual.getVenta();
            if (venta.getCliente().toLowerCase().contains(nombre.toLowerCase())) {
                areaHistorial.append("Venta #" + contador + "\n");
                areaHistorial.append("Cliente: " + venta.getCliente() + "\n");
                areaHistorial.append("Fecha: " + venta.getFecha() + "\n");
                areaHistorial.append("Productos:\n");

                NodoItemVenta nodo = venta.getProductosVendidos().getFrente();
                while (nodo != null) {
                    ItemVenta item = nodo.getItem();
                    Producto p = item.getProducto();
                    int cantidad = item.getCantidad();
                    double subtotal = p.getPrecio() * cantidad;

                    areaHistorial.append(String.format("   • %-20s x%-3d = S/ %.2f\n", p.getNombre(), cantidad, subtotal));
                    nodo = nodo.getSiguiente();
                }

                areaHistorial.append("Total: S/ " + String.format("%.2f", venta.getTotal()) + "\n");
                areaHistorial.append("────────────────────────────────────────────\n");

                totalCliente += venta.getTotal();
                encontrado = true;
            }
            actual = actual.getAnterior();
            contador++;
        }

        if (!encontrado) {
            areaHistorial.setText("No se encontraron ventas para el cliente ingresado.");
        } else {
            areaHistorial.append("\nTotal de ventas del cliente: S/ " + String.format("%.2f", totalCliente));
        }
    }

    // Filtra ventas por fecha
    private void filtrarPorFecha(String fecha) {
        areaHistorial.setText("");

        if (fecha.isEmpty()) {
            areaHistorial.setText("Ingresa una fecha en formato dd/MM/yyyy.");
            return;
        }

        NodoVenta actual = historial.getCima();
        int contador = 1;
        double totalFecha = 0;
        boolean encontrado = false;

        while (actual != null) {
            Venta venta = actual.getVenta();
            if (venta.getFecha().startsWith(fecha)) {
                areaHistorial.append("Venta #" + contador + "\n");
                areaHistorial.append("Cliente: " + venta.getCliente() + "\n");
                areaHistorial.append("Fecha: " + venta.getFecha() + "\n");
                areaHistorial.append("Productos:\n");

                NodoItemVenta nodo = venta.getProductosVendidos().getFrente();
                while (nodo != null) {
                    ItemVenta item = nodo.getItem();
                    Producto p = item.getProducto();
                    int cantidad = item.getCantidad();
                    double subtotal = p.getPrecio() * cantidad;
                                      areaHistorial.append(String.format("   • %-20s x%-3d = S/ %.2f\n", 
                            p.getNombre(), cantidad, subtotal));
                    nodo = nodo.getSiguiente();
                }

                areaHistorial.append("Total: S/ " + String.format("%.2f", venta.getTotal()) + "\n");
                areaHistorial.append("────────────────────────────────────────────\n");

                totalFecha += venta.getTotal();
                encontrado = true;
            }
            actual = actual.getAnterior();
            contador++;
        }

        if (!encontrado) {
            areaHistorial.setText("No se encontraron ventas para la fecha ingresada.");
        } else {
            areaHistorial.append("\nTotal de ventas en esa fecha: S/ " + String.format("%.2f", totalFecha));
        }
    }
}

 