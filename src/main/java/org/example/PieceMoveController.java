package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import org.example.model.*;
import org.example.model.EquationDeDeplacement.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PieceMoveController extends Controller {
    @FXML
    public TextField nomInput;
    @FXML
    public Canvas canvas;
    @FXML
    public ComboBox<JoueurBox> joueurBox;
    @FXML
    public ImageView image;
    @FXML
    public Button boxButton;
    @FXML
    public Button arrowButton;
    @FXML
    public ToggleGroup caseGroup;
    @FXML
    public RadioButton deplacerRadio;
    @FXML
    public RadioButton prendreRadio;
    @FXML
    public RadioButton bothRadio;
    @FXML
    public CheckBox promouvableBox;
    @FXML
    public CheckBox victoireBox;
    @FXML
    public CheckBox traitreBox;
    @FXML
    public BorderPane vieForm;
    @FXML
    public TextField vieInput;

    private CanvasManager canvasManager;
    private String file;
    private ArrayList<PositionDeDeplacement> posDeplacements;
    private ArrayList<VecteurDeDeplacement> vecDeplacements;

    private int posX, posY;

    private Tool tool;

    @Override
    public void initialise() {
        tool = Tool.BOX;
        deplacerRadio.setUserData(EquationDeDeplacement.TypeDeplacement.DEPLACER);
        prendreRadio.setUserData(EquationDeDeplacement.TypeDeplacement.PRENDRE);
        bothRadio.setUserData(EquationDeDeplacement.TypeDeplacement.BOTH);
        for (Joueur p : getApp().varianteManager.getCurrent().getJoueurs()) {
            joueurBox.getItems().add(new JoueurBox(p.getName(), p));
        }

        if (userVar != null) {
            Piece p = (Piece) userVar;
            nomInput.setText(p.getName());
            image.setImage(new Image(p.getSprite()));
            joueurBox.getSelectionModel().select(joueurBox.getItems().stream().filter(jb -> jb.getJoueur() == p.getJoueur()).findFirst().get());
            file = p.getSprite().split("file:")[1];
            posDeplacements = p.getPosDeplacements();
            vecDeplacements = p.getVecDeplacements();

            promouvableBox.setSelected(p.estPromouvable());
            traitreBox.setSelected(p.estTraitre());
            victoireBox.setSelected(p.estConditionDeVictoire());

            vieInput.setText(""+p.getNbLife());
        }
        else {
            joueurBox.getSelectionModel().selectFirst();
            nomInput.setText("Pawn");
            posDeplacements = new ArrayList<>();
            vecDeplacements = new ArrayList<>();
        }

        vieForm.visibleProperty().bindBidirectional(victoireBox.selectedProperty());

        Plateau p = getApp().varianteManager.getCurrent().getPlateau();
        posX = p.getWitdhX()/2;
        posY = p.getHeightY()/2;

        canvasManager = new CanvasManager(canvas, p);
        updateCanvas();
    }

    public void updateCanvas() {
        canvasManager.drawCanvas();
        canvasManager.drawDeplacement(image.getImage(), posX, posY, posDeplacements, vecDeplacements);
    }

    @FXML
    private void validateButton() throws IOException {
        getApp().soundManager.playSound("button-click");

        if (file == null || file.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erreur : image vide!");
            return;
        }

        File f = new File(file);
        if (!f.exists() || f.isDirectory()) {
            showAlert(Alert.AlertType.ERROR, "Erreur : image invalide : "+file);
            return;
        }

        if (nomInput.getText().length() == 0) {
            showAlert(Alert.AlertType.ERROR, "Erreur : nom invalide");
            return;
        }
        if (joueurBox.getSelectionModel().getSelectedItem() == null) {
            showAlert(Alert.AlertType.ERROR, "Erreur : joueur non selectionné");
            return;
        }

        int vie = -1;
        if (victoireBox.isSelected()) {
            try {
                vie = Integer.parseInt(vieInput.getText());
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Erreur : nombre de vie n'est pas un entier ("+vieInput.getText()+")");
                return;
            }
            if (vie < -1) {
                showAlert(Alert.AlertType.ERROR, "Erreur : nombre de vie inférieur à -1 ("+vieInput.getText()+")");
                return;
            }
        }
        
        Piece p;
        if (userVar != null) {
            p = (Piece) userVar;
            p.setName(nomInput.getText());
            p.setSprite("file:" + file);

            p.getJoueur().getTypePawnList().remove(p);
            p.setJoueur(joueurBox.getSelectionModel().getSelectedItem().getJoueur());
        }
        else {
            p = new Piece(nomInput.getText(), "file:" + file, joueurBox.getSelectionModel().getSelectedItem().getJoueur());
        }
        p.setEstPromouvable(promouvableBox.isSelected());
        p.setEstConditionDeVictoire(victoireBox.isSelected());
        p.setEstTraitre(traitreBox.isSelected());

        if (victoireBox.isSelected()) {
            p.setNbLife(vie);
        }

        p.getJoueur().getTypePawnList().add(p);


        getApp().setRoot("piece");
    }

    public void openImageButton() {
        getApp().soundManager.playSound("button-click");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selectionner une variante");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image (*.bmp, *.gif, *.jpeg, *.png)", "*.bmp", "*.gif", "*.jpeg", "*.png"));
        File f = fileChooser.showOpenDialog(getApp().scene.getWindow());

        if (f != null) {
            file = f.getAbsolutePath();

            image.setImage(new Image("file:"+file));
        }
    }

    public void selectBoxTool() {
        getApp().soundManager.playSound("button-click");
        tool = Tool.BOX;
        arrowButton.setDisable(false);
        boxButton.setDisable(true);
    }

    public void selectArowTool() {
        getApp().soundManager.playSound("button-click");
        tool = Tool.ARROW;
        arrowButton.setDisable(true);
        boxButton.setDisable(false);
    }

    public void infoButton() {
        showAlert(Alert.AlertType.INFORMATION, "Ici, vous pouvez modifier le nom de la pièce créée ou modifier, ainsi que le joueur auquel elle appartient et de même pour l'image de la pièce (Ce sera une image sur votre ordinateur).");
        showAlert(Alert.AlertType.INFORMATION, "Concernant les déplacements de la pièce, Le plateau situé à droite représente un plateau de jeu avec une taille définie précédement. Si vous voulez crééer des déplacements spécifiques telles que un déplacement simple ou un déplacement qui sert à prendre une pièce (comme pour le pion par exemple) ou bien un déplacement qui fait les deux.");
        showAlert(Alert.AlertType.INFORMATION,"Le selecteur d'outils, permet de faire des déplacements relatifs à la case tel que le cavalier peut le faire ou bien un déplamcent sur un axe tel qu'il est fait sur la tour par exemple. Avec ce principe,  vous pouvez faire un déplacement horizontal de 2 en 2 cases.\nMaintenant si vous vous être trompé sur un déplacements, vous pouvez le supprimer en faisant clique droit sur le déplacement et en sélectionnant le bon outils ainsi que le bon type de déplacement (Déplacer, Prendre, Les deux) et ensuite le déplacement sera donc supprimer.");
        showAlert(Alert.AlertType.INFORMATION,"De plus les pièces seront aussi définies selon 3 choix, promouvable (comme le pion par exemple), traître (c'est à dire que vous pourrez prendre des pièces alliées) ou encore condition de victoire (comme le roi).\nDe plus si une pièce est condition de victoire, un nombre de vies apparaître. Ce nombre signifie le nombre de fois que cette pièce peut être mise en échec (chaque mise en échec enlève 1 au nombre de vie) avant d'être considéré prise par l'adversaire, par défault il est à -1 (c'est à dire qu'il ne mourra pas pour un certains nombre de mise en échec sur la pièce comme pour le jeu de base).");

    }
    public void onClick(MouseEvent mouseEvent) {
        Case c = canvasManager.getCase(mouseEvent.getX(), mouseEvent.getY());
        int relativeX = c.getPosition().getX()-posX;
        int relativeY = c.getPosition().getY()-posY;

        if (caseGroup.getSelectedToggle() == null) {
            showAlert(Alert.AlertType.ERROR, "Erreur : aucun comportement de case selectionné!");
            return;
        }
        EquationDeDeplacement.TypeDeplacement typeDeplacement = (EquationDeDeplacement.TypeDeplacement) caseGroup.getSelectedToggle().getUserData();

        switch (mouseEvent.getButton())  {
            case PRIMARY -> {
                switch (tool) {
                    case BOX -> {
                        //add case to piece deplacement
                        if (!posDeplacements.contains(new PositionDeDeplacement(relativeX, relativeY, typeDeplacement)) && (relativeX != 0 || relativeY != 0)) {
                            posDeplacements.add(new PositionDeDeplacement(relativeX, relativeY, typeDeplacement));
                        }
                    }
                    case ARROW -> {
                        //add arrow to piece deplacement
                        if (!vecDeplacements.contains(new VecteurDeDeplacement(relativeX, relativeY, typeDeplacement)) && (relativeX != 0 || relativeY != 0)) {
                            vecDeplacements.add(new VecteurDeDeplacement(relativeX, relativeY, typeDeplacement));
                        }
                    }
                }
            }
            case SECONDARY -> {
                switch (tool) {
                    case BOX -> {
                        //remove case to piece deplacement
                        posDeplacements.remove(new PositionDeDeplacement(relativeX, relativeY, typeDeplacement));
                    }
                    case ARROW -> {
                        //remove arrow to piece deplacement
                        vecDeplacements.remove(new VecteurDeDeplacement(relativeX, relativeY, typeDeplacement));
                    }
                }
            }
            case MIDDLE -> {
                posX = c.getPosition().getX();
                posY = c.getPosition().getY();
            }
        }
        updateCanvas();
    }

    private static enum Tool {
        BOX, ARROW
    }
}
