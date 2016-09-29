/**
 * @author chao
 */

package ordenadorclasico;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrdenadorClasico implements Serializable {
    private String marca;
    private String modelo;
    private int anyo;

    public OrdenadorClasico(String marca, String modelo, int anyo) {
        this.marca = marca;
        this.modelo = modelo;
        this.anyo = anyo;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAnyo() {
        return anyo;
    }
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<OrdenadorClasico> listaOrdenador = new ArrayList<>();
        
        try {
            String marca;
            String modelo;
            int anyo;
            File fichero = new File("ordenador.dat");
            FileOutputStream fileOutput = new FileOutputStream(fichero);
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
            
            for (int i = 0; i < 3; i++) {
                System.out.print("Marca: ");
                marca = scan.nextLine();
                System.out.print("Modelo: ");
                modelo = scan.nextLine();
                System.out.print("Año: ");
                anyo = Integer.parseInt(scan.nextLine());
                objectOutput.writeObject(new OrdenadorClasico(marca, modelo, anyo));
            }
            objectOutput.close();
            
            FileInputStream fileInput = new FileInputStream(fichero);
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            
            for (int i = 0; i < 3; i++) {
                OrdenadorClasico ordenador = 
                    (OrdenadorClasico)objectInput.readObject();
                System.out.println("Marca: " + ordenador.getMarca() + 
                    " Modelo: " + ordenador.getModelo() + " Año: " + 
                    ordenador.getAnyo());
            }
            objectInput.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado.");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Clase no encontrada.");
        }
        catch (IOException e) {
            System.out.println("Error en la lectura del archivo.");
        }
        catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
    
}
