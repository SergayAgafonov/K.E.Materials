package com.example.materialscompany;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistrationController {

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
    private Button signUpButton;

    @FXML
    private TextField signUpFamily;

    @FXML
    private TextField signUpName;

    @FXML
    void initialize() {

        signUpButton.setOnAction(event -> { signUpNewUser();

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

    private void signUpNewUser() {

        DataBaseHandler dbHandler = new DataBaseHandler();

        String name = signUpName.getText();
        String family = signUpFamily.getText();
        String login = login_field.getText();
        String password = password_field.getText();

        User user = new User(name, family, login, password);

        dbHandler.signUpUser(user);

    }

}