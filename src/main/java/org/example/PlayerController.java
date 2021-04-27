package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import org.example.model.Joueur;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerController extends Controller {
    @FXML
    public TextField nbPlayerInput;
    @FXML
    private TableView<PlayerTableRow> tablePlayer;
    @FXML
    private TableColumn<PlayerTableRow, String> nameColumn;
    @FXML
    private TableColumn<PlayerTableRow, Integer> teamColumn;
    @FXML
    private TableColumn<PlayerTableRow, Integer> timerMinuteColumn;
    @FXML
    private TableColumn<PlayerTableRow, Integer> timerSecondeColumn;

    private ObservableList<PlayerTableRow> data;

    @FXML
    private void confirmButton() throws IOException {
        getApp().soundManager.playSound("button-click");

        if (data.size() < 1) {
            showAlert(Alert.AlertType.ERROR, "Erreur : pas assez de joueur (1 au minimum)");
            return;
        }
        for (PlayerTableRow prow : data) {
            if (prow.getTimerMinute() == 0 && prow.getTimerSeconde() == 0 || prow.getTimerMinute() < 0 || prow.getTimerSeconde() < 0 || prow.getTimerSeconde() >= 60) {
                showAlert(Alert.AlertType.ERROR, "Erreur : temps de "+prow.getTimerMinute()+" minute et "+prow.getTimerSeconde()+" seconde impossible!" +
                        "\nLe joueur est "+prow.getJoueur().getName());
                return;
            }
        }


        List<Joueur> list = getApp().varianteManager.getCurrent().getJoueurs();
        list.clear();
        getApp().varianteManager.getCurrent().getPlateau().clear();
        for (PlayerTableRow prow:
             data) {
            Joueur j =new Joueur(prow.getName(), prow.getTeam());
            j.setMinute(prow.getTimerMinute());
            j.setSec(prow.getTimerSeconde());
            list.add(j);
        }

        /*System.out.println(nbPlayerInput.getText());
        data.forEach(System.out::println);*/

        getApp().setRoot("VarianteMenu1");
    }

    @Override
    public void initialise() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        teamColumn.setCellValueFactory(cellData -> cellData.getValue().teamProperty().asObject());
        teamColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        timerMinuteColumn.setCellValueFactory(cellData -> cellData.getValue().timerMinuteProperty().asObject());
        timerMinuteColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        timerSecondeColumn.setCellValueFactory(cellData -> cellData.getValue().timerSecondeProperty().asObject());
        timerSecondeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));


        data = FXCollections.observableArrayList();
        ArrayList<Joueur> joueurs = getApp().varianteManager.getCurrent().getJoueurs();
        for (int i = 0; i < joueurs.size(); i++) {
            Joueur p = joueurs.get(i);
            data.add(new PlayerTableRow(p, i));
        }

        ContextMenu contextMenu = new ContextMenu();

        MenuItem menuItem1 = new MenuItem("delete");

        menuItem1.setOnAction((event) -> {
            deleteSelectedPlayer();
        });

        contextMenu.getItems().addAll(menuItem1);

        tablePlayer.setContextMenu(contextMenu);

        nbPlayerInput.setText(""+data.size());

        tablePlayer.setItems(data);
    }

    public void deleteSelectedPlayer() {
        PlayerTableRow p = tablePlayer.getSelectionModel().getSelectedItem();
        if (p != null) {
            data.remove(p);
            updateInput();
        }
        else {
            decrementNbPlayer();
        }
    }

    @FXML
    public void inputAction() {
        int v = data.size();
        try {
            v = Integer.parseInt(nbPlayerInput.getText());
        } catch(NumberFormatException ignore) {
        }
        if (data.size() < v) {
            for (int i = data.size(); i < v; i++) {
                data.add(new PlayerTableRow("Joueur"+(data.size()+1), (data.size()+1)%2, (data.size()+1)));
            }
        }
        else if (data.size() > v && v >= 0){
            for (int i = data.size(); i > v; i--) {
                data.remove(data.get(data.size()-1));
            }
        }
    }

    private void updateInput() {
        nbPlayerInput.setText(""+data.size());
    }

    @FXML
    public void incrementNbPlayer() {
        data.add(new PlayerTableRow("Joueur"+(data.size()+1), (data.size())%2, (data.size()+1)));
        updateInput();
    }

    @FXML
    public void decrementNbPlayer() {
        if(!data.isEmpty()){
            data.remove(data.get(data.size()-1));
            updateInput();
        }
    }

    public void infoButton() {
        showAlert(Alert.AlertType.INFORMATION, "Vous pouvez augmenter ou réduire le nombre de joueur pour cette variante soit en cliquant sur le bouton + et -, soit en entrant directement la valeur dans l'encart.\n\nVous pouvez aussi modifier le nom et l'équipe d'un joueur en cliquant gauche soit sur le nom, soit sur l'équipe du joueur en question.\n\nEnsuite si vous voulez supprimer un joueur en particulier, il suffit de faire un clique droit sur ce joueur pouis appuyer sur le bouton delete qui sera apparu.\n\nPour finir la variante doit avoir au minimum 1 joueur.");
    }

}
