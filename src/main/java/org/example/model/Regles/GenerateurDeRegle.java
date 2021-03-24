package org.example.model.Regles;

import org.example.model.Piece;

import java.util.*;

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
    public static Jeton getAxiome(String axiome){
        Jeton et = Jeton.AUCUN;
        switch (axiome){
            case "mange","sedeplace","estplace"-> et = Jeton.ACTION;
            case "estpromu","estsur","estechec"-> et = Jeton.ETAT;
            case "nb_deplacement","timer"-> et = Jeton.COMPTEUR;
            case "=" , "<" , ">" -> et = Jeton.COMPARAISON;
            case "ET"-> et = Jeton.ET;
            case "OU"-> et = Jeton.OU;
            case "tous-piece" -> et = Jeton.PIECE;
            case "tous-joueur" -> et = Jeton.JOUEUR;
            case "tous-typecase" -> et = Jeton.CASE;
            case "victoire", "defaite", "pat", "manger", "placer", "promouvoir", "deplacer" -> et = Jeton.CONSEQUENCE;
        }
        return et;
    }

    /**Transforme une regle sous forme de chaine de caractères en une liste de jetons, analysables par la partie sémantique.
     * @param regle : ArrayList<String> renseignant la liste des regles à transformer sous forme de jetons.
     * @param nbTypePiece : int renseignant le nombre de type de pieces du jeu.
     * @param nbTypeDeCase : int renseignant le nombre de type de cases du jeu.
     * @param nbJoueur :  int renseignant le nombre de joueurs du jeu.
     * */
    public static ArrayList<Jeton> estSyntaxiquementCorrecte(ArrayList<String> regle, int nbTypePiece, int nbTypeDeCase, int nbJoueur) throws MauvaiseDefinitionRegleException{
        ArrayList<Jeton> regleSousFormeJeton = new ArrayList<>();

        for (int i = 1; i<regle.size();i++){
            Jeton curJeton;
            String curRegle = regle.get(i);

            try {
                int cpt = 0;
                while(curRegle.charAt(cpt) == 'N'){
                    regleSousFormeJeton.add(Jeton.NON);
                    cpt++;
                    curRegle = curRegle.substring(cpt);
                }
                switch (curRegle.charAt(0)) {
                    /*CAS PIECE*/
                    case 'P' -> curJeton = estSyntaxiquementCorrecte_PieceToken(curRegle, nbJoueur,nbTypePiece);
                    /*CAS JOUEUR*/
                    case 'J' -> curJeton = estSyntaxiquementCorrecte_Joueur(curRegle,nbJoueur);
                    /*CAS CASE*/
                    case 'C' -> curJeton = estSyntaxiquementCorrecte_Case(curRegle,nbTypeDeCase);
                    default -> {
                        if (estAxiome(curRegle) || estConnecteur(curRegle)) {
                            Jeton etAx = getAxiome(curRegle);
                            if(etAx == Jeton.AUCUN){
                                throw new MauvaiseSyntaxeRegleException("Mauvaise definition d'axiome");
                            }else{
                                if(etAx == Jeton.PIECE){
                                    regle.set(i,"PALL");
                                    etAx = Jeton.PIECE;
                                }
                                if(etAx == Jeton.JOUEUR){
                                    regle.set(i,"JALL");
                                }
                                if(etAx == Jeton.CASE){
                                    regle.set(i,"CALL");
                                }
                                curJeton = etAx;
                            }
                        }else{
                            try {
                                Integer.parseInt(curRegle);
                                curJeton = Jeton.NOMBRE;
                            } catch (NumberFormatException nfe) {
                                throw new MauvaiseSyntaxeRegleException("Axiome inconnu");
                            }
                        }
                    }
                }
            }catch (MauvaiseSyntaxeRegleException e){
                throw new MauvaiseSyntaxeRegleException(e.getMessage() + " au bloc de regle numero : [" + i + "]");
            }
            regleSousFormeJeton.add(curJeton);
        }
        return regleSousFormeJeton;
    }

    //Fonction renseignant si une case est correctement définie (String cases)
    //CALL C1
    public static Jeton estSyntaxiquementCorrecte_Case(String cases, int nbCase)throws MauvaiseDefinitionRegleException{
        if(cases.length() > 1){
            if(cases.charAt(0) == 'C'){
                if(cases.length() >= 4 && cases.charAt(1) == 'A') {
                    if (cases.charAt(2) == 'L') {
                        if (cases.charAt(3) == 'L') {
                            if(cases.length() == 4){
                                return Jeton.CASE;
                            }
                            throw new MauvaiseSyntaxeRegleException("Syntaxe de case (CALL + mauvais carac) incorrecte");
                        }
                        throw new MauvaiseSyntaxeRegleException("Syntaxe de case (CAL + mauvais carac) incorrecte");
                    }
                    throw new MauvaiseSyntaxeRegleException("Syntaxe de case (CA + mauvais carac) incorrecte");
                }else {
                    try {
                        int nb = Integer.parseInt(cases.substring(1));
                        if(nb<nbCase){
                            if(nb>=0){
                                return Jeton.CASE;
                            }
                            throw new MauvaiseSyntaxeRegleException("Syntaxe de case (numero trop petit) incorrecte");
                        }
                        throw new MauvaiseSyntaxeRegleException("Syntaxe de case (numero trop grand) incorrecte");

                    } catch (NumberFormatException e) {
                        throw new MauvaiseSyntaxeRegleException("Syntaxe de case (numero) incorrecte");
                    }
                }
            }
            throw new MauvaiseSyntaxeRegleException("Syntaxe de case incorrecte (doit commencer par C)");
        }
        throw new MauvaiseSyntaxeRegleException("Syntaxe de case incorrecte");
    }

    //Fonction renseignant si une case est correctement définie (String piece)
    //PALL P1
    public static Jeton estSyntaxiquementCorrecte_Piece(String piece, int nbPiece)throws MauvaiseDefinitionRegleException{
        if(piece.length() > 1){
            if(piece.charAt(0) == 'P'){
                if(piece.length() >= 4 && piece.charAt(1) == 'A') {
                    if (piece.charAt(2) == 'L') {
                        if (piece.charAt(3) == 'L') {
                            if(piece.length() == 4){
                                return Jeton.PIECE;
                            }
                            throw new MauvaiseSyntaxeRegleException("Syntaxe de piece (PALL + mauvais carac) incorrecte");
                        }
                        throw new MauvaiseSyntaxeRegleException("Syntaxe de piece (PAL + mauvais carac) incorrecte");
                    }
                    throw new MauvaiseSyntaxeRegleException("Syntaxe de piece (PA + mauvais carac) incorrecte");
                }else {
                    try {
                        int nb = Integer.parseInt(piece.substring(1));
                        if(nb<nbPiece){
                            if(nb>=0){
                                return Jeton.PIECE;
                            }
                            throw new MauvaiseSyntaxeRegleException("Syntaxe de piece (numero trop petit) incorrecte");
                        }
                        throw new MauvaiseSyntaxeRegleException("Syntaxe de piece (numero trop grand) incorrecte");
                    } catch (NumberFormatException e) {
                        throw new MauvaiseSyntaxeRegleException("Syntaxe de piece (numero) incorrecte");
                    }
                }
            }
            throw new MauvaiseSyntaxeRegleException("Syntaxe de piece incorrecte (doit commencer par P)");
        }
        throw new MauvaiseSyntaxeRegleException("Syntaxe de piece incorrecte");
    }

    //Fonction renseignant si un joueur est correctement défini (String joueur)
    //JALL J1
    public static Jeton estSyntaxiquementCorrecte_Joueur(String joueur, int nbJoueur)throws MauvaiseDefinitionRegleException{
        if(joueur.length() > 1){
            if(joueur.charAt(0) == 'J'){
                if(joueur.length() >= 4 && joueur.charAt(1) == 'A') {
                    if (joueur.charAt(2) == 'L') {
                        if (joueur.charAt(3) == 'L') {
                            if(joueur.length() == 4){
                                return Jeton.JOUEUR;
                            }
                            throw new MauvaiseSyntaxeRegleException("Syntaxe du joueur (JALL + mauvais carac) incorrecte");
                        }
                        throw new MauvaiseSyntaxeRegleException("Syntaxe du joueur (JAL + mauvais carac) incorrecte");
                    }
                    throw new MauvaiseSyntaxeRegleException("Syntaxe du joueur (JA + mauvais carac) incorrecte");
                }else {
                    try {
                        int nb = Integer.parseInt(joueur.substring(1));
                        if(nb<nbJoueur){
                            if(nb>=0){
                                return Jeton.JOUEUR;
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
            throw new MauvaiseSyntaxeRegleException("Syntaxe du joueur incorrecte (doit commencer par J)");
        }
        throw new MauvaiseSyntaxeRegleException("Syntaxe du joueur incorrecte");
    }

    //Fonction renseignant si une piece+token est correctement définie
    public static Jeton estSyntaxiquementCorrecte_PieceToken(String piece, int nbJoueur, int nbPiece) throws MauvaiseDefinitionRegleException{
        String[] sousPiece = piece.split("#");
        if (sousPiece.length == 2) {
            try {
                estSyntaxiquementCorrecte_Piece(sousPiece[0], nbPiece);
                estSyntaxiquementCorrecte_Joueur(sousPiece[1], nbJoueur);
                return Jeton.PIECETOKEN;
            }catch (MauvaiseSyntaxeRegleException e){
                throw new MauvaiseSyntaxeRegleException("Syntaxe de pieceToken incorrecte [" + e.getMessage() + "]");
            }
        } else {
            throw new MauvaiseSyntaxeRegleException("Mauvais nombre de parametre de définition d'une pieceToken");
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
    }

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
