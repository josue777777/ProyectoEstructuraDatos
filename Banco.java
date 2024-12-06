/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoEstructuraDatos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author JosueNavarroB
 */
public class Banco {
    private static String nombreBanco;
    private static int cantidadCajas;
    private static final String CONFIG_FILE = "prod.txt";



    public static boolean verificarConfiguracion() {
        try (BufferedReader br = new BufferedReader(new FileReader(CONFIG_FILE))) {
            String linea = br.readLine();
            if (linea != null && !linea.isEmpty()) {
                String[] datos = linea.split(",");
                if (datos.length == 2) {
                    nombreBanco = datos[0];
                    cantidadCajas = Integer.parseInt(datos[1]);
                    return true;
                }
            }
        } catch (IOException | NumberFormatException e) {
            // Si ocurre un error, asumimos que no está configurado
        }
        return false;
    }

    public static void configurarSistema() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Ingrese el nombre del banco: ");
            nombreBanco = scanner.nextLine();

            System.out.print("Ingrese la cantidad de cajas: ");
            while (true) {
                try {
                    cantidadCajas = Integer.parseInt(scanner.nextLine());
                    if (cantidadCajas > 0) {
                        break;
                    } else {
                        System.out.println("Por favor, ingrese un número positivo.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Por favor, ingrese un número válido.");
                }
            }

            guardarConfiguracion();
            System.out.println("Configuración completada exitosamente.");
        }
    }

    private static void guardarConfiguracion() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CONFIG_FILE))) {
            bw.write(nombreBanco + "," + cantidadCajas);
        } catch (IOException e) {
            System.out.println("Error al guardar la configuración: " + e.getMessage());
        }
    }

    public static String getNombreBanco() {
        if (verificarConfiguracion()) {
            return nombreBanco;
        }
        return null;
    }

    public static int getCantidadCajas() {
        if (verificarConfiguracion()) {
            return cantidadCajas;
        }
        return -1;
    }

    public static void setNombreBanco(String nuevoNombre) {
        nombreBanco = nuevoNombre;
        guardarConfiguracion();
    }

    public static void setCantidadCajas(int nuevasCajas) {
        if (nuevasCajas > 0) {
            cantidadCajas = nuevasCajas;
            guardarConfiguracion();
        } else {
            System.out.println("La cantidad de cajas debe ser un número positivo.");
        }
    }
}

