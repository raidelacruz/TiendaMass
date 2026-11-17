/**
 * Autor: Alessandra Condeso / Pretel Padilla Enrique / Angie Noemi Huaylinos / Karen Giron Faustino
 *
 * */
package tiendamass.modelo;

import java.io.*;

public class ListaUsuarios {

    private Usuario[] usuarios;
    private int cantidad;

    public ListaUsuarios() {
        usuarios = new Usuario[100]; // máximo 100 usuarios
        cantidad = 0;
    }

    public void agregarUsuario(Usuario u) {
        if (cantidad < usuarios.length) {
            usuarios[cantidad] = u;
            cantidad++;
        }
    }

    public boolean validar(String nombre, String clave) {
        for (int i = 0; i < cantidad; i++) {
            if (usuarios[i].validar(nombre, clave)) {
                return true;
            }
        }
        return false;
    }

    public void cargarDesdeArchivo(String ruta) {
        String[] lineas = ArchivoUtil.leerArchivo(ruta);
        for (int i = 0; i < lineas.length; i++) {
            String[] partes = lineas[i].split(",");
            if (partes.length == 2) {
                agregarUsuario(new Usuario(partes[0].trim(), partes[1].trim()));
            }
        }
    }
}

