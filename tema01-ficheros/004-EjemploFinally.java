/*
Crea una variante del ejercicio 1, que use "finally" para garantizar 
que el fichero se cierra correctamente incluso en caso de error
*/

package ejemploFinally;

import java.io.PrintWriter;
import java.io.FileNotFoundException;
/**
 *
 * @author Pedro Antonio
 */
public class EjemploFinally {

    public static void main(String[] args) {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter("ejemplo.txt");
            printWriter.println("Uno");
            printWriter.println("Dos");
            printWriter.println("Tres");
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        finally{
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
}
