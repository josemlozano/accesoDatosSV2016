/*
Crea un programa que pida al usuario un número entero n y cree un 
fichero "cuadrado.txt", que contendrá n líneas, cada una de las cuales 
estará formada por n asteriscos.

Variante 1: usa Scanner, "construye" una línea y la escribe varias veces
*/

import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Bruno Del Greco
 */
public class Cuadrado {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Que tamaño tiene el cuadrado? ");
        int n = entrada.nextInt();
        
        String linea="";
        
        for (int i = 0; i<n; i++){
            linea += "*";
        }
        
        try {
            PrintWriter printWriter = new PrintWriter("cuadrado.txt");
            
            for (int i = 0; i<n; i++){
                printWriter.println(linea);
            }
            printWriter.close();
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
