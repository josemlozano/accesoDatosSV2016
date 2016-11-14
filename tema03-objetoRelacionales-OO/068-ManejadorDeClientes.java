/*
Completa el programa Java que te permita:

1. Añadir un cliente
2. Buscar clientes
3. Añadir un producto
4. Buscar productos
0. Salir

(Debes tener presente detalles como que los clientes pueden ser 
mayoristas o no, de modo que el comportamiento sea correcto)
*/

package manejadordeclientes;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * @author chao
 */
public class ManejadorDeClientes {

    public void anyadirClientes(String url, String usuario, String password) {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.print("Código: ");
            String codigo = scan.nextLine();
            System.out.print("Nombre: ");
            String nombre = scan.nextLine();
            System.out.print("Descuento: ");
            float descuento = Float.parseFloat(scan.nextLine());
            System.out.print("Dirección (línea 1 de 2): ");
            String direccion1 = scan.nextLine();
            System.out.print("Dirección (línea 2 de 2): ");
            String direccion2 = scan.nextLine();
            System.out.print("Es mayorista (S / N): ");
            String esMayorista = scan.nextLine();
            String sql = "insert into ";
            Date fecha = Date.valueOf(LocalDate.now());
            
            if (esMayorista.toUpperCase().equals("S")) {
                System.out.print("Volumen para descuento: ");
                int volumen = Integer.parseInt(scan.nextLine());
                System.out.print("Descuento adicional: ");
                float descuentoAdicional = Float.parseFloat(scan.nextLine());
                
                sql += "clientesmayoristas (codigo, nombre, descuento, " + 
                    "fechaalta, direccion, volumenparadescuento, " + 
                    "descuentoadicional) values ('" + codigo + "', '" + nombre +
                    "', " + descuento + ", (" + (fecha.getMonth() + 1) + "," +
                    (fecha.getYear() + 1900) + "), '{\"" + direccion1 + "\",\"" +
                    direccion2 + "\"}', " + volumen + ", " + descuentoAdicional +
                    ")";
            }
            else {
                sql += "clientes (codigo, nombre, descuento, fechaalta, " +
                    "direccion) values ('" + codigo + "', '" + nombre + "', " + 
                    descuento + ", (" + (fecha.getMonth() + 1) + "," +
                    (fecha.getYear() + 1900) + "), '{\"" + direccion1 + "\",\"" +
                    direccion2 + "\"}')";
            }
            
            Connection con = DriverManager.getConnection(url, usuario, password);
            Statement statement = con.createStatement();
            
            int filasAlmacenadas = statement.executeUpdate(sql);
            
            if (filasAlmacenadas > 0) {
                System.out.println("Cliente guardado correctamente");
            }
            else {
                System.out.println("Error al guardar el cliente");
            }
            con.close();
        }
        catch (SQLException e) {
            System.out.println("Error al añadir cliente");
        }
        catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
    
    public void buscarClientes(String url, String usuario, String password) {
        try {
            Scanner scan = new Scanner(System.in);
            
            System.out.print("Nombre: ");
            String nombre = scan.nextLine().toLowerCase();
            
            Connection con = DriverManager.getConnection(url, usuario, password);
            Statement statement = con.createStatement();
            
            String sql = "select codigo, nombre from clientes where " +
                "lower(nombre) like '%" + nombre + "%'";
            ResultSet rs = statement.executeQuery(sql);
            
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    System.out.println("Código: " + rs.getString(1) + ". " + 
                        " Nombre: " + rs.getString(2));
                }
            }
            else {
                System.out.println("No hay clientes con esa descripción");
            }
            
            rs.close();
            con.close();
        }
        catch (SQLException e) {
            System.out.println("Error al buscar cliente");
        }
        catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
    
    public void anyadirProducto(String url, String usuario, String password) {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.print("Referencia: ");
            String referencia = scan.nextLine();
            System.out.print("Nombre: ");
            String nombre = scan.nextLine();
            System.out.print("Stock mínimo: ");
            int stock = Integer.parseInt(scan.nextLine());
            System.out.print("Activo (Y / N): ");
            String activo = scan.nextLine().toLowerCase();
            System.out.print("Comentarios: ");
            String comentarios = scan.nextLine();
            System.out.print("Fabricación (Fabricado / Distribuido): ");
            String fabricacion = scan.nextLine();
            Date fecha = Date.valueOf(LocalDate.now());
            
            String sql = "insert into productos (codigo, referencia, nombre, " +
                "stockminimo, fechaalta, activo, comentarios, fabricacion) " +
                "values (default, '" + referencia + "', '" + nombre + "', " +
                stock + ", '" + (fecha.getYear() + 1900) + "-" + 
                (fecha.getMonth() + 1) + "-" + fecha.getDate() + "', '" + 
                activo + "', '" + comentarios + "', '" + fabricacion + "')";
            
            Connection con = DriverManager.getConnection(url, usuario, password);
            Statement statement = con.createStatement();
            
            int filasAlmacenadas = statement.executeUpdate(sql);
            
            if (filasAlmacenadas > 0) {
                System.out.println("Producto guardado correctamente");
            }
            else {
                System.out.println("Error al guardar el producto");
            }
            con.close();
        }
        catch (SQLException e) {
            System.out.println("Error al añadir propducto");
        }
        catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
    
    public void buscarProductos(String url, String usuario, String password) {
        try {
            Scanner scan = new Scanner(System.in);
            
            System.out.print("Nombre: ");
            String nombre = scan.nextLine().toLowerCase();
            
            Connection con = DriverManager.getConnection(url, usuario, password);
            Statement statement = con.createStatement();
            
            String sql = "select codigo, nombre, fabricacion from productos where " +
                "lower(nombre) like '%" + nombre + "%'";
            ResultSet rs = statement.executeQuery(sql);
            
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    System.out.println("Código: " + rs.getString(1) + ". " + 
                        " Nombre: " + rs.getString(2) + ". Fabricación: " +
                        rs.getString(3));
                }
            }
            else {
                System.out.println("No hay productos con esa descripción");
            }
            
            rs.close();
            con.close();
        }
        catch (SQLException e) {
            System.out.println("Error al buscar productos");
        }
        catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
    
    public void mostrarMenu() {
        System.out.println("1. Añadir clientes");
        System.out.println("2. Buscar clientes");
        System.out.println("3. Añadir productos");
        System.out.println("4. Buscar productos");
        System.out.println("0. Salir");
        System.out.print("Elige una opción: ");
    }
    
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/tema3a";
        String usuario = "postgres";
        String password = "1234";
        
        Scanner scan = new Scanner(System.in);
        String option;
        ManejadorDeClientes manejador = new ManejadorDeClientes();
        
        do {
            manejador.mostrarMenu();
            option = scan.nextLine();
            
            switch (option) {
                case "0":
                    System.out.println("Adiós");
                    break;
                case "1":
                    manejador.anyadirClientes(url, usuario, password);
                    break;
                case "2":
                    manejador.buscarClientes(url, usuario, password);
                    break;
                case "3":
                    manejador.anyadirProducto(url, usuario, password);
                    break;
                case "4":
                    manejador.buscarProductos(url, usuario, password);
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
        while (!option.equals("0"));
    }
}
