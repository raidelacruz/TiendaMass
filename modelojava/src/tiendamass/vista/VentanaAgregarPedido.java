package tiendamass.vista;

import tiendamass.modelo.*;

import javax.swing.*;
import java.awt.*;

/**
 * Autor: Alessandra Condeso / Pretel Padilla Enrique / Angie Noemi Huaylinos / Karen Giron Faustino
 *
 * Clase VentanaAgregarPedido
 * Ventana gráfica que permite registrar un pedido pendiente cuando no hay stock disponible.
 * El pedido se convierte en una factura y se encola en la estructura ColaPedidos.
 */
public class VentanaAgregarPedido extends JFrame {

    private JTextField txtIdCliente, txtIdProducto, txtCantidad;
    private JButton btnAgregar;
    private JTextArea areaSalida;

    private ListaClientes listaClientes;
    private ListaProductos listaProductos;
    private ColaPedidos colaPedidos;

    private static int contadorFacturas = 1; // ID incremental para facturas

    // Constructor: recibe las estructuras necesarias y configura la interfaz
    public VentanaAgregarPedido(ListaClientes clientes, ListaProductos productos, ColaPedidos cola) {
        this.listaClientes = clientes;
        this.listaProductos = productos;
        this.colaPedidos = cola;

        setTitle("Agregar Pedido a la Cola");
        setSize(550, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel de entrada de datos
        JPanel panelEntrada = new JPanel(new GridLayout(4, 2, 10, 10));
        panelEntrada.setBorder(BorderFactory.createTitledBorder("Datos del Pedido"));

        txtIdCliente = new JTextField();
        txtIdProducto = new JTextField();
        txtCantidad = new JTextField();

        panelEntrada.add(new JLabel("ID Cliente:"));
        panelEntrada.add(txtIdCliente);
        panelEntrada.add(new JLabel("ID Producto:"));
        panelEntrada.add(txtIdProducto);
        panelEntrada.add(new JLabel("Cantidad:"));
        panelEntrada.add(txtCantidad);

        // Botón para agregar pedido
        btnAgregar = new JButton("Agregar Pedido");
        btnAgregar.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnAgregar.setBackground(new Color(255, 235, 59));
        btnAgregar.setFocusPainted(false);
        btnAgregar.setPreferredSize(new Dimension(160, 35));

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.add(btnAgregar);

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(panelEntrada, BorderLayout.CENTER);
        panelSuperior.add(panelBoton, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);

        // Área de salida para mostrar mensajes
        areaSalida = new JTextArea();
        areaSalida.setEditable(false);
        areaSalida.setFont(new Font("Monospaced", Font.PLAIN, 13));
        areaSalida.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scroll = new JScrollPane(areaSalida);
        scroll.setBorder(BorderFactory.createTitledBorder("Resultado"));
        add(scroll, BorderLayout.CENTER);

        // Acción del botón
        btnAgregar.addActionListener(e -> agregarPedido());
    }

    // Método que valida los datos y agrega el pedido a la cola
    private void agregarPedido() {
        try {
            String idClienteTexto = txtIdCliente.getText().trim();
            String idProductoTexto = txtIdProducto.getText().trim();
            String cantidadTexto = txtCantidad.getText().trim();

            if (idClienteTexto.isEmpty() || idProductoTexto.isEmpty() || cantidadTexto.isEmpty()) {
                areaSalida.setText("Completa todos los campos.");
                return;
            }

            int idCliente = Integer.parseInt(idClienteTexto);
            int idProducto = Integer.parseInt(idProductoTexto);
            int cantidad = Integer.parseInt(cantidadTexto);

            if (cantidad <= 0) {
                areaSalida.setText("La cantidad debe ser mayor que cero.");
                return;
            }

            Cliente cliente = listaClientes.buscarClientePorId(idCliente);
            Producto producto = listaProductos.buscarProductoPorId(idProducto);

            if (cliente == null || producto == null) {
                areaSalida.setText("Cliente o producto no encontrado.");
                return;
            }

            // Crear factura con fecha actual
            String fecha = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(new java.util.Date());
            Factura factura = new Factura(contadorFacturas++, cliente, fecha);
            factura.agregarDetalle(producto, cantidad);
            colaPedidos.encolar(factura);

            // Guardar en archivo
            colaPedidos.guardarEnArchivo("src/recursos/pedidos.txt");

            areaSalida.setText("Pedido agregado correctamente a la cola.\n\n" +
                               "Cliente: " + cliente.getNombreCompleto() + "\n" +
                               "Producto: " + producto.getNombre() + " x" + cantidad);

            limpiarCampos();
        } catch (NumberFormatException ex) {
            areaSalida.setText("ID o cantidad inválida.");
        } catch (Exception ex) {
            areaSalida.setText("Error inesperado al agregar el pedido.");
        }
    }

    // Limpia los campos de entrada
    private void limpiarCampos() {
        txtIdCliente.setText("");
        txtIdProducto.setText("");
        txtCantidad.setText("");
    }
}
