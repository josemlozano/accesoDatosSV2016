package dia0211;

import java.util.ArrayList;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author chao chen
 */
class Usuarios {
    private static String url = "jdbc:postgresql://localhost:5432/dia0211";
    private static String usuario = "postgres";
    private static String password = "chen";
    
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
 
 
public class Dia0211 {
    
    public Dia0211() {
    }

    public void mostrarMenu() {
        System.out.println("1. Añadir usuario");
        System.out.println("2. Buscar usuarios");
        System.out.println("0. Salir");
        System.out.print("Elige una opcion: ");
    }
    
    public void anyadirUsuario() throws ClassNotFoundException {
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Nombre: ");
        String nombre = scan.nextLine();
        
        int filasAlmacenadas = Usuarios.anadir(nombre);
        if (filasAlmacenadas > 0) {
            System.out.println("Usuario guardado correctamente");
        }
        else {
            System.out.println("Error al guardar el usuario: " + nombre);
        }
    }
    
    public void obtenerCodigos() throws ClassNotFoundException {
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Nombre: ");
        String nombre = scan.nextLine();
        
        ArrayList<Integer> listaCodigos = Usuarios.obtenerCodigos(nombre);
        if (listaCodigos == null) {
            System.out.println("No existe usuarios con un nombre parecido al" +
                " introducido");
        }
        else {
            for (int i = 0; i < listaCodigos.size(); i++) {
                String nombreUsuario = Usuarios.obtenerNombre(listaCodigos.get(i));
                System.out.println("Codigo: " + listaCodigos.get(i) + ", Nombre :" +
                    nombreUsuario);
            }
        }
    }
    
    public static void main(String[] args) throws ClassNotFoundException {
        Dia0211 dia0211 = new Dia0211();
        
        String opcion;
        Scanner scan = new Scanner(System.in);
        do {
            dia0211.mostrarMenu();
            opcion = scan.nextLine();
            
            switch (opcion) {
                case "0":
                    System.out.println("Adiós");
                    break;
                case "1":
                    dia0211.anyadirUsuario();
                    break;
                case "2":
                    dia0211.obtenerCodigos();
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
        while (!opcion.equals("0"));
    }
}
