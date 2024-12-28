package org.studentsphere.models;

import org.studentsphere.helpers.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseModel {
    public List<Course> getCourses() {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM courses";

        try (Connection connection = Database.connect();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                courses.add(new Course(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching courses: " + e.getMessage());
        }

        return courses;
    }

    public void addCourse(String name, String description) {
        String query = "INSERT INTO courses (name, description) VALUES (?, ?)";

        try (Connection connection = Database.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, name);
            statement.setString(2, description);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding course: " + e.getMessage());
        }
    }

    public void deleteCourse(int courseId) {
        String query = "DELETE FROM courses WHERE id = ?";
        try (Connection connection = Database.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, courseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting course: " + e.getMessage());
        }
    }
}