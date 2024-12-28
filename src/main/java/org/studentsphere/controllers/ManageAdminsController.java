package org.studentsphere.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.studentsphere.models.User;
import org.studentsphere.models.UserModel;

import java.util.List;

public class ManageAdminsController {

    @FXML
    private TableView<User> adminsTable;

    @FXML
    private TableColumn<User, Integer> idColumn;

    @FXML
    private TableColumn<User, String> usernameColumn;

    @FXML
    private TableColumn<User, String> emailColumn;

    private final UserModel userModel = new UserModel();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        loadAdmins();
    }

    private void loadAdmins() {
        List<User> admins = userModel.getAdmins();
        ObservableList<User> adminList = FXCollections.observableArrayList(admins);
        adminsTable.setItems(adminList);
    }

    @FXML
    private void deleteSelectedAdmin() {
        User selectedAdmin = adminsTable.getSelectionModel().getSelectedItem();
        if (selectedAdmin != null) {
            boolean isDeleted = userModel.deleteUser(selectedAdmin.getId());
            if (isDeleted) {
                showAlert("Success", "Admin deleted successfully.");
                loadAdmins(); // Refresh the table
            } else {
                showAlert("Error", "Failed to delete admin.");
            }
        } else {
            showAlert("Warning", "No admin selected.");
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
    private void goToDashboard() {
        // Assuming there is a method to go back to the admin dashboard
        // Replace the path with the actual path to your admin dashboard view
        org.studentsphere.helpers.SceneLoader.loadScene("/views/adminDashboard.fxml");
    }
}