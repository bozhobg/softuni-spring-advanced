package bg.softuni.mobiLeLeLe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PersistException extends DbException {

    public PersistException(String message) {
        super(message);
    }
}
