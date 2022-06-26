package com.example.materialscompany;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AutorizationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button glavnoeMenuButton;

    @FXML
    private TextField login_field;

    @FXML
    private TextField password_field;

    @FXML
    private Button registrationButton;

    @FXML
    private Button signUpButton;

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

        signUpButton.setOnAction(event -> {
            String loginText = login_field.getText().trim();
            String passwordText = password_field.getText().trim();

            if (!loginText.equals("") && !passwordText.equals(""))
                loginUser(loginText, passwordText);
            else
                System.out.println("Логин или пароль неверный.");
        });

        glavnoeMenuButton.setOnAction(event -> {
            glavnoeMenuButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("glavnoemenu.fxml"));

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

    private void loginUser(String loginText, String passwordText) {
        DataBaseHandler dbHandler = new DataBaseHandler();

        User user = new User();
        user.setlogin(loginText);
        user.setpassword(passwordText);
        ResultSet result = dbHandler.getUser(user);

        int counter = 0;

        while(true) {
            try {
                if (!result.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            counter++;
        }

        if (counter >= 1){
            System.out.println("Успешно");
            signUpButton.getScene().getWindow().hide();
            Autorization.OpenIcon("glavnaya.fxml");

        }
    }
}