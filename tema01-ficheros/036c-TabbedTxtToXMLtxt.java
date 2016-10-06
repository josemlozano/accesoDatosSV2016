/*
 * @author David_Gascón_López
 * Convertir a XML.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class TabbedTxtToXMLtxt {

    public static void main(String[] args) throws IOException {
        BufferedReader ficheroEntrada = null;
        PrintWriter salida = null;
        
        try {
            ficheroEntrada = new BufferedReader(new FileReader(
                    new File("surnames.txt")));
            salida = new PrintWriter ("surnames.xml");
            
            salida.println("<surnames>");
            
            String linea = ficheroEntrada.readLine();
            
            while(linea != null) {
                salida.print("    <surname id=\"");
                String[] words = linea.split("\t");
                
                
                salida.print(words[0].toUpperCase().charAt(0) + 
                        words[0].substring(1).toLowerCase() + "\" amount=\"" + 
                        words[1].replace(",", "")
                        .trim() + "\" />");
                linea = ficheroEntrada.readLine();
            }
            
            salida.println("</surnames>");
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
