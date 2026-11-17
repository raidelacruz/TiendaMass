package tiendamass.vista;

import tiendamass.modelo.*;

import javax.swing.*;
import java.awt.*;

/**
 * VentanaReportes
 * Módulo funcional para mostrar estadísticas y reportes del sistema TiendaMass.
 */
public class VentanaReportes extends JFrame {

    private JTextArea areaReportes;

    public VentanaReportes(ListaClientes listaClientes, ArbolCategorias arbolCategorias, ColaPedidos colaPedidos, PilaVentas historialVentas, RegistroVentas registroVentas) {
        setTitle("Reportes del Sistema - TiendaMass");
        setSize(750, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        //  Título
        JLabel titulo = new JLabel("Reportes y Estadísticas", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));
        add(titulo, BorderLayout.NORTH);

        //  Área de texto
        areaReportes = new JTextArea();
        areaReportes.setEditable(false);
        areaReportes.setFont(new Font("Monospaced", Font.PLAIN, 13));
        areaReportes.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scroll = new JScrollPane(areaReportes);
        scroll.setBorder(BorderFactory.createTitledBorder("Resultados del Sistema"));
        add(scroll, BorderLayout.CENTER);

        // Botón generar
        JButton btnGenerar = new JButton("Generar Reporte");
        btnGenerar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnGenerar.setBackground(new Color(255, 235, 59));
        btnGenerar.setFocusPainted(false);
        btnGenerar.setPreferredSize(new Dimension(180, 40));

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnGenerar);
        add(panelBoton, BorderLayout.SOUTH);

        // Acción
        btnGenerar.addActionListener(e -> generarReporte(listaClientes, arbolCategorias, colaPedidos, historialVentas, registroVentas));
    }

    private void generarReporte(ListaClientes listaClientes, ArbolCategorias arbolCategorias, ColaPedidos colaPedidos, PilaVentas historialVentas, RegistroVentas registroVentas) {
        StringBuilder sb = new StringBuilder();

        // Clientes
        int totalClientes = listaClientes.contarClientes();
        sb.append("Clientes registrados: ").append(totalClientes).append("\n");

        // Productos
        int totalProductos = arbolCategorias.contarTotalProductos();
        sb.append("Productos disponibles: ").append(totalProductos).append("\n");

        // Categorías
        int totalCategorias = arbolCategorias.contarCategorias();
        sb.append("Categorías registradas: ").append(totalCategorias).append("\n");

        // Pedidos
        int totalPedidos = colaPedidos.contarPedidos();
        sb.append("Pedidos pendientes: ").append(totalPedidos).append("\n");

        // Ventas
        int totalVentas = contarVentas(historialVentas);
        sb.append("Ventas registradas: ").append(totalVentas).append("\n");

        // Cliente más frecuente
        String clienteTop = historialVentas.clienteMasFrecuente();
        sb.append("Cliente con más compras: ").append(clienteTop).append("\n");

        // Producto más vendido
        String productoTop = historialVentas.productoMasVendido();
        sb.append("Producto más vendido: ").append(productoTop).append("\n");

        // Total vendido
        double totalSoles = historialVentas.totalVendido();
        sb.append("Total vendido: S/ ").append(String.format("%.2f", totalSoles)).append("\n");

        areaReportes.setText(sb.toString());
    }

    // Contar ventas desde la pila
    private int contarVentas(PilaVentas pila) {
        int contador = 0;
        NodoVenta actual = pila.getCima();
        while (actual != null) {
            contador++;
            actual = actual.getAnterior();
        }
        return contador;
    }
}
