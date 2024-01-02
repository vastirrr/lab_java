package com.example.demo1;

import Domain.Comanda;
import Domain.Tort;
import Service.ComandaService;
import Service.TortService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HelloController {
    private TortService tortService;
    private ComandaService comandaService;
    @FXML
    private TextField idTextField;

    @FXML
    private ListView<Tort> listView;

    @FXML
    private TextField tipTextField;

    @FXML
    private Button addTortButton;

    @FXML
    private TextField torturiComandaTextField;


    @FXML
    private TextField idComandaTextField;

    @FXML
    private TextField dataComandaTextField;

    @FXML
    private ListView<Comanda> comandaListView;

    @FXML
    private Button addComandaButton;


    ObservableList<Tort> tortsList;
    ObservableList<Comanda> comandaList;
    public HelloController(TortService tortService, ComandaService comandaService) {
        this.tortService = tortService;
        this.comandaService = comandaService;
    }

    public void initialize(){
        tortsList = FXCollections.observableArrayList(tortService.getAll());
        listView.setItems(tortsList);

        comandaList =  FXCollections.observableArrayList(comandaService.getAll());
        comandaListView.setItems(comandaList);
    }

    @FXML
    void onAddMouseClicked(MouseEvent event) {
        try {
            int id = Integer.parseInt(idTextField.getText());
            String tipTextFieldText = tipTextField.getText();

            tortService.addTort(id, tipTextFieldText);
            tortsList.setAll(tortService.getAll());
        } catch (Exception e) {
            Alert hopa = new Alert(Alert.AlertType.ERROR);
            hopa.setTitle("ERROAREE!");
            hopa.setContentText(e.getMessage());
            hopa.show();
        }
    }
    @FXML
    void onAddComandaMouseClicked(MouseEvent event) {
        try {
            int id = Integer.parseInt(idComandaTextField.getText());
            String torturiId = torturiComandaTextField.getText();
            List<Integer> listId = new ArrayList<>();
            for (int i = 0; i < torturiId.split(",").length; i++){
                listId.add(Integer.parseInt(torturiId.split(",")[i]));
            }

            Date date = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse(dataComandaTextField.getText());
            comandaService.addComanda(id, listId, date);
            comandaList.setAll(comandaService.getAll());
        } catch (Exception e) {
            Alert hopa = new Alert(Alert.AlertType.ERROR);
            hopa.setTitle("ERROAREE!");
            hopa.setContentText(e.getMessage());
            hopa.show();
        }
    }
}