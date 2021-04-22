package org.example.model;

import java.util.List;

public class OrdonnanceurDeJeu {

    //private int nbJoueur;
    private List<Joueur> joueurs;
    //private List<List<Joueur>> equipes;

    private List<String> types_pieces;
    private List<Piece> pieces;
    private Plateau plateau;

    public OrdonnanceurDeJeu(List<Joueur> joueurs, List<Piece> pieces, Plateau plateau){
        this.joueurs = joueurs;
        this.pieces = pieces;
        this.plateau = plateau;
    }

    public List<Joueur> getJoueurs() { return this.joueurs; }

    public Joueur getJoueur(int i) {
        try { return this.joueurs.get(i); }
        catch (ArrayIndexOutOfBoundsException aioobe){ return null; }
    }

    public Plateau getPlateau() { return this.plateau; }

    /**
     * Effectue les vérification et déplace la piece si le déplacement est valide
     * @param origine La Case d'origine du coup
     * @param joueur Le joueur qui fait le coup
     * @param destination La destination du coup
     * @throws Exception Si le coup n'est pas valide. Le message permet d'avoir plus d'information
     */
    public void deplacerPiece(Case origine, Joueur joueur, Case destination) throws Exception {  //todo créer l'exception
        //Appliquer les règles avant coup

        //VERIFIER
        //Verifier qu'il y a une piece et que c'est le bon joueur
        Piece piece = origine.getPieceOnCase();
        if (piece==null || !(piece.getJoueur().equals(joueur))){
            throw new Exception("Pas le bon joueur");
        }

        // Verifier qu'il peut déplacer la piece:
        boolean valide = false;
        System.out.println("Position :");
        for (PositionDeDeplacement pos: piece.getPosDeplacements()) {
            System.out.println(pos.toString());
            valide = verifierEquDeplacement(origine, destination, pos);
            if (valide) break;
        }
        if (!valide) {
            System.out.println("Vecteur :");
            for (VecteurDeDeplacement vec : piece.getVecDeplacements()) {
                System.out.println(vec.toString());
                valide = verifierEquDeplacement(origine, destination, vec);
                if (valide) break;
            }
        }

        if (!valide) {
            throw new Exception("Deplacement inexistant");
        }

        // Verifier que le déplacement ne mets pas en échec une pièce condition de victoire allié
        // generer plateau et verifier echec
        System.out.println("Copie tableau");
        Plateau copie = new Plateau(plateau);

        System.out.println("Deplacement de la piece de "+origine+" à "+destination);
        Case c = copie.getCase(origine.getPosition());
        Piece p = c.getPieceOnCase();
        c.setPieceOnCase(null);
        copie.getCase(destination.getPosition()).setPieceOnCase(p);

        List<Joueur> monEquipe = joueurs.stream().filter(j -> j.getEquipe() == joueur.getEquipe())
                .collect(Collectors.toList());
        System.out.println("Mon equipe : "+monEquipe);

        Set<Piece> pieceConditionVictoire = new LinkedHashSet<>();
        for (Joueur j : monEquipe) {
            pieceConditionVictoire.addAll(j.getPawnList().stream().filter(Piece::estConditionDeVictoire).collect(Collectors.toList()));
        }

        System.out.println("pieceConditionVictoire : "+pieceConditionVictoire);
        valide = verifierEchec(pieceConditionVictoire, copie, joueur.getEquipe()) == null;
        if (!valide) {
            throw new Exception("Mit en echec");
        }

        System.out.println("APPLIQUER");
        //APPLIQUER

        // Si le déplacement met en échec un joueur en face alors décrémenter vie de la pièce concernée
        List<Joueur> equipeAdverse = joueurs.stream().filter(j -> j.getEquipe() != joueur.getEquipe())
                .collect(Collectors.toList());
        System.out.println("equipeAdverse : "+equipeAdverse);

        pieceConditionVictoire = new LinkedHashSet<>();
        for (Joueur j : equipeAdverse) {
            pieceConditionVictoire.addAll(j.getPawnList().stream().filter(Piece::estConditionDeVictoire).collect(Collectors.toList()));
        }
        System.out.println("pieceConditionVictoire : "+pieceConditionVictoire);

        Piece pieceMitEnEchec = null;
        do {
            pieceMitEnEchec = verifierEchec(pieceConditionVictoire, copie, joueur.getEquipe());
            if (pieceMitEnEchec != null) {
                pieceMitEnEchec.setNbLife(piece.getNbLife() - 1);
                pieceConditionVictoire.remove(pieceMitEnEchec);
                System.out.println("Piece en echec : "+pieceMitEnEchec);
            }
            System.out.println("pieceConditionVictoire : "+pieceConditionVictoire);
        } while (pieceMitEnEchec != null);

        // Modifier les états de la piece (déplacé, promu, etc)
        piece.setDeplaceCeTour(true);
        piece.setNbMovement(piece.getNbMovement()+1);

        c = plateau.getCase(origine.getPosition());
        p = c.getPieceOnCase();
        c.setPieceOnCase(null);
        plateau.getCase(destination.getPosition()).setPieceOnCase(p);

        //todo Appliquer les règles après coup
        //regles.appliquer();
    }

