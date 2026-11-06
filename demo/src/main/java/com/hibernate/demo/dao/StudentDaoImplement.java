
package com.hibernate.demo.dao;

import java.util.List;

import com.hibernate.demo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implements the StudentDao interface for student database operations.
 */
@Repository
public class StudentDaoImplement implements StudentDao {

    /** Entity manager used for database operations. */
    private EntityManager em;

    /**
     * Constructs a StudentDaoImplement with an entity manager.
     *
     * @param em the entity manager
     */
    public StudentDaoImplement(EntityManager em) {
        this.em = em;
    }

    /**
     * Saves a student to the database.
     *
     * @param theStudent the student to save
     */
    @Override
    @Transactional
    public void save(Student theStudent) {
        em.persist(theStudent);
    }

    /**
     * Retrieves a student based on the specified ID.
     *
     * @param theId the student ID
     * @return the student matching the ID
     */
    @Override
    public Student findById(int theId) {
        return em.find(Student.class, theId);
    }

    /**
     * Retrieves all students ordered by last name.
     *
     * @return the list of students
     */
    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = em.createQuery(
                "FROM Student order by lastName",
                Student.class);

        return query.getResultList();
    }

    /**
     * Retrieves students based on their last name.
     *
     * @param lastname the last name to search for
     * @return the list of matching students
     */
    @Override
    public List<Student> findByLastName(String lastname) {
        TypedQuery<Student> query = em.createQuery(
                "FROM Student WHERE lastName=:data",
                Student.class);

        query.setParameter("data", lastname);

        return query.getResultList();
    }

    /**
     * Deletes a student based on the specified ID.
     *
     * @param id the student ID to delete
     */
    @Override
    @Transactional
    public void delete(Integer id) {
        Student student = em.find(Student.class, id);
        em.remove(student);
    }

    /**
     * Updates an existing student in the database.
     *
     * @param theStudent the student to update
     */
    @Override
    @Transactional
    public void update(Student theStudent) {
        em.merge(theStudent);
    }

    /**
     * Deletes all student records from the database.
     *
     * @return the number of deleted rows
     */
    @Override
    @Transactional
    public int deleteAll() {
        int numRows = em.createQuery(
                "DELETE FROM Student").executeUpdate();

        return numRows;
    }
}
