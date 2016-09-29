/*
Crea un programa capaz de pedir al usuario tres datos de ordenadores clásicos
(marca, modelo, año), guardarlos uno a uno en un fichero usando serialización
y luego leer esos tres datos y mostrarlos.
 */
package ordenadoresclasicos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 *
 * @author Adrian Sanchis Gallego
 */
public class OrdenadoresClasicos {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectOutputStream ficheriSalida = null;
        File misOrdenadores = new File("ordenadores.dat");
        try {
            Scanner sc = new Scanner(System.in);
            ficheriSalida = new ObjectOutputStream(new FileOutputStream(misOrdenadores));
            for (int salida = 0; salida < 3; salida++) {
                System.out.print("Introduce la marca de ordenador: ");
                String marca = sc.nextLine();

                System.out.print("Introduce el modelo de ordenador: ");
                String modelo = sc.nextLine();

                System.out.print("Introduce el año: ");
                int anyo = Integer.parseInt(sc.nextLine());

                ficheriSalida.writeObject(new Ordenador(marca, modelo, anyo));

            }
        } catch (IOException e) {
            System.out.println("Problema entrada/Salida " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado " + e.getMessage());
        } finally {
            if (ficheriSalida != null) {
                ficheriSalida.close();
            }
        }

        ObjectInputStream ficheroEntrada = null;

        try {
            Scanner sc = new Scanner(System.in);
            ficheroEntrada = new ObjectInputStream(new FileInputStream(misOrdenadores));
            for (int entrada = 0; entrada < 3; entrada++) {
                ((Ordenador) ficheroEntrada.readObject()).Mostrar();
            }
        } catch (IOException e) {
            System.out.println("Problema entrada/Salida " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado " + e.getMessage());
        } finally {
            if (ficheriSalida != null) {
                ficheriSalida.close();
            }
        }

    }

}
