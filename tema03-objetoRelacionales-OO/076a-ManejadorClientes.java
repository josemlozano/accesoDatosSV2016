package manejadorclientes;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

/**
 * @author chao
 */
public class ManejadorClientes {
    ObjectContainer db;
    
    public void mostrarMenu() {
        System.out.println("1. Añadir clientes");
        System.out.println("2. Buscar clientes");
        System.out.println("3. Añadir productos");
        System.out.println("4. Buscar productos");
        System.out.println("0. Salir");
        System.out.print("Elige una opción: ");
    }
    
    public void anyadirClientes(Scanner teclado) {
        try {
            List<String> direccion = new ArrayList<>();
            System.out.print("Código: ");
            String codigo = teclado.nextLine();
            System.out.print("Nombre: ");
            String nombre = teclado.nextLine();
            System.out.print("Descuento: ");
            float descuento = Float.parseFloat(teclado.nextLine());
            System.out.print("Dirección (línea 1 de 2): ");
            direccion.add(teclado.nextLine());
            System.out.print("Dirección (línea 2 de 2): ");
            direccion.add(teclado.nextLine());
            System.out.print("Es mayorista (S / N): ");
            String esMayorista = teclado.nextLine();
            
            Calendar fecha = Calendar.getInstance();
            
            boolean mayorista = false;
            int volumen = 0;
            float descuentoAdicional = 0f;
            
            if (esMayorista.toUpperCase().equals("S")) {
                mayorista = true;
                System.out.print("Volumen para descuento: ");
                volumen = Integer.parseInt(teclado.nextLine());
                System.out.print("Descuento adicional: ");
                descuentoAdicional = Float.parseFloat(teclado.nextLine());
            }
            
            db = Db4o.openFile("almacen.dat");
            Cliente cliente = new Cliente(codigo, null, 0, null, null);
            Cliente clienteMayorista = new ClienteMayorista(codigo, null, 0, 
                null, null, 0, 0);
            
            if (!db.get(cliente).hasNext() && !db.get(clienteMayorista).hasNext()) {
                if (mayorista) {
                    cliente = new ClienteMayorista(codigo, nombre, descuento, fecha,
                        direccion, volumen, descuentoAdicional);
                }
                else {
                    cliente = new Cliente(codigo, nombre, descuento, fecha, 
                        direccion);
                }
                db.set(cliente);
                db.commit();
            }
            else {
                System.out.println("Código cliente repetido.");
            }
        }
        finally {
            if (db != null) {
                db.close();
            }
        }
    }
    
    public void buscarClientes(Scanner teclado) {
        try {
            System.out.print("Nombre: ");
            String nombre = teclado.nextLine().toLowerCase();
            
            db = Db4o.openFile("almacen.dat");
            
            List<Cliente> clientes = db.query(new Predicate<Cliente> (){
                @Override
                public boolean match(Cliente cliente) {
                    return cliente.getNombre().toLowerCase().contains(nombre);
                }
            });
            
            if (clientes.size() > 0) {
                clientes.forEach(System.out::println);
            }
            else {
                System.out.println("No hay clientes con nombre parecido al introducido");
            }
        }
        finally {
            if (db != null) {
                db.close();
            }
        }
    }
    
     public void anyadirProducto(Scanner teclado) {
        try {
            System.out.print("Código (números enteros): ");
            int codigo = Integer.parseInt(teclado.nextLine());
            System.out.print("Referencia: ");
            String referencia = teclado.nextLine();
            System.out.print("Nombre: ");
            String nombre = teclado.nextLine();
            System.out.print("Stock mínimo: ");
            int stock = Integer.parseInt(teclado.nextLine());
            System.out.print("Activo (Y / N): ");
            String activo = teclado.nextLine().toLowerCase();
            System.out.print("Comentarios: ");
            String comentarios = teclado.nextLine();
            System.out.print("Fabricación (Fabricado / Distribuido): ");
            String fabricacion = teclado.nextLine();
            
            Calendar fecha = Calendar.getInstance();
            
            Producto producto = new Producto(codigo, null, null, 0, null, 
                false, null, null);
            db = Db4o.openFile("almacen.dat");
            
            if (!db.get(producto).hasNext()) {
                db.set(new Producto(codigo, referencia, nombre, stock, fecha, 
                    (activo.equals("y") ? true : false), comentarios, fabricacion));
                db.commit();
            }
        }
        finally {
            if (db != null) {
                db.close();
            }
        }
    }
     
      public void buscarProductos(Scanner teclado) {
        try {
            System.out.print("Nombre: ");
            String nombre = teclado.nextLine().toLowerCase();
            
            db = Db4o.openFile("almacen.dat");
            List<Producto> productos = db.query(new Predicate<Producto>() {
                @Override
                public boolean match(Producto producto) {
                    return producto.getNombre().toLowerCase().contains(nombre);
                }
            });
            
            if (productos.size() > 0) {
                productos.forEach(System.out::println);
            }
            else {
                System.out.println("No hay productos con nombre parecido al introducido");
            }
        }
        finally {
            if (db != null) {
                db.close();
            }
        }
    }
    
    public static void main(String[] args) {
        ManejadorClientes manejador = new ManejadorClientes();
        String opcion;
        Scanner teclado = new Scanner(System.in);
        
        do {
            manejador.mostrarMenu();
            opcion = teclado.nextLine();
            switch(opcion) {
                case "0":
                    System.out.println("Adiós");
                    break;
                case "1":
                    manejador.anyadirClientes(teclado);
                    break;
                case "2":
                    manejador.buscarClientes(teclado);
                    break;
                case "3":
                    manejador.anyadirProducto(teclado);
                    break;
                case "4":
                    manejador.buscarProductos(teclado);
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
        while(!opcion.equals("0"));
        teclado.close();
    }
}
