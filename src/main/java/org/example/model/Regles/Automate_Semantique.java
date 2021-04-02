package org.example.model.Regles;


import org.example.model.Piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Automate_Semantique extends Automate{

    public Automate_Semantique(int nbEtat){
        super(nbEtat);
        this.initialiserAutomate();
    }

    public Automate_Semantique(int nbEtat,List<String> nomEtat){
        super(nbEtat, nomEtat);
        this.initialiserAutomate();
    }

    public void initialiserAutomate(){
        //GESTIONS ETATS TERMINAUX
        List<Integer> etatTer = new ArrayList<>(Arrays.asList(6,9,10,11,13,14,19,21,22,23));
        int ind = 0;
        for (Integer i:etatTer) {
            this.ajouterUnEtatTerminal(i,300+ind);
            ind++;
        }

        //GESTION DES ALIAS
        this.ajouterUneTransition(1,Jeton.ALIAS,1);
        this.ajouterUneTransition(2,Jeton.ALIAS,2);
        this.ajouterUneTransition(3,Jeton.ALIAS,3);
        this.ajouterUneTransition(9,Jeton.ALIAS,9);
        this.ajouterUneTransition(10,Jeton.ALIAS,10);
        this.ajouterUneTransition(11,Jeton.ALIAS,11);

        //GESTION DES NEGATIONS
        this.ajouterUneTransition(4,Jeton.NON,4);
        this.ajouterUneTransition(7,Jeton.NON,7);

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

        //ETAT 12
        //this.setValeurEtat(12,"12");
        this.ajouterUneTransition(12,Jeton.COMPTEUR,14);

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
    }

    private boolean peutAvancer(int indice, int etat, List<Jeton> regleSyntaxe){
        try{
            return this.etatSuivant(etat,regleSyntaxe.get(indice)) != -1;
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
    public List<BlocDeRegle> analyserUneRegle(List<Jeton> regleSyntaxe, List<String> regleString) throws MauvaiseSemantiqueRegleException{
        //Etat initial par défaut : 0
        int curEtat = 0;
        //Liste à retourner après le traitement
        List<BlocDeRegle> blocs = new ArrayList<>();
        //Chaine de caractere fourni par le passage dans les états
        String parcours = "0";
        //Integer renseignant l'indice de parcour dans regleSyntaxe
        int  indRegleSyntaxe = 0;

        boolean traitementCondition = true;


        for (Jeton jetons: regleSyntaxe) {

            if(jetons == Jeton.ALORS){
                traitementCondition = false;
            }

            //Récupération de l'indice du prochain état d'après la transition donnée
            curEtat = this.etatSuivant(curEtat,jetons);

            //Récupération de l'état correspondant à l'indice curEtat
            Etat etat = this.recupererEtat(curEtat);

            indRegleSyntaxe++;

            if (etat != null){
                parcours+=etat.getValeur();
                if (etat.estTerminal()) {
                    //Si terminal, peut on encore avanncer apres ?
                    if (peutAvancer(indRegleSyntaxe + 1, curEtat, regleSyntaxe)) {
                        //Si on peut encore avancer,
                        //C'est que l'on peut analyser le prochain jeton
                        //Si c'est un connecteur valide
                        if ((traitementCondition && estConnecteurCondition(regleSyntaxe.get(indRegleSyntaxe + 1))) || (!traitementCondition && estConnecteurConsequence(regleSyntaxe.get(indRegleSyntaxe + 1)))){
                            //Alors notre etat est bien terminal, on l'analyse
                            switch (etat.getCodeDeRetour()) {
                                /*---------------------------------CONDITIONS---------------------------------*/
                                case 306 -> {
                                    //traitement sommet terminal 6 : SUJET-ETAT
                                    //Seul l'état estpromu est possible
                                    if(indRegleSyntaxe >= 2){
                                        switch (parcours) {
                                            case "026" -> {
                                                //Cas Piece+Etat
                                                if (regleString.get(indRegleSyntaxe).equals("estpromu")) {

                                                } else {
                                                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-Etat inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                                }
                                            }
                                            case "0236" -> {
                                                //Cas Piece+Joueur+Etat
                                                if (regleString.get(indRegleSyntaxe).equals("estpromu")) {

                                                } else {
                                                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-Etat inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                                }
                                            }
                                            case "036" -> {
                                                //Cas PieceToken+Etat
                                                if (regleString.get(indRegleSyntaxe).equals("estpromu")) {

                                                } else {
                                                    throw new MauvaiseSemantiqueRegleException("Bloc PieceToken-Etat inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                                }
                                            }
                                            default -> {
                                                throw new MauvaiseSemantiqueRegleException("Bloc Piece-Etat OU Piece-Joueur-Etat OU PieceToken-Etat inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }
                                        }
                                    }else{
                                        throw new MauvaiseSemantiqueRegleException("Pas assez d'argument pour Piece(T)-Etat [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                    }

                                }

                                case 309 -> {
                                    //SUJET-ACTION-PIECE
                                    if (indRegleSyntaxe >= 3) {
                                        switch (parcours) {
                                            //Cas Piece+Action+Piece
                                            case "0259" -> {
                                                switch (regleString.get(indRegleSyntaxe - 1)) {
                                                    case "prend" ->{if(1==1);}
                                                    case "estechec" -> {if(1==1);}
                                                    default -> {
                                                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Piece inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                                    }
                                                }
                                            }
                                            //Cas PieceToken+Action+Piece
                                            case "0359" -> {
                                                switch (regleString.get(indRegleSyntaxe - 1)) {
                                                    case "prend" -> {if(2==2);}
                                                    case "estechec" -> {if(2==2);}
                                                    default -> {
                                                        throw new MauvaiseSemantiqueRegleException("Bloc PieceToken-Action-Piece inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                                    }
                                                }
                                            }
                                            //Cas Piece+Joueur+Action+Piece
                                            case "02359" -> {
                                                switch (regleString.get(indRegleSyntaxe - 1)) {
                                                    case "prend" -> {if(3==3);}
                                                    case "estechec" -> {if(3==3);}
                                                    default -> {
                                                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-Action-Piece inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                                    }
                                                }
                                            }
                                            default -> {
                                                throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Piece OU Piece-Joueur-Action-Piece OU PieceToken-Action-Piece inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }
                                        }
                                    }else{
                                        throw new MauvaiseSemantiqueRegleException("Pas assez d'argument pour Piece(T)-Action-Piece [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                    }
                                }

                                case 310 -> {
                                    //SUJET-ACTION-PIECETOKEN   OU  SUJET-ACTION-PIECE-JOUEUR
                                    if (indRegleSyntaxe >= 3) {
                                        switch (parcours) {
                                            case "025910" -> {
                                                //Cas Piece+action+piece+joueur
                                                switch (regleString.get(indRegleSyntaxe - 2)) {
                                                    case "prend" -> {if(1==1);}
                                                    case "estechec" -> {if(1==1);}
                                                    default -> {
                                                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Piece-Joueur inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                                    }
                                                }
                                            }
                                            case "02510" -> {
                                                //Cas Piece+action+piecetoken
                                                switch (regleString.get(indRegleSyntaxe - 1)) {
                                                    case "prend" -> {if(2==2);}
                                                    case "estechec" -> {if(2==2);}
                                                    default -> {
                                                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-PieceToken inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                                    }
                                                }
                                            }
                                            case "035910" -> {
                                                //Cas Piecetoken+action+piece+joueur
                                                switch (regleString.get(indRegleSyntaxe - 2)) {
                                                    case "prend" -> {if(3==3);}
                                                    case "estechec" -> {if(3==3);}
                                                    default -> {
                                                        throw new MauvaiseSemantiqueRegleException("Bloc PieceToken-Action-Piece-Joueur inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                                    }
                                                }
                                            }
                                            case "03510" -> {
                                                //Cas Piecetoken+action+piecetoken
                                                switch (regleString.get(indRegleSyntaxe - 1)) {
                                                    case "prend" -> {if(4==4);}
                                                    case "estechec" -> {if(4==4);}
                                                    default -> {
                                                        throw new MauvaiseSemantiqueRegleException("Bloc PieceToken-Action-PieceToken inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                                    }
                                                }
                                            }
                                            case "0235910" -> {
                                                //Cas Piece+Joueur+action+piece+joueur
                                                switch (regleString.get(indRegleSyntaxe - 2)) {
                                                    case "prend" -> {if(5==5);}
                                                    case "estechec" -> {if(5==5);}
                                                    default -> {
                                                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-Action-Piece-Joueur inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                                    }
                                                }
                                            }
                                            case "023510" -> {
                                                //Cas Piece+Joueur+action+piecetoken
                                                switch (regleString.get(indRegleSyntaxe - 1)) {
                                                    case "prend" -> {if(6==6);}
                                                    case "estechec" -> {if(6==6);}
                                                    default -> {
                                                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-Action-PieceToken inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                                    }
                                                }
                                            }
                                            default -> {
                                                throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Piece-Joueur OU Piece-Action-PieceToken OU Piece-Joueur-Action-Piece-Joueur OU Piece-Joueur-Action-PieceToken OU PieceToken-Action-Piece-Joueur OU PieceToken-Action-PieceToken inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }
                                        }
                                    }else{
                                        throw new MauvaiseSemantiqueRegleException("Pas assez d'argument pour Piece(T)-Action-Piece(J|T) [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                    }
                                }

                                case 311 -> {
                                    //SUJET-ACTION-CASE
                                    if (indRegleSyntaxe >= 3) {
                                        switch (parcours) {
                                            case "02511" -> {
                                                //Cas Piece+Action+Case
                                                switch (regleString.get(indRegleSyntaxe - 1)) {
                                                    case "estsur" -> {if(5==5);}
                                                    case "prend" -> {if(5==5);}
                                                    case "sedeplace" -> {if(5==5);}
                                                    case "estplace" -> {if(5==5);}
                                                    default -> {
                                                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Case inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                                    }
                                                }
                                            }
                                            case "03511" -> {
                                                //Cas PieceToken+Action+Case
                                                switch (regleString.get(indRegleSyntaxe - 1)) {
                                                    case "estsur" -> {if(5==5);}
                                                    case "prend" -> {if(5==5);}
                                                    case "sedeplace" -> {if(5==5);}
                                                    case "estplace" -> {if(5==5);}
                                                    default -> {
                                                        throw new MauvaiseSemantiqueRegleException("Bloc PieceToken-Action-Case inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                                    }
                                                }
                                            }
                                            case "023511" -> {
                                                //Cas Piece+Joueur+Action+Case
                                                switch (regleString.get(indRegleSyntaxe-1)) {
                                                    case "estsur" -> {if(5==5);}
                                                    case "prend" -> {if(5==5);}
                                                    case "sedeplace" -> {if(5==5);}
                                                    case "estplace" -> {if(5==5);}
                                                    default -> {
                                                        throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-Action-Case inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                                    }
                                                }
                                            }
                                            default -> {
                                                throw new MauvaiseSemantiqueRegleException("Bloc Piece-Action-Case OU Piece-Joueur-Action-Case OU PieceToken-Action-Case inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }
                                        }
                                    }else{
                                        throw new MauvaiseSemantiqueRegleException("Pas assez d'argument pour Piece(T)-Action-Case [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                    }
                                }

                                case 313 -> {
                                    //JOUEUR-COMPTEUR-COMPARAISON-NOMBRE
                                    if (indRegleSyntaxe >= 4) {
                                        //Cas Joueur+Compteur+Comparaison+Nombre
                                        if (parcours.equals("014813")) {
                                            if (regleString.get(indRegleSyntaxe - 2).equals("timer")) {
                                                //nickel michel
                                            } else {
                                                throw new MauvaiseSemantiqueRegleException("Bloc Joueur-Timer inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }
                                        } else {
                                            throw new MauvaiseSemantiqueRegleException("Bloc Joueur-Compteur-Comparaison-Nombre inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                        }
                                    }else{
                                        throw new MauvaiseSemantiqueRegleException("Pas assez d'argument pour Joueur-Compteur-Comparaison-Nombre [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                    }
                                }

                                case 314 -> {
                                    //SUJET-COMPTEUR-COMPARAISON-NOMBRE

                                    if (indRegleSyntaxe >= 4) {
                                        switch (parcours) {
                                            case "0271214" -> {
                                                //Cas Piece+Compteur+Comparaison+Nombre
                                                if (regleString.get(indRegleSyntaxe - 2).equals("nbdeplacement")) {

                                                } else {
                                                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-Compteur-Comparaison-Nombre inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                                }
                                            }
                                            case "02371214" -> {
                                                //Cas Piece+Joueur+Compteur+Comparaison+Nombre
                                                if (regleString.get(indRegleSyntaxe - 2).equals("nbdeplacement")) {
                                                    //insane jeannette
                                                    if (2 == 2) ;
                                                } else {
                                                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-Compteur-Comparaison-Nombre inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                                }
                                            }
                                            case "0371214" -> {
                                                //Cas PieceToken+Compteur+Comparaison+Nombre
                                                if (regleString.get(indRegleSyntaxe - 2).equals("nbdeplacement")) {
                                                    //insane jeannette
                                                    if (3 == 3) ;
                                                } else {
                                                    throw new MauvaiseSemantiqueRegleException("Bloc PieceToken-Compteur-Comparaison-Nombre inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                                }
                                            }
                                            default -> {
                                                throw new MauvaiseSemantiqueRegleException("Bloc Piece-Compteur-Comparaison-Nombre OU Piece-Joueur-Compteur-Comparaison-Nombre OU PieceToken-Compteur-Comparaison-Nombre inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }
                                        }
                                    } else {
                                        throw new MauvaiseSemantiqueRegleException("Pas assez d'argument pour Piece(T)-Compteur-Comparaison-Nombre [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                    }
                                }

                                /*---------------------------------CONSEQUENCES---------------------------------*/
                                case 319 -> {
                                    //Consequence Joueur+ConsequenceTerminale
                                    if(indRegleSyntaxe >= 4) {
                                        if (parcours.equals("151619")) {
                                            //"victoire", "defaite", "pat"
                                            switch (regleString.get(indRegleSyntaxe - 1)) {
                                                case "victoire" -> {}
                                                case "defaite" -> {}
                                                case "pat" -> {}
                                                default -> {
                                                    throw new MauvaiseSemantiqueRegleException("Bloc Joueur-ConsequenceTerminale inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                                }
                                            }
                                        }else{
                                            //Pas atteignable en théorie (on ne peut atteindre 19 que part Joueur)
                                            throw new MauvaiseSemantiqueRegleException("Chemin emprunte inconnu pour ConsequenceTerminale [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                        }
                                    }else{
                                        throw new MauvaiseSemantiqueRegleException("Pas assez d'argument pour Piece(T)-Compteur-Comparaison-Nombre [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                    }
                                }

                                case 321 -> {
                                    //SUJET-CONSEQUENCE-PIECE ou SUJET-CONSEQUENCE-CASE-PIECE
                                    if(indRegleSyntaxe>=4) {
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
                                                    if (1 == 1) ;
                                                } else {
                                                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-Piece inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                                }
                                            }
                                            case "1517182021" -> {
                                                // Piece+Joueur+Consequence+Piece
                                                if (regleString.get(indRegleSyntaxe - 1).equals("prendre")) {
                                                    if (2 == 2) ;
                                                } else {
                                                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-ConsequenceAction-Piece inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                                }
                                            }
                                            case "15182021" -> {
                                                // Piecetoken+Consequence+Piece
                                                if (regleString.get(indRegleSyntaxe - 1).equals("prendre")) {
                                                    if (3 == 3) ;
                                                } else {
                                                    throw new MauvaiseSemantiqueRegleException("Bloc Piecetoken-ConsequenceAction-Piece inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                                }
                                            }

                                            //en passant par 20-23 (case + pièce)
                                            // SUJET-CONSEQUENCE-CASE-PIECE
                                            case "1516202321" -> {
                                                // Joueur+Consequence+Piece
                                                if (regleString.get(indRegleSyntaxe - 2).equals("placer")) {
                                                    if (1 == 1) ;
                                                } else {
                                                    throw new MauvaiseSemantiqueRegleException("Bloc Joueur-ConsequenceAction-Case-Piece inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                                }
                                            }
                                            case "1517202321" -> {
                                                // Piece+Consequence+Piece
                                                throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-Case-Piece inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }
                                            case "151718202321" -> {
                                                // Piece+Joueur+Consequence+Piece
                                                throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-ConsequenceAction-Case-Piece inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }
                                            case "1518202321" -> {
                                                // PieceToken+Consequence+Piece
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
                                    if(indRegleSyntaxe>=4) {
                                        switch (parcours) {
                                            //en passant par 20 (piecetoken)
                                            case "15162022" -> {
                                                // Joueur+ConsequenceAction+PieceToken
                                                throw new MauvaiseSemantiqueRegleException("Bloc Joueur-ConsequenceAction-PieceToken inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }
                                            case "15172022" -> {
                                                // Piece+ConsequenceAction+PieceToken
                                                if (regleString.get(indRegleSyntaxe - 1).equals("prendre")) {
                                                    if (1 == 1) ;
                                                } else {
                                                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-PieceToken inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                                }
                                            }
                                            case "1517182022" -> {
                                                // Joueur+Piece+ConsequenceAction+PieceToken
                                                if (regleString.get(indRegleSyntaxe - 1).equals("prendre")) {
                                                    if (2 == 2) ;
                                                } else {
                                                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-ConsequenceAction-PieceToken inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                                }
                                            }
                                            case "15182022" -> {
                                                // PieceToken+ConsequenceAction+PieceToken
                                                if (regleString.get(indRegleSyntaxe - 1).equals("prendre")) {
                                                    if (3 == 3) ;
                                                } else {
                                                    throw new MauvaiseSemantiqueRegleException("Bloc PieceToken-ConsequenceAction-PieceToken inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                                }
                                            }

                                            //en passant par 21 (piece + joueur)
                                            //en passant par 20 (20-21)(piece)
                                            case "1516202122" -> {
                                                // Joueur+ConsequenceAction+Piece+Joueur
                                                throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-Piece-Joueur inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }
                                            case "1517202122" -> {
                                                // Piece+ConsequenceAction+Piece+Joueur
                                                if (regleString.get(indRegleSyntaxe - 2).equals("prendre")) {
                                                    if (4 == 4) ;
                                                } else {
                                                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-ConsequenceAction-Piece-Joueur inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                                }
                                            }
                                            case "151718202122" -> {
                                                // Piece+Joueur+ConsequenceAction+Piece+Joueur
                                                if (regleString.get(indRegleSyntaxe - 2).equals("prendre")) {
                                                    if (5 == 5) ;
                                                } else {
                                                    throw new MauvaiseSemantiqueRegleException("Bloc Piece-Joueur-ConsequenceAction-Piece-Joueur inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                                }
                                            }
                                            case "1518202122" -> {
                                                // PieceToken+ConsequenceAction+Piece+Joueur
                                                if (regleString.get(indRegleSyntaxe - 2).equals("prendre")) {
                                                    if (6 == 6) ;
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
                                                if (regleString.get(indRegleSyntaxe - 2).equals("placer")) {
                                                    if (7 == 7) ;
                                                } else {
                                                    throw new MauvaiseSemantiqueRegleException("Bloc Joueur-ConsequenceAction-Case-PieceToken inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                                }
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
                                    if(indRegleSyntaxe>=4) {
                                        switch (parcours) {
                                            case "15162023" -> {
                                                // Joueur+ConsequenceAction+Case
                                                throw new MauvaiseSemantiqueRegleException("Bloc Joueur-ConsequenceAction-Case inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]");
                                            }
                                            case "15172023" -> {/*prendre + promouvoir*/
                                                // Piece+ConsequenceAction+Case
                                                switch (regleString.get(indRegleSyntaxe - 1)) {
                                                    case "prendre" -> {
                                                    }
                                                    case "promouvoir" -> {
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
                                                    }
                                                    case "promouvoir" -> {
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
                                                    }
                                                    case "promouvoir" -> {
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
                                    }
                                }

                                default -> { throw new MauvaiseSemantiqueRegleException("Bloc inconnu [" + getMessageErreur(indRegleSyntaxe,regleSyntaxe,regleString) + "]"); }
                            }
                        }

                    }
                }//sinon continuer à l'état suivant

            }else{
                //Erreur Etat Inconnu
            }
        }

        // si sort de la boucle: erreur car etat final non terminal
        return blocs;
    }
}
