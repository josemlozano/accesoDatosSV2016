/*
Crea un proyecto Java para conectar a la base de datos "dia0211".

Crea una clase "usuarios", que será la que realmente acceda a la 
tabla "usuarios". Tendrá 3 métodos:

* Un método "anadir(nombre)", que añadirá a la tabla una persona con 
  ese nombre.

* Un método "obtenerCodigo(nombre)", que devolverá el código de la 
  persona que tenga exactamente ese nombre.

* Un método "obtenerNombre(cod)", que devolverá el nobre de la persona 
  que tenga ese codigo.

* Un método "obtenerCodigos(busqueda)", que devolverá un ArrayList de 
  enteros, cuyos contenidos serán los códigos de las personas cuyo 
  nombre contiene ese texto de búsqueda, ya sea en mayúsculas o 
  minúsculas. Los datos se deberán ordenar alfabéticamente por nombre.
*/

package quejasusuarios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Ruben Blanco
 */
public class Usuarios {

    public void anadir(String nombre) throws ClassNotFoundException, SQLException {
        try {
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost:5432/dia0211";
            String usuario = "postgres";
            String password = "1234";

            Connection con = DriverManager.getConnection(url, usuario, password);
            Statement statement = con.createStatement();

            String sentenciaSQL = "INSERT INTO usuarios VALUES "
                    + "(default,'" + nombre + "');";

            int cantidad = statement.executeUpdate(sentenciaSQL);

            if (cantidad == 1) {
                System.out.println("Usuario añadido correctamente");
            } else {
                System.out.println("Error: No se ha podido añadir!");
            }
            System.out.println();
            con.close();
        } 
        
        catch (SQLException e) {
            System.out.println("Error en la consulta de datos");
        } 
        
        catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    public int obtenerCodigo(String nombre) throws ClassNotFoundException, SQLException {
        int codigo = 0;
        
        try {
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost:5432/dia0211";
            String usuario = "postgres";
            String password = "1234";
            Connection con2 = DriverManager.getConnection(url, usuario, password);
            Statement statement2 = con2.createStatement();

            String buscar = nombre.toUpperCase();

            String selectSQL = "SELECT cod FROM usuarios "
                    + "WHERE UPPER(usuarios.nombre) = '" + buscar + "';";

            ResultSet rs = statement2.executeQuery(selectSQL);
            
            if (rs.next()) {
                    codigo = rs.getInt(1);
                    rs.close();
                    con2.close();
                    return codigo;
            } else {
                System.out.println("No existe usuarios con ese nombre");
            }
            rs.close();
            con2.close();
            
        } 
        
        catch (SQLException e) {
            System.out.println("Error en la consulta de datos");
        } 
        
        catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
        
        return codigo;
    }

    public String obtenerNombre(int cod) throws ClassNotFoundException, SQLException {
        String resultado = "";
        
        try {
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost:5432/dia0211";
            String usuario = "postgres";
            String password = "1234";
            Connection con2 = DriverManager.getConnection(url, usuario, password);
            Statement statement2 = con2.createStatement();

            String selectSQL = "SELECT nombre FROM usuarios "
                    + "WHERE usuarios.cod = " + cod + ";";

            ResultSet rs = statement2.executeQuery(selectSQL);
            

            if (rs.next()) {
                    resultado = rs.getString(1);
                    rs.close();
                    con2.close();
                    return resultado;
            } else {
                System.out.println("No existen usuarios con ese codigo");
            }

            rs.close();
            con2.close();
            
        } 
        
        catch (SQLException e) {
            System.out.println("Error en la consulta de datos");
        } 
        
        catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
        
        return resultado;
    }

    public ArrayList<Integer> obtenerCodigos(String busqueda) throws ClassNotFoundException, SQLException {
        ArrayList<Integer> codList = new ArrayList<>();
        
        try {
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost:5432/dia0211";
            String usuario = "postgres";
            String password = "1234";
            Connection con2 = DriverManager.getConnection(url, usuario, password);
            Statement statement2 = con2.createStatement();

            String buscar = busqueda.toUpperCase();

            String selectSQL = "SELECT cod FROM usuarios "
                    + "WHERE UPPER(usuarios.nombre) LIKE '%" + buscar + "%' "
                    + "ORDER BY usuarios.nombre ;";

            ResultSet rs = statement2.executeQuery(selectSQL);

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    codList.add(rs.getInt(1));
                }
                rs.close();
                con2.close();
                return codList;
            } else {
                System.out.println("No existen usuarios que contengan ese texto");
            }

            rs.close();
            con2.close();
            
        } 
        
        catch (SQLException e) {
            System.out.println("Error en la consulta de datos");
        } 
        
        catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
        
        return codList;
    }
}
