package XMLParsing;

import Logging.LumberJack;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;
import java.io.File;

public class XMLParser
{
    //Private Constants
    private DocumentBuilderFactory _dFactory = DocumentBuilderFactory.newInstance();
    private XPath _xPathParser = XPathFactory.newInstance().newXPath();

    private Document _doc;

    //Constructor
    public XMLParser(String filePath) throws Exception
    {
        _doc = CreateDocument(filePath);
    }

    //Public Methods
    public NodeList OpenNodeList(String xPath) throws Exception
    {
        return EvaluateXPath(_doc, xPath);
    }

    //Public Static Methods
    public static String ParseStringPathContents(NodeList list, int i)
    {
        return list.item(i).getTextContent();
    }

    public static int ParseIntPathContents(NodeList list, int i)
    {
        String text = list.item(i).getTextContent();
        return Integer.parseInt(text);
    }

    public double ParseDoublePathContents(NodeList list, int i)
    {
        String text = list.item(i).getTextContent();
        return Double.parseDouble(text);
    }

    //Private Methods
    private Document CreateDocument(String filePath) throws Exception
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
            throw e;
        }
    }

    private NodeList EvaluateXPath(Document d, String xPath) throws Exception
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
            throw e;
        }
    }

}
