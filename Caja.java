/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoEstructuraDatos;

import ProyectoEstructuraDatos.Nodo.Nodo;
import ProyectoEstructuraDatos.Tiquete;
import ProyectoEstructuraDatos.Cola.Cola;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Caja {
    private Cola colaTiquetes;
    private boolean ocupada;
    private Tiquete tiqueteEnAtencion;

    public Caja() {
        colaTiquetes = new Cola();
        ocupada = false;
    }

    public void agregarTiquete(Tiquete tiquete) {
        colaTiquetes.encola(tiquete);
        System.out.println("Tiquete agregado: " + tiquete);

        if (!ocupada) {
            atenderSiguiente();
        } else {
            int posicionEnCola = colaTiquetes.getCant() - 1; 
            System.out.println("Caja ocupada. # de posicion: " + posicionEnCola + " de la cola.");
        }
    }

    public Tiquete atenderSiguiente() {
        if (colaTiquetes.estaVacia()) {
            System.out.println("No hay tiquetes para atender.");
            ocupada = false; 
            return null;
        }

        Tiquete tiqueteAtendido = colaTiquetes.getPrim();
        if (tiqueteAtendido != null) {
            tiqueteAtendido.setHoraAtencion(new Date());
            colaTiquetes.atiende();
            ocupada = true; 
            tiqueteEnAtencion = tiqueteAtendido; 
            System.out.println("Atendiendo tiquete: " + tiqueteAtendido);
        }
        return tiqueteAtendido;
    }
    public void finalizarAtencion() {
        if (tiqueteEnAtencion != null) {
            System.out.println("Finalizando atencion para: " + tiqueteEnAtencion);
            ocupada = false; // Liberar la caja
            tiqueteEnAtencion = null; // Limpiar el tiquete en atención

            // Atender el siguiente tiquete en la cola
            atenderSiguiente();
        } else {
            System.out.println("No hay ningún tiquete atendiendose");
        }
    }

    public Tiquete atenderTiquete() {
        if (ocupada) {
            System.out.println("La caja está ocupada atendiendo a otro cliente.");
            return null;
        }

        if (colaTiquetes.estaVacia()) {
            System.out.println("No hay tiquetes en la cola para atender.");
            return null;
        }

        Tiquete tiqueteAtendido = atenderSiguiente();
        if (tiqueteAtendido != null) {
            System.out.println("Atendiendo tiquete: " + tiqueteAtendido);
        }

        return tiqueteAtendido;
    }


    public boolean estaOcupada() {
        return ocupada;
    }

    public int getColaSize() {
        return colaTiquetes.getCant();
    }
}


