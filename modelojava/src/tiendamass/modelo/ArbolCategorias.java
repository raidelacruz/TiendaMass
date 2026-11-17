package tiendamass.modelo;

/**
 * Autor: Alessandra Condeso / Pretel Padilla Enrique / Angie Noemi Huaylinos / Karen Giron Faustino
 *
 * Clase ArbolCategorias
 * Representa un árbol binario de categorías de productos.
 * Permite insertar productos por categoría y recorrer el árbol en diferentes órdenes.
 */
public class ArbolCategorias {

    private NodoCategoria raiz;

    // Constructor: inicializa el árbol vacío
    public ArbolCategorias() {
        this.raiz = null;
    }

    // Inserta un producto en el árbol, ubicándolo según su categoría
    public void insertarProducto(Producto producto) {
        raiz = insertarRecursivo(raiz, producto);
    }

    // Inserta recursivamente el producto en el nodo correspondiente
    private NodoCategoria insertarRecursivo(NodoCategoria nodo, Producto producto) {
        if (nodo == null) {
            NodoCategoria nuevo = new NodoCategoria(producto.getCategoria());
            nuevo.getProductos().agregarProducto(producto);
            return nuevo;
        }

        int comparacion = producto.getCategoria().compareToIgnoreCase(nodo.getCategoria());

        if (comparacion < 0) {
            nodo.setIzquierda(insertarRecursivo(nodo.getIzquierda(), producto));
        } else if (comparacion > 0) {
            nodo.setDerecha(insertarRecursivo(nodo.getDerecha(), producto));
        } else {
            nodo.getProductos().agregarProducto(producto);
        }

        return nodo;
    }

    // Cuenta el total de productos en todas las categorías
    public int contarTotalProductos() {
        return contarProductosRecursivo(raiz);
    }

    private int contarProductosRecursivo(NodoCategoria nodo) {
        if (nodo == null) return 0;
        return nodo.getProductos().contarProductos()
             + contarProductosRecursivo(nodo.getIzquierda())
             + contarProductosRecursivo(nodo.getDerecha());
    }

    // Cuenta cuántas categorías existen en el árbol
    public int contarCategorias() {
        return contarCategoriasRecursivo(raiz);
    }

    private int contarCategoriasRecursivo(NodoCategoria nodo) {
        if (nodo == null) return 0;
        return 1 + contarCategoriasRecursivo(nodo.getIzquierda())
                 + contarCategoriasRecursivo(nodo.getDerecha());
    }

    // Muestra las categorías y productos en orden alfabético
    public void mostrarInOrden() {
        inOrden(raiz);
    }

    private void inOrden(NodoCategoria nodo) {
        if (nodo != null) {
            inOrden(nodo.getIzquierda());
            System.out.println("Categoría: " + nodo.getCategoria());
            nodo.getProductos().mostrarProductos();
            inOrden(nodo.getDerecha());
        }
    }

    // Muestra las categorías y productos en preorden
    public void mostrarPreOrden() {
        preOrden(raiz);
    }

    private void preOrden(NodoCategoria nodo) {
        if (nodo != null) {
            System.out.println("Categoría: " + nodo.getCategoria());
            nodo.getProductos().mostrarProductos();
            preOrden(nodo.getIzquierda());
            preOrden(nodo.getDerecha());
        }
    }

    // Muestra las categorías y productos en postorden
    public void mostrarPostOrden() {
        postOrden(raiz);
    }

    private void postOrden(NodoCategoria nodo) {
        if (nodo != null) {
            postOrden(nodo.getIzquierda());
            postOrden(nodo.getDerecha());
            System.out.println("Categoría: " + nodo.getCategoria());
            nodo.getProductos().mostrarProductos();
        }
    }

    // Devuelve el recorrido inOrden como texto
    public String mostrarInOrdenComoTexto() {
        StringBuilder sb = new StringBuilder();
        inOrdenTexto(raiz, sb);
        return sb.toString();
    }

    private void inOrdenTexto(NodoCategoria nodo, StringBuilder sb) {
        if (nodo != null) {
            inOrdenTexto(nodo.getIzquierda(), sb);
            sb.append("Categoría: ").append(nodo.getCategoria()).append("\n");
            sb.append(nodo.getProductos().toString()).append("\n");
            inOrdenTexto(nodo.getDerecha(), sb);
        }
    }

