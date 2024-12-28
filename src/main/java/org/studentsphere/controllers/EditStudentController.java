package org.studentsphere.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.studentsphere.helpers.SceneLoader;
import org.studentsphere.models.Course;
import org.studentsphere.models.CourseModel;
import org.studentsphere.models.Student;
import org.studentsphere.models.StudentModel;

import java.util.List;

public class EditStudentController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private ComboBox<String> courseComboBox;

    private Student student;
    private final StudentModel studentModel = new StudentModel();
    private final CourseModel courseModel = new CourseModel();

    public void setStudent(Student student) {
        this.student = student;
        firstNameField.setText(student.getFirstName());
        lastNameField.setText(student.getLastName());
        emailField.setText(student.getEmail());
        courseComboBox.setValue(student.getCourse());
        loadCourses();
    }

    private void loadCourses() {
        List<Course> courses = courseModel.getCourses();
        List<String> courseNames = courses.stream()
                .map(Course::getName)
                .toList();
        courseComboBox.setItems(FXCollections.observableArrayList(courseNames));
    }

    @FXML
    private void saveChanges() {
        if (student != null) {
            student.setFirstName(firstNameField.getText().trim());
            student.setLastName(lastNameField.getText().trim());
            student.setEmail(emailField.getText().trim());
            student.setCourse(courseComboBox.getValue());

            if (studentModel.updateStudent(student)) {
                SceneLoader.loadScene("/views/listStudents.fxml");
            } else {
                showError("Error", "Failed to save changes.");
            }
        }
    }

    @FXML
    private void cancel() {
        SceneLoader.loadScene("/views/listStudents.fxml");
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}