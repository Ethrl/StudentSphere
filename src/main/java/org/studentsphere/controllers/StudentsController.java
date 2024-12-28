package org.studentsphere.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.studentsphere.helpers.SceneLoader;
import org.studentsphere.models.Student;
import org.studentsphere.models.StudentModel;

public class StudentsController {

    @FXML
    private TableView<Student> studentsTable;
    @FXML
    private TableColumn<Student, String> firstNameColumn;
    @FXML
    private TableColumn<Student, String> lastNameColumn;
    @FXML
    private TableColumn<Student, String> emailColumn;
    @FXML
    private TableColumn<Student, String> courseColumn;

    private final StudentModel studentModel = new StudentModel();

    @FXML
    public void initialize() {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("course"));

        loadStudents();
    }

    private void loadStudents() {
        ObservableList<Student> students = FXCollections.observableArrayList(studentModel.getAllStudents());
        studentsTable.setItems(students);
    }

    @FXML
    private void goBack() {
        // Example navigation back to main menu
        SceneLoader.loadScene("/views/main.fxml");
    }
}