package org.example.model.Regles;

import org.example.model.Case;
import org.example.model.Piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

enum etat {
    AUCUN,NOMBRE,
    PIECE, JOUEUR,CASE,PIECETOKEN,
    MANGE, SURCASE, ESTMENACE, PROMU, NBDEPLACEMENT, SEDEPLACE, ESTPLACEE, TIMER,
    COMPARAISON, ET, OU, CONSEQUENCE

    /*private String valeur;

    private etat(String valeur) {
        this.valeur = valeur ;
    }

    public String getAbreviation() {
        return  this.valeur ;
    }*/
}

public class GenerateurDeRegle {

    private List<String>[] reglesSousFormeDeChaine;

    private List<Regle> regleAvantCoup;
    private List<Regle> regleApresCoup;

    //Liste des axiomes reconnaissables par notre système
    //Utile lors de l'analyse syntaxique

    private ArrayList<String> axiomes = new ArrayList<>();
    private static final String[] axiomestab = {
            "mange", "sedeplace", "estpromu", "estsur", "estechec", "nb_deplacement", "estplace", "timer",  /*conditions*/
            "=", "<", ">",                                                                                  /*comparaisons*/
            "tous-piece", "tous-joueur", "tous-typecase",                                                   /*tokens nimporte*/
            "victoire", "defaite", "pat", "manger", "placer", "promouvoir", "deplacer"                      /*consequences*/
    };

    public GenerateurDeRegle(List<String>[] reglesSousFormeDeChaine) {
        this.reglesSousFormeDeChaine = reglesSousFormeDeChaine;
        this.regleAvantCoup = new ArrayList<>();
        this.regleApresCoup = new ArrayList<>();
        axiomes.addAll(Arrays.asList(axiomestab));
    }



    /**===================================================================================
     * ================================ ANALYSE SYNTAXIQUE ===============================
     * ===================================================================================
     **/

    //Fonction renseignant si un axiome autre qu'un joueur, une case ou une piece fait partie des axiomes connues
    public static boolean estAxiome(String axiome){
        for (String s: axiomestab) {
            if(s.equals(axiome)){
                return true;
            }
        }
        return false;
    }

    //Fonction renseignant un axiome correspondant à axiome
    public static etat getAxiome(String axiome){
        etat et = etat.AUCUN;
        switch (axiome){
            case "mange"-> et = etat.MANGE;
            case "sedeplace"-> et = etat.SEDEPLACE;
            case "estpromu"-> et = etat.PROMU;
            case "estsur"-> et = etat.SURCASE;
            case "estechec"-> et = etat.ESTMENACE;
            case "nb_deplacement"-> et = etat.NBDEPLACEMENT;
            case "estplace"-> et = etat.ESTPLACEE;
            case "timer"-> et = etat.TIMER;
            case "=" , "<" , ">" -> et = etat.COMPARAISON;
            case "ET"-> et = etat.ET;
            case "OU"-> et = etat.OU;
            case "tous-piece" -> et = etat.PIECE;
            case "tous-joueur" -> et = etat.JOUEUR;
            case "tous-typecase" -> et = etat.CASE;
            case "victoire", "defaite", "pat", "manger", "placer", "promouvoir", "deplacer" -> et = etat.CONSEQUENCE;
        }
        return et;
    }

    //Fonction renseignant si une case est correctement définie (String cases)
    //CALL C1
    public static etat estSyntaxiquementCorrecte_Case(String cases, int nbCase)throws MauvaiseDefinitionRegleException{
        if(cases.length() > 1){
            if(cases.charAt(0) == 'C'){
                if(cases.length() == 4 && cases.charAt(1) == 'A') {
                    if (cases.charAt(2) == 'L') {
                        if (cases.charAt(3) == 'L') {
                            return etat.CASE;
                        }
                        throw new MauvaiseSyntaxeRegleException("Syntaxe de case (CAL + mauvais carac) incorrecte");
                    }
                    throw new MauvaiseSyntaxeRegleException("Syntaxe de case (CA + mauvais carac) incorrecte");
                }else {
                    try {
                        int nb = Integer.parseInt(cases.substring(1));
                        if(nb<nbCase){
                            if(nb>=0){
                                return etat.CASE;
                            }
                            throw new MauvaiseSyntaxeRegleException("Syntaxe de case (numero trop petit) incorrecte");
                        }
                        throw new MauvaiseSyntaxeRegleException("Syntaxe de case (numero trop grand) incorrecte");

                    } catch (NumberFormatException e) {
                        throw new MauvaiseSyntaxeRegleException("Syntaxe de case (numero) incorrecte");
                    }
                }
            }
        }
        throw new MauvaiseSyntaxeRegleException("Syntaxe de case incorrecte");
    }

