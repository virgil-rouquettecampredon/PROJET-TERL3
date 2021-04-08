package org.example.model.EquationDeDeplacement;

public class FactoryVecteurDeDeplacement implements FactoryEquationDeDeplacement {

    private String equation;

    /**Constructeur d'une factory VecteurDeDeplacement.
     * @param equation: String renseignant l'équation permettant de construire un VecteurDeDeplacement**/
    public FactoryVecteurDeDeplacement(String equation){
        this.equation = equation;
    }

    public EquationDeDeplacement createEquationDeDeplacement() throws MauvaiseImplementationVecteurDeDeplacementException {
        String[] equationRes = this.equation.split(separateur);
        if(equationRes.length<2){
            throw new MauvaiseImplementationVecteurDeDeplacementException("Pas assez d'arguments de définition (x###y)");
        }else{
            if(equationRes.length>2) {
                throw new MauvaiseImplementationVecteurDeDeplacementException("Trop d'arguments de définition (x###y)");
            }else{
                int valX,valY;
                try {
                    valX = Integer.parseInt(equationRes[0]);
                }catch (NumberFormatException e){
                    throw new MauvaiseImplementationVecteurDeDeplacementException("Valeur non numérique pour x : " + equationRes[0]);
                }
                try {
                    valY = Integer.parseInt(equationRes[1]);
                }catch (NumberFormatException e){
                    throw new MauvaiseImplementationVecteurDeDeplacementException("Valeur non numérique pour y : " + equationRes[1]);
                }
                return new VecteurDeDeplacement(valX,valY, EquationDeDeplacement.TypeDeplacement.BOTH);
            }
        }
    }
}
