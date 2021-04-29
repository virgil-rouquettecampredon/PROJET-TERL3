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
    //Où recuperer les elements graphiques
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

    private CanvasManager canvasManager;                        // Pour dessiner sur le canvas
    private String file;                                        // String vers l'image de la piece
    private ArrayList<PositionDeDeplacement> posDeplacements;   // Les positions de deplacement
    private ArrayList<VecteurDeDeplacement> vecDeplacements;    // Les vecteurs de deplamancement

    private int posX, posY;     // La position relative de la piece

    private Tool tool;          // L'outil selectionné

    /**
     * Initialise les elements graphiques et recurere les donnes
     */
    @Override
    public void initialise() {
        tool = Tool.BOX;    // L'outil de base est celui des cases

        // On place la valeur d'un type de déplacement dans les boutons pour pouvoir les récupérer plus tard
        deplacerRadio.setUserData(EquationDeDeplacement.TypeDeplacement.DEPLACER);
        prendreRadio.setUserData(EquationDeDeplacement.TypeDeplacement.PRENDRE);
        bothRadio.setUserData(EquationDeDeplacement.TypeDeplacement.BOTH);

        // On récupère les données des joueurs sout forme de liste pour designer à quel joueur est la piece
        for (Joueur p : getApp().varianteManager.getCurrent().getJoueurs()) {
            joueurBox.getItems().add(new JoueurBox(p.getName(), p));
        }

        // On initialise tout avec :
        //  -   Soit la piece passée dans userVar (donc on a fait modifier)
        //  -   Soit avec les valeurs par défaut (donc on a fait ajouter)
        if (userVar != null) {
            // MODIFIER
            Piece p = (Piece) userVar;
            nomInput.setText(p.getName());
            image.setImage(new Image(p.getSprite()));
            // On selectionne le joueur dans la liste de joueur
            joueurBox.getSelectionModel().select(joueurBox.getItems().stream().filter(jb -> jb.getJoueur() == p.getJoueur()).findFirst().get());
            // On récurère uniquement le chemin du fichier (à droite du "file:...")
            file = p.getSprite().split("file:")[1];
            posDeplacements = p.getPosDeplacements();
            vecDeplacements = p.getVecDeplacements();

            promouvableBox.setSelected(p.estPromouvable());
            traitreBox.setSelected(p.estTraitre());
            victoireBox.setSelected(p.estConditionDeVictoire());

            vieInput.setText(""+p.getNbLife());
        }
        else {
            // AJOUTER
            joueurBox.getSelectionModel().selectFirst();
            nomInput.setText("Pawn");
            posDeplacements = new ArrayList<>();
            vecDeplacements = new ArrayList<>();
            file = new File("src/main/resources/org/example/images/pawn.png").getAbsolutePath();
        }

        // On "lie" le fait que l'entrèe pour mettre la vie soit visible avec
        //      le fait que la boite condition de victoire est cochée
        vieForm.visibleProperty().bindBidirectional(victoireBox.selectedProperty());

        Plateau p = getApp().varianteManager.getCurrent().getPlateau();
        // On place la position relative initiale aux milieux du plateau
        posX = p.getWitdhX()/2;
        posY = p.getHeightY()/2;

        // On initialise le dessinateur
        canvasManager = new CanvasManager(canvas, p);
        // On dessine
        updateCanvas();
    }

    /**
     * Fonction pour dessiner ce qu'il faut, c'est-à-dire le plateau puis les déplacements
     */
    public void updateCanvas() {
        canvasManager.drawCanvas();
        canvasManager.drawDeplacement(image.getImage(), posX, posY, posDeplacements, vecDeplacements);
    }

    /**
     * Effectué quand on clique sur "valider", fait les vérification et met la piece
     * @throws IOException si le menu des pieces n'existe pas
     */
    @FXML
    private void validateButton() throws IOException {
        getApp().soundManager.playSound("button-click");    // On joue le son du bouton

        //VERIFICATION
        //Image
        //image vide
        if (file == null || file.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erreur : image vide!");
            return;
        }

        //image inexistante
        File f = new File(file);
        if (!f.exists() || f.isDirectory()) {
            showAlert(Alert.AlertType.ERROR, "Erreur : image invalide : "+file);
            return;
        }

        //Nom vide
        if (nomInput.getText().length() == 0) {
            showAlert(Alert.AlertType.ERROR, "Erreur : nom invalide");
            return;
        }

        //Joueur vide
        if (joueurBox.getSelectionModel().getSelectedItem() == null) {
            showAlert(Alert.AlertType.ERROR, "Erreur : joueur non selectionné");
            return;
        }

        //Vie
        int vie = -1;
        if (victoireBox.isSelected()) {
            //Vie est un nombre
            try {
                vie = Integer.parseInt(vieInput.getText());
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Erreur : nombre de vie n'est pas un entier ("+vieInput.getText()+")");
                return;
            }
            //Vie n'est pas -1 ou 0
            if (vie < -1) {
                showAlert(Alert.AlertType.ERROR, "Erreur : nombre de vie inférieur à -1 ("+vieInput.getText()+")");
                return;
            }
            if (vie == 0){
                showAlert(Alert.AlertType.ERROR, "Vous ne pouvez pas créer une pièce avec un nombre de vie égal à 0, cela signifierai que la pièce est déjà morte");
                return;
            }
        }

        //MODIFICATION
        //Piece : Si on avait fait modifier, on modifie directement, sinon on fait une nouvelle piece
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
            p.setPosDeplacements(posDeplacements);
            p.setVecDeplacements(vecDeplacements);
        }
        p.setEstPromouvable(promouvableBox.isSelected());
        p.setEstConditionDeVictoire(victoireBox.isSelected());
        p.setEstTraitre(traitreBox.isSelected());

        if (victoireBox.isSelected()) {
            p.setNbLife(vie);
        }

        //On ajoute la piece au joueur
        p.getJoueur().getTypePawnList().add(p);


        //On charge le menu des pieces
        getApp().setRoot("piece");
    }

    /**
     * Executé sur le bouton pour charger une image : on ouvre une fenetre pour selectionner une image et on met le chemin dans file
     */
    public void openImageButton() {
        getApp().soundManager.playSound("button-click");    //On joue le son du bouton

        //FileChooser est une classe de JavaFX qui permet d'ouvrir une fenetre pour selectionner un fichier
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selectionner une variante");
        //Le dossier ouvers de base est le dossier utilisateur
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        //On autorise les fichiers de type .bmp, .gif, .jpeg, .png
        //Ce sont les images visiblespar javaFX
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image (*.bmp, *.gif, *.jpeg, *.png)", "*.bmp", "*.gif", "*.jpeg", "*.png"));
        //On ouvre la fenetre et on récupère dans la classe java "File"
        File f = fileChooser.showOpenDialog(getApp().scene.getWindow());

        //File est null si l'utilisateur a annulé
        if (f != null) {
            file = f.getAbsolutePath();

            image.setImage(new Image("file:"+file));
        }
        //On met à jour le plateau pour que la piece soit de la nouvelle image
        updateCanvas();
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

    /**
     * Exécuté quand on clique sur le canvas : On applique l'outils sur la case cliquée
     * @param mouseEvent Des details sur le clic, comme les coordonées X et Y
     */
    public void onClick(MouseEvent mouseEvent) {
        //On récupère la case
        Case c = canvasManager.getCase(mouseEvent.getX(), mouseEvent.getY());
        //On récupère les coordonées relatives à la piece
        int relativeX = c.getPosition().getX()-posX;
        int relativeY = c.getPosition().getY()-posY;

        //On récupère le type de déplacement sur les boutons grace aux "userData" défini à l'initialisation
        if (caseGroup.getSelectedToggle() == null) {
            showAlert(Alert.AlertType.ERROR, "Erreur : aucun comportement de case selectionné!");
            return;
        }
        EquationDeDeplacement.TypeDeplacement typeDeplacement = (EquationDeDeplacement.TypeDeplacement) caseGroup.getSelectedToggle().getUserData();

        //On applique l'outil en fonction du type de clic :
        //  -   Clic gauche (PRIMARY): on ajoute
        //  -   Clic droit (SECONDARY): on supprime
        //  -   Clic molette (MIDDLE) : on déplace la piece
        switch (mouseEvent.getButton())  {
            case PRIMARY -> {
                switch (tool) {
                    case BOX -> {
                        //add case to piece deplacement
                        PositionDeDeplacement pos = new PositionDeDeplacement(relativeX, relativeY, typeDeplacement);
                        if (!posDeplacements.contains(pos) && (relativeX != 0 || relativeY != 0)) {
                            posDeplacements.add(pos);
                        }
                    }
                    case ARROW -> {
                        //add arrow to piece deplacement
                        VecteurDeDeplacement vec = new VecteurDeDeplacement(relativeX, relativeY, typeDeplacement);
                        if (!vecDeplacements.contains(vec) && (relativeX != 0 || relativeY != 0)) {
                            vecDeplacements.add(vec);
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
        //On met à jour le plateau
        updateCanvas();
    }

    private static enum Tool {
        BOX, ARROW
    }
}
