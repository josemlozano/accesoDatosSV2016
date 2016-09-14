/*
Crea un programa que pida al usuario el nombre de un fichero 
y muestre todas las líneas que éste contiene y que comienzan 
por la vocal "A".

Cuarta versión: usando el formato avanzado de "try"
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author chao chen
 */
public class LecturaFichero4 {

    public static void main(String[] args) throws IOException{
        System.out.print("Enter the file name: ");
        Scanner read = new Scanner(System.in);
        String name = read.nextLine();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(
                new File(name)))){
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.toUpperCase().trim().startsWith("A")) {
                    System.out.println(line);
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el archivo.");
        }
        catch(IOException e) {
            System.out.println("No se ha podido leer el archivo.");
        }
    }
}
