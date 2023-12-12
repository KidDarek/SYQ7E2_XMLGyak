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

	// Adatok Beolvas�sa
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
		// N�zz�k meg mennyi az eddig elv�gzett k�ldet�sek sz�ma
		// --...--
		NodeList csapatok = document.getElementsByTagName("csapat");
		int �ssz = 0;
		for (int i = 0; i < csapatok.getLength(); i++) {
			Element csapat = (Element) csapatok.item(i);
			�ssz += Integer.parseInt(csapat.getElementsByTagName("k�ldet�sekSz�ma").item(0).getTextContent());
		}
		System.out.println("Elv�gzett k�ldet�sek sz�ma: " + �ssz);
		System.out.println("--------");
		// --...--


		// #2
		// Írjuk ki az Foxtrott csapat Oper�tor�t
		// --...--
		System.out.println("Foxtrott Oper�tora: ");
		NodeList oper�torok = document.getElementsByTagName("oper�tor");
		for (int i = 0; i < oper�torok.getLength(); i++) {
			Element oper�tor = (Element) oper�torok.item(i);
			if (oper�tor.getAttribute("Cn�v").contains( "Foxtrott")) {
				System.out.println(oper�tor.getElementsByTagName("n�v").item(0).getTextContent());
			}
		}
		System.out.println("--------");
		// --...--


		// #3
		// Írjuk ki az amerikai katon�k nev�t
		// --...--
		System.out.println("Amerikaiak:");
		NodeList katon�k = document.getElementsByTagName("katona");
		for (int i = 0; i < katon�k.getLength(); i++) {
			Element katona = (Element) katon�k.item(i);
			Element orszag = (Element) katona.getElementsByTagName("sz�rmaz�siHely").item(0);
			if (orszag.getTextContent().contains("USA")) {
				System.out.println(katona.getElementsByTagName("n�v").item(0).getTextContent());
			}
		}
		System.out.println("--------");
		// --...--


		// #4
		// Sz�moljuk az meg az �sszes haszn�lt j�rm�vet
		// --...--
		NodeList j�rm�vek = document.getElementsByTagName("j�rm�");
		int darab = 0;
		for (int i = 0; i < j�rm�vek.getLength(); i++) {
			darab++;
		}
		System.out.println("A j�rm�vek sz�ma: " + darab);
		System.out.println("--------");
		// --...--
		

		// #5
		// Melyik a legfiatalabb csapat keletkez�si �ve
		// --...--
		System.out.println("Legfiatalabb csapat");
		Element csapatfirst = (Element) csapatok.item(0);
		Element els��v = (Element) csapatfirst.getElementsByTagName("alap�t�s�ve").item(0);
		int �v = Integer.parseInt(els��v.getTextContent());
		for (int i = 0; i < csapatok.getLength(); i++) {
			Element csapat = (Element) csapatok.item(i);
			Element l�trehoz�s = (Element) csapat.getElementsByTagName("alap�t�s�ve").item(0);
			if (Integer.parseInt(l�trehoz�s.getTextContent()) > �v) {
				System.out.println(csapat.getAttribute("Cn�v"));
			}
		}
		// --...--
	}
}
