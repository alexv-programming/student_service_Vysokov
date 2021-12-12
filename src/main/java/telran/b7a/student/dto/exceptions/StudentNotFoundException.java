package telran.b7a.student.dto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
//, reason = "student not found")
//Not_found = 404 Error
// needs <- application.properties
//student-service
//https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#application-properties.server
public class StudentNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9118107565204976641L;//doesn't
	public StudentNotFoundException(int id) {
		super("Student with id " + id + " not found");
	}
}
