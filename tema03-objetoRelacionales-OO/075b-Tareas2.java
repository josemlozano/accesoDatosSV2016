package tareas;

import com.db4o.*;
import com.db4o.query.Predicate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Bruno Del Greco
 */
public class Tareas {

    static ObjectContainer db = null;
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {

        boolean terminado = false;

        do {
            System.out.println("Escoja una opción:");
            System.out.println("1. Añadir una tarea");
            System.out.println("2. Buscar tareas por categoría");
            System.out.println("3. Ver tareas para el dia de hoy");
            System.out.println("4. Modificar una tarea");
            System.out.println("5. Buscar tareas por descripción");
            System.out.println("0. Salir");

            String respuesta = teclado.nextLine();

            switch (respuesta) {
                case "1":
                    anadirTarea();
                    break;
                case "2":
                    buscarTarea();
                    break;
                case "3":
                    TareasHoy();
                    break;
                case "4":
                    ModificarTarea();
                    break;    
                case "5":
                    BuscarPorTexto();
                    break; 
                case "0":
                    terminado = true;
                    break;
            }
        } while (!terminado);
    }

    private static void anadirTarea() {

        try {
            db = Db4o.openFile("misDatos.dat");

            System.out.print("Año? ");
            int anyo = Integer.parseInt(teclado.nextLine()) - 1900;
            System.out.print("Mes? ");
            int mes = Integer.parseInt(teclado.nextLine()) - 1;
            System.out.print("Día? ");
            int dia = Integer.parseInt(teclado.nextLine());
            System.out.print("Descripción? ");
            String descripcion = teclado.nextLine();
            System.out.print("Categoría? ");
            String categoria = teclado.nextLine();
            System.out.print("Prioridad (1 a 5)? ");
            int prioridad = Integer.parseInt(teclado.nextLine());

            // Ejemplo con datos prefijados:
            // Tarea tarea = new Tarea(new Date(1996, 11, 24),
            //     "Cumpleaños","Casa",3);
            Tarea tarea = new Tarea(
                    new Date(anyo, mes, dia),
                    descripcion, categoria, prioridad);
            // Para evitar duplicados
            if (db.get(tarea).hasNext() == false) {
                db.set(tarea);
                db.commit();
                System.out.println("Tarea guardada");
            } else {
                System.out.println("Tarea duplicada!");
            }

        } finally {
            if (db != null) {
                db.close();
            }
        }

    }

    private static void buscarTarea() {

        try {
            db = Db4o.openFile("misDatos.dat");

            System.out.println("Dime la categoria");
            String busqueda = teclado.nextLine();

            ObjectSet category = db.get(new Tarea(null, null, busqueda, 0));
            //Tarea tarea = new Tarea(new Date(1996, 11, 24),"Cumpleaños","Casa",3);
            while (category.hasNext()) {
                System.out.println(category.next().toString());
            }
        } finally {
            if (db != null) {
                db.close();
            }
        }

    }

    private static void TareasHoy() {

        try {
            db = Db4o.openFile("misDatos.dat");
            Date ahora = new Date();
            Tarea patronBusqueda = new Tarea(new Date(
                    ahora.getYear(),
                    ahora.getMonth(),
                    ahora.getDate()),
                    null, null, 0);
            // Si es necesario comprobar:
            // System.out.println(patronBusqueda);
            ObjectSet tareasHoy = db.get(patronBusqueda);
            while (tareasHoy.hasNext()) {
                System.out.println(tareasHoy.next());
            }
        } finally {
            if (db != null) {
                db.close();
            }
        }

    }

    private static void ModificarTarea() {
        
         try {
            db = Db4o.openFile("misDatos.dat");

            System.out.println("Dime la fecha(dd/MM/yyyy)");
            String fecha = teclado.nextLine();

            System.out.println("Dime la descripción");
            String descr = teclado.nextLine();
            
            String[] split = fecha.split("/");
            Date date = new Date(Integer.parseInt(split[2])-1900,
                    Integer.parseInt(split[1])-1,
                    Integer.parseInt(split[0]));
            
            ObjectSet modif = db.get(new Tarea(date, descr, null, 0));
            //Tarea tarea = new Tarea(new Date(1996, 11, 24),"Cumpleaños","Casa",3);
            
            while (modif.hasNext()) {
                System.out.println(modif.next().toString());
            }
            Tarea tarea = null;
            if(modif.hasNext()) {
                tarea = (Tarea) modif.next();
            }
            
            
            // Mostrar y modificar:
            Date fech = tarea.getFecha();
            System.out.println("Año? " + (fech.getYear() + 1900));
            int anyo = Integer.parseInt(teclado.nextLine()) - 1900;
            System.out.println("Mes? " + (fech.getMonth()+1));
            int mes = Integer.parseInt(teclado.nextLine()) - 1;
            System.out.println("Día? " + fech.getDate());
            int dia = Integer.parseInt(teclado.nextLine());
            System.out.println("Descripción? " + tarea.getDescripcion());
            String descripcion = teclado.nextLine();
            System.out.println("Categoría? " + tarea.getCategoria());
            String categoria = teclado.nextLine();
            System.out.println("Prioridad (1 a 5)? " + tarea.getPrioridad());
            int prioridad = Integer.parseInt(teclado.nextLine());
            
            anyo = anyo == 0 ? fech.getYear() : anyo;
            mes = mes == 0 ? fech.getMonth() : mes;
            dia = dia == 0 ? fech.getDate() : dia;
            descripcion = descripcion.equals(null)? descripcion: tarea.getDescripcion();
            categoria = categoria.equals(null)? categoria: tarea.getCategoria();
            prioridad = prioridad == 0 ? prioridad: tarea.getPrioridad();
            
            tarea.setCategoria(categoria);
            tarea.setDescripcion(descripcion);
            tarea.setPrioridad(prioridad);
            tarea.setFecha(new Date(anyo, mes, dia));
            
            
            db.set(tarea);
            
            
        } finally {
            if (db != null) {
                db.close();
            }
        }
        
    }

    private static void BuscarPorTexto() {
        try {
            db = Db4o.openFile("misDatos.dat");

            System.out.println("Descripción a buscar: ");
            String texto = teclado.nextLine();
            
            List<Tarea> tareas = db.query(new Predicate<Tarea>(){
                public boolean match(Tarea posible){
                    return posible.getDescripcion().contains(texto);
                }
            });
        
            tareas.forEach(System.out::println);
            
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }
    
    

}
