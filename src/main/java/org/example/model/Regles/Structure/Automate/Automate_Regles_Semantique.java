package org.example.model.Regles.Structure.Automate;


import java.util.*;

import org.example.model.*;
import org.example.model.Regles.*;
import org.example.model.Regles.Structure.*;
import org.example.model.Regles.Structure.Interpreteur.*;


/** Automate_Regle_Semantique verifie la sémantique d'une règle à l'aide d'un Automate_Règle.
 * Si la règle est correct, analyserUneRegle retourne l'objet Regle correspondant,
 * Sinon, la méthode retourne une MauvaiseSemantiqueRegleException avec le message adequat.
 * */
public class Automate_Regles_Semantique extends Automate_Regles<Jeton>{

    public Automate_Regles_Semantique(){
        super(37,0);
        aliasRegle = new HashMap<>();
    }

    public Automate_Regles_Semantique(List<String> nomEtat){
        super(37,0, nomEtat);
        aliasRegle = new HashMap<>();
    }

    public void ajouterAlias(String nomAlias, String parcours) throws MauvaiseDefinitionRegleException{
        System.out.println("CREATION ALIAS");
        switch (parcours){
            case "0259" , "0359" , "02359" , /*ALIAS : 0259*/ "0R259" , "025R9" , "0R25R9" , /*ALIAS : 0359*/ "0R359" , "035R9" , "0R35R9" ,/*ALIAS : 02359*/ "0R2359" , "02R359" , "0235R9" , "02R35R9" , "0R235R9"  , "0R2R359" , "0R2R35R9" -> aliasRegle.put(nomAlias,new Alias<Jeton,Piece>(Jeton.PIECE,false)/*Alias_Cible(Jeton.PIECE)*/);
            case "025910" , "02510" , "0235910" ,"023510" , "035910" , "03510" , /*ALIAS : 025910*/ "0R25910" , "025R910" , "259R10" , "025R9R10" , "0R259R10" , "0R25R910" , "0R25R9R10" , /*ALIAS : 02510*/ "0R2510" , "025R10" , "0R25R10" , /*ALIAS : 0235910*/ "0R235910" , "02R35910" , "0235R910" , "02359R10" , "0235R9R10" , "02R359R10" , "02R35R910" , "0R2359R10" , "0R235R910" , "0R2R35910" , "02R35R9R10" , "0R235R9R10" , "0R2R359R10" , "0R2R35R910" , "0R2R35R9R10" , /*ALIAS : 023510*/ "0R23510" , "02R3510" , "0235R10" , "02R35R10" , "0R235R10" , "0R2R3510" , "0R2R35R10" , /*ALIAS : 035910*/ "0R35910" , "035R910" , "0359R10" , "035R9R10" , "0R359R10" , "0R35R910" , "0R35R9R10" , /*ALIAS : 03510*/ "0R3510" , "035R10" , "0R35R10" -> aliasRegle.put(nomAlias,new Alias<Jeton,Piece>(Jeton.PIECETOKEN,false)/*Alias_Cible(Jeton.PIECETOKEN)*/);
            case "01" ,/*ALIAS : 01*/ "0R1"-> aliasRegle.put(nomAlias,new Alias<Jeton,Joueur>(Jeton.JOUEUR,true)/*Alias_Sujet(Jeton.JOUEUR)*/);
            case "02" , /*ALIAS : 02*/ "0R2" -> aliasRegle.put(nomAlias,new Alias<Jeton,Piece>(Jeton.PIECE,true)/*Alias_Sujet(Jeton.PIECE)*/);
            case "023" , "03" , /*ALIAS : 023*/ "0R23" , "02R3" , "0R2R3" , /*ALIAS : 03*/ "0R3" -> aliasRegle.put(nomAlias,new Alias<Jeton,Piece>(Jeton.PIECETOKEN,true)/*Alias_Sujet(Jeton.PIECETOKEN)*/);
            case "02511" , "023511" , "03511" , /*ALIAS : 02511*/ "0R2511" , "025R11" , "0R25R11" , /*ALIAS : 023511*/ "0R23511" , "02R3511" , "0235R11" , "02R35R11" , "0R235R11" , "0R2R3511" , "0R2R35R11" , /*ALIAS : 03511*/ "0R3511" , "035R11" , "0R35R11" -> aliasRegle.put(nomAlias,new Alias<Jeton,GroupCases>(Jeton.CASE,false)/*Alias_Cible(Jeton.CASE)*/);
            case "0251127" , "02351127" , "0351127" , "02527" , "023527" , "03527" , /*ALIAS : 0251127*/ "0R251127" , "025R1127" , "02511R27" , "025R11R27" , "0R2511R27" , "0R25R1127" , "0R25R11R27" , /*ALIAS : 02351127*/ "0R2351127" , "02R351127" , "0235R1127" , "023511R27" , "0235R11R27" , "02R3511R27" , "02R35R1127" , "0R23511R27" , "0R235R1127" , "0R2R351127" , "02R35R11R27" , "0R235R11R27" , "0R2R3511R27" , "0R2R35R1127" , "0R2R35R11R27" , /*ALIAS : 0351127*/ "0R351127" , "035R1127" , "03511R27" , "035R11R27" , "0R3511R27" , "0R35R1127" , "0R35R11R27" , /*ALIAS : 02527*/ "0R2527" , "025R27" , "0R25R27" , /*ALIAS : 023527*/ "0R23527" , "02R3527" , "0235R27" , "02R35R27" , "0R235R27" , "0R2R3527" , "0R2R35R27", /*ALIAS : 03527*/ "0R3527" , "035R27" , "0R35R27" -> aliasRegle.put(nomAlias,new Alias<Jeton,GroupCases>(Jeton.CASEPARAM,false)/*Alias_Cible(Jeton.CASEPARAM)*/);
            default -> throw new MauvaiseDefinitionRegleException("Impossible de définir l'alias " + nomAlias);
        }
    }

    public Alias<Jeton,?> recupererAlias(String nomAlias)throws MauvaiseDefinitionRegleException{
        System.out.println("RECUPERATION ALIAS");
        Alias<Jeton,?> alias = aliasRegle.get(nomAlias);
        if(alias == null){
            throw new MauvaiseDefinitionRegleException("Alias inconnu : " + nomAlias);
        }
        return alias;
    }

    public void initialiserAutomate(){
        //GESTIONS ETATS TERMINAUX
        List<Integer> etatTer = new ArrayList<>(Arrays.asList(6,9,10,11,13,14,19,21,22,23,27,31,32,33,34));
        for (Integer i:etatTer) {
            this.ajouterUnEtatTerminal(i,300+i);
        }

        //GESTION DES NEGATIONS (COMPARATEURS)
        this.ajouterUneTransition(4,Jeton.NON,35);
        this.ajouterUneTransition(35,Jeton.COMPARAISON,8);
        this.ajouterUneTransition(7,Jeton.NON,36);
        this.ajouterUneTransition(36,Jeton.COMPARAISON,12);

        //Permet de ne pas renseigner la traversée de ces états dans le parcours de l'automate
        //La négation d'une comparaison se fera au moment de la détection du NON, indépendamment de son traitement au sein de l'automate
        this.setValeurEtat(35,"");
        this.setValeurEtat(36,"");

        //Permet de ne pas renseigner la traversée de cet état dans le parcours de l'automate
        //La négation d'une condition ne change pas fondamentalement le traitement sémantique de celle-ci
        //L'inversion est ici gérée au niveau de la création de l'arbre condition de la règle
        this.setValeurEtat(26,"");
        this.ajouterUneTransition(0,Jeton.NON,26);
        this.ajouterUneTransition(24,Jeton.NON,26);


        //GESTION DU PARENTHESAGE
        this.ajouterUneTransition(0,Jeton.PARENTHESEOUVRANTE,24);
        this.ajouterUneTransition(24,Jeton.PARENTHESEOUVRANTE,24);
        this.ajouterUneTransition(26,Jeton.PARENTHESEOUVRANTE,24);

        this.ajouterUneTransition(24,Jeton.JOUEUR,1);
        this.ajouterUneTransition(24,Jeton.PIECE,2);
        this.ajouterUneTransition(24,Jeton.PIECETOKEN,3);

        //Permet de ne pas renseigner la traversée de cet état dans le parcours de l'automate
        //Le parenthésage ne change pas le traitement sémantique d'un bloc de regle
        this.setValeurEtat(25,"");
        this.setValeurEtat(24,"");

        this.ajouterUneTransition(13,Jeton.PARENTHESEFERMANTE,25);
        this.ajouterUneTransition(9,Jeton.PARENTHESEFERMANTE,25);
        this.ajouterUneTransition(10,Jeton.PARENTHESEFERMANTE,25);
        this.ajouterUneTransition(11,Jeton.PARENTHESEFERMANTE,25);
        this.ajouterUneTransition(14,Jeton.PARENTHESEFERMANTE,25);
        this.ajouterUneTransition(6,Jeton.PARENTHESEFERMANTE,25);
        this.ajouterUneTransition(27,Jeton.PARENTHESEFERMANTE,25);
        this.ajouterUneTransition(25,Jeton.PARENTHESEFERMANTE,25);

        this.ajouterUneTransition(25,Jeton.ET,0);
        this.ajouterUneTransition(25,Jeton.OU,0);
        this.ajouterUneTransition(25,Jeton.ALORS,15);

        //GESTION DES ALIAS
        //Permet de ne pas renseigner la traversée de cet état dans le parcours de l'automate
        //Les alias ne change pas le traitement sémantique d'un bloc de regle
        this.setValeurEtat(28,"");
        this.setValeurEtat(29,"");
        this.setValeurEtat(30,"");
        this.setValeurEtat(31,"");
        this.setValeurEtat(32,"");
        this.setValeurEtat(33,"");
        this.setValeurEtat(34,"");

        this.ajouterUneTransition(1,Jeton.ALIAS,28);
        this.ajouterUneTransition(2,Jeton.ALIAS,29);
        this.ajouterUneTransition(3,Jeton.ALIAS,30);
        this.ajouterUneTransition(9,Jeton.ALIAS,31);
        this.ajouterUneTransition(10,Jeton.ALIAS,32);
        this.ajouterUneTransition(11,Jeton.ALIAS,33);
        this.ajouterUneTransition(27,Jeton.ALIAS,34);

        //Meme transition que 1
        this.ajouterUneTransition(28,Jeton.COMPTEUR,4);

        //Meme transition que 2
        this.ajouterUneTransition(29,Jeton.ACTION,5);
        this.ajouterUneTransition(29,Jeton.ETAT,6);
        this.ajouterUneTransition(29,Jeton.COMPTEUR,7);
        //this.ajouterUneTransition(29,Jeton.JOUEUR,3);

        //Meme transition que 3
        this.ajouterUneTransition(30,Jeton.ACTION,5);
        this.ajouterUneTransition(30,Jeton.ETAT,6);
        this.ajouterUneTransition(30,Jeton.COMPTEUR,7);

        //Meme transition que 9
        //this.ajouterUneTransition(31,Jeton.JOUEUR,10);
        this.ajouterUneTransition(31,Jeton.ET,0);
        this.ajouterUneTransition(31,Jeton.OU,0);
        this.ajouterUneTransition(31,Jeton.ALORS,15);

        //Meme transition que 10
        this.ajouterUneTransition(32,Jeton.ET,0);
        this.ajouterUneTransition(32,Jeton.OU,0);
        this.ajouterUneTransition(32,Jeton.ALORS,15);

        //Meme transition que 11
        this.ajouterUneTransition(33,Jeton.ET,0);
        this.ajouterUneTransition(33,Jeton.OU,0);
        this.ajouterUneTransition(33,Jeton.ALORS,15);
        //this.ajouterUneTransition(33,Jeton.CASE,27);

        //Meme transition que 27
        this.ajouterUneTransition(34,Jeton.ET,0);
        this.ajouterUneTransition(34,Jeton.OU,0);
        this.ajouterUneTransition(34,Jeton.ALORS,15);

        //=========== CONDITIONS ===========
        //ETAT INITIAL
        this.ajouterUneTransition(0,Jeton.JOUEUR,1);
        this.ajouterUneTransition(0,Jeton.PIECE,2);
        this.ajouterUneTransition(0,Jeton.PIECETOKEN,3);

        //ETAT 1
        //this.setValeurEtat(1,"1");
        this.ajouterUneTransition(1,Jeton.COMPTEUR,4);

        //ETAT 2
        //this.setValeurEtat(2,"2");
        this.ajouterUneTransition(2,Jeton.ACTION,5);
        this.ajouterUneTransition(2,Jeton.ETAT,6);
        this.ajouterUneTransition(2,Jeton.COMPTEUR,7);
        this.ajouterUneTransition(2,Jeton.JOUEUR,3);

        //ETAT 3
        //this.setValeurEtat(3,"3");
        this.ajouterUneTransition(3,Jeton.ACTION,5);
        this.ajouterUneTransition(3,Jeton.ETAT,6);
        this.ajouterUneTransition(3,Jeton.COMPTEUR,7);

        //ETAT 4
        //this.setValeurEtat(4,"4");
        this.ajouterUneTransition(4,Jeton.COMPARAISON,8);

        //ETAT 5
        //this.setValeurEtat(5,"5");
        this.ajouterUneTransition(5,Jeton.PIECE,9);
        this.ajouterUneTransition(5,Jeton.PIECETOKEN,10);
        this.ajouterUneTransition(5,Jeton.CASE,11);
        this.ajouterUneTransition(5,Jeton.CASEPARAM,27);

        //ETAT 6
        //this.setValeurEtat(6,"6");
        this.ajouterUneTransition(6,Jeton.ET,0);
        this.ajouterUneTransition(6,Jeton.OU,0);
        this.ajouterUneTransition(6,Jeton.ALORS,15);

        //ETAT 7
        //this.setValeurEtat(7,"7");
        this.ajouterUneTransition(7,Jeton.COMPARAISON,12);

        //ETAT 8
        //this.setValeurEtat(8,"8");
        this.ajouterUneTransition(8,Jeton.NOMBRE,13);

        //ETAT 9
        //this.setValeurEtat(9,"9");
        this.ajouterUneTransition(9,Jeton.JOUEUR,10);
        this.ajouterUneTransition(9,Jeton.ET,0);
        this.ajouterUneTransition(9,Jeton.OU,0);
        this.ajouterUneTransition(9,Jeton.ALORS,15);

        //ETAT 10
        //this.setValeurEtat(10,"10");
        this.ajouterUneTransition(10,Jeton.ET,0);
        this.ajouterUneTransition(10,Jeton.OU,0);
        this.ajouterUneTransition(10,Jeton.ALORS,15);

        //ETAT 11
        //this.setValeurEtat(11,"11");
        this.ajouterUneTransition(11,Jeton.ET,0);
        this.ajouterUneTransition(11,Jeton.OU,0);
        this.ajouterUneTransition(11,Jeton.ALORS,15);
        this.ajouterUneTransition(11,Jeton.CASE,27);

        //ETAT 12
        //this.setValeurEtat(12,"12");
        this.ajouterUneTransition(12,Jeton.NOMBRE,14);

        //ETAT 13
        //this.setValeurEtat(13,"13");
        this.ajouterUneTransition(13,Jeton.ET,0);
        this.ajouterUneTransition(13,Jeton.OU,0);
        this.ajouterUneTransition(13,Jeton.ALORS,15);

        //ETAT 14
        //this.setValeurEtat(14,"14");
        this.ajouterUneTransition(14,Jeton.ET,0);
        this.ajouterUneTransition(14,Jeton.OU,0);
        this.ajouterUneTransition(14,Jeton.ALORS,15);

        //=========== CONSEQUENCE ===========
        //ETAT 15
        //this.setValeurEtat(15,"15");
        this.ajouterUneTransition(15,Jeton.JOUEUR,16);
        this.ajouterUneTransition(15,Jeton.PIECE,17);
        this.ajouterUneTransition(15,Jeton.PIECETOKEN,18);

        //ETAT 16
        //this.setValeurEtat(16,"16");
        this.ajouterUneTransition(16,Jeton.CONSEQUENCEACTION,20);
        this.ajouterUneTransition(16,Jeton.CONSEQUENCETERMINALE,19);

        //ETAT 17
        //this.setValeurEtat(17,"17");
        this.ajouterUneTransition(17,Jeton.JOUEUR,18);
        this.ajouterUneTransition(17,Jeton.CONSEQUENCEACTION,20);

        //ETAT 18
        //this.setValeurEtat(18,"18");
        this.ajouterUneTransition(18,Jeton.CONSEQUENCEACTION,20);

        //ETAT 19
        //this.setValeurEtat(19,"19");

        //ETAT 20
        //this.setValeurEtat(20,"20");
        this.ajouterUneTransition(20,Jeton.PIECE,21);
        this.ajouterUneTransition(20,Jeton.PIECETOKEN,22);
        this.ajouterUneTransition(20,Jeton.CASE,23);

        //ETAT 21
        //this.setValeurEtat(21,"21");
        this.ajouterUneTransition(21,Jeton.ET,20);
        this.ajouterUneTransition(21,Jeton.JOUEUR,22);

        //ETAT 22
        //this.setValeurEtat(22,"22");
        this.ajouterUneTransition(22,Jeton.ET,20);

        //ETAT 23
        //this.setValeurEtat(23,"23");
        this.ajouterUneTransition(23,Jeton.ET,20);
        this.ajouterUneTransition(23,Jeton.PIECETOKEN,22);
        this.ajouterUneTransition(23,Jeton.PIECE,21);

        //ETAT 27
        this.ajouterUneTransition(27,Jeton.ET,0);
        this.ajouterUneTransition(27,Jeton.OU,0);
        this.ajouterUneTransition(27,Jeton.ALORS,15);
    }

    private boolean peutAvancer(int indice, List<Jeton> regleSyntaxe){
        try{
            regleSyntaxe.get(indice);
            return true;
        }catch(IndexOutOfBoundsException  e){
            return false;
        }
    }

    private boolean estConnecteurCondition(Jeton j){
        return j == Jeton.OU || j == Jeton.ET || j == Jeton.ALORS;
    }

    private boolean estConnecteurConsequence(Jeton j){
        return j == Jeton.ET;
    }

    /**Méthode permettant de vérifier si un jeton non référence un comparateur ou non, d'après le parcours de l'automate
     * @param parcours : parcours daans l'automate**/
    private boolean estNonComparateur(String parcours){
        return parcours.equals("014") || parcours.equals("0R14")|| parcours.equals("027") || parcours.equals("037")|| parcours.equals("0237") || parcours.equals("0R27")|| parcours.equals("0R237") || parcours.equals("02R37")|| parcours.equals("0R2R37")|| parcours.equals("0R37");
    }

    private String getMessageErreur(int id, List<Jeton> regleSyntaxe, List<String> regleString){
        return id+"";
    }

