package org.studentsphere.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import org.studentsphere.helpers.SceneLoader;

public class AppController {

    private static AppController instance;

    @FXML
    private Menu viewMenu;

    public AppController() {
        instance = this;
    }

    public static AppController getInstance() {
        return instance;
    }

    public void enableViewMenu() {
        if (viewMenu != null) {
            viewMenu.setDisable(false);
        }
    }

    @FXML
    private void showRegisterForm() {
        SceneLoader.loadScene("/views/register.fxml");
    }

    @FXML
    private void showLoginForm() {
        SceneLoader.loadScene("/views/login.fxml");
    }

    @FXML
    private void showAdminLoginForm() {
        SceneLoader.loadScene("/views/adminLogin.fxml"); // Ładuje widok logowania administratora
    }

    @FXML
    private void showStudentList() {
        SceneLoader.loadScene("/views/listStudents.fxml");
    }

    @FXML
    private void showManageCourses() {
        SceneLoader.loadScene("/views/manageCourses.fxml");
    }

    @FXML
    private void showStatistics() {
        SceneLoader.loadScene("/views/statistics.fxml");
    }

    @FXML
    private void exitApp() {
        System.exit(0);
    }

    @FXML
    private void showAbout() {
        // Opcjonalna implementacja dla About.
    }
}