package org.example;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class PlayerController extends Controller {
    @FXML
    public TextField nbPlayerInput;
    @FXML
    private TableView<PlayerTableRow> tablePlayer;
    @FXML
    private TableColumn<PlayerTableRow, String> nameColumn;
    @FXML
    private TableColumn<PlayerTableRow, Integer> teamColumn;

    private ObservableList<PlayerTableRow> data;

    @FXML
    private void confirmButton() throws IOException {
        App.soundManager.playSound("button-click");
        //TODO: enregistrer les trucs
        System.out.println(nbPlayerInput.getText());
        data.forEach(System.out::println);
        getApp().setRoot("VarianteMenu1");
    }

    @FXML
    public void initialize() {
        //TODO REMPLIR PROPREMENT AVEC LES DONNES ENREGISTREES
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        teamColumn.setCellValueFactory(cellData -> cellData.getValue().teamProperty().asObject());
        data = FXCollections.observableArrayList(new PlayerTableRow("Player1", 0), new PlayerTableRow("Player2", 1));
        tablePlayer.setItems(data);
    }

    private static class PlayerTableRow {
        private final SimpleStringProperty name;
        private final SimpleIntegerProperty team;

        private PlayerTableRow(String name, int team) {
            this.name = new SimpleStringProperty(name);
            this.team = new SimpleIntegerProperty(team);
        }

        public SimpleStringProperty nameProperty() {
            return name;
        }

        public int getTeam() {
            return team.get();
        }

        public SimpleIntegerProperty teamProperty() {
            return team;
        }

        public void setTeam(int team) {
            this.team.set(team);
        }

        public String getName() {
            return name.get();
        }

        public void setName(String name) {
            this.name.set(name);
        }

        @Override
        public String toString() {
            return "PlayerTableRow{" +
                    "name=" + name +
                    ", team=" + team +
                    '}';
        }
    }
}