    //Fonction renseignant si une case est correctement définie (String piece)
    //PALL P1
    public static etat estSyntaxiquementCorrecte_Piece(String piece, int nbPiece)throws MauvaiseDefinitionRegleException{
        if(piece.length() > 1){
            if(piece.charAt(0) == 'P'){
                if(piece.length() == 4 && piece.charAt(1) == 'A') {
                    if (piece.charAt(2) == 'L') {
                        if (piece.charAt(3) == 'L') {
                            return etat.PIECE;
                        }
                        throw new MauvaiseSyntaxeRegleException("Syntaxe de piece (PAL + mauvais carac) incorrecte");
                    }
                    throw new MauvaiseSyntaxeRegleException("Syntaxe de piece (PA + mauvais carac) incorrecte");
                }else {
                    try {
                        int nb = Integer.parseInt(piece.substring(1));
                        if(nb<nbPiece){
                            if(nb>=0){
                                return etat.PIECETOKEN;
                            }
                            throw new MauvaiseSyntaxeRegleException("Syntaxe de piece (numero trop petit) incorrecte");
                        }
                        throw new MauvaiseSyntaxeRegleException("Syntaxe de piece (numero trop grand) incorrecte");
                    } catch (NumberFormatException e) {
                        throw new MauvaiseSyntaxeRegleException("Syntaxe de piece (numero) incorrecte");
                    }
                }
            }
        }
        throw new MauvaiseSyntaxeRegleException("Syntaxe de piece incorrecte");
    }


    //Fonction renseignant si un joueur est correctement défini (String joueur)
    //JALL J1
    public static etat estSyntaxiquementCorrecte_Joueur(String joueur, int nbJoueur)throws MauvaiseDefinitionRegleException{
        if(joueur.length() > 1){
            if(joueur.charAt(0) == 'J'){
                if(joueur.length() == 4 && joueur.charAt(1) == 'A') {
                    if (joueur.charAt(2) == 'L') {
                        if (joueur.charAt(3) == 'L') {
                            return etat.JOUEUR;
                        }
                        throw new MauvaiseSyntaxeRegleException("Syntaxe du joueur (JAL + mauvais carac) incorrecte");
                    }
                    throw new MauvaiseSyntaxeRegleException("Syntaxe du joueur (JA + mauvais carac) incorrecte");
                }else {
                    try {
                        int nb = Integer.parseInt(joueur.substring(1));
                        if(nb<nbJoueur){
                            if(nb>=0){
                                return etat.JOUEUR;
                            }
                            throw new MauvaiseSyntaxeRegleException("Syntaxe du joueur (numero trop petit) incorrecte");
                        }else{
                            throw new MauvaiseSyntaxeRegleException("Syntaxe du joueur (numero trop grand) incorrecte");
                        }

                    } catch (NumberFormatException e) {
                        throw new MauvaiseSyntaxeRegleException("Syntaxe du joueur (numero) incorrecte");
                    }
                }
            }
        }
        throw new MauvaiseSyntaxeRegleException("Syntaxe du joueur incorrecte");
    }

