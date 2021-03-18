package org.example.model.Regles;

import org.example.model.Case;
import org.example.model.Piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GenerateurDeRegle {

    private List<String>[] reglesSousFormeDeChaine;

    private List<Regle> regleAvantCoup;
    private List<Regle> regleApresCoup;

    public GenerateurDeRegle(List<String>[] reglesSousFormeDeChaine) {
        this.reglesSousFormeDeChaine = reglesSousFormeDeChaine;
        this.regleAvantCoup = new ArrayList<>();
        this.regleApresCoup = new ArrayList<>();
        axiomes.addAll(Arrays.asList(axiomestab));
    }


    /**
     *  si est une Piece (P), un type de case (C), un joueur (J); suivis d'un index entier
     *  sinon si est dans les mots d'axiomes
     *  sinon si c'est un nombre entier
     *
     *  A CHANGER => PASSER DUN BOOL A TOKEN/EXCEPTION POUR SYNTAXE
     *
     * Transforme une regle sous forme de chaine de caractères en une liste de jetons, analysables par la partie sémantique.
    * */
    private boolean est_syntaxiquement_correct(ArrayList<String> regle, int nbTypePiece, int nbTypeDeCase, int nbJoueur) throws MauvaiseDefinitionRegleException{
        boolean ctrl = true;
        ArrayList<String> tokens = new ArrayList<>();
        for (int i = 0; i<regle.size();i++){

            switch (regle.get(i).charAt(0)) {
                /*CAS PIECE*/
                case 'P' -> ctrl = estSyntaxiquementCorrect_Piece(regle.get(i));
                /*CAS JOUEUR*/
                case 'J' -> System.out.println();
                /*CAS CASE*/
                case 'C' -> System.out.println();
                /*CAS DEPLACEMENT*/
                case 'D' -> System.out.println();
                default -> {
                    try {
                        if (!axiomes.contains(regle.get(i))) {
                            Integer.parseInt(regle.get(i));
                        }

                    } catch (NumberFormatException nfe){
                        throw new MauvaiseSyntaxeRegleException("Mauvaise definition d'entier au bloc de regle numero : " + i);
                    }
                }
            }
            if(!ctrl) {
                throw new MauvaiseSyntaxeRegleException("Mauvaise definition au bloc de regle numero : " + i);
            }
        }
        return ctrl;
    }

    //Fonction renseignant si une piece est correctement définie


    public boolean estSyntaxiquementCorrect_PieceB(String piece) throws MauvaiseDefinitionRegleException{
        String[] sousPiece = piece.split("#");
        if(sousPiece.length == 3){
            estSyntaxiquementCorrect_Piece(sousPiece[0]);
            estSyntaxiquementCorrect_Joueur(sousPiece[1]);
            if((sousPiece[2].charAt(0) == 'P' || sousPiece[2].charAt(0) == 'D') && sousPiece[2].length() == 1){
                return true;
            }
            if((sousPiece[2].charAt(0) == 'A' && sousPiece[2].charAt(1) == 'L' && sousPiece[2].charAt(2) == 'L') && sousPiece[2].length() == 3){
                return true;
            }
            return false;

        }else{
            return false;
        }
    }

    //Fonction renseignant si une case est correctement définie
    //CALL C1
    public boolean estSyntaxiquementCorrect_Case(String cases)throws MauvaiseDefinitionRegleException{
        if(cases.length() > 1){
            if(cases.charAt(0) == 'C'){
                if(cases.length() == 4 && cases.charAt(1) == 'A') {
                    if (cases.charAt(2) == 'L') {
                        if (cases.charAt(3) == 'L') {
                            return true;
                        }
                    }
                }else {
                    try {
                        int nb = Integer.parseInt(cases.substring(1));
                        return true;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    //Fonction renseignant si une case est correctement définie
    //PALL P1
    public boolean estSyntaxiquementCorrect_Piece(String cases)throws MauvaiseDefinitionRegleException{
        if(cases.length() > 1){
            if(cases.charAt(0) == 'P'){
                if(cases.length() == 4 && cases.charAt(1) == 'A') {
                    if (cases.charAt(2) == 'L') {
                        if (cases.charAt(3) == 'L') {
                            return true;
                        }
                    }
                }else {
                    try {
                        int nb = Integer.parseInt(cases.substring(1));
                        return true;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                }
            }
        }
        return false;
    }


    //Fonction renseignant si un joueur est correctement défini
    //JALL J1
    public boolean estSyntaxiquementCorrect_Joueur(String joueur)throws MauvaiseDefinitionRegleException{
        if(joueur.length() > 1){
            if(joueur.charAt(0) == 'J'){
                if(joueur.length() == 4 && joueur.charAt(1) == 'A') {
                    if (joueur.charAt(2) == 'L') {
                        if (joueur.charAt(3) == 'L') {
                            return true;
                        }
                    }
                }else {
                    try {
                        int nb = Integer.parseInt(joueur.substring(1));
                        return true;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                }
            }
        }
        return false;
    }



    /**
     *  Prend en entrée une liste de Token et retourne une règle si cette liste
     *  de token est sémantiquement correcte.
     *
     * note:
     * private enum etat {
     *         PIECE, JOUEUR, SURPLATEAU, DANSDEFAUSSE,
     *         MANGE, SURCASE, ESTMENACE, PROMU, NBDEPLACEMENT, SEDEPLACE, ESTPLACEE, TIMER,
     *         COMPARAISON, ET, OU
     *
     *
     **/
    public void analyse_sémantique(ArrayList<Integer> lt){

        //if(lt.get(0) == etat.PIECE)

    }



    // [Piece p1] ....
    // [Joueur j1] ....
    // Regle1: {{}}
    // {P0#J1#P, SEDEPLACE, C1}P,ESTECHEC]
    // [P0#J1#P,sedeplace,C1,OU,P0#J2#P,SEDEPLACE,P0#J1#
    // [PALL#JALL#ALL,sedeplace,C1,OU,P0#J2#P,SEDEPLACE,P0#J1#
    //[ ["piece_nimporte"] [(n)->return n] ]

    //[ ["cavalier"] [(n)->if(n.type == cavalier) return n] ]

    /*Fonction qui va se charger de transformer une regle sous forme d'une chaine de caractere,
    * en une instance de REGLE manipulable plus simplement par le système.*/
    public void analyser(ArrayList<Piece> listepiece) throws MauvaiseDefinitionRegleException{
        //Pour chaque règles du jeu définies
        for (int i = 0; i<this.reglesSousFormeDeChaine.length; i++){
            //analyse syntaxique
            //analyse sémantique
        }
    }
}
