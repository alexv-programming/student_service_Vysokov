package telran.b7a.student.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import telran.b7a.student.dao.StudentRepository;
import telran.b7a.student.dto.ScoreDto;
import telran.b7a.student.dto.StudentCredentialsDto;
import telran.b7a.student.dto.StudentDto;
import telran.b7a.student.dto.UpdateStudentDto;
import telran.b7a.student.dto.exceptions.StudentNotFoundException;
import telran.b7a.student.model.Student;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public boolean addStudent(StudentCredentialsDto studentCredentialsDto) {
		if (studentRepository.findById(studentCredentialsDto.getId()).isPresent()) {
			return false;
		}
//		Student student = new Student(studentCredentialsDto.getId(), 
//										studentCredentialsDto.getName(),
//										studentCredentialsDto.getPassword());
		Student student = modelMapper.map(studentCredentialsDto, Student.class);
		studentRepository.save(student);
		return true;
	}

	@Override
	public StudentDto findStudent(Integer id) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new StudentNotFoundException(id));
		return modelMapper.map(student, StudentDto.class);
//				StudentDto.builder()
//								.id(student.getId())
//								.name(student.getName())
//								.scores(student.getScores())
//								.build();
	}

	@Override
	public StudentDto deleteStudent(Integer id) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new StudentNotFoundException(id));
//		StudentDto studentDto = findStudent(id);
		studentRepository.deleteStudent(id);
		return modelMapper.map(student, StudentDto.class);
	}

	@Override
	public StudentCredentialsDto updateStudent(Integer id, UpdateStudentDto updateStudentDto) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new StudentNotFoundException(id));
		student.setPassword(updateStudentDto.getPassword());
		student.setName(updateStudentDto.getName());
		return modelMapper.map(student, StudentCredentialsDto.class);
//				StudentCredentialsDto.builder()
//										.id(student.getId())
//										.name(student.getName())
//										.password(student.getPassword())
//										.build();
	}

	@Override
	public boolean addScore(Integer id, ScoreDto scoreDto) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new StudentNotFoundException(id));
		return student.addScore(scoreDto.getExamName(), scoreDto.getScore());
	}

	@Override
	public List<StudentDto> findStudentsByName(String name) {
		return studentRepository.findAll().stream()
										.filter(s -> name.equalsIgnoreCase(s.getName()))
										.map(s -> modelMapper.map(s, StudentDto.class))
										.collect(Collectors.toList());
	}

}
