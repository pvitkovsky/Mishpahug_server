package application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.SEE_OTHER, reason="Entity is exists")
public class EntityExistsException extends RuntimeException {
    public EntityExistsException(String message) {
        super(message);
    }
}