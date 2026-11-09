package com.hibernate.demo.dao;

import org.springframework.stereotype.Repository;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import com.hibernate.demo.entity.InstructorDetail;
import java.util.List;




@Repository 
public class AppDaoImplement implements AppDao {

    private EntityManager em;

    public AppDaoImplement(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        em.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return em.find(Instructor.class, theId);
    }

    @Override
    @Transactional 
    public void deleteInstructorById(int theId) {
        Instructor in = em.find(Instructor.class, theId);
        em.remove(in);

        List<Course> courses = in.getCourses();
        for(Course c: courses) {
            c.setInstructor(null);
        }

        em.remove(in);

    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return em.find(InstructorDetail.class, theId);
    }

    @Override 
    @Transactional 
    public void update(Instructor in) {
        em.merge(in);
    }

    @Override 
    @Transactional 
    public void deleteInstructorDetailById(int theId) {
        InstructorDetail deets = em.find(InstructorDetail.class, theId);
        deets.getInstructor().setInstructorDeets(null);
        em.remove(deets);
    }

    @Override 
    public List<Course> findCoursesByInstructorId(int theId) {

        TypedQuery<Course> query = em.createQuery(
            "FROM Course WHERE instructor.id =:data", Course.class);
        
        query.setParameter("data", theId);

        List<Course> courses = query.getResultList();
        return courses;
    }

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

    @Override
    @Transactional 
    public void deleteCourseById(int theId) {
        Course in = em.find(Course.class, theId);

       if(in != null) {
        em.remove(in);
       }
    }

}
