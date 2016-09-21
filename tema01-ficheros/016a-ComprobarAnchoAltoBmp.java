/*
Crea un programa que pida al usuario el nombre de un fichero y diga si 
parece un BMP válido (sus dos primeros bytes son B y M) y, en ese caso, 
su ancho y su alto. Debes leer por bloques (la cabecera es un bloque de 
54 bytes).
*/

//import java.io.*;
import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Ruben Blanco
 */
public class ComprobarAnchoAltoBmp {

    public static void main(String[] args) throws IOException {
        
        final int BUFFER_SIZE = 54;
        FileInputStream myFile = null;
        
        try {
            System.out.print("Enter the name of a BMP file: ");
            Scanner sc = new Scanner(System.in);
            String name = sc.nextLine();
            
            myFile = new FileInputStream(new File(name));
            
            byte[] buffer = new byte [BUFFER_SIZE];
            
            myFile.read(buffer,0,BUFFER_SIZE);

            // TO DO
            // Note: the following may fail if:
            // buffer[18] > 127 or buffer[22] > 127 (possible)
            // buffer[19] > 127 or buffer[23] > 127 (less usual)
            // As a Java "byte" is in the range -128..+127
            int width = ((int) buffer[18]) + (((int) buffer[19]) * 256);
            int length = ((int) buffer[22]) + (((int) buffer[23]) * 256);
            
            boolean isBMP = false;
            
                if ( (buffer[0] == 'B') && (buffer[1] == 'M') ) {
                        isBMP = true;
                }
                
            if (isBMP) {
                System.out.println("The file seems a valid BMP");
                System.out.println("Width: " + width);
                System.out.println("Length: " + length);
            }
            else {
                System.out.println("The file seems a not valid BMP");
            }  
        }

        catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        
        //IOException catches all file problems
        catch (IOException e) {
            System.out.println("Can´t read/write the file" + e.getMessage());
        }
        
        // Exception catches the rest of the problems
        catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
        finally {
            if(myFile != null){
                myFile.close();
            }
        }
    }
}
