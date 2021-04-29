package org.example;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.util.converter.NumberStringConverter;
import org.example.model.*;
import org.example.model.EquationDeDeplacement.PositionDeDeplacement;
import org.example.model.EquationDeDeplacement.VecteurDeDeplacement;
import org.example.model.Regles.Jeton;
import org.example.model.Regles.MauvaiseDefinitionRegleException;
import org.junit.runner.manipulation.Ordering;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;

import java.util.function.Function;
import java.util.*;

public class GameController extends Controller {

    //éléments graphiques
    @FXML
    public Label varLabel;          //Le texte en haut de l'écrant
    @FXML
    public Label playerLabel;       //Le texte qui affiche le joueur courrant
    @FXML
    public VBox coupsBox;           //La boite de liste de coup
    @FXML
    public Canvas canvas;           //Le plateau
    @FXML
    public ScrollPane scroll;       //Le paneau de défilement de la liste de coup
    @FXML
    public Canvas graveyardCanvas;  //Le cimetière
    @FXML
    public AnchorPane popupPane;    //L'écrant invisible au dessu du jeu où mettre le menu promotion
    @FXML
    public HBox timersHBox;         //La boite de liste de chronomètres


    //Gestion des éléments graphiques
    private CanvasManager canvasManager;        //gère le plateau
    private GraphicsContext graveyardContext;   //gère le cimetière
    private ArrayList<Object> labelTimers;      //gère les chronomètres
    private Label labelCourant;                 //gère le texte du timer courrant


    //Gestion de la partie
    private Variante<Jeton> gameVariante;                   //La variante de la partie
    private OrdonnanceurDeJeu ordonnanceurDeJeu;            //Gère les déplacements et les vcérifications de jeu

    private int indiceJoueur;                               //Indice du joueur courrant dans l'ordre des joueurs
    private Deque<Integer> perdants = new ArrayDeque<>();   //Pile des perdants

    private Timer timerCourant;                             //Le chronomètre actif


    //Gestion dun tour
    private EtatClick etatClick;        //Permet d'exécuter l'action correspondante d'un clic en fonction de l'avancée du tour

    private boolean aJoue;              //Boolean : vrai ssi le joueur à fini son tour

    private String messageFinDeTour;    //Message à afficher dans la liste de coup à la fin du tour


    //Gestion d'un déplacement
    private Case caseOrigine = null;        //La case de la pièce à déplacer
    private Case caseDestination = null;    //La case destination du coup

    private Set<Case> coupPossibles;        //Ensemble de cases où on peux déplacer la pièce


    //Getion des selections
    private ArrayList<Position> casesPromotion; //Liste des cases pour promouvoir une pièce
    private ArrayList<Piece> piecesRevivre;     //Liste des Pieces morte pour faire revivre
    private ArrayList<Case> casesRevivre;       //liste des cases où mettre la piece à faire revivre

    private Position selectionCase;             //La case sélectionnée
    private Piece selectionPieceRevivre;        //La piece à revivre selectionnée

    private boolean canRevive;                  //Le joueur peux sélectionner un pièce du cimerière à faire revivre

    /**
     * Les différents états d'un clic
     */
    private static enum EtatClick {
        JEU, CHOIX, DESACTIVE
    }

