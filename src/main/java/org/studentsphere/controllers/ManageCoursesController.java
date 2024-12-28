package org.studentsphere.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import org.studentsphere.models.Course;
import org.studentsphere.models.CourseModel;
import org.studentsphere.helpers.SceneLoader;

import java.util.Optional;

public class ManageCoursesController {

    @FXML
    private TableView<Course> coursesTable;
    @FXML
    private TableColumn<Course, String> nameColumn;
    @FXML
    private TableColumn<Course, String> descriptionColumn;
    @FXML
    private TableColumn<Course, Void> deleteColumn;
    @FXML
    private TextField courseNameField;
    @FXML
    private TextArea courseDescriptionArea;

    private final CourseModel courseModel = new CourseModel();
    private ObservableList<Course> courses;

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        loadCourses();
    }

    private void loadCourses() {
        courses = FXCollections.observableArrayList(courseModel.getCourses());
        coursesTable.setItems(courses);
    }

    @FXML
    private void addCourse() {
        String name = courseNameField.getText().trim();
        String description = courseDescriptionArea.getText().trim();

        if (name.isEmpty()) {
            showAlert("Error", "Course name is required.");
            return;
        }

        courseModel.addCourse(name, description);
        loadCourses();
        courseNameField.clear();
        courseDescriptionArea.clear();
    }

    @FXML
    private void deleteCourse() {
        Course selectedCourse = coursesTable.getSelectionModel().getSelectedItem();
        if (selectedCourse == null) {
            showAlert("Error", "No course selected.");
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Delete Course");
        confirmation.setHeaderText(null);
        confirmation.setContentText("Are you sure you want to delete the selected course?");

        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            courseModel.deleteCourse(selectedCourse.getId());
            loadCourses();
        }
    }

    @FXML
    private void goToDashboard() {
        SceneLoader.loadScene("/views/dashboard.fxml");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}