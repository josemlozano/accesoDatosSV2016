/*

Crea un programa que vuelque el contenido de un fichero HTML a otro 
fichero, eliminando todas las etiquetas HTML (todo aquello que se 
encuentre entre "<" y ">". Ambos nombres de ficheros se pedirán al 
usuario.

*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Adrian
 */
public class HtmlToTxt {
    public static void main(String[] args) throws IOException {
        
        Scanner scaner = new Scanner(System.in);
        PrintWriter  myPrint = null;
        BufferedReader myFile = null;
        
        List<String> myText = new ArrayList();
        
        boolean isMark = false;
        
        try{
            
            System.out.println("Nombre");
            String name= scaner.nextLine();
            
            myPrint = new PrintWriter(new BufferedWriter
                ( new FileWriter("FicheroSalida.txt")));
            
            myFile = new BufferedReader
                ( new FileReader(new File(name)));
            
            String line;
            
            line = myFile.readLine();
            while (line!=null) {
                myText.add(line);
                line = myFile.readLine();
            }
            myFile.close();
            
            for (int i = 0; i < myText.size(); i++) {
                String currentLine = myText.get(i);
                String resultLine = "" ;
                for (int j = 0; j < currentLine.length(); j++) {
                    if (currentLine.charAt(j)=='<') {
                        isMark=true;
                    }
                    else if (currentLine.charAt(j)=='>') {
                        isMark=false;
                    }
                    else if (!isMark) {
                        resultLine+=currentLine.charAt(j);
                    }
                }
                 myPrint.println(resultLine);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
        catch (IOException e) {
            System.out.println(e);
        }
        catch (Exception e){
            System.out.println(e);
        }
        
        finally {
           if ( myPrint != null ) {
                   myPrint.close();
           }
        }
    }
    
}
