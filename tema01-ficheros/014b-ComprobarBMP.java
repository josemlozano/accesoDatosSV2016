/*
Crea un programa que pida al usuario el nombre de un fichero y diga
si parece un BMP válido (sus dos primeros bytes son B y M).
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.System.out;
import java.util.Scanner;

/**
 *
 * @author Adrian Sanchis Gallego
 */
public class ComprobarBMP {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        out.print("Introduzca el nombre de fichero: ");
        String nombre_fichero_entrada = sc.nextLine();
        FileInputStream fichero = null;
        try {
            fichero = new FileInputStream(new File(nombre_fichero_entrada));
            byte[] dato = new byte[2];
            int cantidad = 0;

            cantidad = fichero.read(dato, 0, 2);
            if (cantidad == 2) {

                if ((char) dato[0] == 'B' && (char) dato[1] == 'M') {
                    out.println("Parece un archivo BMP");
                } else {
                    out.println("Parece que no es un archivo BMP");
                }

            } else {
                out.println("Error al leer el fichero");
            }

        } catch (FileNotFoundException ex) {
            out.println("No se ha encontrado el archivo");
        } finally {
            if (fichero != null)
                fichero.close();
        }
    }

}
