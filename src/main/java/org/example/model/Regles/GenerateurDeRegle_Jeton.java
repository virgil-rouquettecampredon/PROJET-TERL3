package org.example.model.Regles;

import org.example.model.Regles.Structure.Alias;
import org.example.model.Regles.Structure.Automate.*;

import java.io.Serializable;
import java.util.*;
import javafx.util.Pair;

public class GenerateurDeRegle_Jeton extends GenerateurDeRegle<Jeton> implements Serializable {



    public GenerateurDeRegle_Jeton(Automate_Regles<Jeton> auto, List<Jeton> jetons){
        super(jetons,auto);
    }

    public GenerateurDeRegle_Jeton(Automate_Regles<Jeton> auto){
        super(Arrays.asList(Jeton.values()),auto);
    }

    @Override
    public Jeton getToken(Jeton token) {
        Jeton j;
        return ((j = super.getToken(token)) == null)? Jeton.AUCUN : j;
    }

    @Override
    public Jeton estReconnu(String s) {
        Jeton j;
        return ((j = super.estReconnu(s)) == null)? Jeton.AUCUN : j;
    }

    /**Méthode permettant d'analyser syntaxiquement une regle sous forme de liste de termes.
     * Permet d'étiqueter les différents termes de la règle pour une analyse sémantique ensuite.
     * @param regle : règle à analyser sous forme de liste de termes (String)**/
    public List<Jeton> analyseSyntaxique(List<String> regle) throws MauvaiseSyntaxeRegleException{
        //Liste de jetons à retourner à la fin du traitement
        ArrayList<Jeton> regleSousFormeJeton = new ArrayList<>();
        //On remet la liste d'alias à zéro car elle n'est pas partagée entre les différentes règles
        listeAlias.clear();

        //Pour chacun des mots composant la règle
        for (int i = 0; i<regle.size();i++){
            Jeton curJeton;
            String curRegle = regle.get(i);
            System.out.println("anasyn: cur regle: "+curRegle);

            try {
                //Permets de reconnaitre des blocs NEGATION de la forme "N..."
                while(curRegle.length()>0 && estReconnu(curRegle.charAt(0) + "").equals(Jeton.NON)){
                    regleSousFormeJeton.add(Jeton.NON);
                    curRegle = curRegle.substring(1);
                    regle.set(i,curRegle);
                    regle.add(i,"NON");
                    i++;
                }
                switch ((curRegle.length() > 0)? curRegle.charAt(0) : ' ') {
                    /*CAS PIECE*/
                    case 'P' -> {
                        System.out.println("anasyn: PIECE");
                        //Si rien n'est reconnu (jeton aucun)
                        if((curJeton = estReconnu(curRegle)).equals(Jeton.AUCUN)){
                            //Alors on essaye de savoir pourquoi
                            Jeton jetonPT;
                            Jeton jetonP;
                            //Si notre système ne reconnait pas de pieceToken (jeton aucun)
                            if((jetonPT = getToken(Jeton.PIECETOKEN)).equals(Jeton.AUCUN)){
                                //Et si notre système ne reconnait pas de piece
                                if((jetonP = getToken(Jeton.PIECE)).equals(Jeton.AUCUN)){
                                    throw new MauvaiseSyntaxeRegleException("Impossible de reconnaire une piece ou une pieceToken");
                                }else{
                                    //Si notre système reconnait des PIECE, Alors c'est une erreur de définition de PIECE
                                    throw new MauvaiseSyntaxeRegleException(jetonP.getMessageErreur());
                                }
                            }else{
                                //Si on peut reconnaitre un jeton pieceToken mais que ca n'a pas été reconnu
                                //On essaye de voir si c'est parcequ'il n'y avait pas assez d'arguments
                                if(jetonPT.getMessageErreur().equals("Pas assez de parametre de définition d'une pieceToken")){
                                    //Si c'est le cas, alors on va regarder du coté de Jeton.PIECE
                                    //En effet, PIECE est une forme moins spécialisée de PIECETOKEN
                                    if((jetonP = getToken(Jeton.PIECE)).equals(Jeton.AUCUN)){
                                        //Si on ne reconnait pas de PIECE dans notre système
                                        //Alors c'est une erreur de définition côté PIECETOKEN
                                        throw new MauvaiseSyntaxeRegleException(jetonPT.getMessageErreur());
                                    }else{
                                        //Sinon, c'est une erreur de définition côté PIECE
                                        throw new MauvaiseSyntaxeRegleException(jetonP.getMessageErreur());
                                    }
                                }else{
                                    throw new MauvaiseSyntaxeRegleException(jetonPT.getMessageErreur());
                                }
                            }
                        }
                    }
                    /*CAS JOUEUR*/
                    case 'J' -> {
                        //Si rien n'est reconnu
                        if((curJeton = estReconnu(curRegle)).equals(Jeton.AUCUN)){
                            //Alors on va essayer de savoir pourquoi
                            Jeton jetonJ;
                            if((jetonJ = getToken(Jeton.JOUEUR)).equals(Jeton.AUCUN)){
                                //Si notre système ne reconnait pas de JOUEUR
                                throw new MauvaiseSyntaxeRegleException("Impossible de reconnaire un Joueur");
                            }else{
                                throw new MauvaiseSyntaxeRegleException(jetonJ.getMessageErreur());
                            }
                        }
                    }
                    /*CAS EQUIPE*/
                    case 'E' -> {
                        //Si rien n'est reconnu
                        if((curJeton = estReconnu(curRegle)).equals(Jeton.AUCUN)){
                            //Alors on va essayer de savoir pourquoi
                            Jeton jetonJ;
                            if((jetonJ = getToken(Jeton.JOUEUR)).equals(Jeton.AUCUN)){
                                //Si notre système ne reconnait pas de JOUEUR, et donc pas d'équipes
                                throw new MauvaiseSyntaxeRegleException("Impossible de reconnaire une Equipe");
                            }else{
                                throw new MauvaiseSyntaxeRegleException(jetonJ.getMessageErreur());
                            }
                        }
                    }
                    /*CAS CASE*/
                    case 'C' -> {
                        //Si rien n'est reconnu
                        if((curJeton = estReconnu(curRegle)).equals(Jeton.AUCUN)){
                            //Alors on va essayer de savoir pourquoi
                            Jeton jetonC;
                            if((jetonC = getToken(Jeton.CASE)).equals(Jeton.AUCUN)){
                                //Si notre système ne reconnait pas de CASE
                                throw new MauvaiseSyntaxeRegleException("Impossible de reconnaire une Case");
                            }else{
                                throw new MauvaiseSyntaxeRegleException(jetonC.getMessageErreur());
                            }
                        }
                    }
                    default -> {
                        //Si un jeton est reconnu
                        if(!(curJeton = estReconnu(curRegle)).equals(Jeton.AUCUN)){
                            //Si c'est un jeton alias de reconnus, alors on le supprime de la regle (as)
                            if(curJeton.equals(Jeton.ALIAS)){
                                try {
                                    //On ne garde ainsi que la valeur pour laquelle le jeton est associé
                                    regle.remove(i);
                                    //On va ensuite renseigner notre alias dans la table des alias
                                    listeAlias.add(regle.get(i));
                                }catch (IndexOutOfBoundsException e){
                                    throw new MauvaiseSyntaxeRegleException("Impossible d'appliquer un ALIAS");
                                }catch(UnsupportedOperationException ue){
                                    throw new MauvaiseSyntaxeRegleException("Création d'ALIAS impossible");
                                }
                            }
                            //Si c'est un jeton tous de reconnus, alors on le remplace par sa valeur correspondante
                            if(curJeton.equals(Jeton.TOUS)){
                                switch (curRegle){
                                    case "tous-piece" -> {
                                        regle.set(i,"PALL");
                                        curJeton = Jeton.PIECE;
                                    }
                                    case "tous-joueur" -> {
                                        regle.set(i,"JALL");
                                        curJeton = Jeton.JOUEUR;
                                    }
                                    case "tous-case" ->{
                                        regle.set(i,"CALL");
                                        curJeton = Jeton.CASE;
                                    }
                                    default -> {
                                        throw new MauvaiseSyntaxeRegleException("Macro TOUS inconnu");
                                    }
                                }
                            }
                        }else{
                            //Si aucun jeton n'arrive à reconnaitre un des termes de la Regle
                            boolean ctrl = false;
                            //Alors c'est qu'il s'agit peut être d'un alias
                            for (String s : listeAlias) {
                                //Si un des noms d'alias correspond à la valeur de notre terme courant
                                if(s.equals(curRegle)){
                                    //Alors c'est que l'utilisateur qui a définie la Regle veut réutiliser une alias précédement définie
                                    ctrl = true;
                                    curJeton = Jeton.ALIASREUTILISATION;
                                    break;
                                }
                            }
                            if(!ctrl){
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

    public void genererRegles() throws MauvaiseDefinitionRegleException{
        List<Jeton> regleSousFormeDejetons;
        List<String> regleSousFormeDeChaine;

        for (int i = 0; i < this.getNBReglesAEvaluer(); i++) {
            regleSousFormeDeChaine = this.getRegle(i);
            if(regleSousFormeDeChaine.size() >= 1){
                String premier = regleSousFormeDeChaine.remove(0);

                System.out.println(regleSousFormeDeChaine);

                //Va permettre de déterminer à quel type de règle on a à faire
                // 0 : Regle avant le coup d'un joueur
                // 1 : Regle après le coup d'un joueur
                if(premier.equals("0") || premier.equals("1")) {
                    try{
                        //Analyse syntaxique de la règle
                        //(que des termes connus du système composant la règle)
                        regleSousFormeDejetons = analyseSyntaxique(regleSousFormeDeChaine);
                        System.out.println(regleSousFormeDejetons);
                        //Analyse sémantique de la règle
                        //(bon agancement de termes connus du système)
                        Regle r = this.automate.analyserUneRegle(regleSousFormeDejetons,regleSousFormeDeChaine);
                        if(premier.equals("0")){
                            this.ajouterRegleAvantCoup(r);
                        }else{
                            this.ajouterRegleApresCoup(r);
                        }
                    }catch(MauvaiseSemantiqueRegleException | MauvaiseSyntaxeRegleException e){
                        throw new MauvaiseDefinitionRegleException("Problème à la règle num [" + i + "] => " + e.getMessage());
                    }
                }else{
                    throw new MauvaiseDefinitionRegleException("Problème à la règle num [" + i + "] => indentificateur de règle invalide {0,1}");
                }
            }else{
                throw new MauvaiseDefinitionRegleException("Problème à la règle num [" + i + "] => règle vide");
            }
        }
    }

    /*Getter et Setter*/
    public List<String> getListeAlias(){
        return listeAlias;
    }

    /*Méthode main permettant de tester au niveau du terminal le bon fonctionnement de la création des Regles*/
    public static void main(String[] args){
        //GESTION COULEUR
        String COLOR_RESET = "\u001B[0m";
        String COLOR_BLACK = "\u001B[30m";
        String COLOR_RED = "\u001B[31m";        //Erreurs
        String COLOR_GREEN = "\u001B[32m";      //Indices
        String COLOR_YELLOW = "\u001B[33m";     //Clef
        String COLOR_BLUE = "\u001B[34m";       //Infos importantes
        String COLOR_PURPLE = "\u001B[35m";
        String COLOR_CYAN = "\u001B[36m";       //Valeurs
        String COLOR_WHITE = "\u001B[37m";

        String BLACK_BRIGHT = "\033[0;90m";
        String RED_BRIGHT = "\033[0;91m";       //Arret
        String GREEN_BRIGHT = "\033[0;92m";
        String YELLOW_BRIGHT = "\033[0;93m";
        String BLUE_BRIGHT = "\033[0;94m";
        String PURPLE_BRIGHT = "\033[0;95m";    //Etape
        String CYAN_BRIGHT = "\033[0;96m";      //Valeur finale
        String WHITE_BRIGHT = "\033[0;97m";

        String arret = "#QUIT#";
        String messageArret = RED_BRIGHT + arret + COLOR_RESET + " pour terminer";
        Scanner scan = new Scanner(System.in);

        /*=============== Initialisation ===============*/
        Automate_Regles<Jeton> automate = new Automate_Regles_Semantique();
        automate.initialiserAutomate();
        GenerateurDeRegle<Jeton> generateur = new GenerateurDeRegle_Jeton(automate);

        //=======Liste des Joueurs
        List<String> joueurs = new ArrayList<>();
        //Initialisation des joueurs
        System.out.println(PURPLE_BRIGHT + "INITIALISATION : " +  COLOR_RESET + "Joueurs");
        System.out.println("Veuillez entrer " +COLOR_BLUE+"le nom des Joueurs"+COLOR_RESET+" que vous voulez renseigner dans vos "+COLOR_BLUE+"Regles"+COLOR_RESET);
        System.out.println(messageArret);
        String repJoueur = scan.next();
        while(!repJoueur.equals(arret)){
            joueurs.add(repJoueur);
            repJoueur = scan.next();
        }

        //=======Liste des Groupes de Cases
        List<String> groupCases = new ArrayList<>();
        //Initialisation des Groupes de Cases
        System.out.println(PURPLE_BRIGHT + "INITIALISATION : " +  COLOR_RESET + "Groupe de Cases");
        System.out.println("Veuillez entrer " +COLOR_BLUE+"le nom des groupes de cases"+COLOR_RESET+" que vous voulez renseigner dans vos "+COLOR_BLUE+"Regles"+COLOR_RESET);
        System.out.println(messageArret);
        String repGroupeCase = scan.next();
        while(!repGroupeCase.equals(arret)){
            groupCases.add(repGroupeCase);
            repGroupeCase = scan.next();
        }

        //=======Liste des Types de Pieces
        List<String> typePiece = new ArrayList<>();
        //Initialisation des Groupes de Cases
        System.out.println(PURPLE_BRIGHT + "INITIALISATION : " +  COLOR_RESET + "Type de Pieces");
        System.out.println("Veuillez entrer " +COLOR_BLUE+"le nom des types de pièces"+COLOR_RESET+" que vous voulez renseigner dans vos "+COLOR_BLUE+"Regles"+COLOR_RESET);
        System.out.println(messageArret);
        String repTypePiece = scan.next();
        while(!repTypePiece.equals(arret)){
            typePiece.add(repTypePiece);
            repTypePiece = scan.next();
        }

        //Liste des propositions affichables pour l'utilisateur
        List<Pair<String, String>> propositions = new ArrayList<>();
        propositions.add(new Pair<String, String>("ET", "et"));
        propositions.add(new Pair<String, String>("OU", "OU"));
        propositions.add(new Pair<String, String>("NON", "non"));
        propositions.add(new Pair<String, String>("alors", "alors"));

        propositions.add(new Pair<String, String>("alias", "as"));
        propositions.add(new Pair<String, String>("nombre", "0"));
        propositions.add(new Pair<String, String>("nom", ""));

        propositions.add(new Pair<String, String>("PREND", "prend"));
        propositions.add(new Pair<String, String>("SE DEPLACE", "sedeplace"));
        propositions.add(new Pair<String, String>("EST PLACE", "estplace"));
        propositions.add(new Pair<String, String>("EST SUR", "estsur"));
        propositions.add(new Pair<String, String>("EST ECHEC", "estechec"));

        propositions.add(new Pair<String, String>("EST PROMU", "estpromu"));

        propositions.add(new Pair<String, String>("nombre de déplacement", "nb_deplacement"));
        propositions.add(new Pair<String, String>("temps restant", "timer"));

        propositions.add(new Pair<String, String>("=", "="));
        propositions.add(new Pair<String, String>("<", "<"));
        propositions.add(new Pair<String, String>(">", ">"));

        propositions.add(new Pair<String, String>("VICTOIRE", "victoire"));
        propositions.add(new Pair<String, String>("DEFAITE", "defaite"));
        propositions.add(new Pair<String, String>("EGALITE","pat"));

        propositions.add(new Pair<String, String>("PRENDRE", "prendre"));
        propositions.add(new Pair<String, String>("PLACER", "placer"));
        propositions.add(new Pair<String, String>("PROMOUVOIR", "promouvoir"));
        propositions.add(new Pair<String, String>("DEPLACER", "deplacer"));

        propositions.add(new Pair<String, String>("PARENTHESE OUVRANTE", "("));
        propositions.add(new Pair<String, String>("PARENTHESE FERMANTE",")"));

        propositions.add(new Pair<String, String>("TOUS LES JOUEURS","tous-joueur"));
        propositions.add(new Pair<String, String>("TOUTES LES CASES","tous-typecase"));
        propositions.add(new Pair<String, String>("TOUTES LES PIECES","tous-piece"));

        //Renseignement des éléments joueurs comme éléments de Regle
        int ind = 0;
        for (String joueur: joueurs) {
            propositions.add(new Pair<String, String>(joueur,"J" + ind));
            ind++;
        }
        //Renseignement des éléments groupe de case comme éléments de Regle
        ind = 0;
        for (String cases: groupCases) {
            propositions.add(new Pair<String, String>(cases,"C" + ind));
            ind++;
        }
        //Renseignement des éléments type de pieces comme éléments de Regle
        ind = 0;
        for (String piece: typePiece) {
            propositions.add( new Pair<String,String>(piece,"P" + ind));
            ind++;
        }

        //Liste renseignant les choix de l'utilisateur
        List<Pair<String, String>> regleEnFormation = new ArrayList<>();

        //Liste renseignant les Regles créées
        List<Regle> regleAvantCoup = new ArrayList<>();
        List<Regle> regleApresCoup = new ArrayList<>();

        int indMAx = propositions.size();
        Jeton.JOUEUR.setBorne(joueurs.size());
        Jeton.CASE.setBorne(groupCases.size());
        Jeton.PIECE.setBorne(typePiece.size());

        String terminer = "";
        do {
            //Definition de la Regle
            System.out.println(PURPLE_BRIGHT + "DEFINITION REGLE" + COLOR_RESET);
            System.out.println("Veuillez renseigner la " + COLOR_BLUE + "position de la règle" + COLOR_RESET + " : \n" + COLOR_GREEN + "0" + COLOR_RESET + "->" + COLOR_YELLOW + "avant coup" + COLOR_RESET + "\n" + COLOR_GREEN + "1" + COLOR_RESET + "->" + COLOR_YELLOW + "après coup" + COLOR_RESET);
            String reponsePositionRegle = scan.next();
            while (!(reponsePositionRegle.equals("0") || reponsePositionRegle.equals("1"))) {
                System.out.println("Veuillez renseigner une " + COLOR_RED + "position valide" + COLOR_RESET + "(" + COLOR_GREEN + "0" + COLOR_RESET + " ou " + COLOR_GREEN + "1" + COLOR_RESET + ")");
                reponsePositionRegle = scan.next();
            }

            if (reponsePositionRegle.equals("0")) {
                System.out.println("Choix : Regle " + COLOR_YELLOW + "avant coup" + COLOR_RESET);
                regleEnFormation.add(new Pair<String, String>("Regle avant coup", "0"));
            } else {
                System.out.println("Choix : Regle " + COLOR_YELLOW + "apres coup" + COLOR_RESET);
                regleEnFormation.add(new Pair<String, String>("Regle après coup", "1"));
            }

            //Affichage des choix possibles

            System.out.println("CHOIX POSSIBLES (choisir un " + COLOR_GREEN + "indice" + COLOR_RESET + ") : ");
            System.out.println(messageArret);
            int indice = 1;
            for (Pair<String, String> valeurs : propositions) {
                System.out.println(COLOR_GREEN + indice + COLOR_RESET + ") " + COLOR_YELLOW + valeurs.getKey() + COLOR_RESET + "->" + COLOR_CYAN + valeurs.getValue() + COLOR_RESET);
                indice++;
            }

            //Affichage et traitement du choix
            System.out.print("Choix : " + COLOR_GREEN);
            String reponse = scan.next();
            System.out.print(COLOR_RESET);
            //Récupération de la création de l'utilisateur
            do {
                if (!reponse.equals(arret)) {
                    boolean bon = false;
                    int indRep = 0;
                    //Tant que l'indice n'est pas bon ou que ce n'est pas arret
                    while (!bon) {
                        try {
                            indRep = Integer.parseInt(reponse);
                            --indRep;
                            if (indRep < 0 || indRep > indMAx) {
                                throw new NumberFormatException("chiffre valide");
                            }
                            bon = true;
                        } catch (NumberFormatException e) {
                            if (e.getMessage().equals("chiffre valide")) {
                                System.out.print("Veuillez renseigner un " + COLOR_RED + "chiffre valide" + COLOR_RESET + " (entre " + COLOR_GREEN + "1" + COLOR_RESET + " et " + COLOR_GREEN + indMAx + COLOR_RESET + ") : " + COLOR_GREEN);
                            } else {
                                System.out.print("Veuillez renseigner un " + COLOR_RED + "chiffre" + COLOR_RESET + " : " + COLOR_GREEN);
                            }
                            reponse = scan.next();
                            System.out.print(COLOR_RESET);
                            if (reponse.equals(arret)) {
                                break;
                            }
                        }
                    }
                    if (reponse.equals(arret)) {
                        break;
                    }

                    Pair<String, String> choix = propositions.get(indRep);
                    //S'il veut renseigner un nom
                    if(indRep == 6){
                        //Alors on le prend en compte
                        System.out.print("Veuillez renseigner un " + COLOR_BLUE + "nom" + COLOR_RESET + " : ");
                        String nom = scan.next();
                        choix = new Pair<String,String>("nom",nom);
                    }

                    regleEnFormation.add(choix);
                    //Affichage et traitement du choix
                    System.out.println("Choix : " + COLOR_YELLOW + choix.getKey() + COLOR_RESET);

                    //Affichage des choix possibles
                    System.out.println("CHOIX POSSIBLES (choisir un " + COLOR_GREEN + "indice" + COLOR_RESET + ") : ");
                    System.out.println(messageArret);
                    indice = 1;
                    for (Pair<String, String> valeurs : propositions) {
                        System.out.println(COLOR_GREEN + indice + COLOR_RESET + ") " + COLOR_YELLOW + valeurs.getKey() + COLOR_RESET + "->" + COLOR_CYAN + valeurs.getValue() + COLOR_RESET);
                        indice++;
                    }

                    //Affichage et traitement du choix suivant
                    System.out.print("Choix : " + COLOR_GREEN);
                    reponse = scan.next();
                    System.out.print(COLOR_RESET);
                }
            } while (!reponse.equals(arret));

            List<String> regleSousFormeListe = new ArrayList<>();
            System.out.print(PURPLE_BRIGHT + "FIN DU TRAITEMENT : " + COLOR_RESET);
            for (Pair<String, String> pairRe : regleEnFormation) {
                System.out.print("[" + CYAN_BRIGHT + pairRe.getValue() + COLOR_RESET + "] ");
                regleSousFormeListe.add(pairRe.getValue());
            }
            regleEnFormation.clear();
            System.out.print(COLOR_RESET + "\n");

            generateur.addRegleSousFormeDeChaine(regleSousFormeListe);

            //Une fois que l'on possède la Règle sous forme de liste de termes, on peut l'analyser
            System.out.println(PURPLE_BRIGHT + "CREATION REGLE" + COLOR_RESET);
            try {
                generateur.genererRegles();
                regleAvantCoup.addAll(generateur.getRegleAvantCoup());
                regleApresCoup.addAll(generateur.getRegleApresCoup());

                System.out.println("ALIAS RECONNUS : \n" + COLOR_BLUE + generateur.toStringAlias() + COLOR_RESET);
                for(Map.Entry<String, Alias<Jeton,?>> entry : automate.getAliasRegle().entrySet()){
                    System.out.println(COLOR_CYAN + entry.getKey() +COLOR_RESET+ " -> " +COLOR_YELLOW+entry.getValue().getJetonAssocie()+COLOR_RESET);
                }

                System.out.print("ETAT : "+COLOR_GREEN + "SUCCES" + COLOR_RESET + "\n");
            } catch (MauvaiseDefinitionRegleException e) {
                System.out.print("ETAT : " + COLOR_RED + "ECHEC" + COLOR_RESET + " (" + e.getMessage() + ")\n");
            }

            generateur = new GenerateurDeRegle_Jeton(automate);
            automate.setEtatDeDepart(0);

            //Gestion fin de traitement
            System.out.println(PURPLE_BRIGHT + "TERMINER ? (O/N)" + COLOR_RESET);
            terminer = scan.next();
            while (!(terminer.equals("O") || terminer.equals("N"))){
                System.out.println(RED_BRIGHT + "OUI" +COLOR_RESET +  " OU " +  RED_BRIGHT + "NON" + COLOR_RESET + " (c'est pas compliqué)");
                terminer = scan.next();
            }

        }while (terminer.equals("N"));


        //L'utilisateur a finit de définir ses régles, on peut afficher ce qu'il a généré
        System.out.println("==========|" + PURPLE_BRIGHT + "ELEMENTS GENERES"+COLOR_RESET + "|==========");
        //On commence par ce qu'il peut réutiliser dans les règles
        // ================= JOUEURS =================
        System.out.println("=======|" + COLOR_PURPLE + "JOUEURS"+COLOR_RESET+"|=======");
        for (String joueur: joueurs) {
            System.out.println(COLOR_BLUE + "=>\t" + YELLOW_BRIGHT + joueur + COLOR_RESET);
        }
        // ============== GROUPES CASES ==============
        System.out.println("====|"+ COLOR_PURPLE + "GROUPES CASES"+COLOR_RESET + "|====");
        for (String cases: groupCases) {
            System.out.println(COLOR_BLUE + "=>\t" + YELLOW_BRIGHT + cases + COLOR_RESET);
        }
        // =============== TYPE PIECES ===============
        System.out.println("=====|" +COLOR_PURPLE + "TYPE PIECES"+COLOR_RESET+"|=====");
        for (String piece: typePiece) {
            System.out.println(COLOR_BLUE + "=>\t" + YELLOW_BRIGHT + piece + COLOR_RESET);
        }

        // ================== REGLES =================
        System.out.println("===============|" +PURPLE_BRIGHT + "REGLES"+COLOR_RESET+"|===============");
        System.out.println("========|" +COLOR_PURPLE + "AVANT COUP"+COLOR_RESET+"|========");
        for (Regle regleAvant: regleAvantCoup) {
            System.out.println(BLUE_BRIGHT+"Arbre Condition : "+ COLOR_RESET + regleAvant.getArbreCondition());
            System.out.println(BLUE_BRIGHT+"Consequences : " + COLOR_RESET);
            for (Consequence cons:regleAvant.getConsequences()) {
                System.out.println(GREEN_BRIGHT + cons +COLOR_RESET);
            }
        }
        System.out.println("========|" +COLOR_PURPLE + "APRES COUP"+COLOR_RESET+"|========");
        for (Regle regleApres: regleApresCoup) {
            System.out.println(BLUE_BRIGHT+"Arbre Condition : "+ COLOR_RESET + regleApres.getArbreCondition());
            System.out.println(BLUE_BRIGHT+"Consequences : " + COLOR_RESET);
            for (Consequence cons:regleApres.getConsequences()) {
                System.out.println(GREEN_BRIGHT + cons +COLOR_RESET);
            }
        }
    }
}
