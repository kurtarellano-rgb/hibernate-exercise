
package com.hibernate.demo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.hibernate.demo.dao.StudentDao;
import com.hibernate.demo.entity.Student;

/**
 * Application entry point for the Hibernate student management demo.
 */
@SpringBootApplication
public class DemoApplication {

    /**
     * Starts the Spring Boot application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    /**
     * Executes student database operations when the application starts.
     *
     * @param studentDao the student data access object
     * @return the command-line runner
     */
    @Bean
    CommandLineRunner commandLineRunner(StudentDao studentDao) {
        return runner -> {
            createStudent(studentDao);
            readStudent(studentDao);
            queryStudents(studentDao);
            queryStudentsByLastName(studentDao);
            updateStudent(studentDao);
            deleteStudent(studentDao);
            deleteAllStudent(studentDao);
        };
    }

    /**
     * Creates and saves a new student.
     *
     * @param studentDao the student data access object
     */
    private void createStudent(StudentDao studentDao) {

        // Create student object.
        System.out.println("Creating Student information...");
        Student student = new Student(
                "Light",
                "Yagami",
                "light.yagami@example.com");

        // Save the student object.
        System.out.println("Saving student information...");
        studentDao.save(student);

        // Display the ID of the saved student.
        System.out.println("Saved! Generated id: " + student.getId());
    }

    /**
     * Retrieves and displays a student by ID.
     *
     * @param studentDao the student data access object
     */
    private void readStudent(StudentDao studentDao) {

        // Retrieve student based on the ID.
        System.out.println("Retrieving student id 2...");
        Student student = studentDao.findById(2);

        System.out.println("Found the student: " + student);
    }

    /**
     * Retrieves and displays all students.
     *
     * @param studentDao the student data access object
     */
    private void queryStudents(StudentDao studentDao) {

        // Get list of students.
        List<Student> students = studentDao.findAll();

        // Display list of students.
        for (Student student : students) {
            System.out.println(student);
        }
    }

    /**
     * Retrieves and displays students with a specified last name.
     *
     * @param studentDao the student data access object
     */
    private void queryStudentsByLastName(StudentDao studentDao) {

        // Find students by last name.
        List<Student> students = studentDao.findByLastName("Yagami");

        // Display list of students.
        for (Student student : students) {
            System.out.println(student);
        }
    }

    /**
     * Updates the first name of an existing student.
     *
     * @param studentDao the student data access object
     */
    private void updateStudent(StudentDao studentDao) {

        // Retrieve student based on ID.
        int id = 1;
        System.out.println("Student ID: " + id);
        Student student = studentDao.findById(id);

        // Change first name.
        System.out.println("Updating student name...");
        student.setFirstName("Dark");

        // Update the student.
        studentDao.update(student);

        // Display the updated student information.
        System.out.println("Updated student information: " + student);
    }

    /**
     * Deletes a student using the specified ID.
     *
     * @param studentDao the student data access object
     */
    private void deleteStudent(StudentDao studentDao) {

        // Set student ID.
        int id = 22;
        System.out.println("Delete ID: " + id);

        // Delete the student.
        studentDao.delete(id);
    }

    /**
     * Deletes all student records from the database.
     *
     * @param studentDao the student data access object
     */
    private void deleteAllStudent(StudentDao studentDao) {

        // Delete all student records.
        System.out.println("Deleting all student information...");
        int rowsDeleted = studentDao.deleteAll();

        // Display number of deleted rows.
        System.out.println("Number of deleted rows: " + rowsDeleted);
    }
}
