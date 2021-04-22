package org.example.model;

public class OrdreDesJoueursException extends Exception {
    //Exception liée à un ordonnanceur de joueurs (pour connaitre le prochain joueur)

    private String message;
    private int indice;

    public OrdreDesJoueursException() { super(); }

    public OrdreDesJoueursException(String s) {
        super();
        this.indice = -1;
        this.message = s;
    }

    public OrdreDesJoueursException(String s, int indice) {
        super();
        this.indice = indice;
        this.message = s;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getIndice() {
        return indice;
    }
}