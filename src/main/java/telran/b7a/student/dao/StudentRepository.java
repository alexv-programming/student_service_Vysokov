package telran.b7a.student.dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import telran.b7a.student.model.Student;


public interface StudentRepository {
	// base functionality CRUD
	Student save(Student student);

	Optional<Student> findById(int id);

	Student deleteStudent(int id); 
	
	List<Student> findAll();
}
