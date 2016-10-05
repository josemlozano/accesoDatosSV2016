import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

/**
 * @author chao
 */
public class CountriesFinder {

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String letra;
        List<String> listaPaises = new ArrayList<>();
        
        try {
            File inputFile = new File("countries.xml");
            DocumentBuilderFactory dbFactory 
                = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("country");
            
            do{
                System.out.print("Fragmento a buscar? ");
                letra = scan.nextLine();

                if (!letra.equals("")) {
                    for (int temp = 0; temp < nList.getLength(); temp++) {
                        Node nNode = nList.item(temp);
                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement = (Element) nNode;
                            String[] names = eElement.getAttribute("name").split(",");
                            if (names[1].toLowerCase().contains(letra.toLowerCase())) {
                                listaPaises.add(names[1]);
                            }
                        }
                    }
                    Collections.sort(listaPaises);
                    for (String pais : listaPaises) {
                        System.out.println(pais);
                    }
                    listaPaises.clear();
                }
            }
            while (!letra.equals(""));
            
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