    /**
     * Fonction exécutée lors d'un clic sur le cimetière
     * @param mouseEvent des détails sur l'évènement du clic
     * @throws IOException
     */
    @FXML
    public void ClickGraveyard(MouseEvent mouseEvent) throws IOException{
        if (canRevive) {
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                //On parcour les positions du cimetière de la même façon que pour l'affichage
                Position position = new Position(0, 0);
                double rectSize = canvasManager.getRectSize() / 1.5;
                Position positionClick = new Position((int) (mouseEvent.getX() / rectSize), (int) (mouseEvent.getY() / rectSize));
                for (Joueur j : gameVariante.getJoueurs()) {
                    for (Piece p : j.getGraveyard()) {
                        if (position.equals(positionClick)) {
                            selectionnerPieceRevivre(p);
                            return;
                        }
                        position.setX(position.getX() + 1);
                        if (position.getX() * (rectSize + 1) >= canvas.getWidth()) {
                            position.setX(0);
                            position.setY(position.getY() + 1);
                        }
                    }
                    if (j.getGraveyard().size() > 0) {
                        position.setX(position.getX() + 1);
                        if (position.getX() * (rectSize + 1) >= canvas.getWidth()) {
                            position.setX(0);
                            position.setY(position.getY() + 1);
                        }
                    }
                }
            }
            else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                resetCoup();
            }
        }
    }

    /**
     * Initialise le controlleur : récupère la variante, initialise l'ordonanceur de jeu et la variante, et débute le tour
     */
    @Override
    public void initialise(){
        if (userVar == null) {
            showAlert(Alert.AlertType.ERROR, "Erreur : Aucune variante n'as été selectionné");
            try {
                getApp().setRoot("home");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            //Initialisation de la partie
            try {
                gameVariante = ((Variante<Jeton>) userVar).clone();
            } catch ( CloneNotSupportedException e ) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Erreur lors du clonage de la variante");
            }

            ordonnanceurDeJeu = new OrdonnanceurDeJeu(gameVariante);

            try {
                gameVariante.initialiser(ordonnanceurDeJeu.getListTypesPieces());
            } catch ( VarianteException e ) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, e.getMessage());
            }

            //Initialisations graphiques
            varLabel.setText(gameVariante.getName());
            playerLabel.setText(gameVariante.getOrdrejoueur().get(0).getName());

            labelTimers = new ArrayList<>();
            for (Joueur j : gameVariante.getJoueurs() ) {
                Label timerLabel = createNewTimer(j);
                labelTimers.add(j);
                labelTimers.add(timerLabel);
            }

            //Initialisation du tour
            indiceJoueur = 0;

            canvasManager = new CanvasManager(canvas, gameVariante.getPlateau());
            graveyardContext = graveyardCanvas.getGraphicsContext2D();
            coupPossibles = new LinkedHashSet<>();

            casesPromotion = new ArrayList<>();
            piecesRevivre = new ArrayList<>();
            casesRevivre = new ArrayList<>();
            try {
                resetCoup();
                debutTour();
            } catch (IOException e) {
                e.printStackTrace();
            }
            updateCanvas();
        }
    }

    /**
     * Fonction qui envoie au plateau ce qu'il faut dessiner et dans quel ordre
     */
    public void updateCanvas() {

        graveyardContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        playerLabel.setText(gameVariante.getOrdrejoueur().get(indiceJoueur).getName());

        //Affichage plateau
        canvasManager.drawCanvas();
        if (caseOrigine != null) {
            canvasManager.drawCase(caseOrigine.getPosition());
        }
        if (caseDestination != null) {
            canvasManager.drawCase(caseDestination.getPosition());
        }
        canvasManager.drawCoupPossibles(coupPossibles);

        //Affichage selections
        if (!casesPromotion.isEmpty()) {
            canvasManager.hideCasesExcept(casesPromotion);
        }
        else if (!piecesRevivre.isEmpty() && casesRevivre.isEmpty()) {
            Position position = new Position(0, 0);
            double rectSize = canvasManager.getRectSize()/1.5;
            for (Joueur j : gameVariante.getJoueurs()) {
                for (Piece p : j.getGraveyard()) {
                    if (!piecesRevivre.contains(p)) {
                        graveyardContext.setFill(Color.GRAY);
                        graveyardContext.fillRect(position.getX() * rectSize, position.getY() * rectSize, rectSize, rectSize);
                    }
                    position.setX(position.getX()+1);
                    if (position.getX()*(rectSize+2) >= canvas.getWidth()) {
                        position.setX(0);
                        position.setY(position.getY()+1);
                    }
                }
                if (j.getGraveyard().size() > 0) {
                    position.setX(position.getX()+1);
                    if (position.getX()*(rectSize+2) >= canvas.getWidth()) {
                        position.setX(0);
                        position.setY(position.getY()+1);
                    }
                }
            }
        }
        else if (!casesRevivre.isEmpty()) {
            canvasManager.hideCasesExceptCase(casesRevivre);
        }

        //Affichage element selectionné
        if (selectionCase != null) {
            canvasManager.drawSelectionCase(selectionCase);
        }
        if (selectionPieceRevivre != null) {
            Position position = new Position(0, 0);
            double rectSize = canvasManager.getRectSize()/1.5;
            for (Joueur j : gameVariante.getJoueurs()) {
                for (Piece p : j.getGraveyard()) {
                    if (p == selectionPieceRevivre) {
                        graveyardContext.setFill(Color.MEDIUMBLUE);
                        graveyardContext.fillRect(position.getX() * rectSize, position.getY() * rectSize, rectSize, rectSize);
                    }
                    position.setX(position.getX()+1);
                    if (position.getX()*(rectSize+2) >= canvas.getWidth()) {
                        position.setX(0);
                        position.setY(position.getY()+1);
                    }
                }
                if (j.getGraveyard().size() > 0) {
                    position.setX(position.getX()+1);
                    if (position.getX()*(rectSize+2) >= canvas.getWidth()) {
                        position.setX(0);
                        position.setY(position.getY()+1);
                    }
                }
            }
        }

        //Affichage des pièces
        canvasManager.drawPawn();

        //Affichage du cimetière
        Position position = new Position(0, 0);

        double rectSize = canvasManager.getRectSize()/1.5;
        for (Joueur j : gameVariante.getJoueurs()) {
            for (Piece p : j.getGraveyard()) {
                Image img = new Image(p.getSprite());
                double cx = rectSize * (position.getX() + 0.5);
                double cy = rectSize * (position.getY() + 0.5);
                double w;
                double h;
                if (img.getHeight() > img.getWidth()) {
                    h = rectSize;
                    w = img.getWidth() / img.getHeight() * rectSize;
                } else {
                    w = rectSize;
                    h = img.getHeight() / img.getWidth() * rectSize;
                }
                graveyardContext.drawImage(img, cx - w / 2, cy - h / 2, w, h);

                position.setX(position.getX()+1);
                if (position.getX()*(rectSize+2) >= canvas.getWidth()) {
                    position.setX(0);
                    position.setY(position.getY()+1);
                }
            }
            if (j.getGraveyard().size() > 0) {
                position.setX(position.getX()+1);
                if (position.getX()*(rectSize+2) >= canvas.getWidth()) {
                    position.setX(0);
                    position.setY(position.getY()+1);
                }
            }
        }
    }

    /**
     * Fonction exécutée lors d'un clic sur le plateau de jeu
     * @param mouseEvent des détails sur le clic
     * @throws IOException
     */
    @FXML
    public void play(MouseEvent mouseEvent) throws IOException {
        getApp().soundManager.playSound("button-hover");

        Case c = canvasManager.getCase(mouseEvent.getX(), mouseEvent.getY());

        switch (mouseEvent.getButton()) {
            case PRIMARY -> {
                switch (etatClick) {
                    case JEU -> {
                        // On joue un coup
                        if (c.isAccessible()) {
                            if (caseOrigine == null) {
                                if (c.getPieceOnCase() != null && c.getPieceOnCase().getJoueur().getPawnList().contains(c.getPieceOnCase())) {
                                    caseOrigine = c;
                                    coupPossibles = ordonnanceurDeJeu.deplacementsValide(caseOrigine, joueurQuiJoue());
                                }
                            } else {
                                caseDestination = c;

                                try {
                                    ordonnanceurDeJeu.verifierDeplacement(caseOrigine, joueurQuiJoue(), caseDestination);
                                    jouerCoup();
                                } catch (DeplacementException e) {
                                    System.err.println(e.getMessage());
                                    resetCoup();
                                }
                            }
                        }
                    }
                    case CHOIX -> {
                        //On sélectionne un elément
                        selectionnerCase(c);
                    }
                }

            }
            case SECONDARY -> {
                switch (etatClick) {
                    case JEU -> {
                        resetCoup();
                    }
                }
            }
        }
        updateCanvas();
    }

    /**
     * Permet d'obtenir le joueur qui joue
     * @return le Joueur qui joue
     */
    private Joueur joueurQuiJoue() {
        return gameVariante.getOrdrejoueur().get(indiceJoueur);
    }

    /**
     * réhinitialise un coup. Utilisé si le coup n'est pas valide ou si l'utilisateur fait un clic droit
     * @throws IOException
     */
    private void resetCoup() throws IOException{
        etatClick = EtatClick.JEU;
        casesRevivre.clear();
        caseOrigine = null;
        coupPossibles = new LinkedHashSet<>();
        caseDestination = null;
        updateCanvas();
    }

    /**
     * Demande à l'utilisateur de selectionner en fonction du comportement courrant
     * @return vrai ssi il faut attendre que l'utilisateur choisisse
     * @throws IOException
     */
    private boolean faireSelectionner() throws IOException{
        boolean attendreUtilisateur = false;
        if (!casesPromotion.isEmpty()) {
            attendreUtilisateur = true;
            if (casesPromotion.size() == 1) {
                selectionnerCase(gameVariante.getPlateau().getCase(casesPromotion.get(0)));
            }
            else {
                varLabel.setText("Selection : Promotion");
                updateCanvas();
                etatClick = EtatClick.CHOIX;
            }
        }
        else if (!piecesRevivre.isEmpty()) {
            System.out.println("SELECTION REVIVRE POSSIBLE");
            attendreUtilisateur = true;
            canRevive = true;
        }
        //else if ...
        return attendreUtilisateur;
    }

    /**
     * Vérifie que la case selectionnée est valide et exécute le comportement associé à la selection
     * @param c La case choisie
     * @throws IOException
     */
    private void selectionnerCase(Case c) throws IOException{
        boolean attendreUtilisateur = false;
        boolean caseValide = false;
        if (c.getPieceOnCase() != null && casesPromotion.contains(c.getPosition())) {

            caseValide = true;

            casesPromotion.clear();
            attendreUtilisateur = true;

            selectionCase = c.getPosition();
            fairePromouvoir(c);
        }
        else if (casesRevivre.contains(c)) {
            System.out.println("CASE CHOISIE VALIDE");
            piecesRevivre.clear();

            caseValide = true;

            casesRevivre.clear();
            attendreUtilisateur = false;

            c.setPieceOnCase(selectionPieceRevivre);
            selectionPieceRevivre.getJoueur().getGraveyard().remove(selectionPieceRevivre);
            selectionPieceRevivre.setJoueur(joueurQuiJoue());
            joueurQuiJoue().getPawnList().add(selectionPieceRevivre);

            //Changer le signe y des déplacements de la pièce
            for (PositionDeDeplacement pos : selectionPieceRevivre.getPosDeplacements()) {
                pos.setY(pos.getY() * -1);
                pos.setX(pos.getX() * -1);
            }
            for (VecteurDeDeplacement vec : selectionPieceRevivre.getVecDeplacements()) {
                vec.setY(vec.getY() * -1);
                vec.setX(vec.getX() * -1);
            }


            messageFinDeTour = joueurQuiJoue().getName()+" fait revivre "+selectionPieceRevivre.getName()+" "+c.getPosition();
            aJoue = true;

            selectionPieceRevivre = null;
        }
        else {
            System.out.println("CASE CHOISIE INVALIDE : "+c);
        }
        //else if ...

        if (caseValide) {
            etatClick = EtatClick.JEU;

            varLabel.setText(gameVariante.getName());
            updateCanvas();

            if (!attendreUtilisateur && aJoue) {
                finirTour();
            }
        }
    }

    /**
     * Vérifie que la piece selectionnée est valide et exécute le comportement associé à la selection
     * @param p La piece choisie
     * @throws IOException
     */
    private void selectionnerPieceRevivre(Piece p) throws IOException {
        if (piecesRevivre.contains(p)) {
            selectionPieceRevivre = p;

            for (Case c : p.getCasesPourRevivre()) {
                if (c.isAccessible() && c.getPieceOnCase() == null) {
                    casesRevivre.add(c);
                }
            }

            if (casesRevivre.size() == 1) {
                selectionnerCase(casesRevivre.get(0));
            }
            else {
                varLabel.setText("Selection : Faire revivre");
                updateCanvas();
                etatClick = EtatClick.CHOIX;
            }
        }
    }

    /**
     * Effectue les vérifications des etats changés par les règles et exécute le comportement approprié
     * @return vrai ssi le comportement nécéssite une intéraction avec le joueur
     * @throws IOException
     */
    private boolean verifierEtatsRegle() throws  IOException{
        //verification Joueur
        boolean attendreUtilisateur = false;
        for (Joueur j : gameVariante.getJoueurs()) {
            if (j.aGagne()) {
                for (Joueur jAutre : gameVariante.getJoueurs()) {
                    if (jAutre.getEquipe() != j.getEquipe()) {
                        boolean fin = giveUp("Victoire de "+j.getName(), jAutre);
                        if (fin) {
                            return true;
                        }
                    }
                }
            }
            else if (j.aPerdu()) {
                boolean fin = giveUp("Defaite de "+j.getName(), j);
                if (fin) {
                    return true;
                }
            }
            else if (j.aPat()) {
                for (Joueur jAutre : gameVariante.getJoueurs()) {
                    boolean fin = giveUp("Pat par règle sur "+j.getName(), jAutre);
                    if (fin) {
                        return true;
                    }
                }
            }
        }

        //Verification Ordonnanceur de jeu
        String s = "";
        Joueur j = joueurQuiJoue();
        if (ordonnanceurDeJeu.echecEtMat(j)) {
            s += "\nECHEC ET MAT!!!!";
        }
        else if (ordonnanceurDeJeu.pat(j)) {
            s += "\nPAT";
        }
        if (ordonnanceurDeJeu.zeroPiece(j)) {
            s += "\nPLUS DE PIECE";
        }
        if (ordonnanceurDeJeu.conditionVictoireZeroVie(j)) {
            s += "\nPLUS DE VIE";
        }

        if (!s.isEmpty()) {
            boolean fin = giveUp(s, j);
            if (fin) {
                return true;
            }
        }


        if (ordonnanceurDeJeu.getJoueurPieceCondtionVictoireMorte() != null) {
            boolean fin = giveUp("Condition de victoire mangée!!", ordonnanceurDeJeu.getJoueurPieceCondtionVictoireMorte());
            if (fin) {
                return true;
            }
        }

        //verification Pieces
        casesPromotion = new ArrayList<>();
        for (ArrayList<Case> ligne : gameVariante.getPlateau().getEchiquier()) {
            for (Case c : ligne) {
                Piece p = c.getPieceOnCase();
                if (p != null && p.getJoueur().equals(joueurQuiJoue())) {
                    if (p.estAPromouvoir()) {
                        casesPromotion.add(c.getPosition());
                    }
                    //if est a deplacer...
                }
            }
        }
        piecesRevivre = new ArrayList<>();
        canRevive = false;
        if (!aJoue) {
            for (Joueur joueur : gameVariante.getJoueurs()) {
                for (Piece p : joueur.getGraveyard()) {
                    if (p.getCasesPourRevivre() != null && p.getJoueur().getEquipe() != joueurQuiJoue().getEquipe()) {
                        piecesRevivre.add(p);
                    }
                }
            }
        }
        attendreUtilisateur = faireSelectionner();


        return attendreUtilisateur;
    }

    /**
     * Initialise un tour : reinitialise le plateau et exécute les règles d'avant coup
     * @throws IOException
     */
    private void debutTour() throws IOException{
        try {
            aJoue = false;

            gameVariante.getPlateau().reinitialiserComportementLieAunTour(joueurQuiJoue());

            ordonnanceurDeJeu.appliquerReglesAvant();

            System.out.println("---------VERIFICATION REGLES DEBUT----------");
            verifierEtatsRegle();

        } catch (MauvaiseDefinitionRegleException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur critique : "+e.getMessage());
            getApp().setRoot("home");
        }
    }

    /**
     * Termine le tour : met l'action du joueur sur l'interface, incrémente le joueur courrant, joue le timer, et débute le tour
     * @throws IOException
     */
    private void finirTour() throws IOException {
        if (timerCourant != null) {
            pauseTimer(timerCourant);
        }

        addLabelCoup(messageFinDeTour);
        incrementerIndiceJoueur();
        timerCourant = playTimer(joueurQuiJoue());
        resetCoup();
        debutTour();
    }

    /**
     * Effectue un déplacement de pièce et applique les règles d'après coup
     * @throws IOException
     */
    private void jouerCoup() throws IOException{
        try {
            etatClick = EtatClick.DESACTIVE; //Desactiver le click

            messageFinDeTour = joueurQuiJoue().getName() + " : " + caseOrigine.getPieceOnCase().getName() + " to " + caseDestination.getPosition();

            ordonnanceurDeJeu.deplacerPiece(caseOrigine, joueurQuiJoue(), caseDestination);
            aJoue = true;

            ordonnanceurDeJeu.appliquerReglesApres();

            System.out.println("---------VERIFICATION REGLES APRES----------");
            boolean attendreUtilisateur = verifierEtatsRegle();

            if (!attendreUtilisateur) {
                finirTour();
            }
        } catch (MauvaiseDefinitionRegleException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur critique : "+e.getMessage());
            getApp().setRoot("home");
        }
    }

    /**
     * Permet de passer au joueur suivant
     */
    private void incrementerIndiceJoueur() {
        indiceJoueur++;
        if (indiceJoueur >= gameVariante.getOrdrejoueur().size()) {
            indiceJoueur = 0;
        }
        updateCanvas();
    }

    /**
     * Ajoute un message dans la liste des coup sur l'interface
     * @param message
     */
    private void addLabelCoup(String message) {
        Label l = new Label(message);
        l.setTextAlignment(TextAlignment.CENTER);
        l.setWrapText(true);
        coupsBox.getChildren().add(l);
        scroll.setVvalue(2);
    }

    /**
     * Fait perdre le joueur et si il n'y a plus de joueur dans son équipe, alors on termine la partie
     * @param message Le message à afficher si on termine la partie
     * @param jPerdant Le joueur qui perd
     * @return vrai ssi la partie est finie
     * @throws IOException
     */
    public boolean giveUp(String message, Joueur jPerdant)  throws IOException {
        fairePerdreJoueur(jPerdant);
        if (indiceJoueur >= gameVariante.getOrdrejoueur().size()) {
            indiceJoueur = 0;
        }

        Set<Integer> equipes = new LinkedHashSet<>();
        for (Joueur j:gameVariante.getOrdrejoueur()) {
            equipes.add(j.getEquipe());
        }

        if (equipes.size() <= 1) {
            for (Joueur j :(gameVariante.getOrdrejoueur())) {
                if (!perdants.contains(j.getEquipe())) {
                    perdants.add(j.getEquipe());
                }
            }

            getApp().soundManager.playSound("win");
            popupWindow("gameOver", new EndGameData((Variante<Jeton>) userVar, perdants, message, this));

            if (timerCourant != null) {
                pauseTimer(timerCourant);
            }

            return true;
        }
        else {
            updateCanvas();
            return false;
        }
    }

    /**
     * Fait apparaitre un fenetre pour promouvoir une pièce
     * @param casePromotion la case où est la pièce à promouvoir
     * @throws IOException
     */
    public void fairePromouvoir(Case casePromotion) throws IOException{
        popupWindow("promotion", new PromotionController.PromotionData(casePromotion, this));
    }

    /**
     * fait promouvoir la piece de la case Pormotion en nouvelle piece de typePiece
     * @param casePromotion la case où est la piece à promouvoir
     * @param typePiece le type de pièce à instancier
     * @throws IOException
     */
    public void promote(Case casePromotion, Piece typePiece) throws IOException{
        Piece p = new Piece(typePiece);
        Joueur j = casePromotion.getPieceOnCase().getJoueur();
        p.setJoueur(j);
        j.getPawnList().add(p);
        p.setAEtePromu(true);
        Piece p2 = new Piece(casePromotion.getPieceOnCase());

        casePromotion.setPieceOnCase(p);
        destroyPopup();

        addLabelCoup(j + " : " + p2.getName() + " promu en " + p.getName());

        selectionCase = null;
        updateCanvas();

        boolean attendreUtilisateur = faireSelectionner();

        if (!attendreUtilisateur && aJoue) {
            finirTour();
        }
    }

    /**
     * Créé une zone de texte de chronometre pour le joueur concerne. C'est une initialisation
     * @param joueurConcerne le joueur à qui mettre le timer
     * @return un Label : l'element d'interface contenant le texte du timer
     */
    private Label createNewTimer(Joueur joueurConcerne) {
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setPrefWidth(Region.USE_COMPUTED_SIZE);

        Label playerLabel = new Label(joueurConcerne.getName());
        playerLabel.getStyleClass().add("label-bright");
        vbox.getChildren().add(playerLabel);
        String message;

        if (joueurConcerne.getMinute() != 999) {
            message = joueurConcerne.toStringTimer();
        }
        else{
            message = "Temps infini";
        }

        Label timerLabel = new Label(message);
        timerLabel.getStyleClass().add("label-bright");
        vbox.getChildren().add(timerLabel);

        timersHBox.getChildren().add(vbox);
        return timerLabel;
    }

    /**
     * Met en marche le timer d'un joueur
     * @param joueur le joueur concerné
     * @return le Timer actif
     */
    public Timer playTimer(Joueur joueur) {
        Timer timer = new Timer();

        labelCourant = null;
        for (int i = 0; i < labelTimers.size(); i+=2) {
            Joueur j = (Joueur) labelTimers.get(i);
            if (j == joueur) {
                labelCourant = (Label) labelTimers.get(i+1);
                break;
            }
        }

        //Utilisation d'un thread pour le timer
        //  on englobe les éléments graphiques dans Platform.runLater() de javaFX car les éléments graphiques ne supporte pas les threads extérieurs
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (joueur.getMinute() != 999) {
                    if (joueur.decrementeTempsJoueur()) {
                        Platform.runLater(() -> labelCourant.setText("" + joueur.toStringTimer()));
                    } else {
                        timer.cancel();
                        Platform.runLater(() -> {
                                    String s = "Plus de temps pour " + joueur.getName() + "!";
                                    //showAlert(Alert.AlertType.ERROR, "T'as perdu looser de " + joueur.getName() + " Aussi talentueux que Hugo !");
                                    try {
                                        giveUp(s, joueurQuiJoue());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                        showAlert(Alert.AlertType.ERROR, "WOW! un bug t'as empeché de perde!!");
                                    }
                                }
                        );
                    }
                }
            }
        }, 1000,1000);

        return timer;
    }

    /**
     * Arrête le timer passé en paramètre
     * @param timer le Timer concerné
     */
    public void pauseTimer(Timer timer){
        timer.cancel();
    }

    /**
     * Fait apparaître une fenetre par dessu la zone de jeu
     * @param fxml la fenetre à carcher
     * @param var l'objet à passer à la fenetre
     * @throws IOException
     */
    public void popupWindow(String fxml, Object var) throws IOException {
        Parent layout = getApp().loadFXML(fxml, var);

        popupPane.getChildren().add(layout);
        popupPane.setMouseTransparent(false);
    }

    /**
     * Détruit la fenetre quii est au dessu de la zone de jeu
     */
    public void destroyPopup() {
        popupPane.getChildren().clear();
        popupPane.setMouseTransparent(true);
    }

    /**
     * Exécuté sur le bouton "Abandonner", fait perdre le joueur courrant
     * @throws IOException
     */
    @FXML
    public void giveUpButton() throws IOException{
        giveUp("\nAbandon de "+joueurQuiJoue().getName(), joueurQuiJoue());
    }

    /**
     * Supprime le joueur de la liste des joueurs et l'ajoute à la pile des perdants
     * @param jPerdant
     */
    private void fairePerdreJoueur(Joueur jPerdant) {
        gameVariante.getOrdrejoueur().removeIf(joueur -> joueur.getEquipe() == jPerdant.getEquipe());
        if (!perdants.contains(jPerdant.getEquipe())) {
            perdants.add(jPerdant.getEquipe());
        }
    }

    /**
     * Exécuté sur le bouton d'information, fait apparêtre une fenetre d'aide
     */
    @FXML
    public void infoButton() {
        showAlert(Alert.AlertType.INFORMATION, "Maintenant que vous avez choisi la variante, vous allez donc jouer la partie avec toutes les règles défini pour elle (pièces, plateau, règles, temps, etc ...).\n\nPour commencer, il suffit de choisir la pièce à déplacer de faire un clic gauche puis les emplacement des déplacement valides sera alors afficher, il suffira donc de cliquer sur l'emplacement et votre pièce se déplacera.\n\nSi vous avez cliqué sur une pièce par erreur, un clic droit annulera la sélection.\n\nDe plus, à tout moment vous pouvez abandonner la partie en cliquant sur le bouton abandonner en bas de l'écran, puis dès que il ne reste plus que un joueur sur le plateau alors un classement s'affichera pour afficher le gagnant et le second, ....\n\nIl ne vous reste plus qu'à jouer."); //todo texte edition regle
    }
}
