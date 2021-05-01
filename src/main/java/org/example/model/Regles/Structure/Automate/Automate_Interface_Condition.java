

package org.example.model.Regles.Structure.Automate;

import java.util.*;

import org.example.model.GroupCases;
import org.example.model.Piece;
import org.example.model.Joueur;
import org.example.model.Plateau;
import org.example.model.Regles.ElementRegle;
import org.example.model.Regles.Jeton_Interface;
import org.example.model.Regles.MauvaiseDefinitionRegleException;

import static org.junit.Assert.fail;

public class Automate_Interface_Condition extends Automate_Interface<Jeton_Interface> {

    private List<Piece> pieces;             //Liste des Pieces réutilisables dans les règles
    private List<GroupCases> cases;         //Liste des Groupes de cases réutilisables dans les règles
    private List<Joueur> joueurs;           //Liste des Joueurs réutilisables dans les règles

    private Deque<String> aliasCrees;       //Pile des noms d'alias générés

    private int nbParenthese;               //Nombre de parenthèses de la règle

    public Automate_Interface_Condition(List<Piece> pieces, List<GroupCases> cases, List<Joueur> joueurs) {
        super(34, 0);
        this.pieces = pieces;
        this.cases = cases;
        this.joueurs = joueurs;
        nbParenthese = 0;

        aliasCrees = new ArrayDeque<>();
    }

