package org.example.model.Regles;

import java.util.List;
import java.util.ArrayList;
import org.example.model.Joueur;
import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Piece;

public class TraducteurSujetJoueur extends Sujet<Joueur> {

    private String str_source;

    public TraducteurSujetJoueur(String str_source) {
        this.str_source = str_source;
    }

    public List<Joueur> recupererTout(OrdonnanceurDeJeu ord) {
        List<Joueur> lret = new ArrayList<>();
        if (this.str_source.charAt(0) == 'J' && this.str_source.length() >= 2) {
            if (this.str_source.equals("JALL")) {
                lret.addAll(ord.getJoueurs());
                return lret;
            } else {
                lret.add(ord.getJoueur(Integer.parseInt(this.str_source.substring(1))));
                return lret;
            }
        }
        return null;
    }

}
