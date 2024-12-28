package org.studentsphere.controllers;

import javafx.fxml.FXML;
import org.studentsphere.helpers.SceneLoader;

public class DashboardController {

    @FXML
    private void goToManageStudents() {
        SceneLoader.loadScene("/views/listStudents.fxml");
    }

    @FXML
    private void goToManageCourses() {
        SceneLoader.loadScene("/views/manageCourses.fxml");
    }

    @FXML
    private void goToStatistics() {
        SceneLoader.loadScene("/views/statistics.fxml");
    }

    @FXML
    private void goToExportData() {
        org.studentsphere.helpers.SceneLoader.loadScene("/views/exportData.fxml");
    }

    @FXML
    private void logout() {
        SceneLoader.loadScene("/views/login.fxml");
    }
}