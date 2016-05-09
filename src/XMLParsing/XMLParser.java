package XMLParsing;

import Logging.LumberJack;
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

    private static NodeList _nodeListCache;

    //Constructor
    private XMLParser() {}

    //Public Methods
    public static Document CreateDocument(String filePath)
    {
        try
        {
            File textFile = new File(filePath);
            DocumentBuilder dBuilder = _dFactory.newDocumentBuilder();
            Document d = dBuilder.parse(textFile);
            d.getDocumentElement().normalize();

            return d;
        }
        catch(Exception e)
        {
            LumberJack.LogException("Failed to initialize document.", e);
            return null;
        }
    }

    public static String ParseStringPathContents(Document d, String xPath)
    {
        return ParseStringPathContents(d, 0, xPath);
    }

    public static String ParseStringPathContents(Document d, int i, String xPath)
    {
        NodeList node = EvaluateXPath(d, xPath);
        return node.item(i).getTextContent();
    }

    public static int ParseIntPathContents(Document d, String xPath)
    {
        return ParseIntPathContents(d, 0, xPath);
    }

    public static int ParseIntPathContents(Document d, int i, String xPath)
    {
        NodeList node = EvaluateXPath(d, xPath);
        String text = node.item(i).getTextContent();
        return Integer.parseInt(text);
    }

    public static double ParseDoublePathContents(Document d, String xPath)
    {
        return ParseDoublePathContents(d, 0, xPath);
    }

    public static double ParseDoublePathContents(Document d, int i, String xPath)
    {
        NodeList node = EvaluateXPath(d, xPath);
        String text = node.item(i).getTextContent();
        return Double.parseDouble(text);
    }

    public static int GetNodeListLength(Document d, String xPath)
    {
        NodeList list = EvaluateXPath(d, xPath);

        return list == null ? 0 : list.getLength();
    }

    //Private Methods
    private static NodeList EvaluateXPath(Document d, String xPath)
    {
        try
        {
            XPathExpression expr = _xPathParser.compile(xPath);
            NodeList node = (NodeList) expr.evaluate(d, XPathConstants.NODESET);
            return node;
        }
        catch(Exception e)
        {
            LumberJack.LogException("Failed to compile xpath.", e);
            return null;
        }
    }

}
