package org.studentsphere.helpers;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.studentsphere.models.CourseModel;
import org.studentsphere.models.Student;
import org.studentsphere.models.StudentModel;

public class CSVExporter {

    public static void exportStudents(String filePath) throws IOException {
        StudentModel studentModel = new StudentModel();
        List<Student> students = studentModel.getAllStudents();

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("ID,First Name,Last Name,Email,Course\n");
            for (Student student : students) {
                writer.write(String.format("%d,%s,%s,%s,%s\n",
                        student.getId(),
                        student.getFirstName(),
                        student.getLastName(),
                        student.getEmail(),
                        student.getCourse()));
            }
        }
    }

    public static void exportCourses(String filePath) throws IOException {
        CourseModel courseModel = new CourseModel();
        var courses = courseModel.getCourses();

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("ID,Name,Description\n");
            for (var course : courses) {
                writer.write(String.format("%d,%s,%s\n",
                        course.getId(),
                        course.getName(),
                        course.getDescription()));
            }
        }
    }
}