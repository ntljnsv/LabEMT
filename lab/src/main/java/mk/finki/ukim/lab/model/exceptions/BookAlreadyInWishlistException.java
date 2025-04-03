package mk.finki.ukim.lab.model.exceptions;

public class BookAlreadyInWishlistException extends RuntimeException {
    public BookAlreadyInWishlistException(String username, Long bookId) {
        super(String.format("User %s's wishlist already contains book with id %d", username, bookId));
    }
}
