package org.example.model;

import org.example.model.EquationDeDeplacement.EquationDeDeplacement;
import org.example.model.EquationDeDeplacement.PositionDeDeplacement;
import org.example.model.EquationDeDeplacement.VecteurDeDeplacement;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrdonnanceurDeJeu {

    //private int nbJoueur;
    private List<Joueur> joueurs;
    //private List<List<Joueur>> equipes;
    private List<Piece> listTypesPieces;

    private Plateau plateau;

    public OrdonnanceurDeJeu(List<Joueur> joueurs, Plateau plateau){
        this.joueurs = joueurs;
        this.plateau = plateau;
        this.listTypesPieces = new ArrayList<>();
        LinkedHashSet<Piece> tmp = new LinkedHashSet<>();
        for (int i = 0; i < joueurs.size(); i++) {
            tmp.addAll(joueurs.get(i).getTypePawnList());
        }
        this.listTypesPieces.addAll(tmp);
    }

    public List<Joueur> getJoueurs() { return this.joueurs; }

    public Joueur getJoueur(int i) throws ArrayIndexOutOfBoundsException{
        return this.joueurs.get(i);
    }

    public Plateau getPlateau() { return this.plateau; }

    public List<Piece> getListTypesPieces() { return this.listTypesPieces; }

    /**
     * Parcour les equation d'une case et retourne une liste de case de déplacement possibles
     * @param origine case d'origine
     * @return Ensemble de cases disponnibles
     */
    public Set<Case> deplacementsValide(Case origine, Joueur j) {
        Set<Case> cases = new LinkedHashSet<>();
        Piece piece = origine.getPieceOnCase();
        if (piece == null) {
            return cases;
        }


        for (PositionDeDeplacement pos : piece.getPosDeplacements()) {
            cases.addAll(equDeplacementsValide(pos, origine, j));
        }
        for (VecteurDeDeplacement vec : piece.getVecDeplacements()) {
            cases.addAll(equDeplacementsValide(vec, origine, j));
        }
        return cases;
    }

    /**
     * Donne les cases disponnibles d'une équation
     * @param equ L'equation
     * @param origine la case d'origine
     * @return L'ensemble de cases deplacement possible
     */
    private Set<Case> equDeplacementsValide(EquationDeDeplacement equ, Case origine, Joueur j) {
        Set<Case> cases = new LinkedHashSet<>();
        equ.setEvaluable(true);
        Piece pieceorigine = origine.getPieceOnCase();
        switch (equ.getTypeDeplacement()) {
            case DEPLACER -> {
                Position curseur = origine.getPosition();
                while (equ.canEvaluate()) {
                    curseur = equ.evaluate(curseur);

                    //System.out.println("Curseur : "+curseur);

                    //Si piece ou non-accessible ou hors-Plateau -> false
                    if (curseur.getX() >= plateau.getWitdhX() || curseur.getY() >= plateau.getHeightY() || curseur.getX() < 0 || curseur.getY() < 0) {
                        return cases;
                    }
                    //System.out.println("\t est dans le plateau");

                    Case c = plateau.getCase(curseur);
                    if (c.getPieceOnCase() != null || !c.isAccessible()) {
                        return cases;
                    }
                    //System.out.println("\t est accessible et pas de piece");

                    //Sinon -> true
                    try {
                        Case destination = plateau.getCase(curseur);
                        System.out.println(origine+" to "+destination);

                        verifierDeplacement(origine, j, destination);
                        cases.add(destination);
                    } catch (DeplacementException e) {
                        System.out.println("Invalide! "+e.getMessage());
                    }
                }
            }
            case PRENDRE -> {
                Position curseur = origine.getPosition();
                while (equ.canEvaluate()) {
                    curseur = equ.evaluate(curseur);
                    //System.out.println("Curseur : "+curseur);

                    //Si non-accessible ou hors-Plateau -> false
                    if (curseur.getX() >= plateau.getWitdhX() || curseur.getY() >= plateau.getHeightY() || curseur.getX() < 0 || curseur.getY() < 0) {
                        return cases;
                    }
                    //System.out.println("\t est dans le plateau");

                    Case c = plateau.getCase(curseur);
                    if (!c.isAccessible()) {
                        return cases;
                    }
                    //System.out.println("\t est accessible");


                    //A l'arrivée
                    //System.out.println("\t est la destination");
                    //Si pas de piece -> false

                    Piece victime = c.getPieceOnCase();
                    //Si victime est allièe et je ne suis pas traitre -> false
                    if (victime != null && victime.getJoueur().getEquipe() == pieceorigine.getJoueur().getEquipe()
                            && !pieceorigine.estTraitre()) {
                        return cases;
                    }
                    //System.out.println("\t\t qui n'est pas alliès, ou je serait traite :)");
                    if (victime != null) {
                        try {
                            Case destination = plateau.getCase(curseur);
                            System.out.println(origine+" to "+destination);

                            verifierDeplacement(origine, j, destination);
                            cases.add(destination);
                        } catch (DeplacementException e) {
                            System.out.println("Invalide! "+e.getMessage());
                        }
                    }
                }
            }
            case BOTH -> {
                Position curseur = origine.getPosition();
                while (equ.canEvaluate()) {
                    curseur = equ.evaluate(curseur);
                    //System.out.println("Curseur : "+curseur);

                    //Si non-accessible ou hors-Plateau -> false
                    if (curseur.getX() >= plateau.getWitdhX() || curseur.getY() >= plateau.getHeightY() || curseur.getX() < 0 || curseur.getY() < 0) {
                        return cases;
                    }
                    //System.out.println("\t est dans le plateau");

                    Case c = plateau.getCase(curseur);
                    if (!c.isAccessible()) {
                        return cases;
                    }
                    //System.out.println("\t est accessible");

                    //A l'arrivée
                    //System.out.println("\t est la destination");
                    Piece victime = c.getPieceOnCase();
                    //Si piece victime existe et est allièe et je ne suis pas traitre -> false
                    if (victime != null && victime.getJoueur().getEquipe() == pieceorigine.getJoueur().getEquipe()
                            && !pieceorigine.estTraitre()) {
                        return cases;
                    }
                    //System.out.println("\t\t qui n'est pas alliès, ou je serait traite :)");
                    try {
                        Case destination = plateau.getCase(curseur);
                        System.out.println(origine+" to "+destination);

                        verifierDeplacement(origine, j, destination);
                        cases.add(destination);
                    } catch (DeplacementException e) {
                        System.out.println("Invalide! "+e.getMessage());
                    }
                }
            }
        }
        return cases;
    }

    /**
     * Effectue les vérification du déplacement
     * @param origine La Case d'origine du coup
     * @param joueur Le joueur qui fait le coup
     * @param destination La destination du coup
     * @throws Exception Si le coup n'est pas valide. Le message permet d'avoir plus d'information
     */
    public Plateau verifierDeplacement(Case origine, Joueur joueur, Case destination) throws DeplacementException {
        //VERIFIER
        //Verifier qu'il y a une piece et que c'est le bon joueur
        Piece piece = origine.getPieceOnCase();
        if (piece==null || !(piece.getJoueur().equals(joueur))){
            throw new DeplacementException("Pas le bon joueur");
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
            throw new DeplacementException("Deplacement inexistant");
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
        valide = verifierEchec(pieceConditionVictoire, copie, joueur.getEquipe(), false).isEmpty();
        if (!valide) {
            throw new DeplacementException("Mit en echec");
        }
        return copie;
    }

    /**
     * Effectue les vérification et déplace la piece si le déplacement est valide
     * @param origine La Case d'origine du coup
     * @param joueur Le joueur qui fait le coup
     * @param destination La destination du coup
     * @throws Exception Si le coup n'est pas valide. Le message permet d'avoir plus d'information
     */
    public void deplacerPiece(Case origine, Joueur joueur, Case destination) throws DeplacementException {  //todo créer l'exception
        //Appliquer les règles avant coup

        Plateau copie = verifierDeplacement(origine, joueur, destination);

        System.out.println("APPLIQUER");
        //APPLIQUER

        Piece pDeplace = origine.getPieceOnCase();
        // Modifier les états de la piece (déplacé, promu, etc)
        pDeplace.setDeplaceCeTour(true);
        pDeplace.setNbMovement(pDeplace.getNbMovement()+1);

        origine.setPieceOnCase(null);

        Piece pMange = destination.getPieceOnCase();
        destination.setPieceOnCase(pDeplace);

        if (pMange != null) {
            pMange.getJoueur().getPawnList().remove(pMange);
            pMange.getJoueur().getGraveyard().add(pMange);
        }

        // Si le déplacement met en échec un joueur en face alors décrémenter vie de la pièce concernée
        List<Joueur> equipeAdverse = joueurs.stream().filter(j -> j.getEquipe() != joueur.getEquipe())
                .collect(Collectors.toList());
        System.out.println("equipeAdverse : "+equipeAdverse);

        Set<Piece> pieceConditionVictoire = new LinkedHashSet<>();
        for (Joueur j : equipeAdverse) {
            pieceConditionVictoire.addAll(j.getPawnList().stream().filter(Piece::estConditionDeVictoire).collect(Collectors.toList()));
        }

        System.out.println("pieceConditionVictoire : "+pieceConditionVictoire);

        Set<Case> caseOuPiecesMitEnEchec = verifierEchec(pieceConditionVictoire, copie, joueur.getEquipe(), true);
        System.out.println("caseouPiecesMitEnEchec : " + caseOuPiecesMitEnEchec);
        for (Case caseVictime: caseOuPiecesMitEnEchec) {
            Piece victime = caseVictime.getPieceOnCase();
            victime.setNbLife(victime.getNbLife()-1);
        }

        //todo Appliquer les règles après coup
        //regles.appliquer();
    }



    /**
     * Verifie si, dans le plateau, une des pieces condition de victoire est en echec
     * @param pieceConditionVictoire Liste des pieces condition de victoire
     * @param p Le plateau
     * @param equipe L'equipe alliée aux piece condition de victoire
     * @param nonEquipe Boolean vrai ssi les pieces menacantes sont de la même equipe l'equipe
     * @return Toutes les case où les pieces de condition de victoire sont en echec.
     */
    private Set<Case> verifierEchec(Set<Piece> pieceConditionVictoire, Plateau p, int equipe, boolean nonEquipe) {
        // Verifier que le déplacement ne mets pas en échec une pièce condition de victoire allié
        Set<Case> caseDesPieceMenaces = new LinkedHashSet<>();
        //Parcour du plateau
        for (ArrayList<Case> ligne: p.getEchiquier()) {
            for (Case c : ligne) {
                //Si la piece de la case met en echec une pieceConditionVictoire -> false
                if (c.getPieceOnCase() != null) {
                    Piece piece = c.getPieceOnCase();
                    System.out.println("piece : "+piece);

                    boolean condition;
                    if (nonEquipe) {
                        //la piece est de mon equipe
                        condition = piece.getJoueur().getEquipe() == equipe;
                    }
                    else {
                        //la piece n'est pas de mon equipe
                        condition = piece.getJoueur().getEquipe() != equipe;
                    }
                    if (condition) {
                        System.out.println("POSITION");
                        for (PositionDeDeplacement pos : piece.getPosDeplacements()) {
                            Case caseVictime = verifierEquDeplacementMetEquec(pos, c, p, pieceConditionVictoire);
                            if (caseVictime != null) {
                                caseDesPieceMenaces.add(caseVictime);
                            }
                        }
                        for (VecteurDeDeplacement vec : piece.getVecDeplacements()) {
                            System.out.println("VECTEUR");
                            Case caseVictime = verifierEquDeplacementMetEquec(vec, c, p, pieceConditionVictoire);
                            if (caseVictime != null) {
                                caseDesPieceMenaces.add(caseVictime);
                            }
                        }
                    }
                }
            }
        }
        return caseDesPieceMenaces;
    }

    /**
     * Verifie si une equation de deplacement met en echec une des piece condition de victoire
     * @param equ L'equation de deplacement
     * @param origine La case d'origine
     * @param p Le plateau
     * @param pieceConditionVictoire Ensemble des pieces condition de victoire
     * @return La case où la piece de condition de victoire est en echec pour cette equation. null sinon
     */
    private Case verifierEquDeplacementMetEquec(EquationDeDeplacement equ, Case origine, Plateau p, Set<Piece> pieceConditionVictoire) {
        equ.setEvaluable(true);

        //Déplacement uniquement ne peux pas mettre en echec
        if (equ.getTypeDeplacement() == EquationDeDeplacement.TypeDeplacement.DEPLACER) {
            return null;
        }
        System.out.println(equ.getTypeDeplacement());

        Position curseur = origine.getPosition();
        while (equ.canEvaluate()) {
            curseur = equ.evaluate(curseur);
            System.out.println("Curseur : "+curseur);

            //Si non-accessible ou hors-Plateau -> false
            if (curseur.getX() >= p.getWitdhX() || curseur.getY() >= p.getHeightY() || curseur.getX() < 0 || curseur.getY() < 0) {
                return null;
            }
            System.out.println("\t est dans le plateau");

            Case c = p.getCase(curseur);
            if (!c.isAccessible()) {
                return null;
            }
            System.out.println("\t est accessible");

            //Si il y a une piece:
            if (c.getPieceOnCase() != null) {

                Piece pCurrent = c.getPieceOnCase();
                System.out.println("\t a une piece : "+pCurrent);
                //Si la piece n'est pas condition de victoire -> false
                if (!pieceConditionVictoire.contains(pCurrent)) {
                    return null;
                }
                System.out.println("\t\t qui est condition de victoire :)");
                //Sinon -> true
                return c;
            }
        }
        return null;
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
