package org.example.model.Regles;

import java.io.Serializable;

public abstract class Consequence implements BlocDeRegle, Serializable {
    private Jeton connecteur;

    public abstract void comportement();

    public Jeton getConnecteur(){
        return this.connecteur;
    }

    public void setConnecteur(Jeton j){
        if(j == Jeton.ET){
            this.connecteur = j;
        }
    }
}
