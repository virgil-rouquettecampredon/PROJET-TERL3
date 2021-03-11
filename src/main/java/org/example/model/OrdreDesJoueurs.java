package org.example.model;

public class OrdreDesJoueurs {

    private String ordreStr;
    private int curseur;
    private int nbJoueur;

    public OrdreDesJoueurs(String ordreStr, int nbJoueur) throws OrdreDesJoueursException {
        this.ordreStr = ordreStr;
        this.nbJoueur = nbJoueur;
        this.curseur = 0;
        verifierOrdre();
    }

    public boolean verifierOrdre() throws OrdreDesJoueursException{
        boolean[] tabPresent = new boolean[this.nbJoueur];
        int valueChar = 0;
        for (boolean b: tabPresent){ b=false; }

        try {
            for (char c : this.ordreStr.toCharArray()) {
                valueChar = Integer.parseInt(String.valueOf(c));
                if (valueChar <= this.nbJoueur) {
                    tabPresent[valueChar] = true;
                } else {
                    throw new OrdreDesJoueursException();
                }
            }
            for (boolean b: tabPresent){
                if (b == false){ throw new OrdreDesJoueursException(); }
            }
        } catch (Exception e) {
            throw new OrdreDesJoueursException();
        }
        return true;
    }
}