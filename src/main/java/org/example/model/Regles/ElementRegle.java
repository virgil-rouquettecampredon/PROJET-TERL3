
package org.example.model.Regles;

import java.io.Serializable;
import java.util.Objects;

public class ElementRegle implements Serializable {
    /*Classe permettant de modéliser un élement d'une règle au niveau de l'interface
     *Un ElementRegle permet de faire la jonction entre une Regle au niveau de l'interface et une Regle au niveau du système.*/

    //Nom à afficher au niveau de l'interface
    private String nomInterface;
    //Nom à conserver au niveau du système de jeu.
    //Sert pour la création de la règle au niveau du système.
    private String nomRegle;
    //Jeton lié à cette double nomenclature (~type de l'objet de règle)
    //Sert à regrouper des termes autorisés pour former une Regle
    private Jeton_Interface jetonAssocie;


    public ElementRegle(Jeton_Interface j,String nomInt, String nomRe) {
        this.nomInterface = nomInt;
        this.nomRegle = nomRe;
        this.jetonAssocie = j;
    }

    /*Getter et Setter*/
    public String getNomInterface() {
        return nomInterface;
    }

    public String getNomRegle() {
        return nomRegle;
    }

    public Jeton_Interface getJetonAssocie(){ return this.jetonAssocie;}

    public void setNomInterface(String nomInterface) {
        this.nomInterface = nomInterface;
    }

    public void setNomRegle(String nomRegle) {
        this.nomRegle = nomRegle;
    }

    public void setJetonAssocie(Jeton_Interface jetonAssocie) {
        this.jetonAssocie = jetonAssocie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElementRegle that = (ElementRegle) o;
        return Objects.equals(nomInterface, that.nomInterface) && Objects.equals(nomRegle, that.nomRegle) && jetonAssocie == that.jetonAssocie;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomInterface, nomRegle, jetonAssocie);
    }
}
