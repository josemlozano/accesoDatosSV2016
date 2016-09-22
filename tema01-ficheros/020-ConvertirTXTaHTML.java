/**
 * @author David Gascón López
 * Pide fichero de entrada y de salida, convierte un archivo txt a html.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ConvertirTXTaHTML {

    public static void main(String[] args) throws IOException {
        BufferedReader ficheroEntrada = null;
        PrintWriter salida = null;
        
        try {
            System.out.print("Fichero de entrada? ");
            Scanner teclado = new Scanner(System.in);
            String nombreEntrada = teclado.nextLine();

            ficheroEntrada = new BufferedReader(new FileReader(
                    new File(nombreEntrada)));
            salida = new PrintWriter (nombreEntrada + ".html");
            
            salida.println("<html>");
            salida.println("    <head>");
            salida.println("        <title>Convierteme</title>");
            salida.println("        <meta author=\"David Gascón López\">");
            salida.println("    </head>");
            salida.println("    <body>");
            
            String linea = ficheroEntrada.readLine();
            
            while(linea != null) {
                salida.println("        <p>" + linea + "</p>");
                linea = ficheroEntrada.readLine();
            }
            
            salida.println("    </body>");
            salida.println("</html>");
        }
        
        catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado");
        }        
        catch (IOException e) {
            System.out.println("No se ha podido escribir o leer en fichero");
        }
        catch (Exception e) {
            System.out.println(
                    "Error no controlado: " + e.getMessage()); 
        } 
        finally{
            if ( ficheroEntrada != null){
                ficheroEntrada.close();
            }
            if ( salida != null){
                salida.close();
            }
        }
    }
    
}
