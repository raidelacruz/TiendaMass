package tiendamass.modelo;

/**
 * Autor: Alessandra Condeso / Pretel Padilla Enrique / Angie Noemi Huaylinos / Karen Giron Faustino
 *
 * Clase Cliente
 * Representa a un cliente con sus datos personales.
 * Se utiliza en ventas, pedidos y facturación.
 */
public class Cliente {

    private int id;             // Identificador único del cliente
    private String nombre;      // Nombre del cliente
    private String apPaterno;   // Apellido paterno
    private String apMaterno;   // Apellido materno

    // Constructor: inicializa todos los campos del cliente
    public Cliente(int id, String nombre, String apPaterno, String apMaterno) {
        this.id = id;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
    }

    // Devuelve el ID del cliente
    public int getId() {
        return id;
    }

    // Devuelve el nombre del cliente
    public String getNombre() {
        return nombre;
    }

    // Devuelve el apellido paterno
    public String getApPaterno() {
        return apPaterno;
    }

    // Devuelve el apellido materno
    public String getApMaterno() {
        return apMaterno;
    }

    // Devuelve el nombre completo del cliente
    public String getNombreCompleto() {
        return nombre + " " + apPaterno + " " + apMaterno;
    }

    // Devuelve una representación CSV del cliente (para guardar en archivo)
    public String toCSV() {
        return id + "," + nombre + "," + apPaterno + "," + apMaterno;
    }

    // Devuelve una representación en texto del cliente
    @Override
    public String toString() {
        return id + " - " + nombre + " - " + apPaterno + " - " + apMaterno;
    }
}
