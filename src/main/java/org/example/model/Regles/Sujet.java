package org.example.model.Regles;

import java.util.ArrayList;
import java.util.List;

public abstract class Sujet<T extends SujetDeRegle> {

    public ArrayList<T> recupererTout(/*List<T> list*/) {/*A IMPLEMENTER*/ return null; }
}








    /*private String tokenTypePiece;               //
    private ArrayList<Joueur> proprietaire;  //

    public Sujet(String tokenTypePiece, ArrayList<Joueur> proprietaire) {
        this.tokenTypePiece = tokenTypePiece;
        this.proprietaire = new ArrayList<>();
    }*/

    // OU abstract ou interface
    //public ArrayList<E> getElements();
    /*
    public TokenPiece implements Token<E> {
        private String type;
        private ArrayList<Joueur> proprietaire;

        public TokenPiece(String type, ArrayList<Joueur> proprietaire){
            this.type = type;
            this.proprietaire = propritaire;
        }

        //Pieces
        public ArrayList<E> getElements(){
            ArrayList<Piece> piecesret = new ArrayList<>();
            for(Joueur j: this.proprietaire){
                for(Piece p: j.getPawnList()){
                    if(p.getName().equals("tous")){
                        piecesret.add(p);
                    } else {
                        if(p.getName().equals(this.type)){
                            piecesret.add(p);
                        }
                    }
                }
            }
            return piecesret;
        }

        //Joueur
        public ArrayList<E> getElements(Ordonnanceur o){
            ArrayList<Joueur> joueurret = new ArrayList<>();
            for(Joueur j: this.proprietaire){
                joueurret.add(j);
            }
            return joueurret;
        }
    }
    */

