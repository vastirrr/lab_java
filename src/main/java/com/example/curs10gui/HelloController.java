package com.example.curs10gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to the Jungle!");
    }
    @FXML
    protected void onPWPButtonClick() {
        welcomeText.setText("XOXO GOSSIP GIRL!");
    }
    //Label tip .xml .. Label fx:.. in hello-view..
}