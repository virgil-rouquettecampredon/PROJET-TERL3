package org.example.model;

import org.example.model.Regles.GenerateurDeRegle;
import org.example.model.Regles.Jeton;
import org.example.model.Regles.Regle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Variante implements Serializable {
    private String name;                                // Nom de la variante
    private Plateau plateau;                            // Le plateau correspondant à cette variantes
    private ArrayList<Joueur> joueurs;                  // La liste des joueurs de cette variantes
    private ArrayList<Joueur> ordreJoueurs;             // La liste des joueurs dans l'ordre des tour (avec duplication, etc)
    private ArrayList<RegleInterface> regles;           // Les listes de Regles
    private ArrayList<GroupCases> listGroupCases;       // La liste de toutes les groupes de cases définies dans cette variante

    public Variante(String name, Plateau plateau, ArrayList<Joueur> joueurs, ArrayList<Joueur> ordreJoueurs, ArrayList<RegleInterface> regles, ArrayList<GroupCases> listGroupCases) {
        this.name = name;
        this.plateau = plateau;
        this.joueurs = joueurs;
        this.ordreJoueurs = ordreJoueurs;
        this.regles = regles;
        this.listGroupCases = listGroupCases;
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

    public ArrayList<RegleInterface> getRegles() {
        return regles;
    }

    public ArrayList<Joueur> getOrdrejoueur() { return ordreJoueurs;}

    public void setRegles(ArrayList<RegleInterface> generateurDeRegle) {
        this.regles = generateurDeRegle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<GroupCases> getListGroupCases() {
        return listGroupCases;
    }

    public void setListGroupCases(ArrayList<GroupCases> listGroupCases) {
        this.listGroupCases = listGroupCases;
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
