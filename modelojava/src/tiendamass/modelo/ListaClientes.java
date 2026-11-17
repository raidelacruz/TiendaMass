package tiendamass.modelo;

import java.io.*;

/**
 * Autor: Alessandra Condeso / Pretel Padilla Enrique / Angie Noemi Huaylinos / Karen Giron Faustino
 *
 * Clase ListaClientes
 * Representa una lista enlazada de clientes.
 * Permite agregar, buscar, eliminar, mostrar y persistir clientes sin usar estructuras prohibidas.
 */

public class ListaClientes {

    private NodoCliente cabeza;

     public ListaClientes() {
    cargarDesdeArchivo("src/recursos/clientes.txt");
}


    public NodoCliente getCabeza() {
        return cabeza;
    }

    public boolean agregarCliente(Cliente cliente) {
        if (buscarClientePorId(cliente.getId()) != null) {
            return false;
        }

        NodoCliente nuevo = new NodoCliente(cliente);
        nuevo.setSiguiente(cabeza);
        cabeza = nuevo;
        return true;
    }
    
    public NodoCliente getInicio() {
    return cabeza;
    }


    public Cliente buscarClientePorId(int id) {
        NodoCliente actual = cabeza;
        while (actual != null) {
            if (actual.getCliente().getId() == id) {
                return actual.getCliente();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public Cliente[] obtenerClientesOrdenadosPorNombre() {
        int cantidad = contarClientes();
        Cliente[] arreglo = new Cliente[cantidad];

        // Copiar clientes al arreglo
        NodoCliente actual = cabeza;
        int i = 0;
        while (actual != null) {
            arreglo[i] = actual.getCliente();
            actual = actual.getSiguiente();
            i++;
        }

        // Ordenar alfabéticamente por nombre (burbuja)
        for (int j = 0; j < cantidad - 1; j++) {
            for (int k = 0; k < cantidad - j - 1; k++) {
                String nombre1 = arreglo[k].getNombreCompleto().toLowerCase();
                String nombre2 = arreglo[k + 1].getNombreCompleto().toLowerCase();
                if (nombre1.compareTo(nombre2) > 0) {
                    Cliente temp = arreglo[k];
                    arreglo[k] = arreglo[k + 1];
                    arreglo[k + 1] = temp;
                }
            }
        }

        return arreglo;
    }

    public boolean eliminarClientePorId(int id) {
        if (cabeza == null) return false;

        if (cabeza.getCliente().getId() == id) {
            cabeza = cabeza.getSiguiente();
            return true;
        }

        NodoCliente anterior = cabeza;
        NodoCliente actual = cabeza.getSiguiente();

        while (actual != null) {
            if (actual.getCliente().getId() == id) {
                anterior.setSiguiente(actual.getSiguiente());
                return true;
            }
            anterior = actual;
            actual = actual.getSiguiente();
        }

        return false;
    }

    public void mostrarClientes() {
        NodoCliente actual = cabeza;
        while (actual != null) {
            System.out.println(actual.getCliente());
            actual = actual.getSiguiente();
        }
    }

    public int contarClientes() {
        int contador = 0;
        NodoCliente actual = cabeza;
        while (actual != null) {
            contador++;
            actual = actual.getSiguiente();
        }
        return contador;
    }


    //  Cargar clientes desde archivo sin usar List
    public void cargarDesdeArchivo(String ruta) {
        String[] lineas = ArchivoUtil.leerArchivo(ruta);
        for (int i = 0; i < lineas.length; i++) {
            String linea = lineas[i];
            String[] partes = linea.split(",");
            if (partes.length >= 2) {
                try {
                    int id = Integer.parseInt(partes[0].trim());
                    String nombres = partes[1].trim();
                    String apPaterno = partes.length > 2 ? partes[2].trim() : "";
                    String apMaterno = partes.length > 3 ? partes[3].trim() : "";
                    agregarCliente(new Cliente(id, nombres, apPaterno, apMaterno));
                } catch (NumberFormatException e) {
                    System.out.println("Error al parsear línea: " + linea);
                }
            }
        }
    }   
    //  Guardar clientes en archivo sin usar List
public void guardarEnArchivo(String ruta) {
    int cantidad = contarClientes();
    String[] lineas = new String[cantidad];
    NodoCliente actual = cabeza;
    int i = 0;

    while (actual != null) {
        Cliente c = actual.getCliente();
        lineas[i] = c.toCSV();
        actual = actual.getSiguiente();
        i++;
    }

    ArchivoUtil.escribirArchivo(ruta, lineas);
    }
    //  Obtener clientes como arreglo de Strings (para mostrar en interfaz)
public String[] obtenerClientesComoArreglo() {
    int cantidad = contarClientes();
    String[] lista = new String[cantidad];

    NodoCliente actual = cabeza;
    int i = 0;

    while (actual != null) {
        Cliente c = actual.getCliente();
        lista[i] = c.getId() + " - " + c.getNombreCompleto();
        actual = actual.getSiguiente();
        i++;
    }

    return lista;
    }

}
