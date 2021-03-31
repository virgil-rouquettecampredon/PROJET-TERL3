package org.example.model;

import javafx.beans.property.SimpleStringProperty;

public class JoueurBox {
    private final SimpleStringProperty name;
    private final Joueur joueur;

    public JoueurBox(String name, Joueur joueur) {
        this.name = new SimpleStringProperty(name);
        this.joueur = joueur;
    }

    @Override
    public String toString() {
        return getName();
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public Joueur getJoueur() {
        return joueur;
    }
}
