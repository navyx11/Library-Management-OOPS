package library.exception;

/**
 * Thrown when a student tries to borrow more books than the allowed limit.
 */
public class BorrowLimitExceededException extends Exception {
    public BorrowLimitExceededException(String message) {
        super(message);
    }
}
