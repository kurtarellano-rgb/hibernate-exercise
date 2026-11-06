package com.hibernate.demo.dao;

import com.hibernate.demo.entity.Student;
import java.util.List;

public interface StudentDao {

    void save(Student theStudent);
    Student findById(int theId);
    List<Student> findAll();
    List<Student> findByLastName(String lastname);
    void update(Student theStudent);
    void delete(Integer id);
    int deleteAll();
}
