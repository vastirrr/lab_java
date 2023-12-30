package com.example.demo1;

import Domain.Entity;
import Service.Service;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class HelloController {
    private Service service;
    @FXML
    private TextField idTextField;

    @FXML
    private ListView<Entity> listView;

    public HelloController(Service service) {
        this.service = service;
    }
}