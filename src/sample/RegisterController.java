package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private  javafx.scene.control.Label label;

    @FXML
    private  javafx.scene.control.TextField lastName;

    @FXML
    private javafx.scene.control.TextField firstName;

    @FXML
    private javafx.scene.control.TextField department;

    @FXML
    private javafx.scene.control.TextField email;

    @FXML
    private javafx.scene.control.TextArea address;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }

    static ObservableList<Student> studentList = FXCollections.observableArrayList();

    @FXML
    private void saveButton(javafx.event.ActionEvent event) throws IOException {
        String studentLastName = lastName.getText();
        String studentFirstName = firstName.getText();
        String studentDepartment = department.getText();
        String studentEmail = email.getText();
        String studentAddress = address.getText();

        if(studentLastName.equals("") && studentFirstName.equals("") && studentDepartment.equals("") && studentEmail.equals("") && studentAddress.equals("")) return;

        File file = new File("data.txt");
        if(!file.exists()) file.createNewFile();

        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.write(studentLastName + " # " + studentFirstName + " # " + studentDepartment + " # " + studentEmail + " # " + studentAddress + "\n");
        fileWriter.close();

        label.setText(studentLastName + " " + studentFirstName + " has been save successfully");

        lastName.clear();
        firstName.clear();
        department.clear();
        email.clear();
        address.clear();

    }

    @FXML
    private void resetButton(ActionEvent e) {
        lastName.clear();
        firstName.clear();
        department.clear();
        email.clear();
        address.clear();
    }
}
