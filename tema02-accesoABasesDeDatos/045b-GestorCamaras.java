import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestorCamaras {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/dia20";
        String usuario = "postgres";
        String password = "1234";
        Connection con = DriverManager.getConnection(url, usuario, password);

        Statement statement = (Statement) con.createStatement();
        String sentenciaSQL = "SELECT * FROM camaras ORDER BY marca, modelo";
        ResultSet rs = statement.executeQuery(sentenciaSQL);
        System.out.println("Marca     Modelo          Res.");
        System.out.println("-----------------------------------------");
        while (rs.next()) {
            String marca = rs.getString(2);
            String modelo = rs.getString(3);
            String resoluc = rs.getString(4);
            System.out.printf("%-10s", marca);
            System.out.printf("%-15s", modelo);
            System.out.printf("%5s\n", resoluc);
        }
        rs.close();
        con.close();
    }
} 
