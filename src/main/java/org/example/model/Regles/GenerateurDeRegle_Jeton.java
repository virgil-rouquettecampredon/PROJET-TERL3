package org.example.model.Regles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenerateurDeRegle_Jeton extends GenerateurDeRegle<Jeton>{

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

    public List<Jeton> analyseSyntaxique(List<String> regle) throws MauvaiseSyntaxeRegleException{
        ArrayList<Jeton> regleSousFormeJeton = new ArrayList<>();

        for (int i = 0; i<regle.size();i++){
            Jeton curJeton;
            String curRegle = regle.get(i);

            try {
                while(curRegle.length()>0 && estReconnu(curRegle.charAt(0) + "").equals(Jeton.NON)){
                    regleSousFormeJeton.add(Jeton.NON);
                    curRegle = curRegle.substring(1);
                }
                switch ((curRegle.length() > 0)? curRegle.charAt(0) : ' ') {
                    /*CAS PIECE*/
                    case 'P' -> {
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
                            if(curJeton.equals(Jeton.ALIAS)){
                                regle.remove(i);
                            }
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
                                    case "tous-typecase" ->{
                                        regle.set(i,"CALL");
                                        curJeton = Jeton.CASE;
                                    }
                                    default -> {
                                        throw new MauvaiseSyntaxeRegleException("Macro TOUS inconnu");
                                    }
                                }
                            }
                        }else{
                            throw new MauvaiseSyntaxeRegleException("Axiome inconnu");
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

                //Va permettre de déterminer à quel type de règle on a à faire
                // 0 : Regle avant le coup d'un joueur
                // 1 : Regle après le coup d'un joueur
                if(premier.equals("0") || premier.equals("1")) {
                    try{
                        regleSousFormeDejetons = analyseSyntaxique(regleSousFormeDeChaine);
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
}
