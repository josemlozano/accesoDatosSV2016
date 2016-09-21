/**
 * @author David Gascón López
 * Pide el nombre de dos archivos BMP al usuario y comprueba si es 
 * un BMP y muestra por pantalla el ancho y el alto de esta imagen.
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ComprobarAnchoAltoBmp {

    public static void main(String[] args) {
        
        final int BUFFER_SIZE = 54;

        Scanner scn = new Scanner(System.in);
        System.out.print("Nombre del fichero de entrada: ");
        String nombref= scn.next();
        
        FileInputStream fichero = null;
        
        try {
            fichero = new FileInputStream(new File(nombref));
            int ancho = 0;
            int alto = 0;
            boolean esBmp = true;
            
            byte[] datos = new byte [BUFFER_SIZE];
            int cantidadLeida;
            cantidadLeida = fichero.read(datos,0,BUFFER_SIZE);
            fichero.close();
            
            if(cantidadLeida == BUFFER_SIZE) {
                if((datos[0] == 'B') && (datos[1] == 'M')) {
                    
                    // Primera forma de esquivar el problema
                    // de que los datos entre 127 y 255 aparezcan
                    // como negativos
                    if(datos[18] < 0){
                        ancho = datos[18] + 256; 
                    }
                    else{
                        ancho = datos[18];
                    }
                    ancho += (datos[19] * 256);
                    
                    // Segunda alternativa, algo más compacta
                    alto = datos[23] * 256 + datos[22];
                    if(datos[22] < 0) {
                        alto += 256; 
                    }
                }
                else {
                    esBmp = false;
                }
            }
            else {
                esBmp = false;
            }
            
            if (esBmp) {
                System.out.println("Ancho: " + ancho + " alto: " + alto);
            }
            else{
                System.out.println("No es Un fichero BMP.");
            }
            
        }
        catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado"); 
        } 
        catch (IOException e) {
            System.out.println(
                    "No se ha podido leer/escribir: " + e.getMessage()); 
        }
        catch (Exception e) {
            System.out.println(
                    "Error no controlado: " + e.getMessage()); 
        } 
    }
}
