package org.example.model.Regles;

import java.util.List;

public abstract class TraducteurCible<T extends CibleDeRegle> implements Traducteur_Objet_Regle {

    public List<T> recupererTout(/*List<T> list*/) {/*A IMPLEMENTER*/ return null; }
}
