/*
 * Crea un programa muestre la cabecera de un fichero MP3 con ID3V1, 
 * cuyo nombre introduzca el usuario.
 * 
 * @author chao chen
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Mp3Reader {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Introduzca el nombre del archivo de entrada: ");
        String fichero = scan.nextLine();
        
        try {
            File ficheroEntrada = new File(fichero);
            InputStream entrada = new FileInputStream(ficheroEntrada);
            int tamanyo = (int)ficheroEntrada.length();
            byte[] datos = new byte[128];
            entrada.skip(tamanyo - 128);
            int cantidadDatos = entrada.read(datos, 0, 128);
            entrada.close();
            
            if (cantidadDatos != 128) {
                System.out.print("Fichero demasiado corto");
                return;
            }
            
            if ((char)datos[0] == 'T' && (char)datos[1] == 'A' && 
                    (char)datos[2] == 'G') {
                
                System.out.print("Song title: ");
                for (int i = 3; i < 32; i++) {
                    if (datos[i] != 0) {
                        System.out.print((char)datos[i]);
                    }
                }
                System.out.println();
                
                System.out.print("Artist: ");
                for (int i = 32; i < 62; i++) {
                    if (datos[i] != 0) {
                        System.out.print((char)datos[i]);
                    }
                }
                System.out.println();
                
                System.out.print("Album: ");
                for (int i = 63; i < 92; i++) {
                    if (datos[i] != 0) {
                        System.out.print((char)datos[i]);
                    }
                }
                System.out.println();
                
                System.out.print("Year: ");
                for (int i = 92; i < 96; i++) {
                    System.out.print((char)datos[i]);
                }
                System.out.println();
                
                System.out.print("Comment: ");
                for (int i = 97; i < 127; i++) {
                    if (datos[i] != 0) {
                        System.out.print((char)datos[i]);
                    }
                }
                System.out.println();
                
                System.out.print("Genre: ");
                System.out.println(datos[127]);
            }
            else {
                System.out.println("No es un archivo mp3 válido.");
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado.");
        }
        catch (IOException e) {
            System.out.println("Error en la lectura del archivo.");
        }
        catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
}
