package tiendamass.vista;

import tiendamass.modelo.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Autor: Alessandra Condeso / Pretel Padilla Enrique / Angie Noemi Huaylinos / Karen Giron Faustino
 *
 * Clase VentanaClientes
 * Ventana gráfica para gestionar clientes en el sistema TiendaMass.
 * Permite registrar, buscar, eliminar, mostrar y ordenar clientes.
 */
public class VentanaClientes extends JFrame {

    private JTextField txtId, txtNombre, txtApPaterno, txtApMaterno;
    private JTable tablaClientes;
    private DefaultTableModel modeloTabla;
    private ListaClientes lista;

    // Constructor: configura la interfaz y carga los datos
    public VentanaClientes(ListaClientes listaClientes) {
        this.lista = listaClientes;

        setTitle("Gestión de Clientes - TiendaMass");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Cargar clientes desde archivo
        lista.cargarDesdeArchivo("src/recursos/clientes.txt");

        // Colores institucionales
        Color azul = new Color(0, 51, 102);
        Color amarilloSuave = new Color(255, 230, 150);
        Color fondoFormulario = new Color(255, 255, 240);

        // Encabezado con logo y título
        JPanel encabezado = new JPanel(new BorderLayout());
        encabezado.setBackground(azul);
        encabezado.setPreferredSize(new Dimension(900, 100));

        JLabel lblLogo = new JLabel();
        ImageIcon iconoLogo = new ImageIcon("src/tiendamass/vista/logo_tiendamass.png");
        Image imagenLogo = iconoLogo.getImage().getScaledInstance(160, 80, Image.SCALE_SMOOTH);
        lblLogo.setIcon(new ImageIcon(imagenLogo));
        lblLogo.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

        JLabel lblTitulo = new JLabel("Registro de Clientes", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        encabezado.add(lblLogo, BorderLayout.WEST);
        encabezado.add(lblTitulo, BorderLayout.CENTER);
        encabezado.add(new JLabel(" "), BorderLayout.EAST);

        add(encabezado, BorderLayout.NORTH);

        // Formulario de ingreso
        JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 6, 6));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Cliente"));
        panelFormulario.setBackground(fondoFormulario);
        panelFormulario.setPreferredSize(new Dimension(350, 160));

        txtId = new JTextField();
        txtNombre = new JTextField();
        txtApPaterno = new JTextField();
        txtApMaterno = new JTextField();

        Font formFont = new Font("SansSerif", Font.PLAIN, 13);
        txtId.setFont(formFont);
        txtNombre.setFont(formFont);
        txtApPaterno.setFont(formFont);
        txtApMaterno.setFont(formFont);

        panelFormulario.add(new JLabel("ID:"));
        panelFormulario.add(txtId);
        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(txtNombre);
        panelFormulario.add(new JLabel("Apellido Paterno:"));
        panelFormulario.add(txtApPaterno);
        panelFormulario.add(new JLabel("Apellido Materno:"));
        panelFormulario.add(txtApMaterno);

        // Panel de botones con distribución simétrica
        JPanel panelBotones = new JPanel(new GridBagLayout());
        panelBotones.setBorder(BorderFactory.createTitledBorder("Opciones"));
        panelBotones.setBackground(Color.WHITE);

        JButton btnAgregar = new JButton("Agregar");
        JButton btnBuscar = new JButton("Buscar por ID");
        JButton btnBuscarNombre = new JButton("Buscar por Nombre");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnMostrar = new JButton("Mostrar");
        JButton btnOrdenar = new JButton("Ordenar por Nombre");

        JButton[] botones = {
            btnAgregar, btnBuscar, btnBuscarNombre,
            btnEliminar, btnMostrar, btnOrdenar
        };

        GridBagConstraints gbcBtn = new GridBagConstraints();
        gbcBtn.insets = new Insets(10, 15, 10, 15);
        gbcBtn.fill = GridBagConstraints.HORIZONTAL;
        gbcBtn.weightx = 1;

        for (int i = 0; i < botones.length; i++) {
            gbcBtn.gridx = i % 3;
            gbcBtn.gridy = i / 3;
            botones[i].setFont(new Font("SansSerif", Font.PLAIN, 14));
            botones[i].setPreferredSize(new Dimension(160, 36));
            botones[i].setBackground(amarilloSuave);
            botones[i].setForeground(azul);
            botones[i].setFocusPainted(false);
            botones[i].setBorder(BorderFactory.createLineBorder(azul, 2, true));
            panelBotones.add(botones[i], gbcBtn);
        }

