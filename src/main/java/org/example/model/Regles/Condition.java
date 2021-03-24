package org.example.model.Regles;

import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Regles.Sujet;

import java.util.ArrayList;

public abstract class Condition implements BlocDeRegle {

    /*
    private ArrayList<Sujet> sujets;


    public Condition(ArrayList<Sujet> sujets) {
        this.sujets = new ArrayList<>();
    }

    public boolean verification(OrdonnanceurDeJeu odonnaceur, Sujet sujet){
        return false;
    }*/

    public abstract boolean verification();
    public abstract void consequence();

}
