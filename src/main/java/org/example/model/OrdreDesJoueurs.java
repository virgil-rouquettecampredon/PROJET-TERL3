package org.example.model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class OrdreDesJoueurs implements Supplier<Integer> {
    /*Classe permettaant de définir un ordonnanceur pour les Joueurs*/

    public static final String separateur = "J";        //Separateur standart de définition d'une chaine d'ordre de Joueur
    private String ordreStr;                            //Chaine renseignant l'ordre des joueurs au nivaeu du système
    private int curseur;                                //Curseur de parcours de la chaine ordreStr
    private int nbJoueur;                               //Nombre de joueurs de la variante
    private List<String> ordreSepare;                   //Liste de chaine renseignant l'ordre renseigné en ne récupérant que les valeurs d'indicage

    public OrdreDesJoueurs(String ordreStr, int nbJoueur) {
        this.ordreStr = ordreStr;
        this.nbJoueur = nbJoueur;
        this.curseur = 0;

        ordreSepare = new ArrayList<>();
        Collections.addAll(ordreSepare, ordreStr.split(separateur));
        ordreSepare.remove(0);
    }

    /**Méthode permettant de verifier si une séquence est valide ou pas pour définir un ordre des joueurs.
     * @param ordreStr : chaine à analyser renseignant un ordre de jeu pour les joueurs de la variante.
     * @param nbJoueur : nombre de joueurs présents sur la variante.**/
    public static void verifierOrdre(String ordreStr, int nbJoueur) throws OrdreDesJoueursException{
        boolean[] tabPresent = new boolean[nbJoueur];
        for (boolean b: tabPresent){ b=false; }

        //Valeur de l'indice du joueur renseigné
        int valueChar = 0;
        //Indice courant du parcours de la chaine
        int curIndice = 0;
        String analyserString = ordreStr;

        //Tant que l'on a pas fini de lire la chaine et qu'elle possède encore des éléments à traiter
        while(analyserString.length()>0) {
            curIndice = analyserString.indexOf(separateur) + 1;
            analyserString = analyserString.substring(curIndice);

            String j;
            if (analyserString.contains(separateur)) {
                j = analyserString.substring(0, analyserString.indexOf(separateur));
            } else {
                j = analyserString;
                analyserString = "";
            }

            //Analyser la sous-chaine en tant que indice de joueurs
            try {
                valueChar = Integer.parseInt(j);
                if (valueChar <= nbJoueur && valueChar > 0) {
                    tabPresent[valueChar - 1] = true;
                } else {
                    throw new OrdreDesJoueursException("Joueur renseigné invalide : ", valueChar);
                }
            } catch (NumberFormatException e) {
                throw new OrdreDesJoueursException("Identificateur de Joueur invalide : " + j);
            }
        }

        for (int i = 0; i<nbJoueur;i++){
            if(!tabPresent[i]){
                throw new OrdreDesJoueursException("Joueur non renseigné dans la chaine : ", (i+1));
            }
        }
    }

    public Integer get(){
        int i = Integer.parseInt(ordreSepare.get(curseur));
        curseur = (curseur+1)%ordreSepare.size();
        return i;
    }
}