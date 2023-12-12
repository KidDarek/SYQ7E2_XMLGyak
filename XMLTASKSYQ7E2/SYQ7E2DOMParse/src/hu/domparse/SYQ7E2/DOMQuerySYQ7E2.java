package hu.domparse.SYQ7E2;


import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMQuerySYQ7E2 {

	// Adatok Beolvasása
	// --...--
	public static void main(String[] args) {
		Document doc = null;
		try {
		File inputFile = new File("src/hu/domparse/SYQ7E2/XMLSYQ7E2.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
    } catch (ParserConfigurationException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } catch (SAXException e) {
        e.printStackTrace();
    }
		Query(doc);
	}

	public static void Query(Document document) {


		// #1
		// Nézzük meg mennyi az eddig elvégzett küldetések száma
		// --...--
		NodeList csapatok = document.getElementsByTagName("csapat");
		int össz = 0;
		for (int i = 0; i < csapatok.getLength(); i++) {
			Element csapat = (Element) csapatok.item(i);
			össz += Integer.parseInt(csapat.getElementsByTagName("küldetésekSzáma").item(0).getTextContent());
		}
		System.out.println("Elvégzett küldetések száma: " + össz);
		System.out.println("--------");
		// --...--


		// #2
		// Ãrjuk ki az Foxtrott csapat Operátorát
		// --...--
		System.out.println("Foxtrott Operátora: ");
		NodeList operátorok = document.getElementsByTagName("operátor");
		for (int i = 0; i < operátorok.getLength(); i++) {
			Element operátor = (Element) operátorok.item(i);
			if (operátor.getAttribute("Cnév").contains( "Foxtrott")) {
				System.out.println(operátor.getElementsByTagName("név").item(0).getTextContent());
			}
		}
		System.out.println("--------");
		// --...--


		// #3
		// Ãrjuk ki az amerikai katonák nevét
		// --...--
		System.out.println("Amerikaiak:");
		NodeList katonák = document.getElementsByTagName("katona");
		for (int i = 0; i < katonák.getLength(); i++) {
			Element katona = (Element) katonák.item(i);
			Element orszag = (Element) katona.getElementsByTagName("származásiHely").item(0);
			if (orszag.getTextContent().contains("USA")) {
				System.out.println(katona.getElementsByTagName("név").item(0).getTextContent());
			}
		}
		System.out.println("--------");
		// --...--


		// #4
		// Számoljuk az meg az összes használt jármûvet
		// --...--
		NodeList jármûvek = document.getElementsByTagName("jármû");
		int darab = 0;
		for (int i = 0; i < jármûvek.getLength(); i++) {
			darab++;
		}
		System.out.println("A jármûvek száma: " + darab);
		System.out.println("--------");
		// --...--
		

		// #5
		// Melyik a legfiatalabb csapat keletkezési éve
		// --...--
		System.out.println("Legfiatalabb csapat");
		Element csapatfirst = (Element) csapatok.item(0);
		Element elsõév = (Element) csapatfirst.getElementsByTagName("alapításÉve").item(0);
		int év = Integer.parseInt(elsõév.getTextContent());
		for (int i = 0; i < csapatok.getLength(); i++) {
			Element csapat = (Element) csapatok.item(i);
			Element létrehozás = (Element) csapat.getElementsByTagName("alapításÉve").item(0);
			if (Integer.parseInt(létrehozás.getTextContent()) > év) {
				System.out.println(csapat.getAttribute("Cnév"));
			}
		}
		// --...--
	}
}
