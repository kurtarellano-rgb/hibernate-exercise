
package com.hibernate.demo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hibernate.demo.dao.AppDao;
import com.hibernate.demo.entity.Course;
// import com.hibernate.demo.dao.StudentDao;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
// import com.hibernate.demo.entity.Student;

import jakarta.transaction.Transactional;


/**
 * Application entry point for the Hibernate student management demo.
 */
@SpringBootApplication
public class DemoApplication {

    /**
     * Starts the Spring Boot application.
     *
     */
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(AppDao app) {
        return runner -> {
            // createInstructor(app);
            // findInstructor(app);
            // deleteInstructor(app);
            // findInstructorDetail(app);
            // deleteInstructorDetail(app);
            // createInstructorWithCourses(app);
            // findCoursesForInstructor(app);
            // findInstructorWithCoursesJoinFetch(app);
            // updateInstructor(app);
            // deleteInstructorOnly(app);
            deleteCourseById(app);
        };
    }

    private void createInstructor(AppDao app) {
        // create the instructor and details
        Instructor ins = new Instructor("Vicky",
                "Magtanggol",
                "vicky.mags@example.com");

        InstructorDetail deets = new InstructorDetail("Math",
                "Solving problems");

        ins.setInstructorDeets(deets);

        System.out.println("Information saved!");
        app.save(ins);
    }

    private void findInstructor(AppDao app) {
        int id = 2;
        System.out.println("Find id: " +id);

        Instructor ins = app.findInstructorById(id);

        System.out.println("Instructor: " + ins);
        System.out.println("Details: " + ins.getInstructorDeets());
    }

    @Transactional 
    private void deleteInstructor(AppDao app) {
        int id = 8;
        System.out.println("Delete id: " +id);

        app.deleteInstructorById(id);
        System.out.println("Deleted Succesfully!");
    }

    private void findInstructorDetail(AppDao app) {
        int id = 3;
        InstructorDetail inDeets = app.findInstructorDetailById(id);

        System.out.println("Details: " + inDeets);
        System.out.println("Instructor: " + inDeets.getInstructor());
    }

    private void deleteInstructorDetail(AppDao app) {
        int id = 11;
        System.out.println("Delete id: " +id);

        app.deleteInstructorDetailById(id);
        System.out.println("Deleted Succesfully!");
    }

    private void createInstructorWithCourses(AppDao app) {
        // create the instructor and details
        Instructor ins = new Instructor("Migil",
                "Vic",
                "migil.vic@sample.com");
        InstructorDetail deets = new InstructorDetail("Tech",
                "Creating Problems");

        ins.setInstructorDeets(deets);
        
        Course course1 = new Course("Nihonggo");
        Course course2 = new Course("Tech");

        ins.add(course1);
        ins.add(course2);

        System.out.println("Courses Saved!" +ins.getCourses());
        app.save(ins);
    }

    private void findCoursesForInstructor(AppDao app) {
        int id = 25;

        System.out.println("find instructor id: " + id);
        Instructor ins = app.findInstructorById(id);

        System.out.println("Instructor: " + ins);

        System.out.println("Courses from id=" + id);
        List<Course> courses = app.findCoursesByInstructorId(id);

        ins.setCourses(courses);

        System.out.println(ins.getCourses());
    }

    private void findInstructorWithCoursesJoinFetch(AppDao app) {
        int id = 25;
        System.out.println("find instructor id: " + id);

        Instructor ins = app.findInstructorByIdJoinFetch(id);

        System.out.println("Instructor: " + ins);
        System.out.println("Courses: " + ins.getCourses());
    }

    private void updateInstructor(AppDao app) {
        int id = 3;
        System.out.println("Find instructor id: " + id);

        Instructor ins = app.findInstructorById(id);

        ins.setLastName("Uchiha");

        app.update(ins);
        System.out.println("Updated Instructor: " + ins);
    }

    private void deleteInstructorOnly(AppDao app) {
        int id = 24;
        System.out.println("Delete Instructor: " + id);

        app.deleteInstructorById(id);
        System.out.println("Deleted Successfully!");
    }

    private void deleteCourseById(AppDao app) {
        int id = 4;
        app.deleteCourseById(id);
        System.out.println("Course Deleted! " +id);

    }
        
    /**
     * Executes student database operations when the application starts.
     *
     */
    // @Bean
    // CommandLineRunner commandLineRunner(StudentDao studentDao) {
    //     return runner -> {
    //         // createStudent(studentDao);
    //         // readStudent(studentDao);
    //         // queryStudents(studentDao);
    //         // queryStudentsByLastName(studentDao);
    //         // updateStudent(studentDao);
    //         // deleteStudent(studentDao);
    //         // deleteAllStudent(studentDao);
    //     };
    // }

    /**
     * Creates and saves a new student.
     *
     */
    // private void createStudent(StudentDao studentDao) {

    //     // Create student object.
    //     System.out.println("Creating Student information...");
    //     Student student = new Student(
    //             "Light",
    //             "Yagami",
    //             "light.yagami@example.com");

    //     // Save the student object.
    //     System.out.println("Saving student information...");
    //     studentDao.save(student);

    //     // Display the ID of the saved student.
    //     System.out.println("Saved! Generated id: " + student.getId());
    // }

    // /**
    //  * Retrieves and displays a student by ID.
    //  *
    //  */
    // private void readStudent(StudentDao studentDao) {

    //     // Retrieve student based on the ID.
    //     System.out.println("Retrieving student id 2...");
    //     Student student = studentDao.findById(2);

    //     System.out.println("Found the student: " + student);
    // }

    // /**
    //  * Retrieves and displays all students.
    //  *
    //  */
    // private void queryStudents(StudentDao studentDao) {

    //     // Get list of students.
    //     List<Student> students = studentDao.findAll();

    //     // Display list of students.
    //     for (Student student : students) {
    //         System.out.println(student);
    //     }
    // }

    /**
     * Retrieves and displays students with a specified last name.
     *
     */
    // private void queryStudentsByLastName(StudentDao studentDao) {

    //     // Find students by last name.
    //     List<Student> students = studentDao.findByLastName("Yagami");

    //     // Display list of students.
    //     for (Student student : students) {
    //         System.out.println(student);
    //     }
    // }

    // /**
    //  * Updates the first name of an existing student.
    //  *
    //  */
    // private void updateStudent(StudentDao studentDao) {

    //     // Retrieve student based on ID.
    //     int id = 1;
    //     System.out.println("Student ID: " + id);
    //     Student student = studentDao.findById(id);

    //     // Change first name.
    //     System.out.println("Updating student name...");
    //     student.setFirstName("Dark");

    //     // Update the student.
    //     studentDao.update(student);

    //     // Display the updated student information.
    //     System.out.println("Updated student information: " + student);
    // }

    // /**
    //  * Deletes a student using the specified ID.
    //  *
    //  */
    // private void deleteStudent(StudentDao studentDao) {

    //     // Set student ID.
    //     int id = 22;
    //     System.out.println("Delete ID: " + id);

    //     // Delete the student.
    //     studentDao.delete(id);
    // }

    // /**
    //  * Deletes all student records from the database.
    //  *
    //  */
    // private void deleteAllStudent(StudentDao studentDao) {

    //     // Delete all student records.
    //     System.out.println("Deleting all student information...");
    //     int rowsDeleted = studentDao.deleteAll();

    //     // Display number of deleted rows.
    //     System.out.println("Number of deleted rows: " + rowsDeleted);
    // }

    

}
