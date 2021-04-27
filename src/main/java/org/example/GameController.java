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
    private EtatCoup etatCoup;

    private ArrayList<Position> casesPromotion;

    private static enum EtatCoup {
        AVANT, PENDANT, APRES, FIN
    }

    private static enum EtatClick {
        JEU, CHOIX
    }

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

            resetCoup();
            updateCanvas();
        }
    }

    public void updateCanvas() {
        playerLabel.setText(gameVariante.getOrdrejoueur().get(indiceJoueur).getName());
        canvasManager.drawCanvas();
        if (caseOrigine != null) {
            canvasManager.drawCase(caseOrigine.getPosition());
        }
        if (caseDestination != null) {
            canvasManager.drawCase(caseDestination.getPosition());
        }
        canvasManager.drawCoupPossibles(coupPossibles);
        canvasManager.drawPawn();

        //Graveyard
        Position position = new Position(0, 0);
        graveyardContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

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

    @FXML
    public void play(MouseEvent mouseEvent) throws IOException {

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
                            }
                            if (caseDestination != null) {
                                jouerCoup();
                            }
                        }
                    }
                    case CHOIX -> {
                        if (c.getPieceOnCase() != null) {
                            selectionnerCase(c);
                        }
                    }
                }

            }
            case SECONDARY -> {
                switch (etatClick) {
                    case JEU -> {
                        caseOrigine = null;
                        coupPossibles = new LinkedHashSet<>();
                        caseDestination = null;
                    }
                }
            }
        }
        updateCanvas();
    }

    private Joueur joueurQuiJoue() {
        return gameVariante.getOrdrejoueur().get(indiceJoueur);
    }

    private void resetCoup() {
        etatCoup = EtatCoup.AVANT;
        etatClick = EtatClick.JEU;
        caseOrigine = null;
        coupPossibles = new LinkedHashSet<>();
        caseDestination = null;
    }

    private void avancerEtat() {
        switch (etatCoup) {
            case AVANT -> {
                etatCoup = EtatCoup.PENDANT;
            }
            case PENDANT -> {
                etatCoup = EtatCoup.APRES;
            }
            case APRES -> {
                etatCoup = EtatCoup.FIN;
            }
            case FIN -> {
                System.err.println("Erreur etat avancé trop loin");
            }
        }
    }

    private void faireSelectionner() {
        if (casesPromotion != null) {
            etatClick = EtatClick.CHOIX;
            canvasManager.hideCasesExcept(casesPromotion);
        }
        //else if ...
    }

    private void selectionnerCase(Case c) throws IOException{
        etatClick = EtatClick.JEU;
        if (casesPromotion != null) {
            casesPromotion = null;
            fairePromouvoir(c);
        }
        //else if ...
        updateCanvas();
    }

    private void verifierEtatsRegle() throws  IOException{
        //verification Joueur
        boolean attendreUtilisateur = false;
        for (Joueur j : gameVariante.getJoueurs()) {
            if (j.aGagne()) {
                for (Joueur jAutre : gameVariante.getJoueurs()) {
                    if (jAutre.getEquipe() != j.getEquipe()) {
                        boolean fin = giveUp("Victoire de "+j.getName(), jAutre);
                        if (fin) {
                            return;
                        }
                    }
                }
            }
            else if (j.aPerdu()) {
                boolean fin = giveUp("Defaite de "+j.getName(), j);
                if (fin) {
                    return;
                }
            }
            else if (j.aPat()) {
                for (Joueur jAutre : gameVariante.getJoueurs()) {
                    boolean fin = giveUp("Pat par règle sur "+j.getName(), jAutre);
                    if (fin) {
                        return;
                    }
                }
            }
        }

        if (ordonnanceurDeJeu.fautPromouvoir()) {
            attendreUtilisateur = true;
            fairePromouvoir(caseDestination);
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
            giveUpButton(s);
        }

        if (!attendreUtilisateur) {
            avancerEtat();
            jouerCoup();
        }

        //verification Pieces
        //attendreUtilisateur = faireSelectionnerPieceParmit(Piece::estAPromouvoir);
        casesPromotion = new ArrayList<>();
        for (ArrayList<Case> ligne : gameVariante.getPlateau().getEchiquier()) {
            for (Case c : ligne) {
                Piece p = c.getPieceOnCase();
                if (p != null) {
                    if (p.estAPromouvoir()) {
                        casesPromotion.add(c.getPosition());
                    }
                    //if est a deplacer...
                }
            }
        }
        faireSelectionner();
    }

    private void jouerCoup() throws IOException{
        try {
            switch (etatCoup) {
                case AVANT -> {
                    gameVariante.getPlateau().reinitialiserComportementLieAunTour(joueurQuiJoue());
                    nomPieceDeplace = caseOrigine.getPieceOnCase().getName();
                    //System.out.println(joueurQuiJoue().getName() + " : " + nomPieceDeplace + " to " + caseDestination.getPosition());

                    ordonnanceurDeJeu.appliquerReglesAvant();

                    verifierEtatsRegle();
                }
                case PENDANT -> {
                    try {
                        ordonnanceurDeJeu.deplacerPiece(caseOrigine, joueurQuiJoue(), caseDestination);
                    } catch (DeplacementException e) {
                        System.err.println(e.getMessage());
                        resetCoup();
                    }
                    verifierEtatsRegle();
                }
                case APRES -> {
                    ordonnanceurDeJeu.appliquerReglesApres();

                    if (timerCourant != null) {
                        pauseTimer(timerCourant);
                    }

                    verifierEtatsRegle();
                }
                case FIN -> {
                    addLabelCoup(joueurQuiJoue().getName() + " : " + nomPieceDeplace + " to " + caseDestination.getPosition());
                    incrementerIndiceJoueur();
                    timerCourant = playTimer(joueurQuiJoue());
                    resetCoup();
                }
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
        coupsBox.getChildren().add(new Label(message));
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

    public void giveUpButton(String message) throws IOException{
        giveUp(message, joueurQuiJoue());
    }

    public void fairePromouvoir(Case casePromotion) throws IOException{
        popupWindow("promotion", new PromotionController.PromotionData(casePromotion, this));
    }

    public void promote(Case casePromotion, Piece typePiece) throws IOException{
        //System.out.println("PROMOTION EN "+typePiece);

        Piece p = new Piece(typePiece);
        Joueur j = typePiece.getJoueur();
        p.setJoueur(j);
        j.getPawnList().add(p);
        p.setAEtePromu(true);
        Piece p2 = new Piece(casePromotion.getPieceOnCase());

        casePromotion.setPieceOnCase(p);
        destroyPopup();

        updateCanvas();
        addLabelCoup(j + " : " + p2.getName() + " promu en " + p.getName());

        avancerEtat();
        jouerCoup();
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
                                        giveUpButton(s);
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
        giveUpButton("\nABANDON");
    }

    private void fairePerdreJoueur(Joueur jPerdant) {
        gameVariante.getOrdrejoueur().removeIf(joueur -> joueur.getEquipe() == jPerdant.getEquipe());
        if (!perdants.contains(jPerdant.getEquipe())) {
            perdants.add(jPerdant.getEquipe());
        }
    }

    private void fairePerdreJoueurCourant() {
        fairePerdreJoueur(joueurQuiJoue());
    }

    @FXML
    public void infoButton() {
        showAlert(Alert.AlertType.INFORMATION, "Clic gauche et clic droit et give up shrek"); //todo texte edition regle
    }
}
