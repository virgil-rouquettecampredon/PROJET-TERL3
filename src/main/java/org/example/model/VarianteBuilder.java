package org.example.model;

import org.example.model.Regles.Regle;

import java.util.ArrayList;

public class VarianteBuilder {
    private String name;
    private Plateau plateau;
    private ArrayList<Joueur> joueurs;
    private ArrayList<Regle> regles;
    private ArrayList<Piece> pieces;

    public VarianteBuilder() {
        name = "Default variante";
        plateau = new Plateau();
        joueurs = new ArrayList<>();
        joueurs.add(new Joueur("Joueur1", 0));
        joueurs.add(new Joueur("Joueur2",1));
        regles = new ArrayList<>();
        pieces = new ArrayList<>();
        pieces.add(new Piece("Paw", "file:src/main/resources/org/example/images/pawn.png", null));
        pieces.add(new Piece("King", "file:src/main/resources/org/example/images/king.png", null));
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

    public VarianteBuilder setRegles(ArrayList<Regle> regles) {
        this.regles = regles;
        return this;
    }

    public VarianteBuilder setPieces(ArrayList<Piece> pieces) {
        this.pieces = pieces;
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

    public ArrayList<Regle> getRegles() {
        return regles;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public Variante createVariante() {
        return new Variante(name, plateau, joueurs, regles, pieces);
    }
}