package tareas;

import com.db4o.*;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Adrian Navarro, correciones por Nacho
 */
public class Tareas {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        String respuesta;
        do {
            System.out.println("Escoja una opción:");
            System.out.println("1. Añadir datos");
            System.out.println("2. Busqueda exacta");
            System.out.println("0. Salir");

            respuesta = teclado.nextLine();
            switch (respuesta) {
                case "1":
                    ObjectContainer db = null;
                    try {
                        db = Db4o.openFile("misDatos.dat");

                        System.out.print("Año? ");
                        int anyo = Integer.parseInt(teclado.nextLine());
                        System.out.print("Mes? ");
                        int mes = Integer.parseInt(teclado.nextLine());
                        System.out.print("Día? ");
                        int dia = Integer.parseInt(teclado.nextLine());
                        System.out.print("Descripción? ");
                        String descripcion = teclado.nextLine();
                        System.out.print("Categoría? ");
                        String categoria = teclado.nextLine();
                        System.out.print("Prioridad (1 a 5)? ");
                        int prioridad = Integer.parseInt(teclado.nextLine());

                        //Tarea tarea = new Tarea(new Date(1996, 11, 24),"Cumpleaños","Casa",3);
                        Tarea tarea = new Tarea(
                                new Date(anyo, mes, dia),
                                descripcion, categoria, prioridad);
                        db.set(tarea);
                        db.commit();
                    } finally {
                        if (db != null) {
                            db.close();
                        }
                    }
                    break;
                case "2":
                    db = Db4o.openFile("misDatos.dat");
                    System.out.print("Introduce la categoria a buscar: ");
                    ObjectSet busqueda = db.get(
                        new Tarea(null, null, teclado.nextLine(), 0));
                    while (busqueda.hasNext()) {
                        System.out.println(busqueda.next());
                    }
                    db.close();
                    break;
                case "0":

                    break;
            }

        } while (!"0".equals(respuesta));
    }
}
