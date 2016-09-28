//Vicente Cuenca 


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import manejadoramigos.Amigo;

public class ManejadorAmigos {

    public static void main(String[] args) throws FileNotFoundException, IOException,
            ClassNotFoundException {
        try {
            List<Amigo> misAmigos = new ArrayList<Amigo>();
            
            ManejadorAmigos miManejador = new ManejadorAmigos();

            misAmigos = miManejador.Cargar();

            String nombre;
            String correo;
            int anyo;
            int movil;

            int opcion;

            Scanner teclado = new Scanner(System.in);

            do {
                System.out.println("1- Añadir amigo:");
                System.out.println("2- Ver amigos:");
                System.out.println("0- Salir:");

                opcion = Integer.parseInt(teclado.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.println("Nombre:");
                        nombre = teclado.nextLine();
                        System.out.println("Correo:");
                        correo = teclado.nextLine();
                        System.out.println("año:");
                        anyo = Integer.parseInt(teclado.nextLine());
                        System.out.println("Movil:");
                        movil = Integer.parseInt(teclado.nextLine());

                        Amigo a1 = new Amigo(nombre, correo, anyo, movil);

                        misAmigos.add(a1);

                        miManejador.Guardar(misAmigos);

                        break;
                    case 2:
                        miManejador.mostrarAmigos(misAmigos);
                        break;
                    case 0:
                        miManejador.Guardar(misAmigos);
                        break;
                    default:
                        break;
                }
            } while (opcion != 0);

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado!");
        } catch (IOException e) {
            System.out.println("Problema de entrada o salida! " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado! " + e.getMessage());
        }

    }

    private void mostrarAmigos(List<Amigo> misAmigos) {
        for (int i = 0; i < misAmigos.size(); i++) {
            misAmigos.get(i).escribir();
        }
    }

    private List<Amigo> Cargar() throws FileNotFoundException, IOException, ClassNotFoundException {

        if ((new File("amigos.dat")).exists()) {

            List<Amigo> misAmigosReturn = new ArrayList<Amigo>();

            File fichero = new File("amigos.dat");
            FileInputStream ficheroSalida
                    = new FileInputStream(fichero);
            ObjectInputStream ficheroObjetos
                    = new ObjectInputStream(ficheroSalida);
            misAmigosReturn = (List<Amigo>) ficheroObjetos.readObject();
            ficheroObjetos.close();

            return misAmigosReturn;

        }
        return null;
    }

    private void Guardar(List<Amigo> misAmigos) throws FileNotFoundException, IOException {
        File ficheroGuardar = new File("amigos.dat");
        FileOutputStream ficheroSalidaGuardar
                = new FileOutputStream(ficheroGuardar);
        ObjectOutputStream ficheroObjetosGuardar
                = new ObjectOutputStream(ficheroSalidaGuardar);

        ficheroObjetosGuardar.writeObject(misAmigos);

        ficheroObjetosGuardar.close();
    }

}
