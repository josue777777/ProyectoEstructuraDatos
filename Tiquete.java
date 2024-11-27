/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoEstructuraDatos;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tiquete {
    private String nombre;
    private String id;
    private int edad;
    private Date fechaCreacion;
    private Date horaAtencion;
    private String tramite;
    private char tipo;

    public Tiquete(String nombre, String id, int edad, Date horaCreacion, String tramite, char tipo) {
        this.nombre = nombre;
        this.id = id;
        this.edad = edad;
        this.fechaCreacion = horaCreacion;
        this.horaAtencion = null; // Inicializado como null porque aún no se ha atendido
        this.tramite = tramite;
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getHoraAtencion() {
        return horaAtencion;
    }

    public void setHoraAtencion(Date horaAtencion) {
        this.horaAtencion = horaAtencion;
    }

    public String getTramite() {
        return tramite;
    }

    public void setTramite(String tramite) {
        this.tramite = tramite;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return String.format("| %-12s | %-8s | %-8d | %-10s | %-15s | %-6c |",
                nombre, id, edad, sdf.format(fechaCreacion), tramite, tipo);
    }
}
