package pl.alcolabs.currents;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import org.xml.sax.SAXException;

public class XmlParser {

    public static ArrayList<String> listaNazwa = new ArrayList<String>();
    public static ArrayList<String> listaPrzelicznik = new ArrayList<String>();
    public static ArrayList<String> listaKod = new ArrayList<String>();
    public static ArrayList<String> listaKurs = new ArrayList<String>();


    public static void main(String argv[]) throws ParserConfigurationException,IOException {

        try {

            URL url = new URL("http://www.nbp.pl/kursy/xml/lasta.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(url.openStream());

            //System.out.println("Root element : "+doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("pozycja");
            for (int i = 0; i < nList.getLength(); i++) {
                Node n = nList.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) n;
                    //System.out.println("Waluta        : "+ getTagValue("nazwa_waluty", e));
                    //System.out.println("Przelicznik   : "+ getTagValue("przelicznik", e));
                    //System.out.println("kod waluty    : "+ getTagValue("kod_waluty", e));
                    //System.out.println("kurs          : "+ getTagValue("kurs_sredni", e));
                    listaNazwa.add(getTagValue("nazwa_waluty", e));
                    listaPrzelicznik.add(getTagValue("przelicznik", e));
                    listaKod.add(getTagValue("kod_waluty", e));
                    listaKurs.add(getTagValue("kurs_sredni", e));
                }
            }
            System.out.println(listaNazwa);
            System.out.println(listaPrzelicznik);
            System.out.println(listaKod);
            System.out.println(listaKurs);

        }
        catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    private static String getTagValue(String s, Element e) {

        NodeList nl = e.getElementsByTagName(s).item(0).getChildNodes();
        Node n = (Node) nl.item(0);
        return n.getNodeValue();
    }
}

