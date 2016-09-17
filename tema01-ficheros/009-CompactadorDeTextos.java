/* 

Crea un programa que pida al usuario el nombre de un fichero de entrada y 
de uno de salida. El fichero de salida tendrá el contenido sólo el texto 
puro (letras de la "a" a la "z", minúsculas o mayúsculas) del fichero de 
entrada. Cada vez que el fichero de entrada tenga un carácter que no 
pertenezca a ese conjunto, la siguiente letra del fichero de salida irá en 
mayúsculas. Por ejemplo, si el primer fichero contiene

Hola, quE tal estas? Dijo él
biEn

El fichero de salida deberá contener

HolaQueTalEstasDijoL
Bien

*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author Ruben Blanco, correcciones por Nacho
 */
public class CompactadorDeTextos {

public static void main(String[] args) {
        
        PrintWriter salida = null;
        
        try {
            System.out.print("Fichero de entrada? ");
            Scanner teclado = new Scanner(System.in);
            String nombreEntrada = teclado.nextLine();
            
            System.out.print("Fichero de salida? ");
            String nombreSalida = teclado.nextLine();
            salida = new PrintWriter (nombreSalida);
            
            BufferedReader entrada = new BufferedReader(
                new FileReader(new File(nombreEntrada)));
            
            String linea = entrada.readLine();
            
            while(linea != null) {
                boolean mayusculas = true;
                char letraMinusc;
                
                for (int pos = 0; pos < linea.length(); pos++) {
                    letraMinusc = Character.toLowerCase(linea.charAt(pos));
                    
                    // Carácter no imprimible: el siguiente en mayusculas
                    if((letraMinusc < 'a')||(letraMinusc > 'z')) {
                        mayusculas = true;
                    }
                    // Letra A-Z: mayúsculas la primera, minusc. el resto
                    else
                    {
                        if(mayusculas) {
                             salida.print(Character.toUpperCase(letraMinusc));
                             mayusculas = false;
                        }
                        else {
                            salida.print(letraMinusc);
                        }
                    }
                }           
                salida.println();    
                linea = entrada.readLine();
            }
            
            entrada.close();
            salida.close();
        }
        
        catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado");
        }        
        catch (IOException e) {
            System.out.println("No se ha podido escribir o leer en fichero");
        }
    }
}
