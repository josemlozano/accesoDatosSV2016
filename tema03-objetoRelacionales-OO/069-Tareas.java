/*
Se desea crear un gestor de tareas. Para cada tarea habrá:

* Una descripción

* Una categoría (será una relación 1:M, con los nombres completos
  de las categorías en una segunda tabla).

* Una prioridad (1 a 5)

* Un atributo booleano que indique si está terminada o no.

Además, existirán "tareas con fecha límite", que tendrán un campo 
adicional "fecha".

Crea un programa Java que te permita anotar tareas, ver tareas 
pendientes,buscar tareas por contenido, marcar una tarea como terminada 
y modificar una tarea existente.

*/

/*
CREATE DATABASE dia1011;

CREATE DOMAIN dprioridad AS NUMERIC CHECK (VALUE > 0 and VALUE <= 5);

CREATE TABLE categorias (
    cod VARCHAR(5) PRIMARY KEY,
    descripcion VARCHAR(200)
);

CREATE TABLE tareas(
    cod SERIAL PRIMARY KEY,
    descripcion VARCHAR(600),
    cod_categodira VARCHAR(5) REFERENCES categorias (cod),
    prioridad dprioridad,
    terminado BOOLEAN
);

CREATE TABLE tareatemporal (
    fecha DATE
) INHERITS (tareas);

ALTER TABLE tareatemporal ADD PRIMARY KEY (cod); 
*/

package tareas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author bruno
 */
public class Tareas {

    static String url = "jdbc:postgresql://localhost:5432/dia1011";
    static String usuario = "postgres";
    static String password = "1234";
    static Scanner scan = new Scanner(System.in);
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        while(menu());
    }
    
    public static boolean menu() {
        String option;

        System.out.println("Menu");
        System.out.println("1. Añadir tarea");
        System.out.println("2. Ver tareas pendientes");
        System.out.println("3. Buscar tarea por contenido");
        System.out.println("4. Terminar tarea");
        System.out.println("5. Modificar tarea");
        System.out.println("0. Salir");
        System.out.print("Elige una opción: ");
        option = scan.nextLine();

        switch (option) {
            case "1":
                anadirTarea();
                break;
            case "2":
                verTareas();
                break;
            case "3":
                buscarTarea();
                break;
            case "4":
                terminarTarea();
                break;
            case "5":
                modificarTarea();
                break;
            case "0":
                return false;
            default:
                System.out.print("Opción incorrecta: ");
                break;
        }

        return true;
    }

    private static void anadirTarea() {
        
        try {

            Connection con = DriverManager.getConnection(url, usuario, password);

            String descripcion, cod_cat, fech, fecha;
            int prioridad;

            System.out.println("Añadir tarea -> ");
            
            System.out.println("Descripcion: ");
            descripcion = scan.nextLine();
            
            System.out.println("Codigo categoria: ");
            cod_cat = scan.nextLine();
            
            System.out.println("Prioridad(1-5): ");
            prioridad = Integer.parseInt(scan.nextLine());
            
            System.out.println("Fecha de finalizacion(puede ser nulo): ");
            fecha = scan.nextLine();
            
            String sql;
            
            if(fecha.isEmpty()) {
                sql = "insert into tareas "
                    + "values (default,'" + descripcion + "', '"+cod_cat+ 
                    "',  "+ prioridad +", false);";
            }else{
                DateFormat yearFormat = new SimpleDateFormat("yyyy/MM/dd");
                fech =  yearFormat.format(fecha);
                
                sql = "insert into tareatemporal "
                    + "values (default,'" + descripcion + "', '"+cod_cat+ 
                    "',  "+ prioridad +", false, '"+fech+"');";
            }
            
            Statement statement = con.createStatement();
            statement.executeUpdate(sql);
            con.close();

        } catch (SQLException e) {
            System.out.print(e.getLocalizedMessage());
        }
    }

    private static void verTareas() {

        try {

            Connection con = DriverManager.getConnection(url, usuario, password);
            System.out.println("Tareas pendientes -> ");
            
            String sql = "select * "
                    + "from tareas "
                    + "where terminado = false;";
            
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            
            while (rs.next()) {
                System.out.println(rs.getString(1) + " - " + rs.getString(2) +
                        " - " + rs.getString(3) + " - " + rs.getString(4) );
            }
            
            rs.close();
            con.close();
            
        } catch (SQLException e) {
            System.out.print(e.getLocalizedMessage());
        }
        
    }

    private static void buscarTarea() {
         try {

            Connection con = DriverManager.getConnection(url, usuario, password);
            System.out.println("Buscar tarea -> ");
            
            System.out.println("Descripcion: ");
            String descripcion = scan.nextLine();
            
            String sql = "select * "
                    + "from tareas "
                    + "where descripcion like '%"+descripcion+"%';";
            
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            
            while (rs.next()) {
                System.out.println(rs.getString(1) + " - " + rs.getString(2) +
                        " - " + rs.getString(3) + " - " + rs.getString(4) );
            }
            
            rs.close();
            con.close();
            
        } catch (SQLException e) {
            System.out.print(e.getLocalizedMessage());
        }
    }

    private static void terminarTarea() {
        
                 try {

            Connection con = DriverManager.getConnection(url, usuario, password);
            System.out.println("Terminar tarea -> ");
            
            System.out.println("Codigo: ");
            int cod = Integer.parseInt(scan.nextLine());
            
            String sql = "update tareas "
                    + "set terminado = true "
                    + "where cod = " + cod ;
            
            Statement statement = con.createStatement();
            statement.executeUpdate(sql);
            con.close();
            
        } catch (SQLException e) {
            System.out.print(e.getLocalizedMessage());
        }
        
    }

    private static void modificarTarea() {
    
        try {

            Connection con = DriverManager.getConnection(url, usuario, password);

            String descripcion, cod_cat, fech, fecha;
            int prioridad, cod;

            System.out.println("Modificar tarea -> ");
            
            System.out.println("Codigo: ");
            cod = Integer.parseInt(scan.nextLine());
            
            System.out.println("Descripcion: ");
            descripcion = scan.nextLine();
            
            System.out.println("Codigo categoria: ");
            cod_cat = scan.nextLine();
            
            System.out.println("Prioridad(1-5): ");
            prioridad = Integer.parseInt(scan.nextLine());
            
            /*
            System.out.println("Fecha de finalizacion(puede ser nulo): ");
            fecha = scan.nextLine();
            */

            String sql;
            
            sql = "update tareas \n" +
                "set descripcion = '"+descripcion+"',\n" +
                "   cod_categoria = '"+cod_cat+"',\n" +
                "   prioridad = +"+prioridad+"+\n" +
                "where cod = "+cod+";";
            
            Statement statement = con.createStatement();
            statement.executeUpdate(sql);
            con.close();

        } catch (SQLException e) {
            System.out.print(e.getLocalizedMessage());
        }
    
    }
}
