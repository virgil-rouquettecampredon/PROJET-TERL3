package org.example.model;

import org.example.model.Regles.*;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class VarianteBuilder {
    private String name;
    private Plateau plateau;
    private ArrayList<Joueur> joueurs;
    private ArrayList<Joueur> ordreJoueurs;
    private ArrayList<RegleInterface> regles;
    private ArrayList<GroupCases> listGroupCases;

    public VarianteBuilder() {
        name = "Default variante";
        plateau = new Plateau();
        joueurs = new ArrayList<>();
        ordreJoueurs = new ArrayList<>();
        regles = new ArrayList<>();
        listGroupCases = new ArrayList<>();
    }

    public VarianteBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public VarianteBuilder setPlateau(Plateau plateau) {
        this.plateau = plateau;
        return this;
    }

    public VarianteBuilder setJoueurs(ArrayList<Joueur> joueurs) {
        this.joueurs = joueurs;
        return this;
    }

    public VarianteBuilder setOrdreJoueurs(ArrayList<Joueur> ordreJoueurs) {
        this.ordreJoueurs = ordreJoueurs;
        return this;
    }

    public VarianteBuilder setRegles(ArrayList<RegleInterface> regles) {
        this.regles = regles;
        return this;
    }

    public VarianteBuilder setListGroupCases(ArrayList<GroupCases> listGroupCases) {
        this.listGroupCases = listGroupCases;
        return this;
    }

    public String getName() {
        return name;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }

    public ArrayList<Joueur> getOrdreJoueurs() {
        return ordreJoueurs;
    }

    public ArrayList<RegleInterface> getRegles() {
        return regles;
    }

    public ArrayList<GroupCases> getListGroupCases() {
        return listGroupCases;
    }

    public ArrayList<Piece> getAllPawn() {
        ArrayList<Piece> lp = new ArrayList<>();
        for (Joueur j:
             joueurs) {
            lp.addAll(j.getTypePawnList());
        }
        return lp;
    }

    public VarianteJeton createVariante() {
        return new VarianteJeton(name, plateau, joueurs, ordreJoueurs, regles, listGroupCases);
    }
}