    /**
     *  si est une Piece (P), un type de case (C), un joueur (J); suivis d'un index entier
     *  sinon si est dans les mots d'axiomes
     *  sinon si c'est un nombre entier
     *
     *  A CHANGER => PASSER DUN BOOL A TOKEN/EXCEPTION POUR SYNTAXE
     *
     * Transforme une regle sous forme de chaine de caractères en une liste de jetons, analysables par la partie sémantique.
     * @param regle : ArrayList<String> renseignant la liste des regles à transformer sous forme de jetons.
     * @param nbTypePiece : int renseignant le nombre de type de pieces du jeu.
     * @param nbTypeDeCase : int renseignant le nombre de type de cases du jeu.
     * @param nbJoueur :  int renseignant le nombre de joueurs du jeu.
    * */
    private static ArrayList<etat> est_syntaxiquement_correcte(ArrayList<String> regle, int nbTypePiece, int nbTypeDeCase, int nbJoueur) throws MauvaiseDefinitionRegleException{
        ArrayList<etat> regleSousFormeEtat = new ArrayList<>();

        for (int i = 0; i<regle.size();i++){
            etat curEtat;
            try {
                switch (regle.get(i).charAt(0)) {
                    /*CAS PIECE*/
                    case 'P' -> curEtat = estSyntaxiquementCorrecte_PieceToken(regle.get(i), nbJoueur,nbTypePiece);
                    /*CAS JOUEUR*/
                    case 'J' -> curEtat = estSyntaxiquementCorrecte_Joueur(regle.get(i),nbJoueur);
                    /*CAS CASE*/
                    case 'C' -> curEtat = estSyntaxiquementCorrecte_Case(regle.get(i),nbTypeDeCase);
                    default -> {
                        if (estAxiome(regle.get(i))) {
                            etat etAx = getAxiome(regle.get(i));
                            if(etAx == etat.AUCUN){
                                throw new MauvaiseSyntaxeRegleException("Mauvaise definition d'axiome");
                            }else{
                                if(etAx == etat.PIECE){
                                    regle.set(i,"PALL#JALL#ALL");
                                    etAx = etat.PIECETOKEN;
                                }
                                if(etAx == etat.JOUEUR){
                                    regle.set(i,"JALL");
                                }
                                if(etAx == etat.CASE){
                                    regle.set(i,"CALL");
                                }
                                curEtat = etAx;
                            }
                        }else{
                            try {
                                Integer.parseInt(regle.get(i));
                                curEtat = etat.NOMBRE;
                            } catch (NumberFormatException nfe) {
                                throw new MauvaiseSyntaxeRegleException("Axiome inconnu");
                            }
                        }
                    }
                }
            }catch (MauvaiseSyntaxeRegleException e){
                throw new MauvaiseSyntaxeRegleException(e.getMessage() + " au bloc de regle numero : [" + i + "]");
            }
            regleSousFormeEtat.add(curEtat);
        }
        return regleSousFormeEtat;
    }

    //Fonction renseignant si une piece est correctement définie
    public static etat estSyntaxiquementCorrecte_PieceToken(String piece, int nbJoueur,int nbPiece) throws MauvaiseDefinitionRegleException{
        String[] sousPiece = piece.split("#");

            if (sousPiece.length == 3) {
                try {
                    estSyntaxiquementCorrecte_Piece(sousPiece[0], nbPiece);
                    estSyntaxiquementCorrecte_Joueur(sousPiece[1], nbJoueur);

                    if ((sousPiece[2].charAt(0) == 'P' || sousPiece[2].charAt(0) == 'D') && sousPiece[2].length() == 1) {
                        return etat.PIECETOKEN;
                    }
                    if ((sousPiece[2].charAt(0) == 'A' && sousPiece[2].charAt(1) == 'L' && sousPiece[2].charAt(2) == 'L') && sousPiece[2].length() == 3) {
                        return etat.PIECETOKEN;
                    }
                    throw new MauvaiseSyntaxeRegleException("Syntaxe de la provenance de la piece invalide");
                }catch (MauvaiseSyntaxeRegleException e){
                    throw new MauvaiseSyntaxeRegleException("Syntaxe de piece incorrecte [" + e.getMessage() + "]");
                }
            } else {
                throw new MauvaiseSyntaxeRegleException("Mauvais nombre de parametre de définition d'une piece");
            }

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
