package com.example.materialscompany;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SostoyanieSkladaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<SostoyanieSkladaM> table1;

    @FXML
    private Pane paneAdd1;

    @FXML
    private TableColumn<SostoyanieSkladaM, Integer> id;

    @FXML
    private TableColumn<SostoyanieSkladaM, String> edizmerenie;

    @FXML
    private TableColumn<SostoyanieSkladaM, String> kolechestvo;

    @FXML
    private TableColumn<SostoyanieSkladaM, String> naimenovanie;

    @FXML
    private Button naGlavnuyuButton;

    @FXML
    private Button btnRem1;

    @FXML
    private Button btnSave1;

    @FXML
    private Button btnAdd1;

    @FXML
    private Button btnBack1;

    @FXML
    private TextField tfNaim;

    @FXML
    private TextField tfNum1;

    @FXML
    private TextField tfKol;

    @FXML
    private TextField tfEdIz;

    @FXML
    private TextField poiskField;

    @FXML
    private Button vernutsaVMenuButton;

    private SostoyanieSkladaM selectedTable1 = new SostoyanieSkladaM();

    private final ObservableList<SostoyanieSkladaM> tableData1 = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        edizmerenie.setCellValueFactory(new PropertyValueFactory<>("edizmerenie"));
        kolechestvo.setCellValueFactory(new PropertyValueFactory<>("kolechestvo"));
        naimenovanie.setCellValueFactory(new PropertyValueFactory<>("naimenovanie"));

        tablerefresh();

        naGlavnuyuButton.setOnAction(event -> {
            naGlavnuyuButton.getScene().getWindow().hide();

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


        vernutsaVMenuButton.setOnAction(event -> {
            vernutsaVMenuButton.getScene().getWindow().hide();

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

        btnRem1.setOnAction(event -> {
            selectedTable1 = table1.getSelectionModel().getSelectedItem();
            selectedTable1.getId();
            deleteTable1(selectedTable1.getId());
            table1.getItems().remove(selectedTable1);
        });

        btnAdd1.setOnAction(event ->

        {
            paneAdd1.setVisible(true);
        });
        btnSave1.setOnAction(event ->

        {
            paneAdd1.setVisible(false);
            if (!(tfNum1.getText().isEmpty() || tfKol.getText().isEmpty() || tfNaim.getText().isEmpty()
                    || tfEdIz.getText().isEmpty())) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                    try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/datauser",
                            "root", "MySQL")) {
                        PreparedStatement statement = conn.prepareStatement
                                ("INSERT into datasostoyanieskladam(id,naimenovanie,kolechestvo,edizmerenie) VALUES(?,?,?,?)");
                        statement.setString(1, tfNum1.getText());
                        statement.setString(2, tfNaim.getText());
                        statement.setString(3, tfKol.getText());
                        statement.setString(4, tfEdIz.getText());
                        statement.executeUpdate();
                        tablerefresh();
                    }
                } catch (Exception e) {
                    System.out.println("Проблема с таблицей");
                }
            }
        });
        btnBack1.setOnAction(event ->

        {
            paneAdd1.setVisible(false);
        });

    }

    public static void deleteTable1(int Code1) {
        String querry = "DELETE FROM datasostoyanieskladam WHERE `id` = " + Code1;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/datauser",
                    "root", "MySQL");
            PreparedStatement preparedStatement = conn.prepareStatement(querry);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void tablerefresh() {

        tableData1.clear();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/datauser", // бд пароль и название поменять
                    "root", "MySQL")) {
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM datasostoyanieskladam");        // название таблицы свое поменять
                while (resultSet.next()) {
                    tableData1.add(new SostoyanieSkladaM(
                            resultSet.getInt("id"),
                            resultSet.getString("naimenovanie"),
                            resultSet.getString("kolechestvo"),
                            resultSet.getString("edizmerenie")));
                }
            }
            if (!tableData1.isEmpty()){
                table1.setItems(tableData1);
            }
        }
        catch (Exception e){
        System.out.println("Ошибка с таблицей");
        }

    }
}