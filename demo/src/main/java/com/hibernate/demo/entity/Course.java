
package com.hibernate.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a course entity in the database.
 * Contains course information and its relationships with
 * instructors, reviews, and students.
 */
@Entity
@Table(name = "course")
public class Course {

    /** The unique identifier of the course. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /** The title of the course. */
    @Column(name = "title")
    private String title;

    /** The instructor assigned to the course. */
    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    /**
     * Constructs a course with no initial values.
     */
    public Course() {
    }

    /**
     * Constructs a course with the specified title.
     *
     * @param title the title of the course
     */
    public Course(String title) {
        this.title = title;
    }

    /** The list of reviews associated with the course. */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private List<Review> review;

    //
    /** The list of students enrolled in the course. */
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            }
    )
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students;

    //

    /**
     * Returns the unique identifier of the course.
     *
     * @return the course ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the course.
     *
     * @param id the course ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the title of the course.
     *
     * @return the course title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the course.
     *
     * @param title the course title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the instructor assigned to the course.
     *
     * @return the instructor of the course
     */
    public Instructor getInstructor() {
        return instructor;
    }

    /**
     * Sets the instructor assigned to the course.
     *
     * @param instructor the instructor to assign
     */
    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    /**
     * Returns a string representation of the course.
     *
     * @return a string containing the course information
     */
    @Override
    public String toString() {
        return "Course [id=" + id
                + ", title=" + title
                + ", instructor=" + instructor + "]";
    }

    /**
     * Returns the list of reviews associated with the course.
     *
     * @return the list of course reviews
     */
    public List<Review> getReview() {
        return review;
    }

    /**
     * Sets the list of reviews associated with the course.
     *
     * @param review the list of reviews to set
     */
    public void setReview(List<Review> review) {
        this.review = review;
    }

    /**
     * Adds a review to the course.
     * Initializes the review list if it is null.
     *
     * @param rev the review to add
     */
    public void addReview(Review rev) {
        if (review == null) {
            review = new ArrayList<>();
        }

        review.add(rev);
    }

    /**
     * Adds a student to the course.
     * Initializes the student list if it is null.
     *
     * @param stud the student to add
     */
    public void addStudent(Student stud) {
        if (students == null) {
            students = new ArrayList<>();
        }

        students.add(stud);
    }

    /**
     * Returns the list of students enrolled in the course.
     *
     * @return the list of students
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * Sets the list of students enrolled in the course.
     *
     * @param students the list of students to set
     */
    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
