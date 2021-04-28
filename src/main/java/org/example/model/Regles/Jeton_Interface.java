
package org.example.model.Regles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public enum Jeton_Interface implements EstToken, Serializable {

    //Jetons COMMUNS aux deux modèles
    //==== CONNECTEURS ====
    ET(),
    OU(),
    ALORS(),
    NON("NON"),

    COMPARATEUR("=" , "<" , ">"),

    //==== SUJET/CIBLES ====
    CASE(),
    PIECE(),
    JOUEUR(),
    PIECETOKEN(),
    CASEPARAM(),
    CASEALIAS(),

    //Jetons CONDITION
    //==== ETAT ====
    PROMU("estpromu"),

    //==== ACTION ====
    ESTMENACE("estechec"),
    PREND("prend"),
    DEPLACE("sedeplace"),
    PLACE("estplace"),
    EST("estsur"),

    //==== COMPTEUR ====
    COMPTEUR_DEPLACEMENT("nb_deplacement"),
    COMPTEUR_TEMPSRESTANT("timer"),
    NOMBRE(""),
    //@TODO compteur nbVie PIECE

    //Jetons CONSEQUENCE
    CONSEQUENCE_TERMINALE("victoire", "defaite", "pat"),
    CONSEQUENCE_PRENDRE("prendre"),
    CONSEQUENCE_PLACER("placer"),
    CONSEQUENCE_PROMOUVOIR("promouvoir"),
    CONSEQUENCE_DEPLACER("deplacer"),

    ALIAS(),
    ALIASDEF("as"),
    PARENTHESE_OUVRANTE("("),
    PARENTHESE_FERMANTE(")"),
    FIN("");


    private List<String> elementsReconnaissables;

    Jeton_Interface(String... s){
        elementsReconnaissables = new ArrayList<>();
        for (int i =0; i<s.length;i++){
            elementsReconnaissables.add(s[i]);
        }
    }

    public List<String> getElementsReconnaissables() {
        return elementsReconnaissables;
    }

    @Override
    public boolean estReconnu(String s) {
        return false;
    }
}
