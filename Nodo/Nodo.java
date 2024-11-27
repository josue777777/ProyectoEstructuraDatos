package ProyectoEstructuraDatos.Nodo;

import ProyectoEstructuraDatos.Tiquete;

public class Nodo {
    private Tiquete tiquete;
    private Nodo siguiente;

    public Nodo(Tiquete tiquete) {
        this.tiquete = tiquete;
        this.siguiente = null;
    }

    public Tiquete getTiquete() {
        return tiquete;
    }

    public void setTiquete(Tiquete tiquete) {
        this.tiquete = tiquete;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String toString() {
        return "Nodo: " +
                "\n tiquete: " + tiquete +
                "\n siguiente: " + siguiente;
    }
}
