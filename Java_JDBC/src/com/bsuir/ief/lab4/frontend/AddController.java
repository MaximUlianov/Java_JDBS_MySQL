package com.bsuir.ief.lab4.frontend;

import com.bsuir.ief.lab4.backend.client.Client;
import com.bsuir.ief.lab4.backend.sql.Faculty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddController {

    private Client client;

    @FXML
    private TextField adrText;

    @FXML
    private TextField studText;

    @FXML
    private TextField teachText;

    @FXML
    private Button addBtn;

    @FXML
    private TextField nameText;

    @FXML
    private TextField deanText;

    @FXML
    private TextField specText;

    @FXML
    public void initialize(){
        addBtn.setOnAction(event -> {
            try{
                Integer.parseInt(specText.getText());
                Integer.parseInt(studText.getText());
                Integer.parseInt(teachText.getText());
            }catch (NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Number format error");
                alert.setHeaderText(null);
                alert.setContentText("Incorrect data input");

                alert.showAndWait();
                return;
            }


            Faculty faculty = new Faculty(0, nameText.getText(), deanText.getText(), Integer.parseInt(specText.getText()),
                    Integer.parseInt(studText.getText()), Integer.parseInt(teachText.getText()), adrText.getText());
            client.addRequest(faculty);
            Stage stage = (Stage) addBtn.getScene().getWindow();
            stage.close();
        });
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
