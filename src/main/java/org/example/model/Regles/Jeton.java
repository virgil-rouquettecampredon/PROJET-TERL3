package org.example.model.Regles;

public enum Jeton{
    AUCUN("aucun"),NOMBRE("nombre"),
    PIECE("piece"), JOUEUR("joueur"), CASE("case"), PIECETOKEN("piece token"),
    ACTION("action"), ETAT("etat"), COMPTEUR("compteur"),
    COMPARAISON("comparaison"), ET("et"), OU("ou"),NON("non"),
    ALORS("alors"),CONSEQUENCETERMINALE("consequence terminale"),CONSEQUENCEACTION("consequence"),ALIAS("alias");


    private String valeur;

    Jeton(String s){
        this.valeur = s;
    }

    public String getValeur() {
        return valeur;
    }
}
