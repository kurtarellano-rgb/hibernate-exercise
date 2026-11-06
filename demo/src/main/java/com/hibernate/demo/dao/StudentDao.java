
package com.hibernate.demo.dao;

import java.util.List;

import com.hibernate.demo.entity.Student;

/**
 * Defines database operations for managing student records.
 */
public interface StudentDao {

    /**
     * Saves a student to the database.
     */
    void save(Student theStudent);

    /**
     * Retrieves a student by their ID.
     */
    Student findById(int theId);

    /**
     * Retrieves all students from the database.
     */
    List<Student> findAll();

    /**
     * Retrieves students matching the specified last name.
     */
    List<Student> findByLastName(String lastName);

    /**
     * Updates an existing student's information.
     */
    void update(Student theStudent);

    /**
     * Deletes a student using the specified ID.
     */
    void delete(Integer id);

    /**
     * Deletes all student records from the database.
     */
    int deleteAll();
}
