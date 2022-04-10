package ABSMovies.exception;

public class ApiUserException extends RuntimeException {

    public ApiUserException(String message) {
        super(message);
    }
    public ApiUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
