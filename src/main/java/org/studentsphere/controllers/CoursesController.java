package org.studentsphere.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.studentsphere.helpers.Database;
import org.studentsphere.helpers.SceneLoader;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CoursesController {

    @FXML
    private ListView<String> coursesList;

    @FXML
    private void initialize() {
        loadCourses();
    }

    private void loadCourses() {
        coursesList.getItems().clear();
        try (Connection connection = Database.connect()) {
            String sql = "SELECT name FROM courses";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                coursesList.getItems().add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            System.err.println("Error loading courses: " + e.getMessage());
        }
    }

    @FXML
    private void showAddCourseForm() {
        SceneLoader.loadScene("/views/addCourse.fxml");
    }

    @FXML
    private void goBackToMain() {
        SceneLoader.loadScene("/views/main.fxml");
    }
}