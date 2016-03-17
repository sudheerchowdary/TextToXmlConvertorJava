package com.sudheer.convertor;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created with IntelliJ IDEA.
 * User: Sudheer Chowdary
 * Date: 3/16/16
 * Time: 12:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class TextToXmlConverter {


    static BufferedReader in = null;
    static StreamResult out = null;

    static Document xmlDoc;
    static Element root;

    final static String textFilePath = "C:\\Users\\Sudheer\\Desktop\\samplefile.txt";
    final static String xmlFilePath = "C:\\Users\\Sudheer Chowdary\\Desktop\\data.xml";

    public static void main(String args[]) {
        convertTextToXml();
    }

    public static void convertTextToXml() {
        try {
            in = new BufferedReader(new FileReader(textFilePath));
            out = new StreamResult(xmlFilePath);
            initXML();
            String str;
            while ((str = in.readLine()) != null) {
                process(str);
            }
            in.close();
            writeXML();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void initXML() throws ParserConfigurationException {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final DOMImplementation impl = builder.getDOMImplementation();

        xmlDoc = impl.createDocument(null, "wclist", null);
        root = xmlDoc.getDocumentElement();
    }

    public static void process(final String line) {

        //Delimiter '|'
        final String[] elements = line.split("\\|");
        final Element e0 = xmlDoc.createElement("wc");

        final Element e1 = xmlDoc.createElement("key");
        final Node n1 = xmlDoc.createTextNode(elements[0]);
        e1.appendChild(n1);

        final Element e2 = xmlDoc.createElement("ONTPROJECTID");
        final Node n2 = xmlDoc.createTextNode(elements[1]);
        e2.appendChild(n2);

        final Element e3 = xmlDoc.createElement("PROJECTID");
        final Node n3 = xmlDoc.createTextNode(elements[2]);
        e3.appendChild(n3);

        final Element e4 = xmlDoc.createElement("GEOCD");
        final Node n4 = xmlDoc.createTextNode(elements[3]);
        e4.appendChild(n4);

        final Element e5 = xmlDoc.createElement("VMBLE_REGION");
        final Node n5 = xmlDoc.createTextNode(elements[4]);
        e5.appendChild(n5);

        final Element e6 = xmlDoc.createElement("STATE");
        final Node n6 = xmlDoc.createTextNode(elements[5]);
        e6.appendChild(n6);

        final Element e7 = xmlDoc.createElement("REGION");
        final Node n7 = xmlDoc.createTextNode(elements[6]);
        e7.appendChild(n7);


        e0.appendChild(e1);
        e0.appendChild(e2);
        e0.appendChild(e3);
        e0.appendChild(e4);
        e0.appendChild(e5);
        e0.appendChild(e6);
        e0.appendChild(e7);
        root.appendChild(e0);
    }


    public static void writeXML() throws TransformerException {
        final DOMSource domSource = new DOMSource(xmlDoc);
        final TransformerFactory tf = TransformerFactory.newInstance();
        final Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(domSource, out);
    }
}
