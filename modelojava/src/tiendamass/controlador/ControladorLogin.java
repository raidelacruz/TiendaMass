package tiendamass.controlador;

import tiendamass.modelo.*;

public class ControladorLogin {

    private ListaUsuarios listaUsuarios;

    public ControladorLogin() {
        listaUsuarios = new ListaUsuarios();
        listaUsuarios.cargarDesdeArchivo("src/recursos/usuarios.txt");
    }

    public boolean autenticar(String usuario, String clave) {
        return listaUsuarios.validar(usuario, clave);
    }
}
