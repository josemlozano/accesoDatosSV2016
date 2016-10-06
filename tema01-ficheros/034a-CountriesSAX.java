import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Adrian_Garcia
 */
class ReadXml extends DefaultHandler{
 
public ArrayList<String> procesarXml(){
    ArrayList<String> listaProcesada = new ArrayList<>();
    try {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        DefaultHandler manejadorEventos = new DefaultHandler(){
            String etiquetaActual = "";
            String contenido = "";
            @Override
            public void startElement(String uri, String localName,
                    String qName, Attributes attributes) 
                    throws SAXException {
                
                etiquetaActual = qName;
                if (etiquetaActual.equals("country")) {
                    listaProcesada.add(attributes.getValue("name").split(",")[1]);
                }
            }
        };

        // Cuerpo de la función: trata de analizar el fichero deseado
        // Llamará a startElement(), endElement() y character() 
        saxParser.parse("countries.xml", manejadorEventos);
        } 
        catch (ParserConfigurationException | SAXException e) {
            System.out.println("Problema al trabajar con XML");
        }
        catch (IOException e) {
            System.out.println("Problema al trabajar con ficheros");
        }
        catch (Exception e) {
            System.out.println("Problema no identificado");
        }
    
        return listaProcesada;
    }
}

public class CountriesSAX {
    public static void main(String args[]) {
        ReadXml ficheroXml = new ReadXml();
        Scanner teclado = new Scanner(System.in);
        ArrayList<String> listaPaises = ficheroXml.procesarXml();
        Collections.sort(listaPaises);
        String nombrePais;

        do {
            System.out.print("Indica un fragmento de los paises a buscar: ");
            nombrePais = teclado.nextLine().toLowerCase();

            if(!nombrePais.equals("")) {
                for (String nombre : listaPaises) {
                    if(nombre.toLowerCase().contains(nombrePais)) {                           
                        System.out.println(
                            "Nombre oficial: "+ nombre + " ");
                    }
                }
            }
        }
        while(!nombrePais.equals(""));
    }
}
