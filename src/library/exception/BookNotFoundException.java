package library.exception;

/**
 * Thrown when a book with the given ID is not found in the library.
 */
public class BookNotFoundException extends Exception {
    public BookNotFoundException(String message) {
        super(message);
    }
}
