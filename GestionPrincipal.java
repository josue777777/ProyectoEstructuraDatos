
package ProyectoEstructuraDatos;


import javax.swing.*;
import java.util.Date;

public class GestionPrincipal {
    private static final String CONFIG_FILE = "prod.txt";
    private static final String REPORT_FILE = "reportes.txt";

    private static Caja colaTiquetes = new Caja();
    private static Archivo archivo = new Archivo();

    public static void main(String[] args) {
        archivo.crearArchivo(CONFIG_FILE);
        archivo.crearArchivo(REPORT_FILE);

        while (true) {
            String opcion = JOptionPane.showInputDialog(null, "Seleccione una opcion: \n"
                    + "1. Crear Tiquete \n"
                    + "2. Atender tiquete \n"
                    + "3. Generar reporte \n"
                    + "4. Salir");

            if (opcion == null || opcion.equals("4")) {
                guardarEstado();
                JOptionPane.showMessageDialog(null, "Gracias por usar el sistema.");
                break;
            }

            switch (opcion) {
                case "1":
                    crearTiquete();
                    break;
                case "2":
                    atenderTiquete();
                    break;
                case "3":
                    generarReporte();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion invalida, intente nuevamente.");
            }
        }
    }

    private static void crearTiquete() {
        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del cliente: ");
        String id = JOptionPane.showInputDialog(null, "Ingrese el id del cliente:");
        int edad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la edad del cliente:"));
        String tramite = JOptionPane.showInputDialog(null, "Ingrese el trámite que desea:");
        String tipoInput = JOptionPane.showInputDialog(null, "Ingrese el tipo de tiquete (P, A, B):");

        if (nombre == null || tipoInput == null || tipoInput.length() != 1) {
            JOptionPane.showMessageDialog(null, "Datos inválidos, no se creó el tiquete.");
            return;
        }

        char tipo = Character.toUpperCase(tipoInput.charAt(0));

        if (tipo != 'P' && tipo != 'A' && tipo != 'B') {
            JOptionPane.showMessageDialog(null, "Tipo de tiquete inválido. Use P, A o B.");
            return;
        }

        Tiquete nuevoTiquete = new Tiquete(nombre, id, edad, new Date(), tramite, tipo);
        colaTiquetes.agregarTiquete(nuevoTiquete);
        validarResultado("Creación de tiquete", 1);
    }


    private static void atenderTiquete() {
        Tiquete tiqueteAtendido = colaTiquetes.atenderTiquete();
        if (tiqueteAtendido == null) {
            validarResultado("Atencion de tiquete", 0);
        } else {
            JOptionPane.showMessageDialog(null, "Atendiendo a: " + tiqueteAtendido);
            archivo.agregarAlArchivo(new java.io.File(REPORT_FILE), tiqueteAtendido.toString());
            validarResultado("Atencion de tiquete", 1);
        }
    }



    private static void generarReporte() {
        String reporte = archivo.leerArchivo(new java.io.File(REPORT_FILE));
        if (reporte.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay reportes disponibles.");
        } else {
            String reporteFormateado = formatArchivoParaMostrar(reporte);
            JOptionPane.showMessageDialog(null, reporteFormateado);
        }
    }

    private static void guardarEstado() {
        // Lógica para guardar el estado actual de las colas (si aplica)
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

    // Con este metodo muestro los tiquetes agregados en un formato agradable a la vista
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












