package org.example.model.Regles;

import java.util.ArrayList;
import java.util.List;

public class GenerateurDeRegle {

    private String[] reglesSousFormeDeChaine;

    private List<Regle> regleAvantCoup;
    private List<Regle> regleApresCoup;

    public GenerateurDeRegle(String[] reglesSousFormeDeChaine) {
        this.reglesSousFormeDeChaine = reglesSousFormeDeChaine;
        this.regleAvantCoup = new ArrayList<>();
        this.regleApresCoup = new ArrayList<>();
    }

    public void analyser(){

    }
}
