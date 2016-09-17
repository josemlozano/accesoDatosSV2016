/*
Crea un programa que pida al usuario el nombre de un fichero y vuelque 
a otro fichero (con el mismo nombre pero terminado en ".mayusculas") 
todas las líneas que éste contiene, convertidas a mayúsculas.
*/

//Vicente Cuenca


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CopiarFichero {

    public static void main(String[] args) throws IOException {
        
        BufferedReader fileRead = null;
        PrintWriter fileWriter = null;

        System.out.println("Introduce el nombre del fichero: ");
        Scanner s = new Scanner(System.in);
        String name = s.nextLine();        
        
        if (! (new File(name)).exists() ) {
            System.out.println("No existe el fichero: ");
            return;
        }
        try {
            fileRead = new BufferedReader(
                new FileReader(new File(name)));
            
            fileWriter = new PrintWriter (name+".mayusculas");

            String line = fileRead.readLine();
            while (line != null) {
                fileWriter.println (line.toUpperCase());
                line = fileRead.readLine();

            }
        }
        catch (IOException errorDeFichero) {
            System.out.println(
                "Ha habido problemas: " +
                errorDeFichero.getMessage() );
        }
        finally {
            if(fileRead != null){
                fileRead.close();
            }
            if(fileWriter != null){
                fileWriter.close();
            }
        }
    }
}
