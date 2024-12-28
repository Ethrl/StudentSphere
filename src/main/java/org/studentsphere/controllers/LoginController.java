package org.studentsphere.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.mindrot.jbcrypt.BCrypt;
import org.studentsphere.helpers.Database;
import org.studentsphere.helpers.SceneLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField usernameField; // Używamy usernameField zamiast emailField

    @FXML
    private PasswordField passwordField;

    @FXML
    private void loginUser() {
        String inputUsername = usernameField.getText().trim(); // Pobieramy username zamiast email
        String inputPassword = passwordField.getText().trim();

        if (inputUsername.isEmpty() || inputPassword.isEmpty()) {
            showAlert("Error", "All fields are required.");
            return;
        }

        try (Connection connection = Database.connect()) {
            String sql = "SELECT * FROM users WHERE username = ? LIMIT 1"; // Wyszukujemy po username
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, inputUsername);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String storedHashedPassword = resultSet.getString("password");
                String role = resultSet.getString("role");

                if (BCrypt.checkpw(inputPassword, storedHashedPassword)) {
                    if ("ADMIN".equalsIgnoreCase(role)) {
                        // Admin Dashboard
                        SceneLoader.loadScene("/views/adminDashboard.fxml");
                    } else {
                        // User Dashboard
                        SceneLoader.loadScene("/views/dashboard.fxml");
                    }
                    return;
                }
            }

            // Jeśli hasło nie pasuje lub użytkownik nie istnieje
            showAlert("Error", "Invalid credentials.");
        } catch (SQLException e) {
            showAlert("Error", "Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void goBackToMain() {
        SceneLoader.loadScene("/views/main.fxml");
    }
}