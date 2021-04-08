package org.example.model.Regles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Regle implements Serializable {
    private List<Condition> conditions;
    private List<Consequence> conséquences;

    public Regle() {
        conditions = new ArrayList<>();
        conséquences = new ArrayList<>();
    }

    public void ajouterUneCondition(Condition cond){
        conditions.add(cond);
    }

    public void ajouterUneConsequence(Consequence cons){
        consequences.add(cons);
    }


    public void genererArbreCondition(List<Condition> conditions,List<Jeton> jetons) throws MauvaiseDefinitionRegleException{
        try{
            arbre_conditions = new Arbre_Condition(jetons,conditions);
            arbre_conditions.evaluer();
        }catch (ArbreException e){
            throw new MauvaiseDefinitionRegleException("Regle : Impossible de générer l'arbre de Condition : " + e.getMessage());
        }
    }


    public void analyser() throws MauvaiseDefinitionRegleException{
        try{
            if(arbre_conditions.evaluer()){
                for (Consequence cons:consequences) {
                    cons.comportement();
                }
            }
        }catch (ArbreException e){
            throw new MauvaiseDefinitionRegleException("Problème dans l'évaluation de la règle : " + e.getMessage());
        }

    }

    public void evaluer2(){
        /*boolean resultCond = true;
        //initialisation inutile de résult, permet juste pour compiler, la vrai première valeur de result sera déterminé ensuite
        int ind = 0;
        for (Condition co: conditions) {
            if (ind == 0){
                resultCond = co.verification();
            } else {
                if (co.getConnecteur().equals(Jeton.OU)){
                    resultCond = resultCond || co.verification();
                } else if (co.getConnecteur().equals(Jeton.ET)) {
                    resultCond = resultCond && co.verification();
                }
            }
            ind++;
        }

        if(resultCond) {
            for (Consequence cons : conséquences) {
                cons.comportement();
            }
        }*/
    }

}
