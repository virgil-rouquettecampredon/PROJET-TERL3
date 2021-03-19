package org.example.model;


public class OrdreDesJoueurs {
    private String ordreStr;
    private int curseur;
    private int nbJoueur;

    public OrdreDesJoueurs(String ordreStr, int nbJoueur) {
        this.ordreStr = ordreStr;
        this.nbJoueur = nbJoueur;
        this.curseur = 0;
        //this.verifierOrdre();
    }

    public static void verifierOrdre(String ordreStr, int nbJoueur) throws OrdreDesJoueursException{
        boolean[] tabPresent = new boolean[nbJoueur];
        int valueChar = 0;
        for (boolean b: tabPresent){ b=false; }
        try {
            for (char c : ordreStr.toCharArray()) {
                //NumberFormatException
                valueChar = Integer.parseInt(String.valueOf(c));
                if (valueChar <= nbJoueur && valueChar>0) {
                    tabPresent[valueChar-1] = true;
                } else {
                    throw new OrdreDesJoueursException("Joueur renseigné invalide.");
                }
            }
            int indice = 1;
            for (boolean b: tabPresent){
                if (b == false){
                    throw new OrdreDesJoueursException("Joueur non présent dans la séquence de jeu : " + indice);
                }
                indice++;
            }

        }catch(OrdreDesJoueursException e){
            throw e;
        }catch (Exception e) {
            throw new OrdreDesJoueursException("Nom de joueur renseigné innaproprié.");
        }
    }

    public int getProchainJoueur(){
        this.curseur = (curseur + 1)% ordreStr.length();
        return ordreStr.charAt(curseur);
    }
}