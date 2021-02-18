public class Case{
    private String sprite;
    private Position position;
    private Piece pieceOnCase;

    public Case(String sprite, int x, int y) {
        this.sprite = sprite;
        this.position.setY(y);
        this.position.setX(x);
        this.pieceOnCase = null;
    }

    public String getSprite() {
        return sprite;
    }

    public Position getPosition() {
        return position;
    }

    public Piece getPieceOnCase() {
        return pieceOnCase;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public void setPieceOnCase(Piece pieceOnCase) {
        this.pieceOnCase = pieceOnCase;
    }
}