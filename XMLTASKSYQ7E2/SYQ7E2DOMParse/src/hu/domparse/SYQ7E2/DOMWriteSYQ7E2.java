package hu.domparse.SYQ7E2;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class DOMWriteSYQ7E2 {

    public static void main(String[] args) {
		Write("src/hu/domparse/SYQ7E2/XMLSYQ7E2.xml");
	}

	public static void Write(String filePath)
	{
		try 
        {
            File inputFile = new File(filePath);
            
            //documentum elûkószõtóse
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(inputFile);
            document.getDocumentElement().normalize();

            System.out.println("Writing into the file");
            
            //a tranformer osztíllyal segõtsógóvel kószõtjök az XML filet
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            //kiirjuk az xmlt a consolera
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
            
            //kiirjuk fíjlba az xmlt
            StreamResult result = new StreamResult(new File("SYQ7E2_1.xml"));
            transformer.transform(source, result);

        } 
        catch (SAXException e) 
        {
            e.printStackTrace();
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        catch (ParserConfigurationException e) 
        {
            e.printStackTrace();
        }
        catch (TransformerException e) 
        {
            e.printStackTrace();
        }
	}
	
}
