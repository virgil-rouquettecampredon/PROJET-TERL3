package org.example.model.EquationDeDeplacement;

import org.example.model.Position;

public abstract class EquationDeDeplacement {
    private boolean evaluable;
    private int x = 0;
    private int y = 0;

    /**Constructeur d'une équation de déplacement.
     * @param evaluable : booleaan renseignant si l'équation de déplacement est évaluable ou non.
     * @param x : int renseignant la valeur à ajouter à l'axe X d'une position quelconque afin d'enobtenir une nouvelle, conforme à l'équation de déplacement.
     * @param y : int renseignant la valeur à ajouter à l'axe Y d'une position quelconque afin d'enobtenir une nouvelle, conforme à l'équation de déplacement.
     * **/
    public EquationDeDeplacement(boolean evaluable, int x, int y){
        this.x =x;
        this.y =y;
        this.evaluable = evaluable;
    }

    /**Constructeur d'une équation de déplacement.
     * @param x : int renseignant la valeur à ajouter à l'axe X d'une position quelconque afin d'enobtenir une nouvelle, conforme à l'équation de déplacement.
     * @param y : int renseignant la valeur à ajouter à l'axe Y d'une position quelconque afin d'enobtenir une nouvelle, conforme à l'équation de déplacement.
     * **/
    public EquationDeDeplacement(int x, int y){
        this.x =x;
        this.y =y;
        this.evaluable = false;
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
    /*FIN GETTER SETTER*/
}


