package tiendamass.vista;

import tiendamass.modelo.*;

import javax.swing.*;
import java.awt.*;

/**
 * VentanaPedidos
 * Interfaz visual moderna para mostrar pedidos pendientes en TiendaMass.
 */
public class VentanaPedidos extends JFrame {

    private JTextArea areaPedidos;
    private JLabel lblContador;  
    private JButton btnActualizar;
    private ColaPedidos cola;

    public VentanaPedidos(ColaPedidos colaPedidos) {
        this.cola = colaPedidos;

        setTitle("Pedidos Pendientes - TiendaMass");
        setSize(750, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        //  Título superior
        JLabel titulo = new JLabel("Pedidos en Cola", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));
        add(titulo, BorderLayout.NORTH);
        
        lblContador = new JLabel();
        lblContador.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblContador.setHorizontalAlignment(SwingConstants.CENTER);
        
        JPanel panelTitulo = new JPanel(new GridLayout(2, 1));
        panelTitulo.add(titulo);
        panelTitulo.add(lblContador);
        add(panelTitulo, BorderLayout.NORTH);



        //  Área de texto
        areaPedidos = new JTextArea();
        areaPedidos.setEditable(false);
        areaPedidos.setFont(new Font("Monospaced", Font.PLAIN, 13));
        areaPedidos.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scroll = new JScrollPane(areaPedidos);
        scroll.setBorder(BorderFactory.createTitledBorder("Lista de Pedidos Pendientes"));

        //  Botón actualizar
        btnActualizar = new JButton("Actualizar Lista");
        btnActualizar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnActualizar.setBackground(new Color(255, 235, 59));
        btnActualizar.setFocusPainted(false);
        btnActualizar.setPreferredSize(new Dimension(180, 40));

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnActualizar);

        //  Agregar componentes
        add(scroll, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);

        // Acción del botón
        btnActualizar.addActionListener(e -> mostrarPedidos());
        
    //  Botón atender pedido
    JButton btnAtender = new JButton("Atender Pedido");
    btnAtender.setFont(new Font("SansSerif", Font.BOLD, 14));
    btnAtender.setBackground(new Color(129, 199, 132)); // verde claro
    btnAtender.setFocusPainted(false);
    btnAtender.setPreferredSize(new Dimension(180, 40));
    panelBoton.add(btnAtender);

    // Acción del botón Atender
    btnAtender.addActionListener(e -> atenderPedido());


        // Mostrar al iniciar
        mostrarPedidos();
    }
    
    private void atenderPedido() {
        if (cola.estaVacia()) {
            areaPedidos.setText("No hay pedidos para atender.");
            return;
        }

        Factura f = cola.desencolar();

        areaPedidos.setText("Pedido atendido:\n");
        areaPedidos.append("Cliente: " + f.getCliente().getNombreCompleto() + "\n");

        NodoDetalleFactura detalle = f.getDetalles();
        while (detalle != null) {
            Producto producto = detalle.getProducto();
            int cantidad = detalle.getCantidad();
            double subtotal = producto.getPrecio() * cantidad;

            areaPedidos.append("   • Producto: " + producto.getNombre() + "\n");
            areaPedidos.append("     Cantidad: " + cantidad + "\n");
            areaPedidos.append("     Subtotal: S/ " + String.format("%.2f", subtotal) + "\n");

            detalle = detalle.getSiguiente();
        }

        areaPedidos.append("Total del pedido: S/ " + String.format("%.2f", f.getTotal()) + "\n");
        areaPedidos.append("────────────────────────────────────\n");
        lblContador.setText("Pedidos pendientes: " + cola.contarPedidos());

    }


    private void mostrarPedidos() {
        areaPedidos.setText("");

        NodoPedido actual = cola.getFrente();

        if (actual == null) {
            areaPedidos.setText(" No hay pedidos pendientes.");
            return;
        }

        int contador = 1;
        while (actual != null) {
            Factura f = actual.getFactura();
            areaPedidos.append("Pedido #" + contador + "\n");
            areaPedidos.append("Cliente: " + f.getCliente().getNombreCompleto() + "\n");

            NodoDetalleFactura detalle = f.getDetalles();
            while (detalle != null) {
                Producto producto = detalle.getProducto();
                int cantidad = detalle.getCantidad();
                double subtotal = producto.getPrecio() * cantidad;

                areaPedidos.append("   • Producto: " + producto.getNombre() + "\n");
                areaPedidos.append("     Cantidad: " + cantidad + "\n");
                areaPedidos.append("     Subtotal: S/ " + String.format("%.2f", subtotal) + "\n");

                detalle = detalle.getSiguiente();
            }

            areaPedidos.append("Total del pedido: S/ " + String.format("%.2f", f.getTotal()) + "\n");
            areaPedidos.append("────────────────────────────────────\n");

            actual = actual.getSiguiente();
            contador++;
            lblContador.setText("Pedidos pendientes: " + cola.contarPedidos());

        }
    }
}

