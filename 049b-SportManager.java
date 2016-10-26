package sportmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * @author chao chen
 */
public class SportManager {
    
    public void showMenu() {
        System.out.println("1. Añadir deporte");
        System.out.println("2. Añadir deportistas");
        System.out.println("3. Buscar por nombre de deportista");
        System.out.println("0. Salir");
        System.out.print("Elige una opción: ");
    }
    
    public void anyadirDeporte(String url, String usuario, String password) {
        try {
            Scanner scan = new Scanner(System.in);
            
            System.out.print("Código deporte: ");
            String cod = scan.nextLine();
            System.out.print("Nombre deporte: ");
            String nombre = scan.nextLine();
            
            Connection con = DriverManager.getConnection(url, usuario, password);
            String sql = "insert into deportes (cod, nombre) values ('" + cod +
                "', '" + nombre + "')";
            Statement statement = con.createStatement();
            int filasAlmacenadas = statement.executeUpdate(sql);
            con.close();
            
            if (filasAlmacenadas > 0) {
                System.out.println("Datos guardados correctamente.");
            }
            else {
                System.out.println("Error al guardar los datos");
            }
        }
        catch (SQLException e) {
            System.out.println("Error en el guardado de datos");
        }
        catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
    
    public void anyadirDeportista(String url, String usuario, String password) {
        try {
            Scanner scan = new Scanner(System.in);
            
            System.out.print("Código deportista: ");
            String cod = scan.nextLine();
            System.out.print("Nombre deportista: ");
            String nombre = scan.nextLine();
            System.out.print("Código deporte: ");
            String codDeport = scan.nextLine();
            
            Connection con = DriverManager.getConnection(url, usuario, password);
            
            String sql = "insert into deportistas (cod, nombre, codDeporte)" +
                " values ('" + cod + "', '" + nombre + "', ";
            
            if (codDeport.equals("")) {
                sql += "null)";
            }
            else {
                sql += ("'" + codDeport + "')");
            }
            
            Statement statement = con.createStatement();
            int filasAlmacenadas = statement.executeUpdate(sql);
            con.close();
            
            if (filasAlmacenadas > 0) {
                System.out.println("Datos guardados correctamente.");
            }
            else {
                System.out.println("Error al guardar los datos");
            }
        }
        catch (SQLException e) {
            System.out.println("Error en el guardado de datos");
        }
        catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
    
    public void buscarPorNombreDeportista(String url, String usuario,
            String password) {
        try {
            Scanner scan = new Scanner(System.in);
            
            System.out.print("Nombre deportista: ");
            String nombre = scan.nextLine().toUpperCase();
            Connection con = DriverManager.getConnection(url, usuario, password);
            String sql = "select dep.nombre, dep.cod, d.nombre from deportistas " +
                "dep left join deportes d on (dep.coddeporte = d.cod) where " +
                "upper(dep.nombre) like '%" + nombre + "%'";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    System.out.println("Nombre: " + rs.getString(1) + " Código: " +
                        rs.getString(2) + " Deporte: " + rs.getString(3));
                }
            }
            else {
                System.out.println("No existe deportistas con nombre parecido" +
                    " al introducido");
            }
            rs.close();
            con.close();
        }
        catch (SQLException e) {
            System.out.println("Error en la consulta de datos");
        }
        catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/dia25";
        String usuario = "postgres";
        String password = "1234";
        
        Scanner scan = new Scanner(System.in);
        String option;
        SportManager sportManager = new SportManager();
        
        do {
            sportManager.showMenu();
            option = scan.nextLine();
            switch (option) {
                case "0":
                    System.out.println("Adiós");
                    break;
                case "1":
                    sportManager.anyadirDeporte(url, usuario, password);
                    break;
                case "2":
                    sportManager.anyadirDeportista(url, usuario, password);
                    break;
                case "3":
                    sportManager.buscarPorNombreDeportista(url, usuario, 
                        password);
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
        while (!option.equals("0"));
    }
    
}
