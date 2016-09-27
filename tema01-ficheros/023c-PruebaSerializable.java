/**
 * @author chao
 */

package pruebaserializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class PruebaSerializable {

    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        Amigos amigo;
        
        try {
            File fichero = new File("amigos.dat");
            FileInputStream ficheroEntrada = new FileInputStream(fichero);
            ObjectInputStream ficheroEntradaDeserializar = new ObjectInputStream(
                ficheroEntrada);
            
            amigo = (Amigos) ficheroEntradaDeserializar.readObject();
            amigo.mostrar();
            
            ficheroEntradaDeserializar.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado.");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Clase no encontrada.");
        }
        catch (IOException e) {
            System.out.println("Error en la lectura del archivo.");
        }
        catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
}