    public void initialiserAutomate() {
        //GESTION DES ETATS TERMINAUX
        this.ajouterUnEtatTerminal(5,300);
        this.ajouterUnEtatTerminal(16,300);
        this.ajouterUnEtatTerminal(17,300);
        this.ajouterUnEtatTerminal(14,300);
        this.ajouterUnEtatTerminal(12,300);
        this.ajouterUnEtatTerminal(15,300);

        this.ajouterUnEtatTerminal(22,301);
        this.ajouterUnEtatTerminal(23,301);
        this.ajouterUnEtatTerminal(24,301);
        this.ajouterUnEtatTerminal(26,301);
        this.ajouterUnEtatTerminal(25,301);

        //GESTION DES ALIAS
        this.ajouterUneTransition(1,Jeton_Interface.ALIASDEF,27);
        this.ajouterUneTransition(27,Jeton_Interface.ALIAS,19);
        this.ajouterUneTransition(19,Jeton_Interface.COMPTEUR_TEMPSRESTANT,4);

        this.ajouterUneTransition(2,Jeton_Interface.ALIASDEF,28);
        this.ajouterUneTransition(28,Jeton_Interface.ALIAS,20);
        this.ajouterUneTransition(20,Jeton_Interface.COMPTEUR_DEPLACEMENT,4);
        this.ajouterUneTransition(20,Jeton_Interface.PROMU,5);
        this.ajouterUneTransition(20,Jeton_Interface.ESTMENACE,6);
        this.ajouterUneTransition(20,Jeton_Interface.PREND,7);
        this.ajouterUneTransition(20,Jeton_Interface.DEPLACE,8);
        this.ajouterUneTransition(20,Jeton_Interface.PLACE,9);
        this.ajouterUneTransition(20,Jeton_Interface.EST,10);

        this.ajouterUneTransition(3,Jeton_Interface.ALIASDEF,29);
        this.ajouterUneTransition(29,Jeton_Interface.ALIAS,21);
        this.ajouterUneTransition(21,Jeton_Interface.COMPTEUR_DEPLACEMENT,4);
        this.ajouterUneTransition(21,Jeton_Interface.PROMU,5);
        this.ajouterUneTransition(21,Jeton_Interface.ESTMENACE,6);
        this.ajouterUneTransition(21,Jeton_Interface.PREND,7);
        this.ajouterUneTransition(21,Jeton_Interface.DEPLACE,8);
        this.ajouterUneTransition(21,Jeton_Interface.PLACE,9);
        this.ajouterUneTransition(21,Jeton_Interface.EST,10);

        this.ajouterUneTransition(12,Jeton_Interface.ALIASDEF,31);
        this.ajouterUneTransition(31,Jeton_Interface.ALIAS,23);
        this.ajouterUneTransition(23,Jeton_Interface.PARENTHESE_FERMANTE,17);

        this.ajouterUneTransition(14,Jeton_Interface.ALIASDEF,30);
        this.ajouterUneTransition(30,Jeton_Interface.ALIAS,22);
        this.ajouterUneTransition(22,Jeton_Interface.PARENTHESE_FERMANTE,17);

        this.ajouterUneTransition(15,Jeton_Interface.ALIASDEF,32);
        this.ajouterUneTransition(32,Jeton_Interface.ALIAS,24);
        this.ajouterUneTransition(24,Jeton_Interface.PARENTHESE_FERMANTE,17);

        this.ajouterUneTransition(25,Jeton_Interface.ALIASDEF,33);
        this.ajouterUneTransition(33,Jeton_Interface.ALIAS,26);
        this.ajouterUneTransition(26,Jeton_Interface.PARENTHESE_FERMANTE,17);

        //ETAT INITIAL
        this.ajouterUneTransition(0,Jeton_Interface.PARENTHESE_OUVRANTE,0);
        this.ajouterUneTransition(0,Jeton_Interface.JOUEUR,1);
        this.ajouterUneTransition(0,Jeton_Interface.PIECE,2);
        this.ajouterUneTransition(0,Jeton_Interface.PIECETOKEN,3);
        this.ajouterUneTransition(0,Jeton_Interface.NON,18);

        //ETAT 1
        this.ajouterUneTransition(1,Jeton_Interface.COMPTEUR_TEMPSRESTANT,4);

        //ETAT 2
        this.ajouterUneTransition(2,Jeton_Interface.JOUEUR,3);
        this.ajouterUneTransition(2,Jeton_Interface.COMPTEUR_DEPLACEMENT,4);
        this.ajouterUneTransition(2,Jeton_Interface.PROMU,5);
        this.ajouterUneTransition(2,Jeton_Interface.ESTMENACE,6);
        this.ajouterUneTransition(2,Jeton_Interface.PREND,7);
        this.ajouterUneTransition(2,Jeton_Interface.DEPLACE,8);
        this.ajouterUneTransition(2,Jeton_Interface.PLACE,9);
        this.ajouterUneTransition(2,Jeton_Interface.EST,10);

        //ETAT 3
        this.ajouterUneTransition(3,Jeton_Interface.COMPTEUR_DEPLACEMENT,4);
        this.ajouterUneTransition(3,Jeton_Interface.PROMU,5);
        this.ajouterUneTransition(3,Jeton_Interface.ESTMENACE,6);
        this.ajouterUneTransition(3,Jeton_Interface.PREND,7);
        this.ajouterUneTransition(3,Jeton_Interface.DEPLACE,8);
        this.ajouterUneTransition(3,Jeton_Interface.PLACE,9);
        this.ajouterUneTransition(3,Jeton_Interface.EST,10);

        //ETAT 4
        this.ajouterUneTransition(4,Jeton_Interface.NON,11);
        this.ajouterUneTransition(4,Jeton_Interface.COMPARATEUR,13);

        //ETAT 5
        this.ajouterUneTransition(5,Jeton_Interface.PARENTHESE_FERMANTE,17);

        //ETAT 6
        this.ajouterUneTransition(6,Jeton_Interface.PIECETOKEN,14);
        this.ajouterUneTransition(6,Jeton_Interface.PIECE,12);

        //ETAT 7
        this.ajouterUneTransition(7,Jeton_Interface.PIECE,12);
        this.ajouterUneTransition(7,Jeton_Interface.PIECETOKEN,14);
        this.ajouterUneTransition(7,Jeton_Interface.CASE,15);
        this.ajouterUneTransition(7,Jeton_Interface.CASEALIAS,15);
        this.ajouterUneTransition(7,Jeton_Interface.CASEPARAM,25);

        //ETAT 8
        this.ajouterUneTransition(8,Jeton_Interface.CASE,15);
        this.ajouterUneTransition(8,Jeton_Interface.CASEALIAS,15);
        this.ajouterUneTransition(8,Jeton_Interface.CASEPARAM,25);

        //ETAT 9
        this.ajouterUneTransition(9,Jeton_Interface.CASE,15);
        this.ajouterUneTransition(9,Jeton_Interface.CASEALIAS,15);
        this.ajouterUneTransition(9,Jeton_Interface.CASEPARAM,25);

        //ETAT 10
        this.ajouterUneTransition(10,Jeton_Interface.CASE,15);
        this.ajouterUneTransition(10,Jeton_Interface.CASEALIAS,15);
        this.ajouterUneTransition(10,Jeton_Interface.CASEPARAM,25);

        //ETAT 11
        this.ajouterUneTransition(11,Jeton_Interface.COMPARATEUR,13);

        //ETAT 12
        this.ajouterUneTransition(12,Jeton_Interface.JOUEUR,14);
        this.ajouterUneTransition(12,Jeton_Interface.PARENTHESE_FERMANTE,17);

        //ETAT 13
        this.ajouterUneTransition(13,Jeton_Interface.NOMBRE,16);

        //ETAT 14
        this.ajouterUneTransition(14,Jeton_Interface.PARENTHESE_FERMANTE,17);

        //ETAT 15
        this.ajouterUneTransition(15,Jeton_Interface.PARENTHESE_FERMANTE,17);
        this.ajouterUneTransition(15,Jeton_Interface.CASE,25);

        //ETAT 16
        this.ajouterUneTransition(16,Jeton_Interface.PARENTHESE_FERMANTE,17);

        //ETAT 17
        this.ajouterUneTransition(17,Jeton_Interface.PARENTHESE_FERMANTE,17);

        //ETAT 18
        this.ajouterUneTransition(18,Jeton_Interface.PARENTHESE_OUVRANTE,0);

        //ETAT 25
        this.ajouterUneTransition(25,Jeton_Interface.PARENTHESE_FERMANTE,17);
    }

