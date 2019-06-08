package application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason="Entity with this Id is not found")
public class NotFoundEntityException extends RuntimeException{
    public NotFoundEntityException(String message) {
        super(message);
    }
}
