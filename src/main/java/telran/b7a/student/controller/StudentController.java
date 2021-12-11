package telran.b7a.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.b7a.student.dto.ScoreDto;
import telran.b7a.student.dto.StudentCredentialsDto;
import telran.b7a.student.dto.StudentDto;
import telran.b7a.student.dto.UpdateStudentDto;
import telran.b7a.student.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	StudentService studentService;

	@PostMapping("/student")
	public boolean studentRegister(@RequestBody StudentCredentialsDto studentCredentialsDto) {
		return studentService.addStudent(studentCredentialsDto);
	}

	@GetMapping("/student/{id}") // spring sintakses {id}
	public StudentDto findStudentById(@PathVariable("id") Integer studentId) {
		return studentService.findStudent(studentId);
	}

	@DeleteMapping("/student/{id}")
	public StudentDto removeStudent(@PathVariable Integer id) {
		return studentService.deleteStudent(id);
	}

	@PutMapping("/student/{id}")
	public StudentCredentialsDto editStudent(@PathVariable Integer id, 
			@RequestBody UpdateStudentDto updateStudentDto) {
		return studentService.updateStudent(id, updateStudentDto);
	}

	@PutMapping("/score/student/{id}")
	public boolean addScore(@PathVariable Integer id, @RequestBody ScoreDto scoreDto) {
		return studentService.addScore(id, scoreDto);
	}

}