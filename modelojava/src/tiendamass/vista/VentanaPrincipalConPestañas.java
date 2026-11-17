package tiendamass.vista;

import tiendamass.modelo.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VentanaPrincipalConPestañas extends JFrame {

   private ListaClientes listaClientes;
    private ListaProductos listaProductos;
    private ColaPedidos colaPedidos;
    private PilaVentas historialVentas;
    private RegistroVentas registroVentas;
    private ArbolCategorias arbolCategorias;

    public VentanaPrincipalConPestañas(ListaClientes listaClientes, ListaProductos listaProductos,
                                       ColaPedidos colaPedidos, PilaVentas historialVentas,
                                       RegistroVentas registroVentas) {

        this.listaClientes = listaClientes;
        this.listaProductos = listaProductos;
        this.colaPedidos = colaPedidos;
        this.historialVentas = historialVentas;
        this.registroVentas = registroVentas;
        this.arbolCategorias = new ArbolCategorias();

        setTitle("TiendaMass - Sistema de Gestión");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        //  Colores corporativos Mass
        Color massAzul = new Color(0, 51, 102);
        Color massAmarillo = new Color(255, 204, 0);

        //  Encabezado con logo y título
        JPanel encabezado = new JPanel(null);
        encabezado.setPreferredSize(new Dimension(1000, 100));
        encabezado.setBackground(massAzul);

        ImageIcon iconoLogo = new ImageIcon("src/tiendamass/vista/logo_tiendamass.png");
        Image imagenLogo = iconoLogo.getImage().getScaledInstance(160, 80, Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(imagenLogo));
        lblLogo.setBounds(20, 10, 160, 80);
        encabezado.add(lblLogo);

        JLabel lblTituloEncabezado = new JLabel("TiendaMass - Gestión Total");
        lblTituloEncabezado.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTituloEncabezado.setForeground(Color.WHITE);
        lblTituloEncabezado.setBounds(200, 30, 600, 40);
        encabezado.add(lblTituloEncabezado);

        add(encabezado, BorderLayout.NORTH);

        //  Menú superior (tu código intacto)
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(massAzul);
        menuBar.setForeground(Color.WHITE);

        UIManager.put("Menu.foreground", Color.WHITE);
        UIManager.put("MenuItem.foreground", Color.WHITE);

        // Menú Usuario
        JMenu menuUsuario = new JMenu("Usuario");
        menuUsuario.setForeground(Color.WHITE);

        JMenuItem itemCerrarSesion = new JMenuItem("Cerrar sesión");
        itemCerrarSesion.setForeground(Color.WHITE);

        JMenuItem itemAyuda = new JMenuItem("Ayuda");
        itemAyuda.setForeground(Color.WHITE);

        itemCerrarSesion.addActionListener(e -> {
            dispose();
            new VentanaLogin().setVisible(true);
        });

        itemAyuda.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "Sistema TiendaMass\nDesarrollado por el equipo de Ingeniería de Software.",
                        "Acerca de", JOptionPane.INFORMATION_MESSAGE));

        menuUsuario.add(itemCerrarSesion);
        menuUsuario.add(itemAyuda);
        menuBar.add(menuUsuario);

        // Menú Clientes
        JMenu menuClientes = new JMenu("Clientes");
        menuClientes.setForeground(Color.WHITE);

        JMenuItem itemVerClientes = new JMenuItem("Ver Clientes");
        itemVerClientes.setForeground(Color.WHITE);
        itemVerClientes.addActionListener(e -> new VentanaClientes(listaClientes).setVisible(true));
        menuClientes.add(itemVerClientes);
        menuBar.add(menuClientes);

        // Menú Productos
        JMenu menuProductos = new JMenu("Productos");
        menuProductos.setForeground(Color.WHITE);

        JMenuItem itemVerProductos = new JMenuItem("Ver Productos");
        itemVerProductos.setForeground(Color.WHITE);
        itemVerProductos.addActionListener(e -> new VentanaProductos(arbolCategorias).setVisible(true));
        menuProductos.add(itemVerProductos);
        menuBar.add(menuProductos);

        // Menú Pedidos
        JMenu menuPedidos = new JMenu("Pedidos");
        menuPedidos.setForeground(Color.WHITE);

        JMenuItem itemVerPedidos = new JMenuItem("Ver Pedidos");
        itemVerPedidos.setForeground(Color.WHITE);
        itemVerPedidos.addActionListener(e -> new VentanaPedidos(colaPedidos).setVisible(true));

        JMenuItem itemAgregarPedido = new JMenuItem("Agregar Pedido");
        itemAgregarPedido.setForeground(Color.WHITE);
        itemAgregarPedido.addActionListener(e -> new VentanaAgregarPedido(listaClientes, listaProductos, colaPedidos).setVisible(true));

        menuPedidos.add(itemVerPedidos);
        menuPedidos.add(itemAgregarPedido);
        menuBar.add(menuPedidos);

        // Menú Ventas
        JMenu menuVentas = new JMenu("Ventas");
        menuVentas.setForeground(Color.WHITE);

        JMenuItem itemRealizarVenta = new JMenuItem("Realizar Venta");
        itemRealizarVenta.setForeground(Color.WHITE);
        itemRealizarVenta.addActionListener(e -> new VentanaVentas(arbolCategorias, registroVentas, historialVentas).setVisible(true));

        menuVentas.add(itemRealizarVenta);
        menuBar.add(menuVentas);

        // Menú Historial
        JMenu menuHistorial = new JMenu("Historial");
        menuHistorial.setForeground(Color.WHITE);

        JMenuItem itemVerHistorial = new JMenuItem("Ver Historial");
        itemVerHistorial.setForeground(Color.WHITE);
        itemVerHistorial.addActionListener(e -> mostrarHistorialDesdeArchivo());
        menuHistorial.add(itemVerHistorial);
        menuBar.add(menuHistorial);

        setJMenuBar(menuBar);
        
        JMenu menuReportes = new JMenu("Reportes");
        JMenuItem itemVerReportes = new JMenuItem("Ver Reportes");

        itemVerReportes.addActionListener(e -> {
            new VentanaReportes(listaClientes, arbolCategorias, colaPedidos, historialVentas, registroVentas).setVisible(true);
        });

        menuReportes.add(itemVerReportes);
        menuBar.add(menuReportes);


        //  Panel inferior
        JPanel panelInfo = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        panelInfo.setBackground(new Color(245, 245, 245));

        String fechaActual = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date());
        JLabel lblUsuario = new JLabel("Usuario: " + System.getProperty("user.name"));
        JLabel lblFecha = new JLabel("Fecha: " + fechaActual);
        lblUsuario.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblFecha.setFont(new Font("SansSerif", Font.PLAIN, 14));
        panelInfo.add(lblUsuario);
        panelInfo.add(lblFecha);
        add(panelInfo, BorderLayout.SOUTH);

        // Panel central con diseño Mass
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout());
        panelCentral.setBackground(new Color(245, 245, 245));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

       

        // Panel de botones
        JPanel panelBotones = new JPanel(new GridLayout(2, 2, 20, 20));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(30, 140, 30, 140));
        panelBotones.setOpaque(false);

        JButton btnVentaRapida = new JButton("Venta Rápida");
        JButton btnBuscarProducto = new JButton("Buscar Producto");
        JButton btnResumenVentas = new JButton("Historial");
        JButton btnEstadisticas = new JButton("Estadísticas");

        JButton[] botones = {btnVentaRapida, btnBuscarProducto, btnResumenVentas, btnEstadisticas};

        for (JButton btn : botones) {
            btn.setFont(new Font("SansSerif", Font.BOLD, 14));
            btn.setBackground(massAmarillo);
            btn.setForeground(massAzul);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createLineBorder(massAzul, 2, true));
            btn.setPreferredSize(new Dimension(140, 50));
            panelBotones.add(btn);
        }

        btnVentaRapida.addActionListener(e -> new VentanaVentas(arbolCategorias, registroVentas, historialVentas).setVisible(true));
        btnBuscarProducto.addActionListener(e -> new VentanaProductos(arbolCategorias).setVisible(true));
        btnResumenVentas.addActionListener(e -> mostrarHistorialDesdeArchivo());
        btnEstadisticas.addActionListener(e -> mostrarEstadisticas());

        panelCentral.add(panelBotones, BorderLayout.CENTER);
        add(panelCentral, BorderLayout.CENTER);

        // Guardar ventas al cerrar
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                registroVentas.guardarEnArchivo("src/recursos/ventas.txt");
            }
        });
    }

    private void mostrarHistorialDesdeArchivo() {
        String ventasGuardadas = registroVentas.cargarDesdeArchivoComoTexto("src/recursos/ventas.txt");
        JTextArea areaHistorial = new JTextArea(ventasGuardadas);
        areaHistorial.setEditable(false);
        areaHistorial.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(areaHistorial);
        scroll.setPreferredSize(new Dimension(800, 400));
        JOptionPane.showMessageDialog(this, scroll, "Historial de Ventas", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarEstadisticas() {
        int totalClientes = listaClientes.contarClientes();
        int totalProductos = listaProductos.contarProductos();
        int totalVentas = contarVentas();

        String mensaje = " Estadísticas del sistema:\n\n" +
                "Clientes registrados: " + totalClientes + "\n" +
                "Productos disponibles: " + totalProductos + "\n" +
                "Ventas registradas: " + totalVentas;

        JOptionPane.showMessageDialog(this, mensaje, "Estadísticas", JOptionPane.INFORMATION_MESSAGE);
    }

    private int contarVentas() {
        int contador = 0;
        NodoVentaRegistro actual = registroVentas.getInicio();
        while (actual != null) {
            contador++;
            actual = actual.getSiguiente();
        }
        return contador;
    }
}
