package gestorcamaras;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Scanner;

/**
 * @author chao chen
 */
public class GestorCamaras {
    
    public void anyadirCamaras(String url, String usuario, String password) {
        try {
            Scanner scan = new Scanner(System.in);
            Connection con = DriverManager.getConnection(url, usuario, password);
            Statement statement = con.createStatement();
            
            System.out.print("Marca: ");
            String marca = scan.nextLine();
            System.out.print("Modelo: ");
            String modelo = scan.nextLine();
            System.out.print("Resolución: ");
            float resolucion = Float.parseFloat(scan.nextLine());
            
            String sql = "insert into camaras values (default, '" + marca +
                "', '" + modelo + "', " + resolucion + ");";
            
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
            System.out.println("Error en la inserción de datos");
        }
        catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
    
    public void buscarCamaras(String url, String usuario, String password) {
        try {
            Scanner scan = new Scanner(System.in);
            Connection con = DriverManager.getConnection(url, usuario, password);
            Statement statement = con.createStatement();

            System.out.print("Texto a buscar: ");
            String texto = scan.nextLine().toLowerCase();
            
            String sql = "select marca, modelo, resolucion from camaras where " +
                "lower(marca) like '%" + texto + "%' or lower(modelo) like '%" +
                texto + "%';";
            ResultSet result = statement.executeQuery(sql);
            
            if (result.isBeforeFirst()) {
                System.out.printf("%-10s%-15s%-5s\n", "Marca", "Modelo", "Res.");
                while (result.next()) {
                    System.out.printf("%-10s%-15s%-5s\n", result.getString(1), 
                        result.getString(2), result.getString(3));
                }
            }
            else {
                System.out.println("No hay camaras con esa información.");
            }
            
            result.close();
            con.close();
        }
        catch (SQLException e) {
            System.out.println("Error en la consulta de datos");
        }
        catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
    
    public void mostrarCantidadCamaras(String url, String usuario,
            String password) {
        try {
            Connection con = DriverManager.getConnection(url, usuario, password);
            
            /*Statement statemente = con.createStatement();
            
            String sql = "select CantidadCamaras();";
            ResultSet rs = statemente.executeQuery(sql);
            rs.next();
            System.out.println("Cantidad de cámaras: " + rs.getInt(1));
            rs.close();
            */
            
            CallableStatement cStatement = con.prepareCall(
                "{? = call cantidadcamaras()}");
            cStatement.registerOutParameter(1, Types.INTEGER);
            cStatement.execute();
            
            System.out.println("Cantidad de cámaras: " + cStatement.getInt(1));
            
            con.close();
        }
        catch (SQLException e) {
            System.out.println("Error en la consulta de datos");
        }
        catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
    
    public void mostrarCantidadCamarasMP(String url, String usuario, 
            String password) {
        try {
            Scanner scan = new Scanner(System.in);
            
            System.out.print("Resolución mínima: ");
            float min = Float.parseFloat(scan.nextLine());
            System.out.print("resolución máxima: ");
            float max = Float.parseFloat(scan.nextLine());
            
            Connection con = DriverManager.getConnection(url, usuario, password);
            CallableStatement cStatement = con.prepareCall(
                "{ call CantidadCamarasMP(?, ?) }");
            cStatement.registerOutParameter(1, Types.INTEGER);
            cStatement.setFloat(1, min);
            cStatement.setFloat(2, max);
            
            cStatement.execute();
            System.out.println("Cantidad de cámaras entre las resoluciones " +
                "introducidas: " + cStatement.getInt(1));
            con.close();
        }
        catch (SQLException e) {
            System.out.println("Error en la consulta de datos");
        }
        catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
    
    public void mostrarMenu() {
        System.out.println("1. Añadir cámara");
        System.out.println("2. Buscar");
        System.out.println("3. Mostrar cantidad de cámaras");
        System.out.println("4. Mostrar cantidad de cámaras según resolución");
        System.out.println("0. Salir");
        System.out.print("Elija una opción: ");
    }
    
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/dia20";
        String usuario = "postgres";
        String password = "chen1234";
        GestorCamaras gestorDeCamaras = new GestorCamaras();
        
        Scanner scan = new Scanner(System.in);
        String opcion;
        do {
            gestorDeCamaras.mostrarMenu();
            opcion = scan.nextLine();
            switch (opcion) {
                case "0":
                    System.out.println("Adiós");
                    break;
                case "1":
                    gestorDeCamaras.anyadirCamaras(url, usuario, password);
                    break;
                case "2":
                    gestorDeCamaras.buscarCamaras(url, usuario, password);
                    break;
                case "3":
                    gestorDeCamaras.mostrarCantidadCamaras(
                        url, usuario, password);
                    break;
                case "4":
                    gestorDeCamaras.mostrarCantidadCamarasMP(
                        url, usuario, password);
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        while (!opcion.equals("0"));
    }
    
}
