package bg.softuni.mobiLeLeLe.web;

import bg.softuni.mobiLeLeLe.exceptions.DbException;
import bg.softuni.mobiLeLeLe.exceptions.ErrorInfoRest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalRestExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DbException.class)
    public @ResponseBody ErrorInfoRest handleRestError(HttpServletRequest httpReq, DbException e) {
//        Catch exception and return customized error object with req info
        return new ErrorInfoRest(httpReq.getRequestURL().toString(), e.getMessage());
    }
}
