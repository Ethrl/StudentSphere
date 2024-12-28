package org.studentsphere.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.studentsphere.helpers.SceneLoader;
import org.studentsphere.models.UserModel;

public class AdminLoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private final UserModel userModel = new UserModel();

    @FXML
    private void loginAdmin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "All fields are required.");
            return;
        }

        boolean isAuthenticated = userModel.authenticate(username, password);

        if (isAuthenticated) {
            SceneLoader.loadScene("/views/dashboard.fxml"); // Przekierowanie na dashboard
        } else {
            showAlert("Error", "Invalid username or password.");
        }
    }

    @FXML
    private void goBackToMain() {
        SceneLoader.loadScene("/views/main.fxml");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}