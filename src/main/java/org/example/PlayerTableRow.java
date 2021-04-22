package org.example;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.example.model.Joueur;

class PlayerTableRow {
    private final SimpleStringProperty name;
    private final SimpleIntegerProperty team;
    private final Joueur joueur;
    private final int id;

    PlayerTableRow(String name, int team, int id) {
        this.name = new SimpleStringProperty(name);
        this.team = new SimpleIntegerProperty(team);
        joueur = new Joueur(name, team);
        this.id = id;
    }

    PlayerTableRow(Joueur p, int id) {
        name = new SimpleStringProperty(p.getName());
        team = new SimpleIntegerProperty(p.getEquipe());
        this.joueur = p;
        this.id = id;
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public int getTeam() {
        return team.get();
    }

    public SimpleIntegerProperty teamProperty() {
        return team;
    }

    public void setTeam(int team) {
        this.team.set(team);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public Joueur getJoueur() { return joueur;}

    @Override
    public String toString() {
        return "PlayerTableRow{" +
                "name=" + name +
                ", team=" + team +
                '}';
    }

    public int getId() {
        return id;
    }
}
