package library.model;

import java.time.LocalDate;

/**
 * Represents a Book in the library.
 * Demonstrates: Encapsulation (private fields + getters/setters), Constructor
 */
public class Book {

    private int bookId;
    private String bookName;
    private String authorName;
    private boolean isAvailable;
    private LocalDate issueDate; // Optional: used for fine calculation

    // Constructor
    public Book(int bookId, String bookName, String authorName) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.authorName = authorName;
        this.isAvailable = true; // New books are available by default
        this.issueDate = null;
    }

    // --- Getters and Setters (Encapsulation) ---

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    // Display book details
    public void displayDetails() {
        System.out.println("--------------------------------------------------");
        System.out.printf("  Book ID     : %d%n", bookId);
        System.out.printf("  Book Name   : %s%n", bookName);
        System.out.printf("  Author      : %s%n", authorName);
        System.out.printf("  Status      : %s%n", isAvailable ? "Available" : "Issued");
        if (issueDate != null) {
            System.out.printf("  Issued On   : %s%n", issueDate);
        }
        System.out.println("--------------------------------------------------");
    }
}
