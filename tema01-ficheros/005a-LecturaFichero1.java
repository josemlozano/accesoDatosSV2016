/*
Crea un programa que pida al usuario el nombre de un fichero 
y muestre todas las líneas que éste contiene y que comienzan 
por la vocal "A".

Primera versión: sin "finally"
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class LecturaFichero1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner( System.in);
        System.out.print("Dime el nombre del fichero a mostrar: ");
        String nombre = sc.nextLine();
        
        try {
            BufferedReader ficheroEntrada = new BufferedReader(
                    new FileReader(new File(nombre)));

            String linea = ficheroEntrada.readLine();
            while (linea != null) {    
                if (linea.trim().toLowerCase().startsWith("a")) {
                    System.out.println(linea);
                }
                linea = ficheroEntrada.readLine();
            }
            ficheroEntrada.close();
        }
        catch( IOException e)
        {
            System.out.println("Problemas:");
            e.printStackTrace();
        }
    }
    
}

