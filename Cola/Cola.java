package ProyectoEstructuraDatos.Cola;

import ProyectoEstructuraDatos.Nodo.Nodo;
import ProyectoEstructuraDatos.Tiquete;

public class Cola {
    private Nodo primero, ultimo;
    private int cant = 0;

    public Cola() {
        primero = null;
        ultimo = null;
    }

    public boolean esVacia() {
        return primero == null;
    }

    public Tiquete getPrim() {
        if (esVacia()) {
            System.out.println("La cola está vacía.");
            return null;
        } else {
            return primero.getTiquete();
        }
    }

    public Tiquete getUltimo() {
        if (esVacia()) {
            System.out.println("La cola está vacía.");
            return null;
        } else {
            return ultimo.getTiquete();
        }
    }

    public int encola(Tiquete pDato) {
        Nodo nuevo = new Nodo(pDato);

        if (this.esVacia()) {
            primero = ultimo = nuevo;
        } else {
            ultimo.setSiguiente(nuevo);
            ultimo = nuevo;
        }
        cant += 1;
        return 1;
    }

    public int atiende() {
        if (!this.esVacia()) {
            if (primero == ultimo) {
                primero = ultimo = null;
            } else {
                primero = primero.getSiguiente();
            }
            cant -= 1;
            return 1;
        } else {
            return -1; // cola vacía
        }
    }

    public boolean buscar(Tiquete pDato) {
        if (this.esVacia()) {
            return false;  // cola vacía
        }
        Nodo aux = primero;
        while (aux != null) {
            if (aux.getTiquete().equals(pDato)) {
                return true;
            }
            aux = aux.getSiguiente();
        }
        return false;
    }

    public int eliminar(Tiquete pDato) {
        if (this.esVacia()) {
            return -1;  // cola vacía
        }
        if (primero.getTiquete().equals(pDato)) {
            primero = primero.getSiguiente();
            if (primero == null) {
                ultimo = null;
            }
            cant -= 1;
            return 1;
        }
        Nodo aux = primero;
        while (aux.getSiguiente() != null) {
            if (aux.getSiguiente().getTiquete().equals(pDato)) {
                aux.setSiguiente(aux.getSiguiente().getSiguiente());
                if (aux.getSiguiente() == null) {
                    ultimo = aux;
                }
                cant -= 1;
                return 1;
            }
            aux = aux.getSiguiente();
        }
        return -2; // no se encontró el elemento
    }

    public Nodo getPrimeroNodo() {
        return primero; // Método para obtener el primer nodo
    }

    public boolean estaVacia() {
        return primero == null;
    }
    
    public int getCant() {
    return cant; 
}
}