    public void revenirEnArriere() {
        if (etatsParcourus.size() > 0) {
            curEtat = etatsParcourus.removeLast();
            Jeton_Interface j = jetonsReconnus.removeLast();
            //Si on rejette une parenthèse fermante ou ouvrante, on doit MAJ nbParenthèse
            if (j == Jeton_Interface.PARENTHESE_FERMANTE) {
                ++nbParenthese;
            }
            if (j == Jeton_Interface.PARENTHESE_OUVRANTE) {
                --nbParenthese;
            }
            //Si on rejette un Alias, on doit MAJ la liste des alias en conséquence
            if(j == Jeton_Interface.ALIAS){
                try {
                    alias.remove(aliasCrees.removeLast());
                }catch(NoSuchElementException ignore){/*Si plus d'alias à enlever, tant pis*/}
            }
        } else {
            //Si on ne peut pas/plus revenir en arrière, on recommence au début
            curEtat = 0;
        }
    }

    public List<ElementRegle> elementSelectionnables()throws MauvaiseDefinitionRegleException {
        //Liste des éléments à retourner
        List<ElementRegle> elements = new ArrayList<>();

        //Récupération de l'état courant
        Etat e = this.recupererEtat(curEtat);
        if(e == null){
            throw new MauvaiseDefinitionRegleException("Etat courant inconnu : " + curEtat);
        }

        //Si on est dans un etat terminal dans une définition de Condition
        if (e.estTerminal()) {
            //Alors on peut également proposer des connecteurs de définition d'un nouveau bloc Condition
            elements.add(new ElementRegle(Jeton_Interface.OU,"OU", "OU"));
            elements.add(new ElementRegle(Jeton_Interface.ET,"ET", "et"));
            //Ou encore des connecteurs liés à la fin de définition des Conditions
            if(nbParenthese <= 0){
                elements.add(new ElementRegle(Jeton_Interface.ALORS,"ALORS", "alors"));
            }
        }
        //Sinon pour chacune des transitions sortante de notre état
        for (TransitionSortante t : e.getTransitions()) {
            int ind = 0;
            //On va générer les bons ElementDeRegles en conséquence
            switch (t.getEtiquetteArete()) {
                case CASE -> {
                    elements.add(new ElementRegle(Jeton_Interface.CASE,"Toutes les cases", "tous-case"));
                    ind = 1;
                    for (GroupCases gc : cases) {
                        elements.add(new ElementRegle(Jeton_Interface.CASE,gc.getName(), "C" + ind));
                        ind++;
                    }
                }
                case JOUEUR -> {
                    elements.add(new ElementRegle(Jeton_Interface.JOUEUR,"Tous les joueurs", "tous-joueur"));
                    for (Joueur j : joueurs) {
                        elements.add(new ElementRegle(Jeton_Interface.JOUEUR,j.getName(), "J" + ind));
                        ind++;
                    }
                }
                case PIECE -> {
                    elements.add(new ElementRegle(Jeton_Interface.PIECE,"Toutes les pieces", "tous-piece"));
                    for (Piece p : pieces) {
                        elements.add(new ElementRegle(Jeton_Interface.PIECE,p.getName(), "P" + ind));
                        ind++;
                    }
                }
                default -> {
                    for (String s : t.getEtiquetteArete().getElementsReconnaissables()) {
                        if(nbParenthese > 0 || t.getEtiquetteArete() != Jeton_Interface.PARENTHESE_FERMANTE){
                            elements.add(new ElementRegle(t.getEtiquetteArete(),s, s));
                        }
                    }
                }
            }
            //Gestion des alias
            for (Map.Entry<String, Jeton_Interface> entry : getAlias().entrySet()){
                if(entry.getValue() == t.getEtiquetteArete()){
                    elements.add(new ElementRegle(entry.getValue(), entry.getKey(), entry.getKey()));
                }
            }
        }
        return elements;
    }

