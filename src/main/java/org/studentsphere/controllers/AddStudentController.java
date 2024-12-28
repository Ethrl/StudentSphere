package org.studentsphere.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.studentsphere.helpers.SceneLoader;
import org.studentsphere.models.Course;
import org.studentsphere.models.CourseModel;
import org.studentsphere.models.StudentModel;

public class AddStudentController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private ComboBox<String> courseComboBox;

    private final CourseModel courseModel = new CourseModel();
    private final StudentModel studentModel = new StudentModel();

    @FXML
    public void initialize() {
        loadCourses();
    }

    private void loadCourses() {
        ObservableList<String> courses = FXCollections.observableArrayList();
        courseModel.getCourses().forEach(course -> courses.add(course.getName()));
        courseComboBox.setItems(courses);
    }

    @FXML
    private void saveStudent() {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String email = emailField.getText().trim();
        String course = courseComboBox.getValue();

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || course == null) {
            System.err.println("All fields are required!");
            return;
        }

        studentModel.addStudent(firstName, lastName, email, course);
        goToListStudents();
    }

    @FXML
    private void goToListStudents() {
        SceneLoader.loadScene("/views/listStudents.fxml");
    }
}