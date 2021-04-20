

package org.example.model.Regles.Structure.Interpreteur;

import java.util.List;
import java.util.ArrayList;
import org.example.model.Joueur;
import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Piece;

public class InterpreteurSujetPiece extends InterpreteurSujet<Piece> {

    private String str_source;
    private List<Joueur> proprios;

    public InterpreteurSujetPiece(String str_source) {
        this.str_source = str_source;
    }

    public InterpreteurSujetPiece(String str_joueur, String str_type) {
        this.str_source = str_joueur + "#" + str_type;
    }

    // PN / PALL
    // JN#PN
    /**
     * @param ord : toutes les pieces présentes sur le plateau de jeu*/
    public List<Piece> recupererTout(OrdonnanceurDeJeu ord) {
        List<Piece> lret;
        if (this.str_source.contains("#")){
            lret = new ArrayList<>();

            String[] str_proprio_type = this.str_source.split("#");
            List<Piece> paflitrer = convertPiece(str_proprio_type[0], ord);

            InterpreteurSujetJoueur sujj = new InterpreteurSujetJoueur(str_proprio_type[1]);
            List<Joueur> proprios = sujj.recupererTout(ord);

            for (Piece p: paflitrer){
                if (proprios.contains(p.getJoueur())){ lret.add(p); }
            }
            return lret;

        } else {
            lret = convertPiece(this.str_source, ord);
            return lret;    //peut retourner null !
        }
    }

    public List<Piece> convertPiece(String str, OrdonnanceurDeJeu ord){
        List<Piece> lret = new ArrayList<>();
        if (this.str_source.charAt(0) == 'P' && this.str_source.length() >= 2) {
            if (this.str_source.equals("PALL")) {
                lret.addAll(ord.getPieces());
                return lret;
            } else {
                String nompiece = ord.getTypePiece(Integer.parseInt(this.str_source.substring(1)));
                for (Piece p: ord.getPieces()){
                    if (p.getName().equals(nompiece)){ lret.add(p); }
                }
                return lret;
            }
        }
        return null;
    }

}
