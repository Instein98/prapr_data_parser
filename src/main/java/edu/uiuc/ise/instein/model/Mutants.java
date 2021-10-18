package edu.uiuc.ise.instein.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Mutants {
    private List<Mutant> mutantList;

    public boolean addMutant(Mutant mutant){
        return mutantList.add(mutant);
    }

    public Mutants() {
        this.mutantList = new ArrayList<>();
    }
}
