package org.studentsphere.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.studentsphere.helpers.CSVExporter;
import org.studentsphere.helpers.PDFExporter;
import org.studentsphere.helpers.SceneLoader;

import java.io.File;

public class ExportDataController {

    @FXML
    private ComboBox<String> dataTypeComboBox;
    @FXML
    private RadioButton csvRadioButton;
    @FXML
    private RadioButton pdfRadioButton;
    @FXML
    private Label selectedPathLabel;

    private String selectedPath;

    @FXML
    public void initialize() {
        dataTypeComboBox.getItems().addAll("Students", "Courses");

        // Ustawienie ToggleGroup, aby wymusić wybór tylko jednej opcji
        ToggleGroup formatGroup = new ToggleGroup();
        csvRadioButton.setToggleGroup(formatGroup);
        pdfRadioButton.setToggleGroup(formatGroup);

        csvRadioButton.setSelected(true); // Domyślnie zaznaczony CSV
    }

    @FXML
    private void chooseSaveLocation() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Save Location");

        // Ustawienie domyślnej nazwy pliku w zależności od wybranego typu danych
        String defaultFileName = dataTypeComboBox.getValue() != null
                ? dataTypeComboBox.getValue().toLowerCase() + (csvRadioButton.isSelected() ? ".csv" : ".pdf")
                : "export.csv";
        fileChooser.setInitialFileName(defaultFileName);

        // Filtry plików
        if (csvRadioButton.isSelected()) {
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        } else if (pdfRadioButton.isSelected()) {
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        }

        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            selectedPath = file.getAbsolutePath();
            selectedPathLabel.setText("Selected Path: " + selectedPath);
        } else {
            selectedPathLabel.setText("No path selected");
        }
    }

    @FXML
    private void exportData() {
        String dataType = dataTypeComboBox.getValue();
        if (dataType == null || selectedPath == null) {
            showAlert("Error", "Please select data type and save location.");
            return;
        }

        boolean isCSV = csvRadioButton.isSelected();

        try {
            if ("Students".equals(dataType)) {
                if (isCSV) {
                    CSVExporter.exportStudents(selectedPath);
                } else {
                    PDFExporter.exportStudents(selectedPath);
                }
            } else if ("Courses".equals(dataType)) {
                if (isCSV) {
                    CSVExporter.exportCourses(selectedPath);
                } else {
                    PDFExporter.exportCourses(selectedPath);
                }
            }
            showAlert("Success", "Data exported successfully to " + selectedPath);
        } catch (Exception e) {
            showAlert("Error", "Failed to export data: " + e.getMessage());
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