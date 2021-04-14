
package org.example.model.Regles;

public class ElementRegle {
    private String nomInterface;
    private String nomRegle;
    private Jeton_Interface jetonAssocie;

    public ElementRegle(Jeton_Interface j,String nomInt, String nomRe) {
        this.nomInterface = nomInt;
        this.nomRegle = nomRe;
        this.jetonAssocie = j;
    }

    public String getNomInterface() {
        return nomInterface;
    }

    public String getNomRegle() {
        return nomRegle;
    }

    public Jeton_Interface getJetonAssocie(){ return this.jetonAssocie;}
}
