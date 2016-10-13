/*
    create table amigos( 
       cod varchar(5) primary key,
       nombre varchar(10),
       correo varchar(30),
       anyo numeric(4),
       movil numeric(10)
   );
 */
package amigossql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Ruben Blanco
 */
public class AmigosSQL {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        
        String url = "jdbc:postgresql://localhost:5433/amigos";
        String usuario = "postgres";
        String password = "VMware";
        
        String cod;
        String nombre;
        String correo;
        int anyo;
        int movil;

        int opcion;

        Scanner teclado = new Scanner(System.in);

        do {
            System.out.println("1- Añadir amigo:");
            System.out.println("2- Ver amigos:");
            System.out.println("0- Salir:");

            opcion = Integer.parseInt(teclado.nextLine());

            switch (opcion) {
                case 1:
                    
                    Connection con = DriverManager.getConnection(url, usuario, password);
                    Statement statement = con.createStatement();
                    
                    System.out.println("Codigo:");
                    cod = teclado.nextLine();
                    System.out.println("Nombre:");
                    nombre = teclado.nextLine();
                    System.out.println("Correo:");
                    correo = teclado.nextLine();
                    System.out.println("año:");
                    anyo = Integer.parseInt(teclado.nextLine());
                    System.out.println("Movil:");
                    movil = Integer.parseInt(teclado.nextLine());

                    String sentenciaSQL = "INSERT INTO amigos VALUES " +
                            "('" + cod + "','" + nombre + "','" + correo + 
                            "'," + anyo + "," + movil + ");";
                    int cantidad = statement.executeUpdate(sentenciaSQL);
                    
                    if (cantidad == 1)
                        System.out.println("Guardado correctamente");
                    else
                        System.out.println("No se ha podido guardar!");
                    System.out.println();
                    con.close();                    
                    break;
                    
                case 2:
                    
                    Connection con2 = DriverManager.getConnection(url, 
                        usuario, password);
                    Statement statement2 = con2.createStatement();
                    
                    String selectSQL = "SELECT * FROM amigos ORDER BY cod";
                    ResultSet rs = statement2.executeQuery(selectSQL);

                    while(rs.next()) {
                        System.out.println();
                        System.out.println("Codigo: " + rs.getString(1));
                        System.out.println("Nombre: " + rs.getString(2));
                        System.out.println("Correo: " + rs.getString(3));
                        System.out.println("Año: " + rs.getString(4));
                        System.out.println("Movil: " + rs.getString(5));
                        System.out.println();
                    }
                    
                    rs.close();
                    con2.close();
                    
                    break;
                
                case 0:
                    System.out.println("Hasta Luego!!!");
                    break;
                
                default:
                    break;
            }
        } while (opcion != 0);
    }
}
