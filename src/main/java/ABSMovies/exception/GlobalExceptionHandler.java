package ABSMovies.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ApiUserException.class)
    public ResponseEntity<Object> handlerApiUserException(ApiUserException e){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ExceptionEntity apiException = new ExceptionEntity(
                e.getMessage(),
                status,
                ZonedDateTime.now(ZoneId.of("Europe/Moscow"))
        );
        return new ResponseEntity<>(apiException, status);
    }
}
