/*
Crea un programa que permite gestionar una serie de tareas pendientes.

De cada tarea se guardará un código (autonumérico), una descripción 
(hasta 50 letras, pero se podrá cambiar desde el programa) y una fecha 
en la que la tarea debe estar terminada (sólo fecha, sin hora).

El menú principal mostrará las opciones:

1. Añadir una tarea. (Nota: la fecha se deberá introducir en formato 
DD/MM/AAAA, que tu deberás convertir a un formato aceptable para SQL).

2. Ver tareas previstas para hoy.

3. Buscar tareas (a partir de parte de su descripción o parte de ella).

4. Modificar una tarea (a partir de su código; se introducirá la nueva 
descripción y la nueva fecha).

5. Administración (que se explica más adelante).

0. Salir.

La opción de "Administración" llevará a un nuevo menú, que por ahora 
tendrá una única opción, "1. Cambiar tamaño de descripción", que 
permitirá hacer más grande o más pequeño el campo "descripcion" de la 
tabla. Si el usuario escoge un tamaño demasiado pequeño (que supusiera 
truncar los datos), se tomará en su lugar el tamaño más pequeño posible 
que no implique truncar los datos. Lógicamente, también existirá una 
opción que permita volver al menú principal.
*/


/*
create table tareas (
    cod serial primary key,
    descripcion varchar(50),
    fecha date
);
*/

package manejadordetareas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.Date;
import java.util.Scanner;

/**
 * @author chao
 */
public class ManejadorDeTareas {
    
    public void mostrarMenu() {
        System.out.println("1. Añadir tarea");
        System.out.println("2. Ver tareas para hoy");
        System.out.println("3. Buscar tareas");
        System.out.println("4. Modificar tareas");
        System.out.println("5. Administración");
        System.out.println("0. Salir");
        System.out.print("Elige una opción: ");
    }
    
    public void anyadirTarea(String url, String usuario, String password) {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.print("Descripción: ");
            String descripcion = scan.nextLine();
            System.out.print("Fecha (dd/mm/aaaa): ");
            String fecha = scan.nextLine();
            String[] partesFecha = fecha.split("/");
            fecha = partesFecha[2] + "/" + (
                partesFecha[1].length() == 2 ? partesFecha[1] : "0" + 
                partesFecha[1]) + "/" + (partesFecha[0].length() == 2 ?
                partesFecha[0] : "0" + partesFecha[0]);
            
            Connection con = DriverManager.getConnection(url, usuario, password);
            Statement statement = con.createStatement();
            
            String sql = "insert into tareas values (default, '" + descripcion +
                "', '" + fecha + "')";
            
            int filasAlmacenadas = statement.executeUpdate(sql);
            
            if (filasAlmacenadas > 0) {
                System.out.println("Tarea guardada correctamente");
            }
            else {
                System.out.println("Error al guardar la tarea");
            }
            
            con.close();
        }
        catch (SQLException e) {
            System.out.println("Error al añadir tarea");
        }
        catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
    
