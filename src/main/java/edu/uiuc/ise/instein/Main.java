package edu.uiuc.ise.instein;

import edu.uiuc.ise.instein.model.Mutants;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Main {
    public static void main(String[] args) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        XmlHandler handler = new XmlHandler();

        saxParser.parse("xml_result_1.2/Chart/2.xml", handler);

        Mutants result = handler.getResult();
    }
}
