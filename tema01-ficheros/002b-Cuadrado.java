/*
Crea un programa que pida al usuario un número entero n y cree un 
fichero "cuadrado.txt", que contendrá n líneas, cada una de las cuales 
estará formada por n asteriscos.

Variante 2: usa BufferedReader y dos "for" anidados
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Carlos
 */
public class Cuadrado {

    public static void main(String[] args) {
        System.out.println ("Introduce el tamaño: ");
        try {
            BufferedReader br = new BufferedReader ( 
                new InputStreamReader (System.in));
            int number = Integer.parseInt(br.readLine());
            PrintWriter pw = new PrintWriter ("cuadrado.txt");
            for (int fila = 0; fila < number; fila++) {
                for (int columna = 0; columna<number; columna++) {
                    pw.write("*");
                }
                pw.println("");
            }
            pw.close();
            
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }
}
