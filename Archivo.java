package ProyectoEstructuraDatos;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Date;

public class Archivo {

    // Metodo para crear el archivo
    public File crearArchivo(String nombre) {
        String rutaBase = System.getProperty("user.dir") + "/src/main/java/ArchivosPracticando/";
        File directorio = new File(rutaBase);
        if (!directorio.exists()) {
            directorio.mkdirs(); // Crea los directorios necesarios
        }
        File archivo = new File(directorio, nombre);
        if (!archivo.exists()) { // Si no existe, lo creamos
            try {
                archivo.createNewFile();
                System.out.println("Archivo creado: " + archivo.getPath());
            } catch (IOException e) {
                System.out.println("No se pudo crear el archivo: " + e.getMessage());
                return null; // Retorna null en caso de error
            }
        } else {
            System.out.println("El archivo ya existe: " + archivo.getPath());
        }
        return archivo;
    }

    // Metodo para escribir en un archivo (sobreescribimos lo que ya tenia)
    public int sobreEscribirArchivo(File archivo, String contenido) {
        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write(contenido);
            return 1; // Retorna 1 si se pudo sobreescribir el archivo
        } catch (IOException e) {
            return 0; // Retorna 0 si no se pudo sobreescribir el archivo
        }
    }

    // Metodo para modificar el archivo agregamos el contenido al final
    public int agregarAlArchivo(File archivo, Object contenido) {
        if (archivo != null) {
            try {
                String texto = contenido.toString() + "\n";
                Files.write(archivo.toPath(), texto.getBytes(), StandardOpenOption.APPEND);
                return 1; // Retorna 1 si se pudo agregar contenido al archivo
            } catch (IOException e) {
                return 0; // Retorna 0 si ocurrio un error
            }
        } else {
            return -1; // Retorna 0 si no se pudo escribir en el archivo
        }
    }

    // Metodo para leer el archivo
    public String leerArchivo(File archivo) {
        StringBuilder contenido = new StringBuilder();
        try {
            if (archivo != null && archivo.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                String linea;
                while ((linea = br.readLine()) != null) {
                    contenido.append(linea).append("\n");
                }
                return "" + contenido;
            }

        } catch (IOException e) {
            return "Ocurrió un error al leer el archivo: " + e.getMessage();

        }
        return contenido.toString();
    }

    // metodo para eliminar el archivo
    public int eliminarArchivo(File archivo) {
        if (archivo.delete()) {
            return 1; //se elimino correctamente
        } else {
            return 0; // no existe
        }
    }

    // Metodo para ir registrando atenciones en el archivo
    public int registrarAtencionEnArchivo(Tiquete tiquete) {
        File archivo = crearArchivo("HistorialAtencion.txt");
        if (archivo != null) {
            String registro = "Tiquete atendido: " + tiquete.toString() + ", Hora de atención: " + new Date() + "\n";
            agregarAlArchivo(archivo, registro);
            return 1; // se pudo registrar la atención
        }
        return 0; // no se pudo registrar la atención en el archivo
    }
}
