package org.studentsphere.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.studentsphere.helpers.SceneLoader;
import org.studentsphere.models.Student;
import org.studentsphere.models.StudentModel;

import java.util.Optional;

public class ListStudentsController {

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
    private void editStudent() {
        Student selectedStudent = studentsTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            SceneLoader.loadSceneWithData("/views/editStudent.fxml", controller -> {
                if (controller instanceof EditStudentController) {
                    ((EditStudentController) controller).setStudent(selectedStudent);
                }
            });
        } else {
            showAlert("No Student Selected", "Please select a student to edit.");
        }
    }

    @FXML
    private void deleteStudent() {
        Student selectedStudent = studentsTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Delete Student");
            confirmationAlert.setHeaderText("Are you sure you want to delete this student?");
            confirmationAlert.setContentText("This action cannot be undone.");
            Optional<ButtonType> result = confirmationAlert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                if (studentModel.deleteStudent(selectedStudent.getId())) {
                    studentsTable.getItems().remove(selectedStudent);
                    showAlert("Success", "Student deleted successfully.");
                } else {
                    showAlert("Error", "Failed to delete student.");
                }
            }
        } else {
            showAlert("No Student Selected", "Please select a student to delete.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void addStudent() {
        SceneLoader.loadScene("/views/addStudent.fxml");
    }

    @FXML
    private void goToDashboard() {
        SceneLoader.loadScene("/views/dashboard.fxml");
    }
}