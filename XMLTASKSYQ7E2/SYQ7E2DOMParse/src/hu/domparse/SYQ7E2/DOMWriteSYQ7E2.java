package hu.domparse.SYQ7E2;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class DOMWriteSYQ7E2 {
	
	public static void main(String[] args){
		WriteElementsToFileAndConsole();
	}
    public static void WriteElementsToFileAndConsole() {
        try {
            // Előkészítjük a dokumentumot
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            // Root Element létrehozása
            Element rootElement = doc.createElement("XMLTaskSYQ7E2");
            rootElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            rootElement.setAttribute("xsi:noNamespaceSchemaLocation", "XMLSchemaSYQ7E2.xsd");
            doc.appendChild(rootElement);
            // Csapatok létrehozása
            addCsapat(doc, rootElement, "Alpha", "1998.05.09","124","Project Black Sheep",
                    Arrays.asList("David Mercer", "Ghost"));
            addCsapat(doc, rootElement, "Delta", "2012.01.29","76","Encrypted",
                    Arrays.asList("Eric Blac","George Force"));
            addCsapat(doc, rootElement, "Foxtrott", "2020.11.12","532","Encrypted",
                    Arrays.asList("Sarah Ryder", "Scott Ryder","Vetra Nyx"));
            // Katonák létrehozása
            addKatona(doc, rootElement, "Alpha", "01", "2000.09.01", "a Fey", "Beginner", "England");
            addKatona(doc, rootElement, "Alpha", "02", "2001.09.01", "s Fey", "pro", "England");
            addKatona(doc, rootElement, "Alpha", "03", "2002.09.01", "d Fey", "Beginner", "USA");
            addKatona(doc, rootElement, "Alpha", "04", "2003.09.01", "f Fey", "Beginner", "USA");
            addKatona(doc, rootElement, "Delta", "05", "2004.09.01", "g Fey", "Beginner", "England");
            addKatona(doc, rootElement, "Delta", "06", "2005.09.01", "h Fey", "pro", "England");
            addKatona(doc, rootElement, "Delta", "07", "2006.09.01", "j Fey", "Beginner", "USA");
            addKatona(doc, rootElement, "Delta", "08", "2007.09.01", "k Fey", "Beginner", "England");
            addKatona(doc, rootElement, "Foxtrott", "09", "2001.02.01", "l Fey", "Beginner", "England");
            addKatona(doc, rootElement, "Foxtrott", "10", "2009.09.01", "q Fey", "pro", "England");
            addKatona(doc, rootElement, "Foxtrott", "11", "2008.09.01", "w Fey", "pro", "Spain");
            addKatona(doc, rootElement, "Foxtrott", "12", "1990.09.01", "e Fey", "pro", "England");
            
            // felszerelések létrehozása
            addFelszerelés(doc, rootElement, "01", "01", "AR", "SMG", "RD", "Heavy");
            addFelszerelés(doc, rootElement, "02", "02", "AR", "pistol", "RD", "Heavy");
            addFelszerelés(doc, rootElement, "03", "03", "SNPR", "SMG", "EXP", "Light");
            addFelszerelés(doc, rootElement, "04", "04", "AR", "pistol", "RD", "Heavy");
            addFelszerelés(doc, rootElement, "05", "05", "AR", "SMG", "RD", "Heavy");
            addFelszerelés(doc, rootElement, "06", "06", "SNPR", "pistol", "RD", "Heavy");
            addFelszerelés(doc, rootElement, "07", "07", "AR", "pistol", "RD", "Light");
            addFelszerelés(doc, rootElement, "08", "08", "AR", "pistol", "EXP", "Heavy");
            addFelszerelés(doc, rootElement, "09", "09", "SNPR", "pistol", "RD", "Heavy");
            addFelszerelés(doc, rootElement, "10", "10", "SNPR", "SMG", "EXP", "Light");
            addFelszerelés(doc, rootElement, "11", "11", "AR", "SMG", "RD", "Heavy");
            addFelszerelés(doc, rootElement, "12", "12", "AR", "pistol", "EXP", "Heavy");
            
            // Operátor létrehozása
            addOperátor(doc, rootElement, "Alpha", "01", "2000.09.01", "Sam", "2000.09.02");
            addOperátor(doc, rootElement, "Delta", "02", "2001.09.01", "Sam2", "2004.10.02");
            addOperátor(doc, rootElement, "Alpha", "03", "2002.09.01", "Sam3", "2005.09.02");
            addOperátor(doc, rootElement, "Foxtrott", "04", "2003.09.01", "Sam4", "2006.09.02");
            // jármű létrehozása
            addJármű(doc, rootElement, "Alpha", "01", "Jeep", "Land", "Lvl 5");
            addJármű(doc, rootElement, "Alpha", "02", "Ship", "Water", "Lvl 2");
            addJármű(doc, rootElement, "Delta", "03", "Jeep", "Land", "Lvl 3");
            addJármű(doc, rootElement, "Delta", "04", "Ship", "Water", "Lvl 5");
            addJármű(doc, rootElement, "Delta", "05", "Jet", "Air", "Lvl 4");
            addJármű(doc, rootElement, "Foxtrott", "06", "Ship", "Water", "Lvl 1");
            addJármű(doc, rootElement, "Foxtrott", "07", "Jeep", "Land", "Lvl 3");



            // Dokumentum mentése
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{https://xml.apache.org/xslt}indent-amount", "2");

            printDocument(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addCsapat(Document doc, Element rootElement, String Cnév, String alapításÉve, String küldetésekSzáma, String jelenlegiküldetés,
            List<String> alapítótagok) {
        Element csapat = doc.createElement("csapat");
        csapat.setAttribute("Cnév", Cnév);

        Element alapításÉveElement = createElement(doc, "alapításÉve", alapításÉve);
        Element küldetésekSzámaElement = createElement(doc, "küldetésekSzáma", küldetésekSzáma);
        Element jelenlegiküldetésElement = createElement(doc, "jelenlegiküldetés", jelenlegiküldetés);
        csapat.appendChild(alapításÉveElement);
        csapat.appendChild(küldetésekSzámaElement);
        csapat.appendChild(jelenlegiküldetésElement);

        Element alapítótagokElement = doc.createElement("alapítótagok");
        for (String alapítótag : alapítótagok) {
            Element alapítótagElement = createElement(doc, "alapítótag", alapítótag);
            alapítótagokElement.appendChild(alapítótagElement);
        }
        csapat.appendChild(alapítótagokElement);

        rootElement.appendChild(csapat);
    }

    public static void addKatona(Document doc, Element rootElement, String Cnév, String KID, String születésiDátum, String név, String munkaTapasztalat, String származásiHely) {
        Element katona = doc.createElement("katona");
        katona.setAttribute("Cnév", Cnév);
        katona.setAttribute("KID", KID);

        Element születésiDátumElement = createElement(doc, "születésiDátum", születésiDátum);
        Element névElement = createElement(doc, "név", név);
        Element munkaTapasztalatElement = createElement(doc, "munkaTapasztalat", munkaTapasztalat);
        Element származásiHelylement = createElement(doc, "származásiHely", származásiHely);
        katona.appendChild(születésiDátumElement);
        katona.appendChild(névElement);
        katona.appendChild(munkaTapasztalatElement);
        katona.appendChild(származásiHelylement);

        rootElement.appendChild(katona);
    }

     public static void addFelszerelés(Document doc, Element rootElement, String KID, String FID, String fő, String mellék, String kiegészít, String páncélzat) {
        Element felsz = doc.createElement("felszerelés");
        felsz.setAttribute("KID", KID);
        felsz.setAttribute("FID", FID);

        Element fegyverElement = doc.createElement("fegyver");
        Element főElement = createElement(doc, "fő", fő);
        Element mellékElement = createElement(doc, "mellék", mellék);
        fegyverElement.appendChild(főElement);
        fegyverElement.appendChild(mellékElement);
        
        felsz.appendChild(fegyverElement);

        Element kiegészítElement = createElement(doc, "kiegészít", kiegészít);
        Element páncélzatElement = createElement(doc, "páncélzat", páncélzat);
        felsz.appendChild(kiegészítElement);
        felsz.appendChild(páncélzatElement);

        rootElement.appendChild(felsz);
    }

        public static void addOperátor(Document doc, Element rootElement, String Cnév, String OID, String születésiDátum, String név, String csatlakozásDátum) {
        Element op = doc.createElement("operátor");
        op.setAttribute("Cnév", Cnév);
        op.setAttribute("OID", OID);

        Element születésiDátumElement = createElement(doc, "születésiDátum", születésiDátum);
        Element névElement = createElement(doc, "név", név);
        Element csatlakozásDátumElement = createElement(doc, "csatlakozásDátum", csatlakozásDátum);
        op.appendChild(születésiDátumElement);
        op.appendChild(névElement);
        op.appendChild(csatlakozásDátumElement);

        rootElement.appendChild(op);
    }

        public static void addJármű(Document doc, Element rootElement, String Cnév, String JID, String név, String típus, String páncélozottság) {
        Element jarmu = doc.createElement("jármű");
        jarmu.setAttribute("Cnév", Cnév);
        jarmu.setAttribute("JID", JID);

        Element névElement = createElement(doc, "név", név);
        Element típusElement = createElement(doc, "típus", típus);
        Element páncélozottságElement = createElement(doc, "páncélozottság", páncélozottság);
        jarmu.appendChild(névElement);
        jarmu.appendChild(típusElement);
        jarmu.appendChild(páncélozottságElement);

        rootElement.appendChild(jarmu);
    }
       
        public static void printDocument(Document doc) {
        try {
            File outputFile = new File("SYQ7E2_1.xml");
            PrintWriter writer = new PrintWriter(new FileWriter(outputFile, true));

            // Kiírjuk az XML fõgyökér elemét a konzolra és fájlba
			// --...--
            Element rootElement = doc.getDocumentElement();
            String rootName = rootElement.getTagName();
            StringJoiner rootAttributes = new StringJoiner(" ");
            NamedNodeMap rootAttributeMap = rootElement.getAttributes();

            for (int i = 0; i < rootAttributeMap.getLength(); i++) {
                Node attribute = rootAttributeMap.item(i);
                rootAttributes.add(attribute.getNodeName() + "=\"" + attribute.getNodeValue() + "\"");
            }

            System.out.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");

            System.out.print("<" + rootName + " " + rootAttributes.toString() + ">\n");
            writer.print("<" + rootName + " " + rootAttributes.toString() + ">\n");

            NodeList csapatList = doc.getElementsByTagName("csapat");
            NodeList katonaList = doc.getElementsByTagName("katona");
            NodeList felszerelésList = doc.getElementsByTagName("felszerelés");
            NodeList operátorList = doc.getElementsByTagName("operátor");
            NodeList jármûList = doc.getElementsByTagName("jármû");

            // Kiírjuk az XML-t a konzolra megtartva az eredeti formázást
		    // --...--
            printNodeList(csapatList, writer);
            System.out.println("");
            writer.println("");
            printNodeList(katonaList, writer);
            System.out.println("");
            writer.println("");
            printNodeList(felszerelésList, writer);
            System.out.println("");
            writer.println("");
            printNodeList(operátorList, writer);
            System.out.println("");
            writer.println("");
            printNodeList(jármûList, writer);

            // Zárjuk le az XML gyökér elemét
			// --...--
            System.out.println("</" + rootName + ">");
            writer.append("</" + rootName + ">");

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printNodeList(NodeList nodeList, PrintWriter writer) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            printNode(node, 1, writer);
            System.out.println("");
            writer.println("");
        }
    }

    public static void printNode(Node node, int indent, PrintWriter writer) {
        // Ha az elem típusa ELEMENT_NODE, akkor kiírjuk az elem nevét és attribútumait
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            String nodeName = element.getTagName();
            StringJoiner attributes = new StringJoiner(" ");
            NamedNodeMap attributeMap = element.getAttributes();
            // Kiírjuk az elem nevét és attribútumait
            for (int i = 0; i < attributeMap.getLength(); i++) {
                Node attribute = attributeMap.item(i);
                attributes.add(attribute.getNodeName() + "=\"" + attribute.getNodeValue() + "\"");
            }

            // Kiírjuk az elem nevét és attribútumait
            System.out.print(getIndentString(indent));
            System.out.print("<" + nodeName + " " + attributes.toString() + ">");

            writer.print(getIndentString(indent));
            writer.print("<" + nodeName + " " + attributes.toString() + ">");

            NodeList children = element.getChildNodes();
            if (children.getLength() == 1 && children.item(0).getNodeType() == Node.TEXT_NODE) {
                System.out.print(children.item(0).getNodeValue());
                writer.print(children.item(0).getNodeValue());
            } else {
                System.out.println();
                writer.println();
                for (int i = 0; i < children.getLength(); i++) {
                    printNode(children.item(i), indent + 1, writer);
                }
                System.out.print(getIndentString(indent));
                writer.print(getIndentString(indent));
            }
            System.out.println("</" + nodeName + ">");
            writer.println("</" + nodeName + ">");
        }
    }

    public static Element createElement(Document doc, String name, String value) {
        Element element = doc.createElement(name);
        element.appendChild(doc.createTextNode(value));
        return element;
    }

    public static String getIndentString(int indent) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            sb.append("  ");
        }
        return sb.toString();
    }
}
