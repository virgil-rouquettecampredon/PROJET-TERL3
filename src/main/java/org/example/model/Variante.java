package org.example.model;

import org.example.model.Regles.ElementRegle;
import org.example.model.Regles.GenerateurDeRegle;
import org.example.model.Regles.GenerateurDeRegle_Jeton;
import org.example.model.Regles.Jeton;
import org.example.model.Regles.Jeton_Interface;
import org.example.model.Regles.Structure.Automate.Automate;
import org.example.model.Regles.Structure.Automate.Automate_Regles;
import org.example.model.Regles.Structure.Automate.Automate_Regles_Semantique;
import org.example.model.Regles.Regle;
import org.example.model.Regles.EstToken;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

public abstract class Variante<A extends EstToken> implements Cloneable{
    private String name;                                // Nom de la variante
    private Plateau plateau;                            // Le plateau correspondant à cette variantes
    private ArrayList<Joueur> joueurs;                  // La liste des joueurs de cette variantes
    private ArrayList<Joueur> ordreJoueurs;             // La liste des joueurs dans l'ordre des tour (avec duplication, etc)

    private ArrayList<GroupCases> listGroupCases;       // La liste de toutes les groupes de cases définies dans cette variante

    private GenerateurDeRegle<A> generateurDeRegle;     // Objet permettant de générer des Regle à partir de Token A quelconques

    public Variante(String name, Plateau plateau, ArrayList<Joueur> joueurs, ArrayList<Joueur> ordreJoueurs, ArrayList<GroupCases> listGroupCases) {
        this.name = name;
        this.plateau = plateau;
        this.joueurs = joueurs;
        this.ordreJoueurs = ordreJoueurs;

        this.listGroupCases = new ArrayList<>();
        this.listGroupCases.add(createGroupCase_ToutesLesCases());
        this.listGroupCases.addAll(listGroupCases);
    }

    public Variante(Variante<A> variante) {
        name = variante.name;
        plateau = new Plateau(variante.plateau);
        joueurs = new ArrayList<>();
        for (Joueur j: variante.getJoueurs()) {
            joueurs.add(new Joueur(j));
        }
        ordreJoueurs = new ArrayList<>(variante.ordreJoueurs);

        listGroupCases = new ArrayList<>(variante.listGroupCases);

        for (Joueur j : joueurs) {
            j.setGraveyard(new ArrayList<>());
        }
    }

    /**
     * Clone en profondeur la variante this dans une nouvelle variante
     * @return une nouvelle variante
     * @throws CloneNotSupportedException si la classe n'implémente pas cloneable
     */
    @Override
    public Variante<A> clone() throws CloneNotSupportedException{
        Variante<A> v = (Variante<A>) super.clone();

        //On clone les joueurs sans leur mettre les pawnList
        v.joueurs = new ArrayList<>();
        for (Joueur j : joueurs) {
            v.joueurs.add(j.clone());
        }

        //On clone le plateau et si il y a une piece on lui met le joueur correspondant et on l'ajoute dans la pawnList
        v.plateau = plateau.clone(v.joueurs);

        //On met les joueurs dans l'ordre des joueurs
        v.ordreJoueurs = new ArrayList<>();
        for (int i = 0; i < ordreJoueurs.size(); i++) {
            Joueur jCorrespondant = null;
            for (Joueur vj : v.joueurs) {
                if (vj.getName().equals(ordreJoueurs.get(i).getName()) && vj.getEquipe() == ordreJoueurs.get(i).getEquipe()) {
                    jCorrespondant = vj;
                    break;
                }
            }
            if (jCorrespondant == null) {
                System.err.println("Pas possible 2");
            }
            else {
                v.ordreJoueurs.add(jCorrespondant);
            }
        }

        //On clone les groupe de case qui recherchent les cases sur le plateau
        v.listGroupCases = new ArrayList<>();
        for (GroupCases gc : listGroupCases) {
            v.listGroupCases.add(gc.clone(v.plateau));
        }

        //todo voir avec ervvan pour le générateur de regle
        return v;
    }

    /**Méthode permettant d'initialiser correctement une variante d'après les paramètres qu'elle a recu à la construction**/
    public abstract void initialiser() throws VarianteException;

    /*Getter et Setter*/
    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(ArrayList<Joueur> joueurs) {
        this.joueurs = joueurs;
    }

    public ArrayList<Joueur> getOrdrejoueur() { return ordreJoueurs;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<GroupCases> getListGroupCases() {
        return listGroupCases;
    }

    public void setListGroupCases(ArrayList<GroupCases> listGroupCases) {
        this.listGroupCases = listGroupCases;
    }

    public GenerateurDeRegle<A> getGenerateurDeRegle(){
        return generateurDeRegle;
    }

    public void setGenerateurDeRegle(GenerateurDeRegle<A> generateurDeRegle){
        this.generateurDeRegle = generateurDeRegle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variante variante = (Variante) o;
        return Objects.equals(name, variante.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    private GroupCases createGroupCase_ToutesLesCases() {
        GroupCases g = new GroupCases("Toutes les cases", this.plateau);
        ArrayList<Case> allcases = new ArrayList<>();
        for (List<Case> lc: this.plateau.getEchiquier()){
            allcases.addAll(lc);
        }
        g.setCasesAbsolue(allcases);
        return g;
    }
}
