package com.example.demo1;

import Domain.Comanda;
import Domain.Tort;
import Service.ComandaService;
import Service.TortService;
import ViewModels.TortNrComenzi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @FXML
    private ListView funcListView;
    @FXML
    private Button F1Button;

    @FXML
    private Button F3Button;

    @FXML
    private Button F2Button;
    @FXML
    private TextField startTextField;
    @FXML
    private TextField endTextField;

    @FXML
    private Button tortDateButton;



    ObservableList<Tort> tortsList;
    ObservableList<Comanda> comandaList;
    ObservableList<Map.Entry<Date, Integer>> F1List;
    ObservableList<Map.Entry<Integer, Integer>> F2List;
    ObservableList<TortNrComenzi> F3List;
    ObservableList<Tort> F4List;
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

    @FXML
    void F1onMouseClicked(MouseEvent event) {
        // am facut asa cu for ca sa avem trecute pe fiecare linie in aplicatie ..
        // altfel apareau toate pe un singur rand
        F1List =  FXCollections.observableArrayList();
        Map<Date, Integer> rezultat = comandaService.nrTorturiPerZi();

        for (Map.Entry<Date, Integer> element: rezultat.entrySet()) {
            F1List.add(element);
        }
//       FXCollections.sort(F1List, (o1, o2) -> o1.getValue().compareTo(o2.getValue())); //sortat crescator
        FXCollections.sort(F1List, (o1, o2) -> o2.getValue().compareTo(o1.getValue())); //sortat descrescator
        funcListView.setItems(F1List);
    }

    @FXML
    void F2onMouseClicked(MouseEvent event) {
        F2List =  FXCollections.observableArrayList();
        Map<Integer, Integer> rezultat = comandaService.nrTorturiPerLuna();

        for (Map.Entry<Integer, Integer> element: rezultat.entrySet()) {
            F2List.add(element);
        }
//       FXCollections.sort(F1List, (o1, o2) -> o1.getValue().compareTo(o2.getValue())); //sortat crescator
        FXCollections.sort(F2List, (o1, o2) -> o2.getValue().compareTo(o1.getValue())); //sortat descrescator
        funcListView.setItems(F2List);
    }

    @FXML
    void F3onMouseClicked(MouseEvent event) {
        F3List = FXCollections.observableArrayList(comandaService.celeMaiComandate());
        funcListView.setItems(F3List);
    }
    @FXML
    void TortDateOnMouseClicked(MouseEvent event) {
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse(startTextField.getText());
             endDate = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse(endTextField.getText());
        } catch (ParseException e) {
            Alert hopa = new Alert(Alert.AlertType.ERROR);
            hopa.setTitle("ERROAREE!");
            hopa.setContentText(e.getMessage());
            hopa.show();
        }
        F4List = FXCollections.observableArrayList(comandaService.torturiIntreDate(startDate, endDate));
        funcListView.setItems(F4List);
    }

}