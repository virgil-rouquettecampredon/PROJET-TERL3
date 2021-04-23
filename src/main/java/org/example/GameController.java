package org.example;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.example.model.*;
import org.example.model.Regles.Jeton;

import java.io.File;
import java.io.IOException;
import java.util.Random;

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

    private CanvasManager canvasManager;
    private Variante<Jeton> gameVariante;


    private OrdonnanceurDeJeu ordonnanceurDeJeu;

    private Case caseOrigine = null;
    private Case caseDestination = null;

    private int indiceJoueur;

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
            gameVariante = (Variante) userVar;
            varLabel.setText(gameVariante.getName());
            playerLabel.setText(gameVariante.getOrdrejoueur().get(0).getName());
            indiceJoueur = 0;

            ordonnanceurDeJeu = new OrdonnanceurDeJeu(gameVariante.getJoueurs(), gameVariante.getPlateau());

            canvasManager = new CanvasManager(canvas, gameVariante.getPlateau());
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
        canvasManager.drawPawn();
    }

    @FXML
    public void play(MouseEvent mouseEvent) throws IOException {
        //todo bien sur
        getApp().soundManager.playSound("button-hover");
        //System.out.println(mouseEvent);

        Case c = canvasManager.getCase(mouseEvent.getX(), mouseEvent.getY());

        if (c.isAccessible()) {
            if (caseOrigine == null) {
                if (c.getPieceOnCase() != null) {
                    caseOrigine = c;
                }
            } else {
                caseDestination = c;
            }
            if (caseDestination != null) {
                jouerCoup();
            }
        }
        updateCanvas();

        //todo a enlever
        if (c.getPosition().getX()==0 && c.getPosition().getY()==0) {
            getApp().setRoot("gameOver");
        }
    }

    private Joueur joueurQuiJoue() {
        return gameVariante.getOrdrejoueur().get(indiceJoueur);
    }

    private void jouerCoup() {
        System.out.println(joueurQuiJoue().getName() + " : " + caseOrigine.getPieceOnCase().getName() + " to " + caseOrigine.getPosition());
        try {
            ordonnanceurDeJeu.deplacerPiece(caseOrigine, joueurQuiJoue(), caseDestination);
            addLabelCoup(joueurQuiJoue().getName() + " : " + caseOrigine.getPieceOnCase().getName() + " to " + caseOrigine.getPosition());
            incrementerIndiceJoueur();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "PAS CONTENT !: "+e.getMessage());
        }
        caseOrigine = null;
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
        coupsBox.getChildren().add(new Label(message));
        scroll.setVvalue(2);
    }
}
