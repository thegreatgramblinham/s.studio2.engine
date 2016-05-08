package XMLParsing;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;
import java.io.File;

public final class XMLParser
{
    //TODO will need to be instanced if we do async loading here

    //Private Constants
    private static DocumentBuilderFactory _dFactory = DocumentBuilderFactory.newInstance();
    private static XPath _xPathParser = XPathFactory.newInstance().newXPath();

    //Constructor
    private XMLParser() {}

    //Public Methods
    public static Document CreateDocument(String filePath) throws Exception
    {
        File textFile = new File(filePath);
        DocumentBuilder dBuilder = _dFactory.newDocumentBuilder();
        Document d = dBuilder.parse(textFile);
        d.getDocumentElement().normalize();

        return d;
    }

    public static String ParseStringPathContents(Document d, String xPath) throws Exception
    {
        NodeList node = EvaluateXPath(d, xPath);
        return node.item(0).getTextContent();
    }

    public static int ParseIntPathContents(Document d, String xPath) throws Exception
    {
        NodeList node = EvaluateXPath(d, xPath);
        String text = node.item(0).getTextContent();
        return Integer.parseInt(text);
    }

    public static double ParseDoublePathContents(Document d, String xPath) throws Exception
    {
        NodeList node = EvaluateXPath(d, xPath);
        String text = node.item(0).getTextContent();
        return Double.parseDouble(text);
    }

    //Private Methods
    private static NodeList EvaluateXPath(Document d, String xPath) throws Exception
    {
        XPathExpression expr = _xPathParser.compile(xPath);
        NodeList node = (NodeList)expr.evaluate(d, XPathConstants.NODESET);
        return node;
    }

}
