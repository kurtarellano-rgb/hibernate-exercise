
package com.hibernate.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Represents the details of an instructor in the database.
 * Contains the instructor's subject, hobby, and
 * associated instructor information.
 */
@Entity
@Table(name = "instructor_detail")
public class InstructorDetail {

    /** The unique identifier of the instructor details. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /** The subject associated with the instructor. */
    @Column(name = "subject")
    private String subject;

    /** The hobby of the instructor. */
    @Column(name = "hobby")
    private String hobby;

    /**
     * Constructs instructor details with no initial values.
     */
    public InstructorDetail() {
    }

    /**
     * Constructs instructor details with the specified subject and hobby.
     *
     * @param subject the subject of the instructor
     * @param hobby the hobby of the instructor
     */
    public InstructorDetail(String subject, String hobby) {
        this.subject = subject;
        this.hobby = hobby;
    }

    /**
     * Returns a string representation of the instructor details.
     *
     * @return a string containing the instructor details information
     */
    @Override
    public String toString() {
        return "InstructorDetail [id=" + id
                + ", subject=" + subject
                + ", hobby=" + hobby + "]";
    }

    /** The instructor associated with these instructor details. */
    @OneToOne(
            mappedBy = "instructorDeets",
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            }
    )
    private Instructor instructor;

    /**
     * Returns the unique identifier of the instructor details.
     *
     * @return the instructor details ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the instructor details.
     *
     * @param id the instructor details ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the subject of the instructor.
     *
     * @return the instructor's subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the subject of the instructor.
     *
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Returns the hobby of the instructor.
     *
     * @return the instructor's hobby
     */
    public String getHobby() {
        return hobby;
    }

    /**
     * Sets the hobby of the instructor.
     *
     * @param hobby the hobby to set
     */
    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    /**
     * Returns the instructor associated with these details.
     *
     * @return the associated instructor
     */
    public Instructor getInstructor() {
        return instructor;
    }

    /**
     * Sets the instructor associated with these details.
     *
     * @param instructor the instructor to associate
     */
    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
}
