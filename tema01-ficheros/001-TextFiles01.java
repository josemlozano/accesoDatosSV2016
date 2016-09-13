/*
Crea un programa que escriba las frases "Uno", "Dos" y "Tres", cada una 
en una línea, dentro de un fichero llamado "numeros.txt"
*/

import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class TextFiles01 {
    public static void main(String[] args) {
        try
        {
            PrintWriter printWriter = new PrintWriter ("numeros.txt");
            printWriter.println ("Uno");
            printWriter.println ("Dos");
            printWriter.println ("Tres");
            printWriter.close (); 
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
