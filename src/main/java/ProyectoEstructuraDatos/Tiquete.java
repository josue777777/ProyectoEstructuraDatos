package ProyectoEstructuraDatos;

import java.util.Date;

public class Tiquete {
    private String nombre;
    private String id;
    private int edad;
    private Date horaCreacion;
    private Date horaAtencion;
    private String tramite;
    private char tipo;



    public Tiquete(String nombre, String id, int edad, Date horaCreacion, String tramite, char tipo) {
        this.nombre = nombre;
        this.id = id;
        this.edad = edad;
        this.horaCreacion = new Date(); // se genera automatico a la hora de crear el tiquete
        this.horaAtencion = null; // lo inicializamos asi por que todavia no se atiende
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

    public Date getHoraCreacion() {
        return horaCreacion;
    }

    public void setHoraCreacion(Date horaCreacion) {
        this.horaCreacion = horaCreacion;
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
        return "Tiquete: " +
                "\nnombre: " + nombre +
                "\n id: '" + id +
                "\n edad: " + edad +
                "\n horaCreacion: " + horaCreacion +
                "\n horaAtencion: " + horaAtencion +
                "\n tramite: " + tramite +
                "\n tipo: " + tipo ;
    }
}
