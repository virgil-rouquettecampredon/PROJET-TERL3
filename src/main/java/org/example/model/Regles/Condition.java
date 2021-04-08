package org.example.model.Regles;

import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Regles.Sujet;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Condition implements BlocDeRegle, Serializable {

    private Jeton connecteur;

    public abstract boolean verification();

    public Jeton getConnecteur(){
        return this.connecteur;
    }

    public void setConnecteur(Jeton j){
        if(j == Jeton.OU || j == Jeton.ET){
            this.connecteur = j;
        }
    }
}
