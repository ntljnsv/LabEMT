package mk.finki.ukim.lab.model.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(Long id) {
        super(String.format("Author with %d does not exists)", id));
    }

    public AuthorNotFoundException(String name) {
        super(String.format("Author with name %s does not exist", name));
    }
}
