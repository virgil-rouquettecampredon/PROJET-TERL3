package org.example.model;

import org.example.model.Regles.Jeton;

import java.util.Deque;
import java.util.Queue;

public class EndGameData {
    private Variante<Jeton> variante;
    private Deque<Integer> equipesClasse;

    public EndGameData(Variante<Jeton> variante, Deque<Integer> equipesClasse) {
        this.variante = variante;
        this.equipesClasse = equipesClasse;
    }

    //geteurs setteurs

    public Variante<Jeton> getVariante() {
        return variante;
    }

    public void setVariante(Variante<Jeton> variante) {
        this.variante = variante;
    }

    public Deque<Integer> getJoueursClasse() {
        return equipesClasse;
    }

    public void setJoueursClasse(Deque<Integer> equipesClasse) {
        this.equipesClasse = equipesClasse;
    }
}
