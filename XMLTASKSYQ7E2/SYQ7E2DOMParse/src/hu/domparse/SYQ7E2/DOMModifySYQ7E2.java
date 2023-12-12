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

	// Adatok Beolvasása
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
		// Változtassuk meg a csapatok nevét egy szám értékre az anonimitás érdekében
		// --...--
		NodeList csapatok = document.getElementsByTagName("csapat");
		for (int i = 0; i < csapatok.getLength(); i++) {
			Element csapat = (Element) csapatok.item(i);
			csapat.setAttribute("Cnév","Team: " + i);
		}
		// --...--

		// #2
		// Változtassuk meg a z egyik katonánk életkorát
		// --...--
		NodeList katonák = document.getElementsByTagName("katona");
		Element katona = (Element) katonák.item(0);
		Node szuletesiDatumNode = katona.getElementsByTagName("születésiDátum").item(0);
		szuletesiDatumNode.setTextContent("1999.12.01");
		// --...--

		// #3
		// Változtassuk meg az elsõ felszerelés azonosítóját a használó kérésére
		// --...--
		NodeList felszerelések = document.getElementsByTagName("felszerelés");
		Element felszerelés = (Element) felszerelések.item(0);
		felszerelés.setAttribute("FID", "12");
		// --...--

		// #4
		// Az utolsó operátor személyazonossága megváltozott frissítsük az információját
		// --...--
		NodeList operátorok = document.getElementsByTagName("operátor");
		Element operátor = (Element) operátorok.item(operátorok.getLength() - 1);
		operátor.getElementsByTagName("név").item(0).setNodeValue("Kevin");
		// --...--


		// #5
		// Az utolsó csapat név váltást kért a legutóbbi küldetés után
		// Frissítsük az infromációkat
		// --...--
		Element csapat = (Element) csapatok.item(csapatok.getLength() - 1);
		csapat.setAttribute("Cnév", "Death Squad");
		Node küldetések = csapat.getElementsByTagName("küldetésekSzáma").item(0);
		küldetések.setTextContent("200");
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
