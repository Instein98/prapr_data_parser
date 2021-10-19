package edu.uiuc.ise.instein;

import edu.uiuc.ise.instein.model.Mutant;
import edu.uiuc.ise.instein.model.Mutants;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.List;

/**
 * Collect the data for each buggy subject:
 * 1) total test time (including the timeout test)
 * 2) average rank of killing test
 * 3) the timeout patch percentage
 */
public class Main {
    public static void main(String[] args) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        XmlHandler handler = new XmlHandler();

        File resultDir = new File("xml_result_1.2");
        for (File subjectDir: resultDir.listFiles()){
            if (subjectDir.isDirectory()){
                for (File xmlFile: subjectDir.listFiles()){
                    if (!xmlFile.getName().endsWith(".xml")){
                        continue;
                    }
                    String id = subjectDir.getName() + xmlFile.getName().substring(0, xmlFile.getName().length()-4);
                    try{
                        saxParser.parse(xmlFile.getAbsolutePath(), handler);
                    } catch (Throwable t){
                        System.out.println(id + " parse failed: " + t.getMessage());
                        continue;
                    }
                    Mutants result = handler.getResult();
                    printResult(result, id);
                }
            }
        }
    }

    public static void printResult(Mutants mutants, String identifier){
        List<Mutant> mutantList = mutants.getMutantList();
        int totalTestTime = mutantList.stream()
                .map(Mutant::getTestExecutionTimeSum)
                .mapToInt(i -> i)
                .sum();
        double averageKillerRank = mutantList.stream()
                .filter(x -> x.getStatus().equals("KILLED"))
                .map(x -> x.getNumberOfTestsRun() + 1)
                .mapToInt(i -> i)
                .average()
                .orElse(0);
        double timeoverPercentage = mutantList.stream()
                .mapToInt(x -> x.getStatus().equals("TIMED_OUT") ? 1 : 0)
                .average()
                .orElse(0);
        System.out.println(String.join("\t",
                identifier,
                Double.toString(averageKillerRank),
                Integer.toString(totalTestTime),
                Double.toString(timeoverPercentage)));
    }
}
