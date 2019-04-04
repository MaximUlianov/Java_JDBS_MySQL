package com.bsuir.ief.lab4.frontend;

import com.bsuir.ief.lab4.backend.client.Client;
import com.bsuir.ief.lab4.backend.sql.Faculty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class TableController {

    private ObservableList<Faculty> faculties = FXCollections.observableArrayList();

    private Client client;

    private AddController addController;

    private EditController editController;

    @FXML
    private TableView<Faculty> mainTable;

    @FXML
    private TableColumn<Faculty, String> deanCol;

    @FXML
    private TableColumn<Faculty, Integer> idCol;

    @FXML
    private Button editBtn;

    @FXML
    private TableColumn<Faculty, String> nameCol;

    @FXML
    private TableColumn<Faculty, Integer> teachCol;

    @FXML
    private TableColumn<Faculty, Integer> specCol;

    @FXML
    private TableColumn<Faculty, String> adrCol;

    @FXML
    private TableColumn<Faculty, Integer> studCol;

    @FXML
    private Button addBtn;

    @FXML
    private Button deleteBtn;


    @FXML
    public  void initialize(){
        client = new Client();

        setColumns();
        showInfo();

        deleteBtn.setOnAction(event -> {
            if(!faculties.isEmpty()){

                Faculty faculty = mainTable.getSelectionModel().getSelectedItem();
                mainTable.getItems().remove(faculty);
                faculties.remove(faculty);
                client.deleteRequest(faculty);
                showInfo();

            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Delete information");
                alert.setHeaderText(null);
                alert.setContentText("Table is empty!");

                alert.showAndWait();
            }

        });

        addBtn.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader();

            addController = new AddController();
            addController.setClient(client);
            loader.setController(addController);

            loader.setLocation(getClass().getResource("adding.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Add row");
            stage.setResizable(false);
            stage.showAndWait();
            showInfo();
        });

        editBtn.setOnAction(event -> {
            Faculty faculty = mainTable.getSelectionModel().getSelectedItem();
            if(faculty != null){
                faculties.remove(faculty);
                FXMLLoader loader = new FXMLLoader();

                editController = new EditController();
                editController.setClient(client);
                editController.setFaculty(faculty);

                loader.setController(editController);

                loader.setLocation(getClass().getResource("edit.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Edit row");
                stage.setResizable(false);
                stage.showAndWait();
                showInfo();
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Edit information");
                alert.setHeaderText(null);
                alert.setContentText("None row selected!");

                alert.showAndWait();
            }

        });

    }

    public void setColumns(){
        idCol.setCellValueFactory(new PropertyValueFactory<Faculty, Integer>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Faculty, String>("name"));
        deanCol.setCellValueFactory(new PropertyValueFactory<Faculty, String>("dean"));
        specCol.setCellValueFactory(new PropertyValueFactory<Faculty, Integer>("specialitiesQuantity"));
        studCol.setCellValueFactory(new PropertyValueFactory<Faculty, Integer>("studentsQuantity"));
        teachCol.setCellValueFactory(new PropertyValueFactory<Faculty, Integer>("teachersQuantity"));
        adrCol.setCellValueFactory(new PropertyValueFactory<Faculty, String>("address"));
    }


    public void showInfo(){
        faculties = client.showRequest();
        mainTable.setItems(faculties);
    }



}
