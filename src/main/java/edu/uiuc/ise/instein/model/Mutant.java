package edu.uiuc.ise.instein.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Mutant {
    boolean detected;
    String status;
    int numberOfTestsRun;
    int getWebsite;
    String sourceFile;
    String mutatedClass;
    String mutatedMethod;
    String methodDescription;
    int lineNumber;
    String mutator;
    int index;
    int block;
    String killingTests;
    double suspValue;
    String description;
    Map<String, Integer> testsExecutionTime;
    int patchExecutionTime;
}
