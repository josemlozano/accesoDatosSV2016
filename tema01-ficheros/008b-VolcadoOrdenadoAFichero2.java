/**
 * @author David Gascón López
 * Pide el nombre de dos archivos al usuario y lo vuelca en otro que tambien se 
 * ha pedido el nombre de forma ordenada.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class VolcadoOrdenadoAFichero2 {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el nombre del archivo 1: ");
        String nombre1 = sc.next();
		System.out.print("Introduce el nombre del archivo 2: ");
		String nombre2 = sc.next();
		System.out.print("Introduce el nombre del archivo de salida: ");
		String nombreSalida = sc.next();

		BufferedReader ficheroEntrada1 = null;
		BufferedReader ficheroEntrada2 = null;
		PrintWriter ficheroSalida = null;
		try {
			ficheroEntrada1 = new BufferedReader(new FileReader(
					new File(nombre1)));            

			ArrayList<String> palabras = new ArrayList<String>();
			String linea;

			do {
				linea = ficheroEntrada1.readLine();
				if(linea != null){
					palabras.add(linea);
				}
			} while(linea != null);
			ficheroEntrada1.close();

			ficheroEntrada2 = new BufferedReader(new FileReader(
				new File(nombre2)));
			do {
				linea = ficheroEntrada2.readLine();
				if(linea != null) {
					palabras.add(linea);
				}
			} while(linea != null);
			ficheroEntrada2.close();

			palabras.sort(null);
			
			ficheroSalida = new PrintWriter (nombreSalida);
			for(String palabra : palabras) {
				ficheroSalida.println(palabra);
			}
			ficheroSalida.close();

		}
		catch (FileNotFoundException e) {
			System.out.println("El fichero no se ha encontrado.");
		}
		catch (IOException e) {
			System.out.println("No se ha podido leer.");
		}
    }   
}
