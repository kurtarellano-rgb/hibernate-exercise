
package com.hibernate.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import com.hibernate.demo.dao.StudentDao;
import com.hibernate.demo.entity.Student;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

	@Bean
CommandLineRunner commandLineRunner(StudentDao studentDao) {
    return runner -> {
        createStudent(studentDao);
        readStudent(studentDao);
        queryStudents(studentDao);
        queryStudentsByLastName(studentDao);
        updateStudent(studentDao);
        deleteStudent(studentDao);
        deleteAllStudent(studentDao);
    };
}

    private void createStudent(StudentDao studentDao) {

        // create student object
        System.out.println("Creating Student information...");
        Student student = new Student("Light",
                                      "Yagami",
                                      "light.yagami@example.com");

        // save the student object
        System.out.println("Saving student information...");
        studentDao.save(student);

        // display id of the saved student
        System.out.println("Saved! Generated id: " + student.getId());
    }


    private void readStudent(StudentDao studentDao) {

        // retrieve student based on the id
        System.out.println("Retreiving student id 2...");
		Student student = studentDao.findById(2);

		System.out.println("Found the student: " + student);
    }


    private void queryStudents(StudentDao studentDao) {

        List<Student> students = studentDao.findAll();

        // display list of students
        for (Student s : students) {
            System.out.println(s);
        }

	}

	private void queryStudentsByLastName(StudentDao studentDao) {

		List<Student> students = studentDao.findByLastName("Yagami");

		// display list of students
		for (Student s : students) {
			System.out.println(s);
		}

	}

	private void updateStudent(StudentDao studentDao) {
		// retrieve student based on id
		int id = 1;
		System.out.println("Student ID: " + id);
		Student student = studentDao.findById(id);

		// change first name
		System.out.println("Updating student name...");
		student.setFirstName("Dark");

		// update the student
		studentDao.update(student);

		// display the updated student information
		System.out.println("Updated student information: " + student);
	}


	private void deleteStudent(StudentDao studentDao) {
		int id = 22;
		System.out.println("Delete ID: " + id);

		// delete the student
		studentDao.delete(id);
	}

	private void deleteAllStudent(StudentDao studentDao) {
		System.out.println("Deleting all student information...");
		int rowsDeleted = studentDao.deleteAll();
		System.out.println("Number of deleted rows: " + rowsDeleted);
	}



}