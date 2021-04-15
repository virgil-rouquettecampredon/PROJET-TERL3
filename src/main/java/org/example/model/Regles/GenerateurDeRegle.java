package org.example.model.Regles;

import org.example.model.Regles.Structure.Automate.Automate_Regles;

import java.util.*;

public abstract class GenerateurDeRegle<A extends EstToken> {

    //Liste des règles données sous forme de chaine de caractère (liste de mots) à la construction du GenerateurDeRegle
    private List<List<String>> reglesSousFormeDeChaine;

    //Liste d'objets Tokken utile pour l'analyse syntaxique
    protected List<A> objetsSyntaxiques;
    //Automate utilisé pour l'analyse semantique
    protected Automate_Regles<A> automate;

    //Listes de Regle
    private List<Regle> regleAvantCoup;
    private List<Regle> regleApresCoup;

    public GenerateurDeRegle(List<A> objetsSemantiques, Automate_Regles<A> automate){
        this.automate = automate;
        this.objetsSyntaxiques = objetsSemantiques;

        this.regleAvantCoup = new ArrayList<>();
        this.regleApresCoup = new ArrayList<>();
        reglesSousFormeDeChaine = new ArrayList<>();
    }

    public List<Regle> getRegleAvantCoup() {
        return regleAvantCoup;
    }

    public List<Regle> getRegleApresCoup() {
        return regleApresCoup;
    }

    /**Méthode permettant d'ajouter une règle avant coup.
    * @param r: Regle à ajouter.**/
   public void ajouterRegleAvantCoup(Regle r){
       this.regleAvantCoup.add(r);
   }

    /**Méthode permettant d'ajouter une règle après coup.
     * @param r: Regle à ajouter.**/
    public void ajouterRegleApresCoup(Regle r){
        this.regleApresCoup.add(r);
    }

    /**Méthode permettant de renseigner des règles (sous forme de suite de mot) à faire
     * analyser par le GenerateurDeRegle
     * @param regles : Attribut variable de règles sous forme de liste de mots.**/
    public void addRegleSousFormeDeChaine(List<String>... regles){
        this.reglesSousFormeDeChaine.addAll(Arrays.asList(regles));
    }

    /**Méthode permettant de récupérer le nombre de règles sous forme de liste de mots à anlyser.**/
    public int getNBReglesAEvaluer(){
        return this.reglesSousFormeDeChaine.size();
    }

    /**Méthode permettant de récupérer une règle à l'indice passé en paramètre
     * @param indice : indice de l'élément de objetsSyntaxiques à retourner.
     * @return La règle sous forme de liste de mots à l'indice indice si elle existe, null sinon **/
    public List<String> getRegle(int indice){
        try {
            return reglesSousFormeDeChaine.get(indice);
        }catch(IndexOutOfBoundsException  e){
            return null;
        }
    }

    /**Méthode permettant de récupérer un Token de objetsSyntaxiques, s'il existe
     * @param token: Token de objetsSyntaxiques à récupérer.
     * @return : Un Token de objetsSyntaxiques égal à token s'il existe, null sinon.**/
    public A getToken(A token){
        for (A t: objetsSyntaxiques) {
            if(t.equals(token)){
                return t;
            }
        }
        return null;
    }

    /**Méthode permettant de récupérer un Token de objetsSyntaxiques s'il reconnait la String s
     * @param s: String à tester pour savoir si un élément de objetsSyntaxiques (Liste de Token) la reconnait.
     * @return : Le premier Token de objetsSyntaxiques qui reconnait la chaine s, null sinon.**/
    public A estReconnu(String s){
        for (A t: objetsSyntaxiques) {
            if(t.estReconnu(s)){
                return t;
            }
        }
        return null;
    }

