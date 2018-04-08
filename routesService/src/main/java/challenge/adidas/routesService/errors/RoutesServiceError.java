package challenge.adidas.routesService.errors;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;

/**
 * represents the service error
 * 
 * @author joseam
 *
 */
public class RoutesServiceError {
	HttpStatus status;
	String message;
	LocalDate date;

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
}
