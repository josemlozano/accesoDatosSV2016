package citiesxml;

import java.io.File;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Jose Muñoz
 */
public class CitiesXML {

    public static void main(String[] args) {

        try {
            File inputFile = new File("cities.xml");
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("ms:cities");
            String nombre = "";
            Scanner sc = new Scanner(System.in);

            do {
                System.out.print("Inicial de ciudad: ");
                nombre = sc.nextLine().toUpperCase();
                if (!nombre.equals("")) {
                    for (int temp = 0; temp < nList.getLength(); temp++) {
                        Node nNode = nList.item(temp);
                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement = (Element) nNode;
                            String nombreTemp = eElement.getElementsByTagName("ms:NAME")
                                    .item(0).getTextContent().toUpperCase();
                            if (nombreTemp.startsWith(nombre)) {
                                System.out.print("Ciudad: "
                                        + eElement.getElementsByTagName("ms:NAME")
                                        .item(0).getTextContent());
                                System.out.println(" - Población: "
                                        + eElement.getElementsByTagName("ms:POPULATION")
                                        .item(0).getTextContent());
                            }

                        }
                    }
                }
                System.out.println("");
            } while (!nombre.equals(""));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
