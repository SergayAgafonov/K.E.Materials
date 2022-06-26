package com.example.materialscompany;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GlavnoeMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button autorizationButton;

    @FXML
    private Button registrationButton;

    @FXML
    private Button menuButton;

    @FXML
    void initialize() {
        registrationButton.setOnAction(event -> {
            registrationButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("registration-view.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent room = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(room));
            stage.showAndWait();
        });


        menuButton.setOnAction(event -> {
            menuButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("glavnaya.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent room = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(room));
            stage.showAndWait();
        });

        autorizationButton.setOnAction(event -> {
            autorizationButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("autorization.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent room = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(room));
            stage.showAndWait();
        });

    }

}
