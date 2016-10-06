import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
 * @author chao
 */
public class TabbedTxtToXMLDOM {

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(
                new File("surnames.txt")));
            String line;
            
            DocumentBuilderFactory docFactory = 
                DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            
            // root elements
            Document doc = docBuilder.newDocument();
            Element surnames = doc.createElement("surnames");
            doc.appendChild(surnames);
            
            do {
                line = reader.readLine();
                if (line != null) {
                    String[] parts = line.split("\t");
                    
                    Element surname = doc.createElement("surname");
                    surnames.appendChild(surname);
                    surname.setAttribute("amount", parts[1].replace(",", "").
                        trim());
                    surname.setAttribute("id", parts[0].toUpperCase().charAt(0) 
                        + parts[0].toLowerCase().substring(1).trim());
                }
            }
            while (line != null);
            
            reader.close();
            
            // write the content into xml file
            TransformerFactory transformerFactory = 
                TransformerFactory.newInstance();
            
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("surnames.xml"));
            transformer.transform(source, result);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        catch (IOException e) {
            System.out.println("Error when reading file.");
        }
        catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        }
        catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
        catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}
