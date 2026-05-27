package library.operations;

import library.exception.*;
import library.model.Book;
import library.model.Student;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Core Library class that manages books and students.
 * Demonstrates: Implements LibraryOperations interface (Abstraction)
 */
public class Library implements LibraryOperations {

    private static final double FINE_PER_DAY = 2.0; // Rs. 2 per day after 14 days
    private static final int MAX_LOAN_DAYS = 14;

    private List<Book> books;
    private List<Student> students;

    // Constructor
    public Library() {
        this.books = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    // ===================== BOOK OPERATIONS =====================

    @Override
    public void addBook(Book book) {
        books.add(book);
        System.out.println("  [SUCCESS] Book added: \"" + book.getBookName() + "\" (ID: " + book.getBookId() + ")");
    }

    @Override
    public void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("  No books in the library yet.");
            return;
        }
        System.out.println("\n  ===== ALL BOOKS IN LIBRARY (" + books.size() + " total) =====");
        for (Book book : books) {
            book.displayDetails();
        }
    }

    // ===================== STUDENT OPERATIONS =====================

    public void addStudent(Student student) {
        students.add(student);
        System.out.println("  [SUCCESS] Student registered: " + student.getName() + " (ID: " + student.getId() + ")");
    }

    @Override
    public void viewStudentDetails(int studentId) throws StudentNotFoundException {
        Student student = findStudentById(studentId);
        student.displayDetails();
    }

    // ===================== ISSUE & RETURN =====================

    @Override
    public void issueBook(int bookId, int studentId)
            throws BookNotFoundException, BookAlreadyIssuedException, BorrowLimitExceededException {

        Book book = findBookById(bookId);
        Student student;

        try {
            student = findStudentById(studentId);
        } catch (StudentNotFoundException e) {
            System.out.println("  [ERROR] " + e.getMessage());
            return;
        }

        // Rule: Cannot issue an already issued book
        if (!book.isAvailable()) {
            throw new BookAlreadyIssuedException(
                    "Book \"" + book.getBookName() + "\" is already issued to someone else.");
        }

        // Rule: Student cannot borrow more than MAX_BORROW_LIMIT books
        if (student.hasReachedLimit()) {
            throw new BorrowLimitExceededException(
                    "Student \"" + student.getName() + "\" has already borrowed the maximum of "
                            + Student.MAX_BORROW_LIMIT + " books.");
        }

        // Issue the book
        book.setAvailable(false);
        book.setIssueDate(LocalDate.now());
        student.borrowBook(book);

        System.out.println("  [SUCCESS] Book \"" + book.getBookName() + "\" issued to " + student.getName()
                + " on " + LocalDate.now());
    }

    @Override
    public void returnBook(int bookId, int studentId)
            throws BookNotFoundException, StudentNotFoundException, UnauthorizedReturnException {

        Book book = findBookById(bookId);
        Student student = findStudentById(studentId);

        // Rule: Student can return only books they borrowed
        if (!student.hasBorrowed(book)) {
            throw new UnauthorizedReturnException(
                    "Student \"" + student.getName() + "\" did not borrow book \"" + book.getBookName() + "\".");
        }

        // Calculate fine if applicable
        double fine = calculateFine(book);

        // Return the book
        student.returnBook(book);
        book.setAvailable(true);

        LocalDate issuedOn = book.getIssueDate();
        book.setIssueDate(null);

        System.out.println("  [SUCCESS] Book \"" + book.getBookName() + "\" returned by " + student.getName() + ".");
        if (issuedOn != null) {
            long daysHeld = ChronoUnit.DAYS.between(issuedOn, LocalDate.now());
            System.out.println("  Days held   : " + daysHeld);
        }
        if (fine > 0) {
            System.out.printf("  Fine Due    : Rs. %.2f (overdue by %d days)%n",
                    fine, (long) (fine / FINE_PER_DAY));
        } else {
            System.out.println("  Fine        : No fine.");
        }
    }

    // ===================== SEARCH OPERATIONS (Optional) =====================

    public void searchBookByAuthor(String authorName) {
        System.out.println("\n  ===== SEARCH RESULTS FOR AUTHOR: \"" + authorName + "\" =====");
        boolean found = false;
        for (Book book : books) {
            if (book.getAuthorName().equalsIgnoreCase(authorName)) {
                book.displayDetails();
                found = true;
            }
        }
        if (!found) {
            System.out.println("  No books found by author \"" + authorName + "\".");
        }
    }

    public void searchStudentById(int studentId) {
        try {
            Student student = findStudentById(studentId);
            student.displayDetails();
        } catch (StudentNotFoundException e) {
            System.out.println("  [ERROR] " + e.getMessage());
        }
    }

    // ===================== HELPER METHODS =====================

    private Book findBookById(int bookId) throws BookNotFoundException {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        throw new BookNotFoundException("Book with ID " + bookId + " not found in the library.");
    }

    private Student findStudentById(int studentId) throws StudentNotFoundException {
        for (Student student : students) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        throw new StudentNotFoundException("Student with ID " + studentId + " not found.");
    }

    private double calculateFine(Book book) {
        if (book.getIssueDate() == null) return 0;
        long daysHeld = ChronoUnit.DAYS.between(book.getIssueDate(), LocalDate.now());
        if (daysHeld > MAX_LOAN_DAYS) {
            return (daysHeld - MAX_LOAN_DAYS) * FINE_PER_DAY;
        }
        return 0;
    }

    // Getters for use in menu
    public List<Book> getBooks() {
        return books;
    }

    public List<Student> getStudents() {
        return students;
    }
}
