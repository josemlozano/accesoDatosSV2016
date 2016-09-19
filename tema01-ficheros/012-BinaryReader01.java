/*
Crea un programa que pida al usuario el nombre de un fichero (no 
necesariamente de texto) y muestre en pantalla la cantidad de veces que 
contiene el carácter "a" (a minúscula).
*/

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Vande
 */
public class BinaryReader01 {

    public static void main(String[] args) {
        System.out.println("Enter the name of file: ");
        Scanner sc = new Scanner(System.in);
        String fileName = sc.next();
        try{
            FileInputStream reader = new FileInputStream(new File(fileName));
            
            int data;
            int counter = 0;
            do {
                data = reader.read();
                if (data == 97)
                    counter++;
            } while (data != -1);
            
            reader.close();
            
            System.out.println("The count of 'a' is: "+counter);
        }
        catch (FileNotFoundException e){
            System.out.println("File not found!");
        }
        catch (Exception e){
            e.getMessage();
        }
    }
    
}
