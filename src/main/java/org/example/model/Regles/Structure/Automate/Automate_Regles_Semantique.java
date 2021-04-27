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
        super(35,0);
        aliasRegle = new HashMap<>();
    }

    public Automate_Regles_Semantique(List<String> nomEtat){
        super(35,0, nomEtat);
        aliasRegle = new HashMap<>();
    }

    public void ajouterAlias(String nomAlias, String parcours) throws MauvaiseDefinitionRegleException{
        switch (parcours){
            case "0259" , "0359" , "02359" -> aliasRegle.put(nomAlias,new Alias<Jeton,Piece>(Jeton.PIECE,false)/*Alias_Cible(Jeton.PIECE)*/);
            case "025910" , "02510" , "0235910" ,"023510" , "035910" , "03510" -> aliasRegle.put(nomAlias,new Alias<Jeton,Piece>(Jeton.PIECETOKEN,false)/*Alias_Cible(Jeton.PIECETOKEN)*/);
            case "01"-> aliasRegle.put(nomAlias,new Alias<Jeton,Joueur>(Jeton.JOUEUR,true)/*Alias_Sujet(Jeton.JOUEUR)*/);
            case "02" -> aliasRegle.put(nomAlias,new Alias<Jeton,Piece>(Jeton.PIECE,true)/*Alias_Sujet(Jeton.PIECE)*/);
            case "023" , "03" -> aliasRegle.put(nomAlias,new Alias<Jeton,Piece>(Jeton.PIECETOKEN,true)/*Alias_Sujet(Jeton.PIECETOKEN)*/);
            case "02511" , "023511" , "03511" -> aliasRegle.put(nomAlias,new Alias<Jeton,GroupCases>(Jeton.CASE,false)/*Alias_Cible(Jeton.CASE)*/);
            case "0251127" , "02351127" , "0351127" , "02527" , "023527" , "03527" -> aliasRegle.put(nomAlias,new Alias<Jeton,GroupCases>(Jeton.CASEPARAM,false)/*Alias_Cible(Jeton.CASEPARAM)*/);
            default -> throw new MauvaiseDefinitionRegleException("Impossible de définir l'alias " + nomAlias);
        }
    }

    public Alias<Jeton,?> recupererAlias(String nomAlias)throws MauvaiseDefinitionRegleException{
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

        //GESTION DES NEGATIONS
        this.ajouterUneTransition(4,Jeton.NON,4);
        this.ajouterUneTransition(7,Jeton.NON,7);

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

        for (Jeton j: regleSyntaxe) {
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
                        if(!(parcours.equals("014") || parcours.equals("027") || parcours.equals("0237") || parcours.equals("037"))){
                            jetonsarborescence.add(j);
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
                                    conditionsDeLaRegle.add(cond);
                                    nbConditions++;
                                    jetonsarborescence.add(Jeton.CONDITION);
                                }else{
                                    throw new MauvaiseSemantiqueRegleException("Pas assez d'argument pour Piece(T)-Etat [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                }

                            }

                            case 309 , 331 -> {
                                //traitement des sommets terminaux 9 et 31 : SUJET-ACTION-PIECE
                                if (indRegleSyntaxe >= 2) {
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
                                                    throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-Action-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
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
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " connu mais référencant le mauvais type de donnée : (attendu PIECE | recu " + testAlias.getJetonAssocie()+")");
                                            }catch (MauvaiseDefinitionRegleException re){
                                                throw new MauvaiseDefinitionRegleException("Alias  " + regleString.get(indRegleSyntaxe) + " inconnu dans sa réutilisation (PIECE+[JOUEUR]+ACTION+PIECE)");
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
                                                    throw new MauvaiseSemantiqueRegleException("Bloc PieceAlias-Action-PieceAlias inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                                }
                                            }
                                        }
                                        case "0R235R9" -> {
                                            //Alias sur Piece et Piece : [PIECE]+JOUEUR+ACTION+[PIECE]

                                        }
                                        case "02R35R9" -> {
                                            //Alias sur Piece et Joueur : PIECE+[JOUEUR]+ACTION+[PIECE]

                                        }
                                        case "0R2R35R9" -> {
                                            //Alias sur Piece et Joueur et Piece : [PIECE]+[JOUEUR]+ACTION+[PIECE]

                                        }
                                        default -> {
                                            throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Piece OU Piece-Joueur-Action-Piece OU PieceToken-Action-Piece inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                        }
                                    }
                                    conditionsDeLaRegle.add(cond);
                                    nbConditions++;
                                    jetonsarborescence.add(Jeton.CONDITION);
                                }else{
                                    throw new MauvaiseSemantiqueRegleException("Pas assez d'argument pour Piece(T)-Action-Piece [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                }
                            }

                            case 310 , 332 -> {
                                //SUJET-ACTION-PIECETOKEN   OU  SUJET-ACTION-PIECE-JOUEUR
                                if (indRegleSyntaxe >= 2) {
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
                                        default -> {
                                            throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Piece-Joueur OU Piece-Action-PieceToken OU Piece-Joueur-Action-Piece-Joueur OU Piece-Joueur-Action-PieceToken OU PieceToken-Action-Piece-Joueur OU PieceToken-Action-PieceToken inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                        }
                                    }
                                    conditionsDeLaRegle.add(cond);
                                    nbConditions++;
                                    jetonsarborescence.add(Jeton.CONDITION);
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
                                        default -> {
                                            throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Case OU Piece-Joueur-Action-Case OU PieceToken-Action-Case inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                        }
                                    }
                                    conditionsDeLaRegle.add(cond);
                                    nbConditions++;
                                    jetonsarborescence.add(Jeton.CONDITION);
                                }else{
                                    throw new MauvaiseSemantiqueRegleException("Pas assez d'argument pour Piece(T)-Action-Case [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                }
                            }

                            case 313 -> {
                                //JOUEUR-COMPTEUR-COMPARAISON-NOMBRE
                                if (indRegleSyntaxe >= 3) {
                                    Condition cond = null;
                                    //Cas Joueur+Compteur+Comparaison+Nombre
                                    if (parcours.equals("014813")) {
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
                                            }
                                        } else {
                                            throw new MauvaiseSemantiqueRegleException("Bloc Joueur-Timer inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                        }
                                    } else {
                                        throw new MauvaiseSemantiqueRegleException("Bloc Joueur-Compteur-Comparaison-Nombre inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                    }
                                    conditionsDeLaRegle.add(cond);
                                    nbConditions++;
                                    jetonsarborescence.add(Jeton.CONDITION);
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
                                            if (regleString.get(indRegleSyntaxe - 2).equals("nbdeplacement")) {
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
                                                }
                                            } else {
                                                throw new MauvaiseSemantiqueRegleException("Bloc Piece-Compteur-Comparaison-Nombre inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }
                                        }
                                        case "02371214" -> {
                                            //Cas Piece+Joueur+Compteur+Comparaison+Nombre
                                            if (regleString.get(indRegleSyntaxe - 2).equals("nbdeplacement")) {
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
                                                }
                                            } else {
                                                throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-Compteur-Comparaison-Nombre inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }
                                        }
                                        case "0371214" -> {
                                            //Cas PieceToken+Compteur+Comparaison+Nombre
                                            if (regleString.get(indRegleSyntaxe - 2).equals("nbdeplacement")) {
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
                                                }
                                            } else {
                                                throw new MauvaiseSemantiqueRegleException("Bloc PieceToken-Compteur-Comparaison-Nombre inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }
                                        }
                                        default -> {
                                            throw new MauvaiseSemantiqueRegleException("Bloc Piece-Compteur-Comparaison-Nombre OU Piece-Joueur-Compteur-Comparaison-Nombre OU PieceToken-Compteur-Comparaison-Nombre inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                        }
                                    }
                                    conditionsDeLaRegle.add(cond);
                                    nbConditions++;
                                    jetonsarborescence.add(Jeton.CONDITION);
                                } else {
                                    throw new MauvaiseSemantiqueRegleException("Pas assez d'argument pour Piece(T)-Compteur-Comparaison-Nombre [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                }

                            }

                            case 327 , 334 -> {
                                if (indRegleSyntaxe >= 1) {
                                    Condition cond = null;
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
                                                    //cond = new ConditionAction<Piece, GroupCases>(
                                                    //        new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                                                    //        new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
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
                                                default -> {
                                                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-Action-Case-Case inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                                }
                                            }
                                        }

                                        //Alias CASEPARAM - 27
                                        case "0R2527", "0R3527" -> {}
                                        case "0R23527" -> {}
                                        case "02R3527" -> {}
                                        case "0R2R3527" -> {}

                                        //Alias CASEPARAM - R27
                                        case "025R27", "035R27" -> {}
                                        case "0235R27" -> {}
                                        case "0R25R27", "0R35R27" -> {}
                                        case "0R235R27" -> {}
                                        case "02R35R27" -> {}
                                        case "0R2R35R27" -> {}

                                        //Alias CASE CASE - 1127
                                        case "0R251127", "0R351127" -> {}
                                        case "0R2351127" -> {}
                                        case "02R351127" -> {}
                                        case "0R2R351127" -> {}

                                        //Alias CASE CASE - R1127
                                        case "025R1127", "035R1127" -> {}
                                        case "0235R1127" -> {}
                                        case "0R25R1127", "0R35R1127" -> {}
                                        case "0R235R1127" -> {}
                                        case "02R35R1127" -> {}
                                        case "0R2R35R1127" -> {}

                                        //Alias CASE CASE - 11R27
                                        case "02511R27", "03511R27" -> {}
                                        case "023511R27" -> {}
                                        case "0R2511R27", "0R3511R27" -> {}
                                        case "0R23511R27" -> {}
                                        case "02R3511R27" -> {}
                                        case "0R2R3511R27" -> {}

                                        //Alias CASE CASE - R11R27
                                        case "025R11R27", "035R11R27" -> {}
                                        case "0235R11R27" -> {}
                                        case "0R25R11R27", "0R35R11R27" -> {}
                                        case "0R235R11R27" -> {}
                                        case "02R35R11R27" -> {}
                                        case "0R2R35R11R27" -> {}
                                        //NULL
                                        default -> {
                                            throw new MauvaiseSemantiqueRegleException("Bloc PieceToken-ConsequenceAction-Caseparam inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                        }
                                    }
                                }

                            }

                            /*---------------------------------CONSEQUENCES---------------------------------*/
                            //regle.ajouterUneConsequence(cons);

                            case 319 -> {
                                //Consequence Joueur+ConsequenceTerminale
                                if(indRegleSyntaxe >= 1) {
                                    if (parcours.equals("151619")) {
                                        //"victoire", "defaite", "pat"
                                        switch (regleString.get(indRegleSyntaxe)) {
                                            case "victoire" -> {
                                                consequencesDeLaRegle.add(new ConsequenceTerminale<Joueur>(
                                                        new InterpreteurSujetJoueur(regleString.get(indRegleSyntaxe)),
                                                        Fonctions_Comportements.victoire));
                                                nbConsequence++;
                                            }
                                            case "defaite" -> {
                                                consequencesDeLaRegle.add(new ConsequenceTerminale<Joueur>(
                                                        new InterpreteurSujetJoueur(regleString.get(indRegleSyntaxe)),
                                                        Fonctions_Comportements.defaite));
                                                nbConsequence++;
                                            }
                                            case "pat" -> {
                                                consequencesDeLaRegle.add(new ConsequenceTerminale<Joueur>(
                                                        new InterpreteurSujetJoueur(regleString.get(indRegleSyntaxe)),
                                                        Fonctions_Comportements.pat));
                                                nbConsequence++;
                                            }
                                            default -> {
                                                throw new MauvaiseSemantiqueRegleException("Bloc Joueur-ConsequenceTerminale inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }
                                        }
                                    }else{
                                        //Pas atteignable en théorie (on ne peut atteindre 19 que part Joueur)
                                        throw new MauvaiseSemantiqueRegleException("Bloc Sujet-ConsequenceTerminale inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]" + parcours);
                                    }
                                }else{
                                    throw new MauvaiseSemantiqueRegleException("Pas assez d'argument pour Sujet-ConsequenceTerminale [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                }
                            }

                            case 321 -> {
                                //SUJET-CONSEQUENCE-PIECE ou SUJET-CONSEQUENCE-CASE-PIECE
                                if(indRegleSyntaxe>=2) {
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
                                                consequencesDeLaRegle.add(new ConsequenceAction<Piece, Piece>(
                                                        new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                                                        new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                                                        Fonctions_Comportements.prendre_piece));
                                                nbConsequence++;
                                            } else {
                                                throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-Piece inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }
                                        }
                                        case "1517182021" -> {
                                            // Piece+Joueur+Consequence+Piece
                                            if (regleString.get(indRegleSyntaxe - 1).equals("prendre")) {
                                                consequencesDeLaRegle.add(new ConsequenceAction<Piece, Piece>(
                                                        new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3), regleString.get(indRegleSyntaxe-2)),
                                                        new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                                                        Fonctions_Comportements.prendre_piece));
                                                nbConsequence++;
                                            } else {
                                                throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-ConsequenceAction-Piece inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }
                                        }
                                        case "15182021" -> {
                                            // Piecetoken+Consequence+Piece
                                            if (regleString.get(indRegleSyntaxe - 1).equals("prendre")) {
                                                consequencesDeLaRegle.add(new ConsequenceAction<Piece, Piece>(
                                                        new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                                                        new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                                                        Fonctions_Comportements.prendre_piece));
                                                nbConsequence++;
                                            } else {
                                                throw new MauvaiseSemantiqueRegleException("Bloc Piecetoken-ConsequenceAction-Piece inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }
                                        }

                                        //en passant par 20-23 (case + pièce)
                                        // SUJET-CONSEQUENCE-CASE-PIECE
                                        case "1516202321" -> {
                                            // Joueur+Consequence+Case+Piece
                                            if (regleString.get(indRegleSyntaxe - 2).equals("placer")) {
                                                //EN TRAVAUX RIGHT NOW (signé hugo le boss)
                                                new ConsequenceAction<Piece, GroupCases>(
                                                        new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe - 3), regleString.get(indRegleSyntaxe)),
                                                        new InterpreteurCibleCase(regleString.get(indRegleSyntaxe - 1)),
                                                        Fonctions_Comportements.placer);
                                                nbConsequence++;
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
                                        default -> {
                                            throw new MauvaiseSemantiqueRegleException("Bloc Sujet-ConsequenceAction-Piece ou Sujet-ConsequenceAction-Case-Piece inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                        }
                                    }
                                }else{
                                    throw new MauvaiseSemantiqueRegleException("Pas assez d'argument pour Sujet-ConsequenceAction-(Case)-Piece [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                }
                            }

                            case 322 -> {
                                //SUJET-CONSEQUENCEACTION-PIECETOKEN ou SUJET-CONSEQUENCEACTION-PIECE-JOUEUR ou SUJET-CONSEQUENCEACTION-CASE-PIECETOKEN ou SUJET-CONSEQUENCEACTION-CASE-PIECE-JOUEUR
                                if(indRegleSyntaxe>=2) {
                                    switch (parcours) {
                                        //en passant par 20 (piecetoken)
                                        case "15162022" -> {
                                            // Joueur+ConsequenceAction+PieceToken
                                            throw new MauvaiseSemantiqueRegleException("Bloc Joueur-ConsequenceAction-PieceToken inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                        }
                                        case "15172022" -> {
                                            // Piece+ConsequenceAction+PieceToken
                                            if (regleString.get(indRegleSyntaxe - 1).equals("prendre")) {
                                                consequencesDeLaRegle.add(new ConsequenceAction<Piece, Piece>(
                                                        new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                                                        new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                                                        Fonctions_Comportements.prendre_piece));
                                                nbConsequence++;
                                            } else {
                                                throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-PieceToken inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }
                                        }
                                        case "1517182022" -> {
                                            // Joueur+Piece+ConsequenceAction+PieceToken
                                            if (regleString.get(indRegleSyntaxe - 1).equals("prendre")) {
                                                consequencesDeLaRegle.add(new ConsequenceAction<Piece, Piece>(
                                                        new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3), regleString.get(indRegleSyntaxe-2)),
                                                        new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                                                        Fonctions_Comportements.prendre_piece));
                                                //TODO: Changer de Hugo, le notre est obsolète
                                                nbConsequence++;
                                            } else {
                                                throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-ConsequenceAction-PieceToken inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }
                                        }
                                        case "15182022" -> {
                                            // PieceToken+ConsequenceAction+PieceToken
                                            if (regleString.get(indRegleSyntaxe - 1).equals("prendre")) {
                                                consequencesDeLaRegle.add(new ConsequenceAction<Piece, Piece>(
                                                        new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                                                        new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe)),
                                                        Fonctions_Comportements.prendre_piece));
                                                nbConsequence++;
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
                                                consequencesDeLaRegle.add(new ConsequenceAction<Piece, Piece>(
                                                        new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3)),
                                                        new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-1), regleString.get(indRegleSyntaxe)),
                                                        Fonctions_Comportements.prendre_piece));
                                                nbConsequence++;
                                            } else {
                                                throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-Piece-Joueur inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }
                                        }
                                        case "151718202122" -> {
                                            // Piece+Joueur+ConsequenceAction+Piece+Joueur
                                            if (regleString.get(indRegleSyntaxe - 2).equals("prendre")) {
                                                consequencesDeLaRegle.add(new ConsequenceAction<Piece, Piece>(
                                                        new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-4), regleString.get(indRegleSyntaxe-3)),
                                                        new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-1), regleString.get(indRegleSyntaxe)),
                                                        Fonctions_Comportements.prendre_piece));
                                                nbConsequence++;
                                            } else {
                                                throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-ConsequenceAction-Piece-Joueur inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }
                                        }
                                        case "1518202122" -> {
                                            // PieceToken+ConsequenceAction+Piece+Joueur
                                            if (regleString.get(indRegleSyntaxe - 2).equals("prendre")) {
                                                consequencesDeLaRegle.add(new ConsequenceAction<Piece, Piece>(
                                                        new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3)),
                                                        new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-1), regleString.get(indRegleSyntaxe)),
                                                        Fonctions_Comportements.prendre_piece));
                                                nbConsequence++;
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
                                            // TODO Rachetez un Hugo plus qualifiés et un peu plus beau que er wan sinon un binome serai plus beau et intelligent que l'autre (c'est déjà le cas, virgil nous downgrade)
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

                                        default -> {
                                            throw new MauvaiseSemantiqueRegleException("Bloc Sujet-ConsequenceAction-PieceToken OU Sujet-ConsequenceAction-Piece-Joueur OU Sujet-ConsequenceAction-Case-PieceToken OU Sujet-ConsequenceAction-Case-Piece-Joueur inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                        }
                                    }
                                }else{
                                    throw new MauvaiseSemantiqueRegleException("Pas assez d'argument pour Sujet-ConsequenceAction-(Case)-Piece(T,J) [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                }
                            }

                            case 323 -> {
                                //SUJET-CONSEQUENCE-CASE
                                if(indRegleSyntaxe>=2) {
                                    switch (parcours) {
                                        case "15162023" -> {
                                            // Joueur+ConsequenceAction+Case
                                            throw new MauvaiseSemantiqueRegleException("Bloc Joueur-ConsequenceAction-Case inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                        }
                                        case "15172023" -> {/*prendre + promouvoir*/
                                            // Piece+ConsequenceAction+Case
                                            switch (regleString.get(indRegleSyntaxe - 1)) {
                                                case "prendre" -> {
                                                    consequencesDeLaRegle.add(new ConsequenceAction<Piece, GroupCases>(
                                                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                                                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                                            Fonctions_Comportements.prendre_par_case));
                                                    nbConsequence++;
                                                }
                                                case "promouvoir" -> {
                                                    consequencesDeLaRegle.add(new ConsequenceAction<Piece, GroupCases>(
                                                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                                                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                                            Fonctions_Comportements.promouvoir));
                                                    nbConsequence++;
                                                }
                                                case "deplacer" -> {
                                                    consequencesDeLaRegle.add(new ConsequenceAction<Piece, GroupCases>(
                                                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                                                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                                            Fonctions_Comportements.deplacer));
                                                    nbConsequence++;
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
                                                    consequencesDeLaRegle.add(new ConsequenceAction<Piece, GroupCases>(
                                                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3), regleString.get(indRegleSyntaxe-2)),
                                                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                                            Fonctions_Comportements.prendre_par_case));
                                                    nbConsequence++;
                                                }
                                                case "promouvoir" -> {
                                                    consequencesDeLaRegle.add(new ConsequenceAction<Piece, GroupCases>(
                                                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3), regleString.get(indRegleSyntaxe-2)),
                                                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                                            Fonctions_Comportements.promouvoir));
                                                    nbConsequence++;
                                                }
                                                case "deplacer" -> {
                                                    consequencesDeLaRegle.add(new ConsequenceAction<Piece, GroupCases>(
                                                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-3), regleString.get(indRegleSyntaxe-2)),
                                                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                                            Fonctions_Comportements.deplacer));
                                                    nbConsequence++;
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
                                                    consequencesDeLaRegle.add(new ConsequenceAction<Piece, GroupCases>(
                                                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                                                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                                            Fonctions_Comportements.prendre_par_case));
                                                    nbConsequence++;
                                                }
                                                case "promouvoir" -> {
                                                    consequencesDeLaRegle.add(new ConsequenceAction<Piece, GroupCases>(
                                                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                                                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                                            Fonctions_Comportements.promouvoir));
                                                    nbConsequence++;
                                                }
                                                case "deplacer" -> {
                                                    consequencesDeLaRegle.add(new ConsequenceAction<Piece, GroupCases>(
                                                            new InterpreteurSujetPiece(regleString.get(indRegleSyntaxe-2)),
                                                            new InterpreteurCibleCase(regleString.get(indRegleSyntaxe)),
                                                            Fonctions_Comportements.deplacer));
                                                    nbConsequence++;
                                                }
                                                default -> {
                                                    throw new MauvaiseSemantiqueRegleException("Bloc PieceToken-ConsequenceAction-Case inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                                }
                                            }
                                        }
                                        default -> {
                                            throw new MauvaiseSemantiqueRegleException("Bloc Sujet-ConsequenceAction-Case inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                        }
                                    }
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
}
