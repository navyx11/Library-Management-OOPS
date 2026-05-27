package library.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Student.
 * Demonstrates: Inheritance (extends Person), Encapsulation, Polymorphism (overrides displayDetails)
 */
public class Student extends Person {

    private String department;
    private List<Book> borrowedBooks;

    public static final int MAX_BORROW_LIMIT = 3;

    // Constructor
    public Student(int id, String name, String department) {
        super(id, name); // Call parent constructor
        this.department = department;
        this.borrowedBooks = new ArrayList<>();
    }

    // --- Getters and Setters ---

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    // Check if student has reached the borrow limit
    public boolean hasReachedLimit() {
        return borrowedBooks.size() >= MAX_BORROW_LIMIT;
    }

    // Add a book to borrowed list
    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    // Remove a book from borrowed list
    public boolean returnBook(Book book) {
        return borrowedBooks.remove(book);
    }

    // Check if student has borrowed a specific book
    public boolean hasBorrowed(Book book) {
        return borrowedBooks.contains(book);
    }

    // Polymorphism: Overrides Person's abstract displayDetails()
    @Override
    public void displayDetails() {
        System.out.println("==================================================");
        System.out.println("  STUDENT DETAILS");
        System.out.println("==================================================");
        System.out.printf("  Student ID  : %d%n", getId());
        System.out.printf("  Name        : %s%n", getName());
        System.out.printf("  Department  : %s%n", department);
        System.out.printf("  Books Borrowed (%d/%d):%n", borrowedBooks.size(), MAX_BORROW_LIMIT);
        if (borrowedBooks.isEmpty()) {
            System.out.println("    (No books currently borrowed)");
        } else {
            for (Book book : borrowedBooks) {
                System.out.printf("    -> [ID: %d] %s by %s%n",
                        book.getBookId(), book.getBookName(), book.getAuthorName());
            }
        }
        System.out.println("==================================================");
    }
}
