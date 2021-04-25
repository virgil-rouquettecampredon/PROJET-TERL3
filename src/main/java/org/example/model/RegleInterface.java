package org.example.model;

import org.example.model.Regles.ElementRegle;

import java.io.Serializable;
import java.util.ArrayList;

public class RegleInterface implements Serializable {
    private ArrayList<ElementRegle> regle;      // Liste d'elements de regle
    private boolean traitementAvantCoup;        // Vrai ssi la regle est à traiter avant le tour du joueur

    public RegleInterface() {
        this.regle = new ArrayList<>();
        traitementAvantCoup = true;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (ElementRegle element : regle) {
            if (element.getJetonAssocie() == Jeton_Interface.ALIAS) {
                s.append(element.getNomRegle());
            }
            else {
                s.append(element.getNomInterface());
            }
            s.append(" ");
        }
        return s.toString();
    }

    public ArrayList<ElementRegle> getRegle() {
        return regle;
    }

    public void setRegle(ArrayList<ElementRegle> regle) {
        this.regle = regle;
    }

    public boolean isTraitementAvantCoup() {
        return traitementAvantCoup;
    }

    public void setTraitementAvantCoup(boolean traitementAvantCoup) {
        this.traitementAvantCoup = traitementAvantCoup;
    }
}
