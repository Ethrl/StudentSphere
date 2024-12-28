package org.studentsphere.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String DATABASE_URL = "jdbc:sqlite:src/main/resources/studentsphere.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL);
    }

    public static void initialize() {
        try (Connection connection = connect()) {
            System.out.println("Połączono z bazą danych SQLite.");
        } catch (SQLException e) {
            System.err.println("Nie udało się połączyć z bazą danych: " + e.getMessage());
        }
    }

    public static void createTables() {
        String createStudentsTable = """
            CREATE TABLE IF NOT EXISTS students (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                first_name TEXT NOT NULL,
                last_name TEXT NOT NULL,
                email TEXT NOT NULL UNIQUE,
                course TEXT NOT NULL
            );
        """;
        try (Connection connection = connect()) {
            connection.createStatement().execute(createStudentsTable);
            System.out.println("Tabela 'students' została utworzona.");
        } catch (SQLException e) {
            System.err.println("Błąd podczas tworzenia tabeli: " + e.getMessage());
        }
    }

    public static void createCoursesTable() {
        String createCoursesTable = """
            CREATE TABLE IF NOT EXISTS courses (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL UNIQUE,
                description TEXT
            );
        """;
        try (Connection connection = connect()) {
            connection.createStatement().execute(createCoursesTable);
            System.out.println("Tabela 'courses' została utworzona.");
        } catch (SQLException e) {
            System.err.println("Błąd podczas tworzenia tabeli: " + e.getMessage());
        }
    }

    public static void createCourseStudentsTable() {
        String createCourseStudentsTable = """
            CREATE TABLE IF NOT EXISTS course_students (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                course_id INTEGER NOT NULL,
                student_id INTEGER NOT NULL,
                FOREIGN KEY (course_id) REFERENCES courses(id),
                FOREIGN KEY (student_id) REFERENCES students(id)
            );
        """;
        try (Connection connection = connect()) {
            connection.createStatement().execute(createCourseStudentsTable);
            System.out.println("Tabela 'course_students' została utworzona.");
        } catch (SQLException e) {
            System.err.println("Błąd podczas tworzenia tabeli: " + e.getMessage());
        }
    }
}