    public void verTareasParaHoy(String url, String usuario, String password) {
        try {
            Date fecha = Date.from(Instant.now());
            String fechaHoy = (fecha.getYear() + 1900) + "/" + 
                (fecha.getMonth() + 1) + "/" + fecha.getDate();
            
            Connection con = DriverManager.getConnection(url, usuario, password);
            Statement statement = con.createStatement();
            
            String sql = "select cod, descripcion, fecha from tareas where " +
                "fecha = '" + fechaHoy + "'";
            
            ResultSet rs = statement.executeQuery(sql);
            
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    System.out.println("Código: " + rs.getInt(1) + ". " + 
                        " Descripción: " + rs.getString(2));
                }
            }
            else {
                System.out.println("No hay tareas para hoy");
            }
            rs.close();
            con.close();
        }
        catch (SQLException e) {
            System.out.println("Error al consultar tareas");
        }
        catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
    
    public void buscarTareas(String url, String usuario, String password) {
        try {
            Scanner scan = new Scanner(System.in);
            
            System.out.print("Descripción: ");
            String descripcion = scan.nextLine().toLowerCase();
            
            Connection con = DriverManager.getConnection(url, usuario, password);
            Statement statement = con.createStatement();
            
            String sql = "select cod, descripcion, fecha from tareas where " +
                "lower(descripcion) like '%" + descripcion + "%'";
            ResultSet rs = statement.executeQuery(sql);
            
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    System.out.println("Código: " + rs.getInt(1) + ". " + 
                        " Descripción: " + rs.getString(2));
                }
            }
            else {
                System.out.println("No hay tareas con esa descripción");
            }
            
            rs.close();
            con.close();
        }
        catch (SQLException e) {
            System.out.println("Error al buscar tarea");
        }
        catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
    
    public void modificarTareas(String url, String usuario, String password) {
        try {
            boolean descripcionVacia = false;
            boolean fechaVacia = false;
            Scanner scan = new Scanner(System.in);
            System.out.print("Código: ");
            String codigo = scan.nextLine();
            System.out.print("Nueva descripción o intro para cancelar: ");
            String descripcion = scan.nextLine();
            System.out.print("Nueva fecha (dd/mm/aaaa) o intro para cancelar: ");
            String fecha = scan.nextLine();
            
            String sql = "update tareas set ";
            if (descripcion.equals("")) {
                descripcionVacia = true;
            }
            else {
                sql += "descripcion = '" + descripcion + "' ";
            }
            
            if (fecha.equals("")) {
                fechaVacia = true;
            }
            else {
                String[] partesFecha = fecha.split("/");
                fecha = partesFecha[2] + "/" + (
                    partesFecha[1].length() == 2 ? partesFecha[1] : "0" + 
                    partesFecha[1]) + "/" + (partesFecha[0].length() == 2 ?
                    partesFecha[0] : "0" + partesFecha[0]);
                
                sql += ", fecha = '" + fecha + "' ";
            }
            
            sql += "where cod = " + codigo;
            
            if (!descripcionVacia || !fechaVacia) {
                Connection con = DriverManager.getConnection(url, usuario, password);
                Statement statement = con.createStatement();
                
                int filasAfectadas = statement.executeUpdate(sql);
                if (filasAfectadas > 0) {
                    System.out.println("Tarea actualizada correctamente");
                }
                else {
                    System.out.println("Error al actualizar la tarea");
                }
                
                con.close();
            }
        }
        catch (SQLException e) {
            System.out.println("Error al modificar tarea");
        }
        catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
    
    public void administrar(String url, String usuario, String password) {
        try {
            Scanner scan = new Scanner(System.in);
            mostrarAdministracion();
            String opcion = scan.nextLine();
            
            if (opcion.equals("1")) {
                Connection con = DriverManager.getConnection(url, usuario, password);
                Statement statement = con.createStatement();
                
                int longitud = 0;
                String sql = "select max(length(descripcion)) from tareas";
                ResultSet rs = statement.executeQuery(sql);
                if (rs.isBeforeFirst()) {
                    rs.next();
                    longitud = rs.getInt(1);
                }
                rs.close();
                
				System.out.print("Tamaño de descripción (mínimo " + longitud +
                    "): ");
                int tamanyoDescripcion = Integer.parseInt(scan.nextLine());
                
                if (tamanyoDescripcion >= longitud) {
                    sql = "alter table tareas alter column descripcion type " +
                        "varchar(" + tamanyoDescripcion + ")";
                    
                    statement.executeUpdate(sql);
                    System.out.println("Tamaño de descripción actualizado");
                }
                else {
                    System.out.println("La nueva longitud no debe ser inferior" +
                        " a la mínima: " + longitud);
                }
                
                con.close();
            }
            else if (!opcion.equals("0")) {
                System.out.println("Opción no válida");
            }
        }
        catch (SQLException e) {
            System.out.println("Error al modificar parámetros");
        }
        catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
    
    public void mostrarAdministracion() {
        System.out.println();
        System.out.println("1. Cambiar tamaño descripción");
        System.out.println("0. Volver");
        System.out.print("Elige una opción: ");
    }
    
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/Agenda";
        String usuario = "postgres";
        String password = "chen1234";
        ManejadorDeTareas manejadorDeTareas = new ManejadorDeTareas();
        
        Scanner scan = new Scanner(System.in);
        String opcion;
        do {
            manejadorDeTareas.mostrarMenu();
            opcion = scan.nextLine();
            
            switch (opcion) {
                case "0":
                    System.out.println("Adiós");
                    break;
                case "1":
                    manejadorDeTareas.anyadirTarea(url, usuario, password);
                    break;
                case "2":
                    manejadorDeTareas.verTareasParaHoy(url, usuario, password);
                    break;
                case "3":
                    manejadorDeTareas.buscarTareas(url, usuario, password);
                    break;
                case "4":
                    manejadorDeTareas.modificarTareas(url, usuario, password);
                    break;
                case "5":
                    manejadorDeTareas.administrar(url, usuario, password);
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
        while (!opcion.equals("0"));
    }
    
}
