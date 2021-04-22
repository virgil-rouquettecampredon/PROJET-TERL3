package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;
import org.example.model.Joueur;
import org.example.model.OrdreDesJoueurs;
import org.example.model.OrdreDesJoueursException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderPlayerController extends Controller{
    @FXML
    public TableView<PlayerTableRow> tablePlayer;
    @FXML
    public TableColumn<PlayerTableRow, String> nameColumn;
    @FXML
    public TableColumn<PlayerTableRow, Integer> teamColumn;

    private ObservableList<PlayerTableRow> playerData;

    @FXML
    public TableView<PlayerTableRow> tableOrderPlayer;
    @FXML
    public TableColumn<PlayerTableRow, String> nameOrderColumn;
    @FXML
    public TableColumn<PlayerTableRow, Integer> teamOrderColumn;

    private ObservableList<PlayerTableRow> orderedPlayerData;

    @Override
    public void initialise() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        teamColumn.setCellValueFactory(cellData -> cellData.getValue().teamProperty().asObject());

        nameOrderColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        teamOrderColumn.setCellValueFactory(cellData -> cellData.getValue().teamProperty().asObject());

        playerData = FXCollections.observableArrayList();
        ArrayList<Joueur> joueurs = getApp().varianteManager.getCurrent().getJoueurs();
        for (int i = 0; i < joueurs.size(); i++) {
            Joueur p = joueurs.get(i);
            playerData.add(new PlayerTableRow(p, i));
        }

        orderedPlayerData = FXCollections.observableArrayList();
        ArrayList<Joueur> list = getApp().varianteManager.getCurrent().getOrdreJoueurs();

        //for i pour être sûr de l'ordre
        for (int i = 0; i < list.size(); i ++) {
            int indice = getApp().varianteManager.getCurrent().getJoueurs().indexOf(list.get(i));

            orderedPlayerData.add(new PlayerTableRow(list.get(i), indice));
        }

        ContextMenu contextMenu = new ContextMenu();

        MenuItem menuItem1 = new MenuItem("Supprimer");

        menuItem1.setOnAction((event) -> {
            deleteSelectedOrderedPlayer();
        });

        contextMenu.getItems().addAll(menuItem1);

        tableOrderPlayer.setContextMenu(contextMenu);

        tablePlayer.setItems(playerData);
        tableOrderPlayer.setItems(orderedPlayerData);
    }

    public void confirmButton() throws IOException {
        getApp().soundManager.playSound("button-click");

        //todo VERIFICATION SUR playerData
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <  orderedPlayerData.size(); i++) {
            sb.append("J");
            sb.append(orderedPlayerData.get(i).getId()+1);
        }
        System.out.println(sb.toString());
        try {
            OrdreDesJoueurs.verifierOrdre(sb.toString(), getApp().varianteManager.getCurrent().getJoueurs().size());
        } catch (OrdreDesJoueursException e) {
            String message = "Erreur : "+e.getMessage();
            if (e.getIndice() != -1) {
                message += getApp().varianteManager.getCurrent().getJoueurs().get(e.getIndice()-1).getName();
            }
            showAlert(Alert.AlertType.ERROR, message);
            return;
        }

        List<Joueur> list = getApp().varianteManager.getCurrent().getOrdreJoueurs();
        list.clear();
        //for i pour l'ordre
        for (int i = 0; i <  orderedPlayerData.size(); i++) {
            list.add(orderedPlayerData.get(i).getJoueur());
        }

        getApp().setRoot("VarianteMenu2");
    }

    public void infoButton() {
        getApp().soundManager.playSound("button-click");
        showAlert(Alert.AlertType.INFORMATION, "C'est dans cette partie que vous pourrez définir l'ordre de jeu des joueurs de votre variante.\n" +
                "Si vous voulez ajouter un joueur à cette ordre afin de favoriser une personne ou de lui faire faaire 2 actions dans son tour, etc .... " +
                "Il suffit de cliquer sur un des joueurs à gauche pour le rajouter dans l'ordre (il sera ajouter à la fin).\n" +
                "Si vous vous êtes trompé, vous pourrez supprimer un joueur en faisant clic droit puis supprimer sur le joueur en question. Il sera donc supprimé de la séquence à l'emplacement voulu.");
    }

    public void deleteSelectedOrderedPlayer() {
        PlayerTableRow p = tableOrderPlayer.getSelectionModel().getSelectedItem();
        if (p != null) {
            orderedPlayerData.remove(p);
        }
        else {
            showAlert(Alert.AlertType.ERROR, "Erreur : Pas de joueur selectionne!");
        }
    }

    public void addSelectedToOrder() {
        PlayerTableRow p = tablePlayer.getSelectionModel().getSelectedItem();
        if (p != null) {
            orderedPlayerData.add(new PlayerTableRow(p.getJoueur(), p.getId()));
        }
        else {
            showAlert(Alert.AlertType.ERROR, "Erreur : Pas de joueur selectionne!");
        }
    }
}
