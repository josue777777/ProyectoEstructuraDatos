
package ProyectoEstructuraDatos;

import java.io.File;
import javax.swing.*;
import java.util.Date;


public class GestionPrincipal {
    public static void main(String[] args) {
       
        Caja caja1 = new Caja();
        Caja caja2 = new Caja();

        Archivo archivo = new Archivo();
        File archivoTiquetes = archivo.crearArchivo("ListaTiquetes.txt");
        if (archivoTiquetes == null) {
            JOptionPane.showMessageDialog(null, "Error al crear el archivo", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Limpiar el archivo
        int resultadoSobreescribir = archivo.sobreEscribirArchivo(archivoTiquetes, "");
        validarResultado("Limpieza del archivo", resultadoSobreescribir);

        // Agregar un tiquete al archivo
        Tiquete tiquete1 = new Tiquete("Juan", "123456", 30, new Date(), "Solicitud de permisos", 'A');
        Tiquete tiquete2 = new Tiquete("Maria", "789012", 25, new Date(), "Consulta general", 'B');
        Tiquete tiquete3 = new Tiquete("Carlos", "345678", 40, new Date(), "Pago de servicios", 'A');
        
        
        int resultado1Agregar = archivo.agregarAlArchivo(archivoTiquetes, tiquete1);
        validarResultado("Agregar tiquete al archivo", resultado1Agregar);
        
        int resultado2Agregar = archivo.agregarAlArchivo(archivoTiquetes, tiquete2);
        validarResultado("Agregar tiquete al archivo", resultado2Agregar);
        
        int resultado3Agregar = archivo.agregarAlArchivo(archivoTiquetes, tiquete3);
        validarResultado("Agregar tiquete al archivo", resultado3Agregar);
        
        
        
        // Asigna a cajas
        asignarTiqueteACaja(tiquete1, caja1, caja2);
        asignarTiqueteACaja(tiquete2, caja1, caja2);
        asignarTiqueteACaja(tiquete3, caja1, caja2);

        // Leer archivo
        String lect = archivo.leerArchivo(archivoTiquetes);
        if (lect.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se pudo leer el archivo o esta vacio", "Warning",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            // Mostrar el contenido del archivo en un JOptionPane
            JOptionPane.showMessageDialog(null, formatArchivoParaMostrar(lect), "Contenido del Archivo",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        // Atiende tiquetes
        caja1.finalizarAtencion();
        caja2.finalizarAtencion();
    }

    private static void asignarTiqueteACaja(Tiquete tiquete, Caja caja1, Caja caja2) {
        // Asigna al primer cajero disponible
        if (!caja1.estaOcupada()) {
            caja1.agregarTiquete(tiquete);
            JOptionPane.showMessageDialog(null, "Cliente asignado a la Caja 1");
        } else if (!caja2.estaOcupada()) {
            caja2.agregarTiquete(tiquete);
            JOptionPane.showMessageDialog(null, "Cliente asignado a la Caja 2");
        } else {
            // Si ambos están ocupados, asignar al que tenga menos en cola
            if (caja1.getColaSize() <= caja2.getColaSize()) {
                caja1.agregarTiquete(tiquete);
                JOptionPane.showMessageDialog(null, "Cliente asignado a la Caja 1");
            } else {
                caja2.agregarTiquete(tiquete);
                JOptionPane.showMessageDialog(null, "Cliente asignado a la Caja 2");
            }
        }
    }

    // Con este método validamos y mostramos el resultado de las operaciones con los archivos
    private static void validarResultado(String operacion, int resultado) {
        String mensaje;
        if (resultado == 1) {
            mensaje = operacion + " fue exitosa";
            JOptionPane.showMessageDialog(null, mensaje, "Exito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            mensaje = operacion + " fallo";
            JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    // Con este metodo muestro los tiquetes agregados en un ofrmato agradable a la vista
    private static String formatArchivoParaMostrar(String texto) {
        StringBuilder formato = new StringBuilder();
        formato.append("===========================================================\n");
        formato.append("                INFORME DE TIQUETES                       \n");
        formato.append("===========================================================\n");
        formato.append("|    Nombre    |    ID    |    Edad    |    Fecha    |    Tramite    |    Tipo    |\n");
        formato.append(texto);
        formato.append("===========================================================\n");
        return formato.toString();
    }
}