    /**
     * Verifie si, dans le plateau, une des peces condition de victoire est en echec
     * @param pieceConditionVictoire Liste des pieces condition de victoire
     * @param p Le plateau
     * @param equipe L'equipe alliée aux piece condition de victoire
     * @return La première piece trouvé qui est en echec parmit les pieces condition de victoire. null si il y en as pas.
     */
    private Piece verifierEchec(Set<Piece> pieceConditionVictoire, Plateau p, int equipe) {
        // Verifier que le déplacement ne mets pas en échec une pièce condition de victoire allié

        //Parcour du plateau
        for (ArrayList<Case> ligne: p.getEchiquier()) {
            for (Case c : ligne) {
                //Si la piece de la case met en echec une pieceConditionVictoire -> false
                if (c.getPieceOnCase() != null) {
                    Piece piece = c.getPieceOnCase();
                    System.out.println("piece : "+piece);

                    if (piece.getJoueur().getEquipe() != equipe) {
                        System.out.println("POSITION");
                        for (PositionDeDeplacement pos : piece.getPosDeplacements()) {
                            if (verifierEquDeplacementMetEquec(pos, c, p, pieceConditionVictoire)) {
                                return piece;
                            }
                        }
                        for (VecteurDeDeplacement vec : piece.getVecDeplacements()) {
                            if (verifierEquDeplacementMetEquec(vec, c, p, pieceConditionVictoire)) {
                                return piece;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * Verifie si une equation de deplacement met en echec une des piece condition de victoire
     * @param equ L'equation de deplacement
     * @param origine La case d'origine
     * @param p Le plateau
     * @param pieceConditionVictoire Ensemble des pieces condition de victoire
     * @return vrai ssi l'equation met en echec une piece condition de victoire
     */
    private boolean verifierEquDeplacementMetEquec(EquationDeDeplacement equ, Case origine, Plateau p, Set<Piece> pieceConditionVictoire) {
        //Déplacement uniquement ne peux pas mettre en echec
        equ.setEvaluable(true);
        if (equ.getTypeDeplacement() == EquationDeDeplacement.TypeDeplacement.DEPLACER) {
            return false;
        }
        System.out.println(equ.getTypeDeplacement());

        Position curseur = origine.getPosition();
        while (equ.canEvaluate()) {
            curseur = equ.evaluate(curseur);
            System.out.println("Curseur : "+curseur);

            //Si non-accessible ou hors-Plateau -> false
            if (curseur.getX() >= p.getWitdhX() || curseur.getY() >= p.getHeightY() || curseur.getX() < 0 || curseur.getY() < 0) {
                return false;
            }
            System.out.println("\t est dans le plateau");

            Case c = p.getCase(curseur);
            if (!c.isAccessible()) {
                return false;
            }
            System.out.println("\t est accessible");

            //Si il y a une piece:
            if (c.getPieceOnCase() != null) {

                Piece pCurrent = c.getPieceOnCase();
                System.out.println("\t a une piece : "+pCurrent);
                //Si la piece n'est pas condition de victoire -> false
                if (!pieceConditionVictoire.contains(pCurrent)) {
                    return false;
                }
                System.out.println("\t\t qui est condition de victoire :)");
                //Sinon -> true
                return true;
            }
        }
        return false;
    }

    /**
     * Effectue les vérifications d'une equation de déplacement
     * @param origine Case d'origine du doup
     * @param destination Case de destination du doup
     * @param equ L'equation de déplacement
     * @return vrai ssi l'equation de déplacement est valide
     */
    private boolean verifierEquDeplacement(Case origine, Case destination, EquationDeDeplacement equ) {
        // La case est accessible via un des déplacements de la piece
        //  -   Pas de piece si position seulement
        //  -   Il y a une piece enemie -> mange (mettre la piece prise dans le cimetière du joueur de la pièce prise)
        //  -   Pas de piece allies si both (sauf si la piece est traitre)
        equ.setEvaluable(true);
        Piece pieceorigine = origine.getPieceOnCase();
        System.out.println(equ.getTypeDeplacement().name());
        switch (equ.getTypeDeplacement()) {
            case DEPLACER -> {
                Position curseur = origine.getPosition();
                while (equ.canEvaluate()) {
                    curseur = equ.evaluate(curseur);

                    System.out.println("Curseur : "+curseur);

                    //Si piece ou non-accessible ou hors-Plateau -> false
                    if (curseur.getX() >= plateau.getWitdhX() || curseur.getY() >= plateau.getHeightY() || curseur.getX() < 0 || curseur.getY() < 0) {
                        return false;
                    }
                    System.out.println("\t est dans le plateau");

                    Case c = plateau.getCase(curseur);
                    if (c.getPieceOnCase() != null || !c.isAccessible()) {
                        return false;
                    }
                    System.out.println("\t est accessible et pas de piece");

                    //Sinon -> true
                    if (c.equals(destination)) {
                        System.out.println("\t est la destination :)");
                        return true;
                    }
                }
            }
            case PRENDRE -> {
                Position curseur = origine.getPosition();
                while (equ.canEvaluate()) {
                    curseur = equ.evaluate(curseur);
                    System.out.println("Curseur : "+curseur);

                    //Si non-accessible ou hors-Plateau -> false
                    if (curseur.getX() >= plateau.getWitdhX() || curseur.getY() >= plateau.getHeightY() || curseur.getX() < 0 || curseur.getY() < 0) {
                        return false;
                    }
                    System.out.println("\t est dans le plateau");

                    Case c = plateau.getCase(curseur);
                    if (!c.isAccessible()) {
                        return false;
                    }
                    System.out.println("\t est accessible");

                    //En chemin :
                    if (!c.equals(destination)) {
                        System.out.println("\t est en chemin");
                        //Si il y a une piece -> false
                        if (c.getPieceOnCase() != null) {
                            return false;
                        }
                        System.out.println("\t\t et n'as pas de piece");
                    }

                    //A l'arrivée
                    if (c.equals(destination)) {
                        System.out.println("\t est la destination");
                        //Si pas de piece -> false
                        if (c.getPieceOnCase() == null) {
                            return false;
                        }
                        System.out.println("\t a une piece");

                        Piece victime = c.getPieceOnCase();
                        //Si victime est allièe et je ne suis pas traitre -> false
                        if (victime.getJoueur().getEquipe() == pieceorigine.getJoueur().getEquipe()
                            && !pieceorigine.estTraitre()) {
                            return false;
                        }
                        System.out.println("\t\t qui n'est pas alliès, ou je serait traite :)");

                        //Sinon true
                        return true;
                    }
                }
            }
            case BOTH -> {
                Position curseur = origine.getPosition();
                while (equ.canEvaluate()) {
                    curseur = equ.evaluate(curseur);
                    System.out.println("Curseur : "+curseur);

                    //Si non-accessible ou hors-Plateau -> false
                    if (curseur.getX() >= plateau.getWitdhX() || curseur.getY() >= plateau.getHeightY() || curseur.getX() < 0 || curseur.getY() < 0) {
                        return false;
                    }
                    System.out.println("\t est dans le plateau");

                    Case c = plateau.getCase(curseur);
                    if (!c.isAccessible()) {
                        return false;
                    }
                    System.out.println("\t est accessible");

                    //En chemin :
                    if (!c.equals(destination)) {
                        System.out.println("\t est en chemin");
                        //Si il y a une piece -> false
                        if (c.getPieceOnCase() != null) {
                            return false;
                        }
                        System.out.println("\t\t et n'as pas de piece");
                    }

                    //A l'arrivée
                    if (c.equals(destination)) {
                        System.out.println("\t est la destination");
                        Piece victime = c.getPieceOnCase();
                        //Si piece victime existe et est allièe et je ne suis pas traitre -> false
                        if (victime != null && victime.getJoueur().getEquipe() == pieceorigine.getJoueur().getEquipe()
                                && !pieceorigine.estTraitre()) {
                            return false;
                        }
                        System.out.println("\t\t qui n'est pas alliès, ou je serait traite :)");

                        //Sinon true
                        return true;
                    }
                }
            }
        }
        System.out.println("Aucun chemin trouvé");
        return false;
    }
}
