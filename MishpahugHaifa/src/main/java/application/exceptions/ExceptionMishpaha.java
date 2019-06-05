package application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Id Not Found")
public class ExceptionMishpaha extends Exception {

    public ExceptionMishpaha(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionMishpaha(String message) {
        super(message);
    }
}
