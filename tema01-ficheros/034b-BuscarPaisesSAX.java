/**
 *
 * @author Adrian Sanchis Gallego
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.Scanner;

class SaxReader extends DefaultHandler {

    List<String> mylist = new ArrayList<>();

    public List<String> getList() {
        return this.mylist;
    }

    public void procesarXml() {
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            DefaultHandler manejadorEventos = new DefaultHandler() {

                String etiquetaActual = "";
                String contenido = "";

                // Método que se llama al encontrar inicio de etiqueta: '<'
                public void startElement(String uri, String localName,
                        String qName, Attributes attributes)
                        throws SAXException {

                    // Si el nombre es "asignatura",
                    // empieza una nueva y mostramos su id
                    // Si no, memorizamos el nombre para mostrar después
                    etiquetaActual = qName;
                    if (etiquetaActual == "country") {
                        mylist.add(attributes.getValue("name").split(",")[1]);

                    }
                }

                // Obtiene los datos entre '<>' y '</>'
                public void characters(char ch[], int start, int length)
                        throws SAXException {

                    contenido = new String(ch, start, length);
                }

                // Llamado al encontrar un fin de etiqueta: '>'
                public void endElement(String uri, String localName, String qName)
                        throws SAXException {

                }
            };

            // Cuerpo de la función: trata de analizar el fichero deseado
            // Llamará a startElement(), endElement() y character()
            saxParser.parse("countries.xml", manejadorEventos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
 
 
public class BuscarPaisesSAX {

    public static void main(String[] args) {
        SaxReader ficheroXml = new SaxReader();
        ficheroXml.procesarXml();
        List<String> mylist = ficheroXml.getList();
        Scanner teclado = new Scanner(System.in);

        String nombrePais = "";
        Collections.sort(mylist);

        do {
            System.out.print("Indica un fragmento de los paises a buscar: ");
            nombrePais = teclado.nextLine().toLowerCase();

            if (!nombrePais.equals("")) {
                for (String nombre : mylist) {
                    if (nombre.toLowerCase().contains(nombrePais)) {
                        System.out.println(
                                "Nombre oficial: " + nombre + " ");
                    }
                }
            }
        } while (!nombrePais.equals(""));

    }

}
