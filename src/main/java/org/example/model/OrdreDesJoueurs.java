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
                    throw new OrdreDesJoueursException("Joueur renseigné invalide : " + valueChar);
                }
            } catch (NumberFormatException e) {
                throw new OrdreDesJoueursException("Identificateur de Joueur invalide : " + j);
            }
        }

        for (int i = 0; i<nbJoueur;i++){
            if(!tabPresent[i]){
                throw new OrdreDesJoueursException("Joueur non renseigné dans la chaine : " + (i+1));
            }
        }
    }

    public Integer get(){
        int i = Integer.parseInt(ordreSepare.get(curseur));
        curseur = (curseur+1)%ordreSepare.size();
        return i;
    }
}