package org.example.model.EquationDeDeplacement;

public class FactoryPositionDeDeplacement implements FactoryEquationDeDeplacement {
    private String equation;

    /**Constructeur d'une factory VecteurDeDeplacement.
     * @param equation: String renseignant l'équation permettant de construire un VecteurDeDeplacement**/
    public FactoryPositionDeDeplacement(String equation){
        this.equation = equation;
    }

    public EquationDeDeplacement createEquationDeDeplacement() throws MauvaiseImplementationPositionDeDeplacementException {
        String[] equationRes = this.equation.split(separateur);
        if(equationRes.length<2){
            throw new MauvaiseImplementationPositionDeDeplacementException("Pas assez d'arguments de définition (x###y)");
        }else{
            if(equationRes.length>2) {
                throw new MauvaiseImplementationPositionDeDeplacementException("Trop d'arguments de définition (x###y)");
            }else{
                int valX,valY;
                try {
                    valX = Integer.parseInt(equationRes[0]);
                }catch (NumberFormatException e){
                    throw new MauvaiseImplementationPositionDeDeplacementException("Valeur non numérique pour x : " + equationRes[0]);
                }
                try {
                    valY = Integer.parseInt(equationRes[1]);
                }catch (NumberFormatException e){
                    throw new MauvaiseImplementationPositionDeDeplacementException("Valeur non numérique pour y : " + equationRes[1]);
                }
                return new PositionDeDeplacement(valX, valY, EquationDeDeplacement.TypeDeplacement.BOTH);
            }
        }
    }
}
