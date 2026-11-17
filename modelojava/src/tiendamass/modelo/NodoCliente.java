package tiendamass.modelo;

/**
 * NodoCliente representa un nodo de la lista enlazada de clientes.
 * Contiene un objeto Cliente y una referencia al siguiente nodo.
 */
public class NodoCliente {

    private Cliente cliente;
    private NodoCliente siguiente;

    // Constructor
    public NodoCliente(Cliente cliente) {
        this.cliente = cliente;
        this.siguiente = null;
    }

    // Getter: cliente
    public Cliente getCliente() {
        return cliente;
    }

    // Setter: cliente
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    // Getter: siguiente nodo
    public NodoCliente getSiguiente() {
        return siguiente;
    }

    // Setter: siguiente nodo
    public void setSiguiente(NodoCliente siguiente) {
        this.siguiente = siguiente;
    }
}
