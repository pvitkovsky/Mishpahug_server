package application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.SEE_OTHER, reason="Entity exists")
public class EntityExistsDException extends RuntimeException {
    public EntityExistsDException(String message) {
        super(message);
    }
}