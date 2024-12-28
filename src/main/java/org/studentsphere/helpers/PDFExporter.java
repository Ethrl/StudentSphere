package org.studentsphere.helpers;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.studentsphere.models.CourseModel;
import org.studentsphere.models.Student;
import org.studentsphere.models.StudentModel;

import java.io.FileOutputStream;
import java.util.List;

public class PDFExporter {

    public static void exportStudents(String filePath) throws Exception {
        StudentModel studentModel = new StudentModel();
        List<Student> students = studentModel.getAllStudents();

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();

        document.add(new Paragraph("Student List", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20)));
        document.add(new Paragraph("\n"));

        PdfPTable table = new PdfPTable(5);
        table.addCell("ID");
        table.addCell("First Name");
        table.addCell("Last Name");
        table.addCell("Email");
        table.addCell("Course");

        for (Student student : students) {
            table.addCell(String.valueOf(student.getId()));
            table.addCell(student.getFirstName());
            table.addCell(student.getLastName());
            table.addCell(student.getEmail());
            table.addCell(student.getCourse());
        }

        document.add(table);
        document.close();
    }

    public static void exportCourses(String filePath) throws Exception {
        CourseModel courseModel = new CourseModel();
        var courses = courseModel.getCourses();

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();

        document.add(new Paragraph("Course List", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20)));
        document.add(new Paragraph("\n"));

        PdfPTable table = new PdfPTable(3);
        table.addCell("ID");
        table.addCell("Name");
        table.addCell("Description");

        for (var course : courses) {
            table.addCell(String.valueOf(course.getId()));
            table.addCell(course.getName());
            table.addCell(course.getDescription());
        }

        document.add(table);
        document.close();
    }
}