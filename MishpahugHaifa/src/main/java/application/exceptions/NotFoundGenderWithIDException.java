package application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason="Gender witn this Id is not found")
public class NotFoundGenderWithIDException extends RuntimeException {
    public NotFoundGenderWithIDException(String message) {
        super(message);
    }
}