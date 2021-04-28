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
import org.example.model.Regles.Jeton;
import org.example.model.Regles.MauvaiseDefinitionRegleException;
import org.junit.runner.manipulation.Ordering;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;

public class GameController extends Controller {
    @FXML
    public Label varLabel;
    @FXML
    public Label playerLabel;
    @FXML
    public VBox coupsBox;
    @FXML
    public Canvas canvas;
    @FXML
    public ScrollPane scroll;
    @FXML
    public Canvas graveyardCanvas;
    @FXML
    public AnchorPane popupPane;
    @FXML
    public HBox timersHBox;

    private GraphicsContext graveyardContext;
    private CanvasManager canvasManager;

    private Variante<Jeton> gameVariante;


    private OrdonnanceurDeJeu ordonnanceurDeJeu;

    private Case caseOrigine = null;
    private Case caseDestination = null;

    private int indiceJoueur;
    private Set<Case> coupPossibles;

    private Deque<Integer> perdants = new ArrayDeque<>();

    private Timer timerCourant;

    private ArrayList<Object> labelTimers;
    private Label labelCourant;

    private String nomPieceDeplace;

    private EtatClick etatClick;
    private boolean aJoue;

    private ArrayList<Position> casesPromotion;
    private ArrayList<Piece> piecesRevivre;
    private ArrayList<Case> casesRevivre;

    private Position selectionCase;
    private Piece selectionPieceRevivre;
    private String messageFinDeTour;


    private static enum EtatClick {
        JEU, CHOIX, CHOIXREVIVRE, DESACTIVE
    }

