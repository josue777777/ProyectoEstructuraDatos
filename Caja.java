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
    
 
    public void generarReporte(String nombreArchivo) {
    if (colaTiquetes.estaVacia()) {
        System.out.println("No hay tiquetes en la cola para generar un reporte.");
        return;
    }

    List<Tiquete> listaTiquetes = new ArrayList<>();
    Nodo aux = colaTiquetes.getPrimeroNodo();

    // Recorrer los nodos de la cola y agregar los tiquetes a la lista
    while (aux != null) {
        if (aux.getTiquete() != null) { // Verificar que el tiquete no sea nulo
            listaTiquetes.add(aux.getTiquete());
        }
        aux = aux.getSiguiente();
    }

    // Generar el archivo de reporte
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
        // Encabezado del reporte
        writer.write("Reporte de Tiquetes\n");
        writer.write("===================\n");
        writer.write("Generado el: " + new Date() + "\n\n");

        // Detalle de los tiquetes
        for (Tiquete tiquete : listaTiquetes) {
            writer.write(tiquete.toString() + "\n");
        }

        System.out.println("Reporte generado correctamente en el archivo: " + nombreArchivo);
    } catch (IOException e) {
        System.err.println("Error al generar el reporte: " + e.getMessage());
    }
}

    public boolean estaOcupada() {
        return ocupada;
    }

    public int getColaSize() {
        return colaTiquetes.getCant();
    }
}


