package org.studentsphere.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.studentsphere.helpers.Database;
import org.studentsphere.helpers.SceneLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddCourseController {

    @FXML
    private TextField courseNameField;

    @FXML
    private TextField courseDescriptionField;

    @FXML
    private void addCourse() {
        String courseName = courseNameField.getText().trim();
        String courseDescription = courseDescriptionField.getText().trim();

        if (courseName.isEmpty()) {
            showAlert("Error", "Course name cannot be empty.");
            return;
        }

        try (Connection connection = Database.connect()) {
            String sql = "INSERT INTO courses (name, description) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, courseName);
            statement.setString(2, courseDescription);
            statement.executeUpdate();
            showAlert("Success", "Course added successfully.");
            SceneLoader.loadScene("/views/manageCourses.fxml");
        } catch (SQLException e) {
            showAlert("Error", "Failed to add course: " + e.getMessage());
        }
    }

    @FXML
    private void goBackToCourses() {
        SceneLoader.loadScene("/views/manageCourses.fxml");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}