package com.hibernate.demo;

import com.hibernate.demo.dao.AppDao;
// import com.hibernate.demo.dao.StudentDao;
import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
// import com.hibernate.demo.entity.Student;
import com.hibernate.demo.entity.Review;
import com.hibernate.demo.entity.Student;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Application entry point for the Hibernate student management demo.
 */
@SpringBootApplication
public class DemoApplication {

    /**
     * Starts the Spring Boot application.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    /**
     * Creates a command-line runner for database operations.
     *
     * @param app the data access object used for database operations
     * @return a runner that executes the selected database operation
     */
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
            findInstructorWithCourses(app);
            // findInstructorWithCoursesJoinFetch(app);
            // updateInstructor(app);
            // deleteInstructorOnly(app);
            // deleteCourseById(app);
            // createCourseAndReviews(app);
            // retrieveCourseAndReviews(app);
            // deleteCourseAndReviews(app);
            // createCourseAndStudents(app);
            // findCourseAndStudents(app);
            // findStudentAndCourses(app);
            // addMoreCoursesForStudent(app);
            // deleteStudent(app);
        };
    }

    /* Retrieves an instructor along with their associated courses from the database 
        and prints their details to the console. */
    private void findInstructorWithCourses(AppDao app) {

        int id = 2;
        System.out.println("Finding instructor id: " + id);

        Instructor ins = app.findInstructorById(id);

        System.out.println("Instructor: " + ins);
        System.out.println("Courses: " + ins.getCourses());

        System.out.println("Done!");
    }

    /**
     * Creates an instructor with details and saves the instructor.
     *
     * @param app the data access object used to save the instructor
     */
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

    /**
     * Retrieves and displays an instructor by ID.
     *
     * @param app the data access object used to find the instructor
     */
    private void findInstructor(AppDao app) {
        int id = 2;
        System.out.println("Find id: " + id);

        Instructor ins = app.findInstructorById(id);

        System.out.println("Instructor: " + ins);
        System.out.println("Details: " + ins.getInstructorDeets());
    }

    /**
     * Deletes an instructor by ID.
     *
     * @param app the data access object used to delete the instructor
     */
    @Transactional
    private void deleteInstructor(AppDao app) {
        int id = 8;
        System.out.println("Delete id: " + id);

        app.deleteInstructorById(id);
        System.out.println("Deleted Succesfully!");
    }

    /**
     * Retrieves and displays instructor details by ID.
     *
     * @param app the data access object used to find instructor details
     */
    private void findInstructorDetail(AppDao app) {
        int id = 3;
        InstructorDetail inDeets = app.findInstructorDetailById(id);

        System.out.println("Details: " + inDeets);
        System.out.println("Instructor: " + inDeets.getInstructor());
    }

    /**
     * Deletes instructor details by ID.
     *
     * @param app the data access object used to delete instructor details
     */
    private void deleteInstructorDetail(AppDao app) {
        int id = 11;
        System.out.println("Delete id: " + id);

        app.deleteInstructorDetailById(id);
        System.out.println("Deleted Succesfully!");
    }

    /**
     * Creates and saves an instructor with details and courses.
     *
     * @param app the data access object used to save the instructor
     */
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

        System.out.println("Courses Saved!" + ins.getCourses());
        app.save(ins);
    }

    /**
     * Retrieves and displays the courses for an instructor.
     *
     */
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

    /**
     * Retrieves and displays an instructor and courses using JOIN FETCH.
     *
     * @param app the data access object used to retrieve the instructor
     */
    private void findInstructorWithCoursesJoinFetch(AppDao app) {
        int id = 25;
        System.out.println("find instructor id: " + id);

        Instructor ins = app.findInstructorByIdJoinFetch(id);

        System.out.println("Instructor: " + ins);
        System.out.println("Courses: " + ins.getCourses());
    }

    /**
     * Updates an instructor's last name.
     *
     * @param app the data access object used to update the instructor
     */
    private void updateInstructor(AppDao app) {
        int id = 3;
        System.out.println("Find instructor id: " + id);

        Instructor ins = app.findInstructorById(id);

        ins.setLastName("Uchiha");

        app.update(ins);
        System.out.println("Updated Instructor: " + ins);
    }

    /**
     * Deletes an instructor by ID.
     *
     * @param app the data access object used to delete the instructor
     */
    private void deleteInstructorOnly(AppDao app) {
        int id = 24;
        System.out.println("Delete Instructor: " + id);

        app.deleteInstructorById(id);
        System.out.println("Deleted Successfully!");
    }

    /**
     * Deletes a course by ID.
     *
     * @param app the data access object used to delete the course
     */
    private void deleteCourseById(AppDao app) {
        int id = 1;
        app.deleteCourseById(id);
        System.out.println("Course Deleted! " + id);
    }

    /**
     * Creates and saves a course with reviews.
     *
     * @param app the data access object used to save the course
     */
    private void createCourseAndReviews(AppDao app) {
        Course course = new Course("How to Speed Run Spring in 1 Day");

        course.addReview(new Review("Great speed 2x...loved it!!!"));
        course.addReview(new Review("I wanted the speed 0.75x :(("));
        course.addReview(new Review("Hoping to do a speed of 3x" 
                                            +  ":D. Anyways, nice!"));

        System.out.println("Course saved!");
        app.save(course);
    }

    /**
     * Retrieves and displays a course and its reviews.
     *
     * @param app the data access object used to retrieve the course
     */
    private void retrieveCourseAndReviews(AppDao app) {
        int id = 21;
        System.out.println("Get course and reviews from id: " + id);

        // get the course and reviews
        Course course = app.findCourseAndReviewsByCourseId(id);

        // print the course
        System.out.println(course);

        // print the reviews
        System.out.println(course.getReview());
    }

    /**
     * Deletes a course and its associated reviews by ID.
     *
     * @param app the data access object used to delete the course
     */
    private void deleteCourseAndReviews(AppDao app) {
        int id = 2;
        System.out.println("Delete course: " + id);

        app.deleteCourseById(id);
        System.out.println("Deleted successfully!");
    }

    /**
     * Creates a course with students and saves the course.
     *
     * @param app the data access object used to save the course
     */
    private void createCourseAndStudents(AppDao app) {
        Course course = new Course("How to Be a Fullstack Dev in Half A Day");

        Student student1 = new Student(
                "Light", "Yagami", "light.yagami@example.com");
        Student student2 = new Student(
                "Leslie", "Andrews", "leslie.andrews@example.com");

        course.addStudent(student1);
        course.addStudent(student2);

        app.save(course);
        System.out.println("Successfully enrolled!");
    }

    /**
     * Retrieves and displays a course and its students.
     *
     * @param app the data access object used to retrieve the course
     */
    private void findCourseAndStudents(AppDao app) {
        int id = 22;
        Course course = app.findCourseAndStudentByCourseId(id);

        System.out.println("Course: " + course);
        System.out.println("Students: " + course.getStudents());
    }

    /**
     * Retrieves and displays a student and their courses.
     *
     * @param app the data access object used to retrieve the student
     */
    private void findStudentAndCourses(AppDao app) {
        int id = 2;
        Student student = app.findStudentAndCoursesByStudentId(id);

        System.out.println("Student: " + student);
        System.out.println("Courses: " + student.getCourses());
    }

    /**
     * Adds courses to an existing student and updates the student.
     *
     */
    private void addMoreCoursesForStudent(AppDao app) {
        int id = 2;
        Student student = app.findStudentAndCoursesByStudentId(id);

        Course course1 = new Course("How to Test without Running Application");
        Course course2 = new Course("How to Develop without Computer");
        Course course3 = new Course("How to Be a Legit Developer");

        student.addCourse(course1);
        student.addCourse(course2);
        student.addCourse(course3);

        System.out.println("Successfully enrolled to new courses!");

        app.update(student);
    }

    /**
     * Deletes a student by ID.
     *
     * @param app the data access object used to delete the student
     */
    private void deleteStudent(AppDao app) {
        int id = 2;
        System.out.println("Delete student: " + id);

        app.deleteStudentById(id);
        System.out.println("Deleted successfully!");
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
