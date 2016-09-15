/**
 * @author David Gascón López
 * Pide el nombre de dos archivos al usuario y lo vuelca en otro que tambien se 
 * ha pedido el nombre de forma ordenada.
 */
package volcadoordenadoafichero;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class VolcadoOrdenadoAFichero {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el nombre del archivo 1: ");
        String nombre1 = sc.next();
        File f1 = new File(nombre1);
        if(f1.exists() && !f1.isDirectory()){
            System.out.print("Introduce el nombre del archivo 2: ");
            String nombre2 = sc.next();

            File f2 = new File(nombre2);
            if(f2.exists() && !f2.isDirectory()) {
                System.out.print("Introduce el nombre del archivo de salida: ");
                String nombreSalida = sc.next();

                BufferedReader ficheroEntrada1 = null;
                BufferedReader ficheroEntrada2 = null;
                PrintWriter ficheroSalida = null;
                try {
                    ficheroEntrada1 = new BufferedReader(new FileReader(
                            new File(nombre1)));            

                    ArrayList<String> palabras = new ArrayList<String>();
                    String linea;

                    do {
                        linea = ficheroEntrada1.readLine();
                        if(linea != null){
                            palabras.add(linea);
						}
                    } while(linea != null);

                    ficheroEntrada2 = new BufferedReader(new FileReader(
                            new File(nombre2)));

                    do {
                        linea = ficheroEntrada2.readLine();
                        if(linea != null) {
                            palabras.add(linea);
                        }
                    } while(linea != null);

                    palabras.sort(null);
                    
                    ficheroSalida = new PrintWriter (nombreSalida);

                    for(String palabra : palabras) {
                        ficheroSalida.println(palabra);
                    }

                }
                catch (FileNotFoundException e) {
                    System.out.println("El fichero no se ha encontrado.");
                }
                catch (IOException e) {
                    System.out.println("No se ha podido leer.");
                }
                finally {
                    if(ficheroEntrada1 != null) {
                        ficheroEntrada1.close();
                    }
                    if(ficheroEntrada2 != null) {
                        ficheroEntrada2.close();
                    }
                    if(ficheroSalida != null) {
                        ficheroSalida.close();
                    }    
                }
            }
        }
    }   
}
