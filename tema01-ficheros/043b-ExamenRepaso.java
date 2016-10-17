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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author chao chen
 */
public class ExamenRepaso {
    
    public void guardarFecha() {
        try {
            File fichero = new File("exportaciones.dat");
            FileOutputStream fichSalida = new FileOutputStream(fichero, true);
            ObjectOutputStream objSalida = new ObjectOutputStream(fichSalida);
            
            objSalida.writeObject(Calendar.getInstance());
            objSalida.close();
        }
        catch (IOException e) {
            System.out.println("Error al guardar el archivo.");
        }
        catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
    
    public List<String> leerXml(String input) {
        List<String> sql = new ArrayList<>();
        sql.add("INSERT INTO 'asisten' ('fecha', 'horad', 'horah'," + 
            " 'justi', 'nia', 'contenido') VALUES");
        
        try {
            File fichEntrada = new File(input);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fichEntrada);
            doc.getDocumentElement().normalize();
            
            NodeList nList = doc.getElementsByTagName("falta_alumno");
            
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String[] fechaPartes = eElement.getAttribute("dia").split("/");
                    String horaDesde = eElement.getAttribute("hora_desde");
                    String horaHasta = eElement.getAttribute("hora_hasta");
                    String justificada = eElement.getAttribute("justificada");
                    String nia = eElement.getAttribute("alumno");
                    
                    if (temp != nList.getLength() - 1) {
                        sql.add( "('" + fechaPartes[2] + "-" + fechaPartes[1] + 
                            "-" + fechaPartes[0] + "', '" + horaDesde + "', '" +
                            horaHasta + "', '" + justificada + "', '" + nia + 
                            "', ''),");
                    }
                    else {
                        sql.add( "('" + fechaPartes[2] + "-" + fechaPartes[1] + 
                            "-" + fechaPartes[0] + "', '" + horaDesde + "', '" +
                            horaHasta + "', '" + justificada + "', '" + nia + 
                            "', '');");
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el archivo");
        }
        catch (IOException e) {
            System.out.println("Error al leer el archivo.");
        }
        catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
        return sql;
    }
    
    public void guardarTxt(List<String> data, String ficheroSalida) {
        try {
            File fichero = new File(ficheroSalida);
            PrintWriter writer = new PrintWriter(fichero);
            
            for (String line : data) {
                writer.println(line);
            }
            writer.close();
        }
        catch (IOException e) {
            System.out.println("Error al crear el archivo.");
        }
        catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        String input, output;
        Scanner scan = new Scanner(System.in);
        System.out.print("Nombre del fichero entrada: ");
        input = scan.nextLine();
        System.out.print("Nombre del fichero salida: ");
        output = scan.nextLine();
        
        ExamenRepaso examen = new ExamenRepaso();
        examen.guardarFecha();
        examen.guardarTxt(examen.leerXml(input), output);
    }
}
