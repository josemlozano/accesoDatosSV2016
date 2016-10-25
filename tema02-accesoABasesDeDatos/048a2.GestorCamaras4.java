package gestorcamaras;

import java.sql.CallableStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class GestorCamaras {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/dia20";
        String usuario = "postgres";
        String password = "n";

        String marca = "";
        String modelo = "";
        String resolucion = "";
        
        int opcion;

        Scanner teclado = new Scanner(System.in);

        do {
            System.out.println("1- Añadir cámara:");
            System.out.println("2- Buscar cámaras:");
            System.out.println("3- Cantidad de cámaras:");
            System.out.println("4- Cámaras según resolución:");
            System.out.println("0- Salir:");

            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    Connection con = 
                            DriverManager.getConnection(url, usuario, password);
                    Statement statement = con.createStatement();

                    System.out.println("Marca:");
                    marca = teclado.nextLine();
                    System.out.println("Modelo:");
                    modelo = teclado.nextLine();
                    System.out.println("Resolucion:");
                    resolucion = teclado.nextLine();

                    String sentenciaSQL = "INSERT INTO camaras VALUES "
                            + "(default,'" + marca + "','" + modelo + "'," + resolucion +");";
                    int cantidad = statement.executeUpdate(sentenciaSQL);

                    if (cantidad == 1) {
                        System.out.println("Guardado correctamente");
                    } else {
                        System.out.println("No se ha podido guardar!");
                    }
                    System.out.println();
                    con.close();

                    break;

                case 2:

                    Connection con2 = DriverManager.getConnection(url, 
                        usuario, password);
                    Statement statement2 = con2.createStatement();
                    
                    System.out.print("Dime marca o modelo de cámara: ");
                    
                    String busqueda = teclado.nextLine().toUpperCase();
                    
                    String selectSQL = "SELECT * FROM camaras where "
                            + "UPPER(marca) LIKE '%"+busqueda
                            +"%' or UPPER(modelo) LIKE '%"+busqueda+"%'";
                    ResultSet rs = statement2.executeQuery(selectSQL);

                    System.out.printf("%-10s", "Marca");
                    System.out.printf("%-15s", "Modelo");
                    System.out.printf("%-5s", "Resolución \n");
                    System.out.println("-----------------------------------------");
                    while (rs.next()) {
                        marca = rs.getString(2);
                        modelo = rs.getString(3);
                        resolucion = rs.getString(4);
                        System.out.printf("%-10s", marca);
                        System.out.printf("%-15s", modelo);
                        System.out.printf("%-5s \n", resolucion);
                    }
                    
                    rs.close();
                    con2.close();
                    
                    break;

                case 3:

                    Connection con3 = DriverManager.getConnection(url, 
                        usuario, password);
                    Statement statement3 = con3.createStatement();
                    String consulta = "SELECT CantidadCamaras();";
                    ResultSet rs3 = statement3.executeQuery(consulta);
                    
                    rs3.next();
                    System.out.println( "Camaras: " + rs3.getString(1) );
                    
                    rs3.close();
                    con3.close();
                    
                    break;

                case 4:
                    
                    System.out.print("MP Mínimos: ");                    
                    float min = teclado.nextFloat();
                    System.out.print("MP Máximos: ");                    
                    float max = teclado.nextFloat();

                    Connection con3 = DriverManager.getConnection(url, 
                        usuario, password);
                    CallableStatement cStmt = con3.
                        prepareCall("{call CantidadCamarasMP(?, ?)}");   
                    cStmt.setInt(1, (int) min);
                    cStmt.setInt(2, (int) max);
                    cStmt.registerOutParameter(1, Types.INTEGER); 
                    cStmt.execute();
                    System.out.println( "Camaras: " + cStmt.getInt(1) );
                    con3.close();
                    
                    break;
            }

        } while (opcion != 0);
    }
}
