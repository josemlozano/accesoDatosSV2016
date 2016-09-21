/*
Crea un programa que vuelque todos los bytes del 32 al 127 de un 
fichero binario a otro fichero binario, ignorando todos los bytes 
que no estén en ese rango. Debes leer usando bloques de 1024 bytes.
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author monikote
 */ 

public class LeeFichBloques {
    public static void main(String[] args) throws FileNotFoundException, IOException {
    
        final int BUFFER_SIZE = 1024;

        Scanner scn = new Scanner(System.in);
        System.out.print("Nombre del fichero entrada: ");
        String nombref= scn.next();
        System.out.print("Nombre del fichero salida: ");
        String salidaf= scn.next();
        
        FileInputStream fichero = null;
        FileOutputStream fichout = null;
        
        try {
            fichero = new FileInputStream(new File(nombref));
            fichout =  new FileOutputStream(new File(salidaf)); 
            
            byte[] buffer = new byte [BUFFER_SIZE];
            int cantidadLeida;
            while ((cantidadLeida = 
                fichero.read(buffer,0,BUFFER_SIZE) ) > 0)
            {
                for (int i = 0; i < cantidadLeida; i++) { 
                    if ( buffer[i] >= 32 && buffer[i] <= 127 ) {
                        fichout.write((char) buffer[i]);
                    } 
                } 
            } 
        }
        catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado"); 
        } 
        catch (IOException e) {
            System.out.println(
                    "No se ha podido leer: "
                    + e.getMessage()); 
        }
        catch (Exception e) {
            System.out.println(
                    "Error no controlado: "
                    + e.getMessage()); 
        } 
        finally{
            if ( fichero != null){
                fichero.close();
            }
            if ( fichout != null){
                fichout.close();
            }
        }         
    } 
}
