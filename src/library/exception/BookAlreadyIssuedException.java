package library.exception;

/**
 * Thrown when trying to issue a book that is already issued.
 */
public class BookAlreadyIssuedException extends Exception {
    public BookAlreadyIssuedException(String message) {
        super(message);
    }
}
