package tiendamass.vista;

import tiendamass.modelo.*;

import javax.swing.*;
import java.awt.*;

public class VentanaProductos extends JFrame {

    private JTextField txtId, txtNombre, txtPrecio, txtCategoria;
    private JTextArea areaSalida;
    private ArbolCategorias arbol;

    public VentanaProductos(ArbolCategorias arbolCategorias) {
        this.arbol = arbolCategorias;
        arbol.cargarDesdeArchivo("src/recursos/productos.txt");

        setTitle("Gestión de Productos por Categoría");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        Color azul = new Color(0, 51, 102);
        Color amarilloSuave = new Color(255, 230, 150);
        Color fondoFormulario = new Color(255, 255, 240);

        // Encabezado
        JPanel encabezado = new JPanel(new BorderLayout());
        encabezado.setBackground(azul);
        encabezado.setPreferredSize(new Dimension(900, 110));

        JLabel lblLogo = new JLabel();
        ImageIcon iconoLogo = new ImageIcon("src/tiendamass/vista/logo_tiendamass.png");
        Image imagenLogo = iconoLogo.getImage().getScaledInstance(160, 80, Image.SCALE_SMOOTH);
        lblLogo.setIcon(new ImageIcon(imagenLogo));
        lblLogo.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

        JLabel lblTitulo = new JLabel("Gestión de Productos", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 30));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        encabezado.add(lblLogo, BorderLayout.WEST);
        encabezado.add(lblTitulo, BorderLayout.CENTER);
        encabezado.add(new JLabel(" "), BorderLayout.EAST); // espacio simétrico

        add(encabezado, BorderLayout.NORTH);

