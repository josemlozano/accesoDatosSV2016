package csv_genarator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
/**
 *
 * @author Adrian Navarro
 */
public class TabbedTxtToXMLDOM2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        List<String> misLineas = new ArrayList<>();
          
          try {
            BufferedReader ficheroEntrada = new BufferedReader(
                  new FileReader(new File("surnames.txt")));

            String linea = ficheroEntrada.readLine();
            while (linea != null) {    
                  misLineas.add(linea);
              linea = ficheroEntrada.readLine();
            }
            ficheroEntrada.close();
          }
          catch (FileNotFoundException e){
              System.out.println("Fichero no encontrado");
          }
          catch (IOException e)
          {
              System.out.println("Error de lectura.");
          }
          catch (Exception e) {
              System.out.println("Error");
          }
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, "xml", null);
            Element raiz = document.createElement("surnames");  // creamos el elemento raiz
            
                for (int i = 0; i < misLineas.size(); i++) {
                    String[] linea =  misLineas.get(i).split("\t");
                    Element elemento = document.createElement("surname"); //creamos un nuevo elemento
                    elemento.setAttribute("id", linea[0]);
                    elemento.setAttribute("amount", linea[1]);
                    document.setXmlVersion("1.0"); // asignamos la version de nuestro XML
                    document.getDocumentElement().appendChild(raiz);  //pegamos la raiz al documento
                    raiz.appendChild(elemento); //pegamos el elemento hijo a la raiz
                }

            Source source = new DOMSource(document);
            Result result = new StreamResult(new java.io.File("resultado.xml")); //nombre del archivo
            Result console= new StreamResult(System.out);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            transformer.transform(source, console);

        }catch(Exception e){
            System.err.println("Error: "+e);
        }
    }
    
}