    public void selectionnerElement(ElementRegle elR) throws MauvaiseDefinitionRegleException{
        //On ajoute l'état dans lequel on est actuellement au parcours
        etatsParcourus.addLast(curEtat);

        //Puis on gère ensuite le choix de l'utilisateur
        jetonsReconnus.addLast(elR.getJetonAssocie());
        //Gestion des parenthèses
        if(elR.getJetonAssocie() == Jeton_Interface.PARENTHESE_OUVRANTE) ++nbParenthese;
        if(elR.getJetonAssocie() == Jeton_Interface.PARENTHESE_FERMANTE){
            if(nbParenthese == 0){
                throw new MauvaiseDefinitionRegleException("Impossible d'appliquer une parenthèse fermante ici");
            }
            --nbParenthese;
        }

        //Gestion des alias
        if(elR.getJetonAssocie() == Jeton_Interface.ALIAS){
            //On ajoute ce nom d'alias à la liste des Alias renseignés (backtrack)
            aliasCrees.addLast(elR.getNomRegle());

            //Puis on ajoute cet Alias à la liste des alias connus de l'automate
            switch (curEtat){
                case 27 -> alias.put(elR.getNomRegle(),Jeton_Interface.JOUEUR);
                case 28 , 31 -> alias.put(elR.getNomRegle(),Jeton_Interface.PIECE);
                case 29 , 30 -> alias.put(elR.getNomRegle(),Jeton_Interface.PIECETOKEN);
                case 32 -> alias.put(elR.getNomRegle(),Jeton_Interface.CASEALIAS);
                case 33 -> alias.put(elR.getNomRegle(), Jeton_Interface.CASEPARAM);
                default -> throw new MauvaiseDefinitionRegleException("Impossible d'appliquer un ALIAS ici : " + curEtat);
            }
        }

        //On récupère l'état suivant en fonction du choix donné en paramètre
        int etat = this.etatSuivant(curEtat, elR.getJetonAssocie());
        //On récupère aussi l'état actuel dans lequel on se trouve
        Etat e = this.recupererEtat(curEtat);
        if(e == null){
            throw new MauvaiseDefinitionRegleException("Etat courant inconnu : " + curEtat);
        }
        if(e.estTerminal()){
            if(elR.getJetonAssocie() == Jeton_Interface.OU || elR.getJetonAssocie() == Jeton_Interface.ET) {
                //Si on se trouve sur un état terminal et que l'on cherche à lire un ET ou un OU
                //On a finit de traiter une condition, on retourne donc à l'état initial
                curEtat = 0;
                return;
            }else{
                if(elR.getJetonAssocie() == Jeton_Interface.ALORS){
                    //Si on est sur un etat terminal et que l'on cherche à lire un ALORS
                    //Il faut vérifier si on peut bien faire une transition ALORS et passer à l'automate d'évaluation des consequences ou non
                    if(nbParenthese>0){
                        String mesErr = (nbParenthese == 1)? "une parenthèse fermante" : nbParenthese + " parenthèses fermantes";
                        throw new MauvaiseDefinitionRegleException("Impossible de terminer les conséquences, il manque encore " + mesErr);
                    }
                    //Si on est dans un etat terminal, que l'on cherche à lire un ALORS et qu'il n'y a pas de problèmes de parenthèsage
                    //Alors l'état curEtat vaudra -1 (seul cas ou ca vaut -1 sans être une erreur)
                    curEtat = -1;
                    return;
                }
            }
        }
        //Si on est pas sur un etat terminal et que la transition que l'on veut lire n'existe pas (etatSuivant = -1), on lève une erreur
        if(etat == -1){
            throw new MauvaiseDefinitionRegleException("Transition inconnue : " + curEtat + " --" + elR.getJetonAssocie() + "-> ?");
        }
        //Sinon on valide le choix au niveau de l'automate en déplacant le curseur curEtat
        curEtat = etat;
    }