    // Devuelve el recorrido preOrden como texto
    public String mostrarPreOrdenComoTexto() {
        StringBuilder sb = new StringBuilder();
        preOrdenTexto(raiz, sb);
        return sb.toString();
    }

    private void preOrdenTexto(NodoCategoria nodo, StringBuilder sb) {
        if (nodo != null) {
            sb.append("Categoría: ").append(nodo.getCategoria()).append("\n");
            sb.append(nodo.getProductos().toString()).append("\n");
            preOrdenTexto(nodo.getIzquierda(), sb);
            preOrdenTexto(nodo.getDerecha(), sb);
        }
    }

    // Devuelve el recorrido postOrden como texto
    public String mostrarPostOrdenComoTexto() {
        StringBuilder sb = new StringBuilder();
        postOrdenTexto(raiz, sb);
        return sb.toString();
    }

    private void postOrdenTexto(NodoCategoria nodo, StringBuilder sb) {
        if (nodo != null) {
            postOrdenTexto(nodo.getIzquierda(), sb);
            postOrdenTexto(nodo.getDerecha(), sb);
            sb.append("Categoría: ").append(nodo.getCategoria()).append("\n");
            sb.append(nodo.getProductos().toString()).append("\n");
        }
    }

    // Busca un nodo de categoría por nombre
    public NodoCategoria buscarCategoria(String nombre) {
        return buscarRecursivo(raiz, nombre);
    }

    private NodoCategoria buscarRecursivo(NodoCategoria nodo, String nombre) {
        if (nodo == null) return null;

        int comparacion = nombre.compareToIgnoreCase(nodo.getCategoria());

        if (comparacion == 0) {
            return nodo;
        } else if (comparacion < 0) {
            return buscarRecursivo(nodo.getIzquierda(), nombre);
        } else {
            return buscarRecursivo(nodo.getDerecha(), nombre);
        }
    }

    // Elimina un producto por ID dentro de una categoría específica
    public boolean eliminarProductoPorId(int id, String categoria) {
        NodoCategoria nodo = buscarCategoria(categoria);
        if (nodo != null) {
            return nodo.getProductos().eliminarProductoPorId(id);
        }
        return false;
    }

    // Devuelve la raíz del árbol
    public NodoCategoria getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoCategoria raiz) {
        this.raiz = raiz;
    }

    // Guarda todos los productos del árbol en un archivo de texto
    public void guardarTodosLosProductosEnArchivo(String ruta) {
        int total = contarTotalProductos();
        String[] lineas = new String[total];
        int[] indice = {0}; // contador mutable para posición en el arreglo

        guardarRecursivo(raiz, lineas, indice);
        ArchivoUtil.escribirArchivo(ruta, lineas);
    }

    // Carga productos desde un archivo de texto y los inserta en el árbol
    public void cargarDesdeArchivo(String ruta) {
        String[] lineas = ArchivoUtil.leerArchivo(ruta);
        for (String linea : lineas) {
            String[] partes = linea.split(",");
            if (partes.length == 4) {
                try {
                    int id = Integer.parseInt(partes[0]);
                    String nombre = partes[1];
                    double precio = Double.parseDouble(partes[2]);
                    String categoria = partes[3];

                    Producto producto = new Producto(id, nombre, precio, categoria);
                    insertarProducto(producto);
                } catch (Exception e) {
                    System.out.println("Error al cargar producto: " + linea);
                }
            }
        }
    }

    // Recorre el árbol y guarda cada producto en el arreglo de líneas
    private void guardarRecursivo(NodoCategoria nodo, String[] lineas, int[] i) {
        if (nodo == null) return;

        guardarRecursivo(nodo.getIzquierda(), lineas, i);

        NodoProducto actual = nodo.getProductos().getFrente();
        while (actual != null) {
            Producto p = actual.getProducto();
            lineas[i[0]] = p.getId() + "," + p.getNombre() + "," + p.getPrecio() + "," + p.getCategoria();
            i[0]++;
            actual = actual.getSiguiente();
        }

        guardarRecursivo(nodo.getDerecha(), lineas, i);
    }
}
