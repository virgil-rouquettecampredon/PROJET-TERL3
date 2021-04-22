

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

    public InterpreteurSujetPiece(String str_joueur, String str_type) {
        this.str_source = str_joueur + "#" + str_type;
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
                List<Piece> paflitrer = convertPiece(str_proprio_type[0], ord);

                InterpreteurSujetJoueur sujj = new InterpreteurSujetJoueur(str_proprio_type[1]);
                List<Joueur> proprios = sujj.recupererTout(ord);

                for (Piece p : paflitrer) {
                    if (proprios.contains(p.getJoueur())) {
                        lret.add(p);
                    }
                }
                return lret;
            } catch (MauvaiseInterpretationObjetRegleException m){
                throw new MauvaiseInterpretationObjetRegleException(erreurPiece + m.getMessage());
            }

        } else {
            lret = convertPiece(this.str_source, ord);
            return lret;
        }
    }

    public List<Piece> convertPiece(String str, OrdonnanceurDeJeu ord) throws MauvaiseInterpretationObjetRegleException{
        /*List<Piece> lret = new ArrayList<>();
        String erreurPiece = "Piece: ";
        if (this.str_source.charAt(0) == 'P' && this.str_source.length() >= 2) {
            if (this.str_source.equals("PALL")) {
                lret.addAll(ord.getPlateau().getPieces());
                return lret;
            } else {
                try {
                    String nompiece = ord.getTypePiece(Integer.parseInt(this.str_source.substring(1)));
                    for (Piece p : ord.getPieces()) {
                        if (p.getName().equals(nompiece)) {
                            lret.add(p);
                        }
                    }
                    return lret;
                } catch (NumberFormatException ne) {
                    throw new MauvaiseInterpretationObjetRegleException(erreurPiece + "Entier imparsable (NumberFormatException)");
                } catch (ArrayIndexOutOfBoundsException ie) {
                    throw new MauvaiseInterpretationObjetRegleException(erreurPiece + "numéro piece inconnu");
                }
            }
        }
        throw new MauvaiseInterpretationObjetRegleException(erreurPiece + "Format incorrect (syntaxe de la forme PALL ou PN où N est un entier positif)");*/
        return null;
    }

}
