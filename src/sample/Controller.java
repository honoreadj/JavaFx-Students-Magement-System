package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView<Student> studentTable;

    @FXML
    private TableColumn<Student, String> lastNameCol;

    @FXML
    private TableColumn<Student, String> firstNameCol;

    @FXML
    private TableColumn<Student, String> departmentCol;

    @FXML
    private TableColumn<Student, String> emailCol;

    @FXML
    private TableColumn<Student, String> addressCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        RegisterController.studentList.clear();
        try {

            studentTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            File file = new File("data.txt");
            if(!file.exists()) file.createNewFile();

            Scanner sc = new Scanner(file);
            while(sc.hasNext()) {
                String str = sc.nextLine();
                String parts[] = str.split("#");

                Student student = new Student(parts[0], parts[1], parts[2], parts[3], parts[4]);
                RegisterController.studentList.add(student);
            }

            lastNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
            firstNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
            departmentCol.setCellValueFactory(new PropertyValueFactory<Student, String>("department"));
            emailCol.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
            addressCol.setCellValueFactory(new PropertyValueFactory<Student, String>("address"));

            studentTable.setItems(RegisterController.studentList);

        } catch(Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   @FXML
    private void logRegister(javafx.event.ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Register.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void showAllStudent(javafx.event.ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AllStudent.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    public void minimize(ActionEvent event) {
        Main.primaryStage.setIconified(true);
    }

    @FXML
    private void exit(ActionEvent actionEvent) {
        Platform.exit();
    }

    @FXML
    private void removeButton(javafx.event.ActionEvent event) throws IOException {
        List<Student> selectedStudents = studentTable.getSelectionModel().getSelectedItems();
        RegisterController.studentList.removeAll(selectedStudents);

        File file = new File("data.txt");
        FileWriter fileWriter = new FileWriter(file);

        String str = "";
        for(Student std : RegisterController.studentList) {
            str += std.getLastName() + " # " + std.getFirstName() + " # " + std.getDepartment() + " # " + std.getEmail() + " # " + std.getAddress() + "\n";
        }

        fileWriter.write(str);
        fileWriter.close();
    }
}
