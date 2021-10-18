package edu.uiuc.ise.instein;

import edu.uiuc.ise.instein.model.Mutants;
import edu.uiuc.ise.instein.model.Mutant;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;

public class XmlHandler extends DefaultHandler {
    private Mutants mutants;
    private Mutant mutant;
    private StringBuilder elementValue;


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // Todo: why
        if (elementValue == null) {
            elementValue = new StringBuilder();
        } else {
            elementValue.append(ch, start, length);
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName){
            case "mutation":
                mutant = new Mutant();
                break;
            case "testsExecutionTime":
                mutant.setTestsExecutionTime(new HashMap<>());
                break;
        }
        if (elementValue != null){
            elementValue.setLength(0);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName){
            case "mutation":
                mutants.addMutant(mutant);
                break;
            case "sourceFile":
                mutant.setSourceFile(elementValue.toString());
                break;
            case "mutatedClass":
                mutant.setMutatedClass(elementValue.toString());
                break;
            case "mutatedMethod":
                mutant.setMutatedMethod(elementValue.toString());
                break;
            case "methodDescription":
                mutant.setMethodDescription(elementValue.toString());
                break;
            case "lineNumber":
                mutant.setLineNumber(Integer.parseInt(elementValue.toString()));
                break;
            case "mutator":
                mutant.setMutator(elementValue.toString());
                break;
            case "index":
                mutant.setIndex(Integer.parseInt(elementValue.toString()));
                break;
            case "block":
                mutant.setBlock(Integer.parseInt(elementValue.toString()));
                break;
            case "killingTests":
                mutant.setKillingTests(elementValue.toString());
                break;
            case "suspValue":
                mutant.setSuspValue(Double.parseDouble(elementValue.toString()));
                break;
            case "description":
                mutant.setDescription(elementValue.toString());
                break;
            case "testsExecutionTime":
                System.out.println("testsExecutionTime ends");
                break;
            case "patchExecutionTime":
                elementValue.setLength(elementValue.length() - 2);
                mutant.setPatchExecutionTime(Integer.parseInt(elementValue.toString()));
                break;
            // for test execution time
//            case "name":
//                break;
//            case "time":
//                break;
        }
    }

    @Override
    public void startDocument() throws SAXException {
        mutants = new Mutants();
    }

    public Mutants getResult() {
        return mutants;
    }
}
