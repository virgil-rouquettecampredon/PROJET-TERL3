package org.example.model.Regles;

import org.example.model.Piece;

import java.lang.reflect.Array;
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



    /**Classes utiles pour l'analyse sémantique
     * Permet une modélisation simple d'un automate reconnaissant des Jeton**/

    //Modélisation d'une transition sortante d'un état de l'automate
    class TransitionSortante_Semantique{
        private Jeton etiquetteArete;
        private int etatArrive;
        public TransitionSortante_Semantique(Jeton etiquette,int arrive){
            this.etiquetteArete = etiquette;
            this.etatArrive = arrive;
        }
    }

    //Modélisation d'un état de l'automate
    class Etat_Semantique{
        private int num;
        private List<TransitionSortante_Semantique> transitions;
        private boolean estTerminal;
        private int codeDeRetour;

        public Etat_Semantique(int num){
            this.num = num;
            this.transitions = new ArrayList<>();
            this.estTerminal = false;
            this.codeDeRetour = 0;
        }

        public void ajouterTransitionSortante(TransitionSortante_Semantique t){
            this.transitions.add(t);
        }

        public boolean estTerminal(){
            return estTerminal;
        }
    }

    //Modélisation de l'automate
    class Automate_Semantique {
        private int nbEtat;
        private int nbEtatTerminaux;
        private List<Etat_Semantique> etatsTr;

        public Automate_Semantique(int nbEtat){
            this.nbEtat = nbEtat;
            nbEtatTerminaux = 0;
            int i = 0;
            while (i<nbEtat){
                etatsTr.add(new Etat_Semantique(i));
                i++;
            }
        }

        public void ajouterUnEtatTerminal(int etat, int codeDeRetour){
            if(etat<nbEtat && etat>=0){
                etatsTr.get(etat).estTerminal = true;
                etatsTr.get(etat).codeDeRetour = codeDeRetour;
                this.nbEtatTerminaux++;
            }
        }

        public void ajouterUneTransition(int dep, Jeton val, int arrive){
            for (Etat_Semantique e: etatsTr) {
                if(e.num == dep){
                    Iterator<TransitionSortante_Semantique> t = e.transitions.iterator();
                    while (t.hasNext()) {
                        if(t.next().etiquetteArete == val){
                            //Permet d'empécher les automates indeterministes
                            t.remove();
                        }
                    }
                    e.ajouterTransitionSortante(new TransitionSortante_Semantique(val,arrive));
                }
            }
        }

        /*
        private boolean estReconnu(int sommet,Jeton val){
            for (Etat_Semantique e: etatsTr) {
                if(e.num ==sommet){
                    for(TransitionSortante_Semantique t:e.transitions){
                        if(t.etiquetteArete == val){
                            return true;
                        }
                    }
                    return false;
                }
            }
            return false;
        }*/
        public int etatSuivant(int sommet, Jeton val){
            for (Etat_Semantique e: etatsTr) {
                if(e.num ==sommet){
                    for(TransitionSortante_Semantique t:e.transitions){
                        if(t.etiquetteArete == val){
                            return t.etatArrive;
                        }
                    }
                    return -1;
                }
            }
            return -1;
        }

        public Etat_Semantique recupererEtat(int etat){
            return etatsTr.get(etat);
        }
    }

    public Automate_Semantique initialiserAutomate_Semantique(List<Integer> etatTerminaux){
        Automate_Semantique autoSem = new Automate_Semantique(23);
        //GESTIONS ETATS TERMINAUX
        int ind = 0;
        for (Integer i:etatTerminaux) {
            autoSem.ajouterUnEtatTerminal(i,300+ind);
            ind++;
        }

        //GESTION DES ALIAS
        autoSem.ajouterUneTransition(1,Jeton.ALIAS,1);
        autoSem.ajouterUneTransition(2,Jeton.ALIAS,2);
        autoSem.ajouterUneTransition(3,Jeton.ALIAS,3);
        autoSem.ajouterUneTransition(9,Jeton.ALIAS,9);
        autoSem.ajouterUneTransition(10,Jeton.ALIAS,10);
        autoSem.ajouterUneTransition(11,Jeton.ALIAS,11);

        //GESTION NEGATION
        autoSem.ajouterUneTransition(4,Jeton.NON,4);
        autoSem.ajouterUneTransition(7,Jeton.NON,7);

        //GESTION DES NEGATIONS


        //=========== CONDITIONS ===========
        //ETAT INITIAL
        autoSem.ajouterUneTransition(0,Jeton.JOUEUR,1);
        autoSem.ajouterUneTransition(0,Jeton.PIECE,2);
        autoSem.ajouterUneTransition(0,Jeton.PIECETOKEN,3);

        //ETAT 1
        autoSem.ajouterUneTransition(1,Jeton.COMPTEUR,4);

        //ETAT 2
        autoSem.ajouterUneTransition(2,Jeton.ACTION,5);
        autoSem.ajouterUneTransition(2,Jeton.ETAT,6);
        autoSem.ajouterUneTransition(2,Jeton.COMPTEUR,7);
        autoSem.ajouterUneTransition(2,Jeton.JOUEUR,3);

        //ETAT 3
        autoSem.ajouterUneTransition(3,Jeton.ACTION,5);
        autoSem.ajouterUneTransition(3,Jeton.ETAT,6);
        autoSem.ajouterUneTransition(3,Jeton.COMPTEUR,7);

        //ETAT 4
        autoSem.ajouterUneTransition(4,Jeton.COMPARAISON,8);

        //ETAT 5
        autoSem.ajouterUneTransition(5,Jeton.PIECE,9);
        autoSem.ajouterUneTransition(5,Jeton.PIECETOKEN,10);
        autoSem.ajouterUneTransition(5,Jeton.CASE,11);

        //ETAT 6
        autoSem.ajouterUneTransition(6,Jeton.ET,0);
        autoSem.ajouterUneTransition(6,Jeton.OU,0);
        autoSem.ajouterUneTransition(6,Jeton.ALORS,15);

        //ETAT 7
        autoSem.ajouterUneTransition(7,Jeton.COMPARAISON,12);

        //ETAT 8
        autoSem.ajouterUneTransition(8,Jeton.NOMBRE,13);

        //ETAT 9
        autoSem.ajouterUneTransition(9,Jeton.JOUEUR,10);
        autoSem.ajouterUneTransition(9,Jeton.ET,0);
        autoSem.ajouterUneTransition(9,Jeton.OU,0);
        autoSem.ajouterUneTransition(9,Jeton.ALORS,15);

        //ETAT 10
        autoSem.ajouterUneTransition(10,Jeton.ET,0);
        autoSem.ajouterUneTransition(10,Jeton.OU,0);
        autoSem.ajouterUneTransition(10,Jeton.ALORS,15);

        //ETAT 11
        autoSem.ajouterUneTransition(11,Jeton.ET,0);
        autoSem.ajouterUneTransition(11,Jeton.OU,0);
        autoSem.ajouterUneTransition(11,Jeton.ALORS,15);

        //ETAT 12
        autoSem.ajouterUneTransition(12,Jeton.COMPTEUR,14);

        //ETAT 13
        autoSem.ajouterUneTransition(13,Jeton.ET,0);
        autoSem.ajouterUneTransition(13,Jeton.OU,0);
        autoSem.ajouterUneTransition(13,Jeton.ALORS,15);

        //ETAT 14
        autoSem.ajouterUneTransition(14,Jeton.ET,0);
        autoSem.ajouterUneTransition(14,Jeton.OU,0);
        autoSem.ajouterUneTransition(14,Jeton.ALORS,15);

        //=========== CONDITIONS ===========
        //ETAT 15
        autoSem.ajouterUneTransition(15,Jeton.JOUEUR,16);
        autoSem.ajouterUneTransition(15,Jeton.PIECE,17);
        autoSem.ajouterUneTransition(15,Jeton.PIECETOKEN,18);

        //ETAT 16
        autoSem.ajouterUneTransition(16,Jeton.CONSEQUENCEACTION,20);
        autoSem.ajouterUneTransition(16,Jeton.CONSEQUENCETERMINALE,19);

        //ETAT 17
        autoSem.ajouterUneTransition(17,Jeton.JOUEUR,18);
        autoSem.ajouterUneTransition(17,Jeton.CONSEQUENCEACTION,20);

        //ETAT 18
        autoSem.ajouterUneTransition(18,Jeton.CONSEQUENCEACTION,20);

        //ETAT 20
        autoSem.ajouterUneTransition(20,Jeton.PIECE,21);
        autoSem.ajouterUneTransition(20,Jeton.PIECETOKEN,22);
        autoSem.ajouterUneTransition(20,Jeton.CASE,23);

        //ETAT 21
        autoSem.ajouterUneTransition(21,Jeton.ET,20);
        autoSem.ajouterUneTransition(21,Jeton.JOUEUR,22);

        //ETAT 22
        autoSem.ajouterUneTransition(22,Jeton.ET,20);

        //ETAT 23
        autoSem.ajouterUneTransition(23,Jeton.ET,20);
        autoSem.ajouterUneTransition(23,Jeton.PIECETOKEN,22);
        autoSem.ajouterUneTransition(23,Jeton.PIECE,21);

        return  autoSem;
    }

    //Java : les var-args

    public void analyseSemantique(List<Jeton> regleSyntaxe, List<String> regle){
        //Etat initial par défaut : 0
        int curEtat = 0;
        //regles de etat-transitions de l'automate de notre système
        Automate_Semantique automateSem = initialiserAutomate_Semantique(new ArrayList<>(Arrays.asList(6,9,10,11,13,14,19,21,22,23)));
        //Pile de gestion des blocs de règle parcourus
        List<String> blocsParcourus = new ArrayList<>();


        for (Jeton jetons: regleSyntaxe) {
            //Récupération de l'indice du prochain état d'après la transition donnée
            curEtat = automateSem.etatSuivant(curEtat,jetons);

            //Récupération de l'état correspondant à l'indice curEtat
            Etat_Semantique etat = automateSem.recupererEtat(curEtat);
            if(etat.estTerminal){
                //Si terminal, traitement du code de retour
                switch (etat.codeDeRetour){
                    case 300 -> /*traitement*/ System.out.println("");
                    case 301 -> /*traitement*/ System.out.println("");
                    case 302 -> /*traitement*/ System.out.println("");
                    case 303 -> /*traitement*/ System.out.println("");
                    case 304 -> /*traitement*/ System.out.println("");
                    case 305 -> /*traitement*/ System.out.println("");
                    case 306 -> /*traitement*/ System.out.println("");
                    case 307 -> /*traitement*/ System.out.println("");
                    case 308 -> /*traitement*/ System.out.println("");
                    case 309 -> /*traitement*/ System.out.println("");
                }
            }
        }
    }


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
            case "mange","sedeplace","estplace","estsur"-> et = Jeton.ACTION;
            case "estpromu","estechec"-> et = Jeton.ETAT;
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
     * note :
     *          AUCUN,NOMBRE,
     *          PIECE, JOUEUR, CASE,
     *          ACTION, CIBLE, ETAT,
     *          COMPARAISON, ET, OU, CONSEQUENCE,NON
     **/
    public void estSemantiquementCorrecte(ArrayList<Jeton> lt, ArrayList<String> reglestr) throws MauvaiseSemantiqueRegleException {
        int c = 0;    //curseur
        Regle regle = new Regle();
        //int nbContexte=2;
        //enum Contexte{A, B};

        /*Map<Etat, Map<Integer, ArrayList<Etat>>> map_etats = new HashMap<>();
        for(Etat e: Etat.values()){
            map_etats.put(e, new HashMap<>());
            for (int i=1; i<=nbContexte; i++){
                map_etats.get(e).put(i, new ArrayList<>());
            }
        }

        map_etats.get(Etat.PIECE).get(1).add(Etat.JOUEUR); //.add(Etat.JOUEURALL);
        map_etats.get(Etat.JOUEUR).get(1).add(Etat.NOMBRE);
        map_etats.get(Etat.ACTION).get(1).add(Etat.NOMBRE);*/

        while (lt.get(c) == Jeton.NON) { regle.setBool(!regle.getBool()); c++; }

        do {
            if (lt.get(c) == Jeton.PIECE) {
                //interpreter Piece
                c++;
                if (lt.get(c) == Jeton.ACTION) {
                    //"mange","sedeplace","estplace","estechec","estsur"
                    if (reglestr.get(c).equals("mange")) {
                        c++;
                        if (lt.get(c) == Jeton.CASE) {
                            //interpreter Case
                            //CAS PIECE+MANGE+CASE

                        } else if (lt.get(c) == Jeton.PIECE) {
                            //interpreter Piece
                            //CAS PIECE+MANGE+PIECE
                        } else {
                            throw new MauvaiseSemantiqueRegleException("manger: cible \"" + reglestr.get(c) + "\" incorrect (mot num " + c + "), une case ou une piece est attendu");
                        }
                    } else if (reglestr.get(c).equals("sedeplace")) {
                        if (lt.get(c) == Jeton.CASE) {
                            //interpreter Case
                            //CAS PIECE+SEDEPLACER+CASE
                        } else {
                            throw new MauvaiseSemantiqueRegleException("sedeplace: cible \"" + reglestr.get(c) + "\" incorrect (mot num " + c + "), une case est attendu");
                        }
                    } else if (reglestr.get(c).equals("estplace")) {
                        if (lt.get(c) == Jeton.CASE) {
                            //interpreter Case
                            //CAS PIECE+ESTPLACE+CASE

                        } else {
                            throw new MauvaiseSemantiqueRegleException("estplace: cible \"" + reglestr.get(c) + "\" incorrect (mot num " + c + "), une case est attendu");
                        }
                    } else if (reglestr.get(c).equals("estechec")) {
                        if (lt.get(c) == Jeton.PIECE) {
                            //interpreter Piece
                            //CAS PIECE+ESTECHEC+PIECE

                        } else {
                            throw new MauvaiseSemantiqueRegleException("estechec: cible \"" + reglestr.get(c) + "\" incorrect (mot num " + c + "), une piece est attendu");
                        }
                    } else if (reglestr.get(c).equals("estsur")) {
                        if (lt.get(c) == Jeton.CASE) {
                            //interpreter Case
                            //CAS PIECE+ESTSUR+CASE

                        } else {
                            throw new MauvaiseSemantiqueRegleException("estsur: cible \"" + reglestr.get(c) + "\" incorrect (mot num " + c + "), une case est attendu");
                        }
                    } else {
                        throw new MauvaiseSemantiqueRegleException("\"" + reglestr.get(c) + "\" incorrect (mot num " + c + "), une action est attendu.");
                    }

                } else if (lt.get(c) == Jeton.ETAT) {
                    //"estpromu"
                    if (reglestr.get(c).equals("estpromu")) {
                        //CAS PIECE+ESTPROMU
                    } else {
                        throw new MauvaiseSemantiqueRegleException("\"" + reglestr.get(c) + "\" incorrect (mot num " + c + "), un etat est attendu.");
                    }

                } else if (lt.get(c) == Jeton.COMPTEUR) {
                    if (reglestr.get(c).equals("nb_deplacement")) {
                        c++;
                        if (lt.get(c) == Jeton.COMPARAISON) {
                            c++;
                            if (lt.get(c) == Jeton.NOMBRE) {
                                //CAS PIECE+NBDEPLACEMENT+COMPARAISON(=<>)+NOMBRE
                            } else {
                                throw new MauvaiseSemantiqueRegleException("\"" + reglestr.get(c) + "\" incorrect (mot num " + c + "), un nombre est attendu.");
                            }
                        } else {
                            throw new MauvaiseSemantiqueRegleException("\"" + reglestr.get(c) + "\" incorrect (mot num " + c + "), une comparaison est attendu.");
                        }
                    } else {
                        throw new MauvaiseSemantiqueRegleException("\"" + reglestr.get(c) + "\" incorrect (mot num " + c + "), un compteur est attendu.");
                    }
                } else {
                    throw new MauvaiseSemantiqueRegleException("\"" + reglestr.get(c) + "\" incorrect (mot num " + c + "), une action, un etat ou un compteur est attendu");
                }
            }
            else if (lt.get(c) == Jeton.JOUEUR) {
                //interpreter Joueur
                c++;
                if (lt.get(c) == Jeton.COMPTEUR) {
                    if (reglestr.get(c).equals("timer")) {
                        c++;
                        if (lt.get(c) == Jeton.COMPARAISON) {
                            c++;
                            if (lt.get(c) == Jeton.NOMBRE) {
                                //CAS JOUEUR+TIMER+COMPARAISON(=<>)+NOMBRE
                            } else {
                                throw new MauvaiseSemantiqueRegleException("\"" + reglestr.get(c) + "\" incorrect (mot num " + c + "), un nombre est attendu.");
                            }
                        } else {
                            throw new MauvaiseSemantiqueRegleException("\"" + reglestr.get(c) + "\" incorrect (mot num " + c + "), une comparaison est attendu.");
                        }
                    } else {
                        throw new MauvaiseSemantiqueRegleException("\"" + reglestr.get(c) + "\" incorrect (mot num " + c + "), un timer est attendu.");
                    }
                } else {
                    throw new MauvaiseSemantiqueRegleException("\"" + reglestr.get(c) + "\" incorrect (mot num " + c + "), un compteur est attendu.");
                }
            }

            else {
                throw new MauvaiseSemantiqueRegleException("\"" + reglestr.get(c) + "\" incorrect (mot num " + c + "), un joueur ou une piece est attendu.");
            }

            c++;
        } while(lt.get(c) == Jeton.ET || lt.get(c) == Jeton.OU);

        if (lt.get(c) == Jeton.CONSEQUENCE) {
            //interpreter consequence
        }
        else {
            throw new MauvaiseSemantiqueRegleException("\"" + reglestr.get(c) + "\" incorrect (mot num " + c + "), un OU, un ET ou une conséquence est attendu.");
        }

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
