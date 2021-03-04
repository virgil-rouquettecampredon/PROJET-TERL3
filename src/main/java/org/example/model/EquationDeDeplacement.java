package org.example.model;

public class EquationDeDeplacement {

    private String equation;

    public EquationDeDeplacement(String equation) {
        this.equation = equation;
    }

    /**
     * @param nbPosition: int, nbPosition à générer d'après l'évaluation de l'équation à la position de départ de begin
     * @param begin: Position, position de départ de l'évaluation
     * @return Position[], Les positions atteignablent à partir de la position de départ d'après l'évaluation de l'équation et se trouvant avant nbPosition
     **/
    public Position[] evaluer(int nbPosition, Position begin){
        // à compléter

        return null;
    }

    /*DEBUT GETTER SETTER*/

    public void setEquation(String equation) {
        this.equation = equation;
    }

    /*FIN GETTER SETTER*/
}
