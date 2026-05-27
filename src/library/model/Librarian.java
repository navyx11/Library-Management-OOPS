package library.model;

/**
 * Represents a Librarian.
 * Demonstrates: Inheritance (extends Person), Polymorphism (overrides displayDetails)
 */
public class Librarian extends Person {

    private String employeeCode;

    // Constructor
    public Librarian(int id, String name, String employeeCode) {
        super(id, name); // Call parent constructor
        this.employeeCode = employeeCode;
    }

    // --- Getters and Setters ---

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    // Polymorphism: Overrides Person's abstract displayDetails()
    @Override
    public void displayDetails() {
        System.out.println("==================================================");
        System.out.println("  LIBRARIAN DETAILS");
        System.out.println("==================================================");
        System.out.printf("  Librarian ID    : %d%n", getId());
        System.out.printf("  Name            : %s%n", getName());
        System.out.printf("  Employee Code   : %s%n", employeeCode);
        System.out.println("==================================================");
    }
}
