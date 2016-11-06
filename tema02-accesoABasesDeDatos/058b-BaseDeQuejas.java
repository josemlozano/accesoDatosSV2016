/*
Crea una clase Java que represente la interfaz con el usuario: por 
ahora, mostrará un menú que permitirá únicamente añadir nuevos usuarios 
o buscar usuarios (introduciendo parte del nombre), así como salir de 
la aplicación. Esta aplicación no deberá acceder directamente a la base 
de datos, sino emplear los métodos que le ofrece la clase "usuarios".
*/

// Indra Lopez

package basedequejas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.Scanner;

class Quejas {
    
    
    public static boolean anadir(String nombre) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/dia0211";
        String usuario = "postgres";
        String password = "1234";
        Connection con = DriverManager.getConnection(url, usuario, password);
        Statement statement = con.createStatement();
        
        String insertSQL ="insert into usuarios values"
                + "(default,'"+nombre+"')";
        int cantidad = statement.executeUpdate(insertSQL);
        con.close();
        return cantidad == 1;
    }
    
    public static String obtenerCodigo(String nombre) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/dia0211";
        String usuario = "postgres";
        String password = "1234";
        String resultado;
        Connection con = DriverManager.getConnection(url, usuario, password);
        Statement statement = con.createStatement();
        
        String sql = "select cod from usuarios where nombre like'"+nombre+"';";
        ResultSet rs = statement.executeQuery(sql);
        if (rs.next()) {
            resultado = rs.getString(1);
        }
        else {
            con.close();
            rs.close();
            return null;
        }
        con.close();
        rs.close();
        return resultado;
    }
    
    public static String obtenerNombre(String cod) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/dia0211";
        String usuario = "postgres";
        String password = "1234";
        String resultado;
        Connection con = DriverManager.getConnection(url, usuario, password);
        Statement statement = con.createStatement();
        
        String sql = "select nombre from usuarios where cod="+cod+";";
        ResultSet rs = statement.executeQuery(sql);
        
        if (rs.next()) {
            resultado = rs.getString(1);
        }
        else {
            con.close();
            rs.close();
            return null;
        }
        con.close();
        rs.close();
        return resultado;
    }
    
    public static List<String> obtenerCodigos(String busqueda) throws ClassNotFoundException, SQLException {
        List<String> codigos = new ArrayList<>();
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/dia0211";
        String usuario = "postgres";
        String password = "1234";
        Connection con = DriverManager.getConnection(url, usuario, password);
        Statement statement = con.createStatement();
        
        String sql = "select cod from usuarios where nombre like '%"+busqueda.toLowerCase()+
                "%' or nombre like '%"+busqueda.toUpperCase()+"%';";
        ResultSet rs = statement.executeQuery(sql);
        
        if (rs.isBeforeFirst()) {
            while (rs.next()) {
                    codigos.add(rs.getString(1));
                }
        }
        rs.close();
        con.close();
        if(codigos.isEmpty()) {
            return null;
        }
        
        else {
            return codigos;
        }

    }
}  

// -------------------------------------------------------------



public class BaseDeQuejas {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner scan = new Scanner(System.in);
        String opcion, nombre, codigo, busqueda, resultado;
        List<String> codigos = new ArrayList<>();
        List<String> nombres = new ArrayList<>();

        do {
            mostrarMenu();
            opcion = scan.next();
            switch (opcion) {
                case "0":
                    System.out.println("Adiós");
                    break;
                case "1":
                    System.out.println("Introduzca nombre usuario a añadir");
                    nombre = scan.next();
                    if(Quejas.anadir(nombre)) {
                        System.out.println("Insercion realizada con exito");
                    }
                    else {
                        System.out.println("Error en la insercion");
                    }
                    break;
                    
                case "2":
                    System.out.println("Introduzca nombre a buscar");
                    nombre = scan.next();
                    resultado =Quejas.obtenerCodigo(nombre);
                    if(resultado !=null) {
                        System.out.println("El codigo de "+nombre+" es "
                                +resultado);
                    }
                    else {
                        System.out.println("Nombre no encontrado");
                    }
                    break;
                    
                case "3":
                    System.out.println("Introduzca codigo a buscar");
                    codigo = scan.next();
                    resultado =Quejas.obtenerNombre(codigo);
                    if(resultado !=null) {
                        System.out.println("El nombre del codigo "+codigo+" es "
                                +resultado);
                    }
                    else {
                        System.out.println("Codigo no encontrado");
                    }
                    break;
                
                case "4":
                    System.out.println("Introduzca busqueda");
                    busqueda = scan.next();
                    codigos = Quejas.obtenerCodigos(busqueda);
                    for (String codigo1 : codigos) {
                        nombres.add(Quejas.obtenerNombre(codigo1));
                    }
                    
                    Collections.sort(nombres, (String o1, String o2) -> o1.compareTo(o2));
                    
                    for (String nombrecin : nombres) {
                        System.out.println(nombrecin);
                    }
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        while (!opcion.equals("0"));
    }
    
    public static void mostrarMenu() {
        System.out.println("1. Añadir usuario");
        System.out.println("2. Obtener codigo");
        System.out.println("3. Obtener nombre");
        System.out.println("4. Obtener codigos");
        System.out.println("0. Salir");
        System.out.print("Elija una opción: ");
    }
    
}
