package org.example.model.Regles;

import java.io.Serializable;

public enum Jeton implements Serializable {
    AUCUN("aucun"),NOMBRE("nombre"),
    PIECE("piece"), JOUEUR("joueur"), CASE("case"), PIECETOKEN("piece token"),
    ACTION("action"), ETAT("etat"), COMPTEUR("compteur"),
    COMPARAISON("comparaison"), ET("et"), OU("ou"),NON("non"),
    ALORS("alors"),CONSEQUENCETERMINALE("consequence terminale"),CONSEQUENCEACTION("consequence"),ALIAS("alias")
    ,PARENTHESEOUVRANTE("("), PARENTHESEFERMANTE(")"), CONDITION("condition");


    private String valeur;

    Jeton(String s){
        this.valeur = s;
    }

    public String getValeur() {
        return valeur;
    }
}