    /*Méthode main permettant de tester au niveau du terminal le bon fonctionnement de l'automate*/
    public static void main(String[] args) {
        //GESTION COULEUR
        String COLOR_RESET = "\u001B[0m";
        String COLOR_BLACK = "\u001B[30m";
        String COLOR_RED = "\u001B[31m";
        String COLOR_GREEN = "\u001B[32m";
        String COLOR_YELLOW = "\u001B[33m";
        String COLOR_BLUE = "\u001B[34m";
        String COLOR_PURPLE = "\u001B[35m";
        String COLOR_CYAN = "\u001B[36m";
        String COLOR_WHITE = "\u001B[37m";

        String BLACK_BRIGHT = "\033[0;90m";  // BLACK
        String RED_BRIGHT = "\033[0;91m";    // RED
        String GREEN_BRIGHT = "\033[0;92m";  // GREEN
        String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
        String BLUE_BRIGHT = "\033[0;94m";   // BLUE
        String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
        String CYAN_BRIGHT = "\033[0;96m";   // CYAN
        String WHITE_BRIGHT = "\033[0;97m";  // WHITE

        //Liste des éléments pouvant composer notre règle
        List<Joueur> joueurs = new ArrayList<>();
        List<GroupCases> cases = new ArrayList<>();
        List<Piece> pieces = new ArrayList<>();

        joueurs.add(new Joueur("toto",1));
        joueurs.add(new Joueur("titi",0));

        cases.add(new GroupCases("MonGroupeDeCases",new Plateau()));

        pieces.add(new Piece("Roi",""));
        pieces.add(new Piece("Fou",""));
        pieces.add(new Piece("Cavalier",""));

        //Création et initialisation
        Automate_Interface<Jeton_Interface> auto = new Automate_Interface_Condition(pieces,cases,joueurs);
        auto.initialiserAutomate();

        Scanner scan = new Scanner(System.in);
        String regle = "";

        List<ElementRegle> regleChoix = new ArrayList<>();
        try{
            while (auto.getCurEtat() != -1) {
                //Récupération des éléments à afficher
                List<ElementRegle> elem = auto.elementSelectionnables();
                String reponse = "";
                ElementRegle choix = null;

                //Gestion cas numéro à rentrer
                if(elem.size() == 1 && elem.get(0).getJetonAssocie() == Jeton_Interface.NOMBRE){
                    boolean bon = false;
                    String mes = "Veuillez entrer un numéro valide ("+ COLOR_RED + "#P" +COLOR_RESET+ " pour revenir en arrière) :";
                    while(!bon){
                        try {
                            System.out.println(mes);
                            String rep = scan.next();
                            //Si on veut revenir en arrière
                            if(rep.equals("#P") && regleChoix.size()>0){
                                int etatEnleve = auto.getCurEtat();
                                auto.revenirEnArriere();
                                ElementRegle elemSup =  regleChoix.remove(regleChoix.size()-1);
                                //Gestion chaine de caractère regle
                                regle = regle.substring(0,regle.lastIndexOf("]"));
                                regle = regle.substring(0,regle.lastIndexOf("]") +1);
                                System.out.println("Retour en arrière : " + COLOR_RED + "suppression " + COLOR_RESET + "de " + BLUE_BRIGHT + elemSup.getNomInterface()+ CYAN_BRIGHT +" ("+ etatEnleve+")" + COLOR_RESET);
                                break;
                            }
                            int indRep = Integer.parseInt(rep);
                            reponse = rep;
                            bon = true;
                            choix = elem.get(0);
                            choix.setNomInterface(rep);
                        }catch (NumberFormatException nex){
                            mes = COLOR_RED + "J'ai dit valide : " + COLOR_RESET;
                        }
                    }
                    //Si on revient en arrière
                    if(!bon){
                        System.out.println("Regle : " + regle + "\n");
                        continue;
                    }
                }else {
                    //Gestion cas alias à rentrer
                    if(elem.size() == 1 && elem.get(0).getJetonAssocie() == Jeton_Interface.ALIAS) {
                        boolean bon = false;
                        String mes = "Veuillez entrer un nom d'alias valide (" + COLOR_RED + "#P" + COLOR_RESET + " pour revenir en arrière) :";
                        while (!bon) {
                            System.out.println(mes);
                            String rep = scan.next();
                            //Si on veut revenir en arrière
                            if (rep.equals("#P") && regleChoix.size() > 0) {
                                int etatEnleve = auto.getCurEtat();
                                auto.revenirEnArriere();
                                ElementRegle elemSup = regleChoix.remove(regleChoix.size() - 1);
                                //Gestion chaine de caractère regle
                                regle = regle.substring(0, regle.lastIndexOf("]"));
                                regle = regle.substring(0, regle.lastIndexOf("]") + 1);
                                System.out.println("Retour en arrière : " + COLOR_RED + "suppression " + COLOR_RESET + "de " + BLUE_BRIGHT + elemSup.getNomInterface() + CYAN_BRIGHT + " (" + etatEnleve + ")" + COLOR_RESET);
                                break;
                            } else {
                                if (auto.peutEtreRenseigne(rep)) {
                                    reponse = rep;
                                    bon = true;
                                    choix = elem.get(0);
                                    choix.setNomInterface(rep);
                                } else {
                                    mes = "Nom interdit : " + COLOR_RED + rep + COLOR_RESET;
                                }
                            }
                        }
                        //Si on revient en arrière
                        if (!bon) {
                            System.out.println("Regle : " + regle + "\n");
                            continue;
                        }
                    }else {
                        //Affichage des choix possibles
                        if (regleChoix.size() > 0) {
                            System.out.println("Elements possibles (sélectionner " + COLOR_GREEN + "indice" + COLOR_RESET + " ou " + COLOR_RED + "#P " + COLOR_RESET + "pour revenir en arrière) : ");
                        } else {
                            System.out.println("Elements possibles (sélectionner " + COLOR_GREEN + "indice" + COLOR_RESET + ") : ");
                        }
                        int ind = 1;
                        for (ElementRegle e : elem) {
                            System.out.println(COLOR_GREEN + ind + COLOR_RESET + " -> " + e.getNomInterface());
                            ind++;
                        }
                        //Affichage et traitement du choix
                        System.out.print("Choix : " + COLOR_GREEN);
                        String rep = scan.next();
                        System.out.print(COLOR_RESET);

                        //Gestion retour en arrière
                        if (rep.equals("#P") && regleChoix.size() > 0) {
                            int etatEnleve = auto.getCurEtat();
                            auto.revenirEnArriere();
                            ElementRegle elemSup = regleChoix.remove(regleChoix.size() - 1);
                            //Gestion chaine de caractères regle
                            regle = regle.substring(0, regle.lastIndexOf("]"));
                            regle = regle.substring(0, regle.lastIndexOf("]") + 1);
                            System.out.println("Retour en arrière : " + COLOR_RED + "suppression " + COLOR_RESET + "de " + BLUE_BRIGHT + elemSup.getNomInterface() + CYAN_BRIGHT + " (" + etatEnleve + ")" + COLOR_RESET);
                            System.out.println("Regle : " + regle + "\n");
                            continue;
                        } else {
                            int indRep = Integer.parseInt(rep);
                            --indRep;
                            if (indRep < 0 || indRep > ind) {
                                throw new MauvaiseDefinitionRegleException("Seul un nombre valide est autorisé");
                            } else {
                                choix = elem.get(indRep);
                                reponse = choix.getNomInterface();
                            }
                        }
                    }
                }
                //Gestion + Affichage Regle à un instant t de l'exécution
                regleChoix.add(choix);
                regle += "[" + COLOR_BLUE + reponse + COLOR_RESET +"]";
                System.out.println("Regle : " + regle);
                auto.selectionnerElement(choix);

                //Affichage etats parcourus dans l'automate
                System.out.print("Etats déjà parcourus : " + PURPLE_BRIGHT);
                for (Integer i: auto.etatsParcourus) {
                    System.out.print(i +" ");
                }
                System.out.println(COLOR_RESET + "(" + auto.getCurEtat()+")\n");
            }

            //AFFICHAGE DE LA REGLE
            System.out.println("REGLE CREE : ");
            Iterator<ElementRegle> ite = regleChoix.iterator();
            while (ite.hasNext()){
                ElementRegle e = ite.next();
                if(e != null){
                    if(e.getJetonAssocie() == Jeton_Interface.NON){
                        ElementRegle eBis = ite.next();
                        if(eBis != null){
                            String s;
                            switch (eBis.getNomInterface()){
                                case "="-> s = "!=";
                                case "<"-> s = ">=";
                                case ">"-> s = "<=";
                                default -> s= eBis.getNomInterface();
                            }
                            System.out.print(COLOR_PURPLE + "{" + COLOR_CYAN + s + COLOR_PURPLE+ "}" + COLOR_RESET);
                        }
                    }else {
                        if(e.getJetonAssocie() == Jeton_Interface.PARENTHESE_OUVRANTE || e.getJetonAssocie() == Jeton_Interface.PARENTHESE_FERMANTE){
                            System.out.print(e.getNomInterface());
                        }else{
                            if(e.getJetonAssocie() != Jeton_Interface.ET && e.getJetonAssocie() != Jeton_Interface.OU && e.getJetonAssocie() != Jeton_Interface.ALORS){
                                System.out.print(COLOR_PURPLE + "{" + COLOR_CYAN + e.getNomInterface() + COLOR_PURPLE+ "}" + COLOR_RESET);
                            }else{
                                System.out.print(COLOR_YELLOW +e.getNomInterface()+ COLOR_RESET);
                            }
                        }
                    }
                }
            }

            //AFFICHAGE ALIAS
            System.out.println("\nALIAS : ");
            for (Map.Entry<String, Jeton_Interface> entry : auto.getAlias().entrySet()) {
                System.out.println(COLOR_BLUE + entry.getKey() + COLOR_RESET + "->" + COLOR_GREEN + entry.getValue() + COLOR_RESET);
            }
        }catch(MauvaiseDefinitionRegleException ex){
            System.err.println("Exception détectée : " + ex.getMessage());
        }
        catch(NumberFormatException nex){
            System.err.println("Seul un nombre est autorisé");
        }
    }
}
