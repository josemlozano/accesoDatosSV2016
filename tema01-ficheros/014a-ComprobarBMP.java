/*
Crea un programa que pida al usuario el nombre de un fichero y diga si 
parece un BMP válido (sus dos primeros bytes son B y M).
*/

import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Pedro Antonio
 */
public class ComprobarBMP {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre de archivo: ");
        String name = sc.nextLine();

        try {
            FileInputStream ficheroALeer =
                new FileInputStream(new File(name));

            int data1 = ficheroALeer.read();
            int data2 = ficheroALeer.read();
            ficheroALeer.close();

            if ((data1 == 'B') && ( data2 == 'M')) {
                System.out.println("Parece un archivo .bmp");
            }
            else {
                System.out.println("No parece un archivo .bmp");
            }

        }
        catch(FileNotFoundException e) {
            System.out.println("Archivo no encontrado");
        }
        catch(IOException e) {
            System.out.println("Problema de entrada/salida " + e.getMessage());
        }
        catch(Exception e) {
            System.out.println("Error inesperado:" + e.getMessage());
        }
    }

}
