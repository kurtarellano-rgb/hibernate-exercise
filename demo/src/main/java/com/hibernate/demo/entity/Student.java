
package com.hibernate.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a student entity in the database.
 */
@Entity
@Table(name = "student")
public class Student {

    /** Unique identifier for the student. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /** First name of the student. */
    @Column(name = "first_name")
    private String firstName;

    /** Last name of the student. */
    @Column(name = "last_name")
    private String lastName;

    /** Email address of the student. */
    @Column(name = "email")
    private String email;

    /**
     * Default constructor required by JPA.
     */
    public Student() {
    }

    /**
     * Constructs a student with the specified information.
     *
     * @param firstName the first name of the student
     * @param lastName the last name of the student
     * @param email the email address of the student
     */
    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /**
     * Returns the unique identifier of the student.
     *
     * @return the student ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the first name of the student.
     *
     * @return the student's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the last name of the student.
     *
     * @return the student's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the email address of the student.
     *
     * @return the student's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the unique identifier of the student.
     *
     * @param id the student ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the first name of the student.
     *
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the last name of the student.
     *
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets the email address of the student.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns a string representation of the student.
     *
     * @return a string containing the student information
     */
    @Override
    public String toString() {
        return "Student [id=" + id
                + ", firstName=" + firstName
                + ", lastName=" + lastName
                + ", email=" + email
                + "]";
    }

    /** The list of courses associated with the student. */
    @ManyToMany(
            mappedBy = "students",
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            }
    )
    private List<Course> courses;

    /**
     * Adds a course to the student's list of courses.
     * Initializes the course list if it is null and
     * establishes the relationship between the student
     * and the course.
     *
     * @param course the course to add
     */
    public void addCourse(Course course) {
        if (courses == null) {
            courses = new ArrayList<>();
        }

        courses.add(course);
        course.addStudent(this);
    }

    /**
     * Returns the list of courses associated with the student.
     *
     * @return the list of courses
     */
    public List<Course> getCourses() {
        return courses;
    }

    /**
     * Sets the list of courses associated with the student.
     *
     * @param courses the list of courses to set
     */
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
