package com.example.materialscompany;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class RashodController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane paneAdd2;

    @FXML
    private TableView<Rashods> table2;

    @FXML
    private TableColumn<?, ?> codeobject;

    @FXML
    private TableColumn<?, ?> datarashoda;

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private Button naGlavnuyuButton;

    @FXML
    private TableColumn<?, ?> object;

    @FXML
    private TableColumn<?, ?> osnovanie;

    @FXML
    private TableColumn<?, ?> material;

    @FXML
    private TableColumn<?, ?> sotrudnik;

    @FXML
    private TableColumn<?, ?> summa;

    @FXML
    private TextField poiskField;

    @FXML
    private TextField tfNum2;

    @FXML
    private TextField tfDatRas;

    @FXML
    private TextField tfSot;

    @FXML
    private TextField tfCodeOb;

    @FXML
    private TextField tfObject;

    @FXML
    private TextField tfMat;

    @FXML
    private TextField tfOsnDRas;

    @FXML
    private TextField tfSumma1;

    @FXML
    private Button btnSave2;

    @FXML
    private Button btnBack2;

    @FXML
    private Button btnAdd2;

    @FXML
    private Button btnRem2;

    @FXML
    private Button vernutsaVMenuButton;

    private Rashods selectedTable2 = new Rashods();

    private final ObservableList<Rashods> tableData2 = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        datarashoda.setCellValueFactory(new PropertyValueFactory<>("datarashoda"));
        codeobject.setCellValueFactory(new PropertyValueFactory<>("codeobject"));
        object.setCellValueFactory(new PropertyValueFactory<>("object"));
        material.setCellValueFactory(new PropertyValueFactory<>("material"));
        sotrudnik.setCellValueFactory(new PropertyValueFactory<>("sotrudnik"));
        osnovanie.setCellValueFactory(new PropertyValueFactory<>("osnovanie"));
        summa.setCellValueFactory(new PropertyValueFactory<>("summa"));

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

        btnRem2.setOnAction(event -> {
            selectedTable2 = table2.getSelectionModel().getSelectedItem();
            selectedTable2.getId();
            deleteTable2(selectedTable2.getId());
            table2.getItems().remove(selectedTable2);
        });

        btnAdd2.setOnAction(event ->

        {
            paneAdd2.setVisible(true);
        });
        btnSave2.setOnAction(event ->

        {
            paneAdd2.setVisible(false);
            if (!(tfNum2.getText().isEmpty() || tfDatRas.getText().isEmpty() || tfSot.getText().isEmpty()
                    || tfCodeOb.getText().isEmpty() || tfObject.getText().isEmpty() || tfMat.getText().isEmpty() || tfOsnDRas.getText().isEmpty() || tfSumma1.getText().isEmpty())) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                    try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/datauser",
                            "root", "MySQL")) {
                        PreparedStatement statement = conn.prepareStatement
                                ("INSERT into datarashods(id,datarashoda,sotrudnik,codeobject,object,material,osnovanie,summa) VALUES(?,?,?,?,?,?,?,?)");
                        statement.setString(1, tfNum2.getText());
                        statement.setString(2, tfDatRas.getText());
                        statement.setString(3, tfSot.getText());
                        statement.setString(4, tfCodeOb.getText());
                        statement.setString(5, tfObject.getText());
                        statement.setString(6, tfMat.getText());
                        statement.setString(7, tfOsnDRas.getText());
                        statement.setString(8, tfSumma1.getText());
                        statement.executeUpdate();
                        tablerefresh();
                    }
                } catch (Exception e) {
                    System.out.println("Проблема с таблицей");
                }
            }
        });
        btnBack2.setOnAction(event ->

        {
            paneAdd2.setVisible(false);
        });
    }

    public static void deleteTable2(int Code1) {
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

        tableData2.clear();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/datauser",
                    "root", "MySQL")) {
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM datarashods");
                while (resultSet.next()) {
                    tableData2.add(new Rashods(
                            resultSet.getInt("id"),
                            resultSet.getString("datarashoda"),
                            resultSet.getString("sotrudnik"),
                            resultSet.getString("codeobject"),
                            resultSet.getString("object"),
                            resultSet.getString("material"),
                            resultSet.getString("osnovanie"),
                            resultSet.getString("summa")));
                }
            }
            if (!tableData2.isEmpty()){
                table2.setItems(tableData2);
            }
        }
        catch (Exception e){
            System.out.println("Ошибка с таблицей");
        }

    }
}