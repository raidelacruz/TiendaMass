package tiendamass.vista;

import tiendamass.modelo.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * VentanaLogin
 * Interfaz gráfica de inicio de sesión para el sistema TiendaMass.
 * Estilo corporativo con diseño redondeado y validación desde archivo.
 */
public class VentanaLogin extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtClave;
    private JButton btnIngresar;

    public VentanaLogin() {
        setTitle("TiendaMass - Login");
        setSize(440, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Fondo principal
        JPanel fondo = new JPanel(null);
        fondo.setBackground(new Color(0, 51, 102)); // azul institucional
        fondo.setBounds(0, 0, 440, 500);
        add(fondo);

        // Panel blanco redondeado
        JPanel panel = new RoundedPanel(30);
        panel.setBackground(Color.WHITE);
        panel.setBounds(40, 40, 360, 400);
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createLineBorder(new Color(255, 204, 0), 3));
        fondo.add(panel);

        // Logo de la empresa
        ImageIcon iconoLogo = new ImageIcon("src/tiendamass/vista/logo_tiendamass.png");
        Image imagenGrande = iconoLogo.getImage().getScaledInstance(180, 90, Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(imagenGrande));
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogo.setBounds(90, 30, 180, 90);
        panel.add(lblLogo);

        // Etiqueta y campo de usuario
        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblUsuario.setBounds(40, 130, 280, 20);
        panel.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setFont(new Font("SansSerif", Font.PLAIN, 15));
        txtUsuario.setBounds(40, 155, 280, 35);
        panel.add(txtUsuario);

        // Etiqueta y campo de contraseña
        JLabel lblClave = new JLabel("Contraseña");
        lblClave.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblClave.setBounds(40, 210, 280, 20);
        panel.add(lblClave);

        txtClave = new JPasswordField();
        txtClave.setFont(new Font("SansSerif", Font.PLAIN, 15));
        txtClave.setBounds(40, 235, 280, 35);
        panel.add(txtClave);

        // Botón de ingreso
        btnIngresar = new JButton("INGRESAR");
        btnIngresar.setBackground(new Color(255, 204, 0));
        btnIngresar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnIngresar.setBounds(105, 310, 150, 45);
        btnIngresar.setFocusPainted(false);
        btnIngresar.setBorderPainted(false);

        // Efecto hover para el botón
        btnIngresar.addChangeListener(e -> {
            if (btnIngresar.getModel().isRollover()) {
                btnIngresar.setBackground(new Color(255, 180, 0));
            } else {
                btnIngresar.setBackground(new Color(255, 204, 0));
            }
        });

        // Acción al hacer clic en "INGRESAR"
        btnIngresar.addActionListener(e -> validarLogin());
        panel.add(btnIngresar);
    }
        // Valida el usuario y contraseña desde archivo y abre la ventana principal si son correctos
    private void validarLogin() {
        String usuario = txtUsuario.getText().trim();
        String clave = new String(txtClave.getPassword()).trim();

        if (validarDesdeArchivo(usuario, clave)) {
            // Cargar estructuras necesarias para el sistema
            ListaClientes listaClientes = new ListaClientes();
            listaClientes.cargarDesdeArchivo("src/recursos/clientes.txt");

            ListaProductos listaProductos = new ListaProductos();
            listaProductos.cargarDesdeArchivo("src/recursos/productos.txt");

            ColaPedidos colaPedidos = new ColaPedidos();
            colaPedidos.cargarDesdeArchivo("src/recursos/pedidos.txt");

            PilaVentas historialVentas = new PilaVentas();

            RegistroVentas registroVentas = new RegistroVentas();
            registroVentas.cargarVentasDesdeArchivo("src/recursos/ventas.txt");

            // Abrir ventana principal con pestañas
            new VentanaPrincipalConPestañas(
                listaClientes,
                listaProductos,
                colaPedidos,
                historialVentas,
                registroVentas
            ).setVisible(true);

            dispose(); // cerrar login
        } else {
            JOptionPane.showMessageDialog(this,
                    "Usuario o contraseña incorrectos",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Verifica credenciales desde archivo de usuarios
    public boolean validarDesdeArchivo(String usuario, String clave) {
        String[] lineas = ArchivoUtil.leerArchivo("src/recursos/usuarios.txt");
        for (String linea : lineas) {
            String[] partes = linea.split(",");
            if (partes.length == 2 &&
                partes[0].equals(usuario) &&
                partes[1].equals(clave)) {
                return true;
            }
        }
        return false;
    }

    // Panel personalizado con bordes redondeados
    class RoundedPanel extends JPanel {
        private int radius;

        public RoundedPanel(int radius) {
            this.radius = radius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), radius, radius));
        }
    }

    // Método principal para ejecutar la ventana
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaLogin().setVisible(true));
    }
}

