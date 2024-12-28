package org.studentsphere.models;

import org.studentsphere.helpers.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentModel {

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";

        try (Connection connection = Database.connect();
             PreparedStatement statement = connection.prepareStatement(query);
             var resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Student student = new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("course")
                );
                students.add(student);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching students: " + e.getMessage());
        }

        return students;
    }

    public void addStudent(String firstName, String lastName, String email, String course) {
        String query = "INSERT INTO students (first_name, last_name, email, course) VALUES (?, ?, ?, ?)";

        try (Connection connection = Database.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, email);
            statement.setString(4, course);
            statement.executeUpdate();
            System.out.println("Student added successfully.");
        } catch (SQLException e) {
            System.err.println("Error adding student: " + e.getMessage());
        }
    }

    public boolean deleteStudent(int studentId) {
        String query = "DELETE FROM students WHERE id = ?";

        try (Connection connection = Database.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, studentId);
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting student: " + e.getMessage());
            return false;
        }
    }

    public boolean updateStudent(Student student) {
        String query = "UPDATE students SET first_name = ?, last_name = ?, email = ?, course = ? WHERE id = ?";

        try (Connection connection = Database.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getEmail());
            statement.setString(4, student.getCourse());
            statement.setInt(5, student.getId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error updating student: " + e.getMessage());
            return false;
        }
    }
}