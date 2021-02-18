import java.util.ArrayList;

public class Joueur {

    private String name;
    private int equipe;
    private ArrayList<Piece> graveyard;
    private int timer;

    public Joueur(String name, int equipe) {
        this.name = name;
        this.equipe = equipe;
        this.graveyard = new ArrayList<Piece>();
        this.timer = -1;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEquipe(int equipe) {
        this.equipe = equipe;
    }

    public void setGraveyard(ArrayList<Piece> graveyard) {
        this.graveyard = graveyard;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public String getName() {
        return name;
    }

    public int getEquipe() {
        return equipe;
    }

    public ArrayList<Piece> getGraveyard() {
        return graveyard;
    }

    public int getTimer() {
        return timer;
    }
}
