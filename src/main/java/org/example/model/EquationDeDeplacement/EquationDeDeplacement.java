package org.example.model.EquationDeDeplacement;

import org.example.model.Position;

import java.io.Serializable;
import java.util.Objects;

public abstract class EquationDeDeplacement implements Serializable {
    private boolean evaluable;
    private int x = 0;
    private int y = 0;
    private TypeDeplacement typeDeplacement;

    /**Constructeur d'une équation de déplacement.
     * @param evaluable : booleaan renseignant si l'équation de déplacement est évaluable ou non.
     * @param x : int renseignant la valeur à ajouter à l'axe X d'une position quelconque afin d'enobtenir une nouvelle, conforme à l'équation de déplacement.
     * @param y : int renseignant la valeur à ajouter à l'axe Y d'une position quelconque afin d'enobtenir une nouvelle, conforme à l'équation de déplacement.
     * **/
    public EquationDeDeplacement(boolean evaluable, int x, int y, TypeDeplacement typeDeplacement) {
        this.x =x;
        this.y =y;
        this.evaluable = evaluable;
        this.typeDeplacement = typeDeplacement;
    }

    /**Fonction permettant d'évaluer une équation de déplacement à partir d'une position de départ.
     * @param depart : Position à prendre en compte afin de retourner une nouvelle position théoriquement atteignable d'après l'équation de déplacement.
     * **/
    public Position evaluate(Position depart){
        return new Position(depart.getX()+x,depart.getY()+y);
    }

    //Fonction permettant de savoir si une équation de déplacement est évaluable ou non
    public boolean canEvaluate(){
        return this.evaluable;
    }

    /*DEBUT GETTER SETTER*/
    public void setEvaluable(boolean evaluable) {
        this.evaluable = evaluable;
    }

    public TypeDeplacement getTypeDeplacement() {
        return typeDeplacement;
    }

    //test Only
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    /*FIN GETTER SETTER*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquationDeDeplacement that = (EquationDeDeplacement) o;
        return evaluable == that.evaluable && x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(evaluable, x, y);
    }

    public static enum TypeDeplacement {
        DEPLACER,
        PRENDRE,
        BOTH
    }
}


