
package ProyectoEstructuraDatos;


import javax.swing.*;
import java.util.Date;

public class GestionPrincipal {
    
  

    private static Caja colaTiquetes = new Caja();
    private static Archivo archivo = new Archivo();
    private static String nombreBanco;
    private static int cantidadCajas;

    public static void main(String[] args) {
        archivo.crearArchivo("prod.txt");
        archivo.crearArchivo("reportes.txt");

        if (!cargarConfiguracion()) {
            configurarSistema();
        }

        mostrarMenu();
    }

    private static boolean cargarConfiguracion() {
        String config = archivo.leerArchivo(new java.io.File("prod.txt"));
        if (config == null || config.isEmpty()) {
            return false;
        }

        String[] datos = config.split(",");
        if (datos.length == 2) {
            nombreBanco = datos[0];
            cantidadCajas = Integer.parseInt(datos[1]);
            return true;
        }
        return false;
    }

    private static void configurarSistema() {
        nombreBanco = JOptionPane.showInputDialog(null, "Ingrese el nombre del banco:");
        while (true) {
            try {
                cantidadCajas = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad de cajas:"));
                if (cantidadCajas > 0) {
                    break;
                }
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un número positivo.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.");
            }
        }

        archivo.agregarAlArchivo(new java.io.File("prod.txt"), nombreBanco + "," + cantidadCajas);
        JOptionPane.showMessageDialog(null, "Configuración guardada exitosamente.");
    }

    private static void mostrarMenu() {
        while (true) {
            String opcion = JOptionPane.showInputDialog(null, "Banco: " + nombreBanco + 
                "\nSeleccione una opcion: \n"
                + "1. Crear Tiquete \n"
                + "2. Atender tiquete \n"
                + "3. Generar reporte \n"
                + "4. Salir");

            if (opcion == null || opcion.equals("4")) {
                guardarEstado();
                JOptionPane.showMessageDialog(null, "Gracias por usar el sistema.");
                break;
            }

            if (opcion.equals("1")) {
                crearTiquete();
            } else if (opcion.equals("2")) {
                atenderTiquete();
            } else if (opcion.equals("3")) {
                generarReporte();
            } else {
                JOptionPane.showMessageDialog(null, "Opcion invalida, intente nuevamente.");
            }
        }
    }

    private static void crearTiquete() {
        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del cliente: ");
        String id = JOptionPane.showInputDialog(null, "Ingrese el id del cliente:");
        int edad;
        try {
            edad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la edad del cliente:"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Edad inválida, no se creó el tiquete.");
            return;
        }
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
        JOptionPane.showMessageDialog(null, "Tiquete creado exitosamente.");
    }

    private static void atenderTiquete() {
        Tiquete tiqueteAtendido = colaTiquetes.atenderTiquete();
        if (tiqueteAtendido == null) {
            JOptionPane.showMessageDialog(null, "No hay tiquetes en la cola para atender.");
        } else {
            JOptionPane.showMessageDialog(null, "Atendiendo a: " + tiqueteAtendido);
            archivo.agregarAlArchivo(new java.io.File("reportes.txt"), tiqueteAtendido.toString());
        }
    }

    private static void generarReporte() {
        String reporte = archivo.leerArchivo(new java.io.File("reportes.txt"));
        if (reporte.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay reportes disponibles.");
        } else {
            String encabezado = "Reporte generado el: " + new Date() + "\n";
            StringBuilder contenido = new StringBuilder();
            contenido.append("===========================================================\n");
            contenido.append("                INFORME DE TIQUETES                       \n");
            contenido.append("===========================================================\n");
            contenido.append("|    Nombre    |    ID    |    Edad    |    Fecha    |    Tramite    |    Tipo    |\n");
            contenido.append(reporte);
            contenido.append("===========================================================\n");
            JOptionPane.showMessageDialog(null, encabezado + contenido.toString());
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












