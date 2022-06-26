package com.example.materialscompany;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Rashod extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Rashod.class.getResource("rashod.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 510);
        stage.setTitle("Rashod!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}