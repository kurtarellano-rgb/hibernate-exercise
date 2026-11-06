
package com.hibernate.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
     * @param firstName the student's first name
     * @param lastName the student's last name
     * @param email the student's email address
     */
    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /**
     * Returns the student's ID.
     *
     * @return the student ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the student's first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the student's last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the student's email address.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the student's ID.
     *
     * @param id the student ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the student's first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the student's last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets the student's email address.
     *
     * @param email the email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns a string representation of the student.
     *
     * @return the student's information as a string
     */
    @Override
    public String toString() {
        return "Student [id=" + id
                + ", firstName=" + firstName
                + ", lastName=" + lastName
                + ", email=" + email
                + "]";
    }
}
