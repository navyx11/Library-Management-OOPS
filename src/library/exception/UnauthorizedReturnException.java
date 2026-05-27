package library.exception;

/**
 * Thrown when a student tries to return a book they did not borrow.
 */
public class UnauthorizedReturnException extends Exception {
    public UnauthorizedReturnException(String message) {
        super(message);
    }
}
