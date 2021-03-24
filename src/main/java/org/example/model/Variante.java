package org.example.model;

import org.example.model.Regles.Regle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Variante implements Serializable {
    private String name;
    private Plateau plateau;
    private ArrayList<Joueur> joueurs;
    private ArrayList<Regle> regles;
    private ArrayList<Piece> pieces;

    public Variante(String name, Plateau plateau, ArrayList<Joueur> joueurs, ArrayList<Regle> regles, ArrayList<Piece> pieces) {
        this.name = name;
        this.plateau = plateau;
        this.joueurs = joueurs;
        this.regles = regles;
        this.pieces = pieces;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(ArrayList<Joueur> joueurs) {
        this.joueurs = joueurs;
    }

    public ArrayList<Regle> getRegles() {
        return regles;
    }

    public void setRegles(ArrayList<Regle> regles) {
        this.regles = regles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variante variante = (Variante) o;
        return Objects.equals(name, variante.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
