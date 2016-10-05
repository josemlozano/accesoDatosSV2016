import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class CountriesFinder {
    public static void main(String[] args){
        Scanner teclado = new Scanner(System.in);
        try {
            File inputFile = new File("countries.xml");
            DocumentBuilderFactory dbFactory 
                = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList listaNodos = doc.getElementsByTagName("country");
            ArrayList<String> listaNombresOficiales = new ArrayList<>();
            
            for (int indice = 0; indice < listaNodos.getLength(); indice++) {
                Node nNode = listaNodos.item(indice);   
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    listaNombresOficiales.add(eElement.
                        getAttribute("name").toString().split(",")[1]);
                }
            }

            Collections.sort(listaNombresOficiales);
            String nombrePais = "";
            
            do {
                System.out.print("Indica un fragmento de los paises a buscar: ");
                nombrePais = teclado.nextLine().toLowerCase();
                
                if(!nombrePais.equals("")) {
                    for (String nombre : listaNombresOficiales) {
                        if(nombre.toLowerCase().contains(nombrePais)) {                           
                            System.out.println(
                                "Nombre oficial: "+ nombre + " ");
                        }
                    }
                }
            }
            while(!nombrePais.equals(""));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
