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

public class PostuplenieController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<PostuplenieM> table3;

    @FXML
    private Pane paneAdd3;

    @FXML
    private TableColumn<?, ?> cena;

    @FXML
    private TableColumn<?, ?> codematerials;

    @FXML
    private TableColumn<?, ?> datapostupleniya;

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<?, ?> edizmerenie;

    @FXML
    private TableColumn<?, ?> kolichestvo;

    @FXML
    private TableColumn<?, ?> materials;

    @FXML
    private TableColumn<?, ?> summa;

    @FXML
    private Button naGlavnuyuButton;

    @FXML
    private TextField poiskField;

    @FXML
    private TextField tfNum3;

    @FXML
    private TextField tfCena;

    @FXML
    private TextField tfDatPost;

    @FXML
    private TextField tfEdIz3;

    @FXML
    private TextField tfCodeMat;

    @FXML
    private TextField tfSumma3;

    @FXML
    private TextField tfMat3;

    @FXML
    private TextField tfKolvo;

    @FXML
    private Button vernutsaVMenuButton;

    @FXML
    private Button btnRem3;

    @FXML
    private Button btnSave3;

    @FXML
    private Button btnAdd3;

    @FXML
    private Button btnBack3;

    private PostuplenieM selectedTable3 = new PostuplenieM();

    private final ObservableList<PostuplenieM> tableData3 = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        cena.setCellValueFactory(new PropertyValueFactory<>("cena"));
        codematerials.setCellValueFactory(new PropertyValueFactory<>("codematerials"));
        datapostupleniya.setCellValueFactory(new PropertyValueFactory<>("datapostupleniya"));
        edizmerenie.setCellValueFactory(new PropertyValueFactory<>("edizmerenie"));
        kolichestvo.setCellValueFactory(new PropertyValueFactory<>("kolichestvo"));
        materials.setCellValueFactory(new PropertyValueFactory<>("materials"));
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

        btnRem3.setOnAction(event -> {
            selectedTable3 = table3.getSelectionModel().getSelectedItem();
            selectedTable3.getId();
            deleteTable3(selectedTable3.getId());
            table3.getItems().remove(selectedTable3);
        });

        btnAdd3.setOnAction(event ->

        {
            paneAdd3.setVisible(true);
        });
        btnSave3.setOnAction(event ->

        {
            paneAdd3.setVisible(false);
            if (!(tfNum3.getText().isEmpty() || tfCena.getText().isEmpty() || tfDatPost.getText().isEmpty()
                    || tfEdIz3.getText().isEmpty() || tfCodeMat.getText().isEmpty() || tfSumma3.getText().isEmpty() || tfMat3.getText().isEmpty() || tfKolvo.getText().isEmpty())) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                    try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/datauser",
                            "root", "MySQL")) {
                        PreparedStatement statement = conn.prepareStatement
                                ("INSERT into datapostupleniem(id,cena,codematerials,datapostupleniya,edizmerenie,kolichestvo,materials,summa) VALUES(?,?,?,?,?,?,?,?)");
                        statement.setString(1, tfNum3.getText());
                        statement.setString(2, tfCena.getText());
                        statement.setString(3, tfDatPost.getText());
                        statement.setString(4, tfEdIz3.getText());
                        statement.setString(5, tfCodeMat.getText());
                        statement.setString(6, tfSumma3.getText());
                        statement.setString(7, tfMat3.getText());
                        statement.setString(8, tfKolvo.getText());
                        statement.executeUpdate();
                        tablerefresh();
                    }
                } catch (Exception e) {
                    System.out.println("Проблема с таблицей");
                }
            }
        });
        btnBack3.setOnAction(event ->

        {
            paneAdd3.setVisible(false);
        });

    }

    public static void deleteTable3(int Code1) {
        String querry = "DELETE FROM datapostupleniem WHERE `id` = " + Code1;
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

        tableData3.clear();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/datauser", // бд пароль и название поменять
                    "root", "MySQL")) {
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM datapostupleniem");        // название таблицы свое поменять
                while (resultSet.next()) {
                    tableData3.add(new PostuplenieM(
                            resultSet.getInt("id"),
                            resultSet.getString("datapostupleniya"),
                            resultSet.getString("codematerials"),
                            resultSet.getString("materials"),
                            resultSet.getString("kolichestvo"),
                            resultSet.getString("cena"),
                            resultSet.getString("edizmerenie"),
                            resultSet.getString("summa")));
                }
            }
            if (!tableData3.isEmpty()){
                table3.setItems(tableData3);
            }
        }
        catch (Exception e){
            System.out.println("Ошибка с таблицей");
        }

    }

}
