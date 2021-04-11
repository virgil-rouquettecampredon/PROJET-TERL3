package org.example.model.Regles;

import org.example.model.Joueur;
import org.example.model.Piece;

import java.util.List;

public class Fonctions_Comportements {

    /*--------------------------------CONDITION ETAT------------------------------*/
    public static boolean estpromu(List<Joueur> listej){ return false; }

    /*-----------------------------CONDITION ACTION CIBLE-------------------------*/
    /**
     * @param liste_a : liste des pièces attaquante possible
     * @param liste_v : liste des pièces victime possible
     * @return vrai si au moins une pièces dans la liste des pièces attaquante à pris une pièce dans la liste des pièces victime*/
    public static boolean prend(List<Piece> liste_a, List<Piece> liste_v){ return false; }

    //public static boolean estsur(){ return false; }

}
