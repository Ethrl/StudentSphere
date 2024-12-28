package org.studentsphere.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.studentsphere.models.UserModel;

public class ResetPasswordController {

    @FXML
    private TextField userIdField;

    @FXML
    private PasswordField newPasswordField;

    private final UserModel userModel = new UserModel();

    @FXML
    private void resetPassword() {
        String userIdText = userIdField.getText();
        String newPassword = newPasswordField.getText();

        if (userIdText.isEmpty() || newPassword.isEmpty()) {
            showAlert("Error", "Please fill in all fields.");
            return;
        }

        try {
            int userId = Integer.parseInt(userIdText);
            boolean success = userModel.resetPassword(userId, newPassword);

            if (success) {
                showAlert("Success", "Password reset successfully.");
            } else {
                showAlert("Error", "Failed to reset password.");
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "User ID must be a number.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}