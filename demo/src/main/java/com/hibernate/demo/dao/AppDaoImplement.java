
package com.hibernate.demo.dao;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * Data access implementation for instructors, courses, and students.
 */
@Repository
public class AppDaoImplement implements AppDao {

    private EntityManager em;

    /**
     * Constructs an AppDaoImplement with the specified EntityManager.
     *
     * @param em the EntityManager used for database operations
     */
    public AppDaoImplement(EntityManager em) {
        this.em = em;
    }

    /**
     * Saves an instructor to the database.
     *
     * @param theInstructor the instructor to save
     */
    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        em.persist(theInstructor);
    }

    /**
     * Finds an instructor by their ID.
     *
     * @param theId the ID of the instructor
     * @return the instructor if found, otherwise null
     */
    @Override
    public Instructor findInstructorById(int theId) {
        return em.find(Instructor.class, theId);
    }

    /**
     * Deletes an instructor by their ID.
     * Removes the instructor's associations with courses before deletion.
     *
     * @param theId the ID of the instructor to delete
     */
    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        Instructor in = em.find(Instructor.class, theId);

        if (in == null) {
            return;
        }

        List<Course> courses = in.getCourses();

        if (courses != null) {
            for (Course c : courses) {
                c.setInstructor(null);
            }
        }

        em.remove(in);
    }

    /**
     * Finds instructor details by their ID.
     *
     * @param theId the ID of the instructor details
     * @return the instructor details if found, otherwise null
     */
    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return em.find(InstructorDetail.class, theId);
    }

    /**
     * Updates an existing instructor in the database.
     *
     * @param in the instructor to update
     */
    @Override
    @Transactional
    public void update(Instructor in) {
        em.merge(in);
    }

    /**
     * Deletes instructor details by their ID.
     * Removes the association with the instructor before deletion.
     *
     * @param theId the ID of the instructor details to delete
     */
    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        InstructorDetail deets = em.find(InstructorDetail.class, theId);

        if (deets == null) {
            return;
        }

        if (deets.getInstructor() != null) {
            deets.getInstructor().setInstructorDeets(null);
        }

        em.remove(deets);
    }

    /**
     * Finds all courses associated with a specific instructor.
     *
     * @param theId the ID of the instructor
     * @return a list of courses associated with the instructor
     */
    @Override
    public List<Course> findCoursesByInstructorId(int theId) {

        TypedQuery<Course> query = em.createQuery(
                "FROM Course WHERE instructor.id =:data",
                Course.class);

        query.setParameter("data", theId);

        List<Course> courses = query.getResultList();

        return courses;
    }

    /**
     * Finds an instructor by ID using a JOIN FETCH query.
     * Retrieves the instructor, associated courses, and instructor details.
     *
     * @param theId the ID of the instructor
     * @return the instructor with associated courses and details
     */
    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        // create query
        TypedQuery<Instructor> query = em.createQuery(
                "SELECT i FROM Instructor i "
                        + "JOIN FETCH i.courses "
                        + "JOIN FETCH i.instructorDeets "
                        + "WHERE i.id=:data",
                Instructor.class);

        query.setParameter("data", theId);

        Instructor in = query.getSingleResult();

        return in;
    }

    // @Override
    // @Transactional 
    // public void deleteCourseById(int theId) {
    //     Course in = em.find(Course.class, theId);

    //    if(in != null) {
    //     em.remove(in);
    //    }
    // }

    /**
     * Saves a course to the database.
     *
     * @param theCourse the course to save
     */
    @Override
    @Transactional
    public void save(Course theCourse) {
        em.persist(theCourse);
    }

    /**
     * Finds a course and its associated reviews by course ID.
     *
     * @param theId the ID of the course
     * @return the course with its associated reviews
     */
    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {

        // create query
        TypedQuery<Course> query = em.createQuery(
                "SELECT c FROM Course c "
                        + "JOIN FETCH c.review "
                        + "WHERE c.id=:data",
                Course.class);

        query.setParameter("data", theId);

        // execute query
        Course course = query.getSingleResult();

        return course;
    }

    /**
     * Deletes a course from the database by its ID.
     *
     * @param theId the ID of the course to delete
     */
    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        Course course = em.find(Course.class, theId);

        if (course == null) {
            return;
        }

        em.remove(course);
    }

    /**
     * Finds a course and its associated students by course ID.
     *
     * @param theId the ID of the course
     * @return the course with its associated students
     */
    @Override
    public Course findCourseAndStudentByCourseId(int theId) {

        // create query
        TypedQuery<Course> query = em.createQuery(
                "SELECT c FROM Course c "
                        + "JOIN FETCH c.students "
                        + "WHERE c.id=:data",
                Course.class);

        query.setParameter("data", theId);

        // execute query
        Course course = query.getSingleResult();

        return course;
    }

    /**
     * Finds a student and their associated courses by student ID.
     *
     * @param theId the ID of the student
     * @return the student with their associated courses
     */
    @Override
    public Student findStudentAndCoursesByStudentId(int theId) {

        // create query
        TypedQuery<Student> query = em.createQuery(
                "SELECT s FROM Student s "
                        + "JOIN FETCH s.courses "
                        + "WHERE s.id=:data",
                Student.class);

        query.setParameter("data", theId);

        // execute query
        Student student = query.getSingleResult();

        return student;
    }

    /**
     * Updates an existing student in the database.
     *
     * @param student the student to update
     */
    @Override
    @Transactional
    public void update(Student student) {
        em.merge(student);
    }

    /**
     * Deletes a student from the database by their ID.
     * Removes the student's course associations before deletion.
     *
     * @param theId the ID of the student to delete
     */
    @Override
    @Transactional
    public void deleteStudentById(int theId) {

        // retrieve student
        Student student = em.find(Student.class, theId);

        if (student != null) {

            // get the courses
            List<Course> courses = student.getCourses();

            // break the associations of courses for student
            for (Course c : courses) {
                c.getStudents().remove(student);
            }

            em.remove(student);
        }
    }
}
