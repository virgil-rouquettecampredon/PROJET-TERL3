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
        conséquences.add(cons);
    }

    public void evaluer(){
        for (Condition co: conditions) {
            if(!co.verification()){
                return;
            }
        }
        for (Consequence cons: conséquences) {
            cons.comportement();
        }
    }

    public void evaluer2(){
        boolean resultCond = true;
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
        }
    }

}