        // Panel central con márgenes amplios
        JPanel panelCentral = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 40, 10, 40);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.55;
        panelCentral.add(panelFormulario, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.45;
        panelCentral.add(panelBotones, gbc);

        add(panelCentral, BorderLayout.CENTER);
                // Tabla de clientes (vacía al inicio)
        String[] columnas = {"ID", "Nombre", "Ap. Paterno", "Ap. Materno"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaClientes = new JTable(modeloTabla);
        tablaClientes.setRowHeight(25);
        tablaClientes.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tablaClientes.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));

        JScrollPane scroll = new JScrollPane(tablaClientes);
        scroll.setBorder(BorderFactory.createTitledBorder("Lista de Clientes"));
        scroll.setPreferredSize(new Dimension(860, 220));

        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBorder(BorderFactory.createEmptyBorder(0, 40, 10, 40)); // márgenes laterales
        panelTabla.add(scroll, BorderLayout.CENTER);

        add(panelTabla, BorderLayout.SOUTH);

        // Asignar acciones a los botones
        btnAgregar.addActionListener(e -> agregarCliente());
        btnBuscar.addActionListener(e -> buscarCliente());
        btnBuscarNombre.addActionListener(e -> buscarPorNombre());
        btnEliminar.addActionListener(e -> eliminarCliente());
        btnMostrar.addActionListener(e -> mostrarClientes());
        btnOrdenar.addActionListener(e -> ordenarPorNombre());
    }

    // Método para agregar un nuevo cliente
    private void agregarCliente() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            String nombre = txtNombre.getText().trim();
            String apPaterno = txtApPaterno.getText().trim();
            String apMaterno = txtApMaterno.getText().trim();

            if (nombre.isEmpty() || apPaterno.isEmpty() || apMaterno.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Completa todos los campos.");
                return;
            }

            Cliente nuevo = new Cliente(id, nombre, apPaterno, apMaterno);
            boolean agregado = lista.agregarCliente(nuevo);

            if (agregado) {
                JOptionPane.showMessageDialog(this, "Cliente agregado correctamente.");
                limpiarCampos();
                mostrarClientes(); // Refrescar la tabla
                lista.guardarEnArchivo("src/recursos/clientes.txt");
            } else {
                JOptionPane.showMessageDialog(this, "Ya existe un cliente con ese ID.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido.");
        }
    }

    // Limpiar campos del formulario
    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtApPaterno.setText("");
        txtApMaterno.setText("");
    }
        // Buscar cliente por ID
    private void buscarCliente() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            Cliente encontrado = lista.buscarClientePorId(id);

            modeloTabla.setRowCount(0);
            if (encontrado != null) {
                modeloTabla.addRow(new Object[]{
                    encontrado.getId(),
                    encontrado.getNombre(),
                    encontrado.getApPaterno(),
                    encontrado.getApMaterno()
                });
            } else {
                JOptionPane.showMessageDialog(this, "Cliente no encontrado.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido.");
        }
    }

    // Buscar cliente por nombre
    private void buscarPorNombre() {
        String nombreBuscado = txtNombre.getText().trim().toLowerCase();
        if (nombreBuscado.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa un nombre para buscar.");
            return;
        }

        modeloTabla.setRowCount(0);
        NodoCliente actual = lista.getInicio();
        boolean encontrado = false;

        while (actual != null) {
            Cliente c = actual.getCliente();
            if (c.getNombre().toLowerCase().contains(nombreBuscado)) {
                modeloTabla.addRow(new Object[]{
                    c.getId(),
                    c.getNombre(),
                    c.getApPaterno(),
                    c.getApMaterno()
                });
                encontrado = true;
            }
            actual = actual.getSiguiente();
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(this, "No se encontraron coincidencias.");
        }
    }

    // Eliminar cliente por ID
    private void eliminarCliente() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            boolean eliminado = lista.eliminarClientePorId(id);

            if (eliminado) {
                JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente.");
                mostrarClientes();
                lista.guardarEnArchivo("src/recursos/clientes.txt");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el cliente.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido.");
        }
    }

    // Mostrar clientes en orden de inserción
    private void mostrarClientes() {
        modeloTabla.setRowCount(0);
        NodoCliente actual = lista.getInicio();

        while (actual != null) {
            Cliente c = actual.getCliente();
            modeloTabla.addRow(new Object[]{
                c.getId(),
                c.getNombre(),
                c.getApPaterno(),
                c.getApMaterno()
            });
            actual = actual.getSiguiente();
        }

        JOptionPane.showMessageDialog(this, "Total de clientes: " + lista.contarClientes());
    }

    // Ordenar clientes por nombre
    private void ordenarPorNombre() {
        Cliente[] arreglo = lista.obtenerClientesOrdenadosPorNombre();
        modeloTabla.setRowCount(0);

        for (Cliente c : arreglo) {
            modeloTabla.addRow(new Object[]{
                c.getId(),
                c.getNombre(),
                c.getApPaterno(),
                c.getApMaterno()
            });
        }
    }
}



