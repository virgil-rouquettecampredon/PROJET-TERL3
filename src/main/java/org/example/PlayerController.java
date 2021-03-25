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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
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

    private ObservableList<PlayerTableRow> data;

    @FXML
    private void confirmButton() throws IOException {
        getApp().soundManager.playSound("button-click");

        List<Joueur> list = getApp().varianteManager.getCurrent().getJoueurs();
        list.clear();
        for (PlayerTableRow prow:
             data) {
            getApp().varianteManager.getCurrent().getJoueurs().add(new Joueur(prow.getName(), prow.getTeam()));
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


        data = FXCollections.observableArrayList();
        for (Joueur p:
             getApp().varianteManager.getCurrent().getJoueurs()) {
            data.add(new PlayerTableRow(p));
        }

        nbPlayerInput.setText(""+data.size());

        tablePlayer.setItems(data);
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
                data.add(new PlayerTableRow("Joueur"+(data.size()+1), (data.size()+1)%2));
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
        data.add(new PlayerTableRow("Joueur"+(data.size()+1), (data.size()+1)%2));
        updateInput();
    }

    @FXML
    public void decrementNbPlayer() {
        if(!data.isEmpty()){
            data.remove(data.get(data.size()-1));
            updateInput();
        }
    }

    private static class PlayerTableRow {
        private final SimpleStringProperty name;
        private final SimpleIntegerProperty team;

        private PlayerTableRow(String name, int team) {
            this.name = new SimpleStringProperty(name);
            this.team = new SimpleIntegerProperty(team);
        }

        private PlayerTableRow(Joueur p) {
            name = new SimpleStringProperty(p.getName());
            team = new SimpleIntegerProperty(p.getEquipe());
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
