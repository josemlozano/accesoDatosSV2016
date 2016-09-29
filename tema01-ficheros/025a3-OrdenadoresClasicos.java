/*
 * @author David Gascón López
 * Pedir 3 ordenadores clasicos, guardaros, cargarlos y mostrarlos
 */
package ordenadoresclasicos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class OrdenadoresClasicos {

    public static void main(String[] args) {
        try{
            ArrayList<Ordenadores> listaOrdenadores = new ArrayList<Ordenadores>();

            OrdenadoresClasicos miManejador = new OrdenadoresClasicos();

            String marca;
            String modelo;
            int anyo;

            Ordenadores ordenador;

            Scanner teclado = new Scanner(System.in);

            for(int i = 0; i < 3 ;  i ++) {
                System.out.print("Marca del Ordenador: ");
                marca = teclado.nextLine();

                System.out.print("Modelo del Ordenador: ");
                modelo = teclado.nextLine();

                System.out.print("Año del Ordenador: ");
                anyo = teclado.nextInt();
                teclado.nextLine();
                
                System.out.println("");
                
                ordenador = new Ordenadores(marca, modelo, anyo);
                listaOrdenadores.add(ordenador);
            }
            miManejador.guardar(listaOrdenadores);

            ArrayList<Ordenadores> misOrdenadores = new ArrayList<Ordenadores>();

            misOrdenadores = miManejador.cargar();
        
            miManejador.mostrar(misOrdenadores);
        }
        catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado");
        }        
        catch (IOException e) {
            System.out.println("No se ha podido escribir o leer en fichero");
        }
        catch (Exception e) {
            System.out.println(
                    "Error no controlado: " + e.getMessage()); 
        }
    }
    
    private void guardar(ArrayList<Ordenadores> ListaOrdenadores) throws FileNotFoundException, IOException {
        File ficheroGuardar = new File("ordenadores.dat");
        FileOutputStream ficheroSalidaGuardar
                = new FileOutputStream(ficheroGuardar);
        ObjectOutputStream ficheroObjetosGuardar
                = new ObjectOutputStream(ficheroSalidaGuardar);

        ficheroObjetosGuardar.writeObject(ListaOrdenadores);

        ficheroObjetosGuardar.close();
    }
    
    private ArrayList<Ordenadores> cargar() throws FileNotFoundException, IOException, ClassNotFoundException {

        if ((new File("ordenadores.dat")).exists()) {

            ArrayList<Ordenadores> ordenadoresReturn = new ArrayList<Ordenadores>();

            File fichero = new File("ordenadores.dat");
            FileInputStream ficheroSalida
                    = new FileInputStream(fichero);
            ObjectInputStream ficheroObjetos
                    = new ObjectInputStream(ficheroSalida);
            ordenadoresReturn = (ArrayList<Ordenadores>) ficheroObjetos.readObject();
            ficheroObjetos.close();

            return ordenadoresReturn;

        }
        return null;
    }
    
    private void mostrar(ArrayList<Ordenadores> listaAmigos) {
        for(int i = 0 ; i < listaAmigos.size() ; i ++){
            System.out.println("Marca del ordenador: " + 
                    listaAmigos.get(i).getMarca());
            System.out.println("Modelo del ordenador: " + 
                    listaAmigos.get(i).getModelo());
            System.out.println("Año del ordenador: " + 
                    listaAmigos.get(i).getAnyo());

            // Este if es para poner una separacion entre 
            // amigos siempre que no sea el ultimo ordenador
            if(i + 1 < listaAmigos.size()){
                System.out.println("");
            }
        }
    }
}
