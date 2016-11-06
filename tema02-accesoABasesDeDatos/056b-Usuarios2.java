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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Yahir
 */
public class usuarios {
    
    String url, usuario, password;
    
    public usuarios () throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        this.url = "jdbc:postgresql://localhost:5432/dia0211";
        this.usuario = "dia0211";
        this.password = "1234";
    }
    
    public void anadir(String nombre) throws SQLException {
        Connection con = DriverManager.getConnection(url, usuario, password);
        Statement statement = con.createStatement();
        
        String sql = "insert into usuarios values (default, '" +nombre+ ");";
        
        int filasAlmacenadas = statement.executeUpdate(sql);
        con.close();
            
        if (filasAlmacenadas > 0) {
            System.out.println("Datos guardados correctamente");
        }
        else {
            System.out.println("Error al guardar los datos");
        }
    }
    
    public String obtenerCodigo(String nombre) throws SQLException {
        Connection con = DriverManager.getConnection(url, usuario, password);
        Statement statement = con.createStatement();
        
        String sql = "select cod from usuarios where nombre="+nombre+";";
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        rs.close();
        con.close();
        return rs.getString(0);
    }
    
    public String obtenerNombre(String cod) throws SQLException {
        Connection con = DriverManager.getConnection(url, usuario, password);
        Statement statement = con.createStatement();
        
        String sql = "select nombre from usuarios where cod="+cod+";";
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        rs.close();
        con.close();
        return rs.getString(0);
    }

}