    /*"prend", "sedeplace", "estpromu", "estsur", "estechec", "nb_deplacement", "estplace", "timer",
      "=", "<", ">",
      "tous-piece", "tous-joueur", "tous-typecase","victoire", "defaite", "pat", "manger", "placer", "promouvoir", "deplacer"*/
    @SuppressWarnings("Duplicates")
    public Regle analyserUneRegle(List<Jeton> regleSyntaxe, List<String> regleString) throws MauvaiseDefinitionRegleException {
        int curEtat = this.getEtatDeDepart();

        //Regle à retourner après le traitement
        Regle regle = new Regle();

        //Chaine de caractere fourni par le passage dans les états
        Etat dep = this.recupererEtat(this.getEtatDeDepart());
        if(dep == null){
            throw new MauvaiseDefinitionRegleException("Impossible de commencer une analyse en partant de l'état de départ : " + getEtatDeDepart());
        }
        String parcours = dep.getValeur();

        //Integer renseignant l'indice de parcour dans regleSyntaxe
        int  indRegleSyntaxe = 0;
        //Ré-initialisation des Alias
        aliasRegle.clear();

        //Compteurs permettant de reconnaitre une Regle bien formée
        int nbConditions = 0;
        int nbConsequence = 0;

        //Booléen permettant de connaitre dans quel endroit du traitement de la Regle on se trouve
        boolean traitementCondition = true;

        //Listes utiles pour la création de la Regle ensuite
        List<Jeton> jetonsarborescence = new ArrayList<>();
        List<Condition> conditionsDeLaRegle = new ArrayList<>();
        List<Consequence> consequencesDeLaRegle = new ArrayList<>();

        //Liste des alias définie dans une Condition
        List<Alias<Jeton,?>> aliasCondtion = new ArrayList<>();

        //Compteur permettant de vérifier que la Regle est bien parenthésée
        int indParenthese = 0;

        Iterator<Jeton> ite = regleSyntaxe.iterator();

        while(ite.hasNext()){
            Jeton j = ite.next();
            switch (j) {
                //Gestion du parenthésage
                case PARENTHESEOUVRANTE -> {
                    jetonsarborescence.add(Jeton.PARENTHESEOUVRANTE);
                    indParenthese++;
                }
                //Gestion du parenthésage
                case PARENTHESEFERMANTE -> {
                    jetonsarborescence.add(Jeton.PARENTHESEFERMANTE);
                    indParenthese--;
                    if(indParenthese<0){
                        throw new MauvaiseSemantiqueRegleException("Probleme de paranthesage, une paranthese fermante est présente alors qu'il n'existe pas de paranthèse ouvrante pour celle-ci : " + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString));
                    }
                }
                //Gestion des connecteurs (Pour l'arbre des conditions)
                case ET,OU -> {
                    if (traitementCondition) {
                        jetonsarborescence.add(j);
                    }
                }
                //Gestion de la négation
                case NON -> {
                    if (traitementCondition) {
                        if (!estNonComparateur(parcours)) {
                            jetonsarborescence.add(j);
                        }else{
                            parcours+="N";
                        }
                    }
                }
                //Gestion du ALORS (fin du traitement des conditions)
                case ALORS -> {
                    if (traitementCondition) {
                        traitementCondition = false;
                        if(indParenthese != 0){
                            throw new MauvaiseSemantiqueRegleException("Probleme de paranthesage, trop de parenthèses " + ((indParenthese < 0)? "fermante" : "ouvrante") + " : " + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString));
                        }
                    } else {
                        throw new MauvaiseSemantiqueRegleException("Double alors [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                    }
                }
                //Gestion des Alias dans la Regle (définition)
                case ALIAS -> {
                    //Si on rencontre une définition d'Alias dans les conséquences
                    if(!traitementCondition){
                        //Alors il s'agit d'un problème
                        throw new MauvaiseDefinitionRegleException("Imposible de définir un Alias dans les Consequences de Regle : " + indRegleSyntaxe);
                    }else{
                        //Sinon, on doit la définir dans notre flot d'alias
                        try {
                            ajouterAlias(regleString.get(indRegleSyntaxe), parcours);
                            aliasCondtion.add(recupererAlias(regleString.get(indRegleSyntaxe)));
                            regleString.remove(indRegleSyntaxe);
                            --indRegleSyntaxe;
                            ite.remove();
                        }catch (MauvaiseDefinitionRegleException e){
                            throw new MauvaiseDefinitionRegleException(e.getMessage() + " (" + indRegleSyntaxe + ")");
                        }
                    }
                }
                //Gestion des Alias dans la Regle (utilisation)
                case ALIASREUTILISATION -> {
                    //Si la personne définissaant sa Regle veut réutiliser un des Alias qu'il a correctement défini
                    //Alors on doit faire en sorte que ce ne soit plus un AliasReutilisation que le systèmedoit reconnaitre, mais la valeur du jeton correspondante à l'alias
                    j = recupererAlias(regleString.get(indRegleSyntaxe)).getJetonAssocie();
                    //Puis on indique que l'utilisateur veut réutiliser un Alias à un endroit précis du système
                    parcours+="R";
                }
            }

            System.out.println("REGLESTRING ARS: " + regleString + " à l'indice : " + indRegleSyntaxe);
            System.out.println("PARCOUR : " + parcours);

            //Récupération de l'état précédent (messages d'erreur)
            int predEtat = curEtat;

            //Récupération de l'indice du prochain état d'après la transition donnée
            curEtat = this.etatSuivant(curEtat, j);

            //Récupération de l'état correspondant à l'indice curEtat
            Etat etat = this.recupererEtat(curEtat);

            if (etat != null){
                parcours+=etat.getValeur();
                if (etat.estTerminal()) {
                    /** Soit: peut avancer et jeton suivant est un connecteur adéquat
                     *      OU
                     *      Ne peut pas avancer (mais est terminal)
                     *
                     * */
                    if ((peutAvancer(indRegleSyntaxe + 1, regleSyntaxe)
                            && ( (          traitementCondition
                            &&
                            estConnecteurCondition(regleSyntaxe.get(indRegleSyntaxe + 1)))
                            ||
                            (       !traitementCondition
                                    &&
                                    estConnecteurConsequence(regleSyntaxe.get(indRegleSyntaxe + 1))) ) )
                            ||
                            !peutAvancer(indRegleSyntaxe + 1, regleSyntaxe)
                    )  {

                        //Alors notre etat est bien terminal de block, on l'analyse
                        switch (etat.getCodeDeRetour()) {
                            /*---------------------------------CONDITIONS---------------------------------*/
                            case 306 -> {
                                //traitement sommet terminal 6 : SUJET-ETAT
                                //Seul l'état estpromu est possible
                                if(indRegleSyntaxe >= 1){
                                    Condition cond = case306(parcours, indRegleSyntaxe, regleSyntaxe, regleString );
                                    nbConditions++;
                                    jetonsarborescence.add(Jeton.CONDITION);
                                    //Edition des Alias : ConditionDeDepart
                                    for(Alias<Jeton,?> alias : aliasCondtion){
                                        alias.setConditionDeDefinition(cond);
                                    }
                                    aliasCondtion.clear();
                                }else{
                                    throw new MauvaiseSemantiqueRegleException("Pas assez d'argument pour Piece(T)-Etat [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                }

                            }

                            case 309 , 331 -> {
                                //traitement des sommets terminaux 9 et 31 : SUJET-ACTION-PIECE
                                if (indRegleSyntaxe >= 2) {
                                    Condition cond = case309(parcours, indRegleSyntaxe, regleSyntaxe, regleString);
                                    conditionsDeLaRegle.add(cond);
                                    nbConditions++;
                                    jetonsarborescence.add(Jeton.CONDITION);
                                    //Edition des Alias : ConditionDeDepart
                                    for(Alias<Jeton,?> alias : aliasCondtion){
                                        alias.setConditionDeDefinition(cond);
                                    }
                                    aliasCondtion.clear();
                                }else{
                                    throw new MauvaiseSemantiqueRegleException("Pas assez d'argument pour Piece(T)-Action-Piece [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                }
                            }

                            case 310 , 332 -> {
                                //SUJET-ACTION-PIECETOKEN   OU  SUJET-ACTION-PIECE-JOUEUR
                                if (indRegleSyntaxe >= 2) {
                                    Condition cond = case310(parcours, indRegleSyntaxe, regleSyntaxe, regleString);
                                    conditionsDeLaRegle.add(cond);
                                    nbConditions++;
                                    jetonsarborescence.add(Jeton.CONDITION);
                                    //Edition des Alias : ConditionDeDepart
                                    for(Alias<Jeton,?> alias : aliasCondtion){
                                        alias.setConditionDeDefinition(cond);
                                    }
                                    aliasCondtion.clear();
                                }else{
                                    throw new MauvaiseSemantiqueRegleException("Pas assez d'argument pour Piece(T)-Action-Piece(J|T) [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                }
                            }

                            case 311 , 333 -> {
                                //SUJET-ACTION-CASE
                                if (indRegleSyntaxe >= 2) {
                                    Condition cond;
                                    switch (parcours) {
                                        case "02511" -> {
                                            //Cas Piece+Action+Case
                                            switch (regleString.get(indRegleSyntaxe - 1)) {
                                                case "estsur" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                                                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                                            Fonctions_Comportements.est_Sur);
                                                }
                                                case "prend" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                                                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                                            Fonctions_Comportements.prend_Par_Case);
                                                }
                                                case "sedeplace" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                                                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                                            Fonctions_Comportements.se_deplace);
                                                }
                                                case "estplace" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                                                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                                            Fonctions_Comportements.est_place);

                                                }
                                                default -> {
                                                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Case inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                                }
                                            }
                                        }
                                        case "03511" -> {
                                            //Cas PieceToken+Action+Case
                                            switch (regleString.get(indRegleSyntaxe - 1)) {
                                                case "estsur" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                                                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                                            Fonctions_Comportements.est_Sur);
                                                }
                                                case "prend" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                                                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                                            Fonctions_Comportements.prend_Par_Case);
                                                }
                                                case "sedeplace" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                                                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                                            Fonctions_Comportements.se_deplace);
                                                }
                                                case "estplace" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                                                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                                            Fonctions_Comportements.est_place);
                                                }
                                                default -> {
                                                    throw new MauvaiseSemantiqueRegleException("Bloc PieceToken-Action-Case inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                                }
                                            }
                                        }
                                        case "023511" -> {
                                            //Cas Piece+Joueur+Action+Case
                                            switch (regleString.get(indRegleSyntaxe-1)) {
                                                case "estsur" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3), regleString.get(indRegleSyntaxe-2)),
                                                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                                            Fonctions_Comportements.est_Sur);
                                                }
                                                case "prend" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3), regleString.get(indRegleSyntaxe-2)),
                                                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                                            Fonctions_Comportements.prend_Par_Case);
                                                }
                                                case "sedeplace" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3), regleString.get(indRegleSyntaxe-2)),
                                                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                                            Fonctions_Comportements.se_deplace);
                                                }
                                                case "estplace" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3), regleString.get(indRegleSyntaxe-2)),
                                                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                                            Fonctions_Comportements.est_place);
                                                }
                                                default -> {
                                                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-Action-Case inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                                }
                                            }
                                        }
                                        /*==== Reutilisation Alias ====*/
                                        case "0R2511" , "0R3511" -> {
                                            //Alias sur Piece(Token) : [PIECE(Token)]+ACTION+CASE
                                            Alias<Jeton,Piece> alias;
                                            Alias<Jeton,?> testAlias = null;

                                            try{
                                                testAlias = recupererAlias(regleString.get(indRegleSyntaxe-2));
                                                alias = (Alias<Jeton, Piece>) testAlias;
                                            }catch (ClassCastException e){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                                            }catch (MauvaiseDefinitionRegleException re){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " inconnu dans sa réutilisation ([PIECE(Token)]+ACTION+CASE)");
                                            }

                                            switch (regleString.get(indRegleSyntaxe - 1)) {
                                                case "estsur" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new Interpreteur_Alias_Sujet<Piece>(alias),
                                                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                                            Fonctions_Comportements.est_Sur);
                                                }
                                                case "prend" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new Interpreteur_Alias_Sujet<Piece>(alias),
                                                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                                            Fonctions_Comportements.prend_Par_Case);
                                                }
                                                case "sedeplace" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new Interpreteur_Alias_Sujet<Piece>(alias),
                                                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                                            Fonctions_Comportements.se_deplace);
                                                }
                                                case "estplace" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new Interpreteur_Alias_Sujet<Piece>(alias),
                                                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                                            Fonctions_Comportements.est_place);

                                                }
                                                default -> {
                                                    throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-Action-Case inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                                }
                                            }
                                        }
                                        case "025R11" , "035R11" -> {
                                            //Alias sur Case : PIECE(Token)+ACTION+[CASE]
                                            Alias<Jeton,GroupCases> alias;
                                            Alias<Jeton,?> testAlias = null;

                                            try{
                                                testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                                                alias = (Alias<Jeton, GroupCases>) testAlias;
                                            }catch (ClassCastException e){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu CASE | recu " + testAlias.getJetonAssocie()+")");
                                            }catch (MauvaiseDefinitionRegleException re){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE(Token)+ACTION+[CASE])");
                                            }

                                            switch (regleString.get(indRegleSyntaxe - 1)) {
                                                case "estsur" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 2)),
                                                            new Interpreteur_Alias_Cible<GroupCases>(alias),
                                                            Fonctions_Comportements.est_Sur);
                                                }
                                                case "prend" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 2)),
                                                            new Interpreteur_Alias_Cible<GroupCases>(alias),
                                                            Fonctions_Comportements.prend_Par_Case);
                                                }
                                                case "sedeplace" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 2)),
                                                            new Interpreteur_Alias_Cible<GroupCases>(alias),
                                                            Fonctions_Comportements.se_deplace);
                                                }
                                                case "estplace" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 2)),
                                                            new Interpreteur_Alias_Cible<GroupCases>(alias),
                                                            Fonctions_Comportements.est_place);

                                                }
                                                default -> {
                                                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-CaseAlias inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                                                }
                                            }
                                        }
                                        case "0R25R11" , "0R35R11" -> {
                                            //Alias sur Piece(Token) et Case : [PIECE(Token)]+ACTION+[CASE]
                                            Alias<Jeton,Piece> alias1;
                                            Alias<Jeton,GroupCases> alias2;
                                            Alias<Jeton,?> testAlias = null;

                                            try{
                                                testAlias = recupererAlias(regleString.get(indRegleSyntaxe-2));
                                                alias1 = (Alias<Jeton, Piece>) testAlias;
                                            }catch (ClassCastException e){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                                            }catch (MauvaiseDefinitionRegleException re){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " inconnu dans sa réutilisation ([PIECE(Token)]+ACTION+CASE)");
                                            }
                                            try{
                                                testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                                                alias2 = (Alias<Jeton, GroupCases>) testAlias;
                                            }catch (ClassCastException e){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu CASE | recu " + testAlias.getJetonAssocie()+")");
                                            }catch (MauvaiseDefinitionRegleException re){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE(Token)+ACTION+[CASE])");
                                            }

                                            switch (regleString.get(indRegleSyntaxe - 1)) {
                                                case "estsur" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new Interpreteur_Alias_Sujet<Piece>(alias1),
                                                            new Interpreteur_Alias_Cible<GroupCases>(alias2),
                                                            Fonctions_Comportements.est_Sur);
                                                }
                                                case "prend" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new Interpreteur_Alias_Sujet<Piece>(alias1),
                                                            new Interpreteur_Alias_Cible<GroupCases>(alias2),
                                                            Fonctions_Comportements.prend_Par_Case);
                                                }
                                                case "sedeplace" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new Interpreteur_Alias_Sujet<Piece>(alias1),
                                                            new Interpreteur_Alias_Cible<GroupCases>(alias2),
                                                            Fonctions_Comportements.se_deplace);
                                                }
                                                case "estplace" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new Interpreteur_Alias_Sujet<Piece>(alias1),
                                                            new Interpreteur_Alias_Cible<GroupCases>(alias2),
                                                            Fonctions_Comportements.est_place);

                                                }
                                                default -> {
                                                    throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-Action-CaseAlias inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                                                }
                                            }
                                        }

                                        case "0R23511" -> {
                                            //Alias sur Piece : [PIECE]+JOUEUR+ACTION+CASE
                                            Alias<Jeton,Piece> alias;
                                            Alias<Jeton,?> testAlias = null;

                                            try{
                                                testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                                                alias = (Alias<Jeton, Piece>) testAlias;
                                            }catch (ClassCastException e){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                                            }catch (MauvaiseDefinitionRegleException re){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+CASE)");
                                            }

                                            switch (regleString.get(indRegleSyntaxe - 1)) {
                                                case "estsur" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias),regleString.get(indRegleSyntaxe - 2)),
                                                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                                            Fonctions_Comportements.est_Sur);
                                                }
                                                case "prend" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias),regleString.get(indRegleSyntaxe - 2)),
                                                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                                            Fonctions_Comportements.prend_Par_Case);
                                                }
                                                case "sedeplace" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias),regleString.get(indRegleSyntaxe - 2)),
                                                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                                            Fonctions_Comportements.se_deplace);
                                                }
                                                case "estplace" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias),regleString.get(indRegleSyntaxe - 2)),
                                                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                                            Fonctions_Comportements.est_place);
                                                }
                                                default -> {
                                                    throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-Joueur-Action-Case inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                                                }
                                            }
                                        }
                                        case "02R3511" -> {
                                            //Alias sur Joueur : PIECE+[JOUEUR]+ACTION+CASE
                                            Alias<Jeton,Joueur> alias;
                                            Alias<Jeton,?> testAlias = null;

                                            try{
                                                testAlias = recupererAlias(regleString.get(indRegleSyntaxe-2));
                                                alias = (Alias<Jeton, Joueur>) testAlias;
                                            }catch (ClassCastException e){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                                            }catch (MauvaiseDefinitionRegleException re){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " inconnu dans sa réutilisation (PIECE+[JOUEUR]+ACTION+CASE)");
                                            }

                                            switch (regleString.get(indRegleSyntaxe - 1)) {
                                                case "estsur" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias), regleString.get(indRegleSyntaxe - 3)),
                                                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                                            Fonctions_Comportements.est_Sur);
                                                }
                                                case "prend" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias), regleString.get(indRegleSyntaxe - 3)),
                                                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                                            Fonctions_Comportements.prend_Par_Case);
                                                }
                                                case "sedeplace" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias), regleString.get(indRegleSyntaxe - 3)),
                                                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                                            Fonctions_Comportements.se_deplace);
                                                }
                                                case "estplace" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias), regleString.get(indRegleSyntaxe - 3)),
                                                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                                            Fonctions_Comportements.est_place);
                                                }
                                                default -> {
                                                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-JoueurAlias-Action-Case inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                                                }
                                            }
                                        }
                                        case "0235R11" -> {
                                            //Alias sur Case : PIECE+JOUEUR+ACTION+[CASE]
                                            Alias<Jeton,GroupCases> alias;
                                            Alias<Jeton,?> testAlias = null;

                                            try{
                                                testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                                                alias = (Alias<Jeton, GroupCases>) testAlias;
                                            }catch (ClassCastException e){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu CASE | recu " + testAlias.getJetonAssocie()+")");
                                            }catch (MauvaiseDefinitionRegleException re){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE+JOUEUR+ACTION+[CASE])");
                                            }

                                            switch (regleString.get(indRegleSyntaxe - 1)) {
                                                case "estsur" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3), regleString.get(indRegleSyntaxe-2)),
                                                            new Interpreteur_Alias_Cible<GroupCases>(alias),
                                                            Fonctions_Comportements.est_Sur);
                                                }
                                                case "prend" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3), regleString.get(indRegleSyntaxe-2)),
                                                            new Interpreteur_Alias_Cible<GroupCases>(alias),
                                                            Fonctions_Comportements.prend_Par_Case);
                                                }
                                                case "sedeplace" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3), regleString.get(indRegleSyntaxe-2)),
                                                            new Interpreteur_Alias_Cible<GroupCases>(alias),
                                                            Fonctions_Comportements.se_deplace);
                                                }
                                                case "estplace" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3), regleString.get(indRegleSyntaxe-2)),
                                                            new Interpreteur_Alias_Cible<GroupCases>(alias),
                                                            Fonctions_Comportements.est_place);
                                                }
                                                default -> {
                                                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-Action-CaseAlias inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                                                }
                                            }
                                        }
                                        case "0R2R3511" -> {
                                            //Alias sur Piece et Joueur : [PIECE]+[JOUEUR]+ACTION+CASE
                                            Alias<Jeton,Piece> alias1;
                                            Alias<Jeton,Joueur> alias2;
                                            Alias<Jeton,?> testAlias = null;

                                            try{
                                                testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                                                alias1 = (Alias<Jeton, Piece>) testAlias;
                                            }catch (ClassCastException e){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                                            }catch (MauvaiseDefinitionRegleException re){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+CASE)");
                                            }

                                            try{
                                                testAlias = recupererAlias(regleString.get(indRegleSyntaxe-2));
                                                alias2 = (Alias<Jeton, Joueur>) testAlias;
                                            }catch (ClassCastException e){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                                            }catch (MauvaiseDefinitionRegleException re){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " inconnu dans sa réutilisation (PIECE+[JOUEUR]+ACTION+CASE)");
                                            }

                                            switch (regleString.get(indRegleSyntaxe - 1)) {
                                                case "estsur" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1), new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                                            Fonctions_Comportements.est_Sur);
                                                }
                                                case "prend" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1), new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                                            Fonctions_Comportements.prend_Par_Case);
                                                }
                                                case "sedeplace" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1), new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                                            Fonctions_Comportements.se_deplace);
                                                }
                                                case "estplace" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1), new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                                            Fonctions_Comportements.est_place);
                                                }
                                                default -> {
                                                    throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-JoueurAlias-Action-Case inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                                                }
                                            }
                                        }
                                        case "02R35R11" -> {
                                            //Alias sur Joueur et Case : PIECE+[JOUEUR]+ACTION+[CASE]
                                            Alias<Jeton,Joueur> alias1;
                                            Alias<Jeton,GroupCases> alias2;
                                            Alias<Jeton,?> testAlias = null;

                                            try{
                                                testAlias = recupererAlias(regleString.get(indRegleSyntaxe-2));
                                                alias1 = (Alias<Jeton, Joueur>) testAlias;
                                            }catch (ClassCastException e){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                                            }catch (MauvaiseDefinitionRegleException re){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " inconnu dans sa réutilisation (PIECE+[JOUEUR]+ACTION+CASE)");
                                            }

                                            try{
                                                testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                                                alias2 = (Alias<Jeton, GroupCases>) testAlias;
                                            }catch (ClassCastException e){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu CASE | recu " + testAlias.getJetonAssocie()+")");
                                            }catch (MauvaiseDefinitionRegleException re){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE+JOUEUR+ACTION+[CASE])");
                                            }

                                            switch (regleString.get(indRegleSyntaxe - 1)) {
                                                case "estsur" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias1),regleString.get(indRegleSyntaxe - 3)),
                                                            new Interpreteur_Alias_Cible<GroupCases>(alias2),
                                                            Fonctions_Comportements.est_Sur);
                                                }
                                                case "prend" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias1),regleString.get(indRegleSyntaxe - 3)),
                                                            new Interpreteur_Alias_Cible<GroupCases>(alias2),
                                                            Fonctions_Comportements.prend_Par_Case);
                                                }
                                                case "sedeplace" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias1),regleString.get(indRegleSyntaxe - 3)),
                                                            new Interpreteur_Alias_Cible<GroupCases>(alias2),
                                                            Fonctions_Comportements.se_deplace);
                                                }
                                                case "estplace" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias1),regleString.get(indRegleSyntaxe - 3)),
                                                            new Interpreteur_Alias_Cible<GroupCases>(alias2),
                                                            Fonctions_Comportements.est_place);
                                                }
                                                default -> {
                                                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-JoueurAlias-Action-CaseAlias inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                                                }
                                            }
                                        }
                                        case "0R235R11" -> {
                                            //Alias sur Piece et Case : [PIECE]+JOUEUR+ACTION+[CASE]
                                            Alias<Jeton,Piece> alias1;
                                            Alias<Jeton,GroupCases> alias2;
                                            Alias<Jeton,?> testAlias = null;

                                            try{
                                                testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                                                alias1 = (Alias<Jeton, Piece>) testAlias;
                                            }catch (ClassCastException e){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                                            }catch (MauvaiseDefinitionRegleException re){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+CASE)");
                                            }

                                            try{
                                                testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                                                alias2 = (Alias<Jeton, GroupCases>) testAlias;
                                            }catch (ClassCastException e){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu CASE | recu " + testAlias.getJetonAssocie()+")");
                                            }catch (MauvaiseDefinitionRegleException re){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE+JOUEUR+ACTION+[CASE])");
                                            }

                                            switch (regleString.get(indRegleSyntaxe - 1)) {
                                                case "estsur" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias1),regleString.get(indRegleSyntaxe - 2)),
                                                            new Interpreteur_Alias_Cible<GroupCases>(alias2),
                                                            Fonctions_Comportements.est_Sur);
                                                }
                                                case "prend" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias1),regleString.get(indRegleSyntaxe - 2)),
                                                            new Interpreteur_Alias_Cible<GroupCases>(alias2),
                                                            Fonctions_Comportements.prend_Par_Case);
                                                }
                                                case "sedeplace" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias1),regleString.get(indRegleSyntaxe - 2)),
                                                            new Interpreteur_Alias_Cible<GroupCases>(alias2),
                                                            Fonctions_Comportements.se_deplace);
                                                }
                                                case "estplace" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias1),regleString.get(indRegleSyntaxe - 2)),
                                                            new Interpreteur_Alias_Cible<GroupCases>(alias2),
                                                            Fonctions_Comportements.est_place);
                                                }
                                                default -> {
                                                    throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-Joueur-Action-CaseAlias inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                                                }
                                            }
                                        }
                                        case "0R2R35R11" -> {
                                            //Alias sur Piece et Joueur et Case : [PIECE]+[JOUEUR]+ACTION+[CASE]
                                            Alias<Jeton,Piece> alias1;
                                            Alias<Jeton,Joueur> alias2;
                                            Alias<Jeton,GroupCases> alias3;
                                            Alias<Jeton,?> testAlias = null;

                                            try{
                                                testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                                                alias1 = (Alias<Jeton, Piece>) testAlias;
                                            }catch (ClassCastException e){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                                            }catch (MauvaiseDefinitionRegleException re){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+CASE)");
                                            }

                                            try{
                                                testAlias = recupererAlias(regleString.get(indRegleSyntaxe-2));
                                                alias2 = (Alias<Jeton, Joueur>) testAlias;
                                            }catch (ClassCastException e){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                                            }catch (MauvaiseDefinitionRegleException re){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " inconnu dans sa réutilisation (PIECE+[JOUEUR]+ACTION+CASE)");
                                            }

                                            try{
                                                testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                                                alias3 = (Alias<Jeton, GroupCases>) testAlias;
                                            }catch (ClassCastException e){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu CASE | recu " + testAlias.getJetonAssocie()+")");
                                            }catch (MauvaiseDefinitionRegleException re){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE+JOUEUR+ACTION+[CASE])");
                                            }

                                            switch (regleString.get(indRegleSyntaxe - 1)) {
                                                case "estsur" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1),new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                                            new Interpreteur_Alias_Cible<GroupCases>(alias3),
                                                            Fonctions_Comportements.est_Sur);
                                                }
                                                case "prend" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1),new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                                            new Interpreteur_Alias_Cible<GroupCases>(alias3),
                                                            Fonctions_Comportements.prend_Par_Case);
                                                }
                                                case "sedeplace" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1),new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                                            new Interpreteur_Alias_Cible<GroupCases>(alias3),
                                                            Fonctions_Comportements.se_deplace);
                                                }
                                                case "estplace" -> {
                                                    cond = new ConditionAction<Piece, GroupCases>(
                                                            new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1),new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                                            new Interpreteur_Alias_Cible<GroupCases>(alias3),
                                                            Fonctions_Comportements.est_place);
                                                }
                                                default -> {
                                                    throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-JoueurAlias-Action-CaseAlias inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                                                }
                                            }
                                        }
                                        default -> {
                                            throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Case OU Piece-Joueur-Action-Case OU PieceToken-Action-Case inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                        }
                                    }
                                    conditionsDeLaRegle.add(cond);
                                    nbConditions++;
                                    jetonsarborescence.add(Jeton.CONDITION);
                                    //Edition des Alias : ConditionDeDepart
                                    for(Alias<Jeton,?> alias : aliasCondtion){
                                        alias.setConditionDeDefinition(cond);
                                    }
                                    aliasCondtion.clear();
                                }else{
                                    throw new MauvaiseSemantiqueRegleException("Pas assez d'argument pour Piece(T)-Action-Case [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                }
                            }

                            case 313 -> {
                                //JOUEUR-COMPTEUR-COMPARAISON-NOMBRE
                                if (indRegleSyntaxe >= 3) {
                                    Condition cond;
                                    //Cas Joueur+Compteur+Comparaison+Nombre
                                    switch (parcours){
                                        case "014813" -> {
                                            if (regleString.get(indRegleSyntaxe - 2).equals("timer")) {
                                                switch(regleString.get(indRegleSyntaxe-1)){
                                                    case "<" -> {
                                                        cond = new ConditionAction<Joueur, IntegerRegle>(
                                                                new InterpreteurSujetJoueur(regleString.get(indRegleSyntaxe-3)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                Fonctions_Comportements.timer_inferieur_a);
                                                    }
                                                    case "=" -> {
                                                        cond = new ConditionAction<Joueur, IntegerRegle>(
                                                                new InterpreteurSujetJoueur(regleString.get(indRegleSyntaxe-3)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                Fonctions_Comportements.timer_egal_a);
                                                    }
                                                    case ">" -> {
                                                        cond = new ConditionAction<Joueur, IntegerRegle>(
                                                                new InterpreteurSujetJoueur(regleString.get(indRegleSyntaxe-3)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                Fonctions_Comportements.timer_superieur_a);
                                                    }
                                                    default -> {
                                                        throw new MauvaiseDefinitionRegleException("Comparateur inconnu (=,<,>)");
                                                    }
                                                }
                                            } else {
                                                throw new MauvaiseSemantiqueRegleException("Bloc Joueur-Timer inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }
                                        }
                                        case "014N813" -> {
                                            if (regleString.get(indRegleSyntaxe - 2).equals("timer")) {
                                                switch(regleString.get(indRegleSyntaxe-1)){
                                                    case "<" -> {
                                                        cond = new ConditionAction<Joueur, IntegerRegle>(
                                                                new InterpreteurSujetJoueur(regleString.get(indRegleSyntaxe-3)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                (joueursFonctionComp,integersFonctionComp) -> !Fonctions_Comportements.timer_inferieur_a.apply(joueursFonctionComp,integersFonctionComp));
                                                    }
                                                    case "=" -> {
                                                        cond = new ConditionAction<Joueur, IntegerRegle>(
                                                                new InterpreteurSujetJoueur(regleString.get(indRegleSyntaxe-3)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                (joueursFonctionComp,integersFonctionComp) -> !Fonctions_Comportements.timer_egal_a.apply(joueursFonctionComp,integersFonctionComp));
                                                    }
                                                    case ">" -> {
                                                        cond = new ConditionAction<Joueur, IntegerRegle>(
                                                                new InterpreteurSujetJoueur(regleString.get(indRegleSyntaxe-3)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                (joueursFonctionComp,integersFonctionComp) -> !Fonctions_Comportements.timer_superieur_a.apply(joueursFonctionComp,integersFonctionComp));
                                                    }
                                                    default -> {
                                                        throw new MauvaiseDefinitionRegleException("Comparateur inconnu (=,<,>)");
                                                    }
                                                }
                                            } else {
                                                throw new MauvaiseSemantiqueRegleException("Bloc Joueur-Timer inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }
                                        }
                                        /*==== Reutilisation Alias ====*/
                                        case "0R14813" -> {
                                            //Alias sur Joueur : [JOUEUR]+COMPTEUR+COMPARAISON+NOMBRE
                                            if (regleString.get(indRegleSyntaxe - 2).equals("timer")) {
                                                Alias<Jeton, Joueur> alias;
                                                Alias<Jeton, ?> testAlias = null;

                                                try {
                                                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe - 3));
                                                    alias = (Alias<Jeton, Joueur>) testAlias;
                                                } catch (ClassCastException e) {
                                                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 3) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie() + ")");
                                                } catch (MauvaiseDefinitionRegleException re) {
                                                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 3) + " inconnu dans sa réutilisation ([JOUEUR]+COMPTEUR+COMPARAISON+NOMBRE)");
                                                }

                                                switch (regleString.get(indRegleSyntaxe - 1)) {
                                                    case "<" -> {
                                                        cond = new ConditionAction<Joueur, IntegerRegle>(
                                                                new Interpreteur_Alias_Sujet<Joueur>(alias),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                Fonctions_Comportements.timer_inferieur_a);
                                                    }
                                                    case "=" -> {
                                                        cond = new ConditionAction<Joueur, IntegerRegle>(
                                                                new Interpreteur_Alias_Sujet<Joueur>(alias),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                Fonctions_Comportements.timer_egal_a);
                                                    }
                                                    case ">" -> {
                                                        cond = new ConditionAction<Joueur, IntegerRegle>(
                                                                new Interpreteur_Alias_Sujet<Joueur>(alias),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                Fonctions_Comportements.timer_superieur_a);
                                                    }
                                                    default -> {
                                                        throw new MauvaiseDefinitionRegleException("Comparateur (alias) inconnu (=,<,>)");
                                                    }
                                                }
                                            }else {
                                                throw new MauvaiseSemantiqueRegleException("Bloc JoueurAlias-Timer inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }

                                        }
                                        case "0R14N813" -> {
                                            //Alias sur Joueur : [JOUEUR]+COMPTEUR+COMPARAISON+NOMBRE
                                            if (regleString.get(indRegleSyntaxe - 2).equals("timer")) {
                                                Alias<Jeton, Joueur> alias;
                                                Alias<Jeton, ?> testAlias = null;

                                                try {
                                                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe - 3));
                                                    alias = (Alias<Jeton, Joueur>) testAlias;
                                                } catch (ClassCastException e) {
                                                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 3) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie() + ")");
                                                } catch (MauvaiseDefinitionRegleException re) {
                                                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 3) + " inconnu dans sa réutilisation ([JOUEUR]+COMPTEUR+NON+COMPARAISON+NOMBRE)");
                                                }

                                                switch (regleString.get(indRegleSyntaxe - 1)) {
                                                    case "<" -> {
                                                        cond = new ConditionAction<Joueur, IntegerRegle>(
                                                                new Interpreteur_Alias_Sujet<Joueur>(alias),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                (joueursFonctionComp,integersFonctionComp) -> !Fonctions_Comportements.timer_inferieur_a.apply(joueursFonctionComp,integersFonctionComp));
                                                    }
                                                    case "=" -> {
                                                        cond = new ConditionAction<Joueur, IntegerRegle>(
                                                                new Interpreteur_Alias_Sujet<Joueur>(alias),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                (joueursFonctionComp,integersFonctionComp) -> !Fonctions_Comportements.timer_egal_a.apply(joueursFonctionComp,integersFonctionComp));
                                                    }
                                                    case ">" -> {
                                                        cond = new ConditionAction<Joueur, IntegerRegle>(
                                                                new Interpreteur_Alias_Sujet<Joueur>(alias),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                (joueursFonctionComp,integersFonctionComp) -> !Fonctions_Comportements.timer_superieur_a.apply(joueursFonctionComp,integersFonctionComp));
                                                    }
                                                    default -> {
                                                        throw new MauvaiseDefinitionRegleException("Comparateur (alias) inconnu (=,<,>)");
                                                    }
                                                }
                                            }else {
                                                throw new MauvaiseSemantiqueRegleException("Bloc JoueurAlias-Timer inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }

                                        }
                                        default -> {
                                            throw new MauvaiseSemantiqueRegleException("Bloc Joueur-Compteur-(NON)Comparaison-Nombre inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                        }
                                    }
                                    conditionsDeLaRegle.add(cond);
                                    nbConditions++;
                                    jetonsarborescence.add(Jeton.CONDITION);
                                    //Edition des Alias : ConditionDeDepart
                                    for(Alias<Jeton,?> alias : aliasCondtion){
                                        alias.setConditionDeDefinition(cond);
                                    }
                                    aliasCondtion.clear();
                                }else{
                                    throw new MauvaiseSemantiqueRegleException("Pas assez d'argument pour Joueur-Compteur-Comparaison-Nombre [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                }
                            }

                            case 314 -> {
                                //SUJET-COMPTEUR-COMPARAISON-NOMBRE
                                if (indRegleSyntaxe >= 3) {
                                    Condition cond = null;
                                    switch (parcours) {
                                        case "0271214" -> {
                                            //Cas Piece+Compteur+Comparaison+Nombre
                                            if (regleString.get(indRegleSyntaxe-2).equals("nb_deplacement")) {
                                                switch(regleString.get(indRegleSyntaxe-1)){
                                                    case "<" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                Fonctions_Comportements.deplacement_inferieur_a);
                                                    }
                                                    case "=" -> {
                                                        System.out.println("EGAL 0o");
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                Fonctions_Comportements.deplacement_egal_a);
                                                    }
                                                    case ">" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                Fonctions_Comportements.deplacement_superieur_a);
                                                    }
                                                    default -> {
                                                        throw new MauvaiseDefinitionRegleException("Comparateur inconnu (=,<,>)");
                                                    }
                                                }
                                            } else {
                                                throw new MauvaiseSemantiqueRegleException("Bloc Piece-Compteur-Comparaison-Nombre inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }
                                        }
                                        case "02371214" -> {
                                            //Cas Piece+Joueur+Compteur+Comparaison+Nombre
                                            if (regleString.get(indRegleSyntaxe - 2).equals("nb_deplacement")) {
                                                switch(regleString.get(indRegleSyntaxe-1)) {
                                                    case "<" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-4), regleString.get(indRegleSyntaxe-3)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                Fonctions_Comportements.deplacement_inferieur_a);
                                                    }
                                                    case "=" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-4), regleString.get(indRegleSyntaxe-3)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                Fonctions_Comportements.deplacement_egal_a);
                                                    }
                                                    case ">" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-4), regleString.get(indRegleSyntaxe-3)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                Fonctions_Comportements.deplacement_superieur_a);
                                                    }
                                                    default -> {
                                                        throw new MauvaiseDefinitionRegleException("Comparateur inconnu (=,<,>)");
                                                    }
                                                }
                                            } else {
                                                throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-Compteur-Comparaison-Nombre inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }
                                        }
                                        case "0371214" -> {
                                            //Cas PieceToken+Compteur+Comparaison+Nombre
                                            if (regleString.get(indRegleSyntaxe - 2).equals("nb_deplacement")) {
                                                switch(regleString.get(indRegleSyntaxe-1)){
                                                    case "<" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                Fonctions_Comportements.deplacement_inferieur_a);
                                                    }
                                                    case "=" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                Fonctions_Comportements.deplacement_egal_a);
                                                    }
                                                    case ">" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                Fonctions_Comportements.deplacement_superieur_a);
                                                    }
                                                    default -> {
                                                        throw new MauvaiseDefinitionRegleException("Comparateur inconnu (=,<,>)");
                                                    }
                                                }
                                            } else {
                                                throw new MauvaiseSemantiqueRegleException("Bloc PieceToken-Compteur-Comparaison-Nombre inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }
                                        }
                                        /*==== Reutilisation Alias ====*/
                                        case "0R271214" , "0R371214" -> {
                                            //Alias sur Piece : [PIECE(Token)]+COMPTEUR+COMPARAISON+NOMBRE
                                            Alias<Jeton,Piece> alias;
                                            Alias<Jeton,?> testAlias = null;

                                            try{
                                                testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                                                alias = (Alias<Jeton,Piece>) testAlias;
                                            }catch (ClassCastException e){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                                            }catch (MauvaiseDefinitionRegleException re){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation ([PIECE(Token)]+COMPTEUR+COMPARAISON+NOMBRE)");
                                            }
                                            if (regleString.get(indRegleSyntaxe - 2).equals("nb_deplacement")) {
                                                switch(regleString.get(indRegleSyntaxe-1)){
                                                    case "<" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new Interpreteur_Alias_Sujet<Piece>(alias),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                Fonctions_Comportements.deplacement_inferieur_a);
                                                    }
                                                    case "=" -> {
                                                        System.out.println("EGAL : alias: " + alias);
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new Interpreteur_Alias_Sujet<Piece>(alias),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                Fonctions_Comportements.deplacement_egal_a);
                                                    }
                                                    case ">" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new Interpreteur_Alias_Sujet<Piece>(alias),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                Fonctions_Comportements.deplacement_superieur_a);
                                                    }
                                                    default -> {
                                                        throw new MauvaiseDefinitionRegleException("Comparateur inconnu (=,<,>)");
                                                    }
                                                }
                                            } else {
                                                throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-Compteur-Comparaison-Nombre inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }
                                        }
                                        case "0R2371214" -> {
                                            //Alias sur Piece : [PIECE]+JOUEUR+COMPTEUR+COMPARAISON+NOMBRE
                                            Alias<Jeton,Piece> alias;
                                            Alias<Jeton,?> testAlias = null;

                                            try{
                                                testAlias = recupererAlias(regleString.get(indRegleSyntaxe-4));
                                                alias = (Alias<Jeton,Piece>) testAlias;
                                            }catch (ClassCastException e){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-4) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                                            }catch (MauvaiseDefinitionRegleException re){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-4) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+COMPTEUR+COMPARAISON+NOMBRE)");
                                            }
                                            if (regleString.get(indRegleSyntaxe - 2).equals("nb_deplacement")) {
                                                switch(regleString.get(indRegleSyntaxe-1)){
                                                    case "<" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias),regleString.get(indRegleSyntaxe-3)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                Fonctions_Comportements.deplacement_inferieur_a);
                                                    }
                                                    case "=" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias),regleString.get(indRegleSyntaxe-3)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                Fonctions_Comportements.deplacement_egal_a);
                                                    }
                                                    case ">" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias),regleString.get(indRegleSyntaxe-3)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                Fonctions_Comportements.deplacement_superieur_a);
                                                    }
                                                    default -> {
                                                        throw new MauvaiseDefinitionRegleException("Comparateur inconnu (=,<,>)");
                                                    }
                                                }
                                            } else {
                                                throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-Joueur-Compteur-Comparaison-Nombre inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }
                                        }
                                        case "02R371214" -> {
                                            //Alias sur Joueur : PIECE+[JOUEUR]+COMPTEUR+COMPARAISON+NOMBRE
                                            Alias<Jeton,Joueur> alias;
                                            Alias<Jeton,?> testAlias = null;

                                            try{
                                                testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                                                alias = (Alias<Jeton,Joueur>) testAlias;
                                            }catch (ClassCastException e){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                                            }catch (MauvaiseDefinitionRegleException re){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation (PIECE+[JOUEUR]+COMPTEUR+COMPARAISON+NOMBRE)");
                                            }
                                            if (regleString.get(indRegleSyntaxe - 2).equals("nb_deplacement")) {
                                                switch(regleString.get(indRegleSyntaxe-1)){
                                                    case "<" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias),regleString.get(indRegleSyntaxe-4)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                Fonctions_Comportements.deplacement_inferieur_a);
                                                    }
                                                    case "=" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias),regleString.get(indRegleSyntaxe-4)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                Fonctions_Comportements.deplacement_egal_a);
                                                    }
                                                    case ">" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias),regleString.get(indRegleSyntaxe-4)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                Fonctions_Comportements.deplacement_superieur_a);
                                                    }
                                                    default -> {
                                                        throw new MauvaiseDefinitionRegleException("Comparateur inconnu (=,<,>)");
                                                    }
                                                }
                                            } else {
                                                throw new MauvaiseSemantiqueRegleException("Bloc Piece-JoueurAlias-Compteur-Comparaison-Nombre inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }
                                        }
                                        case "0R2R371214" -> {
                                            //Alias sur Piece et Joueur : [PIECE]+[JOUEUR]+COMPTEUR+COMPARAISON+NOMBRE
                                            Alias<Jeton,Piece> alias1;
                                            Alias<Jeton,Joueur> alias2;
                                            Alias<Jeton,?> testAlias = null;

                                            try{
                                                testAlias = recupererAlias(regleString.get(indRegleSyntaxe-4));
                                                alias1 = (Alias<Jeton,Piece>) testAlias;
                                            }catch (ClassCastException e){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-4) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                                            }catch (MauvaiseDefinitionRegleException re){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-4) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+COMPTEUR+COMPARAISON+NOMBRE)");
                                            }

                                            try{
                                                testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                                                alias2 = (Alias<Jeton,Joueur>) testAlias;
                                            }catch (ClassCastException e){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                                            }catch (MauvaiseDefinitionRegleException re){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation (PIECE+[JOUEUR]+COMPTEUR+COMPARAISON+NOMBRE)");
                                            }
                                            if (regleString.get(indRegleSyntaxe - 2).equals("nb_deplacement")) {
                                                switch(regleString.get(indRegleSyntaxe-1)){
                                                    case "<" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1),new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                Fonctions_Comportements.deplacement_inferieur_a);
                                                    }
                                                    case "=" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1),new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                Fonctions_Comportements.deplacement_egal_a);
                                                    }
                                                    case ">" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1),new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                Fonctions_Comportements.deplacement_superieur_a);
                                                    }
                                                    default -> {
                                                        throw new MauvaiseDefinitionRegleException("Comparateur inconnu (=,<,>)");
                                                    }
                                                }
                                            } else {
                                                throw new MauvaiseSemantiqueRegleException("Bloc Piece-JoueurAlias-Compteur-Comparaison-Nombre inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }
                                        }
                                        /*==== Gestion Negation ====*/
                                        case "027N1214"-> {
                                            //Cas Piece+Compteur+NON+Comparaison+Nombre
                                            if (regleString.get(indRegleSyntaxe - 2).equals("nb_deplacement")) {
                                                switch(regleString.get(indRegleSyntaxe-1)){
                                                    case "<" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                (pieceFonctionComp,integersFonctionComp) ->!Fonctions_Comportements.deplacement_inferieur_a.apply(pieceFonctionComp,integersFonctionComp));
                                                    }
                                                    case "=" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                (pieceFonctionComp,integersFonctionComp) ->!Fonctions_Comportements.deplacement_egal_a.apply(pieceFonctionComp,integersFonctionComp));
                                                    }
                                                    case ">" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                (pieceFonctionComp,integersFonctionComp) -> !Fonctions_Comportements.deplacement_superieur_a.apply(pieceFonctionComp,integersFonctionComp));
                                                    }
                                                    default -> {
                                                        throw new MauvaiseDefinitionRegleException("Comparateur inconnu (=,<,>)");
                                                    }
                                                }
                                            } else {
                                                throw new MauvaiseSemantiqueRegleException("Bloc Piece-Compteur-NON-Comparaison-Nombre inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }
                                        }
                                        case "0237N1214" -> {
                                            //Cas Piece+Joueur+Compteur+NON+Comparaison+Nombre
                                            if (regleString.get(indRegleSyntaxe - 2).equals("nb_deplacement")) {
                                                switch(regleString.get(indRegleSyntaxe-1)) {
                                                    case "<" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-4), regleString.get(indRegleSyntaxe-3)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                (pieceFonctionComp,integersFonctionComp) ->!Fonctions_Comportements.deplacement_inferieur_a.apply(pieceFonctionComp,integersFonctionComp));
                                                    }
                                                    case "=" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-4), regleString.get(indRegleSyntaxe-3)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                (pieceFonctionComp,integersFonctionComp) ->!Fonctions_Comportements.deplacement_egal_a.apply(pieceFonctionComp,integersFonctionComp));
                                                    }
                                                    case ">" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-4), regleString.get(indRegleSyntaxe-3)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                (pieceFonctionComp,integersFonctionComp) -> !Fonctions_Comportements.deplacement_superieur_a.apply(pieceFonctionComp,integersFonctionComp));
                                                    }
                                                    default -> {
                                                        throw new MauvaiseDefinitionRegleException("Comparateur inconnu (=,<,>)");
                                                    }
                                                }
                                            } else {
                                                throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-Compteur-NON-Comparaison-Nombre inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }
                                        }
                                        case "037N1214" -> {
                                            //Cas PieceToken+Compteur+NON+Comparaison+Nombre
                                            if (regleString.get(indRegleSyntaxe - 2).equals("nb_deplacement")) {
                                                switch(regleString.get(indRegleSyntaxe-1)){
                                                    case "<" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                (pieceFonctionComp,integersFonctionComp) ->!Fonctions_Comportements.deplacement_inferieur_a.apply(pieceFonctionComp,integersFonctionComp));
                                                    }
                                                    case "=" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                (pieceFonctionComp,integersFonctionComp) ->!Fonctions_Comportements.deplacement_egal_a.apply(pieceFonctionComp,integersFonctionComp));
                                                    }
                                                    case ">" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                (pieceFonctionComp,integersFonctionComp) -> !Fonctions_Comportements.deplacement_superieur_a.apply(pieceFonctionComp,integersFonctionComp));
                                                    }
                                                    default -> {
                                                        throw new MauvaiseDefinitionRegleException("Comparateur inconnu (=,<,>)");
                                                    }
                                                }
                                            } else {
                                                throw new MauvaiseSemantiqueRegleException("Bloc PieceToken-Compteur-Non-Comparaison-Nombre inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }
                                        }

                                        case "0R27N1214" , "0R37N1214" -> {
                                            //Alias sur Piece : [PIECE(Token)]+COMPTEUR+NON+COMPARAISON+NOMBRE
                                            Alias<Jeton,Piece> alias;
                                            Alias<Jeton,?> testAlias = null;

                                            try{
                                                testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                                                alias = (Alias<Jeton,Piece>) testAlias;
                                            }catch (ClassCastException e){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                                            }catch (MauvaiseDefinitionRegleException re){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation ([PIECE(Token)]+COMPTEUR+NON+COMPARAISON+NOMBRE)");
                                            }
                                            if (regleString.get(indRegleSyntaxe - 2).equals("nb_deplacement")) {
                                                switch(regleString.get(indRegleSyntaxe-1)){
                                                    case "<" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new Interpreteur_Alias_Sujet<Piece>(alias),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                (pieceFonctionComp,integersFonctionComp) ->!Fonctions_Comportements.deplacement_inferieur_a.apply(pieceFonctionComp,integersFonctionComp));
                                                    }
                                                    case "=" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new Interpreteur_Alias_Sujet<Piece>(alias),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                (pieceFonctionComp,integersFonctionComp) ->!Fonctions_Comportements.deplacement_egal_a.apply(pieceFonctionComp,integersFonctionComp));
                                                    }
                                                    case ">" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new Interpreteur_Alias_Sujet<Piece>(alias),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                (pieceFonctionComp,integersFonctionComp) -> !Fonctions_Comportements.deplacement_superieur_a.apply(pieceFonctionComp,integersFonctionComp));
                                                    }
                                                    default -> {
                                                        throw new MauvaiseDefinitionRegleException("Comparateur inconnu (=,<,>)");
                                                    }
                                                }
                                            } else {
                                                throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-Compteur-Non-Comparaison-Nombre inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }
                                        }
                                        case "0R237N1214" -> {
                                            //Alias sur Piece : [PIECE]+JOUEUR+COMPTEUR+NON+COMPARAISON+NOMBRE
                                            Alias<Jeton,Piece> alias;
                                            Alias<Jeton,?> testAlias = null;

                                            try{
                                                testAlias = recupererAlias(regleString.get(indRegleSyntaxe-4));
                                                alias = (Alias<Jeton,Piece>) testAlias;
                                            }catch (ClassCastException e){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-4) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                                            }catch (MauvaiseDefinitionRegleException re){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-4) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+COMPTEUR+NON+COMPARAISON+NOMBRE)");
                                            }
                                            if (regleString.get(indRegleSyntaxe - 2).equals("nb_deplacement")) {
                                                switch(regleString.get(indRegleSyntaxe-1)){
                                                    case "<" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias),regleString.get(indRegleSyntaxe-3)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                (pieceFonctionComp,integersFonctionComp) ->!Fonctions_Comportements.deplacement_inferieur_a.apply(pieceFonctionComp,integersFonctionComp));
                                                    }
                                                    case "=" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias),regleString.get(indRegleSyntaxe-3)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                (pieceFonctionComp,integersFonctionComp) ->!Fonctions_Comportements.deplacement_egal_a.apply(pieceFonctionComp,integersFonctionComp));
                                                    }
                                                    case ">" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias),regleString.get(indRegleSyntaxe-3)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                (pieceFonctionComp,integersFonctionComp) -> !Fonctions_Comportements.deplacement_superieur_a.apply(pieceFonctionComp,integersFonctionComp));
                                                    }
                                                    default -> {
                                                        throw new MauvaiseDefinitionRegleException("Comparateur inconnu (=,<,>)");
                                                    }
                                                }
                                            } else {
                                                throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-Joueur-Compteur-Non-Comparaison-Nombre inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }
                                        }
                                        case "02R37N1214" -> {
                                            //Alias sur Joueur : PIECE+[JOUEUR]+COMPTEUR+NON+COMPARAISON+NOMBRE
                                            Alias<Jeton,Joueur> alias;
                                            Alias<Jeton,?> testAlias = null;

                                            try{
                                                testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                                                alias = (Alias<Jeton,Joueur>) testAlias;
                                            }catch (ClassCastException e){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                                            }catch (MauvaiseDefinitionRegleException re){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation (PIECE+[JOUEUR]+COMPTEUR+NON+COMPARAISON+NOMBRE)");
                                            }
                                            if (regleString.get(indRegleSyntaxe - 2).equals("nb_deplacement")) {
                                                switch(regleString.get(indRegleSyntaxe-1)){
                                                    case "<" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias),regleString.get(indRegleSyntaxe-4)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                (pieceFonctionComp,integersFonctionComp) ->!Fonctions_Comportements.deplacement_inferieur_a.apply(pieceFonctionComp,integersFonctionComp));
                                                    }
                                                    case "=" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias),regleString.get(indRegleSyntaxe-4)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                (pieceFonctionComp,integersFonctionComp) ->!Fonctions_Comportements.deplacement_egal_a.apply(pieceFonctionComp,integersFonctionComp));
                                                    }
                                                    case ">" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias),regleString.get(indRegleSyntaxe-4)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                (pieceFonctionComp,integersFonctionComp) -> !Fonctions_Comportements.deplacement_superieur_a.apply(pieceFonctionComp,integersFonctionComp));
                                                    }
                                                    default -> {
                                                        throw new MauvaiseDefinitionRegleException("Comparateur inconnu (=,<,>)");
                                                    }
                                                }
                                            } else {
                                                throw new MauvaiseSemantiqueRegleException("Bloc Piece-JoueurAlias-Compteur-Non-Comparaison-Nombre inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }
                                        }
                                        case "0R2R37N1214" -> {
                                            //Alias sur Piece et Joueur : [PIECE]+[JOUEUR]+COMPTEUR+NON+COMPARAISON+NOMBRE
                                            Alias<Jeton,Piece> alias1;
                                            Alias<Jeton,Joueur> alias2;
                                            Alias<Jeton,?> testAlias = null;

                                            try{
                                                testAlias = recupererAlias(regleString.get(indRegleSyntaxe-4));
                                                alias1 = (Alias<Jeton,Piece>) testAlias;
                                            }catch (ClassCastException e){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-4) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                                            }catch (MauvaiseDefinitionRegleException re){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-4) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+COMPTEUR+COMPARAISON+NOMBRE)");
                                            }

                                            try{
                                                testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                                                alias2 = (Alias<Jeton,Joueur>) testAlias;
                                            }catch (ClassCastException e){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                                            }catch (MauvaiseDefinitionRegleException re){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation (PIECE+[JOUEUR]+COMPTEUR+COMPARAISON+NOMBRE)");
                                            }
                                            if (regleString.get(indRegleSyntaxe - 2).equals("nb_deplacement")) {
                                                switch(regleString.get(indRegleSyntaxe-1)){
                                                    case "<" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1),new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                (pieceFonctionComp,integersFonctionComp) ->!Fonctions_Comportements.deplacement_inferieur_a.apply(pieceFonctionComp,integersFonctionComp));
                                                    }
                                                    case "=" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1),new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                (pieceFonctionComp,integersFonctionComp) ->!Fonctions_Comportements.deplacement_egal_a.apply(pieceFonctionComp,integersFonctionComp));
                                                    }
                                                    case ">" -> {
                                                        cond = new ConditionAction<Piece, IntegerRegle>(
                                                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1),new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                                                new InterpreteurInteger(regleString.get(indRegleSyntaxe)),
                                                                (pieceFonctionComp,integersFonctionComp) -> !Fonctions_Comportements.deplacement_superieur_a.apply(pieceFonctionComp,integersFonctionComp));
                                                    }
                                                    default -> {
                                                        throw new MauvaiseDefinitionRegleException("Comparateur inconnu (=,<,>)");
                                                    }
                                                }
                                            } else {
                                                throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-JoueurAlias-Compteur-Non-Comparaison-Nombre inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }
                                        }
                                        default -> {
                                            throw new MauvaiseSemantiqueRegleException("Bloc Piece-Compteur-Comparaison-Nombre OU Piece-Joueur-Compteur-Comparaison-Nombre OU PieceToken-Compteur-Comparaison-Nombre inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                        }
                                    }
                                    conditionsDeLaRegle.add(cond);
                                    nbConditions++;
                                    jetonsarborescence.add(Jeton.CONDITION);
                                    //Edition des Alias : ConditionDeDepart
                                    for(Alias<Jeton,?> alias : aliasCondtion){
                                        alias.setConditionDeDefinition(cond);
                                    }
                                    aliasCondtion.clear();
                                } else {
                                    throw new MauvaiseSemantiqueRegleException("Pas assez d'argument pour Piece(T)-Compteur-Comparaison-Nombre [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                }

                            }

                            case 327 , 334 -> {
                                if (indRegleSyntaxe >= 1) {
                                    Condition cond = case327_334(parcours, indRegleSyntaxe, regleSyntaxe, regleString);
                                    conditionsDeLaRegle.add(cond);
                                    nbConditions++;
                                    jetonsarborescence.add(Jeton.CONDITION);
                                    //Edition des Alias : ConditionDeDepart
                                    for(Alias<Jeton,?> alias : aliasCondtion){
                                        alias.setConditionDeDefinition(cond);
                                    }
                                    aliasCondtion.clear();
                                }else{
                                    throw new MauvaiseSemantiqueRegleException("Pas assez d'argument pour PieceToken-ConsequenceAction-Caseparam [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                }
                            }

                            /*---------------------------------CONSEQUENCES---------------------------------*/
                            //regle.ajouterUneConsequence(cons);
                            case 319 -> {
                                //Consequence Joueur+ConsequenceTerminale
                                if(indRegleSyntaxe >= 1) {
                                    Consequence conseq = case319(parcours, indRegleSyntaxe, regleSyntaxe, regleString);
                                    consequencesDeLaRegle.add(conseq);
                                    nbConsequence++;
                                }else{
                                    throw new MauvaiseSemantiqueRegleException("Pas assez d'argument pour Sujet-ConsequenceTerminale [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                }
                            }

                            case 321 -> {
                                //SUJET-CONSEQUENCE-PIECE ou SUJET-CONSEQUENCE-CASE-PIECE
                                if(indRegleSyntaxe>=2) {
                                    Consequence conseq = case321(parcours, indRegleSyntaxe, regleSyntaxe, regleString);
                                    nbConsequence++;
                                    consequencesDeLaRegle.add(conseq);
                                }else{
                                    throw new MauvaiseSemantiqueRegleException("Pas assez d'argument pour Sujet-ConsequenceAction-(Case)-Piece [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                }
                            }

                            case 322 -> {
                                //SUJET-CONSEQUENCEACTION-PIECETOKEN ou SUJET-CONSEQUENCEACTION-PIECE-JOUEUR ou SUJET-CONSEQUENCEACTION-CASE-PIECETOKEN ou SUJET-CONSEQUENCEACTION-CASE-PIECE-JOUEUR
                                if(indRegleSyntaxe>=2)  {
                                    Consequence conseq = case322(parcours, indRegleSyntaxe, regleSyntaxe, regleString);
                                    nbConsequence++;
                                    consequencesDeLaRegle.add(conseq);
                                }else{
                                    throw new MauvaiseSemantiqueRegleException("Pas assez d'argument pour Sujet-ConsequenceAction-(Case)-Piece(T,J) [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                }
                            }

                            case 323 -> {
                                //SUJET-CONSEQUENCE-CASE
                                if(indRegleSyntaxe>=2) {
                                    Consequence conseq = case323(parcours, indRegleSyntaxe, regleSyntaxe, regleString);
                                    nbConsequence++;
                                    consequencesDeLaRegle.add(conseq);
                                } else {
                                    throw new MauvaiseSemantiqueRegleException("Pas assez d'argument pour Sujet-ConsequenceAction-Case(T,J) [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                }
                            }

                            default -> { throw new MauvaiseSemantiqueRegleException("Bloc inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]"); }
                        }
                        parcours = "";  //on a gagné !

                    }
                    //Sinon on ne fait rien car on est terminal, on peut encore avancer et on est pas un connecteur adequat
                    //connecteur non adequat gérer au niveaux de transition automate

                } else {  //sinon état non terminal, continuer à l'état suivant
                    if (!peutAvancer(indRegleSyntaxe + 1, regleSyntaxe)) {
                        throw new MauvaiseSemantiqueRegleException("Bloc incomplet: ne fini pas par un jeton terminal [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }

            } else {
                //Erreur Etat Inconnu
                throw new MauvaiseSemantiqueRegleException("Transition inconnue (etat == null) : " + j.getValeur() + " à l'état: "+ predEtat + "(" + indRegleSyntaxe + ")");
            }
            indRegleSyntaxe++;
        }
        // Si sort de la boucle sans encombre, alors on a pu tout définir d'après le liste des Jetons et des String
        // Il faut désormais vérifier si notre règle comporte au moins un bloc condition et au moins un bloc conséquence
        if(nbConditions == 0){
            throw new MauvaiseSemantiqueRegleException("Il faut définir au moins une condition pour créer une règle");
        }
        if(nbConsequence == 0){
            throw new MauvaiseSemantiqueRegleException("Il faut définir au moins une consequence pour créer une règle");
        }
        //Ensuite, on essaye de générer l'arbre de conditions de notre règle
        regle.genererArbreCondition(conditionsDeLaRegle,jetonsarborescence);
        for (Consequence c: consequencesDeLaRegle) {
            regle.ajouterUneConsequence(c);
        }
        return regle;
    }



    public Condition case306(String parcours, int indRegleSyntaxe, List<Jeton> regleSyntaxe, List<String> regleString) throws MauvaiseDefinitionRegleException {
        Condition cond;
        switch (parcours) {
            case "026" -> {
                //Cas Piece+Etat
                if (regleString.get(indRegleSyntaxe).equals("estpromu")) {
                    cond = new ConditionEtat<Piece>(new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-1)), Fonctions_Comportements.estPromu);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-Etat inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "0236" -> {
                //Cas Piece+Joueur+Etat
                if (regleString.get(indRegleSyntaxe).equals("estpromu")) {
                    cond = new ConditionEtat<Piece>(new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2), regleString.get(indRegleSyntaxe-1)), Fonctions_Comportements.estPromu);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-Etat inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "036" -> {
                //Cas PieceToken+Etat
                if (regleString.get(indRegleSyntaxe).equals("estpromu")) {
                    cond = new ConditionEtat<Piece>(new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-1)), Fonctions_Comportements.estPromu);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc PieceToken-Etat inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            /*==== Reutilisation Alias ====*/
            case "0R2R36" -> {
                //Alias sur Piece et Joueur : PIECE+JOUEUR+ETAT
                if (regleString.get(indRegleSyntaxe).equals("estpromu")) {
                    Alias<Jeton,Piece> alias1;
                    Alias<Jeton,Joueur> alias2;
                    Alias<Jeton,?> testAlias = null;
                    try{
                        testAlias = recupererAlias(regleString.get(indRegleSyntaxe-1));
                        alias2 = (Alias<Jeton, Joueur>) testAlias;
                    } catch (ClassCastException e) {
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 1) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie() + ")");
                    } catch (MauvaiseDefinitionRegleException re){
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " inconnu dans sa réutilisation (PIECE+[JOUEUR]+ETAT)");
                    }
                    try {
                        testAlias = recupererAlias(regleString.get(indRegleSyntaxe - 2));
                        alias1 = (Alias<Jeton, Piece>) testAlias;
                    }catch (ClassCastException e) {
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 2) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                    } catch (MauvaiseDefinitionRegleException re){
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ETAT)");
                    }

                    cond = new ConditionEtat<Piece>(new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<>(alias1),new Interpreteur_Alias_Sujet<>(alias2)),Fonctions_Comportements.estPromu);
                }else{
                    throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-Etat inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "0R26", "0R36" -> {
                //Alias sur Piece(Token) : PIECE(Token)+ETAT
                if (regleString.get(indRegleSyntaxe).equals("estpromu")) {
                    Alias<Jeton,Piece> alias;
                    Alias<Jeton,?> testAlias = null;
                    try{
                        testAlias = recupererAlias(regleString.get(indRegleSyntaxe-1));
                        alias = (Alias<Jeton, Piece>) testAlias;
                    }catch (ClassCastException e){
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " connu mais référencant le mauvais type de donnée : (attendu PIECE(Token) | recu " + testAlias.getJetonAssocie()+")");
                    }catch (MauvaiseDefinitionRegleException re){
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " inconnu dans sa réutilisation (PIECE(Token)+ETAT)");
                    }
                    cond = new ConditionEtat<Piece>(new Interpreteur_Alias_Sujet<>(alias),Fonctions_Comportements.estPromu);
                }else{
                    throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-Etat inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "02R36" -> {
                //Alias sur Joueur : PIECE+JOUEUR+ETAT
                if (regleString.get(indRegleSyntaxe).equals("estpromu")) {
                    Alias<Jeton,Joueur> alias;
                    Alias<Jeton,?> testAlias = null;
                    try{
                        testAlias = recupererAlias(regleString.get(indRegleSyntaxe-1));
                        alias = (Alias<Jeton, Joueur>) testAlias;
                    } catch (ClassCastException e){
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                    } catch (MauvaiseDefinitionRegleException re){
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " inconnu dans sa réutilisation (PIECE+[JOUEUR]+ETAT)");
                    }
                    cond = new ConditionEtat<Piece>(new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias),regleString.get(indRegleSyntaxe-2)),Fonctions_Comportements.estPromu);
                }else{
                    throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-Etat inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "0R236" -> {
                //Alias sur Piece : PIECE+JOUEUR+ETAT
                if (regleString.get(indRegleSyntaxe).equals("estpromu")) {
                    Alias<Jeton,Piece> alias;
                    Alias<Jeton,?> testAlias = null;
                    try{
                        testAlias = recupererAlias(regleString.get(indRegleSyntaxe-1));
                        alias = (Alias<Jeton, Piece>) testAlias;
                    }  catch (MauvaiseDefinitionRegleException re){
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ETAT)");
                    }catch (ClassCastException e){
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                    }
                    cond = new ConditionEtat<Piece>(new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias), regleString.get(indRegleSyntaxe-1)),Fonctions_Comportements.estPromu);
                }else{
                    throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-Etat inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            default -> {
                throw new MauvaiseSemantiqueRegleException("Bloc Piece-Etat OU Piece-Joueur-Etat OU PieceToken-Etat inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
            }
        }
        return cond;
    }

    public Condition case309(String parcours, int indRegleSyntaxe, List<Jeton> regleSyntaxe, List<String> regleString) throws MauvaiseDefinitionRegleException{
        Condition cond = null;
        switch (parcours) {
            case "0259" -> {
                //Cas Piece+Action+Piece
                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "prend" ->{
                        cond = new ConditionAction<Piece, Piece>(new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)), new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)), Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond  = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Piece inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "0359" -> {
                //Cas PieceToken+Action+Piece
                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc PieceToken-Action-Piece inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "02359" -> {
                //Cas Piece+Joueur+Action+Piece
                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3), regleString.get(indRegleSyntaxe-2)),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3), regleString.get(indRegleSyntaxe-2)),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-Action-Piece inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            /*==== Reutilisation Alias ====*/
            case "0R259" , "0R359" ->{
                //Alias sur Piece : [PIECE(Token)]+ACTION+PIECE
                Alias<Jeton,Piece> alias;
                Alias<Jeton,?> testAlias = null;
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-2));
                    alias = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " connu mais référencant le mauvais type de donnée : (attendu PIECE(Token) | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " inconnu dans sa réutilisation ([PIECE(Token)]+ACTION+PIECE)");
                }
                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new Interpreteur_Alias_Sujet<>(alias),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new Interpreteur_Alias_Sujet<>(alias),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece(Token)Alias-Action-Piece inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "025R9" , "035R9"->{
                //Alias sur Piece : PIECE(Token)+ACTION+[PIECE]
                Alias<Jeton,Piece> alias;
                Alias<Jeton,?> testAlias = null;
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE(Token)+ACTION+[PIECE])");
                }
                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                                new Interpreteur_Alias_Cible<>(alias),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                                new Interpreteur_Alias_Cible<>(alias),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "0R25R9" , "0R35R9" ->{
                //Alias sur Piece et Piece : [PIECE(Token)]+ACTION+[PIECE]
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,Piece> alias2;
                Alias<Jeton,?> testAlias = null;
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-2));
                    alias1 = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " connu mais référencant le mauvais type de donnée : (attendu PIECE(Token) | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " inconnu dans sa réutilisation ([PIECE(Token)]+ACTION+PIECE)");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias2 = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE(Token)+ACTION+[PIECE])");
                }
                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new Interpreteur_Alias_Sujet<Piece>(alias1),
                                new Interpreteur_Alias_Cible<Piece>(alias2),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new Interpreteur_Alias_Sujet<Piece>(alias1),
                                new Interpreteur_Alias_Cible<Piece>(alias2),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-Action-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "0R2359" -> {
                //Alias sur Piece : [PIECE]+JOUEUR+ACTION+PIECE
                Alias<Jeton,Piece> alias;
                Alias<Jeton,?> testAlias = null;
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }
                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias),regleString.get(indRegleSyntaxe-2)),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias),regleString.get(indRegleSyntaxe-2)),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-Joueur-Action-Piece inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "02R359" -> {
                //Alias sur Joueur : PIECE+[JOUEUR]+ACTION+PIECE
                Alias<Jeton,Joueur> alias;
                Alias<Jeton,?> testAlias = null;
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-2));
                    alias = (Alias<Jeton, Joueur>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " inconnu dans sa réutilisation (PIECE+[JOUEUR]+ACTION+PIECE)");
                }
                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias),regleString.get(indRegleSyntaxe-3)),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias),regleString.get(indRegleSyntaxe-3)),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-JoueurAlias-Action-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }

            }
            case "0235R9" -> {
                //Alias sur Piece : PIECE+JOUEUR+ACTION+[PIECE]
                Alias<Jeton,Piece> alias;
                Alias<Jeton,?> testAlias = null;
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE+JOUEUR+ACTION+[PIECE])");
                }
                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3), regleString.get(indRegleSyntaxe-2)),
                                new Interpreteur_Alias_Cible<Piece>(alias),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3), regleString.get(indRegleSyntaxe-2)),
                                new Interpreteur_Alias_Cible<Piece>(alias),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-Action-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }

            }
            case "0R2R359" -> {
                //Alias sur Piece et Joueur : [PIECE]+[JOUEUR]+ACTION+PIECE
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,Joueur> alias2;
                Alias<Jeton,?> testAlias = null;
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias1 = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-2));
                    alias2 = (Alias<Jeton, Joueur>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " inconnu dans sa réutilisation (PIECE+[JOUEUR]+ACTION+PIECE)");
                }
                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1),new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1),new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-JoueurAlias-Action-Piece inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "0R235R9" -> {
                //Alias sur Piece et Piece : [PIECE]+JOUEUR+ACTION+[PIECE]
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,Piece> alias2;
                Alias<Jeton,?> testAlias = null;
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias1 = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias2 = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE+JOUEUR+ACTION+[PIECE])");
                }
                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias1),regleString.get(indRegleSyntaxe-2)),
                                new Interpreteur_Alias_Sujet<Piece>(alias2),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias1),regleString.get(indRegleSyntaxe-2)),
                                new Interpreteur_Alias_Sujet<Piece>(alias2),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-Joueur-Action-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }

            }
            case "02R35R9" -> {
                //Alias sur Piece et Joueur : PIECE+[JOUEUR]+ACTION+[PIECE]
                Alias<Jeton,Joueur> alias1;
                Alias<Jeton,Piece> alias2;
                Alias<Jeton,?> testAlias = null;
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-2));
                    alias1 = (Alias<Jeton, Joueur>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " inconnu dans sa réutilisation (PIECE+[JOUEUR]+ACTION+PIECE)");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias2 = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE+JOUEUR+ACTION+[PIECE])");
                }
                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias1),regleString.get(indRegleSyntaxe-3)),
                                new Interpreteur_Alias_Cible<Piece>(alias2),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias1),regleString.get(indRegleSyntaxe-3)),
                                new Interpreteur_Alias_Cible<Piece>(alias2),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-JoueurAlias-Action-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "0R2R35R9" -> {
                //Alias sur Piece et Joueur et Piece : [PIECE]+[JOUEUR]+ACTION+[PIECE]
                Alias<Jeton,Joueur> alias2;
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,Piece> alias3;
                Alias<Jeton,?> testAlias = null;
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias1 = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-2));
                    alias2 = (Alias<Jeton, Joueur>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " inconnu dans sa réutilisation (PIECE+[JOUEUR]+ACTION+PIECE)");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias3 = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE+JOUEUR+ACTION+[PIECE])");
                }

                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1),new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                new Interpreteur_Alias_Cible<Piece>(alias3),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1),new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                new Interpreteur_Alias_Cible<Piece>(alias3),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-JoueurAlias-Action-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }

            }
            default -> {
                throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Piece OU Piece-Joueur-Action-Piece OU PieceToken-Action-Piece inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
            }
        }
        return cond;
    }

    public Condition case310(String parcours, int indRegleSyntaxe, List<Jeton> regleSyntaxe, List<String> regleString) throws MauvaiseDefinitionRegleException{
        Condition cond;
        switch (parcours) {
            case "025910" -> {
                //Cas Piece+action+piece+joueur
                switch (regleString.get(indRegleSyntaxe - 2)) {
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3)),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3)),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Piece-Joueur inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "02510" -> {
                //Cas Piece+action+piecetoken
                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-PieceToken inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "035910" -> {
                //Cas Piecetoken+action+piece+joueur
                switch (regleString.get(indRegleSyntaxe - 2)) {
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3)),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3)),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc PieceToken-Action-Piece-Joueur inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "03510" -> {
                //Cas Piecetoken+action+piecetoken
                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc PieceToken-Action-PieceToken inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "0235910" -> {
                //Cas Piece+Joueur+action+piece+joueur
                switch (regleString.get(indRegleSyntaxe - 2)) {
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-4), regleString.get(indRegleSyntaxe-3)),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-4), regleString.get(indRegleSyntaxe-3)),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-Action-Piece-Joueur inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "023510" -> {
                //Cas Piece+Joueur+action+piecetoken
                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3), regleString.get(indRegleSyntaxe-2)),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3), regleString.get(indRegleSyntaxe-2)),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-Action-PieceToken inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            /*==== Reutilisation Alias ====*/
            case "0R25910" , "0R35910" ->{
                //Alias sur Piece : [PIECE(Token)]+ACTION+PIECE+JOUEUR
                Alias<Jeton,Piece> alias;
                Alias<Jeton,?> testAlias = null;
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE(Token) | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation ([PIECE(Token)]+ACTION+PIECE+JOUEUR)");
                }
                switch (regleString.get(indRegleSyntaxe - 2)) {
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new Interpreteur_Alias_Sujet<Piece>(alias),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new Interpreteur_Alias_Sujet<Piece>(alias),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-Action-Piece-Joueur inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "025R910" , "035R910" ->{
                //Alias sur Piece : PIECE(Token)+ACTION+[PIECE]+JOUEUR
                Alias<Jeton,Piece> alias;
                Alias<Jeton,?> testAlias = null;
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-1));
                    alias = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " inconnu dans sa réutilisation (PIECE(Token)+ACTION+[PIECE]+JOUEUR)");
                }
                switch (regleString.get(indRegleSyntaxe - 2)) {
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3)),
                                new InterpreteurCibleAliasJoueurPT(new Interpreteur_Alias_Cible<Piece>(alias), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3)),
                                new InterpreteurCibleAliasJoueurPT(new Interpreteur_Alias_Cible<Piece>(alias), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-PieceAlias-Joueur inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "0259R10" , "0359R10" ->{
                //Alias sur Joueur : PIECE(Token)+ACTION+PIECE+[JOUEUR]
                Alias<Jeton,Joueur> alias;
                Alias<Jeton,?> testAlias = null;
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias = (Alias<Jeton, Joueur>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE(Token)+ACTION+PIECE+[JOUEUR])");
                }
                switch (regleString.get(indRegleSyntaxe - 2)) {
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3)),
                                new InterpreteurCiblePieceAliasPT(new Interpreteur_Alias_Cible<Joueur>(alias), regleString.get(indRegleSyntaxe-1)),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3)),
                                new InterpreteurCiblePieceAliasPT(new Interpreteur_Alias_Cible<Joueur>(alias), regleString.get(indRegleSyntaxe-1)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Piece-JoueurAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "0R259R10" , "0R359R10" ->{
                //Alias sur Piece et Joueur : [PIECE(Token)]+ACTION+PIECE+[JOUEUR]
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,?> testAlias = null;
                Alias<Jeton,Joueur> alias2;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias1 = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation ([PIECE(Token)]+ACTION+PIECE+JOUEUR)");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias2 = (Alias<Jeton, Joueur>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE(Token)+ACTION+PIECE+[JOUEUR])");
                }
                switch (regleString.get(indRegleSyntaxe - 2)) {
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new Interpreteur_Alias_Sujet<Piece>(alias1),
                                new InterpreteurCiblePieceAliasPT(new Interpreteur_Alias_Cible<Joueur>(alias2), regleString.get(indRegleSyntaxe-1)),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new Interpreteur_Alias_Sujet<Piece>(alias1),
                                new InterpreteurCiblePieceAliasPT(new Interpreteur_Alias_Cible<Joueur>(alias2), regleString.get(indRegleSyntaxe-1)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-Action-Piece-JoueurAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "0R25R910" , "0R35R910" ->{
                //Alias sur Piece et Piece : [PIECE(Token)]+ACTION+[PIECE]+JOUEUR
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,?> testAlias = null;
                Alias<Jeton,Piece> alias2;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias1 = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation ([PIECE(Token)]+ACTION+PIECE+JOUEUR)");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-1));
                    alias2 = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " inconnu dans sa réutilisation (PIECE(Token)+ACTION+[PIECE]+JOUEUR)");
                }
                switch (regleString.get(indRegleSyntaxe - 2)) {
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new Interpreteur_Alias_Sujet<Piece>(alias1),
                                new InterpreteurCibleAliasJoueurPT(new Interpreteur_Alias_Cible<Piece>(alias2), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new Interpreteur_Alias_Sujet<Piece>(alias1),
                                new InterpreteurCibleAliasJoueurPT(new Interpreteur_Alias_Cible<Piece>(alias2), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-Action-PieceAlias-Joueur inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "025R9R10" , "035R9R10" ->{
                //Alias sur Piece et Joueur : PIECE(Token)+ACTION+[PIECE]+[JOUEUR]
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,?> testAlias = null;
                Alias<Jeton,Joueur> alias2;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-1));
                    alias1 = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " inconnu dans sa réutilisation (PIECE(Token)+ACTION+[PIECE]+JOUEUR)");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias2 = (Alias<Jeton, Joueur>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE(Token)+ACTION+PIECE+[JOUEUR])");
                }
                switch (regleString.get(indRegleSyntaxe - 2)) {
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3)),
                                new InterpreteurCibleAliasAliasPT(new Interpreteur_Alias_Cible<Piece>(alias1), new Interpreteur_Alias_Cible<Joueur>(alias2)),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3)),
                                new InterpreteurCibleAliasAliasPT(new Interpreteur_Alias_Cible<Piece>(alias1), new Interpreteur_Alias_Cible<Joueur>(alias2)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-Action-PieceAlias-JoueurAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "0R25R9R10" , "0R35R9R10" ->{
                //Alias sur Piece et Piece et Joueur : [PIECE(Token)]+ACTION+[PIECE]+[JOUEUR]
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,?> testAlias = null;
                Alias<Jeton,Joueur> alias3;
                Alias<Jeton,Piece> alias2;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias1 = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation ([PIECE(Token)]+ACTION+PIECE+JOUEUR)");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-1));
                    alias2 = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " inconnu dans sa réutilisation (PIECE(Token)+ACTION+[PIECE]+JOUEUR)");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias3 = (Alias<Jeton, Joueur>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE(Token)+ACTION+PIECE+[JOUEUR])");
                }
                switch (regleString.get(indRegleSyntaxe - 2)) {
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new Interpreteur_Alias_Sujet<Piece>(alias1),
                                new InterpreteurCibleAliasAliasPT(new Interpreteur_Alias_Cible<Piece>(alias2), new Interpreteur_Alias_Cible<Joueur>(alias3)),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new Interpreteur_Alias_Sujet<Piece>(alias1),
                                new InterpreteurCibleAliasAliasPT(new Interpreteur_Alias_Cible<Piece>(alias2), new Interpreteur_Alias_Cible<Joueur>(alias3)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-Action-PieceAlias-JoueurAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }

            case "0R2510" , "0R3510" ->{
                //Alias sur Piece : [PIECE(Token)]+ACTION+PIECETOKEN
                Alias<Jeton,Piece> alias;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-2));
                    alias = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " inconnu dans sa réutilisation ([PIECE(Token)]+ACTION+PIECETOKEN)");
                }
                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new Interpreteur_Alias_Sujet<Piece>(alias),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new Interpreteur_Alias_Sujet<Piece>(alias),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-Action-PieceToken inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "025R10" , "035R10" ->{
                //Alias sur Piece : PIECE(Token)+ACTION+[PIECETOKEN]
                Alias<Jeton,Piece> alias;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECETOKEN | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE(Token)+ACTION+[PIECETOKEN])");
                }
                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                                new Interpreteur_Alias_Cible<Piece>(alias),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                                new Interpreteur_Alias_Cible<Piece>(alias),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-PieceTokenAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "0R25R10" , "0R35R10" ->{
                //Alias sur Piece et PieceToken : PIECE(Token)+ACTION+[PIECETOKEN]
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,Piece> alias2;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-2));
                    alias1 = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " inconnu dans sa réutilisation ([PIECE(Token)]+ACTION+PIECETOKEN)");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias2 = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECETOKEN | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE(Token)+ACTION+[PIECETOKEN])");
                }

                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new Interpreteur_Alias_Sujet<Piece>(alias1),
                                new Interpreteur_Alias_Cible<Piece>(alias2),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new Interpreteur_Alias_Sujet<Piece>(alias1),
                                new Interpreteur_Alias_Cible<Piece>(alias2),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-PieceTokenAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }

            case "0R235910" -> {
                //Alias sur Piece : [PIECE]+JOUEUR+ACTION+PIECE+JOUEUR
                Alias<Jeton,Piece> alias;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-4));
                    alias = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-4) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-4) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE+JOUEUR)");
                }

                switch (regleString.get(indRegleSyntaxe - 2)){
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias), regleString.get(indRegleSyntaxe-3)),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias), regleString.get(indRegleSyntaxe-3)),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-Joueur-Action-Piece-Joueur inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "02R35910" -> {
                //Alias sur Joueur : PIECE+[JOUEUR]+ACTION+PIECE+JOUEUR
                Alias<Jeton,Joueur> alias;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias = (Alias<Jeton, Joueur>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation (PIECE+[JOUEUR]+ACTION+PIECE+JOUEUR)");
                }

                switch (regleString.get(indRegleSyntaxe - 2)){
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias), regleString.get(indRegleSyntaxe-4)),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias), regleString.get(indRegleSyntaxe-4)),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-JoueurAlias-Action-Piece-Joueur inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "0235R910" -> {
                //Alias sur Piece : PIECE+JOUEUR+ACTION+[PIECE]+JOUEUR
                Alias<Jeton,Piece> alias;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-1));
                    alias = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " inconnu dans sa réutilisation (PIECE+JOUEUR+ACTION+[PIECE]+JOUEUR)");
                }

                switch (regleString.get(indRegleSyntaxe - 2)){
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-4), regleString.get(indRegleSyntaxe-3)),
                                new InterpreteurCibleAliasJoueurPT(new Interpreteur_Alias_Cible<Piece>(alias), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-4), regleString.get(indRegleSyntaxe-3)),
                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-Action-PieceAlias-Joueur inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "02359R10" -> {
                //Alias sur Piece : PIECE+JOUEUR+ACTION+PIECE+[JOUEUR]
                Alias<Jeton,Joueur> alias;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias = (Alias<Jeton, Joueur>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE+JOUEUR+ACTION+PIECE+[JOUEUR])");
                }

                switch (regleString.get(indRegleSyntaxe - 2)){
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-4), regleString.get(indRegleSyntaxe-3)),
                                new InterpreteurCiblePieceAliasPT(new Interpreteur_Alias_Cible<Joueur>(alias), regleString.get(indRegleSyntaxe-1)),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-4), regleString.get(indRegleSyntaxe-3)),
                                new InterpreteurCiblePieceAliasPT(new Interpreteur_Alias_Cible<Joueur>(alias), regleString.get(indRegleSyntaxe-1)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-Action-Piece-JoueurAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "0235R9R10" -> {
                //Alias sur Piece et Joueur : PIECE+JOUEUR+ACTION+[PIECE]+[JOUEUR]
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,Joueur> alias2;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-1));
                    alias1 = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " inconnu dans sa réutilisation (PIECE+JOUEUR+ACTION+[PIECE]+JOUEUR)");
                }

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias2 = (Alias<Jeton, Joueur>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE+JOUEUR+ACTION+PIECE+[JOUEUR])");
                }

                switch (regleString.get(indRegleSyntaxe - 2)){
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-4), regleString.get(indRegleSyntaxe-3)),
                                new InterpreteurCibleAliasAliasPT(new Interpreteur_Alias_Cible<Piece>(alias1), new Interpreteur_Alias_Cible<Joueur>(alias2)),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-4), regleString.get(indRegleSyntaxe-3)),
                                new InterpreteurCibleAliasAliasPT(new Interpreteur_Alias_Cible<Piece>(alias1), new Interpreteur_Alias_Cible<Joueur>(alias2)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-Action-PieceAlias-JoueurAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "02R359R10" -> {
                //Alias sur Joueur et Joueur : PIECE+[JOUEUR]+ACTION+PIECE+[JOUEUR]
                Alias<Jeton,Joueur> alias2;
                Alias<Jeton,Joueur> alias1;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias1 = (Alias<Jeton, Joueur>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation (PIECE+[JOUEUR]+ACTION+PIECE+JOUEUR)");
                }

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias2 = (Alias<Jeton, Joueur>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE+JOUEUR+ACTION+PIECE+[JOUEUR])");
                }

                switch (regleString.get(indRegleSyntaxe - 2)){
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias1),regleString.get(indRegleSyntaxe-4)),
                                new InterpreteurCiblePieceAliasPT(new Interpreteur_Alias_Cible<Joueur>(alias2), regleString.get(indRegleSyntaxe-1)),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias1),regleString.get(indRegleSyntaxe-4)),
                                new InterpreteurCiblePieceAliasPT(new Interpreteur_Alias_Cible<Joueur>(alias2), regleString.get(indRegleSyntaxe-1)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-JoueurAlias-Action-Piece-JoueurAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "02R35R910" -> {
                //Alias sur Joueur et Piece : PIECE+[JOUEUR]+ACTION+[PIECE]+JOUEUR
                Alias<Jeton,Piece> alias2;
                Alias<Jeton,Joueur> alias1;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias1 = (Alias<Jeton, Joueur>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation (PIECE+[JOUEUR]+ACTION+PIECE+JOUEUR)");
                }

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-1));
                    alias2 = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " inconnu dans sa réutilisation (PIECE+JOUEUR+ACTION+[PIECE]+JOUEUR)");
                }

                switch (regleString.get(indRegleSyntaxe - 2)){
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias1),regleString.get(indRegleSyntaxe-4)),
                                new InterpreteurCibleAliasJoueurPT(new Interpreteur_Alias_Cible<Piece>(alias2), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias1),regleString.get(indRegleSyntaxe-4)),
                                new InterpreteurCibleAliasJoueurPT(new Interpreteur_Alias_Cible<Piece>(alias2), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-JoueurAlias-Action-PieceAlias-Joueur inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "0R2359R10" -> {
                //Alias sur Piece et Joueur : [PIECE]+JOUEUR+ACTION+PIECE+[JOUEUR]
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,Joueur> alias2;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-4));
                    alias1 = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-4) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-4) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE+JOUEUR)");
                }

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias2 = (Alias<Jeton, Joueur>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE+JOUEUR+ACTION+PIECE+[JOUEUR])");
                }

                switch (regleString.get(indRegleSyntaxe - 2)){
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias1),regleString.get(indRegleSyntaxe-3)),
                                new InterpreteurCiblePieceAliasPT(new Interpreteur_Alias_Cible<Joueur>(alias2), regleString.get(indRegleSyntaxe-1)),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias1),regleString.get(indRegleSyntaxe-3)),
                                new InterpreteurCiblePieceAliasPT(new Interpreteur_Alias_Cible<Joueur>(alias2), regleString.get(indRegleSyntaxe-1)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-Joueur-Action-Piece-JoueurAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "0R235R910" -> {
                //Alias sur Piece et Piece : [PIECE]+JOUEUR+ACTION+[PIECE]+JOUEUR
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,Piece> alias2;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-4));
                    alias1 = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-4) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-4) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE+JOUEUR)");
                }

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-1));
                    alias2 = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " inconnu dans sa réutilisation (PIECE+JOUEUR+ACTION+[PIECE]+JOUEUR)");
                }

                switch (regleString.get(indRegleSyntaxe - 2)){
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias1),regleString.get(indRegleSyntaxe-3)),
                                new InterpreteurCibleAliasJoueurPT(new Interpreteur_Alias_Cible<Piece>(alias2), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias1),regleString.get(indRegleSyntaxe-3)),
                                new InterpreteurCibleAliasJoueurPT(new Interpreteur_Alias_Cible<Piece>(alias2), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-Joueur-Action-PieceAlias-Joueur inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "0R2R35910" -> {
                //Alias sur Piece et Joueur : [PIECE]+[JOUEUR]+ACTION+PIECE+JOUEUR
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,Joueur> alias2;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-4));
                    alias1 = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-4) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-4) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE+JOUEUR)");
                }

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias2 = (Alias<Jeton, Joueur>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation (PIECE+[JOUEUR]+ACTION+PIECE+JOUEUR)");
                }

                switch (regleString.get(indRegleSyntaxe - 2)){
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1),new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1),new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-JoueurAlias-Action-Piece-Joueur inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "02R35R9R10" -> {
                //Alias sur Joueur et Piece et Joueur : PIECE+[JOUEUR]+ACTION+[PIECE]+[JOUEUR]
                Alias<Jeton,Joueur> alias1;
                Alias<Jeton,Piece> alias2;
                Alias<Jeton,Joueur> alias3;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias1 = (Alias<Jeton, Joueur>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation (PIECE+[JOUEUR]+ACTION+PIECE+JOUEUR)");
                }

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-1));
                    alias2 = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " inconnu dans sa réutilisation (PIECE+JOUEUR+ACTION+[PIECE]+JOUEUR)");
                }

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias3 = (Alias<Jeton, Joueur>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE+JOUEUR+ACTION+PIECE+[JOUEUR])");
                }

                switch (regleString.get(indRegleSyntaxe - 2)){
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias1),regleString.get(indRegleSyntaxe-4)),
                                new InterpreteurCibleAliasAliasPT(new Interpreteur_Alias_Cible<Piece>(alias2),new Interpreteur_Alias_Cible<Joueur>(alias3)),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias1),regleString.get(indRegleSyntaxe-4)),
                                new InterpreteurCibleAliasAliasPT(new Interpreteur_Alias_Cible<Piece>(alias2),new Interpreteur_Alias_Cible<Joueur>(alias3)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-JoueurAlias-Action-PieceAlias-JoueurAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "0R235R9R10" -> {
                //Alias sur Piece et Piece et Joueur : [PIECE]+JOUEUR+ACTION+[PIECE]+[JOUEUR]
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,Piece> alias2;
                Alias<Jeton,Joueur> alias3;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-4));
                    alias1 = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-4) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-4) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE+JOUEUR)");
                }

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-1));
                    alias2 = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " inconnu dans sa réutilisation (PIECE+JOUEUR+ACTION+[PIECE]+JOUEUR)");
                }

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias3 = (Alias<Jeton, Joueur>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE+JOUEUR+ACTION+PIECE+[JOUEUR])");
                }

                switch (regleString.get(indRegleSyntaxe - 2)){
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias1),regleString.get(indRegleSyntaxe-3)),
                                new InterpreteurCibleAliasAliasPT(new Interpreteur_Alias_Cible<Piece>(alias2),new Interpreteur_Alias_Cible<Joueur>(alias3)),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias1),regleString.get(indRegleSyntaxe-3)),
                                new InterpreteurCibleAliasAliasPT(new Interpreteur_Alias_Cible<Piece>(alias2),new Interpreteur_Alias_Cible<Joueur>(alias3)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-Joueur-Action-PieceAlias-JoueurAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "0R2R359R10" -> {
                //Alias sur Piece et Joueur et Joueur : [PIECE]+[JOUEUR]+ACTION+PIECE+[JOUEUR]
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,Joueur> alias2;
                Alias<Jeton,Joueur> alias3;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-4));
                    alias1 = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-4) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-4) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE+JOUEUR)");
                }

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias2 = (Alias<Jeton, Joueur>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation (PIECE+[JOUEUR]+ACTION+PIECE+JOUEUR)");
                }

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias3 = (Alias<Jeton, Joueur>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE+JOUEUR+ACTION+PIECE+[JOUEUR])");
                }

                switch (regleString.get(indRegleSyntaxe - 2)){
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1),new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                new InterpreteurCiblePieceAliasPT(new Interpreteur_Alias_Cible<Joueur>(alias3),regleString.get(indRegleSyntaxe - 1)),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1),new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                new InterpreteurCiblePieceAliasPT(new Interpreteur_Alias_Cible<Joueur>(alias3),regleString.get(indRegleSyntaxe - 1)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-JoueurAlias-Action-Piece-JoueurAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "0R2R35R910" -> {
                //Alias sur Piece et Joueur et Piece : [PIECE]+[JOUEUR]+ACTION+[PIECE]+JOUEUR
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,Joueur> alias2;
                Alias<Jeton,Piece> alias3;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-4));
                    alias1 = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-4) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-4) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE+JOUEUR)");
                }

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias2 = (Alias<Jeton, Joueur>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation (PIECE+[JOUEUR]+ACTION+PIECE+JOUEUR)");
                }

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-1));
                    alias3 = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " inconnu dans sa réutilisation (PIECE+JOUEUR+ACTION+[PIECE]+JOUEUR)");
                }

                switch (regleString.get(indRegleSyntaxe - 2)){
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1),new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                new InterpreteurCibleAliasJoueurPT(new Interpreteur_Alias_Cible<Piece>(alias3),regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1),new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                new InterpreteurCibleAliasJoueurPT(new Interpreteur_Alias_Cible<Piece>(alias3),regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-JoueurAlias-Action-PieceAlias-Joueur inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "0R2R35R9R10" -> {
                //Alias sur Piece et Joueur et Piece et Joueur : [PIECE]+[JOUEUR]+ACTION+[PIECE]+[JOUEUR]
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,Joueur> alias2;
                Alias<Jeton,Piece> alias3;
                Alias<Jeton,Joueur> alias4;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-4));
                    alias1 = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-4) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-4) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE+JOUEUR)");
                }

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias2 = (Alias<Jeton, Joueur>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation (PIECE+[JOUEUR]+ACTION+PIECE+JOUEUR)");
                }

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-1));
                    alias3 = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " inconnu dans sa réutilisation (PIECE+JOUEUR+ACTION+[PIECE]+JOUEUR)");
                }

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias4 = (Alias<Jeton, Joueur>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE+JOUEUR+ACTION+PIECE+[JOUEUR])");
                }

                switch (regleString.get(indRegleSyntaxe - 2)){
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1),new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                new InterpreteurCibleAliasAliasPT(new Interpreteur_Alias_Cible<Piece>(alias3),new Interpreteur_Alias_Cible<Joueur>(alias4)),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1),new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                new InterpreteurCibleAliasAliasPT(new Interpreteur_Alias_Cible<Piece>(alias3),new Interpreteur_Alias_Cible<Joueur>(alias4)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-JoueurAlias-Action-PieceAlias-JoueurAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }

            case "0R23510" -> {
                //Alias sur Piece : [PIECE]+JOUEUR+ACTION+PIECETOKEN
                Alias<Jeton,Piece> alias;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECETOKEN)");
                }

                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias), regleString.get(indRegleSyntaxe-2)),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias), regleString.get(indRegleSyntaxe-2)),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-Joueur-Action-PieceToken inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "02R3510" -> {
                //Alias sur Joueur : PIECE+[JOUEUR]+ACTION+PIECETOKEN
                Alias<Jeton,Joueur> alias;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-2));
                    alias = (Alias<Jeton, Joueur>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " inconnu dans sa réutilisation (PIECE+[JOUEUR]+ACTION+PIECETOKEN)");
                }

                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias), regleString.get(indRegleSyntaxe-3)),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias), regleString.get(indRegleSyntaxe-3)),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-JoueurAlias-Action-PieceToken inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "0235R10" -> {
                //Alias sur PieceToken : PIECE+JOUEUR+ACTION+[PIECETOKEN]
                Alias<Jeton,Piece> alias;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECETOKEN | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE+JOUEUR+ACTION+[PIECETOKEN])");
                }

                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3), regleString.get(indRegleSyntaxe-2)),
                                new Interpreteur_Alias_Cible<Piece>(alias),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3), regleString.get(indRegleSyntaxe-2)),
                                new Interpreteur_Alias_Cible<Piece>(alias),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-Action-PieceTokenAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "02R35R10" -> {
                //Alias sur Joueur et PieceToken : PIECE+[JOUEUR]+ACTION+[PIECETOKEN]
                Alias<Jeton,Joueur> alias1;
                Alias<Jeton,Piece> alias2;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-2));
                    alias1 = (Alias<Jeton, Joueur>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " inconnu dans sa réutilisation (PIECE+[JOUEUR]+ACTION+PIECETOKEN)");
                }

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias2 = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECETOKEN | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE+JOUEUR+ACTION+[PIECETOKEN])");
                }

                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias1), regleString.get(indRegleSyntaxe-3)),
                                new Interpreteur_Alias_Cible<Piece>(alias2),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias1), regleString.get(indRegleSyntaxe-3)),
                                new Interpreteur_Alias_Cible<Piece>(alias2),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-JoueurAlias-Action-PieceTokenAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "0R2R3510" -> {
                //Alias sur Piece et Joueur : [PIECE]+[JOUEUR]+ACTION+PIECETOKEN
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,Joueur> alias2;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias1 = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECETOKEN)");
                }

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-2));
                    alias2 = (Alias<Jeton, Joueur>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " inconnu dans sa réutilisation (PIECE+[JOUEUR]+ACTION+PIECETOKEN)");
                }

                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1),new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1),new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-JoueurAlias-Action-PieceToken inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "0R235R10" -> {
                //Alias sur Piece et PieceToken : [PIECE]+JOUEUR+ACTION+[PIECETOKEN]
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,Piece> alias2;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias1 = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECETOKEN)");
                }

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias2 = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECETOKEN | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE+JOUEUR+ACTION+[PIECETOKEN])");
                }

                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias1),regleString.get(indRegleSyntaxe-2)),
                                new Interpreteur_Alias_Cible<Piece>(alias2),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias1),regleString.get(indRegleSyntaxe-2)),
                                new Interpreteur_Alias_Cible<Piece>(alias2),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-Joueur-Action-PieceTokenAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            case "0R2R35R10" -> {
                //Alias sur Piece et Joueur et PieceToken : [PIECE]+[JOUEUR]+ACTION+[PIECETOKEN]
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,Joueur> alias2;
                Alias<Jeton,Piece> alias3;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias1 = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECETOKEN)");
                }

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-2));
                    alias2 = (Alias<Jeton, Joueur>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " inconnu dans sa réutilisation (PIECE+[JOUEUR]+ACTION+PIECETOKEN)");
                }

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias3 = (Alias<Jeton, Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECETOKEN | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE+JOUEUR+ACTION+[PIECETOKEN])");
                }

                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "prend" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1),new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                new Interpreteur_Alias_Cible<Piece>(alias3),
                                Fonctions_Comportements.prend_Par_Piece);
                    }
                    case "estechec" -> {
                        cond = new ConditionAction<Piece, Piece>(
                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1),new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                new Interpreteur_Alias_Cible<Piece>(alias3),
                                Fonctions_Comportements.est_Menace);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-JoueurAlias-Action-PieceTokenAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            default -> {
                throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Piece-Joueur OU Piece-Action-PieceToken OU Piece-Joueur-Action-Piece-Joueur OU Piece-Joueur-Action-PieceToken OU PieceToken-Action-Piece-Joueur OU PieceToken-Action-PieceToken inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
            }
        }
        return cond;
    }

    public Consequence case319(String parcours, int indRegleSyntaxe, List<Jeton> regleSyntaxe, List<String> regleString) throws MauvaiseDefinitionRegleException{
        Consequence conseq;
        switch (parcours){
            case "151619" -> {
                //"victoire", "defaite", "pat"
                switch (regleString.get(indRegleSyntaxe)) {
                    case "victoire" ->{
                        conseq = new ConsequenceTerminale<Joueur>(
                                new InterpreteurSujetJoueur(regleString.get(indRegleSyntaxe-1)),
                                Fonctions_Comportements.victoire);
                    }
                    case "defaite" -> {
                        conseq = new ConsequenceTerminale<Joueur>(
                                new InterpreteurSujetJoueur(regleString.get(indRegleSyntaxe-1)),
                                Fonctions_Comportements.defaite);
                    }
                    case "pat" -> {
                        conseq = new ConsequenceTerminale<Joueur>(
                                new InterpreteurSujetJoueur(regleString.get(indRegleSyntaxe-1)),
                                Fonctions_Comportements.pat);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Joueur-ConsequenceTerminale inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            /*==== Reutilisation Alias ====*/
            case "15R1619" -> {
                //"victoire", "defaite", "pat"
                //Alias sur Joueur
                Alias<Jeton,Joueur> alias;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-1));
                    alias = (Alias<Jeton,Joueur>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " inconnu dans sa réutilisation ([JOUEUR]+ConsequenceTerminale)");
                }
                switch (regleString.get(indRegleSyntaxe)) {
                    case "victoire" ->{
                        conseq = new ConsequenceTerminale<Joueur>(
                                new Interpreteur_Alias_Sujet<Joueur>(alias),
                                Fonctions_Comportements.victoire);
                    }
                    case "defaite" -> {
                        conseq = new ConsequenceTerminale<Joueur>(
                                new Interpreteur_Alias_Sujet<Joueur>(alias),
                                Fonctions_Comportements.defaite);
                    }
                    case "pat" -> {
                        conseq = new ConsequenceTerminale<Joueur>(
                                new Interpreteur_Alias_Sujet<Joueur>(alias),
                                Fonctions_Comportements.pat);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc JoueurAlias-ConsequenceTerminale inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                    }
                }
            }
            default -> {
                //Pas atteignable en théorie (on ne peut atteindre 19 que part Joueur)
                throw new MauvaiseSemantiqueRegleException("Bloc Sujet-ConsequenceTerminale inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]" + parcours);
            }
        }
        return conseq;
    }

    public Consequence case321(String parcours, int indRegleSyntaxe, List<Jeton> regleSyntaxe, List<String> regleString) throws MauvaiseDefinitionRegleException{
        Consequence conseq;
        switch (parcours) {
            //en passant par 20-21 (pièce)
            // SUJET-CONSEQUENCE-PIECE
            case "15162021" -> {
                // Joueur+Consequence+Piece
                throw new MauvaiseSemantiqueRegleException("Bloc Joueur-ConsequenceAction-Piece inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
            }
            case "15172021" -> {
                // Piece+Consequence+Piece
                if (regleString.get(indRegleSyntaxe - 1).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-Piece inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "1517182021" -> {
                // Piece+Joueur+Consequence+Piece
                if (regleString.get(indRegleSyntaxe - 1).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3), regleString.get(indRegleSyntaxe-2)),
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-ConsequenceAction-Piece inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "15182021" -> {
                // Piecetoken+Consequence+Piece
                if (regleString.get(indRegleSyntaxe - 1).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piecetoken-ConsequenceAction-Piece inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }

            //en passant par 20-23 (case + pièce)
            // SUJET-CONSEQUENCE-CASE-PIECE
            case "1516202321" -> {
                // Joueur+Consequence+Case+Piece
                //[P1, estsur, tous-case, alors, J0, placer, C5, P5]
                if (regleString.get(indRegleSyntaxe - 2).equals("placer")) {
                    conseq = new ConsequenceAction<Piece, GroupCases>(
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe),regleString.get(indRegleSyntaxe - 3)),
                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe - 1)),
                            Fonctions_Comportements.placer);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Joueur-ConsequenceAction-Case-Piece inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "1517202321" -> {
                // Piece+Consequence+Case+Piece
                throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-Case-Piece inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
            }
            case "151718202321" -> {
                // Piece+Joueur+Case+Piece
                throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-ConsequenceAction-Case-Piece inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
            }
            case "1518202321" -> {
                // PieceToken+Consequence+Case+Piece
                throw new MauvaiseSemantiqueRegleException("Bloc Piecetoken-ConsequenceAction-Case-Piece inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
            }

            /*==== Reutilisation Alias ====*/
            case "15R172021" , "15R182021" -> {
                //Alias sur Piece : [PIECE(Token)]+CONSEQUENCEACTION+PIECE
                Alias<Jeton,Piece> alias;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-2));
                    alias = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " inconnu dans sa réutilisation ([PIECE(Token)]+CONSEQUENCEACTION+PIECE)");
                }
                if (regleString.get(indRegleSyntaxe - 1).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new Interpreteur_Alias_Sujet<Piece>(alias),
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-ConsequenceAction-Piece inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "151720R21" , "151820R21"-> {
                //Alias sur Piece : PIECE(Token)+CONSEQUENCEACTION+[PIECE]
                Alias<Jeton,Piece> alias;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE(Token)+CONSEQUENCEACTION+[PIECE])");
                }
                if (regleString.get(indRegleSyntaxe - 1).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                            new Interpreteur_Alias_Cible<Piece>(alias),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "15R1720R21" , "15R1820R21"-> {
                //Alias sur Piece et Piece : [PIECE(Token)]+CONSEQUENCEACTION+[PIECE]
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,Piece> alias2;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-2));
                    alias1 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " inconnu dans sa réutilisation ([PIECE(Token)]+CONSEQUENCEACTION+PIECE)");
                }

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias2 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE(Token)+CONSEQUENCEACTION+[PIECE])");
                }

                if (regleString.get(indRegleSyntaxe - 1).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new Interpreteur_Alias_Sujet<Piece>(alias1),
                            new Interpreteur_Alias_Cible<Piece>(alias2),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-ConsequenceAction-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }

            case "15R17182021" -> {
                //Alias sur Piece : [PIECE]+JOUEUR+CONSEQUENCEACTION+PIECE
                Alias<Jeton,Piece> alias;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+CONSEQUENCEACTION+PIECE)");
                }

                if (regleString.get(indRegleSyntaxe - 1).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias), regleString.get(indRegleSyntaxe-2)),
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-Joueur-ConsequenceAction-Piece inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "1517R182021" -> {
                //Alias sur Joueur : PIECE+[JOUEUR]+CONSEQUENCEACTION+PIECE
                Alias<Jeton,Joueur> alias;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-2));
                    alias = (Alias<Jeton,Joueur>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " inconnu dans sa réutilisation (PIECE+[JOUEUR]+CONSEQUENCEACTION+PIECE)");
                }

                if (regleString.get(indRegleSyntaxe - 1).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias), regleString.get(indRegleSyntaxe-3)),
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-JoueurAlias-ConsequenceAction-Piece inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "15171820R21" -> {
                //Alias sur Piece : PIECE+JOUEUR+CONSEQUENCEACTION+[PIECE]
                Alias<Jeton,Piece> alias;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE+JOUEUR+CONSEQUENCEACTION+[PIECE])");
                }

                if (regleString.get(indRegleSyntaxe - 1).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3), regleString.get(indRegleSyntaxe-2)),
                            new Interpreteur_Alias_Cible<Piece>(alias),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-ConsequenceAction-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "1517R1820R21" -> {
                //Alias sur Joueur et Piece : PIECE+[JOUEUR]+CONSEQUENCEACTION+[PIECE]
                Alias<Jeton,Joueur> alias1;
                Alias<Jeton,Piece> alias2;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-2));
                    alias1 = (Alias<Jeton,Joueur>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " inconnu dans sa réutilisation (PIECE+[JOUEUR]+CONSEQUENCEACTION+PIECE)");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias2 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE+JOUEUR+CONSEQUENCEACTION+[PIECE])");
                }

                if (regleString.get(indRegleSyntaxe - 1).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias1),regleString.get(indRegleSyntaxe-3)),
                            new Interpreteur_Alias_Cible<Piece>(alias2),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-JoueurAlias-ConsequenceAction-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "15R171820R21" -> {
                //Alias sur Piece et Piece : [PIECE]+JOUEUR+CONSEQUENCEACTION+[PIECE]
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,Piece> alias2;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias1 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+CONSEQUENCEACTION+PIECE)");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias2 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE+JOUEUR+CONSEQUENCEACTION+[PIECE])");
                }

                if (regleString.get(indRegleSyntaxe - 1).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias1),regleString.get(indRegleSyntaxe-2)),
                            new Interpreteur_Alias_Cible<Piece>(alias2),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-Joueur-ConsequenceAction-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "15R17R182021" -> {
                //Alias sur Piece et Joueur : [PIECE]+[JOUEUR]+CONSEQUENCEACTION+PIECE
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,Joueur> alias2;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias1 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+CONSEQUENCEACTION+PIECE)");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-2));
                    alias2 = (Alias<Jeton,Joueur>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " inconnu dans sa réutilisation (PIECE+[JOUEUR]+CONSEQUENCEACTION+PIECE)");
                }

                if (regleString.get(indRegleSyntaxe - 1).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1),new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-JoueurAlias-ConsequenceAction-Piece inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "15R17R1820R21" -> {
                //Alias sur Piece et Joueur et Piece : [PIECE]+[JOUEUR]+CONSEQUENCEACTION+[PIECE]
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,Joueur> alias2;
                Alias<Jeton,Piece> alias3;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias1 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+CONSEQUENCEACTION+PIECE)");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-2));
                    alias2 = (Alias<Jeton,Joueur>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " inconnu dans sa réutilisation (PIECE+[JOUEUR]+CONSEQUENCEACTION+PIECE)");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias3 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE+JOUEUR+CONSEQUENCEACTION+[PIECE])");
                }

                if (regleString.get(indRegleSyntaxe - 1).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1),new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                            new Interpreteur_Alias_Cible<Piece>(alias3),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-JoueurAlias-ConsequenceAction-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }

            case "15R16202321" -> {
                //Alias sur Joueur : [JOUEUR]+CONSEQUENCEACTION+CASE+PIECE
                Alias<Jeton,Joueur> alias;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias = (Alias<Jeton,Joueur>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation ([JOUEUR]+CONSEQUENCEACTION+CASE+PIECE)");
                }

                if (regleString.get(indRegleSyntaxe - 2).equals("placer")) {
                    conseq = new ConsequenceAction<Piece, GroupCases>(
                            new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias),regleString.get(indRegleSyntaxe)),
                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe - 1)),
                            Fonctions_Comportements.placer);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc JoueurAlias-ConsequenceAction-Case-Piece inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "151620R2321" -> {
                //Alias sur Case : JOUEUR+CONSEQUENCEACTION+[CASE]+PIECE
                Alias<Jeton,GroupCases> alias;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-1));
                    alias = (Alias<Jeton,GroupCases>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " connu mais référencant le mauvais type de donnée : (attendu CASE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " inconnu dans sa réutilisation (JOUEUR+CONSEQUENCEACTION+[CASE]+PIECE)");
                }

                if (regleString.get(indRegleSyntaxe - 2).equals("placer")) {
                    conseq = new ConsequenceAction<Piece, GroupCases>(
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe),regleString.get(indRegleSyntaxe-3)),
                            new Interpreteur_Alias_Cible<GroupCases>(alias),
                            Fonctions_Comportements.placer);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Joueur-ConsequenceAction-CaseAlias-Piece inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "15162023R21" -> {
                //Alias sur Piece : JOUEUR+CONSEQUENCEACTION+CASE+[PIECE]
                Alias<Jeton,Piece> alias;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (JOUEUR+CONSEQUENCEACTION+CASE+[PIECE])");
                }

                if (regleString.get(indRegleSyntaxe - 2).equals("placer")) {
                    conseq = new ConsequenceAction<Piece, GroupCases>(
                            new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias),regleString.get(indRegleSyntaxe-3)),
                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe - 1)),
                            Fonctions_Comportements.placer);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Joueur-ConsequenceAction-Case-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "151620R23R21" -> {
                //Alias sur Case et Piece : JOUEUR+CONSEQUENCEACTION+[CASE]+[PIECE]
                Alias<Jeton,GroupCases> alias1;
                Alias<Jeton,?> testAlias = null;
                Alias<Jeton,Piece> alias2;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-1));
                    alias1 = (Alias<Jeton,GroupCases>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " connu mais référencant le mauvais type de donnée : (attendu CASE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " inconnu dans sa réutilisation (JOUEUR+CONSEQUENCEACTION+[CASE]+PIECE)");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias2 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (JOUEUR+CONSEQUENCEACTION+CASE+[PIECE])");
                }

                if (regleString.get(indRegleSyntaxe - 2).equals("placer")) {
                    conseq = new ConsequenceAction<Piece, GroupCases>(
                            new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias2),regleString.get(indRegleSyntaxe-3)),
                            new Interpreteur_Alias_Cible<GroupCases>(alias1),
                            Fonctions_Comportements.placer);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Joueur-ConsequenceAction-CaseAlias-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "15R162023R21" -> {
                //Alias sur Joueur et Piece : [JOUEUR]+CONSEQUENCEACTION+CASE+[PIECE]
                Alias<Jeton,Joueur> alias1;
                Alias<Jeton,?> testAlias = null;
                Alias<Jeton,Piece> alias2;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias1 = (Alias<Jeton,Joueur>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation ([JOUEUR]+CONSEQUENCEACTION+CASE+PIECE)");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias2 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (JOUEUR+CONSEQUENCEACTION+CASE+[PIECE])");
                }

                if (regleString.get(indRegleSyntaxe - 2).equals("placer")) {
                    conseq = new ConsequenceAction<Piece, GroupCases>(
                            new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias2),new Interpreteur_Alias_Sujet<Joueur>(alias1)),
                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe-1)),
                            Fonctions_Comportements.placer);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc JoueurAlias-ConsequenceAction-Case-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "15R1620R2321" -> {
                //Alias sur Joueur et Case : [JOUEUR]+CONSEQUENCEACTION+[CASE]+PIECE
                Alias<Jeton,Joueur> alias1;
                Alias<Jeton,?> testAlias = null;
                Alias<Jeton,GroupCases> alias2;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias1 = (Alias<Jeton,Joueur>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation ([JOUEUR]+CONSEQUENCEACTION+CASE+PIECE)");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-1));
                    alias2 = (Alias<Jeton,GroupCases>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " connu mais référencant le mauvais type de donnée : (attendu CASE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " inconnu dans sa réutilisation (JOUEUR+CONSEQUENCEACTION+[CASE]+PIECE)");
                }

                if (regleString.get(indRegleSyntaxe - 2).equals("placer")) {
                    conseq = new ConsequenceAction<Piece, GroupCases>(
                            new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias1),regleString.get(indRegleSyntaxe)),
                            new Interpreteur_Alias_Cible<GroupCases>(alias2),
                            Fonctions_Comportements.placer);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc JoueurAlias-ConsequenceAction-CaseAlias-Piece inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "15R1620R23R21" -> {
                //Alias sur Joueur et Case et Piece : [JOUEUR]+CONSEQUENCEACTION+[CASE]+[PIECE]
                Alias<Jeton,Joueur> alias1;
                Alias<Jeton,?> testAlias = null;
                Alias<Jeton,GroupCases> alias2;
                Alias<Jeton,Piece> alias3;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias1 = (Alias<Jeton,Joueur>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation ([JOUEUR]+CONSEQUENCEACTION+CASE+PIECE)");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-1));
                    alias2 = (Alias<Jeton,GroupCases>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " connu mais référencant le mauvais type de donnée : (attendu CASE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1) + " inconnu dans sa réutilisation (JOUEUR+CONSEQUENCEACTION+[CASE]+PIECE)");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias3 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (JOUEUR+CONSEQUENCEACTION+CASE+[PIECE])");
                }

                if (regleString.get(indRegleSyntaxe - 2).equals("placer")) {
                    conseq = new ConsequenceAction<Piece, GroupCases>(
                            new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias3),new Interpreteur_Alias_Sujet<Joueur>(alias1)),
                            new Interpreteur_Alias_Cible<GroupCases>(alias2),
                            Fonctions_Comportements.placer);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc JoueurAlias-ConsequenceAction-CaseAlias-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            default -> {
                throw new MauvaiseSemantiqueRegleException("Bloc Sujet-ConsequenceAction-Piece ou Sujet-ConsequenceAction-Case-Piece inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
            }
        }
        return conseq;
    }

    public Consequence case322(String parcours, int indRegleSyntaxe, List<Jeton> regleSyntaxe, List<String> regleString) throws MauvaiseDefinitionRegleException{
        Consequence conseq = null;
        switch (parcours) {
            //en passant par 20 (piecetoken)
            case "15162022" -> {
                // Joueur+ConsequenceAction+PieceToken
                throw new MauvaiseSemantiqueRegleException("Bloc Joueur-ConsequenceAction-PieceToken inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
            }
            case "15172022" -> {
                // Piece+ConsequenceAction+PieceToken
                if (regleString.get(indRegleSyntaxe - 1).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-PieceToken inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "1517182022" -> {
                // Joueur+Piece+ConsequenceAction+PieceToken
                if (regleString.get(indRegleSyntaxe - 1).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3), regleString.get(indRegleSyntaxe-2)),
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-ConsequenceAction-PieceToken inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "15182022" -> {
                // PieceToken+ConsequenceAction+PieceToken
                if (regleString.get(indRegleSyntaxe - 1).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc PieceToken-ConsequenceAction-PieceToken inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }

            //en passant par 21 (piece + joueur)
            //en passant par 20 (20-21)(piece)
            case "1516202122" -> {
                // Joueur+ConsequenceAction+Piece+Joueur
                throw new MauvaiseSemantiqueRegleException("Bloc Joueur-ConsequenceAction-Piece-Joueur inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
            }
            case "1517202122" -> {
                // Piece+ConsequenceAction+Piece+Joueur
                if (regleString.get(indRegleSyntaxe - 2).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3)),
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-1), regleString.get(indRegleSyntaxe)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-Piece-Joueur inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "151718202122" -> {
                // Piece+Joueur+ConsequenceAction+Piece+Joueur
                if (regleString.get(indRegleSyntaxe - 2).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-4), regleString.get(indRegleSyntaxe-3)),
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-1), regleString.get(indRegleSyntaxe)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-ConsequenceAction-Piece-Joueur inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "1518202122" -> {
                // PieceToken+ConsequenceAction+Piece+Joueur
                if (regleString.get(indRegleSyntaxe - 2).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3)),
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-1), regleString.get(indRegleSyntaxe)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piecetoken-ConsequenceAction-Piece-Joueur inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }

            //en passant par 20-23 (20-23-21)(case + pièce + joueur)
            case "151620232122" -> {
                // Joueur+ConsequenceAction+Case+Piece+Joueur
                throw new MauvaiseSemantiqueRegleException("Bloc Joueur-ConsequenceAction-Case-Piece-Joueur inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
            }
            case "151720232122" -> {
                // Piece+ConsequenceAction+Case+Piece+Joueur
                throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-Case-Piece-Joueur inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
            }
            case "15171820232122" -> {
                // Piece+Joueur+ConsequenceAction+Case+Piece+Joueur
                throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-ConsequenceAction-Case-Piece-Joueur inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
            }
            case "151820232122" -> {
                // JoueurToken+ConsequenceAction+Case+Piece+Joueur
                throw new MauvaiseSemantiqueRegleException("Bloc PieceToken-ConsequenceAction-Case-Piece-Joueur inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
            }

            //en passant par 23 (20-23)(case + piecetoken)
            case "1516202322" -> {
                // Joueur+ConsequenceAction+Case+PieceToken
                throw new MauvaiseSemantiqueRegleException("Bloc Joueur-ConsequenceAction-Case-PieceToken inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
            }
            case "1517202322" -> {
                // Piece+ConsequenceAction+Case+PieceToken
                throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-Case-PieceToken inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
            }
            case "151718202322" -> {
                // Piece+Joueur+ConsequenceAction+Case+PieceToken
                throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-ConsequenceAction-Case-PieceToken inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
            }
            case "1518202322" -> {
                // PieceToken+ConsequenceAction+Case+PieceToken
                throw new MauvaiseSemantiqueRegleException("Bloc PieceToken-ConsequenceAction-Case-PieceToken inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
            }


            /*==== Reutilisation Alias ====*/
            /*22*/
            //22
            case "15R1620"+"22" -> {
                // joueur alias + conseq + piecetoken
                throw new MauvaiseSemantiqueRegleException("Bloc Joueur-ConsequenceAction-PieceToken inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
            }
            case "15R1720"+"22", "15R1820"+"22" -> {
                // piece/pt alias + conseq + piecetoken
                Alias<Jeton,Piece> alias;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-2));
                    alias = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  "
                        + regleString.get(indRegleSyntaxe-2) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  "
                        + regleString.get(indRegleSyntaxe-2) + " inconnu dans sa réutilisation piece/pt alias + conseq + piecetoken");
                }
                if (regleString.get(indRegleSyntaxe - 1).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new Interpreteur_Alias_Sujet<Piece>(alias),
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "15R171820"+"22" -> {
                // piece alias + joueur + conseq + piecetoken
                Alias<Jeton,Piece> alias;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  "
                        + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  "
                        + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation piece alias + joueur + conseq + piecetoken");
                }
                if (regleString.get(indRegleSyntaxe - 1).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias), regleString.get(indRegleSyntaxe-2)),
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "1517R1820"+"22" -> {
                // piece + joueur alias + conseq + piecetoken
                Alias<Jeton,Joueur> alias;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-2));
                    alias = (Alias<Jeton,Joueur>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  "
                        + regleString.get(indRegleSyntaxe-2) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  "
                        + regleString.get(indRegleSyntaxe-2) + " inconnu dans sa réutilisation piece + joueur alias + conseq + piecetoken");
                }
                if (regleString.get(indRegleSyntaxe - 1).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias), regleString.get(indRegleSyntaxe-3)),
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "15R17R1820"+"22" -> {
                // piece alias + joueur alias + conseq + piecetoken
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,Joueur> alias2;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias1 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  "
                        + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  "
                        + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation piece alias + joueur alias + conseq + piecetoken"); }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-2));
                    alias2 = (Alias<Jeton,Joueur>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  "
                        + regleString.get(indRegleSyntaxe-2) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  "
                        + regleString.get(indRegleSyntaxe-2) + " inconnu dans sa réutilisation piece alias + joueur alias + conseq + piecetoken"); }

                if (regleString.get(indRegleSyntaxe - 1).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1), new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }

            //R22
            case "151620"+"R22" -> {
                // joueur + conseq + piecetoken alias
                throw new MauvaiseSemantiqueRegleException("Bloc Joueur-ConsequenceAction-PieceToken inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
            }
            case "151720"+"R22", "151820"+"R22" -> {
                // piece/PT + conseq + piecetoken alias
                Alias<Jeton,Piece> alias;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  "
                        + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  "
                        + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation: piece/PT + conseq + piecetoken alias");
                }
                if (regleString.get(indRegleSyntaxe - 1).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                            new Interpreteur_Alias_Cible<Piece>(alias),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "15171820"+"R22" -> {
                // piece + joueur + conseq + piecetoken alias
                Alias<Jeton,Piece> alias;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  "
                        + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  "
                        + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation: piece + joueur + conseq + piecetoken alias");
                }
                if (regleString.get(indRegleSyntaxe - 1).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3), regleString.get(indRegleSyntaxe-2)),
                            new Interpreteur_Alias_Cible<Piece>(alias),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }


            case "15R1620"+"R22" -> {
                // joueur alias + conseq + piecetoken alias
                throw new MauvaiseSemantiqueRegleException("Bloc Joueur-ConsequenceAction-PieceToken inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
            }
            case "15R1720"+"R22", "15R1820"+"R22" -> {
                // piece alias + conseq + piecetoken alias
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,Piece> alias2;
                Alias<Jeton,?> testAlias = null;
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-2));
                    alias1 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " inconnu dans sa réutilisation: piece alias + conseq + piecetoken alias");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias2 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation: piece alias + conseq + piecetoken alias");
                }
                if (regleString.get(indRegleSyntaxe - 1).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new Interpreteur_Alias_Sujet<Piece>(alias1),
                            new Interpreteur_Alias_Cible<Piece>(alias2),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "15R171820"+"R22" -> {
                // piece alias + joueur + conseq + piecetoken alias
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,Piece> alias2;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias1 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation: piece alias + joueur + conseq + piecetoken alias");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias2 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation: piece alias + joueur + conseq + piecetoken alias");
                }
                if (regleString.get(indRegleSyntaxe - 1).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias1), regleString.get(indRegleSyntaxe-2)),
                            new Interpreteur_Alias_Cible<Piece>(alias2),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "1517R1820"+"R22" -> {
                // piece + joueur alias + conseq + piecetoken alias
                Alias<Jeton,Joueur> alias1;
                Alias<Jeton,Piece> alias2;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-2));
                    alias1 = (Alias<Jeton,Joueur>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2)
                        + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2)
                        + " inconnu dans sa réutilisation: piece + joueur alias + conseq + piecetoken alias");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias2 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe)
                        + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe)
                        + " inconnu dans sa réutilisation: piece + joueur alias + conseq + piecetoken alias");
                }
                if (regleString.get(indRegleSyntaxe - 1).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias1), regleString.get(indRegleSyntaxe-3)),
                            new Interpreteur_Alias_Cible<Piece>(alias2),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "15R17R1820"+"R22" -> {
                // piece alias + joueur alias + conseq + piecetoken alias
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,Joueur> alias2;
                Alias<Jeton,Piece> alias3;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias1 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3)
                        + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3)
                        + " inconnu dans sa réutilisation: piece alias + joueur alias + conseq + piecetoken alias");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-2));
                    alias2 = (Alias<Jeton,Joueur>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2)
                        + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2)
                        + " inconnu dans sa réutilisation: piece alias + joueur alias + conseq + piecetoken alias");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias3 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe)
                        + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe)
                        + " inconnu dans sa réutilisation: piece alias + joueur alias + conseq + piecetoken alias");
                }
                if (regleString.get(indRegleSyntaxe - 1).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1), new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                            new Interpreteur_Alias_Cible<Piece>(alias3),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }

            /*2122*/
            //2122
            case "15R1620"+"2122" -> {
                throw new MauvaiseSemantiqueRegleException("Bloc Joueur-ConsequenceAction-Piece-Joueur inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
            }
            case "15R1720"+"2122", "15R1820"+"2122" -> {
                // piece/pt alias + conseq + piece + joueur
                Alias<Jeton,Piece> alias;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3)
                        + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3)
                        + " inconnu dans sa réutilisation: piece/pt alias + conseq + piece + joueur");
                }
                if (regleString.get(indRegleSyntaxe - 2).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new Interpreteur_Alias_Sujet<Piece>(alias),
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-1), regleString.get(indRegleSyntaxe)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "15R171820"+"2122" -> {
                // piece alias + joueur + conseq + piece + joueur
                Alias<Jeton,Piece> alias;
                Alias<Jeton,?> testAlias = null;
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-4));
                    alias = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-4)
                        + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-4)
                        + " inconnu dans sa réutilisation: piece alias + joueur + conseq + piece + joueur");
                }
                if (regleString.get(indRegleSyntaxe - 2).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias), regleString.get(indRegleSyntaxe-3)),
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-1), regleString.get(indRegleSyntaxe)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "1517R1820"+"2122" -> {
                // piece + joueur alias + conseq + piece + joueur
                Alias<Jeton,Joueur> alias;
                Alias<Jeton,?> testAlias = null;
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias = (Alias<Jeton,Joueur>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3)
                        + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3)
                        + " inconnu dans sa réutilisation: piece + joueur alias + conseq + piece + joueur");
                }
                if (regleString.get(indRegleSyntaxe - 2).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias), regleString.get(indRegleSyntaxe-4)),
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-1), regleString.get(indRegleSyntaxe)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "15R17R1820"+"2122" -> {
                // piece alias + joueur alias + conseq + piece + joueur
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,Joueur> alias2;
                Alias<Jeton,?> testAlias = null;
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-4));
                    alias1 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-4)
                        + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-4)
                        + " inconnu dans sa réutilisation: piece alias + joueur alias + conseq + piece + joueur");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias2 = (Alias<Jeton,Joueur>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3)
                        + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3)
                        + " inconnu dans sa réutilisation: piece alias + joueur alias + conseq + piece + joueur");
                }
                if (regleString.get(indRegleSyntaxe - 2).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1),new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-1), regleString.get(indRegleSyntaxe)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            //R2122
            case "151620"+"R2122" -> {
                throw new MauvaiseSemantiqueRegleException("Bloc Joueur-ConsequenceAction-Piece-Joueur inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
            }
            case "151720"+"R2122", "151820"+"R2122"  -> {
                // piece/pt + conseq + piece alias + joueur
                Alias<Jeton,Piece> alias;
                Alias<Jeton,?> testAlias = null;
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-1));
                    alias = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1)
                        + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1)
                        + " inconnu dans sa réutilisation: piece/pt + conseq + piece alias + joueur");
                }
                if (regleString.get(indRegleSyntaxe - 2).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3)),
                            new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias), regleString.get(indRegleSyntaxe)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "15171820"+"R2122" -> {
                // piece + joueur + conseq + piece alias + joueur
                Alias<Jeton,Piece> alias;
                Alias<Jeton,?> testAlias = null;
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-1));
                    alias = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1)
                        + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1)
                        + " inconnu dans sa réutilisation: piece + joueur + conseq + piece alias + joueur");
                }
                if (regleString.get(indRegleSyntaxe - 2).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-4), regleString.get(indRegleSyntaxe-3)),
                            new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias), regleString.get(indRegleSyntaxe)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }

            case "15R1620"+"R2122" -> {
                throw new MauvaiseSemantiqueRegleException("Bloc Joueur-ConsequenceAction-Piece-Joueur inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
            }
            case "15R1720"+"R2122", "15R1820"+"R2122" -> {
                // piece/pt alias + conseq + piece alias + joueur
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,Piece> alias2;
                Alias<Jeton,?> testAlias = null;
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias1 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3)
                        + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3)
                        + " inconnu dans sa réutilisation: piece/pt alias + conseq + piece alias + joueur");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-1));
                    alias2 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1)
                        + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1)
                        + " inconnu dans sa réutilisation: piece/pt alias + conseq + piece alias + joueur");
                }
                if (regleString.get(indRegleSyntaxe - 2).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new Interpreteur_Alias_Sujet<Piece>(alias1),
                            new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias2), regleString.get(indRegleSyntaxe)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "15R171820"+"R2122" -> {
                // piece alias + joueur + conseq + piece alias + joueur
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,Piece> alias2;
                Alias<Jeton,?> testAlias = null;
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-4));
                    alias1 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-4)
                        + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-4)
                        + " inconnu dans sa réutilisation: piece alias + joueur + conseq + piece alias + joueur");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-1));
                    alias2 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1)
                        + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1)
                        + " inconnu dans sa réutilisation: piece alias + joueur + conseq + piece alias + joueur");
                }
                if (regleString.get(indRegleSyntaxe - 2).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias1), regleString.get(indRegleSyntaxe-3)),
                            new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias2), regleString.get(indRegleSyntaxe)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "1517R1820"+"R2122" -> {
                // piece + joueur alias + conseq + piece alias + joueur
                Alias<Jeton,Joueur> alias1;
                Alias<Jeton,Piece> alias2;
                Alias<Jeton,?> testAlias = null;
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias1 = (Alias<Jeton,Joueur>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3)
                        + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3)
                        + " inconnu dans sa réutilisation: piece + joueur alias + conseq + piece alias + joueur");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-1));
                    alias2 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1)
                        + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1)
                        + " inconnu dans sa réutilisation: piece + joueur alias + conseq + piece alias + joueur");
                }
                if (regleString.get(indRegleSyntaxe - 2).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias1), regleString.get(indRegleSyntaxe-4)),
                            new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias2), regleString.get(indRegleSyntaxe)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "15R17R1820"+"R2122" -> {
                // piece alias + joueur alias + conseq + piece alias + joueur
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,Joueur> alias2;
                Alias<Jeton,Piece> alias3;
                Alias<Jeton,?> testAlias = null;
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-4));
                    alias1 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-4)
                        + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-4)
                        + " inconnu dans sa réutilisation: piece alias + joueur alias + conseq + piece alias + joueur");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias2 = (Alias<Jeton,Joueur>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3)
                        + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3)
                        + " inconnu dans sa réutilisation: piece alias + joueur alias + conseq + piece alias + joueur");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-1));
                    alias3 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1)
                        + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1)
                        + " inconnu dans sa réutilisation: piece alias + joueur alias + conseq + piece alias + joueur");
                }
                if (regleString.get(indRegleSyntaxe - 2).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1), new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                            new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias3), regleString.get(indRegleSyntaxe)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            //21R22
            case "151620"+"21R22" -> {
                throw new MauvaiseSemantiqueRegleException("Bloc Joueur-ConsequenceAction-Piece-Joueur inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
            }
            case "151720"+"21R22", "151820"+"21R22" -> {
                // piece/pt + conseq + piece + joueur alias
                Alias<Jeton,Joueur> alias;
                Alias<Jeton,?> testAlias = null;
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias = (Alias<Jeton,Joueur>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe)
                        + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe)
                        + " inconnu dans sa réutilisation: piece/pt + conseq + piece + joueur alias");
                }
                if (regleString.get(indRegleSyntaxe - 2).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3)),
                            new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias), regleString.get(indRegleSyntaxe-1)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "15171820"+"21R22" -> {
                // piece + joueur + conseq + piece + joueur alias
                Alias<Jeton,Joueur> alias;
                Alias<Jeton,?> testAlias = null;
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias = (Alias<Jeton,Joueur>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe)
                        + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe)
                        + " inconnu dans sa réutilisation: piece + joueur + conseq + piece + joueur alias");
                }
                if (regleString.get(indRegleSyntaxe - 2).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-4), regleString.get(indRegleSyntaxe-3)),
                            new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias), regleString.get(indRegleSyntaxe-1)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }

            case "15R1620"+"21R22" -> {
                throw new MauvaiseSemantiqueRegleException("Bloc Joueur-ConsequenceAction-Piece-Joueur inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
            }
            case "15R1720"+"21R22", "15R1820"+"21R22" -> {
                // piece/pt alias + conseq + piece + joueur alias
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,Joueur> alias2;
                Alias<Jeton,?> testAlias = null;
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias1 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3)
                        + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3)
                        + " inconnu dans sa réutilisation: piece/pt alias + conseq + piece + joueur alias");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias2 = (Alias<Jeton,Joueur>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe)
                        + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe)
                        + " inconnu dans sa réutilisation: piece/pt alias + conseq + piece + joueur alias");
                }
                if (regleString.get(indRegleSyntaxe - 2).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new Interpreteur_Alias_Sujet<Piece>(alias1),
                            new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias2), regleString.get(indRegleSyntaxe-1)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "15R171820"+"21R22" -> {
                // piece alias + joueur + conseq + piece + joueur alias
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,Joueur> alias2;
                Alias<Jeton,?> testAlias = null;
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-4));
                    alias1 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-4)
                        + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-4)
                        + " inconnu dans sa réutilisation: piece alias + joueur + conseq + piece + joueur alias");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias2 = (Alias<Jeton,Joueur>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe)
                        + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe)
                        + " inconnu dans sa réutilisation: piece alias + joueur + conseq + piece + joueur alias");
                }
                if (regleString.get(indRegleSyntaxe - 2).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias1), regleString.get(indRegleSyntaxe-3)),
                            new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias2), regleString.get(indRegleSyntaxe-1)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "1517R1820"+"21R22" -> {
                // piece + joueur alias + conseq + piece + joueur alias
                Alias<Jeton,Joueur> alias1;
                Alias<Jeton,Joueur> alias2;
                Alias<Jeton,?> testAlias = null;
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias1 = (Alias<Jeton,Joueur>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3)
                        + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3)
                        + " inconnu dans sa réutilisation: piece + joueur alias + conseq + piece + joueur alias");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias2 = (Alias<Jeton,Joueur>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe)
                        + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe)
                        + " inconnu dans sa réutilisation: piece + joueur alias + conseq + piece + joueur alias");
                }
                if (regleString.get(indRegleSyntaxe - 2).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias1), regleString.get(indRegleSyntaxe-4)),
                            new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias2), regleString.get(indRegleSyntaxe-1)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "15R17R1820"+"21R22" -> {
                // piece alias + joueur alias + conseq + piece + joueur alias
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,Joueur> alias2;
                Alias<Jeton,Joueur> alias3;
                Alias<Jeton,?> testAlias = null;
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-4));
                    alias1 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-4)
                        + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-4)
                        + " inconnu dans sa réutilisation: piece alias + joueur alias + conseq + piece + joueur alias");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias2 = (Alias<Jeton,Joueur>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3)
                        + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3)
                        + " inconnu dans sa réutilisation: piece alias + joueur alias + conseq + piece + joueur alias");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias3 = (Alias<Jeton,Joueur>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe)
                        + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe)
                        + " inconnu dans sa réutilisation: piece alias + joueur alias + conseq + piece + joueur alias");
                }
                if (regleString.get(indRegleSyntaxe - 2).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1), new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                            new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias3), regleString.get(indRegleSyntaxe-1)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            //R21R22
            case "151620"+"R21R22" -> {
                throw new MauvaiseSemantiqueRegleException("Bloc Joueur-ConsequenceAction-Piece-Joueur inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
            }
            case "151720"+"R21R22", "151820"+"R21R22" -> {
                // piece/pt + conseq + piece alias + joueur alias
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,Joueur> alias2;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-1));
                    alias1 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1)
                        + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1)
                        + " inconnu dans sa réutilisation: piece/pt + conseq + piece alias + joueur alias");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias2 = (Alias<Jeton,Joueur>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe)
                        + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe)
                        + " inconnu dans sa réutilisation: piece/pt + conseq + piece alias + joueur alias");
                }
                if (regleString.get(indRegleSyntaxe - 2).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3)),
                            new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1), new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "15171820"+"R21R22" -> {
                // piece + joueur + conseq + piece alias + joueur alias
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,Joueur> alias2;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-1));
                    alias1 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1)
                        + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1)
                        + " inconnu dans sa réutilisation: piece + joueur + conseq + piece alias + joueur alias");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias2 = (Alias<Jeton,Joueur>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe)
                        + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe)
                        + " inconnu dans sa réutilisation: piece + joueur + conseq + piece alias + joueur alias");
                }
                if (regleString.get(indRegleSyntaxe - 2).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-4), regleString.get(indRegleSyntaxe-3)),
                            new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1), new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }

            case "15R1620"+"R21R22" -> {
                throw new MauvaiseSemantiqueRegleException("Bloc Joueur-ConsequenceAction-Piece-Joueur inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
            }
            case "15R1720"+"R21R22", "15R1820"+"R21R22" -> {
                // piece/pt alias + conseq + piece alias + joueur alias
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,Piece> alias2;
                Alias<Jeton,Joueur> alias3;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias1 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3)
                        + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3)
                        + " inconnu dans sa réutilisation: piece/pt alias + conseq + piece alias + joueur alias");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-1));
                    alias2 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1)
                        + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1)
                        + " inconnu dans sa réutilisation: piece/pt alias + conseq + piece alias + joueur alias");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias3 = (Alias<Jeton,Joueur>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe)
                        + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe)
                        + " inconnu dans sa réutilisation: piece/pt alias + conseq + piece alias + joueur alias");
                }
                if (regleString.get(indRegleSyntaxe - 2).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new Interpreteur_Alias_Sujet<Piece>(alias1),
                            new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias2), new Interpreteur_Alias_Sujet<Joueur>(alias3)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "15R171820"+"R21R22" -> {
                // piece alias + joueur + conseq + piece alias + joueur alias
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,Piece> alias2;
                Alias<Jeton,Joueur> alias3;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-4));
                    alias1 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-4)
                        + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-4)
                        + " inconnu dans sa réutilisation: piece/pt alias + conseq + piece alias + joueur alias");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-1));
                    alias2 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1)
                        + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1)
                        + " inconnu dans sa réutilisation: piece/pt alias + conseq + piece alias + joueur alias");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias3 = (Alias<Jeton,Joueur>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe)
                        + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe)
                        + " inconnu dans sa réutilisation: piece/pt alias + conseq + piece alias + joueur alias");
                }
                if (regleString.get(indRegleSyntaxe - 2).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias1), regleString.get(indRegleSyntaxe-3)),
                            new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias2), new Interpreteur_Alias_Sujet<Joueur>(alias3)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "1517R1820"+"R21R22" -> {
                // piece + joueur alias + conseq + piece alias + joueur alias
                Alias<Jeton,Joueur> alias1;
                Alias<Jeton,Piece> alias2;
                Alias<Jeton,Joueur> alias3;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias1 = (Alias<Jeton,Joueur>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3)
                        + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3)
                        + " inconnu dans sa réutilisation: piece + joueur alias + conseq + piece alias + joueur alias");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-1));
                    alias2 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1)
                        + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1)
                        + " inconnu dans sa réutilisation: piece + joueur alias + conseq + piece alias + joueur alias");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias3 = (Alias<Jeton,Joueur>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe)
                        + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe)
                        + " inconnu dans sa réutilisation: piece + joueur alias + conseq + piece alias + joueur alias");
                }
                if (regleString.get(indRegleSyntaxe - 2).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias1), regleString.get(indRegleSyntaxe-4)),
                            new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias2), new Interpreteur_Alias_Sujet<Joueur>(alias3)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            case "15R17R1820"+"R21R22" -> {
                // piece alias + joueur alias + conseq + piece alias + joueur alias
                Alias<Jeton,Piece> alias1;
                Alias<Jeton,Joueur> alias2;
                Alias<Jeton,Piece> alias3;
                Alias<Jeton,Joueur> alias4;
                Alias<Jeton,?> testAlias = null;

                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias1 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3)
                        + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3)
                        + " inconnu dans sa réutilisation: piece alias + joueur alias + conseq + piece alias + joueur alias");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                    alias2 = (Alias<Jeton,Joueur>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3)
                        + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3)
                        + " inconnu dans sa réutilisation: piece alias + joueur alias + conseq + piece alias + joueur alias");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe-1));
                    alias3 = (Alias<Jeton,Piece>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1)
                        + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-1)
                        + " inconnu dans sa réutilisation: piece alias + joueur alias + conseq + piece alias + joueur alias");
                }
                try{
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias4 = (Alias<Jeton,Joueur>) testAlias;
                }catch (ClassCastException e){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe)
                        + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                }catch (MauvaiseDefinitionRegleException re){ throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe)
                        + " inconnu dans sa réutilisation: piece alias + joueur alias + conseq + piece alias + joueur alias");
                }
                if (regleString.get(indRegleSyntaxe - 2).equals("prendre")) {
                    conseq = new ConsequenceAction<Piece, Piece>(
                            new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1), new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                            new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias3), new Interpreteur_Alias_Sujet<Joueur>(alias4)),
                            Fonctions_Comportements.prendre_piece);
                } else {
                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }

            /*202322 et 20232122 exceptions*/
            default -> {
                throw new MauvaiseSemantiqueRegleException("Bloc Sujet-ConsequenceAction-PieceToken OU Sujet-ConsequenceAction-Piece-Joueur OU Sujet-ConsequenceAction-Case-PieceToken OU Sujet-ConsequenceAction-Case-Piece-Joueur inconnu. parcours: " + parcours + " [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
            }
        }
        return conseq;
    }

    public Consequence case323(String parcours, int indRegleSyntaxe, List<Jeton> regleSyntaxe, List<String> regleString) throws MauvaiseDefinitionRegleException {
            Consequence conseq;
            switch (parcours) {
                case "15162023" -> {
                    // Joueur+ConsequenceAction+Case
                    throw new MauvaiseSemantiqueRegleException("Bloc Joueur-ConsequenceAction-Case inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
                case "15172023" -> {/*prendre + promouvoir*/
                    // Piece+ConsequenceAction+Case
                    switch (regleString.get(indRegleSyntaxe - 1)) {
                        case "prendre" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                                    new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                    Fonctions_Comportements.prendre_par_case);
                        }
                        case "promouvoir" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                                    new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                    Fonctions_Comportements.promouvoir);
                        }
                        case "deplacer" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                                    new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                    Fonctions_Comportements.deplacer);
                        }
                        default -> {
                            throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-Case inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                        }
                    }
                }
                case "1517182023" -> {/*prendre + promouvoir*/
                    // Piece+Joueur+ConsequenceAction+Case
                    switch (regleString.get(indRegleSyntaxe - 1)) {
                        case "prendre" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3), regleString.get(indRegleSyntaxe-2)),
                                    new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                    Fonctions_Comportements.prendre_par_case);
                        }
                        case "promouvoir" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3), regleString.get(indRegleSyntaxe-2)),
                                    new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                    Fonctions_Comportements.promouvoir);
                        }
                        case "deplacer" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3), regleString.get(indRegleSyntaxe-2)),
                                    new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                    Fonctions_Comportements.deplacer);
                        }
                        default -> {
                            throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-ConsequenceAction-Case inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                        }
                    }
                }
                case "15182023" -> {/*prendre + promouvoir*/
                    // PieceToken+ConsequenceAction+Case
                    switch (regleString.get(indRegleSyntaxe - 1)) {
                        case "prendre" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                                    new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                    Fonctions_Comportements.prendre_par_case);
                        }
                        case "promouvoir" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                                    new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                    Fonctions_Comportements.promouvoir);
                        }
                        case "deplacer" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                                    new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                    Fonctions_Comportements.deplacer);
                        }
                        default -> {
                            throw new MauvaiseSemantiqueRegleException("Bloc PieceToken-ConsequenceAction-Case inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                        }
                    }
                }
                /*==== Reutilisation Alias ====*/
                case "15R172023" , "15R182023" ->{
                    //Alias sur Piece : [PIECE(Token)]+CONSEQUENCEACTION+CASE
                    Alias<Jeton,Piece> alias;
                    Alias<Jeton,?> testAlias = null;

                    try{
                        testAlias = recupererAlias(regleString.get(indRegleSyntaxe-2));
                        alias = (Alias<Jeton,Piece>) testAlias;
                    }catch (ClassCastException e){
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                    }catch (MauvaiseDefinitionRegleException re){
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " inconnu dans sa réutilisation ([PIECE(Token)]+CONSEQUENCEACTION+CASE)");
                    }

                    switch (regleString.get(indRegleSyntaxe - 1)) {
                        case "prendre" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new Interpreteur_Alias_Sujet<Piece>(alias),
                                    new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                    Fonctions_Comportements.prendre_par_case);
                        }
                        case "promouvoir" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new Interpreteur_Alias_Sujet<Piece>(alias),
                                    new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                    Fonctions_Comportements.promouvoir);
                        }
                        case "deplacer" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new Interpreteur_Alias_Sujet<Piece>(alias),
                                    new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                    Fonctions_Comportements.deplacer);
                        }
                        default -> {
                            throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-ConsequenceAction-Case inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                        }
                    }
                }
                case "151720R23" , "151820R23" -> {
                    //Alias sur Case : PIECE(Token)+CONSEQUENCEACTION+[CASE]
                    Alias<Jeton, GroupCases> alias;
                    Alias<Jeton, ?> testAlias = null;

                    try {
                        testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                        alias = (Alias<Jeton, GroupCases>) testAlias;
                    } catch (ClassCastException e) {
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu CASE | recu " + testAlias.getJetonAssocie() + ")");
                    } catch (MauvaiseDefinitionRegleException re) {
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE(Token)+CONSEQUENCEACTION+[CASE])");
                    }

                    switch (regleString.get(indRegleSyntaxe - 1)) {
                        case "prendre" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 2)),
                                    new Interpreteur_Alias_Cible<GroupCases>(alias),
                                    Fonctions_Comportements.prendre_par_case);
                        }
                        case "promouvoir" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 2)),
                                    new Interpreteur_Alias_Cible<GroupCases>(alias),
                                    Fonctions_Comportements.promouvoir);
                        }
                        case "deplacer" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 2)),
                                    new Interpreteur_Alias_Cible<GroupCases>(alias),
                                    Fonctions_Comportements.deplacer);
                        }
                        default -> {
                            throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-CaseAlias inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                        }
                    }
                }
                case "15R1720R23" , "15R1820R23" -> {
                    //Alias sur Piece et Case : [PIECE(Token)]+CONSEQUENCEACTION+[CASE]
                    Alias<Jeton, GroupCases> alias2;
                    Alias<Jeton, ?> testAlias = null;
                    Alias<Jeton, Piece> alias1;

                    try {
                        testAlias = recupererAlias(regleString.get(indRegleSyntaxe - 2));
                        alias1 = (Alias<Jeton, Piece>) testAlias;
                    } catch (ClassCastException e) {
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 2) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                    } catch (MauvaiseDefinitionRegleException re) {
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 2) + " inconnu dans sa réutilisation ([PIECE(Token)]+CONSEQUENCEACTION+CASE)");
                    }

                    try {
                        testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                        alias2 = (Alias<Jeton, GroupCases>) testAlias;
                    } catch (ClassCastException e) {
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu CASE | recu " + testAlias.getJetonAssocie() + ")");
                    } catch (MauvaiseDefinitionRegleException re) {
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE(Token)+CONSEQUENCEACTION+[CASE])");
                    }

                    switch (regleString.get(indRegleSyntaxe - 1)) {
                        case "prendre" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new Interpreteur_Alias_Sujet<Piece>(alias1),
                                    new Interpreteur_Alias_Cible<GroupCases>(alias2),
                                    Fonctions_Comportements.prendre_par_case);
                        }
                        case "promouvoir" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new Interpreteur_Alias_Sujet<Piece>(alias1),
                                    new Interpreteur_Alias_Cible<GroupCases>(alias2),
                                    Fonctions_Comportements.promouvoir);
                        }
                        case "deplacer" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new Interpreteur_Alias_Sujet<Piece>(alias1),
                                    new Interpreteur_Alias_Cible<GroupCases>(alias2),
                                    Fonctions_Comportements.deplacer);
                        }
                        default -> {
                            throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-ConsequenceAction-CaseAlias inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                        }
                    }
                }

                case "15R17182023" -> {
                    //Alias sur Piece : [PIECE]+JOUEUR+CONSEQUENCEACTION+CASE
                    Alias<Jeton,Piece> alias;
                    Alias<Jeton,?> testAlias = null;

                    try{
                        testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                        alias = (Alias<Jeton,Piece>) testAlias;
                    }catch (ClassCastException e){
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                    }catch (MauvaiseDefinitionRegleException re){
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+CONSEQUENCEACTION+CASE)");
                    }

                    switch (regleString.get(indRegleSyntaxe - 1)) {
                        case "prendre" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias),regleString.get(indRegleSyntaxe-2)),
                                    new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                    Fonctions_Comportements.prendre_par_case);
                        }
                        case "promouvoir" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias),regleString.get(indRegleSyntaxe-2)),
                                    new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                    Fonctions_Comportements.promouvoir);
                        }
                        case "deplacer" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias),regleString.get(indRegleSyntaxe-2)),
                                    new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                    Fonctions_Comportements.deplacer);
                        }
                        default -> {
                            throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-Joueur-ConsequenceAction-Case inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                        }
                    }
                }
                case "1517R182023" -> {
                    //Alias sur Joueur : PIECE+[JOUEUR]+CONSEQUENCEACTION+CASE
                    Alias<Jeton,Joueur> alias;
                    Alias<Jeton,?> testAlias = null;

                    try{
                        testAlias = recupererAlias(regleString.get(indRegleSyntaxe-2));
                        alias = (Alias<Jeton,Joueur>) testAlias;
                    }catch (ClassCastException e){
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                    }catch (MauvaiseDefinitionRegleException re){
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " inconnu dans sa réutilisation (PIECE+[JOUEUR]+CONSEQUENCEACTION+CASE)");
                    }

                    switch (regleString.get(indRegleSyntaxe - 1)) {
                        case "prendre" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias),regleString.get(indRegleSyntaxe-3)),
                                    new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                    Fonctions_Comportements.prendre_par_case);
                        }
                        case "promouvoir" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias),regleString.get(indRegleSyntaxe-3)),
                                    new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                    Fonctions_Comportements.promouvoir);
                        }
                        case "deplacer" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias),regleString.get(indRegleSyntaxe-3)),
                                    new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                    Fonctions_Comportements.deplacer);
                        }
                        default -> {
                            throw new MauvaiseSemantiqueRegleException("Bloc Piece-JoueurAlias-ConsequenceAction-Case inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                        }
                    }
                }
                case "15171820R23" -> {
                    //Alias sur Case : PIECE+JOUEUR+CONSEQUENCEACTION+[CASE]
                    Alias<Jeton,GroupCases> alias;
                    Alias<Jeton,?> testAlias = null;

                    try{
                        testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                        alias = (Alias<Jeton,GroupCases>) testAlias;
                    }catch (ClassCastException e){
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu CASE | recu " + testAlias.getJetonAssocie()+")");
                    }catch (MauvaiseDefinitionRegleException re){
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE+JOUEUR+CONSEQUENCEACTION+[CASE])");
                    }

                    switch (regleString.get(indRegleSyntaxe - 1)) {
                        case "prendre" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3),regleString.get(indRegleSyntaxe-2)),
                                    new Interpreteur_Alias_Cible<GroupCases>(alias),
                                    Fonctions_Comportements.prendre_par_case);
                        }
                        case "promouvoir" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3),regleString.get(indRegleSyntaxe-2)),
                                    new Interpreteur_Alias_Cible<GroupCases>(alias),
                                    Fonctions_Comportements.promouvoir);
                        }
                        case "deplacer" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3),regleString.get(indRegleSyntaxe-2)),
                                    new Interpreteur_Alias_Cible<GroupCases>(alias),
                                    Fonctions_Comportements.deplacer);
                        }
                        default -> {
                            throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-ConsequenceAction-CaseAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                        }
                    }
                }
                case "1517R1820R23" -> {
                    //Alias sur Joueur et Case : PIECE+[JOUEUR]+CONSEQUENCEACTION+[CASE]
                    Alias<Jeton,Joueur> alias1;
                    Alias<Jeton,GroupCases> alias2;
                    Alias<Jeton,?> testAlias = null;

                    try{
                        testAlias = recupererAlias(regleString.get(indRegleSyntaxe-2));
                        alias1 = (Alias<Jeton,Joueur>) testAlias;
                    }catch (ClassCastException e){
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                    }catch (MauvaiseDefinitionRegleException re){
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " inconnu dans sa réutilisation (PIECE+[JOUEUR]+CONSEQUENCEACTION+CASE)");
                    }

                    try{
                        testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                        alias2 = (Alias<Jeton,GroupCases>) testAlias;
                    }catch (ClassCastException e){
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu CASE | recu " + testAlias.getJetonAssocie()+")");
                    }catch (MauvaiseDefinitionRegleException re){
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE+JOUEUR+CONSEQUENCEACTION+[CASE])");
                    }

                    switch (regleString.get(indRegleSyntaxe - 1)) {
                        case "prendre" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias1),regleString.get(indRegleSyntaxe-3)),
                                    new Interpreteur_Alias_Cible<GroupCases>(alias2),
                                    Fonctions_Comportements.prendre_par_case);
                        }
                        case "promouvoir" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias1),regleString.get(indRegleSyntaxe-3)),
                                    new Interpreteur_Alias_Cible<GroupCases>(alias2),
                                    Fonctions_Comportements.promouvoir);
                        }
                        case "deplacer" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias1),regleString.get(indRegleSyntaxe-3)),
                                    new Interpreteur_Alias_Cible<GroupCases>(alias2),
                                    Fonctions_Comportements.deplacer);
                        }
                        default -> {
                            throw new MauvaiseSemantiqueRegleException("Bloc Piece-JoueurAlias-ConsequenceAction-CaseAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                        }
                    }
                }
                case "15R171820R23" -> {
                    //Alias sur Piece et Case : [PIECE]+JOUEUR+CONSEQUENCEACTION+[CASE]
                    Alias<Jeton,Piece> alias1;
                    Alias<Jeton,GroupCases> alias2;
                    Alias<Jeton,?> testAlias = null;

                    try{
                        testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                        alias1 = (Alias<Jeton,Piece>) testAlias;
                    }catch (ClassCastException e){
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                    }catch (MauvaiseDefinitionRegleException re){
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+CONSEQUENCEACTION+CASE)");
                    }

                    try{
                        testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                        alias2 = (Alias<Jeton,GroupCases>) testAlias;
                    }catch (ClassCastException e){
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu CASE | recu " + testAlias.getJetonAssocie()+")");
                    }catch (MauvaiseDefinitionRegleException re){
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE+JOUEUR+CONSEQUENCEACTION+[CASE])");
                    }

                    switch (regleString.get(indRegleSyntaxe - 1)) {
                        case "prendre" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias1),regleString.get(indRegleSyntaxe-2)),
                                    new Interpreteur_Alias_Cible<GroupCases>(alias2),
                                    Fonctions_Comportements.prendre_par_case);
                        }
                        case "promouvoir" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias1),regleString.get(indRegleSyntaxe-2)),
                                    new Interpreteur_Alias_Cible<GroupCases>(alias2),
                                    Fonctions_Comportements.promouvoir);
                        }
                        case "deplacer" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias1),regleString.get(indRegleSyntaxe-2)),
                                    new Interpreteur_Alias_Cible<GroupCases>(alias2),
                                    Fonctions_Comportements.deplacer);
                        }
                        default -> {
                            throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-Joueur-ConsequenceAction-CaseAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                        }
                    }
                }
                case "15R17R182023" -> {
                    //Alias sur Piece et Joueur : [PIECE]+[JOUEUR]+CONSEQUENCEACTION+CASE
                    Alias<Jeton,Piece> alias1;
                    Alias<Jeton,Joueur> alias2;
                    Alias<Jeton,?> testAlias = null;

                    try{
                        testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                        alias1 = (Alias<Jeton,Piece>) testAlias;
                    }catch (ClassCastException e){
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                    }catch (MauvaiseDefinitionRegleException re){
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+CONSEQUENCEACTION+CASE)");
                    }

                    try{
                        testAlias = recupererAlias(regleString.get(indRegleSyntaxe-2));
                        alias2 = (Alias<Jeton,Joueur>) testAlias;
                    }catch (ClassCastException e){
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                    }catch (MauvaiseDefinitionRegleException re){
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " inconnu dans sa réutilisation (PIECE+[JOUEUR]+CONSEQUENCEACTION+CASE)");
                    }

                    switch (regleString.get(indRegleSyntaxe - 1)) {
                        case "prendre" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1),new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                    new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                    Fonctions_Comportements.prendre_par_case);
                        }
                        case "promouvoir" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1),new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                    new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                    Fonctions_Comportements.promouvoir);
                        }
                        case "deplacer" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1),new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                    new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                    Fonctions_Comportements.deplacer);
                        }
                        default -> {
                            throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-JoueurAlias-ConsequenceAction-Case inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                        }
                    }
                }
                case "15R17R1820R23" -> {
                    //Alias sur Piece et Joueur et Case : [PIECE]+[JOUEUR]+CONSEQUENCEACTION+[CASE]
                    Alias<Jeton,Piece> alias1;
                    Alias<Jeton,Joueur> alias2;
                    Alias<Jeton,GroupCases> alias3;
                    Alias<Jeton,?> testAlias = null;

                    try{
                        testAlias = recupererAlias(regleString.get(indRegleSyntaxe-3));
                        alias1 = (Alias<Jeton,Piece>) testAlias;
                    }catch (ClassCastException e){
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                    }catch (MauvaiseDefinitionRegleException re){
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-3) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+CONSEQUENCEACTION+CASE)");
                    }

                    try{
                        testAlias = recupererAlias(regleString.get(indRegleSyntaxe-2));
                        alias2 = (Alias<Jeton,Joueur>) testAlias;
                    }catch (ClassCastException e){
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " connu mais référencant le mauvais type de donnée : (attendu JOUEUR | recu " + testAlias.getJetonAssocie()+")");
                    }catch (MauvaiseDefinitionRegleException re){
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe-2) + " inconnu dans sa réutilisation (PIECE+[JOUEUR]+CONSEQUENCEACTION+CASE)");
                    }

                    try{
                        testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                        alias3 = (Alias<Jeton,GroupCases>) testAlias;
                    }catch (ClassCastException e){
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu CASE | recu " + testAlias.getJetonAssocie()+")");
                    }catch (MauvaiseDefinitionRegleException re){
                        throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE+JOUEUR+CONSEQUENCEACTION+[CASE])");
                    }

                    switch (regleString.get(indRegleSyntaxe - 1)) {
                        case "prendre" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1),new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                    new Interpreteur_Alias_Cible<GroupCases>(alias3),
                                    Fonctions_Comportements.prendre_par_case);
                        }
                        case "promouvoir" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1),new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                    new Interpreteur_Alias_Cible<GroupCases>(alias3),
                                    Fonctions_Comportements.promouvoir);
                        }
                        case "deplacer" -> {
                            conseq = new ConsequenceAction<Piece, GroupCases>(
                                    new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1),new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                    new Interpreteur_Alias_Cible<GroupCases>(alias3),
                                    Fonctions_Comportements.deplacer);
                        }
                        default -> {
                            throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-JoueurAlias-ConsequenceAction-CaseAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                        }
                    }
                }
                default -> {
                    throw new MauvaiseSemantiqueRegleException("Bloc Sujet-ConsequenceAction-Case inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                }
            }
            return conseq;
        }

    public Condition case327_334(String parcours, int indRegleSyntaxe, List<Jeton> regleSyntaxe, List<String> regleString) throws MauvaiseDefinitionRegleException{
        Condition cond;
    switch (parcours) {
            case "02527" -> {
                //PIECE + ACTION + CASEPARAM
                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "estsur" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 2)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Sur);
                    }
                    case "prend" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 2)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Case);
                    }
                    case "sedeplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 2)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.se_deplace);
                    }
                    case "estplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 2)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_place);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Caseparam inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                    }
                }
            }
            case "023527" -> {
                //PIECE + JOUEUR + ACTION + CASEPARAM
                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "estsur" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 3), regleString.get(indRegleSyntaxe - 2)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Sur);
                    }
                    case "prend" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 3), regleString.get(indRegleSyntaxe - 2)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Case);
                    }
                    case "sedeplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 3), regleString.get(indRegleSyntaxe - 2)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.se_deplace);
                    }
                    case "estplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 3), regleString.get(indRegleSyntaxe - 2)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_place);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-Action-Caseparam inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                    }
                }
            }
            case "03527" -> {
                //PIECETOKEN + ACTION + CASEPARAM
                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "estsur" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 2)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Sur);
                    }
                    case "prend" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 2)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Case);
                    }
                    case "sedeplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 2)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.se_deplace);
                    }
                    case "estplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 2)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_place);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piecetoken-Action-Caseparam inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                    }
                }
            }

            case "0251127" -> {
                //PIECE + ACTION + CASE + CASE
                switch (regleString.get(indRegleSyntaxe - 2)) {
                    case "estsur" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe - 1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Sur);
                    }
                    case "prend" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe - 1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Case);
                    }
                    case "sedeplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe - 1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.se_deplace);
                    }
                    case "estplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe - 1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_place);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Case-Case inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                    }
                }
            }
            case "02351127" -> {
                //PIECE + JOUEUR + ACTION + CASE + CASE
                switch (regleString.get(indRegleSyntaxe - 2)) {
                    case "estsur" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 4), regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe - 1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Sur);
                    }
                    case "prend" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 4), regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe - 1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Case);
                    }
                    case "sedeplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 4), regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe - 1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.se_deplace);
                    }
                    case "estplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 4), regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe - 1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_place);

                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-Action-Case-Case inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                    }
                }
            }
            case "0351127" -> {
                //PIECETOKEN + ACTION + CASE + CASE
                switch (regleString.get(indRegleSyntaxe - 2)) {
                    case "estsur" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe - 1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Sur);
                    }
                    case "prend" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe - 1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Case);
                    }
                    case "sedeplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe - 1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.se_deplace);
                    }
                    case "estplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe - 1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_place);
                    }
                    default -> throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-Action-Case-Case inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                }
            }

            //Alias CASEPARAM - 27
            case "0R2527", "0R3527" -> {
                // Piece/PT alias + action + caseparam
                Alias<Jeton, Piece> alias;
                Alias<Jeton, ?> testAlias = null;
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe - 2));
                    alias = (Alias<Jeton, Piece>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 2) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 2) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }

                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "estsur" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new Interpreteur_Alias_Sujet<Piece>(alias),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Sur);
                    }
                    case "prend" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new Interpreteur_Alias_Sujet<Piece>(alias),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Case);
                    }
                    case "sedeplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new Interpreteur_Alias_Sujet<Piece>(alias),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.se_deplace);
                    }
                    case "estplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new Interpreteur_Alias_Sujet<Piece>(alias),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_place);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Caseparam inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                    }
                }
            }
            case "0R23527" -> {
                // Piece alias + Joueur + action + caseparam
                Alias<Jeton, Piece> alias;
                Alias<Jeton, ?> testAlias = null;
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe - 3));
                    alias = (Alias<Jeton, Piece>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 3) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }

                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "estsur" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias), regleString.get(indRegleSyntaxe - 2)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Sur);
                    }
                    case "prend" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias), regleString.get(indRegleSyntaxe - 2)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Case);
                    }
                    case "sedeplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias), regleString.get(indRegleSyntaxe - 2)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.se_deplace);
                    }
                    case "estplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias), regleString.get(indRegleSyntaxe - 2)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_place);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Caseparam inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                    }
                }
            }
            case "02R3527" -> {
                // Piece + Joueur alias action + caseparam
                Alias<Jeton, Joueur> alias;
                Alias<Jeton, ?> testAlias = null;
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe - 3));
                    alias = (Alias<Jeton, Joueur>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 3) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }

                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "estsur" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias), regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Sur);
                    }
                    case "prend" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias), regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Case);
                    }
                    case "sedeplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias), regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.se_deplace);
                    }
                    case "estplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias), regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_place);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Caseparam inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                    }
                }
            }
            case "0R2R3527" -> {
                // Piece alias + Joueur alias + action + caseparam
                Alias<Jeton, Piece> alias1;
                Alias<Jeton, Joueur> alias2;
                Alias<Jeton, ?> testAlias = null;
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe - 3));
                    alias1 = (Alias<Jeton, Piece>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 2) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 2) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe - 2));
                    alias2 = (Alias<Jeton, Joueur>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 3) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }

                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "estsur" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1), new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Sur);
                    }
                    case "prend" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1), new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Case);
                    }
                    case "sedeplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1), new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.se_deplace);
                    }
                    case "estplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1), new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_place);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Caseparam inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                    }
                }
            }

            //Alias CASEPARAM - R27
            case "025R27", "035R27" -> {
                // Piece/PT + action + caseparam alias
                Alias<Jeton, GroupCases> alias;
                Alias<Jeton, ?> testAlias = null;
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias = (Alias<Jeton, GroupCases>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }

                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "estsur" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 2)),
                                new Interpreteur_Alias_Cible<GroupCases>(alias),
                                Fonctions_Comportements.est_Sur);
                    }
                    case "prend" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 2)),
                                new Interpreteur_Alias_Cible<GroupCases>(alias),
                                Fonctions_Comportements.prend_Par_Case);
                    }
                    case "sedeplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 2)),
                                new Interpreteur_Alias_Cible<GroupCases>(alias),
                                Fonctions_Comportements.se_deplace);
                    }
                    case "estplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 2)),
                                new Interpreteur_Alias_Cible<GroupCases>(alias),
                                Fonctions_Comportements.est_place);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Caseparam inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                    }
                }
            }
            case "0235R27" -> {
                // Piece + Joueur + action + caseparam alias
                Alias<Jeton, GroupCases> alias;
                Alias<Jeton, ?> testAlias = null;
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias = (Alias<Jeton, GroupCases>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }

                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "estsur" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 3), regleString.get(indRegleSyntaxe - 2)),
                                new Interpreteur_Alias_Cible<GroupCases>(alias),
                                Fonctions_Comportements.est_Sur);
                    }
                    case "prend" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 3), regleString.get(indRegleSyntaxe - 2)),
                                new Interpreteur_Alias_Cible<GroupCases>(alias),
                                Fonctions_Comportements.prend_Par_Case);
                    }
                    case "sedeplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 3), regleString.get(indRegleSyntaxe - 2)),
                                new Interpreteur_Alias_Cible<GroupCases>(alias),
                                Fonctions_Comportements.se_deplace);
                    }
                    case "estplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 3), regleString.get(indRegleSyntaxe - 2)),
                                new Interpreteur_Alias_Cible<GroupCases>(alias),
                                Fonctions_Comportements.est_place);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Caseparam inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                    }
                }
            }
            case "0R25R27", "0R35R27" -> {
                // Piece alias + action + caseparam alias
                Alias<Jeton, Piece> alias1;
                Alias<Jeton, GroupCases> alias2;
                Alias<Jeton, ?> testAlias = null;
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe - 2));
                    alias1 = (Alias<Jeton, Piece>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 2) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 2) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias2 = (Alias<Jeton, GroupCases>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }

                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "estsur" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new Interpreteur_Alias_Sujet<Piece>(alias1),
                                new Interpreteur_Alias_Cible<GroupCases>(alias2),
                                Fonctions_Comportements.est_Sur);
                    }
                    case "prend" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new Interpreteur_Alias_Sujet<Piece>(alias1),
                                new Interpreteur_Alias_Cible<GroupCases>(alias2),
                                Fonctions_Comportements.prend_Par_Case);
                    }
                    case "sedeplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new Interpreteur_Alias_Sujet<Piece>(alias1),
                                new Interpreteur_Alias_Cible<GroupCases>(alias2),
                                Fonctions_Comportements.se_deplace);
                    }
                    case "estplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new Interpreteur_Alias_Sujet<Piece>(alias1),
                                new Interpreteur_Alias_Cible<GroupCases>(alias2),
                                Fonctions_Comportements.est_place);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Caseparam inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                    }
                }
            }
            case "0R235R27" -> {
                // Piece alias + Joueur + action + caseparam alias
                Alias<Jeton, Piece> alias1;
                Alias<Jeton, GroupCases> alias2;
                Alias<Jeton, ?> testAlias = null;
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe - 3));
                    alias1 = (Alias<Jeton, Piece>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 3) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias2 = (Alias<Jeton, GroupCases>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }

                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "estsur" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias1), regleString.get(indRegleSyntaxe - 3)),
                                new Interpreteur_Alias_Cible<GroupCases>(alias2),
                                Fonctions_Comportements.est_Sur);
                    }
                    case "prend" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias1), regleString.get(indRegleSyntaxe - 3)),
                                new Interpreteur_Alias_Cible<GroupCases>(alias2),
                                Fonctions_Comportements.prend_Par_Case);
                    }
                    case "sedeplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias1), regleString.get(indRegleSyntaxe - 3)),
                                new Interpreteur_Alias_Cible<GroupCases>(alias2),
                                Fonctions_Comportements.se_deplace);
                    }
                    case "estplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias1), regleString.get(indRegleSyntaxe - 3)),
                                new Interpreteur_Alias_Cible<GroupCases>(alias2),
                                Fonctions_Comportements.est_place);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Caseparam inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                    }
                }
            }
            case "02R35R27" -> {
                // Piece + Joueur alias + action + caseparam alias
                Alias<Jeton, Joueur> alias1;
                Alias<Jeton, GroupCases> alias2;
                Alias<Jeton, ?> testAlias = null;
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe - 2));
                    alias1 = (Alias<Jeton, Joueur>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 2) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 2) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias2 = (Alias<Jeton, GroupCases>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }

                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "estsur" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias1), regleString.get(indRegleSyntaxe - 2)),
                                new Interpreteur_Alias_Cible<GroupCases>(alias2),
                                Fonctions_Comportements.est_Sur);
                    }
                    case "prend" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias1), regleString.get(indRegleSyntaxe - 2)),
                                new Interpreteur_Alias_Cible<GroupCases>(alias2),
                                Fonctions_Comportements.prend_Par_Case);
                    }
                    case "sedeplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias1), regleString.get(indRegleSyntaxe - 2)),
                                new Interpreteur_Alias_Cible<GroupCases>(alias2),
                                Fonctions_Comportements.se_deplace);
                    }
                    case "estplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias1), regleString.get(indRegleSyntaxe - 2)),
                                new Interpreteur_Alias_Cible<GroupCases>(alias2),
                                Fonctions_Comportements.est_place);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Caseparam inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                    }
                }
            }
            case "0R2R35R27" -> {
                // Piece alias + Joueur alias + action + caseparam alias
                Alias<Jeton, Piece> alias1;
                Alias<Jeton, Joueur> alias2;
                Alias<Jeton, GroupCases> alias3;
                Alias<Jeton, ?> testAlias = null;
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe - 3));
                    alias1 = (Alias<Jeton, Piece>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 3) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe - 2));
                    alias2 = (Alias<Jeton, Joueur>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 2) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 2) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias3 = (Alias<Jeton, GroupCases>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }

                switch (regleString.get(indRegleSyntaxe - 1)) {
                    case "estsur" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1), new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                new Interpreteur_Alias_Cible<GroupCases>(alias3),
                                Fonctions_Comportements.est_Sur);
                    }
                    case "prend" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1), new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                new Interpreteur_Alias_Cible<GroupCases>(alias3),
                                Fonctions_Comportements.prend_Par_Case);
                    }
                    case "sedeplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1), new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                new Interpreteur_Alias_Cible<GroupCases>(alias3),
                                Fonctions_Comportements.se_deplace);
                    }
                    case "estplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1), new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                new Interpreteur_Alias_Cible<GroupCases>(alias3),
                                Fonctions_Comportements.est_place);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Caseparam inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                    }
                }
            }

            //Alias CASE CASE - 1127
            case "0R251127", "0R351127" -> {
                // Piece/PT alias + action + case + case
                Alias<Jeton, Piece> alias;
                Alias<Jeton, ?> testAlias = null;
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe - 3));
                    alias = (Alias<Jeton, Piece>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 3) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }

                switch (regleString.get(indRegleSyntaxe - 2)) {
                    case "estsur" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new Interpreteur_Alias_Sujet<Piece>(alias),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe - 1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Sur);
                    }
                    case "prend" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new Interpreteur_Alias_Sujet<Piece>(alias),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe - 1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Case);
                    }
                    case "sedeplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new Interpreteur_Alias_Sujet<Piece>(alias),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe - 1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.se_deplace);
                    }
                    case "estplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new Interpreteur_Alias_Sujet<Piece>(alias),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe - 1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_place);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Caseparam inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                    }
                }
            }
            case "0R2351127" -> {
                // Piece alias + Joueur + action + case + case
                Alias<Jeton, Piece> alias;
                Alias<Jeton, ?> testAlias = null;
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe - 4));
                    alias = (Alias<Jeton, Piece>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 4) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 4) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }

                switch (regleString.get(indRegleSyntaxe - 2)) {
                    case "estsur" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias), regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe - 1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Sur);
                    }
                    case "prend" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias), regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe - 1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Case);
                    }
                    case "sedeplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias), regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe - 1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.se_deplace);
                    }
                    case "estplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias), regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe - 1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_place);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Caseparam inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                    }
                }
            }
            case "02R351127" -> {
                // Piece + Joueur alias action + case + case
                Alias<Jeton, Joueur> alias;
                Alias<Jeton, ?> testAlias = null;
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe - 3));
                    alias = (Alias<Jeton, Joueur>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 3) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }

                switch (regleString.get(indRegleSyntaxe - 2)) {
                    case "estsur" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias), regleString.get(indRegleSyntaxe - 4)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe - 1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Sur);
                    }
                    case "prend" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias), regleString.get(indRegleSyntaxe - 4)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe - 1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Case);
                    }
                    case "sedeplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias), regleString.get(indRegleSyntaxe - 4)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe - 1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.se_deplace);
                    }
                    case "estplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias), regleString.get(indRegleSyntaxe - 4)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe - 1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_place);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Caseparam inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                    }
                }
            }
            case "0R2R351127" -> {
                // Piece alias + Joueur alias + action + case + case
                Alias<Jeton, Piece> alias1;
                Alias<Jeton, Joueur> alias2;
                Alias<Jeton, ?> testAlias = null;
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe - 4));
                    alias1 = (Alias<Jeton, Piece>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 4) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 4) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe - 3));
                    alias2 = (Alias<Jeton, Joueur>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 3) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }

                switch (regleString.get(indRegleSyntaxe - 2)) {
                    case "estsur" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1), new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe - 1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Sur);
                    }
                    case "prend" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1), new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe - 1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Case);
                    }
                    case "sedeplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1), new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe - 1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.se_deplace);
                    }
                    case "estplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1), new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                new InterpreteurCibleCase(regleString.get(indRegleSyntaxe - 1), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_place);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Caseparam inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                    }
                }
            }

            //Alias CASE CASE - R1127
            case "025R1127", "035R1127" -> {
                // Piece/PT + action + case alias + case
                Alias<Jeton, GroupCases> alias;
                Alias<Jeton, ?> testAlias = null;
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe - 1));
                    alias = (Alias<Jeton, GroupCases>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 1) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 1) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }

                switch (regleString.get(indRegleSyntaxe - 2)) {
                    case "estsur" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Sur);
                    }
                    case "prend" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Case);
                    }
                    case "sedeplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.se_deplace);
                    }
                    case "estplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_place);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Caseparam inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                    }
                }
            }
            case "0235R1127" -> {
                // Piece + Joueur + action + case alias + case
                Alias<Jeton, GroupCases> alias;
                Alias<Jeton, ?> testAlias = null;
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe - 1));
                    alias = (Alias<Jeton, GroupCases>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 1) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 1) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }

                switch (regleString.get(indRegleSyntaxe - 2)) {
                    case "estsur" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 4), regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Sur);
                    }
                    case "prend" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 4), regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Case);
                    }
                    case "sedeplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 4), regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.se_deplace);
                    }
                    case "estplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 4), regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_place);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Caseparam inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                    }
                }
            }
            case "0R25R1127", "0R35R1127" -> {
                // Piece/PT alias + action + case alias + case
                Alias<Jeton, Piece> alias1;
                Alias<Jeton, GroupCases> alias2;
                Alias<Jeton, ?> testAlias = null;
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe - 3));
                    alias1 = (Alias<Jeton, Piece>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 3) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe - 1));
                    alias2 = (Alias<Jeton, GroupCases>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 1) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 1) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }

                switch (regleString.get(indRegleSyntaxe - 2)) {
                    case "estsur" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new Interpreteur_Alias_Sujet<Piece>(alias1),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias2), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Sur);
                    }
                    case "prend" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new Interpreteur_Alias_Sujet<Piece>(alias1),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias2), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Case);
                    }
                    case "sedeplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new Interpreteur_Alias_Sujet<Piece>(alias1),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias2), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.se_deplace);
                    }
                    case "estplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new Interpreteur_Alias_Sujet<Piece>(alias1),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias2), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_place);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Caseparam inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                    }
                }
            }
            case "0R235R1127" -> {
                // Piece alias + Joueur + action + case alias + case
                Alias<Jeton, Piece> alias1;
                Alias<Jeton, GroupCases> alias2;
                Alias<Jeton, ?> testAlias = null;
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe - 4));
                    alias1 = (Alias<Jeton, Piece>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 4) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 4) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe - 1));
                    alias2 = (Alias<Jeton, GroupCases>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 1) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 1) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }

                switch (regleString.get(indRegleSyntaxe - 2)) {
                    case "estsur" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias1), regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias2), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Sur);
                    }
                    case "prend" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias1), regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias2), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Case);
                    }
                    case "sedeplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias1), regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias2), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.se_deplace);
                    }
                    case "estplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias1), regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias2), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_place);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Caseparam inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                    }
                }
            }
            case "02R35R1127" -> {
                // Piece + Joueur alias + action + case alias + case
                Alias<Jeton, Joueur> alias1;
                Alias<Jeton, GroupCases> alias2;
                Alias<Jeton, ?> testAlias = null;
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe - 3));
                    alias1 = (Alias<Jeton, Joueur>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 3) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe - 1));
                    alias2 = (Alias<Jeton, GroupCases>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 1) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 1) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }

                switch (regleString.get(indRegleSyntaxe - 2)) {
                    case "estsur" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias1), regleString.get(indRegleSyntaxe - 4)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias2), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Sur);
                    }
                    case "prend" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias1), regleString.get(indRegleSyntaxe - 4)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias2), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Case);
                    }
                    case "sedeplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias1), regleString.get(indRegleSyntaxe - 4)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias2), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.se_deplace);
                    }
                    case "estplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias1), regleString.get(indRegleSyntaxe - 4)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias2), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_place);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Caseparam inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                    }
                }
            }
            case "0R2R35R1127" -> {
                // Piece alias + Joueur alias + action + case alias + case
                Alias<Jeton, Piece> alias1;
                Alias<Jeton, Joueur> alias2;
                Alias<Jeton, GroupCases> alias3;
                Alias<Jeton, ?> testAlias = null;
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe - 4));
                    alias1 = (Alias<Jeton, Piece>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 4) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 4) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe - 3));
                    alias2 = (Alias<Jeton, Joueur>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 3) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe - 1));
                    alias3 = (Alias<Jeton, GroupCases>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 1) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 1) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }

                switch (regleString.get(indRegleSyntaxe - 2)) {
                    case "estsur" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1), new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias3), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_Sur);
                    }
                    case "prend" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1), new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias3), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.prend_Par_Case);
                    }
                    case "sedeplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1), new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias3), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.se_deplace);
                    }
                    case "estplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1), new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias3), regleString.get(indRegleSyntaxe)),
                                Fonctions_Comportements.est_place);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Caseparam inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                    }
                }
            }

            //Alias CASE CASE - 11R27
            case "02511R27", "03511R27" -> {
                // Piece/PT + action + case + case alias
                Alias<Jeton, GroupCases> alias;
                Alias<Jeton, ?> testAlias = null;
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias = (Alias<Jeton, GroupCases>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }

                switch (regleString.get(indRegleSyntaxe - 2)) {
                    case "estsur" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias), regleString.get(indRegleSyntaxe - 1)),
                                Fonctions_Comportements.est_Sur);
                    }
                    case "prend" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias), regleString.get(indRegleSyntaxe - 1)),
                                Fonctions_Comportements.prend_Par_Case);
                    }
                    case "sedeplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias), regleString.get(indRegleSyntaxe - 1)),
                                Fonctions_Comportements.se_deplace);
                    }
                    case "estplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias), regleString.get(indRegleSyntaxe - 1)),
                                Fonctions_Comportements.est_place);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Caseparam inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                    }
                }
            }
            case "023511R27" -> {
                // Piece + Joueur + action + case + case alias
                Alias<Jeton, GroupCases> alias;
                Alias<Jeton, ?> testAlias = null;
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias = (Alias<Jeton, GroupCases>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }

                switch (regleString.get(indRegleSyntaxe - 2)) {
                    case "estsur" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 4), regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias), regleString.get(indRegleSyntaxe - 1)),
                                Fonctions_Comportements.est_Sur);
                    }
                    case "prend" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 4), regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias), regleString.get(indRegleSyntaxe - 1)),
                                Fonctions_Comportements.prend_Par_Case);
                    }
                    case "sedeplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 4), regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias), regleString.get(indRegleSyntaxe - 1)),
                                Fonctions_Comportements.se_deplace);
                    }
                    case "estplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 4), regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias), regleString.get(indRegleSyntaxe - 1)),
                                Fonctions_Comportements.est_place);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Caseparam inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                    }
                }
            }
            case "0R2511R27", "0R3511R27" -> {
                // Piece/PT alias + action + case + case alias
                Alias<Jeton, Piece> alias1;
                Alias<Jeton, GroupCases> alias2;
                Alias<Jeton, ?> testAlias = null;
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe - 3));
                    alias1 = (Alias<Jeton, Piece>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 3) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias2 = (Alias<Jeton, GroupCases>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }

                switch (regleString.get(indRegleSyntaxe - 2)) {
                    case "estsur" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new Interpreteur_Alias_Sujet<Piece>(alias1),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias2), regleString.get(indRegleSyntaxe - 1)),
                                Fonctions_Comportements.est_Sur);
                    }
                    case "prend" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new Interpreteur_Alias_Sujet<Piece>(alias1),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias2), regleString.get(indRegleSyntaxe - 1)),
                                Fonctions_Comportements.prend_Par_Case);
                    }
                    case "sedeplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new Interpreteur_Alias_Sujet<Piece>(alias1),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias2), regleString.get(indRegleSyntaxe - 1)),
                                Fonctions_Comportements.se_deplace);
                    }
                    case "estplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new Interpreteur_Alias_Sujet<Piece>(alias1),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias2), regleString.get(indRegleSyntaxe - 1)),
                                Fonctions_Comportements.est_place);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Caseparam inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                    }
                }
            }
            case "0R23511R27" -> {
                // Piece alias + Joueur + action + case + case alias
                Alias<Jeton, Piece> alias1;
                Alias<Jeton, GroupCases> alias2;
                Alias<Jeton, ?> testAlias = null;
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe - 4));
                    alias1 = (Alias<Jeton, Piece>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 4) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 4) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias2 = (Alias<Jeton, GroupCases>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }

                switch (regleString.get(indRegleSyntaxe - 2)) {
                    case "estsur" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias1), regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias2), regleString.get(indRegleSyntaxe - 1)),
                                Fonctions_Comportements.est_Sur);
                    }
                    case "prend" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias1), regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias2), regleString.get(indRegleSyntaxe - 1)),
                                Fonctions_Comportements.prend_Par_Case);
                    }
                    case "sedeplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias1), regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias2), regleString.get(indRegleSyntaxe - 1)),
                                Fonctions_Comportements.se_deplace);
                    }
                    case "estplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasJoueurPT(new Interpreteur_Alias_Sujet<Piece>(alias1), regleString.get(indRegleSyntaxe - 3)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias2), regleString.get(indRegleSyntaxe - 1)),
                                Fonctions_Comportements.est_place);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Caseparam inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                    }
                }
            }
            case "02R3511R27" -> {
                // Piece + Joueur alias action + case + case alias
                Alias<Jeton, Joueur> alias1;
                Alias<Jeton, GroupCases> alias2;
                Alias<Jeton, ?> testAlias = null;
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe - 3));
                    alias1 = (Alias<Jeton, Joueur>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 3) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias2 = (Alias<Jeton, GroupCases>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }

                switch (regleString.get(indRegleSyntaxe - 2)) {
                    case "estsur" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias1), regleString.get(indRegleSyntaxe - 4)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias2), regleString.get(indRegleSyntaxe - 1)),
                                Fonctions_Comportements.est_Sur);
                    }
                    case "prend" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias1), regleString.get(indRegleSyntaxe - 4)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias2), regleString.get(indRegleSyntaxe - 1)),
                                Fonctions_Comportements.prend_Par_Case);
                    }
                    case "sedeplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias1), regleString.get(indRegleSyntaxe - 4)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias2), regleString.get(indRegleSyntaxe - 1)),
                                Fonctions_Comportements.se_deplace);
                    }
                    case "estplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetPieceAliasPT(new Interpreteur_Alias_Sujet<Joueur>(alias1), regleString.get(indRegleSyntaxe - 4)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias2), regleString.get(indRegleSyntaxe - 1)),
                                Fonctions_Comportements.est_place);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Caseparam inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                    }
                }
            }
            case "0R2R3511R27" -> {
                // Piece alias + Joueur alias + action + case + case alias
                Alias<Jeton, Piece> alias1;
                Alias<Jeton, Joueur> alias2;
                Alias<Jeton, GroupCases> alias3;
                Alias<Jeton, ?> testAlias = null;
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe - 4));
                    alias1 = (Alias<Jeton, Piece>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 4) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 4) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe - 3));
                    alias2 = (Alias<Jeton, Joueur>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 3) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe - 3) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }
                try {
                    testAlias = recupererAlias(regleString.get(indRegleSyntaxe));
                    alias3 = (Alias<Jeton, GroupCases>) testAlias;
                } catch (ClassCastException e) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie() + ")");
                } catch (MauvaiseDefinitionRegleException re) {
                    throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation ([PIECE]+JOUEUR+ACTION+PIECE)");
                }

                switch (regleString.get(indRegleSyntaxe - 2)) {
                    case "estsur" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1), new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias3), regleString.get(indRegleSyntaxe - 1)),
                                Fonctions_Comportements.est_Sur);
                    }
                    case "prend" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1), new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias3), regleString.get(indRegleSyntaxe - 1)),
                                Fonctions_Comportements.prend_Par_Case);
                    }
                    case "sedeplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1), new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias3), regleString.get(indRegleSyntaxe - 1)),
                                Fonctions_Comportements.se_deplace);
                    }
                    case "estplace" -> {
                        cond = new ConditionAction<Piece, GroupCases>(
                                new InterpreteurSujetAliasAliasPT(new Interpreteur_Alias_Sujet<Piece>(alias1), new Interpreteur_Alias_Sujet<Joueur>(alias2)),
                                new InterpreteurCibleAliasCaseParam(new Interpreteur_Alias_Cible<GroupCases>(alias3), regleString.get(indRegleSyntaxe - 1)),
                                Fonctions_Comportements.est_place);
                    }
                    default -> {
                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Caseparam inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
                    }
                }
            }

            //Alias CASE CASE - R11R27
            case "025R11R27", "035R11R27" -> {
                // Piece/PT + action + case alias + case alias
                throw new MauvaiseSemantiqueRegleException("Bloc Piece/PT + action + case alias + case alias impossible (bloc case alias + case alias impossible) [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
            }
            case "0235R11R27" -> {
                // Piece + Joueur + action + case alias + case alias
                throw new MauvaiseSemantiqueRegleException("Bloc Piece + Joueur + action + case alias + case alias impossible (bloc case alias + case alias impossible) [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
            }
            case "0R25R11R27", "0R35R11R27" -> {
                // Piece/PT alias + action + case alias + case alias
                throw new MauvaiseSemantiqueRegleException("Bloc Piece/PT alias + action + case alias + case alias impossible (bloc case alias + case alias impossible) [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
            }
            case "0R235R11R27" -> {
                // Piece alias + Joueur + action + case alias + case alias
                throw new MauvaiseSemantiqueRegleException("Bloc Piece alias + Joueur + action + case alias + case alias impossible (bloc case alias + case alias impossible) [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
            }
            case "02R35R11R27" -> {
                // Piece + Joueur alias action + case alias + case alias
                throw new MauvaiseSemantiqueRegleException("Bloc Piece + Joueur alias action + case alias + case alias impossible (bloc case alias + case alias impossible) [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
            }
            case "0R2R35R11R27" -> {
                // Piece alias + Joueur alias + action + case alias + case alias
                throw new MauvaiseSemantiqueRegleException("Bloc Piece alias + Joueur alias + action + case alias + case alias impossible (bloc case alias + case alias impossible) [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
            }
            default -> {
                throw new MauvaiseSemantiqueRegleException("Bloc PieceToken-ConsequenceAction-Caseparam inconnu [" + getMessageErreur(indRegleSyntaxe, regleSyntaxe, regleString) + "]");
            }
        }
        return cond;
    }
}
