package org.example.model.Regles;

import org.example.model.Case;
import org.example.model.Piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GenerateurDeRegle {

    //Tableau des règles données sous forme de chaine de caractère
    //à la construction du GenerateurDeRegle
    private List<String>[] reglesSousFormeDeChaine;

    //Listes de Regle qui seront remplies par l'analyseur sémantique
    //Si syntaxe et sémantique sont correctes
    private List<Regle> regleAvantCoup;
    private List<Regle> regleApresCoup;


    //private ArrayList<String> axiomes = new ArrayList<>();

    //Tableau des axiomes reconnaissables par notre système
    //Utile lors de l'analyse syntaxique dans un premier temps,
    //puis semantique ensuite
    private static final String[] axiomestab = {
            "mange", "sedeplace", "estpromu", "estsur", "estechec", "nb_deplacement", "estplace", "timer",  /*conditions*/
            "=", "<", ">",                                                                                  /*comparaisons*/
            "tous-piece", "tous-joueur", "tous-typecase",                                                   /*tokens nimporte*/
            "victoire", "defaite", "pat", "manger", "placer", "promouvoir", "deplacer"                      /*consequences*/
    };

    private static final String[] connecteur = {"ET","OU"};



    /**Constructeur d'un GenerateurDeRegle
     * @param reglesSousFormeDeChaine : Tableau de Liste de Chaines de caractères. Chaque liste représente une règle de jeu :
     *                                [0] : ["0","PALL","sedeplace","C2","manger","PALL"]
     *                                [1] : ["1","JALL","timer","=","3","ET","PALL","estechec","victoire"]
     *                                [2] : ["0","PALL#J2#P", "ET","PALL#J2#P","estsur","C5","defaite"]
     **/
    public GenerateurDeRegle(List<String>[] reglesSousFormeDeChaine) {
        this.reglesSousFormeDeChaine = reglesSousFormeDeChaine;
        this.regleAvantCoup = new ArrayList<>();
        this.regleApresCoup = new ArrayList<>();
        //axiomes.addAll(Arrays.asList(axiomestab));
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

    //Fonction renseignant si un mot est un connecteur
    public static boolean estConnecteur(String mot){
        for (String s: connecteur) {
            if(s.equals(mot)){
                return true;
            }
        }
        return false;
    }

    //Fonction renseignant un axiome correspondant à axiome
    public static Etat getAxiome(String axiome){
        Etat et = Etat.AUCUN;
        switch (axiome){
            case "mange"-> et = Etat.MANGE;
            case "sedeplace"-> et = Etat.SEDEPLACE;
            case "estpromu"-> et = Etat.PROMU;
            case "estsur"-> et = Etat.SURCASE;
            case "estechec"-> et = Etat.ESTMENACE;
            case "nb_deplacement"-> et = Etat.NBDEPLACEMENT;
            case "estplace"-> et = Etat.ESTPLACEE;
            case "timer"-> et = Etat.TIMER;
            case "=" , "<" , ">" -> et = Etat.COMPARAISON;
            case "ET"-> et = Etat.ET;
            case "OU"-> et = Etat.OU;
            case "tous-piece" -> et = Etat.PIECE;
            case "tous-joueur" -> et = Etat.JOUEUR;
            case "tous-typecase" -> et = Etat.CASE;
            case "victoire", "defaite", "pat", "manger", "placer", "promouvoir", "deplacer" -> et = Etat.CONSEQUENCE;
        }
        return et;
    }

    //Fonction renseignant si une case est correctement définie (String cases)
    //CALL C1
    public static Etat estSyntaxiquementCorrecte_Case(String cases, int nbCase)throws MauvaiseDefinitionRegleException{
        if(cases.length() > 1){
            if(cases.charAt(0) == 'C'){
                if(cases.length() == 4 && cases.charAt(1) == 'A') {
                    if (cases.charAt(2) == 'L') {
                        if (cases.charAt(3) == 'L') {
                            return Etat.CASE;
                        }
                        throw new MauvaiseSyntaxeRegleException("Syntaxe de case (CAL + mauvais carac) incorrecte");
                    }
                    throw new MauvaiseSyntaxeRegleException("Syntaxe de case (CA + mauvais carac) incorrecte");
                }else {
                    try {
                        int nb = Integer.parseInt(cases.substring(1));
                        if(nb<nbCase){
                            if(nb>=0){
                                return Etat.CASE;
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
    public static Etat estSyntaxiquementCorrecte_Piece(String piece, int nbPiece)throws MauvaiseDefinitionRegleException{
        if(piece.length() > 1){
            if(piece.charAt(0) == 'P'){
                if(piece.length() == 4 && piece.charAt(1) == 'A') {
                    if (piece.charAt(2) == 'L') {
                        if (piece.charAt(3) == 'L') {
                            return Etat.PIECE;
                        }
                        throw new MauvaiseSyntaxeRegleException("Syntaxe de piece (PAL + mauvais carac) incorrecte");
                    }
                    throw new MauvaiseSyntaxeRegleException("Syntaxe de piece (PA + mauvais carac) incorrecte");
                }else {
                    try {
                        int nb = Integer.parseInt(piece.substring(1));
                        if(nb<nbPiece){
                            if(nb>=0){
                                return Etat.PIECE;
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
    public static Etat estSyntaxiquementCorrecte_Joueur(String joueur, int nbJoueur)throws MauvaiseDefinitionRegleException{
        if(joueur.length() > 1){
            if(joueur.charAt(0) == 'J'){
                if(joueur.length() == 4 && joueur.charAt(1) == 'A') {
                    if (joueur.charAt(2) == 'L') {
                        if (joueur.charAt(3) == 'L') {
                            return Etat.JOUEUR;
                        }
                        throw new MauvaiseSyntaxeRegleException("Syntaxe du joueur (JAL + mauvais carac) incorrecte");
                    }
                    throw new MauvaiseSyntaxeRegleException("Syntaxe du joueur (JA + mauvais carac) incorrecte");
                }else {
                    try {
                        int nb = Integer.parseInt(joueur.substring(1));
                        if(nb<nbJoueur){
                            if(nb>=0){
                                return Etat.JOUEUR;
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
    public static ArrayList<Etat> estSyntaxiquementCorrecte(ArrayList<String> regle, int nbTypePiece, int nbTypeDeCase, int nbJoueur) throws MauvaiseDefinitionRegleException{
        ArrayList<Etat> regleSousFormeEtat = new ArrayList<>();

        for (int i = 1; i<regle.size();i++){
            Etat curEtat;
            String curRegle = regle.get(i);

            try {
                if(curRegle.charAt(0) == 'N'){
                    regleSousFormeEtat.add(Etat.NON);
                    curRegle = curRegle.substring(1);
                }
                switch (curRegle.charAt(0)) {
                    /*CAS PIECE*/
                    case 'P' -> curEtat = estSyntaxiquementCorrecte_PieceToken(curRegle, nbJoueur,nbTypePiece);
                    /*CAS JOUEUR*/
                    case 'J' -> curEtat = estSyntaxiquementCorrecte_Joueur(curRegle,nbJoueur);
                    /*CAS CASE*/
                    case 'C' -> curEtat = estSyntaxiquementCorrecte_Case(curRegle,nbTypeDeCase);
                    default -> {
                        if (estAxiome(curRegle) || estConnecteur(curRegle)) {
                            Etat etAx = getAxiome(curRegle);
                            if(etAx == Etat.AUCUN){
                                throw new MauvaiseSyntaxeRegleException("Mauvaise definition d'axiome");
                            }else{
                                if(etAx == Etat.PIECE){
                                    regle.set(i,"PALL#JALL#ALL");
                                    etAx = Etat.PIECETOKEN;
                                }
                                if(etAx == Etat.JOUEUR){
                                    regle.set(i,"JALL");
                                }
                                if(etAx == Etat.CASE){
                                    regle.set(i,"CALL");
                                }
                                curEtat = etAx;
                            }
                        }else{
                            try {
                                Integer.parseInt(curRegle);
                                curEtat = Etat.NOMBRE;
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

    //Fonction renseignant si une piece+token est correctement définie
    public static Etat estSyntaxiquementCorrecte_PieceToken(String piece, int nbJoueur,int nbPiece) throws MauvaiseDefinitionRegleException{
        String[] sousPiece = piece.split("#");

            if (sousPiece.length == 3) {
                try {
                    estSyntaxiquementCorrecte_Piece(sousPiece[0], nbPiece);
                    estSyntaxiquementCorrecte_Joueur(sousPiece[1], nbJoueur);

                    if ((sousPiece[2].charAt(0) == 'P' || sousPiece[2].charAt(0) == 'D') && sousPiece[2].length() == 1) {
                        return Etat.PIECETOKEN;
                    }
                    if ((sousPiece[2].charAt(0) == 'A' && sousPiece[2].charAt(1) == 'L' && sousPiece[2].charAt(2) == 'L') && sousPiece[2].length() == 3) {
                        return Etat.PIECETOKEN;
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
     *         COMPARAISON, ET, OU, ENTIER
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
            //bloc try
            //traitement premier caractère (permet de filtrer regles avant-coup et regle apres-coup)
            //idée : faire un liste de liste de regle et donner un supplier<Integer> "listeAregarder" a l'ordonnanceur a la construction
            //analyse syntaxique
            //analyse sémantique
            //construction de la règle
            //ajout règle dans bonne liste
            //bloc catch (action à définir : si une regle fausse, tout faux ?)
        }
    }


}
