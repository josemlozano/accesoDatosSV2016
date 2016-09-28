package ejercicioserializardeserializar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Adrian
 */
public class EjercicioSerializarDeserializar {

    public static Amigo guardar() {
        System.out.print("Nombre del amigo: ");

        try {
            File fichero = new File("Amigos.dat");
            FileOutputStream ficheroSalida
                    = new FileOutputStream(fichero);
            ObjectOutputStream ficheroObjetos
                    = new ObjectOutputStream(ficheroSalida);

            Scanner sc = new Scanner(System.in);

            String nombre = sc.nextLine();

            System.out.print("E-mail del amigo: ");
            String eMail = sc.nextLine();

            System.out.print("Año de nacimiento del amigo: ");
            int anyoNac = sc.nextInt();

            sc.nextLine();

            System.out.print("Numero movil del amigo: ");
            String movil = sc.nextLine();

            Amigo amig = new Amigo(nombre, eMail, anyoNac, movil);
            ficheroObjetos.writeObject(amig);

            ficheroSalida.close();
            ficheroObjetos.close();

            return amig;

        } catch (FileNotFoundException e) {
            System.out.println("Error :" + e);
            return null;
        } catch (IOException e) {
            System.out.println("Error :" + e);
            return null;
        } catch (Exception e) {
            System.out.println("Error :" + e);
            return null;
        }

    }

    public static List<Amigo> cargarAmigos() throws FileNotFoundException, IOException {

        List<Amigo> misAmigos = new ArrayList<>();

        File fichero = new File("amigos.dat");
        FileInputStream ficheroEntrada = new FileInputStream(fichero);
        ObjectInputStream ficheroEntradaDeserializar = new ObjectInputStream(
                ficheroEntrada);

        try {

            while (true) {
                misAmigos.add((Amigo) ficheroEntradaDeserializar.readObject());
            }
        } catch (FileNotFoundException e) {
            ficheroEntrada.close();
            ficheroEntradaDeserializar.close();
            return misAmigos;
        } catch (IOException e) {
            ficheroEntrada.close();
            ficheroEntradaDeserializar.close();
            return misAmigos;
        } catch (Exception e) {
            System.out.println("Error :" + e);
            ficheroEntrada.close();
            ficheroEntradaDeserializar.close();
            return misAmigos;
        }

    }

    public static void main(String[] args) {

        boolean salir = false;
        List<Amigo> misAmigos = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        try {
            misAmigos = cargarAmigos();
            do {

                System.out.println("Que quieres hacer :");
                System.out.println("1.Añadir");
                System.out.println("2.Mostrar");
                System.out.println("3.Salir");
                int operacion = sc.nextInt();

                if (operacion == 1) {
                    Amigo nuevoAmigo = guardar();
                    if (nuevoAmigo != null) {
                        misAmigos.add(nuevoAmigo);
                    }
                } else if (operacion == 2) {
                    for (int i = 0; i < misAmigos.size(); i++) {
                        System.out.println(misAmigos.get(i).mostrar());
                    }
                } else if (operacion == 3) {
                    salir = true;
                }
            } while (salir == false);
        } catch (Exception e) {
            System.out.println("Error :" + e);
        }
    }
}
