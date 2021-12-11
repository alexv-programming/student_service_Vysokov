package telran.b7a.student.dao;

import telran.b7a.student.model.Student;


public interface StudentRepository {
	// base functionality CRUD
	Student save(Student student);

	Student findById(int id);

	Student deleteStudent(int id); 
}
