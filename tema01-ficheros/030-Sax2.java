import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Yahir Peñarrubia Farach
 * Curso 2ºDAM Presencial
 */
class ReadXml extends DefaultHandler{
 
public void procesarXml(){
    try {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        DefaultHandler manejadorEventos = new DefaultHandler(){

            String etiquetaActual = "";
            String contenido = "";
            boolean analizandoNombre = false, 
                analizandoApellido = false;

            // Método que se llama al encontrar inicio de etiqueta: '<'
            public void startElement(String uri, String localName,
                    String qName, Attributes attributes) 
                    throws SAXException {
                
                etiquetaActual = qName;
                if (etiquetaActual == "nombre") {
                    analizandoNombre = true;
                }
                
                if (etiquetaActual == "apellido"){
                    analizandoApellido = true;
                }
            }

            // Obtiene los datos entre '<>' y '</>'
            public void characters(char ch[], int start, int length)
                    throws SAXException {
                
                if (analizandoNombre) {
                    System.out.print("Nombre: " + 
                        new String(ch, start, length));
                    analizandoNombre = false;
                }
                
                if (analizandoApellido) {
                    System.out.println(" "+new String(ch, start, length));
                    analizandoApellido = false;
                }
            }

            // Llamado al encontrar un fin de etiqueta: '>'
            public void endElement(String uri, String localName, String qName)
                    throws SAXException {
            }
        };

        // Cuerpo de la función: trata de analizar el fichero deseado
        // Llamará a startElement(), endElement() y character() 
        saxParser.parse("alumnos.xml", manejadorEventos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



public class Sax2 {
    public static void main(String args[]) {
        ReadXml ficheroXml = new ReadXml();
        ficheroXml.procesarXml();
    }
}
