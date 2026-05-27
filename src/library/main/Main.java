package library.main;

import library.exception.*;
import library.model.Book;
import library.model.Librarian;
import library.model.Student;
import library.operations.Library;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main class — entry point of the Library Management System.
 * Contains the interactive menu and ties all components together.
 */
public class Main {

    private static Library library = new Library();
    private static Librarian librarian = new Librarian(1, "Dr. Sharma", "LIB-001");
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║       COLLEGE LIBRARY MANAGEMENT SYSTEM          ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        System.out.println("  Managed by: " + librarian.getName() + " (" + librarian.getEmployeeCode() + ")");

        // Preload some sample data so you can test right away
        preloadSampleData();

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("  Enter your choice: ");

            switch (choice) {
                case 1 -> addBook();
                case 2 -> library.viewAllBooks();
                case 3 -> addStudent();
                case 4 -> issueBook();
                case 5 -> returnBook();
                case 6 -> viewStudentDetails();
                case 7 -> searchByAuthor();
                case 8 -> librarian.displayDetails();
                case 9 -> {
                    System.out.println("\n  Thank you! Goodbye.\n");
                    running = false;
                }
                default -> System.out.println("  [!] Invalid choice. Please enter a number from 1 to 9.");
            }
        }
        scanner.close();
    }

    // ===================== MENU =====================

    private static void printMenu() {
        System.out.println("\n┌──────────────────────────────────────────────────┐");
        System.out.println("│                     MAIN MENU                    │");
        System.out.println("├──────────────────────────────────────────────────┤");
        System.out.println("│  1. Add Book                                      │");
        System.out.println("│  2. View All Books                                │");
        System.out.println("│  3. Add Student                                   │");
        System.out.println("│  4. Issue Book to Student                         │");
        System.out.println("│  5. Return Book                                   │");
        System.out.println("│  6. View Student Details                          │");
        System.out.println("│  7. Search Book by Author                         │");
        System.out.println("│  8. View Librarian Details                        │");
        System.out.println("│  9. Exit                                          │");
        System.out.println("└──────────────────────────────────────────────────┘");
    }

    // ===================== ACTIONS =====================

    private static void addBook() {
        System.out.println("\n  --- ADD NEW BOOK ---");
        int id = readInt("  Enter Book ID       : ");
        System.out.print("  Enter Book Name     : ");
        String name = scanner.nextLine().trim();
        System.out.print("  Enter Author Name   : ");
        String author = scanner.nextLine().trim();

        library.addBook(new Book(id, name, author));
    }

    private static void addStudent() {
        System.out.println("\n  --- REGISTER NEW STUDENT ---");
        int id = readInt("  Enter Student ID    : ");
        System.out.print("  Enter Student Name  : ");
        String name = scanner.nextLine().trim();
        System.out.print("  Enter Department    : ");
        String dept = scanner.nextLine().trim();

        library.addStudent(new Student(id, name, dept));
    }

    private static void issueBook() {
        System.out.println("\n  --- ISSUE BOOK ---");
        int bookId = readInt("  Enter Book ID       : ");
        int studentId = readInt("  Enter Student ID    : ");

        try {
            library.issueBook(bookId, studentId);
        } catch (BookNotFoundException | BookAlreadyIssuedException | BorrowLimitExceededException e) {
            System.out.println("  [ERROR] " + e.getMessage());
        }
    }

    private static void returnBook() {
        System.out.println("\n  --- RETURN BOOK ---");
        int bookId = readInt("  Enter Book ID       : ");
        int studentId = readInt("  Enter Student ID    : ");

        try {
            library.returnBook(bookId, studentId);
        } catch (BookNotFoundException | StudentNotFoundException | UnauthorizedReturnException e) {
            System.out.println("  [ERROR] " + e.getMessage());
        }
    }

    private static void viewStudentDetails() {
        System.out.println("\n  --- VIEW STUDENT DETAILS ---");
        int studentId = readInt("  Enter Student ID    : ");

        try {
            library.viewStudentDetails(studentId);
        } catch (StudentNotFoundException e) {
            System.out.println("  [ERROR] " + e.getMessage());
        }
    }

    private static void searchByAuthor() {
        System.out.println("\n  --- SEARCH BY AUTHOR ---");
        System.out.print("  Enter Author Name   : ");
        String author = scanner.nextLine().trim();
        library.searchBookByAuthor(author);
    }

    // ===================== SAMPLE DATA =====================

    private static void preloadSampleData() {
        System.out.println("\n  [INFO] Loading sample data for demo...");

        // Add books
        library.addBook(new Book(101, "Data Structures and Algorithms", "Thomas Cormen"));
        library.addBook(new Book(102, "Clean Code", "Robert C. Martin"));
        library.addBook(new Book(103, "Object-Oriented Programming in Java", "Herbert Schildt"));
        library.addBook(new Book(104, "Database Management Systems", "Ramakrishnan"));
        library.addBook(new Book(105, "Operating System Concepts", "Abraham Silberschatz"));

        // Add students
        library.addStudent(new Student(201, "Priya Sharma", "Computer Science"));
        library.addStudent(new Student(202, "Rahul Verma", "Information Technology"));
        library.addStudent(new Student(203, "Ankit Joshi", "Electronics"));

        System.out.println("  [INFO] Sample data loaded. You can start using the system!\n");
    }

    // ===================== INPUT UTILITY =====================

    /**
     * Safely reads an integer, handles non-numeric input gracefully.
     */
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = scanner.nextInt();
                scanner.nextLine(); // consume leftover newline
                return value;
            } catch (InputMismatchException e) {
                System.out.println("  [!] Invalid input. Please enter a valid number.");
                scanner.nextLine(); // clear bad input
            }
        }
    }
}
