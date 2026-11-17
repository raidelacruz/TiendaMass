package tiendamass.vista;

import tiendamass.modelo.*;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * Autor: Alessandra Condeso / Pretel Padilla Enrique / Angie Noemi Huaylinos / Karen Giron Faustino
 *
 * Clase VentanaVentas
 * Permite registrar ventas y visualizar el historial.
 */
public class VentanaVentas extends JFrame {

    private JTextField txtCliente, txtCategoria, txtIdProducto, txtCantidad;
    private JTextArea areaSalida;
    private JButton btnAgregarProducto, btnRegistrarVenta, btnVerHistorial, btnVerPila;

    private ArbolCategorias arbol;
    private RegistroVentas registro;
    private Venta ventaActual;
    private PilaVentas pilaVentas;

    public VentanaVentas(ArbolCategorias arbol, RegistroVentas registro, PilaVentas pilaVentas) {
        this.arbol = arbol;
        this.registro = registro;
        this.pilaVentas = pilaVentas;

        setTitle("Registro de Ventas");
        setSize(700, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        //  Colores corporativos
        Color massAzul = new Color(0, 51, 102);
        Color massAmarillo = new Color(255, 204, 0);

        //  Encabezado con logo y título
        JPanel encabezado = new JPanel(null);
        encabezado.setPreferredSize(new Dimension(700, 80));
        encabezado.setBackground(massAzul);

        ImageIcon iconoLogo = new ImageIcon("src/tiendamass/vista/logo_tiendamass.png");
        Image imagenLogo = iconoLogo.getImage().getScaledInstance(140, 60, Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(imagenLogo));
        lblLogo.setBounds(20, 10, 140, 60);
        encabezado.add(lblLogo);

        JLabel lblTituloEncabezado = new JLabel("Registro de Ventas");
        lblTituloEncabezado.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTituloEncabezado.setForeground(Color.WHITE);
        lblTituloEncabezado.setBounds(180, 20, 400, 40);
        encabezado.add(lblTituloEncabezado);

        add(encabezado, BorderLayout.NORTH);

        //  Panel de formulario
        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos de la Venta"));

        txtCliente = new JTextField();
        txtCategoria = new JTextField();
        txtIdProducto = new JTextField();
        txtCantidad = new JTextField();

        panelFormulario.add(new JLabel("Cliente:"));
        panelFormulario.add(txtCliente);
        panelFormulario.add(new JLabel("Categoría:"));
        panelFormulario.add(txtCategoria);
        panelFormulario.add(new JLabel("ID Producto:"));
        panelFormulario.add(txtIdProducto);
        panelFormulario.add(new JLabel("Cantidad:"));
        panelFormulario.add(txtCantidad);

        btnAgregarProducto = new JButton("Agregar Producto");
        btnRegistrarVenta = new JButton("Registrar Venta");

        panelFormulario.add(btnAgregarProducto);
        panelFormulario.add(btnRegistrarVenta);

        add(panelFormulario, BorderLayout.CENTER);

        //  Área de resultados
        areaSalida = new JTextArea();
        areaSalida.setEditable(false);
        areaSalida.setFont(new Font("Monospaced", Font.PLAIN, 13));
        areaSalida.setMargin(new Insets(10, 10, 10, 10));
        JScrollPane scroll = new JScrollPane(areaSalida);
        scroll.setBorder(BorderFactory.createTitledBorder(" Detalle de Venta / Historial"));
        add(scroll, BorderLayout.EAST);

        // Panel de historial
        JPanel panelHistorial = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnVerHistorial = new JButton("Ver Historial");
        btnVerPila = new JButton("Ver Últimas Ventas");
        panelHistorial.add(btnVerHistorial);
        panelHistorial.add(btnVerPila);

        //  Línea inferior amarilla
        JPanel bordeInferior = new JPanel();
        bordeInferior.setPreferredSize(new Dimension(700, 5));
        bordeInferior.setBackground(massAmarillo);

        JPanel panelSur = new JPanel(new BorderLayout());
        panelSur.add(panelHistorial, BorderLayout.CENTER);
        panelSur.add(bordeInferior, BorderLayout.SOUTH);

        add(panelSur, BorderLayout.SOUTH);

        //  Eventos
        btnAgregarProducto.addActionListener(e -> agregarProductoAVenta());
        btnRegistrarVenta.addActionListener(e -> registrarVenta());
        btnVerHistorial.addActionListener(e -> mostrarHistorial());
        btnVerPila.addActionListener(e -> mostrarHistorialPila());
    }

    private void agregarProductoAVenta() {
        try {
            String cliente = txtCliente.getText().trim();
            String categoria = txtCategoria.getText().trim();
            String idTexto = txtIdProducto.getText().trim();
            String cantidadTexto = txtCantidad.getText().trim();

            if (cliente.isEmpty() || categoria.isEmpty() || idTexto.isEmpty() || cantidadTexto.isEmpty()) {
                areaSalida.setText("Completa todos los campos.");
                return;
            }

            int id = Integer.parseInt(idTexto);
            int cantidad = Integer.parseInt(cantidadTexto);
            if (cantidad <= 0) {
                areaSalida.setText("La cantidad debe ser mayor que cero.");
                return;
            }

            if (ventaActual == null) {
                String fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date());
                ventaActual = new Venta(cliente, fecha);
            }

            NodoCategoria nodo = arbol.buscarCategoria(categoria);
            if (nodo == null) {
                areaSalida.setText("Categoría no encontrada.");
                return;
            }

            Producto encontrado = buscarProductoPorId(nodo.getProductos(), id);
            if (encontrado == null) {
                areaSalida.setText("Producto no encontrado en la categoría.");
                return;
            }

            ventaActual.agregarProducto(encontrado, cantidad);
            areaSalida.setText("Producto agregado a la venta:\n" + encontrado.getNombre() + " x" + cantidad);

        } catch (NumberFormatException ex) {
            areaSalida.setText("ID o cantidad inválida.");
        } catch (Exception ex) {
            areaSalida.setText("Error inesperado al agregar producto.");
        }
    }

    private Producto buscarProductoPorId(ColaProducto cola, int id) {
        NodoProducto actual = cola.getFrente();
        while (actual != null) {
            if (actual.getProducto().getId() == id) {
                return actual.getProducto();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    private void registrarVenta() {
        if (ventaActual != null && ventaActual.getProductosVendidos() != null && !ventaActual.getProductosVendidos().estaVacia()) {
            registro.registrarVenta(ventaActual);
            pilaVentas.apilar(ventaActual);
            registro.guardarVentasEstructuradas("src/recursos/ventas.txt");
            areaSalida.setText("Venta registrada con éxito:\n\n" + ventaActual.toString());
            ventaActual = null;
            limpiarCampos();
        } else {
            areaSalida.setText("No hay productos en la venta actual.");
        }
    }


    private void mostrarHistorial() {
        if (registro.estaVacio()) {
            areaSalida.setText("No hay ventas registradas.");
        } else {
            areaSalida.setText("Historial de Ventas:\n\n" + registro.mostrarVentas());
        }
    }

    private void mostrarHistorialPila() {
        if (pilaVentas.estaVacia()) {
            areaSalida.setText("No hay ventas en la pila.");
        } else {
            areaSalida.setText("Últimas Ventas (orden inverso):\n\n" + pilaVentas.mostrarHistorial());
        }
    }

    private void limpiarCampos() {
        txtCliente.setText("");
        txtCategoria.setText("");
        txtIdProducto.setText("");
        txtCantidad.setText("");
    }
}

