package application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason="Address with this Id is not found")
public class NotFoundAddressWithIDException  extends RuntimeException{
    public NotFoundAddressWithIDException(String message) {
        super(message);
    }
}
