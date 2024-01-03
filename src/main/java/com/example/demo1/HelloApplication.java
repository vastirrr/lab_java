package com.example.demo1;

import Domain.Comanda;
import Domain.ComandaFactory;
import Domain.Tort;
import Domain.TortFactory;
import Repository.*;
import Service.ComandaService;
import Service.TortService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, DuplicateEntityException {
        //schimba Entity!!
        IRepository<Tort> tortRepository = null;
        IRepository<Comanda> comandaRepository = null;
        Settings settings = new Settings();
        if(settings.getRepositoryType().equals("database")){
            tortRepository = new TortJdbcRepository();
            comandaRepository = new ComandaJdbcRepository();
        } else if (settings.getRepositoryType().equals("text")) {
            String filename = settings.getTorturiFile();
            String filename2 = settings.getComandaFile();
            tortRepository = new TextFileRepository<Tort>(filename, new TortFactory());
            comandaRepository = new TextFileRepository<>(filename2, new ComandaFactory());
        }else if(settings.getRepositoryType().equals("binary")) {
            String filename = settings.getTorturiFile();
            String filename2 = settings.getComandaFile();
            tortRepository = new BinaryRepository<>(filename);
            comandaRepository = new BinaryRepository<>(filename2);
        }

        TortService tortService = new TortService(tortRepository);
        ComandaService comandaService = new ComandaService(comandaRepository, tortRepository);
        HelloController controller = new HelloController(tortService, comandaService);

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        fxmlLoader.setController(controller);

        Scene scene = new Scene(fxmlLoader.load(), 720, 740);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}