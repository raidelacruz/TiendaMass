package tiendamass.modelo;

/**
 * Autor: Alessandra Condeso / Pretel Padilla Enrique / Angie Noemi Huaylinos / Karen Giron Faustino
 *
 * Clase Usuario
 * Representa un usuario del sistema TiendaMass con nombre y clave.
 * Se utiliza para validar el acceso en el controlador de login.
 */
public class Usuario {

    private String nombre; // Nombre de usuario
    private String clave;  // Clave de acceso

    // Constructor: inicializa el nombre y la clave del usuario
    public Usuario(String nombre, String clave) {
        this.nombre = nombre;
        this.clave = clave;
    }

    // Devuelve el nombre del usuario
    public String getNombre() {
        return nombre;
    }

    // Devuelve la clave del usuario
    public String getClave() {
        return clave;
    }

    // Valida si el nombre y la clave coinciden con los del usuario
    public boolean validar(String nombre, String clave) {
        return this.nombre.equals(nombre) && this.clave.equals(clave);
    }

    // Devuelve una representación CSV del usuario (para guardar en archivo)
    public String toCSV() {
        return nombre + "," + clave;
    }
}
 