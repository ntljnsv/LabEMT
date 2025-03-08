package mk.finki.ukim.lab.model.exceptions;


public class NoAvailableCopiesException extends RuntimeException {
    public NoAvailableCopiesException(Long id) {
        super(String.format("There are no available copies of book with id %d", id));
    }
}
