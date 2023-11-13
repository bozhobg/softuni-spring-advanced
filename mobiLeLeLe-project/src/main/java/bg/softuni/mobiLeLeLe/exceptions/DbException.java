package bg.softuni.mobiLeLeLe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DbException extends RuntimeException{


    public DbException(String message) {
        super(message);
    }


}
