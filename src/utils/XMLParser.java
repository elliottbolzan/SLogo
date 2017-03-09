package utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author nikita
 * @author elliott
 * @author Robert Duvall Some code taken from class. This class is used to parse
 *         xml.
 */
public class XMLParser {

	private static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();
	private static final String path = "resources/";

	/**
	 * check whether a file is xml file or not.
	 * 
	 * @param fileName
	 *            string of file to check whether it is an xml or not
	 * @return if file is xml or not
	 */
	public static boolean isXMLFile(String fileName) {
		return fileName.substring(fileName.lastIndexOf('.') + 1).equals("xml");
	}

	/**
	 * parse xml file.
	 * 
	 * @param fileName
	 *            name of file to parse
	 * @return map of parsed fields.
	 */
	public static Map<String, String> parse(String fileName) {
		File xmlFile = new File(path + fileName + ".xml");
		return populateMap(getRootElement(xmlFile));
	}

	/**
	 * write fields out to xml file.
	 * 
	 * @param data
	 *            map to be written
	 */
	public static void write(Map<String, String> data) {
		try {
			DOCUMENT_BUILDER.reset();
			Document doc = DOCUMENT_BUILDER.newDocument();
			Element rootElement = doc.createElement("settings");
			doc.appendChild(rootElement);
			for (String key : data.keySet()) {
				Element nickname = doc.createElement(key);
				nickname.appendChild(doc.createTextNode(data.get(key)));
				rootElement.appendChild(nickname);
			}
			writeToFile(doc);
		} catch (TransformerException exception) {
			exception.printStackTrace();
		}
	}

	private static void writeToFile(Document doc) throws TransformerFactoryConfigurationError, TransformerException {
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(createFile(path));
		transformer.transform(source, result);
	}

	private static Map<String, String> populateMap(Element root) {
		HashMap<String, String> map = new HashMap<String, String>();
		NodeList nList = root.getChildNodes();
		for (int i = 0; i < nList.getLength(); i++) {
			Node temp = nList.item(i);
			if (temp.getNodeType() == Node.ELEMENT_NODE) {
				map.put(temp.getNodeName(), ((Element) temp).getTextContent());
			} 
		}
		return map;
	}

	private static Element getRootElement(File xmlFile) {
		try {
			DOCUMENT_BUILDER.reset();
			Document xmlDocument = DOCUMENT_BUILDER.parse(xmlFile);
			return xmlDocument.getDocumentElement();
		} catch (SAXException | IOException e) {
			throw new XMLException(e);
		}
	}

	private static DocumentBuilder getDocumentBuilder() {
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new XMLException(e);
		}
	}

	private static File createFile(String folder) {
		String fileName = folder + "Configuration%02d.xml";
		File file = null;
		for (int i = 1; i < 100; i++) {
			file = new File(String.format(fileName, i));
			if (!file.exists()) {
				return file;
			}
		}
		return null;
	}

}
