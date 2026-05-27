package library.operations;

import library.model.Book;
import library.model.Student;
import library.exception.*;

/**
 * Interface defining core library operations.
 * Demonstrates: Abstraction (interface)
 */
public interface LibraryOperations {

    void addBook(Book book);

    void issueBook(int bookId, int studentId)
            throws BookNotFoundException, BookAlreadyIssuedException, BorrowLimitExceededException;

    void returnBook(int bookId, int studentId)
            throws BookNotFoundException, StudentNotFoundException, UnauthorizedReturnException;

    void viewAllBooks();

    void viewStudentDetails(int studentId) throws StudentNotFoundException;
}
