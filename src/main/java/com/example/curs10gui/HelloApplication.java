package com.example.curs10gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    /* Java FX
        Modalitati de a aranja contolerele pe fereastra:
         1. Incarcand un fisiser .fxml -> prima linie din start
         2. Lucrand cu un fisier .fxmlo in Scene Builder
         3. Sa scriem code :D
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml")); // ne separa interfata grafica ..Fisier declarativ!!
        //VBox = fiec controler ce intra in el va fi pus vertical de sus in jos
        //Label cand dau click pe buton
        //Button ... onAction -> "cand apas butonu ce se va intampla" --> in ce cls gasim onHelloClick? in HelloController!!

        // TODO Nnu folosim mai multe moduri de a adauga contoalere in acelasi proiect
        Scene scene = new Scene(fxmlLoader.load(), 720, 720);
        stage.setTitle("Hello!");
        stage.setScene(scene);

       // System.out.println(scene.getRoot().getClass());
        // VBox rootNode = scene.getRoot(); --> da error

        VBox rootNode = (VBox) scene.getRoot();
        //rootNode.getChildren().add(new Button("Added from source code!"));

        Button btnAddedNow = new Button("Added from source code!");
        btnAddedNow.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Alert hopa = new Alert(Alert.AlertType.INFORMATION, "ups");
                hopa.show();
            }
        });

        rootNode.getChildren().add(btnAddedNow);

        stage.show(); // interfata apare pe ecran
    }

    public static void main(String[] args) {
        launch();
    }
}