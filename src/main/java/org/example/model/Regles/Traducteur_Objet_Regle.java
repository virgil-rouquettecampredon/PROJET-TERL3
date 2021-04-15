package org.example.model.Regles;

import java.util.List;

public interface Traducteur_Objet_Regle <T extends SujetDeRegle> {

    List<T> recupererTout();
}
