/* 
Crea un programa que pida al usuario el nombre de dos ficheros, 
uno de entrada y uno de salida. El fichero de entrada contendrá 
un cuadrado de asteriscos y en el de salida se creará un triángulo 
descendente que comience con el número de asteriscos, por ejemplo:

Entrada.txt:
***
***
***
Salida.txt:
***
**
* 

*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CuadradoATriangulo {

    public static void main(String[] args) throws IOException {

        BufferedReader fileRead = null;
        PrintWriter fileWriter = null;

        System.out.println("Introduce el nombre de lectura: ");
        Scanner teclado = new Scanner(System.in);
        String ficheroEntrada = teclado.nextLine();
        
        System.out.println("Introduce el nombre de escritura : ");
        String ficheroSalida = teclado.nextLine();

        try {
            fileRead = new BufferedReader(
                    new FileReader(new File(ficheroEntrada)));
            String linea = fileRead.readLine();
            fileRead.close();
            
            fileWriter = new PrintWriter(ficheroSalida);
            int altura = linea.length();
            for (int fila = altura; fila > 0; fila--) {
                for (int col = 0; col < fila; col++) {
                    fileWriter.write("*");
                }
                fileWriter.println();
            }
            fileWriter.close();
        }
        catch (FileNotFoundException noExiste) {
            System.out.println(
                    "Fichero no encontrado");
        }       
        catch (IOException errorDeFichero) {
            System.out.println(
                    "Ha habido problemas: "
                    + errorDeFichero.getMessage());
        }
    }
}
