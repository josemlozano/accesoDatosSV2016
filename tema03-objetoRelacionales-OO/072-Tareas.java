package tareas;

import com.db4o.*;
import java.util.Date;
import java.util.Scanner;

public class Tareas {
    public static void main(String[] args) {
        
        Scanner teclado = new Scanner(System.in);
        boolean terminado = false;
        
        do {
            ObjectContainer db = null;
            System.out.println("Escoja una opción:");
            System.out.println("1. Añadir una tarea");
            System.out.println("2. Buscar tareas por categoría");
            System.out.println("3. Ver tareas para el dia de hoy");
            System.out.println("0. Salir");
            
            String respuesta = teclado.nextLine();
            
            switch (respuesta) {
                case "1":
                    
                    try {
                        db=Db4o.openFile("misDatos.dat");

                        System.out.print("Año? ");
                        int anyo = Integer.parseInt(teclado.nextLine())-1900;
                        System.out.print("Mes? ");
                        int mes = Integer.parseInt(teclado.nextLine())-1;
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
                        db.set(tarea);
                        db.commit();
                    }
                    finally {
                        if(db!=null) {
                            db.close();
                        }
                    }
                    break;

                case "2":
                    try {
                        db=Db4o.openFile("misDatos.dat");
                        
                        System.out.println("Dime la categoria");
                        String busqueda = teclado.nextLine();
                        
                        ObjectSet category = db.get(new Tarea(null,null,busqueda,0));
                        //Tarea tarea = new Tarea(new Date(1996, 11, 24),"Cumpleaños","Casa",3);
                        while (category.hasNext())
                            System.out.println(category.next().toString());
                    }
                    finally {
                        if(db!=null) {
                            db.close();
                        }
                    }
                    break;

                case "3":
                    try {
                        db=Db4o.openFile("misDatos.dat");
                        Date ahora = new Date();
                        Tarea patronBusqueda = new Tarea(new Date(
                                ahora.getYear(),
                                ahora.getMonth(),
                                ahora.getDate()),
                                null,null,0);
                        // Si es necesario comprobar:
                        // System.out.println(patronBusqueda);
                        ObjectSet tareasHoy = db.get(patronBusqueda);
                        while (tareasHoy.hasNext())
                            System.out.println(tareasHoy.next());
                    }
                    finally {
                        if(db!=null) {
                            db.close();
                        }
                    }
                    break;
                    
                case "0":
                    terminado = true;
                    break;
            }
        }
        while (! terminado);
    }
}
