package com.hibernate.demo.dao;

import com.hibernate.demo.entity.Student;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.TypedQuery;
import java.util.List;

@Repository
public class StudentDaoImplement implements StudentDao {

    private EntityManager em;

    public StudentDaoImplement(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void save(Student theStudent) {
        em.persist(theStudent);
    }

    @Override 
    public Student findById(int theId) {
        return em.find(Student.class, theId);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = em.createQuery("FROM Student order by lastName", Student.class);
        return query.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastname) {
        TypedQuery<Student> query = em.createQuery("FROM Student WHERE lastName=:data", Student.class);
        query.setParameter("data", lastname);
        return query.getResultList();
    }

    @Override 
    @Transactional 
    public void delete(Integer id) {
        Student student = em.find(Student.class, id);
        em.remove(student);
    }

    @Override 
    @Transactional 
    public void update(Student theStudent) {
        em.merge(theStudent);
    }

    @Override 
    @Transactional 
    public int deleteAll() {
        int numRows = em.createQuery("DELETE FROM Student").executeUpdate();
        return numRows;        
    }

}