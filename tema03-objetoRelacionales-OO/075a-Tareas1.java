package tareas;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * @author chao
 */
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
            System.out.println("4. Modificar tarea");
            System.out.println("5. Buscar por descripción (parcial)");
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
                        // Para evitar duplicados
                        if (!db.get(tarea).hasNext()) 
                        {
                            db.set(tarea);
                            db.commit();
                            System.out.println("Tarea guardada");
                        }
                        else
                            System.out.println("Tarea duplicada!");
                        
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
                    
                case "4":
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
                        
                        ObjectSet tareas = db.get(new Tarea(new Date(anyo, mes,
                            dia), descripcion, null, 0));
                        while(tareas.hasNext()) {
                            Tarea tarea = (Tarea) tareas.next();
                            Date fechaActual = tarea.getFecha();
                            System.out.println("Tarea actual: " + tarea.toString());
                            System.out.println("Modificar? (S / N)");
                            String opcion = teclado.nextLine().toUpperCase();
                            if (opcion.equals("S")) {
                                System.out.println("Fecha actual (" + fechaActual + "): ");
                                System.out.print("Año: ");
                                String anyoTexto = teclado.nextLine();
                                if (!anyoTexto.equals("")) {
                                    fechaActual.setYear(Integer.parseInt(anyoTexto) - 1900);
                                }
                                System.out.print("Mes: ");
                                String mesTexto = teclado.nextLine();
                                if (!mesTexto.equals("")) {
                                    fechaActual.setMonth(Integer.parseInt(mesTexto) - 1);
                                }
                                System.out.print("Día: ");
                                String diaTexto = teclado.nextLine();
                                if (!diaTexto.equals("")) {
                                    fechaActual.setDate(Integer.parseInt(diaTexto));
                                }
                                tarea.setFecha(fechaActual);
                                System.out.println("Descripción actual (" + tarea.getDescripcion() + "): ");
                                System.out.print("Descripción: ");
                                String nuevaDescripcion = teclado.nextLine();
                                if (!nuevaDescripcion.equals("")) {
                                    tarea.setDescripcion(nuevaDescripcion);
                                }
                                System.out.println("Categoría actual (" + tarea.getCategoria()+ "): ");
                                String nuevaCategoria = teclado.nextLine();
                                if (!nuevaCategoria.equals("")) {
                                    tarea.setCategoria(nuevaCategoria);
                                }
                                System.out.println("Prioridad actual (" + tarea.getPrioridad() + "): ");
                                String nuevaPrioridad = teclado.nextLine();
                                if (!nuevaPrioridad.equals("")) {
                                    tarea.setPrioridad(Integer.parseInt(nuevaPrioridad));
                                }
                                
                                db.set(tarea);
                                db.commit();
                            }
                        }
                    }
                    finally {
                        if (db != null) {
                            db.close();
                        }
                    }
                    break;
                    
                case "5":
                    try {
                        db=Db4o.openFile("misDatos.dat");
                        
                        System.out.print("Dime la descripción: ");
                        String busqueda = teclado.nextLine().toLowerCase();
                        
                        List<Tarea> listaTareas = db.query(new Predicate<Tarea>() {
                            @Override
                            public boolean match(Tarea tarea) {
                                return tarea.getDescripcion().toLowerCase().
                                    contains(busqueda);
                            }
                        });
                        if (listaTareas.size() > 0) {
                            listaTareas.forEach(System.out::println);
                        }
                        else {
                            System.out.println("No hay tareas con esa descripción");
                        }
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