    /**Méthode permettant de générer les règles qui seront ajoutées dans les deux listes de r**/
    public abstract void genererRegles() throws MauvaiseDefinitionRegleException;
/*
    //Tableau des axiomes reconnaissables par notre système
    //Utile lors de l'analyse syntaxique dans un premier temps,
    //puis semantique ensuite
    private static final String[] axiomestab = {
            "mange", "sedeplace", "estpromu", "estsur", "estechec", "nb_deplacement", "estplace", "timer",  /*conditions
            "=", "<", ">",                                                                                  /*comparaisons
            "tous-piece", "tous-joueur", "tous-typecase",                                                   /*tokens nimporte
            "victoire", "defaite", "pat", "prendre", "placer", "promouvoir", "deplacer",                      /*consequences
            "as"
    };

    private static final String[] connecteur = {"ET","OU","alors"};

    /**Constructeur d'un GenerateurDeRegle
     * @param reglesSousFormeDeChaine : Tableau de Liste de Chaines de caractères. Chaque liste représente une règle de jeu :
     *                                [0] : ["0","PALL","sedeplace","C2","manger","PALL"]
     *                                [1] : ["1","JALL","timer","=","3","ET","PALL","estechec","victoire"]
     *                                [2] : ["0","PALL#J2#P", "ET","PALL#J2#P","estsur","C5","defaite"]
     *
    public GenerateurDeRegle(List<String>[] reglesSousFormeDeChaine, Automate<Jeton> auto) {
        this.reglesSousFormeDeChaine = reglesSousFormeDeChaine;
        this.regleAvantCoup = new ArrayList<>();
        this.regleApresCoup = new ArrayList<>();

        this.automate_semantique = auto;
        this.automate_semantique.initialiserAutomate();
    }

    /**===================================================================================
     * ================================ ANALYSE SYNTAXIQUE ===============================
     * ===================================================================================
     *
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
            case "victoire", "defaite", "pat" -> et = Jeton.CONSEQUENCETERMINALE;
            case "prendre", "placer", "promouvoir", "deplacer" -> et = Jeton.CONSEQUENCEACTION;
            case "alors" -> et = Jeton.ALORS;
            case "as" -> et = Jeton.ALIAS;
        }
        return et;
    }

    /**Transforme une regle sous forme de chaine de caractères en une liste de jetons, analysables par la partie sémantique.
     * @param regle : ArrayList<String> renseignant la liste des regles à transformer sous forme de jetons.
     * @param nbTypePiece : int renseignant le nombre de type de pieces du jeu.
     * @param nbTypeDeCase : int renseignant le nombre de type de cases du jeu.
     * @param nbJoueur :  int renseignant le nombre de joueurs du jeu.
     *
    public static ArrayList<Jeton> estSyntaxiquementCorrecte(List<String> regle, int nbTypePiece, int nbTypeDeCase, int nbJoueur) throws MauvaiseDefinitionRegleException{
        ArrayList<Jeton> regleSousFormeJeton = new ArrayList<>();

        for (int i = 0; i<regle.size();i++){
            Jeton curJeton;
            String curRegle = regle.get(i);

            try {
                while(curRegle.charAt(0) == 'N'){
                    regleSousFormeJeton.add(Jeton.NON);
                    curRegle = curRegle.substring(1);
                }
                switch (curRegle.charAt(0)) {
                    /*CAS PIECE
                    case 'P' -> curJeton = estSyntaxiquementCorrecte_PieceToken(curRegle, nbJoueur,nbTypePiece);
                    /*CAS JOUEUR
                    case 'J' -> curJeton = estSyntaxiquementCorrecte_Joueur(curRegle,nbJoueur);
                    /*CAS CASE
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
                                if(etAx == Jeton.ALIAS){
                                    regle.remove(i);
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
                            if(cases.length() == 4) {
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
    public static Jeton estSyntaxiquementCorrecte_PieceToken(String piece, int nbJoueur, int nbPiece) throws MauvaiseDefinitionRegleException {
        String[] sousPiece = piece.split("#");
        if (sousPiece.length == 2) {
            try {
                estSyntaxiquementCorrecte_Piece(sousPiece[0], nbPiece);
                estSyntaxiquementCorrecte_Joueur(sousPiece[1], nbJoueur);
                return Jeton.PIECETOKEN;
            } catch (MauvaiseSyntaxeRegleException e) {
                throw new MauvaiseSyntaxeRegleException("Syntaxe de pieceToken incorrecte [" + e.getMessage() + "]");
            }
        } else {
            throw new MauvaiseSyntaxeRegleException("Mauvais nombre de parametre de définition d'une pieceToken");
        }
    }


    /*Fonction qui va se charger de transformer une regle sous forme d'une chaine de caractere,
     * en une instance de REGLE manipulable plus simplement par le système.
    public void analyser (List<List<SujetDeRegle>> sujetsDeRegle, List<List<CibleDeRegle>> ciblesDeRegle) throws MauvaiseDefinitionRegleException {
        int ind = 0;

        //Pour chaque règles du jeu définies
        for(List<String> reglestring: this.reglesSousFormeDeChaine){
            if(reglestring.size() >= 1){
                String premier = reglestring.remove(0);
                if(premier.equals("0") || premier.equals("1")) {
                    try{
                        List<Jeton> regleJeton = estSyntaxiquementCorrecte(reglestring, type_piece.size(), type_case.size(), joueurs.size());
                        Regle r = this.automate_semantique.analyserUneRegle(regleJeton,reglestring);

                        if(premier.equals("0")){
                            this.regleAvantCoup.add(r);
                        }else{
                            this.regleApresCoup.add(r);
                        }
                    }catch(MauvaiseSemantiqueRegleException | MauvaiseSyntaxeRegleException e){
                        throw new MauvaiseDefinitionRegleException("Problème à la règle num [" + ind + "] => " + e.getMessage());
                    }
                }else{
                    throw new MauvaiseDefinitionRegleException("Problème à la règle num [" + ind + "] => indentificateur de règle invalide {0,1}");
                }
            }else{
                throw new MauvaiseDefinitionRegleException("Problème à la règle num [" + ind + "] => règle vide");
            }
            ind++;
        }
    }*/
}
