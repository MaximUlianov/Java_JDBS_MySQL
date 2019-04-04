package com.bsuir.ief.lab4.frontend;

import com.bsuir.ief.lab4.backend.client.Client;
import com.bsuir.ief.lab4.backend.sql.Faculty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditController {

    private Client client;

    private Faculty faculty;


    @FXML
    private TextField adrText;

    @FXML
    private TextField studText;

    @FXML
    private TextField teachText;

    @FXML
    private Button editBtn;

    @FXML
    private TextField nameText;

    @FXML
    private TextField deanText;

    @FXML
    private TextField specText;

    @FXML
    public void initialize(){
        String name = faculty.getName();

        nameText.setText(name);
        deanText.setText(faculty.getDean());
        specText.setText(Integer.toString(faculty.getSpecialitiesQuantity()));
        studText.setText(Integer.toString(faculty.getStudentsQuantity()));
        teachText.setText(Integer.toString(faculty.getTeachersQuantity()));
        adrText.setText(faculty.getAddress());

        editBtn.setOnAction(event -> {

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

            faculty.setName(nameText.getText());
            faculty.setDean(deanText.getText());
            faculty.setSpecialitiesQuantity(Integer.parseInt(specText.getText()));
            faculty.setStudentsQuantity(Integer.parseInt(studText.getText()));
            faculty.setTeachersQuantity(Integer.parseInt(teachText.getText()));
            faculty.setAddress(adrText.getText());


            client.updateRequest(name, faculty);

            Stage stage = (Stage) editBtn.getScene().getWindow();
            stage.close();
        });
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}

