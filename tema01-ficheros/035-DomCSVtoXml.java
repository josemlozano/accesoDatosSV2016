import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.File;
import java.io.FileNotFoundException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Ruben Blanco
 */
public class DomCSVtoXml {

    public static void main(String[] args) {
        try {
                
                BufferedReader ficheroEntrada = new BufferedReader(
                    new FileReader(new File("states.csv")));

                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                // root elements
                Document doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("states");
                doc.appendChild(rootElement);

                String linea = ficheroEntrada.readLine();
                String separator = "\"";
                String [] words;
            
                while (linea != null) {   
                    
                    // state elements
                    Element oneState = doc.createElement("state");
                    rootElement.appendChild(oneState);

                    words = linea.split(separator);
                    
                    // name elements
                    Element firstname = doc.createElement("name");
                    firstname.appendChild(doc.createTextNode(words[1]));
                    oneState.appendChild(firstname);
                    
                    // code elements
                    Element codeState = doc.createElement("code");
                    codeState.appendChild(doc.createTextNode(words[3]));
                    oneState.appendChild(codeState);
                
                    linea = ficheroEntrada.readLine();
                }
                ficheroEntrada.close();
 
                // write the content into xml file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File("states.xml"));

                // Output to console for testing
                // StreamResult result = new StreamResult(System.out);

                transformer.transform(source, result);

                System.out.println("File saved!");

            } 
            catch (ParserConfigurationException pce) {
                    pce.printStackTrace();
            } 
            catch (TransformerException tfe) {
                    tfe.printStackTrace();
            }
        
            catch( FileNotFoundException e){
                System.out.println("File Not Found:");
                e.printStackTrace();
            }
        
            catch( IOException e){
                System.out.println("Problems:");
                e.printStackTrace();
            }
    }
}
