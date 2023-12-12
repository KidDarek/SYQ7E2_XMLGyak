package hu.domparse.SYQ7E2;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

public class DOMModifySYQ7E2 {

	// Adatok Beolvas�sa
	// --...--
	public static void main(String[] args) throws TransformerException {
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
		Modify(doc);
	}

	public static void Modify(Document document) throws TransformerException {

		// #1
		// V�ltoztassuk meg a csapatok nev�t egy sz�m �rt�kre az anonimit�s �rdek�ben
		// --...--
		NodeList csapatok = document.getElementsByTagName("csapat");
		for (int i = 0; i < csapatok.getLength(); i++) {
			Element csapat = (Element) csapatok.item(i);
			csapat.setAttribute("Cn�v","Team: " + i);
		}
		// --...--

		// #2
		// V�ltoztassuk meg a z egyik katon�nk �letkor�t
		// --...--
		NodeList katon�k = document.getElementsByTagName("katona");
		Element katona = (Element) katon�k.item(0);
		Node szuletesiDatumNode = katona.getElementsByTagName("sz�let�siD�tum").item(0);
		szuletesiDatumNode.setTextContent("1999.12.01");
		// --...--

		// #3
		// V�ltoztassuk meg az els� felszerel�s azonos�t�j�t a haszn�l� k�r�s�re
		// --...--
		NodeList felszerel�sek = document.getElementsByTagName("felszerel�s");
		Element felszerel�s = (Element) felszerel�sek.item(0);
		felszerel�s.setAttribute("FID", "12");
		// --...--

		// #4
		// Az utols� oper�tor szem�lyazonoss�ga megv�ltozott friss�ts�k az inform�ci�j�t
		// --...--
		NodeList oper�torok = document.getElementsByTagName("oper�tor");
		Element oper�tor = (Element) oper�torok.item(oper�torok.getLength() - 1);
		oper�tor.getElementsByTagName("n�v").item(0).setNodeValue("Kevin");
		// --...--


		// #5
		// Az utols� csapat n�v v�lt�st k�rt a legut�bbi k�ldet�s ut�n
		// Friss�ts�k az infrom�ci�kat
		// --...--
		Element csapat = (Element) csapatok.item(csapatok.getLength() - 1);
		csapat.setAttribute("Cn�v", "Death Squad");
		Node k�ldet�sek = csapat.getElementsByTagName("k�ldet�sekSz�ma").item(0);
		k�ldet�sek.setTextContent("200");
		// --...--

		printDocument(document);
	}

	public static void printDocument(Document document) throws TransformerException {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(document), new StreamResult(writer));
        String output = writer.getBuffer().toString();
        System.out.println(output);
    }
}
