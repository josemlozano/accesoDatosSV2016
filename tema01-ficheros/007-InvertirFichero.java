/*
Crea un programa que pida al usuario el nombre de un fichero y vuelque 
a otro fichero, de la última línea a la primera y en cada línea de la 
última letra a la primera. Por ejemplo, si el primer fichero contiene

uno
dos
tres

El fichero de salida deberá contener

sert
sod
onu

Nota: no puedes usar PrintWriter sino BufferedWriter.
*/

/**
 * @author chao chen
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class InvertirFichero {

    public static void main(String[] args) throws IOException {
        Scanner escanear = new Scanner(System.in);
        System.out.print("introduzca el nombre del archivo: ");
        String nombre = escanear.nextLine();
        
        File archivo = new File(nombre);
        if (! archivo.exists()) {
            System.out.println("No existe el archivo.");
            return;
        }
        
        BufferedReader leer = null;
        BufferedWriter imprimir = null;
        
        try {
            leer = new BufferedReader(new FileReader(archivo));
            
            Stack<String> lineas = new Stack<>();
            String linea;
            do {
                linea = leer.readLine();
                if (linea != null) {
                    lineas.add(linea);
                }
            }
            while (linea != null);
            
            imprimir = new BufferedWriter(new FileWriter(
                new File(nombre + ".inv")));
            while (lineas.size() > 0) {
                linea = lineas.pop();
                for (int i = linea.length() - 1; i >= 0; i--) {
                    imprimir.write(linea.charAt(i));
                }
                imprimir.newLine();
            }
        }
        catch (IOException e) {
            System.out.println("No se ha podido leer o crear el archivo.");
        }
        finally {
            if (leer != null) {
                leer.close();
            }
            if (imprimir != null) {
                imprimir.close();
            }
        }
    }
    
}
