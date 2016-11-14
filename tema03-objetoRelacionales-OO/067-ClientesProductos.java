/*
Crea un programa Java que te permita:

1. Añadir un cliente
2. Buscar clientes
3. Añadir un producto
4. Buscar productos
0. Salir

(Debes tener presente detalles como que los clientes pueden ser 
mayoristas o no, de modo que el comportamiento sea correcto)
*/

package clientesproductos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Ruben Blanco
 */
public class ClientesProductos {

    public static void anadirCliente() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost:5432/tema3a";
            String usuario = "postgres";
            String password = "1234";

            Connection con = DriverManager.getConnection(url, usuario, password);
            Statement statement = con.createStatement();
            
            Scanner teclado = new Scanner(System.in);
            
            System.out.println("Codigo (nnnn): ");
            String cod = teclado.nextLine();
            
            System.out.println("Nombre: ");
            String nombre = teclado.nextLine();
            
            System.out.println("Descuento: ");
            double descuento = Double.parseDouble(teclado.nextLine());
            
            System.out.println("Direccion - Calle: ");
            String direccion1 = teclado.nextLine();
            
            System.out.println("Direccion - Ciudad: ");
            String direccion2 = teclado.nextLine();
            
            System.out.println("Direccion - Provincia: ");
            String direccion3 = teclado.nextLine();
            
            System.out.println("Direccion - CP: ");
            String direccion4 = teclado.nextLine();
            
            System.out.println("Es cliente mayorista? (si / no)");
            String mayorista = teclado.nextLine();
            boolean esMayorista = false;
            
            if (mayorista.equals("") || mayorista.equals("no") 
                    || mayorista.equals("NO")) {
                String sentenciaSQL = "INSERT INTO clientes VALUES "
                    + "('" + cod + "','" + nombre + "'," + descuento 
                    + ",'{\"" + direccion1 + "\",\"" + direccion2 
                    + "\",\"" + direccion3 
                    + "\",\"" + direccion4 + "\"}');";
                
                int cantidad = statement.executeUpdate(sentenciaSQL);

                if (cantidad == 1) {
                    System.out.println("Cliente añadido correctamente");
                } else {
                    System.out.println("Error: No se ha podido añadir!");
                }
                System.out.println();
                con.close();
            }
            
            else {
                
                System.out.println("Volumen para descuento mayorista: ");
                String volumen = teclado.nextLine();
            
                System.out.println("Descuento por mayorista: ");
                double descMayorista = Double.parseDouble(teclado.nextLine());
                
                String sentenciaSQL = "INSERT INTO clientesMayoristas VALUES "
                    + "('" + cod + "','" + nombre + "'," + descuento 
                    + ",'{\"" + direccion1 + "\",\"" + direccion2 + "\",\"" 
                    + direccion3 
                    + "\",\"" + direccion4 + "\"}'," + volumen + ","
                    + descMayorista + ");";

                int cantidad = statement.executeUpdate(sentenciaSQL);

                if (cantidad == 1) {
                    System.out.println("Cliente Mayorista añadido correctamente");
                } else {
                    System.out.println("Error: No se ha podido añadir!");
                }
                System.out.println();
                con.close();
            }
        } 
        
        catch (SQLException e) {
            System.out.println("Error en la consulta de datos");
        } 
        
        catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    public static void VerMenu() {
        System.out.println();
        System.out.println("1- Añadir clientes:");
        System.out.println("2- Buscar clientes:");
        System.out.println("3- Añadir productos:");
        System.out.println("4- Buscar productos:");
        System.out.println("0- Salir:");
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        int opcion;
        Scanner teclado = new Scanner(System.in);

        do {
            VerMenu();
            
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    anadirCliente();
                    break;

                case 2:
                    //TODO: buscarCliente();   
                    break;
                    
                case 3:
                    //TODO: anadirProducto(); 
                    break;

                case 4:
                    //TODO: BuscarProducto(); 
                    break;
                default:
                    break;
            }
        } while (opcion != 0);
        System.out.println("Hasta luego!");
    }
}
