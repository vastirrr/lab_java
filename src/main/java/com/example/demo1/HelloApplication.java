package com.example.demo1;

import Domain.Tort;
import Domain.TortFactory;
import Repository.*;
import Service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, DuplicateEntityException {
        //schimba Entity!!
        IRepository<Tort> repository = null;
        Settings settings = new Settings();
        if(settings.getRepositoryType().equals("database")){
            repository = new JdbcRepository();
        } else if (settings.getRepositoryType().equals("text")) {
            String filename = settings.getPatientsFile();
            repository = new TextFileRepository<Tort>(filename, new TortFactory());
        }else if(settings.getRepositoryType().equals("binary")) {
            String filename = settings.getPatientsFile();
            repository = new BinaryRepository<>(filename);
        }

        Service service = new Service(repository);
        HelloController controller = new HelloController(service);

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