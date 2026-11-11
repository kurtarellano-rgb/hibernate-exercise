
package com.hibernate.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an instructor entity in the database.
 * Contains instructor information and its relationships
 * with instructor details and courses.
 */
@Entity
@Table(name = "instructor")
public class Instructor {

    /** The unique identifier of the instructor. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /** The first name of the instructor. */
    @Column(name = "first_name")
    private String firstName;

    /** The last name of the instructor. */
    @Column(name = "last_name")
    private String lastName;

    /** The email address of the instructor. */
    @Column(name = "email")
    private String email;

    /** The details associated with the instructor. */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")
    private InstructorDetail instructorDeets;

    /** The list of courses assigned to the instructor. */
    @OneToMany(
            mappedBy = "instructor",
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            }
    )
    private List<Course> courses;

    /**
     * Constructs an instructor with no initial values.
     */
    public Instructor() {
    }

    /**
     * Constructs an instructor with the specified information.
     *
     * @param firstName the first name of the instructor
     * @param lastName the last name of the instructor
     * @param email the email address of the instructor
     */
    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /**
     * Returns the unique identifier of the instructor.
     *
     * @return the instructor ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the first name of the instructor.
     *
     * @return the instructor's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the last name of the instructor.
     *
     * @return the instructor's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the email address of the instructor.
     *
     * @return the instructor's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the details associated with the instructor.
     *
     * @return the instructor details
     */
    public InstructorDetail getInstructorDeets() {
        return instructorDeets;
    }

    /**
     * Sets the unique identifier of the instructor.
     *
     * @param id the instructor ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the first name of the instructor.
     *
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the last name of the instructor.
     *
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets the email address of the instructor.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the details associated with the instructor.
     *
     * @param instructorDeets the instructor details to set
     */
    public void setInstructorDeets(InstructorDetail instructorDeets) {
        this.instructorDeets = instructorDeets;
    }

    /**
     * Adds a course to the instructor's list of courses.
     * Initializes the course list if it is null and
     * establishes the relationship between the course
     * and the instructor.
     *
     * @param tcourse the course to add
     */
    public void add(Course tcourse) {
        if (this.courses == null) {
            this.courses = new ArrayList<>();
        }

        this.courses.add(tcourse);
        tcourse.setInstructor(this);
    }

    /**
     * Returns a string representation of the instructor.
     *
     * @return a string containing the instructor information
     */
    @Override
    public String toString() {
        return "Instructor [id=" + id
                + ", firstName=" + firstName
                + ", lastName=" + lastName
                + ", email=" + email
                + ", instructorDeets=" + instructorDeets + "]";
    }

    /**
     * Returns the list of courses assigned to the instructor.
     *
     * @return the list of courses
     */
    public List<Course> getCourses() {
        return courses;
    }

    /**
     * Sets the list of courses assigned to the instructor.
     *
     * @param courses the list of courses to set
     */
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
