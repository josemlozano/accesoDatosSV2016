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

package dia0211;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author chao chen
 */
public class Usuarios {
    private static String url = "jdbc:postgresql://localhost:5432/dia0211";
    private static String usuario = "postgres";
    private static String password = "1234";
    
    public static int anadir(String nombre) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        int filasAlmacenadas = 0;
        try {
            Connection con = DriverManager.getConnection(url, usuario, password);
            Statement statement = con.createStatement();
            
            String sql = "insert into usuarios values (default, '" +
                nombre + "')";
            filasAlmacenadas = statement.executeUpdate(sql);
            
            con.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return filasAlmacenadas;
    }
    
    public static Integer obtenerCodigo(String nombre) 
            throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        int codigo = 0;
        try {
            Connection con = DriverManager.getConnection(url, usuario, password);
            Statement statement = con.createStatement();
            
            String sql = "select cod from usuarios where nombre = '" + nombre + 
                "'";
            ResultSet rs = statement.executeQuery(sql);
            
            if (rs.isBeforeFirst()) {
                rs.next();
                codigo = rs.getInt(1);
            }
            rs.close();
            con.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return codigo;
    }
    
    public static String obtenerNombre(int cod) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String nombre = null;
        try {
            Connection con = DriverManager.getConnection(url, usuario, password);
            Statement statement = con.createStatement();
            
            String sql = "select nombre from usuarios where cod = " + cod;
            ResultSet rs = statement.executeQuery(sql);
            
            if (rs.isBeforeFirst()) {
                rs.next();
                nombre = rs.getString(1);
            }
            rs.close();
            con.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return nombre;
    }
    
    public static ArrayList<Integer> obtenerCodigos(String busqueda) 
            throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        ArrayList<Integer> listaCodigos = null;
        try {
            listaCodigos = new ArrayList<>();
            Connection con = DriverManager.getConnection(url, usuario, password);
            Statement statement = con.createStatement();
            
            String sql = "select cod from usuarios where upper(nombre) like '%" +
                busqueda.toUpperCase() + "%' order by nombre";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                listaCodigos.add(rs.getInt(1));
            }
            rs.close();
            con.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return listaCodigos;
    }
}
