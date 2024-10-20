package ProyectoEstructuraDatos.Cola;

import ProyectoEstructuraDatos.Nodo.Nodo;
import ProyectoEstructuraDatos.Tiquete;

public class  Cola {

    private Nodo primero, ultimo;
    private int cant = 0;

    public Cola() {
        primero = null;
        ultimo = null;
    }


    // verifica si hay alguien en la fila
    public boolean esVacia() {
        if (primero == null) {
            return true;
        } else {
            return false;
        }
    }



    // obtener el primero de la fila
    public Tiquete getPrim() {
        if (esVacia()) {
            System.out.println("La cola está vacía.");
            return null;
        } else {
            Tiquete primElement = primero.getTiquete();
            System.out.println("Elemento que esta primero en la fila es : " + primElement);
            return primElement;
        }
    }

    //obtener el ultimo de la fila
    public Tiquete getUltimo() {
        if (esVacia()) {
            System.out.println("La cola está vacía.");
            return null;
        } else {
            Tiquete ultimoElement = ultimo.getTiquete();
            System.out.println("Ultimo elemento en la fila es : " + ultimoElement);
            return ultimoElement;
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

    //Atiende el primero de la fila
    public int atiende() {
        if (!this.esVacia()){
            if (primero == ultimo){
                primero = ultimo = null;
            } else {
                primero = primero.getSiguiente();
            }
            cant -= 1;
            return 1;
        } else {
            return -1; // cola vacia
        }
    }


    // encontrar un elemento
    public boolean buscar(Tiquete pDato) {
        if (this.esVacia()){
            return false;  // cola vacia
        }
        Nodo aux = primero;
        while (aux!= null) {
            if (aux.getTiquete().equals(pDato)) {
                return true;
            }
            aux = aux.getSiguiente();
        }
        return false;
    }

    // eliminar un elemento
    public int eliminar(Tiquete pDato) {
        if (this.esVacia()){
            return -1;  // cola vacia
        }
        if (primero.getTiquete().equals(pDato)){
            primero = primero.getSiguiente();
            if (primero == null) {
                ultimo = null;
            }
            cant -= 1;
            return 1;
        }
        Nodo aux = primero;
        while (aux.getSiguiente()!= null) {
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
        return -2; // no se encontro el elemento a
    }

}


