package org.example.model.Regles;

import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Regles.Sujet;

import java.util.ArrayList;

public class Condition {
    private ArrayList<Sujet> sujets;


    public Condition(ArrayList<Sujet> sujets) {
        this.sujets = new ArrayList<>();
    }

    public boolean verification(OrdonnanceurDeJeu odonnaceur, Sujet sujet){
        return false;
    }

}
