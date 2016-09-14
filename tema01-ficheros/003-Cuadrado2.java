/*
Crea una variante del ejercicio 2, que use "throws" en vez de "try-catch"
*/

import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Bruno Del Greco
 */
public class Cuadrado2 {

    public static void main(String[] args)  
            throws FileNotFoundException {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Que tamaño tiene el cuadrado? ");
        int n = entrada.nextInt();
        
        String linea="";
        
        for (int i = 0; i<n; i++){
            linea += "*";
        }

        PrintWriter printWriter = new PrintWriter("cuadrado.txt");
        for (int i = 0; i<n; i++){
            printWriter.println(linea);
        }
        printWriter.close();
    }
}
