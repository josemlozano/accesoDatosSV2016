/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author alumno
 */
public class BMPvisor {
   public static void main(String[] args) {
        
        System.out.print("File name? ");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        
        try {
            FileInputStream myFile = new FileInputStream(new File(name));
            
            File mi_fichero = new File(name);
            int BUFFER_SIZE = (int) mi_fichero.length();
            
            byte[] imageData = new byte [BUFFER_SIZE];
            
            myFile.read(imageData,0,BUFFER_SIZE);
            myFile.close();

            int width = ((int) imageData[18]) + (((int) imageData[19]) * 256);
            int height = ((int) imageData[22]) + (((int) imageData[23]) * 256);
            
            
            if ( (imageData[0] != 'B') || (imageData[1] != 'M') ) {
                System.out.println("Does not seem a valid BMP!");
                return;
            }
                
            System.out.println("Seems a valid BMP file");
            System.out.println("Width: " + width);
            System.out.println("Height: " + height);
            
            if ( (width > 79) || (height > 24) ) {
                System.out.println("Too big for console!");
                return;
            }
                
            int startPos = ((int) imageData[10]) + (((int) imageData[11]) * 256);
            
            System.out.println("Data starts at position: "+
                startPos);
            System.out.println();
            
            System.out.println("Image lines:");
            System.out.println();
            
            // We'll store the data inside an array of strings
            String[] lines = new String[height];
            for (int i = 0; i < height; i++) {
                lines[i] = "";
            }
            

            int count = 0;
            String dataLine = "";
            
            for (int i = BUFFER_SIZE-1 ; i > startPos ; i--) {
                count++;
                
                if (imageData[i]<0) {
                    dataLine+=" ";
                }
                else {
                    dataLine+="*";
                }
                
                if (count >= width) {
                    String invertedLine = new StringBuilder(dataLine).
                        reverse().toString();
                    /*
                    String invertedLine="";
                    for (int j=dataLine.length()-1; j >= 0; j--) {
                        invertedLine += dataLine.charAt(j);
                    }
                    */

                    System.out.println(invertedLine);
                    
                    dataLine="";
                    count=0;
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage() );
        }
    }

}
