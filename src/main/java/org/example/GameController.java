package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import org.example.model.*;
import org.example.model.Regles.Jeton;
import org.junit.runner.manipulation.Ordering;

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

    private GraphicsContext graveyardContext;
    private CanvasManager canvasManager;

    private Variante<Jeton> gameVariante;


    private OrdonnanceurDeJeu ordonnanceurDeJeu;

    private Case caseOrigine = null;
    private Case caseDestination = null;

    private int indiceJoueur;
    private Set<Case> coupPossibles;

    private Deque<Integer> perdants = new ArrayDeque<>();

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
            indiceJoueur = 0;

            ordonnanceurDeJeu = new OrdonnanceurDeJeu(gameVariante.getJoueurs(), gameVariante.getPlateau());

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
        //todo bien sur
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

    private void jouerCoup() {
        try {
            String name = caseOrigine.getPieceOnCase().getName();
            System.out.println(joueurQuiJoue().getName() + " : " + name + " to " + caseDestination.getPosition());

            ordonnanceurDeJeu.deplacerPiece(caseOrigine, joueurQuiJoue(), caseDestination);

            addLabelCoup(joueurQuiJoue().getName() + " : " + name + " to " + caseDestination.getPosition());
            incrementerIndiceJoueur();
        } catch (DeplacementException e) {
            //showAlert(Alert.AlertType.ERROR, "PAS CONTENT !: "+e.getMessage());
            System.out.println(e.getMessage());
        }
        caseOrigine = null;
        coupPossibles = new LinkedHashSet<>();
        caseDestination = null;
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

    @FXML
    public void giveUpButton() throws IOException{
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
            getApp().setRoot("gameOver", new EndGameData((Variante<Jeton>) userVar, perdants));
        }
        else {
            updateCanvas();
        }
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
