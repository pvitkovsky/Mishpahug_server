package application.configurations;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import javax.persistence.EntityNotFoundException;
import javax.security.auth.login.FailedLoginException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

	@ExceptionHandler({EntityNotFoundException.class, IllegalArgumentException.class, FailedLoginException.class})
	public final ResponseEntity<String> handleException(Exception ex, WebRequest request) {
		HttpHeaders headers = new HttpHeaders();
		if (ex instanceof EntityNotFoundException) {
			HttpStatus status = HttpStatus.NOT_FOUND;
			return handleExceptionInternal(ex, "User Not Found", headers, status, request);
		}
		if (ex instanceof FailedLoginException) {
			HttpStatus status = HttpStatus.FORBIDDEN;
			return handleExceptionInternal(ex, "Incorrect password or username", headers, status, request);
		}
		return handleExceptionInternal(ex, "Unspecified error", headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	protected ResponseEntity<String> handleExceptionInternal(Exception ex, String body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
			request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
		}
		return new ResponseEntity<>(body, headers, status);
	}
}
