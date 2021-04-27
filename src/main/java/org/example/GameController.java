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
import org.junit.runner.manipulation.Ordering;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;
import java.util.*;

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
            gameVariante = new VarianteJeton((Variante) userVar);
            varLabel.setText(gameVariante.getName());
            playerLabel.setText(gameVariante.getOrdrejoueur().get(0).getName());

            labelTimers = new ArrayList<>();
            for (Joueur j : gameVariante.getJoueurs() ) {
                Label timerLabel = createNewTimer(j);
                labelTimers.add(j);
                labelTimers.add(timerLabel);
            }

            indiceJoueur = 0;

            ordonnanceurDeJeu = new OrdonnanceurDeJeu(gameVariante);

            canvasManager = new CanvasManager(canvas, gameVariante.getPlateau());
            graveyardContext = graveyardCanvas.getGraphicsContext2D();
            coupPossibles = new LinkedHashSet<>();
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

        double rectSize = canvasManager.getRectSize();
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
        System.out.println(joueurQuiJoue().getTimer());

        getApp().soundManager.playSound("button-hover");
        //System.out.println(mouseEvent);

        Case c = canvasManager.getCase(mouseEvent.getX(), mouseEvent.getY());
        //System.out.println("Case : "+c+"\n mouseEvent : "+mouseEvent+"\n caseOrigine:"+caseOrigine);

        switch (mouseEvent.getButton()) {
            case PRIMARY -> {
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
            case SECONDARY -> {
                caseOrigine = null;
                coupPossibles = new LinkedHashSet<>();
                caseDestination = null;
            }
        }
        updateCanvas();
    }

    private Joueur joueurQuiJoue() {
        return gameVariante.getOrdrejoueur().get(indiceJoueur);
    }

    private void jouerCoup() throws IOException{
        try {
            gameVariante.getPlateau().reinitialiserComportementLieAunTour(joueurQuiJoue());
            String name = caseOrigine.getPieceOnCase().getName();
            System.out.println(joueurQuiJoue().getName() + " : " + name + " to " + caseDestination.getPosition());

            ordonnanceurDeJeu.deplacerPiece(caseOrigine, joueurQuiJoue(), caseDestination);

            addLabelCoup(joueurQuiJoue().getName() + " : " + name + " to " + caseDestination.getPosition());
            incrementerIndiceJoueur();

            if (timerCourant != null) {
                pauseTimer(timerCourant);
            }

            if (ordonnanceurDeJeu.fautPromouvoir()) {
                fairePromouvoir(caseDestination);
            }
            else {
                timerCourant = playTimer(joueurQuiJoue());
            }
        } catch (DeplacementException e) {
            //showAlert(Alert.AlertType.ERROR, "PAS CONTENT !: "+e.getMessage());
            System.out.println(e.getMessage());
        }

        caseOrigine = null;
        coupPossibles = new LinkedHashSet<>();
        caseDestination = null;

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

    public void giveUpButton(String message) throws IOException{
        //todo passer le gagnant/perdant
        fairePerdreJoueurCourant();
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
        }
        else {
            updateCanvas();
        }
    }

    public void fairePromouvoir(Case casePromotion) throws IOException{
        popupWindow("promotion", new PromotionController.PromotionData(casePromotion, this));
    }

    public void promote(Case casePromotion, Piece typePiece) {
        System.out.println("PROMOTION EN "+typePiece);

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


        timerCourant = playTimer(joueurQuiJoue());
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
        System.out.println(labelTimers);

        labelCourant = null;
        for (int i = 0; i < labelTimers.size(); i+=2) {
            Joueur j = (Joueur) labelTimers.get(i);
            if (j.equals(joueur)) {
                labelCourant = (Label) labelTimers.get(i+1);
                break;
            }
        }
        System.out.println(joueur + " : " + labelCourant);

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

    private void fairePerdreJoueurCourant() {
        Joueur j = joueurQuiJoue();
        gameVariante.getOrdrejoueur().removeIf(joueur -> joueur.getEquipe() == j.getEquipe());
        if (!perdants.contains(j.getEquipe())) {
            perdants.add(j.getEquipe());
        }
    }

    @FXML
    public void infoButton() {
        showAlert(Alert.AlertType.INFORMATION, "Clic gauche et clic droit et give up shrek"); //todo texte edition regle
    }
}
