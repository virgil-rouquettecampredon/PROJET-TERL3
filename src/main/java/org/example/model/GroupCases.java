package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;

public class GroupCases implements Serializable {
    private String name;
    private ArrayList<Case> casesAbsolue;
    private ArrayList<Position> positionsRelatives;

    public GroupCases(String name) {
        this.name = name;
        this.casesAbsolue = new ArrayList<>();
        this.positionsRelatives = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Case> getCasesAbsolue() {
        return casesAbsolue;
    }

    public void setCasesAbsolue(ArrayList<Case> casesAbsolue) {
        this.casesAbsolue = casesAbsolue;
    }

    public ArrayList<Position> getPositionsRelatives() {
        return positionsRelatives;
    }

    public void setPositionsRelatives(ArrayList<Position> positionsRelatives) {
        this.positionsRelatives = positionsRelatives;
    }
}
