/* 
 * Pedir una frase al usuario y añadirla al final del
 * fichero "registro.txt" 
 */

import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class AnadirFichero {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Frase? ");
        String frase = entrada.nextLine();
        
        try{
            PrintWriter printWriter = new PrintWriter(new
                BufferedWriter(
                new FileWriter("registro.txt", true)));
            printWriter.println(frase);
            printWriter.close();
        }
        catch(Exception e)
        {
           System.out.print("Ocurrio un error "+e);
        }
    }
}
