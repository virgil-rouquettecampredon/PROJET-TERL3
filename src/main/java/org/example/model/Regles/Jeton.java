package org.example.model.Regles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Jeton implements Serializable, EstToken {
    //Jeton particulier si rien n'est reconnu
    AUCUN("aucun") {
        @Override
        public boolean estReconnu(String s) {
            return false;
        }
    },

    //Jetons particuliers pour les ObjetsDeRegle
    PIECE("piece") {
        @Override
        public boolean estReconnu(String piece) {
            if (piece.length() > 1) {
                if (piece.charAt(0) == 'P') {
                    if (piece.length() >= 4 && piece.charAt(1) == 'A') {
                        if (piece.charAt(2) == 'L') {
                            if (piece.charAt(3) == 'L') {
                                if (piece.length() == 4) {
                                    return true;
                                } else {
                                    PIECE.messageErreur = "Syntaxe de piece (PALL + mauvais carac) incorrecte";
                                }
                            } else {
                                PIECE.messageErreur = "Syntaxe de piece (PAL + mauvais carac) incorrecte";
                            }
                        } else {
                            PIECE.messageErreur = "Syntaxe de piece (PA + mauvais carac) incorrecte";
                        }
                    } else {
                        try {
                            int nb = Integer.parseInt(piece.substring(1));
                            if (nb < PIECE.borne[0]) {
                                if (nb >= 0) {
                                    return true;
                                } else {
                                    PIECE.messageErreur = "Syntaxe de piece (numero trop petit) incorrecte";
                                }
                            } else {
                                PIECE.messageErreur = "Syntaxe de piece (numero trop grand) incorrecte";
                            }
                            return false;
                        } catch (NumberFormatException e) {
                            PIECE.messageErreur = "Syntaxe de piece (numero) incorrecte, un entier positif est requis";
                            return false;
                        }
                    }
                } else {
                    PIECE.messageErreur = "Syntaxe de piece incorrecte (doit commencer par P)";
                }
            } else {
                PIECE.messageErreur = "Syntaxe de piece incorrecte (syntaxe de la forme PALL ou PN où N est un entier positif)";
            }
            return false;
        }
    },
    JOUEUR("joueur") {
        @Override
        public boolean estReconnu(String joueur) {
            if (joueur.length() > 1) {
                if (joueur.charAt(0) == 'J') {
                    if (joueur.length() >= 4 && joueur.charAt(1) == 'A') {
                        if (joueur.charAt(2) == 'L') {
                            if (joueur.charAt(3) == 'L') {
                                if (joueur.length() == 4) {
                                    return true;
                                } else {
                                    JOUEUR.messageErreur = "Syntaxe du joueur (JALL + mauvais carac) incorrecte";
                                }
                            } else {
                                JOUEUR.messageErreur = "Syntaxe du joueur (JAL + mauvais carac) incorrecte";
                            }
                        } else {
                            JOUEUR.messageErreur = "Syntaxe du joueur (JA + mauvais carac) incorrecte";
                        }
                    } else {
                        try {
                            int nb = Integer.parseInt(joueur.substring(1));
                            if (nb < JOUEUR.borne[0]) {
                                if (nb >= 0) {
                                    return true;
                                } else {
                                    JOUEUR.messageErreur = "Syntaxe du joueur (numero trop petit) incorrecte";
                                }
                            } else {
                                JOUEUR.messageErreur = "Syntaxe du joueur (numero trop grand) incorrecte";
                            }
                            return false;

                        } catch (NumberFormatException e) {
                            JOUEUR.messageErreur = "Syntaxe du joueur (numero) incorrecte, un entier positif est requis";
                            return false;
                        }
                    }
                } else if (joueur.charAt(0) == 'E') {
                    try {
                        int nb = Integer.parseInt(joueur.substring(1));
                        if (nb < JOUEUR.borne[1]) {
                            if (nb >= 0) {
                                return true;
                            } else {
                                JOUEUR.messageErreur = "Syntaxe de l'équipe (numero trop petit) incorrecte";
                            }
                        } else {
                            JOUEUR.messageErreur = "Syntaxe de l'équipe (numero trop grand) incorrecte";
                        }
                        return false;

                    } catch (NumberFormatException e) {
                        JOUEUR.messageErreur = "Syntaxe de l'équipe (numero) incorrecte, un entier positif ou nul est requis";
                        return false;
                    }

                } else {
                    JOUEUR.messageErreur = "Syntaxe du joueur incorrecte (doit commencer par J ou E)";
                }
            } else {
                JOUEUR.messageErreur = "Syntaxe du joueur incorrecte (syntaxe de la forme JALL, JN ou EN où N est un entier positif)";
            }
            return false;
        }
    },
    CASE("case") {
        @Override
        public boolean estReconnu(String cases) {
            if (cases.length() > 1) {
                if (cases.charAt(0) == 'C') {
                    if (cases.length() >= 4 && cases.charAt(1) == 'A') {
                        if (cases.charAt(2) == 'L') {
                            if (cases.charAt(3) == 'L') {
                                if (cases.length() == 4) {
                                    return true;
                                } else {
                                    CASE.messageErreur = "Syntaxe de case (CALL + mauvais carac) incorrecte";
                                }
                            } else {
                                CASE.messageErreur = "Syntaxe de case (CAL + mauvais carac) incorrecte";
                            }
                        } else {
                            CASE.messageErreur = "Syntaxe de case (CA + mauvais carac) incorrecte";
                        }
                    } else {
                        try {
                            int nb = Integer.parseInt(cases.substring(1));
                            if (nb < CASE.borne[0]) {
                                if (nb >= 0) {
                                    return true;
                                } else {
                                    CASE.messageErreur = "Syntaxe de case (numero trop petit) incorrecte";
                                }
                            } else {
                                CASE.messageErreur = "Syntaxe de case (numero trop grand) incorrecte";
                            }

                        } catch (NumberFormatException e) {
                            CASE.messageErreur = "Syntaxe de case (numero) incorrecte";
                        }
                    }
                } else {
                    CASE.messageErreur = "Syntaxe de case incorrecte (doit commencer par C)";
                }
            } else {
                CASE.messageErreur = "Syntaxe de case incorrecte";
            }
            return false;
        }
    },

    PIECETOKEN("piece token") {
        @Override
        public boolean estReconnu(String piece) {
            String[] sousPiece = piece.split("#");
            if (sousPiece.length == 2) {
                boolean p = PIECE.estReconnu(sousPiece[0]);
                if (p) {
                    boolean j = JOUEUR.estReconnu(sousPiece[1]);
                    if (j) {
                        return true;
                    }
                    PIECETOKEN.messageErreur = "Syntaxe de pieceToken incorrecte [" + JOUEUR.messageErreur + "]";
                } else {
                    PIECETOKEN.messageErreur = "Syntaxe de pieceToken incorrecte [" + PIECE.messageErreur + "]";
                }
            } else {
                PIECETOKEN.messageErreur = ((sousPiece.length>2)? "Trop" : "Pas assez")+ " de parametre de définition d'une pieceToken";
            }
            return false;
        }
    },
    CASEPARAM("case param") {
        @Override
        public boolean estReconnu(String piece) {
            String[] sousPiece = piece.split("#");
            if (sousPiece.length == 2) {
                boolean p = CASE.estReconnu(sousPiece[0]);
                if (p) {
                    boolean j = CASE.estReconnu(sousPiece[1]);
                    if (j) {
                        return true;
                    }
                    CASEPARAM.messageErreur = "Syntaxe de case paramétré incorrecte (2) [" + CASE.messageErreur + "]";
                } else {
                    CASEPARAM.messageErreur = "Syntaxe de pieceToken incorrecte (1) [" + CASE.messageErreur + "]";
                }
            } else {
                CASEPARAM.messageErreur = ((sousPiece.length>2)? "Trop" : "Pas assez")+ " de parametre de définition d'une case paramétré";
            }
            return false;
        }
    },

    //Jeton pour les macros, à traiter dans le générateur
    TOUS("macro tous","tous-piece", "tous-joueur", "tous-typecase"),

    //Jeton Action ou etat
    ACTION("action","prend","sedeplace","estplace","estsur","estechec"),
    ETAT("etat","estpromu"),
    COMPTEUR("compteur","nb_deplacement","timer"),
    COMPARAISON("comparateur","=" , "<" , ">"),
    NOMBRE("nombre") {
        @Override
        public boolean estReconnu(String s) {
            try {
                int i = Integer.parseInt(s);
                return true;
            } catch (NumberFormatException nfe) {
                NOMBRE.messageErreur = "NaN";
                return false;
            }
        }
    },

    //Jeton connecteur
    ET("et","ET"),
    OU("ou","OU"),
    NON("non","non","NON","N"){
        @Override
        public boolean estReconnu(String s){
            if(s.length()>0 && s.charAt(0) == 'N') return true;
            for (String st: NON.elementsReconnaissables){
                if(s.equals(st)){
                    return true;
                }
            }
            NON.messageErreur = NON.valeur + " non reconnu";
            return false;
        }
    },

    //Jetons référencant les conséquences
    ALORS("alors","alors"),
    CONSEQUENCETERMINALE("consequence terminale","victoire", "defaite", "pat"),
    CONSEQUENCEACTION("consequence action","prendre", "placer", "promouvoir", "deplacer"),

    ALIAS("alias","as"),

    PARENTHESEOUVRANTE("parenthèse ouvrante","("),
    PARENTHESEFERMANTE("parenthèse fermante",")"),
    CONDITION("condition"){
        @Override
        public boolean estReconnu(String s) {
            return false;
        }
    };

    private String valeur;
    private List<String> elementsReconnaissables;
    private String messageErreur;

    /** La borne peut avoir plusieurs valeurs dans le cas du jeton joueur qui traite aussi les équipes
     * où il faut stocker la borne du nombre de joueur et la borne du nombre d'équipe
     * Dans les autres cas le tableau n'a qu'un seul élément. */
    private int[] borne;

    Jeton(String... s){
        this.valeur = s[0];
        elementsReconnaissables = new ArrayList<>();
        for (int i =1; i<s.length;i++){
            elementsReconnaissables.add(s[i]);
        }
        this.borne = new int[2];    //init à 0
        this.messageErreur = "";
    }

    public void setBorne(int b){
        this.borne[0] = b;
    }

    public void setBorne(int b, int index){
        try {
            this.borne[index] = b;
        } catch (ArrayIndexOutOfBoundsException e) {
            this.borne[0] = b;
        }
    }

    public String getMessageErreur(){
        return this.messageErreur;
    }

    public String getValeur() {
        return valeur;
    }

    public int getBorne() {
        return this.borne[0];
    }

    public int getBorne(int index) {
        try {
            return this.borne[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            return this.borne[0];
        }
    }

    public List<String> getElementsReconnaissables() {
        return elementsReconnaissables;
    }

    @Override
    public boolean estReconnu(String s) {
        for (String st: elementsReconnaissables){
            if(s.equals(st)){
                return true;
            }
        }
        messageErreur = valeur + " non reconnu";
        return false;
    }

}
