package application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason="Gender is not correct")
public class IncorrectGenderException extends RuntimeException {
    public IncorrectGenderException(String message) {
        super(message);
    }
}
