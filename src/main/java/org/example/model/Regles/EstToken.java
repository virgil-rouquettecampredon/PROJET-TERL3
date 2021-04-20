package org.example.model.Regles;

import java.io.Serializable;

public interface EstToken{
    /**Méthode permettant à un élément implémentant EstToken et la redéfinissant d'indiquer s'il peut reconnaitre la chaine s.
     * @param s : chaine de caractère dont il faut indiquer la reconnaissance ou non.
     * @return true si s est bien reconnu par this, et false sinon.**/
    boolean estReconnu(String s);
}
