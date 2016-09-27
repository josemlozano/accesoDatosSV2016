/**
 * @author David Gascón López
 * Manejado de amiguitos, tienen que tener nombre, email y año de nacimiento.
 */
package pruebaserializable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class PruebaSerializable {

    public static void main(String[] args) {
        try {
            File fichero = new File("Amigos.dat");
            FileOutputStream ficheroSalida = 
                new FileOutputStream(fichero);
            ObjectOutputStream ficheroObjetos = 
                new ObjectOutputStream(ficheroSalida);

            String nombre;
            String eMail;
            int anyoNac;
            
            Scanner teclado = new Scanner(System.in);
            for(int i = 0 ; i < 3 ; i ++){
                System.out.print("Nombre del amigo: ");
                nombre = teclado.nextLine();

                System.out.print("E-mail del amigo: ");
                eMail = teclado.nextLine();

                System.out.print("Año de nacimiento del amigo: ");
                anyoNac = teclado.nextInt();
                String saltoDeLinea = teclado.nextLine();

                Amigos amig = new Amigos(nombre, eMail, anyoNac);
                ficheroObjetos.writeObject(amig);

            }

            ficheroObjetos.close();
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
    }
}
