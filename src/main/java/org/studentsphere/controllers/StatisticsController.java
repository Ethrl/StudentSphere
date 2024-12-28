package org.studentsphere.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import org.studentsphere.helpers.Database;
import org.studentsphere.helpers.SceneLoader;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatisticsController {

    @FXML
    private Label totalStudentsLabel;

    @FXML
    private Label totalCoursesLabel;

    @FXML
    private Label popularCourseLabel;

    @FXML
    private PieChart coursePieChart;

    @FXML
    public void initialize() {
        loadStatistics();
    }

    private void loadStatistics() {
        try (Connection connection = Database.connect()) {
            // Total students
            ResultSet studentsResult = connection.createStatement().executeQuery("SELECT COUNT(*) AS total FROM students");
            if (studentsResult.next()) {
                totalStudentsLabel.setText("Total Students: " + studentsResult.getInt("total"));
            }

            // Total courses
            ResultSet coursesResult = connection.createStatement().executeQuery("SELECT COUNT(*) AS total FROM courses");
            if (coursesResult.next()) {
                totalCoursesLabel.setText("Total Courses: " + coursesResult.getInt("total"));
            }

            // Most popular course
            String popularCourseQuery = """
                SELECT courses.name, COUNT(course_students.student_id) AS student_count
                FROM courses
                LEFT JOIN course_students ON courses.id = course_students.course_id
                GROUP BY courses.id
                ORDER BY student_count DESC
                LIMIT 1
            """;
            ResultSet popularCourseResult = connection.createStatement().executeQuery(popularCourseQuery);
            if (popularCourseResult.next()) {
                popularCourseLabel.setText("Most Popular Course: " + popularCourseResult.getString("name"));
            } else {
                popularCourseLabel.setText("Most Popular Course: N/A");
            }

            // Students per course chart
            String courseChartQuery = """
                SELECT courses.name, COUNT(course_students.student_id) AS student_count
                FROM courses
                LEFT JOIN course_students ON courses.id = course_students.course_id
                GROUP BY courses.id
            """;
            ResultSet courseChartResult = connection.createStatement().executeQuery(courseChartQuery);
            while (courseChartResult.next()) {
                coursePieChart.getData().add(new PieChart.Data(
                        courseChartResult.getString("name"),
                        courseChartResult.getInt("student_count")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error loading statistics: " + e.getMessage());
        }
    }

    @FXML
    private void goToDashboard() {
        SceneLoader.loadScene("/views/dashboard.fxml");
    }

}