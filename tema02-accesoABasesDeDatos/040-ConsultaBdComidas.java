/*
 * Crea un programa que muestre datos de "recetas" desde Java.
 */
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author monica
 */
public class ConsultaBD {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5433/comidas";
        String usuario = "postgres";
        String password = "m";
        Connection con = DriverManager.getConnection(url, usuario, password);

        Statement statement = (Statement) con.createStatement();
        String sentenciaSQL = "SELECT * FROM recetas ORDER BY nombre";
        ResultSet rs = statement.executeQuery(sentenciaSQL);
        System.out.println("Codigo" + "\t" + "Nombre" + "\t" + "minutos");
        System.out.println("-----------------------------------------");
        while (rs.next()) {
            System.out.println(rs.getString(1)
                + "\t " + rs.getString(2));
                + "\t " + rs.getString(3));
        }
        rs.close();
        con.close();
    }
} 