    /**
     * Fonction exécutée lors d'un clic sur le cimetière
     * @param mouseEvent des détails sur l'évènement du clic
     * @throws IOException
     */
    @FXML
    public void ClickGraveyard(MouseEvent mouseEvent) throws IOException{
        if (etatClick == EtatClick.CHOIXREVIVRE) {
            Position position = new Position(0, 0);
            double rectSize = canvasManager.getRectSize()/1.5;
            Position positionClick = new Position((int)(mouseEvent.getX()/rectSize), (int)(mouseEvent.getY()/rectSize));
            for (Joueur j : gameVariante.getJoueurs()) {
                for (Piece p : j.getGraveyard()) {
                    if (position.equals(positionClick)) {
                        selectionnerPieceRevivre(p);

                        System.out.println("Piece morte choisie : "+p);

                        return;
                    }
                    position.setX(position.getX()+1);
                    if (position.getX()*(rectSize+1) >= canvas.getWidth()) {
                        position.setX(0);
                        position.setY(position.getY()+1);
                    }
                }
                if (j.getGraveyard().size() > 0) {
                    position.setX(position.getX()+1);
                    if (position.getX()*(rectSize+1) >= canvas.getWidth()) {
                        position.setX(0);
                        position.setY(position.getY()+1);
                    }
                }
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
            varLabel.setText(gameVariante.getName());
            playerLabel.setText(gameVariante.getOrdrejoueur().get(0).getName());

            labelTimers = new ArrayList<>();
            for (Joueur j : gameVariante.getJoueurs() ) {
                Label timerLabel = createNewTimer(j);
                labelTimers.add(j);
                labelTimers.add(timerLabel);
            }

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
     * Fonction qui dit au plateau ce qu'il faut dessiner et dans quel ordre
     */
    public void updateCanvas() {

        graveyardContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        playerLabel.setText(gameVariante.getOrdrejoueur().get(indiceJoueur).getName());
        canvasManager.drawCanvas();
        if (caseOrigine != null) {
            canvasManager.drawCase(caseOrigine.getPosition());
        }
        if (caseDestination != null) {
            canvasManager.drawCase(caseDestination.getPosition());
        }
        canvasManager.drawCoupPossibles(coupPossibles);

        if (!casesPromotion.isEmpty()) {
            canvasManager.hideCasesExcept(casesPromotion);
        }
        else if (!piecesRevivre.isEmpty()) {
            System.out.println(" DESSIN : PIECES REVRIVRE" + piecesRevivre);
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
            canvasManager.hideAllCases();
        }
        else if (!casesRevivre.isEmpty()) {
            canvasManager.hideCasesExceptCase(casesRevivre);
        }

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
        canvasManager.drawPawn();

        //Graveyard
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
        //System.out.println("CLICK! "+etatClick);

        getApp().soundManager.playSound("button-hover");
        //System.out.println(mouseEvent);

        Case c = canvasManager.getCase(mouseEvent.getX(), mouseEvent.getY());
        //System.out.println("Case : "+c+"\n mouseEvent : "+mouseEvent+"\n caseOrigine:"+caseOrigine);

        switch (mouseEvent.getButton()) {
            case PRIMARY -> {
                switch (etatClick) {
                    case JEU -> {
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
        caseOrigine = null;
        coupPossibles = new LinkedHashSet<>();
        caseDestination = null;
    }

    /**
     * Demande à l'utilisateur de selectionner en fonction du comportement courrant
     * @return vrai ssi il faut attendre que l'utilisateur choisisse
     * @throws IOException
     */
    private boolean faireSelectionner() throws IOException{
        boolean attendreUtilisateur = false;
        if (!casesPromotion.isEmpty()) {
            //System.out.println("SELECTION PROMOTION");
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
            System.out.println("SELECTION REVIVRE");
            //PIECE A FAIRE REVIVRE
            attendreUtilisateur = true;
            if (piecesRevivre.size() == 1) {
                selectionnerPieceRevivre(piecesRevivre.get(0));
            }
            else {
                varLabel.setText("Selection : Piece à revivre");
                updateCanvas();
                etatClick = EtatClick.CHOIXREVIVRE;
            }
        }
        //else if ...
        return attendreUtilisateur;
    }

    private void selectionnerCase(Case c) throws IOException{
        boolean attendreUtilisateur = false;
        boolean caseValide = false;
        if (c.getPieceOnCase() != null && casesPromotion.contains(c.getPosition())) {
            //System.out.println("CHOIX VALIDE : PROMOTION");

            caseValide = true;

            casesPromotion.clear();
            attendreUtilisateur = true;

            selectionCase = c.getPosition();
            fairePromouvoir(c);
        }
        else if (casesRevivre.contains(c)) {
            System.out.println("CASE CHOISIE VALIDE");
            caseValide = true;

            casesRevivre.clear();
            attendreUtilisateur = false;

            c.setPieceOnCase(selectionPieceRevivre);
            selectionPieceRevivre.getJoueur().getGraveyard().remove(selectionPieceRevivre);
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

    private void selectionnerPieceRevivre(Piece p) throws IOException {
        if (piecesRevivre.contains(p)) {
            piecesRevivre.clear();
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
                varLabel.setText("Selection : Placer piece");
                updateCanvas();
                etatClick = EtatClick.CHOIX;
            }
        }
    }

    private boolean verifierEtatsRegle() throws  IOException{

        //verification Joueur
        boolean attendreUtilisateur = false;
        for (Joueur j : gameVariante.getJoueurs()) {
            if (j.aGagne()) {
                //System.out.println("JOUEUR VICTOIRE TROUVE "+j);
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
        for (Joueur joueur : gameVariante.getJoueurs()) {
            for (Piece p : joueur.getGraveyard()) {
                if (p.getCasesPourRevivre() != null && p.getJoueur().getEquipe()!=joueurQuiJoue().getEquipe()) {
                    piecesRevivre.add(p);
                }
            }
        }
        attendreUtilisateur = faireSelectionner();


        return attendreUtilisateur;
    }

    private void debutTour() throws IOException{
        try {
            aJoue = false;

            gameVariante.getPlateau().reinitialiserComportementLieAunTour(joueurQuiJoue());

            ordonnanceurDeJeu.appliquerReglesAvant();

            //Todo enlmever ce truc
            ArrayList<Case> cases = new ArrayList<>();
            for (int i = 1; i < 7; i++) {
                for (int j = 0; j < 8; j++) {
                    Case c = gameVariante.getPlateau().getCase(new Position(j, i));
                    cases.add(c);
                    Piece p = c.getPieceOnCase();
                    if ( p != null && (p.getName().equals("Pion blanc") || p.getName().equals("Pion noir"))) {
                        p.getJoueur().getPawnList().remove(p);
                        p.getJoueur().getGraveyard().add(p);
                        p.setCasesPourRevivre(cases);
                        c.setPieceOnCase(null);
                    }
                }
            }

            System.out.println("---------VERIFICATION REGLES DEBUT----------");
            verifierEtatsRegle();

        } catch (MauvaiseDefinitionRegleException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur critique : "+e.getMessage());
            getApp().setRoot("home");
        }
    }

    private void finirTour() throws IOException {
        addLabelCoup(messageFinDeTour);
        incrementerIndiceJoueur();
        timerCourant = playTimer(joueurQuiJoue());
        resetCoup();
        debutTour();
    }

    private void jouerCoup() throws IOException{
        //System.out.println("Case origine : "+caseOrigine + " Case destination : "+caseDestination);
        try {
            etatClick = EtatClick.DESACTIVE; //Desactiver le click

            nomPieceDeplace = caseOrigine.getPieceOnCase().getName();
            //System.out.println(joueurQuiJoue().getName() + " : " + nomPieceDeplace + " to " + caseDestination.getPosition());

            ordonnanceurDeJeu.deplacerPiece(caseOrigine, joueurQuiJoue(), caseDestination);
            messageFinDeTour = joueurQuiJoue().getName() + " : " + nomPieceDeplace + " to " + caseDestination.getPosition();
            aJoue = true;

            ordonnanceurDeJeu.appliquerReglesApres();

            if (timerCourant != null) {
                pauseTimer(timerCourant);
            }

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

    private void incrementerIndiceJoueur() {
        indiceJoueur++;
        if (indiceJoueur >= gameVariante.getOrdrejoueur().size()) {
            indiceJoueur = 0;
        }
        updateCanvas();
    }

    private void addLabelCoup(String message) {
        Label l = new Label(message);
        l.setTextAlignment(TextAlignment.CENTER);
        l.setWrapText(true);
        coupsBox.getChildren().add(l);
        scroll.setVvalue(2);
    }

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
            return true;
        }
        else {
            updateCanvas();
            return false;
        }
    }

    public void fairePromouvoir(Case casePromotion) throws IOException{
        popupWindow("promotion", new PromotionController.PromotionData(casePromotion, this));
    }

    public void promote(Case casePromotion, Piece typePiece) throws IOException{
        //System.out.println("PROMOTION EN "+typePiece);

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

        //System.out.println("FIN PROMOTION");
        boolean attendreUtilisateur = faireSelectionner();

        if (!attendreUtilisateur && aJoue) {
            finirTour();
        }
    }

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

    public Timer playTimer(Joueur joueur) {
        Timer timer = new Timer();
        //System.out.println(labelTimers);

        labelCourant = null;
        for (int i = 0; i < labelTimers.size(); i+=2) {
            Joueur j = (Joueur) labelTimers.get(i);
            if (j.equals(joueur)) {
                labelCourant = (Label) labelTimers.get(i+1);
                break;
            }
        }
        //System.out.println(joueur + " : " + labelCourant);

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

    public void pauseTimer(Timer timer){
        timer.cancel();
    }

    public void popupWindow(String fxml, Object var) throws IOException {
        Parent layout = getApp().loadFXML(fxml, var);

        popupPane.getChildren().add(layout);
        popupPane.setMouseTransparent(false);
    }

    public void destroyPopup() {
        popupPane.getChildren().clear();
        popupPane.setMouseTransparent(true);
    }

    @FXML
    public void giveUpButton() throws IOException{
        giveUp("\nAbandon de "+joueurQuiJoue(), joueurQuiJoue());
    }

    private void fairePerdreJoueur(Joueur jPerdant) {
        gameVariante.getOrdrejoueur().removeIf(joueur -> joueur.getEquipe() == jPerdant.getEquipe());
        if (!perdants.contains(jPerdant.getEquipe())) {
            perdants.add(jPerdant.getEquipe());
        }
    }

    @FXML
    public void infoButton() {
        showAlert(Alert.AlertType.INFORMATION, "Maintenant que vous avez choisi la variante, vous allez donc jouer la partie avec toutes les règles défini pour elle (pièces, plateau, règles, temps, etc ...).\n\nPour commencer, il suffit de choisir la pièce à déplacer de faire un clic gauche puis les emplacement des déplacement valides sera alors afficher, il suffira donc de cliquer sur l'emplacement et votre pièce se déplacera.\n\nSi vous avez cliqué sur une pièce par erreur, un clic droit annulera la sélection.\n\nDe plus, à tout moment vous pouvez abandonner la partie en cliquant sur le bouton abandonner en bas de l'écran, puis dès que il ne reste plus que un joueur sur le plateau alors un classement s'affichera pour afficher le gagnant et le second, ....\n\nIl ne vous reste plus qu'à jouer."); //todo texte edition regle
    }
}
