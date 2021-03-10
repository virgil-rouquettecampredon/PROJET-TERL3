package org.example.model;

public class Coup {

    private boolean mange;
    private Piece piece;
    private Position posDepart;
    private Position posArrivee;





    /*DEBUT GETTER SETTER*/

    public void setMange(boolean mange) { this.mange = mange; }
    public void setPiece(Piece piece) { this.piece = piece; }
    public void setPosDepart(Position posDepart) { this.posDepart = posDepart; }
    public void setPosArrivee(Position posArrivee) { this.posArrivee = posArrivee; }

    public boolean isMange() { return mange; }
    public Piece getPiece() { return piece; }
    public Position getPosDepart() { return posDepart; }
    public Position getPosArrivee() { return posArrivee; }
}
