package ABSMovies.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ApiUserException.class)
    public ResponseEntity<Object> handlerApiUserException(ApiUserException e){
        HttpStatus status = HttpStatus.CONFLICT;
        ExceptionEntity apiException = new ExceptionEntity(
                e.getMessage(),
                status
        );

        return new ResponseEntity<>(apiException, status);
    }
}
