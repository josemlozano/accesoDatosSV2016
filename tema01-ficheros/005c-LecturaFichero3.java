/*
Crea un programa que pida al usuario el nombre de un fichero 
y muestre todas las líneas que éste contiene y que comienzan 
por la vocal "A".

Tercera versión: lectura en la condición del "while"
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class LecturaFichero3 {

    public static void main(String[] args) 
            throws IOException {
        Scanner sc = new Scanner( System.in);
        System.out.print("Dime el nombre del fichero a mostrar: ");
        String nombre = sc.nextLine();
        
        BufferedReader ficheroEntrada = null;
        
        try {
            ficheroEntrada = new BufferedReader(
                    new FileReader(new File(nombre)));

            String linea;
            while ((linea = ficheroEntrada.readLine()) != null) {    
                if (linea.trim().toLowerCase().startsWith("a")) {
                    System.out.println(linea);
                }
            }
        }
        catch( FileNotFoundException e )
        {
            System.out.println("Fichero no encontrado");
        }
        catch( IOException e)
        {
            System.out.println("No se ha podido leer");
        }
        finally
        {
            if (ficheroEntrada != null) {
                ficheroEntrada.close();
            }
        }
    }   
}

