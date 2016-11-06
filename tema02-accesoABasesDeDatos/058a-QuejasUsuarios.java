/*
Crea una clase Java que represente la interfaz con el usuario: por 
ahora, mostrará un menú que permitirá únicamente añadir nuevos usuarios 
o buscar usuarios (introduciendo parte del nombre), así como salir de 
la aplicación. Esta aplicación no deberá acceder directamente a la base 
de datos, sino emplear los métodos que le ofrece la clase "usuarios".
*/

package quejasusuarios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Ruben Blanco
 */
public class QuejasUsuarios {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        int opcion;
        Usuarios myUser = new Usuarios();
        Scanner teclado = new Scanner(System.in);

        do {
            System.out.println();
            System.out.println("1- Añadir usuario:");
            System.out.println("2- Obtener codigo de usuario por nombre:");
            System.out.println("3- Obtener nombre de usuario por codigo:");
            System.out.println("4- Obtener lista de codigos cuyo nombre contenga texto:");
            System.out.println("0- Salir:");

            opcion = teclado.nextInt();
            teclado.nextLine();
            
            switch (opcion) {
                case 1:
                    System.out.println("Nombre: ");
                    String nombre = teclado.nextLine();
                    myUser.anadir(nombre);
                    break;
                    
                case 2:
                    int codigo2;
                    System.out.println("Nombre: ");
                    String nombreBuscar = teclado.nextLine();
                    codigo2 = myUser.obtenerCodigo(nombreBuscar);
                    System.out.println("Codigo: " + codigo2);
                    break;
                    
                case 3:
                    String nombre3;
                    System.out.println("Codigo: ");
                    int cod = Integer.parseInt(teclado.nextLine());
                    nombre3 = myUser.obtenerNombre(cod);
                    System.out.println("Nombre: " + nombre3);
                    break;
                    
                case 4:
                    ArrayList<Integer> list4 = new ArrayList<>();
                    System.out.println("Texto: ");
                    String texto = teclado.nextLine();
                    list4 = myUser.obtenerCodigos(texto);
                    
                    for (int i = 0; i < list4.size(); i++) {
                        System.out.println("Codigo: " + list4.get(i));
                        System.out.println("Nombre: " + myUser.obtenerNombre(list4.get(i)));
                    }
                    break;
                default:
                    break;
            }
        } while (opcion != 0);
        System.out.println("Hasta luego!");
    }
}