        // Formulario
        JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 6, 6));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Producto"));
        panelFormulario.setBackground(fondoFormulario);
        panelFormulario.setPreferredSize(new Dimension(350, 160));

        txtId = new JTextField();
        txtNombre = new JTextField();
        txtPrecio = new JTextField();
        txtCategoria = new JTextField();

        Font formFont = new Font("SansSerif", Font.PLAIN, 13);
        txtId.setFont(formFont);
        txtNombre.setFont(formFont);
        txtPrecio.setFont(formFont);
        txtCategoria.setFont(formFont);

        panelFormulario.add(new JLabel("ID:"));
        panelFormulario.add(txtId);
        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(txtNombre);
        panelFormulario.add(new JLabel("Precio:"));
        panelFormulario.add(txtPrecio);
        panelFormulario.add(new JLabel("Categoría:"));
        panelFormulario.add(txtCategoria);

        // Botones
        JPanel panelBotones = new JPanel(new GridLayout(2, 3, 8, 8));
        panelBotones.setBorder(BorderFactory.createTitledBorder("Opciones"));
        panelBotones.setBackground(Color.WHITE);

        JButton btnAgregar = new JButton("Agregar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnBuscar = new JButton("Buscar Categoría");
        JButton btnMostrar = new JButton("Mostrar Todo");
        JButton btnOrdenarPrecio = new JButton("Ordenar por Precio");
        JButton btnOrdenarNombre = new JButton("Ordenar por Nombre");

        JButton[] botones = {
            btnAgregar, btnEliminar, btnBuscar,
            btnMostrar, btnOrdenarPrecio, btnOrdenarNombre
        };

        for (JButton btn : botones) {
            btn.setFont(new Font("SansSerif", Font.PLAIN, 12));
            btn.setPreferredSize(new Dimension(110, 30));
            btn.setMaximumSize(new Dimension(130, 34));
            btn.setBackground(amarilloSuave);
            btn.setForeground(azul);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createLineBorder(azul, 2, true));
            panelBotones.add(btn);
        }

        // Panel central con márgenes laterales
        JPanel panelCentral = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 40, 10, 40); // margen lateral amplio
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
                // Área de resultados centrada y amplia
        areaSalida = new JTextArea();
        areaSalida.setEditable(false);
        areaSalida.setFont(new Font("Monospaced", Font.PLAIN, 13));
        areaSalida.setMargin(new Insets(12, 12, 12, 12));
        areaSalida.setBackground(new Color(250, 250, 240));

        JScrollPane scroll = new JScrollPane(areaSalida);
        scroll.setBorder(BorderFactory.createTitledBorder("Resultados"));
        scroll.setPreferredSize(new Dimension(860, 260));

        JPanel panelResultados = new JPanel(new BorderLayout());
        panelResultados.setBorder(BorderFactory.createEmptyBorder(0, 40, 10, 40)); // margen lateral amplio
        panelResultados.add(scroll, BorderLayout.CENTER);

        add(panelResultados, BorderLayout.SOUTH);

        // Acciones
        btnAgregar.addActionListener(e -> agregarProducto());
        btnEliminar.addActionListener(e -> eliminarProducto());
        btnBuscar.addActionListener(e -> buscarCategoria());
        btnMostrar.addActionListener(e -> mostrarCategorias());
        btnOrdenarPrecio.addActionListener(e -> ordenarPorPrecio());
        btnOrdenarNombre.addActionListener(e -> ordenarPorNombre());
    }

    private void agregarProducto() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            String nombre = txtNombre.getText().trim();
            double precio = Double.parseDouble(txtPrecio.getText().trim());
            String categoria = txtCategoria.getText().trim();

            if (nombre.isEmpty() || categoria.isEmpty()) {
                areaSalida.setText("Por favor completa todos los campos antes de agregar el producto.");
                return;
            }

            Producto nuevo = new Producto(id, nombre, precio, categoria);
            arbol.insertarProducto(nuevo);
            arbol.guardarTodosLosProductosEnArchivo("src/recursos/productos.txt");

            areaSalida.setText("Producto agregado correctamente a la categoría '" + categoria + "'.");
        } catch (Exception ex) {
            areaSalida.setText("Error al agregar producto. Verifica que los datos sean válidos.");
        }
    }

    private void eliminarProducto() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            String categoria = txtCategoria.getText().trim();

            int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Eliminar el producto con ID " + id + " de la categoría '" + categoria + "'?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
            );

            if (confirmacion == JOptionPane.YES_OPTION) {
                boolean eliminado = arbol.eliminarProductoPorId(id, categoria);

                if (eliminado) {
                    arbol.guardarTodosLosProductosEnArchivo("src/recursos/productos.txt");
                    areaSalida.setText("Producto eliminado correctamente de la categoría '" + categoria + "'.");
                } else {
                    areaSalida.setText("No se encontró el producto en la categoría especificada.");
                }
            } else {
                areaSalida.setText("Eliminación cancelada.");
            }

        } catch (Exception ex) {
            areaSalida.setText("Error al eliminar producto. Verifica los datos ingresados.");
        }
    }

    private void buscarCategoria() {
        String categoria = txtCategoria.getText().trim();
        if (categoria.isEmpty()) {
            areaSalida.setText("Ingresa una categoría para realizar la búsqueda.");
            return;
        }

        String[] opciones = {"InOrden", "PreOrden", "PostOrden"};
        int seleccion = JOptionPane.showOptionDialog(
            this,
            "¿Qué tipo de recorrido deseas para la categoría '" + categoria + "'?",
            "Seleccionar recorrido",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );

        NodoCategoria nodo = arbol.buscarCategoria(categoria);
        if (nodo == null || nodo.getProductos().estaVacia()) {
            areaSalida.setText("La categoría '" + categoria + "' no existe o no tiene productos registrados.");
            return;
        }

        areaSalida.setText("Productos en la categoría '" + categoria + "' (" + opciones[seleccion] + "):\n");
        areaSalida.append("ID   | Nombre         | Precio   | Categoría\n");
        areaSalida.append("-----+----------------+----------+-----------\n");

        Producto[] productos;
        switch (seleccion) {
            case 1: productos = nodo.getProductos().obtenerProductosPreOrden(); break;
            case 2: productos = nodo.getProductos().obtenerProductosPostOrden(); break;
            default: productos = nodo.getProductos().obtenerProductosInOrden(); break;
        }

        for (Producto p : productos) {
            areaSalida.append(p.toString() + "\n");
        }
    }
        private void mostrarCategorias() {
        areaSalida.setText("Listado completo de categorías y productos registrados:\n");
        areaSalida.append("ID   | Nombre         | Precio   | Categoría\n");
        areaSalida.append("-----+----------------+----------+-----------\n");
        mostrarProductosDesdeNodo(arbol.getRaiz());
    }

    private void mostrarProductosDesdeNodo(NodoCategoria nodo) {
        if (nodo == null) return;

        mostrarProductosDesdeNodo(nodo.getIzquierda());

        NodoProducto actual = nodo.getProductos().getFrente();
        while (actual != null) {
            Producto p = actual.getProducto();
            areaSalida.append(p.toString() + "\n");
            actual = actual.getSiguiente();
        }

        mostrarProductosDesdeNodo(nodo.getDerecha());
    }

    private void ordenarPorPrecio() {
        String categoria = txtCategoria.getText().trim();
        NodoCategoria nodo = arbol.buscarCategoria(categoria);

        if (nodo == null || nodo.getProductos().estaVacia()) {
            areaSalida.setText("La categoría '" + categoria + "' no existe o no tiene productos registrados.");
            return;
        }

        Producto[] productos = nodo.getProductos().obtenerProductosOrdenadosPorPrecio();
        areaSalida.setText("Productos ordenados por precio en '" + categoria + "':\n");
        areaSalida.append("ID   | Nombre         | Precio   | Categoría\n");
        areaSalida.append("-----+----------------+----------+-----------\n");

        for (Producto p : productos) {
            areaSalida.append(p.toString() + "\n");
        }
    }

    private void ordenarPorNombre() {
        String categoria = txtCategoria.getText().trim();
        NodoCategoria nodo = arbol.buscarCategoria(categoria);

        if (nodo == null || nodo.getProductos().estaVacia()) {
            areaSalida.setText("La categoría '" + categoria + "' no existe o no tiene productos registrados.");
            return;
        }

        Producto[] productos = nodo.getProductos().obtenerProductosOrdenadosPorNombre();
        areaSalida.setText("Productos ordenados por nombre en '" + categoria + "':\n");
        areaSalida.append("ID   | Nombre         | Precio   | Categoría\n");
        areaSalida.append("-----+----------------+----------+-----------\n");

        for (Producto p : productos) {
            areaSalida.append(p.toString() + "\n");
        }
    }
}


