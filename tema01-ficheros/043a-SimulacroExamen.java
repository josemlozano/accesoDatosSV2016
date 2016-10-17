/*
(Nota: el examen podría ser parecido a este ejercicio, pero más largo)

Crea un programa de consola que lea un fichero XML como este:

<?xml version="1.0"?>

<centro codigo="03" denominacion="IES SAN VICENTE" curso="2016" 
   fechaExportacion="17/10/2016 17:55:16" version="1.0">  
<faltas_alumno>    
<falta_alumno alumno="103" plantilla="1" sesion_orden="13" 
   hora_desde="17:10" hora_hasta="18:00" grupo="2CFBJ" dia="06/10/2016" 
   justificada="N" retraso="N" falta="S"/>    
<falta_alumno alumno="102" plantilla="1" sesion_orden="14" 
   hora_desde="18:00" hora_hasta="18:50" grupo="2DAM" dia="14/10/2016"
   justificada="N" retraso="N" falta="S"/>    
<falta_alumno alumno="104" plantilla="1" sesion_orden="17" 
   hora_desde="20:05" hora_hasta="21:00" grupo="2ESOA" dia="14/10/2016"
   justificada="N" retraso="N" falta="S"/>

(...)


Y genere un fichero con una apariencia como esta:

INSERT INTO `asisten` (`fecha`, `horad`, `horah`, `justi`, `nia`, `contenido`) VALUES
('2016-09-11', '09:45', '10:40', 'N', '100', ''),
('2016-09-11', '20:05', '21:00', 'N', '102', ''),
('2016-09-12', '07:55', '08:50', 'N', '101', ''),
(...)

Además, en cada ejecución, añadirá al fichero "exportaciones.dat" la 
fecha actual, usando serialización (pero puede basarse en la clase que 
tu elijas: Date, Calendar o cualquier otra.

*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Adrian Sanchis Gallego
 */
public class SimulacroExamen {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el nombre de archivo: ");
        String file = sc.nextLine();
        File inputFile = new File(file);

        if (!inputFile.exists()) {
            System.out.println("Fichero no econtrado");
            return;
        }

        PrintWriter printWriter = null;

        try {

            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("falta_alumno");

            printWriter = new PrintWriter("ejemplo.sql");
            printWriter.println("INSERT INTO `asisten` (`fecha`, `horad`,"
                +"`horah`, `justi`, `nia`, `contenido`) VALUES");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String[] dia = eElement.getAttribute("dia").split("/");

                    printWriter.println("('" + dia[2] + "-" + dia[1] + "-" + dia[0]
                            + "', '" + eElement.getAttribute("hora_desde") 
                            + "', '" + eElement.getAttribute("hora_hasta")
                            + "', '" + eElement.getAttribute("justificada") 
                            + "', '" + eElement.getAttribute("alumno") + "', ''),");
                }
            }

        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al leer del fichero");
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }

        }

        File fichero = new File("exportaciones.dat");
        FileOutputStream ficheroSalida
                = new FileOutputStream(fichero, true);
        ObjectOutputStream ficheroObjetos = new ObjectOutputStream(ficheroSalida);

        ficheroObjetos.writeObject(new Date());
        ficheroObjetos.close();

    }
}
