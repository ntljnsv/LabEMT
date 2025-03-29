package mk.finki.ukim.lab.model.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException {

    public UsernameAlreadyExistsException(String username) {
        super(String.format("Username %s already exists", username));
    }
}
