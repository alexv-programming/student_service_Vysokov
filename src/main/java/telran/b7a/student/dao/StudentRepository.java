package telran.b7a.student.dao;

import java.util.Optional;

import telran.b7a.student.model.Student;


public interface StudentRepository {
	// base functionality CRUD
	Student save(Student student);

	Optional<Student> findById(int id);

	Student deleteStudent(int id); 
}
