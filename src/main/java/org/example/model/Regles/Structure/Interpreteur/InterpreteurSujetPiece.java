

package org.example.model.Regles.Structure.Interpreteur;

import java.util.List;
import java.util.ArrayList;
import java.util.MissingFormatArgumentException;

import org.example.model.Joueur;
import org.example.model.OrdonnanceurDeJeu;
import org.example.model.Piece;

public class InterpreteurSujetPiece extends InterpreteurSujet<Piece> {

    private String str_source;
    private List<Joueur> proprios;

    public InterpreteurSujetPiece(String str_source) {
        this.str_source = str_source;
    }

    public InterpreteurSujetPiece(String str_type, String str_joueur) {
        this.str_source = str_type + "#" + str_joueur;
    }

    // PN / PALL
    // JN#PN
    /**
     * @param ord : toutes les pieces présentes sur le plateau de jeu*/
    @Override
    public List<Piece> recupererTout(OrdonnanceurDeJeu ord) throws MauvaiseInterpretationObjetRegleException {
        List<Piece> lret;
        String erreurPiece = "'" + this.str_source + "': ";
        if (this.str_source.contains("#")){
            try {
                erreurPiece = "Piece + Joueur: " + erreurPiece;
                lret = new ArrayList<>();

                String[] str_proprio_type = this.str_source.split("#");
                if (str_proprio_type.length < 2) { throw new MauvaiseInterpretationObjetRegleException("Format Piece + Joueur: '#' vide"); }
                List<Piece> paflitrer = convertPiece(str_proprio_type[0], ord);

                InterpreteurSujetJoueur sujj = new InterpreteurSujetJoueur(str_proprio_type[1]);
                List<Joueur> proprios = sujj.recupererTout(ord);

                for (Piece p : paflitrer) {
                    if (proprios.contains(p.getJoueur())) {
                        lret.add(p);
                    }
                }
                return lret;
            } catch (MauvaiseInterpretationObjetRegleException m) {
                throw new MauvaiseInterpretationObjetRegleException(erreurPiece + m.getMessage());
            }

        } else {
            lret = convertPiece(this.str_source, ord);
            return lret;
        }
    }

    public List<Piece> convertPiece(String str, OrdonnanceurDeJeu ord) throws MauvaiseInterpretationObjetRegleException{
        List<Piece> allpieces = new ArrayList<>();
        String erreurPiece = "Piece: " + "'" + str + "': ";
        if (this.str_source.charAt(0) == 'P' && str.length() >= 2) {
            for(Joueur j: ord.getJoueurs()) { allpieces.addAll(j.getPawnList()); }

            if (str.equals("PALL")) {
                return allpieces;
            } else {
                try {
                    List<Piece> lret = new ArrayList<>();
                    Piece piece_reference = ord.getListTypesPieces().get(Integer.parseInt(str.substring(1)));
                    for(Piece p: allpieces){
                        if(p.equals(piece_reference)) { lret.add(p); }
                    }
                    return lret;
                } catch (NumberFormatException ne) {
                    throw new MauvaiseInterpretationObjetRegleException(erreurPiece + "Entier imparsable (NumberFormatException)");
                } catch (IndexOutOfBoundsException ie) {
                    throw new MauvaiseInterpretationObjetRegleException(erreurPiece + "numéro piece inconnu");
                }
            }
        }
        throw new MauvaiseInterpretationObjetRegleException(erreurPiece + "Format incorrect (syntaxe de la forme PALL ou PN où N est un entier positif)");
        //return null;
    }

}
