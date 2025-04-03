package mk.finki.ukim.lab.model.api_exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiError {

    private HttpStatus status;
    private String message;


    public ApiError(HttpStatus status, Throwable ex) {
        this.status = status;
        this.message = ex.getMessage();
    }
}
