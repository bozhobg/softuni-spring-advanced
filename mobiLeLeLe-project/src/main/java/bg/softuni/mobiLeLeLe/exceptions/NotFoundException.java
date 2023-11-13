package bg.softuni.mobiLeLeLe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends DbException {

    private Long id;

    public NotFoundException(String message) {
        super(message);
    }
    public NotFoundException(String message, Long id) {
        super(message);
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }
}
