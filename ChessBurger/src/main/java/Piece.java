import java.util.ArrayList;

public class Piece{
    private String name;
    private String sprite;
    private int nbMovement;
    private int nbLife;
    private ArrayList<EquationDeDeplacement> deplacements;

    public Piece(String name, String sprite) {
        this.name = name;
        this.sprite = sprite;
        this.nbMovement = 0;
        this.nbLife = -1;
        this.deplacements = new ArrayList<EquationDeDeplacement>();
    }

    public Position[] deplacementTheoriques(){
        // A completer

        return null;
    }

    /*DEBUT GETTER SETTER*/

    public void setName(String name) {
        this.name = name;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public void setNbMovement(int nbMovement) {
        this.nbMovement = nbMovement;
    }

    public void setNbLife(int nbLife) {
        this.nbLife = nbLife;
    }

    public void setDeplacements(ArrayList<EquationDeDeplacement> deplacements) {
        this.deplacements = deplacements;
    }

    public String getName() {
        return name;
    }

    public String getSprite() {
        return sprite;
    }

    public int getNbMovement() {
        return nbMovement;
    }

    public int getNbLife() {
        return nbLife;
    }

    public ArrayList<EquationDeDeplacement> getDeplacements() {
        return deplacements;
    }

    /*FIN GETTER SETTER*/
}