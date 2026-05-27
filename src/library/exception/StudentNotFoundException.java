package library.exception;

/**
 * Thrown when a student with the given ID is not found.
 */
public class StudentNotFoundException extends Exception {
    public StudentNotFoundException(String message) {
        super(message);
    }
}
