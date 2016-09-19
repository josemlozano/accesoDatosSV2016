/*
Crea un programa que vuelque todos los bytes del 32 al 127 de un 
fichero binario a otro fichero binario, ignorando todos los bytes que 
no estén en ese rango.
*/

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.System.out;

/**
 *
 * @author carlos
 */
public class VolcarTextoDeFichero {

    public static void main(String[] args) 
            throws FileNotFoundException, IOException {
        
        Scanner sc = new Scanner (System.in);
        String nombreFichero1, nombreFichero2;
        FileInputStream fichero1 = null;
        BufferedWriter fichero2 = null;
        int dato;
        
        out.print("Introduce el nombre del fichero de entrada: ");
        nombreFichero1 = sc.nextLine();
        out.print("Escribe el nombre del fichero de salida: ");
        nombreFichero2 = sc.nextLine();
        try {
            fichero1 = new FileInputStream(new File(nombreFichero1));
            fichero2 = new BufferedWriter( new FileWriter(new File 
                (nombreFichero2)));
            while ((dato = fichero1.read()) != -1) {
                if (dato > 32 && dato < 126) {
                    fichero2.write((char) dato);
                }
            }
        } 
        catch (FileNotFoundException ex) {
            System.err.println("Archivo no encontrado");
        } 
        catch (IOException e) {
            System.err.println("Error al abrir el fichero");
        } 
        finally {
            if (fichero1 != null) {
                fichero1.close();
            }
            if (fichero2 != null) {
                fichero2.close();
            }
        }
    }
    
}
