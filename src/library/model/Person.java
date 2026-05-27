package library.model;

/**
 * Abstract parent class representing a Person.
 * Demonstrates: Abstraction, Encapsulation, Inheritance base
 */
public abstract class Person {

    private int id;
    private String name;

    // Constructor
    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and Setters (Encapsulation)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Abstract method — forces subclasses to implement their own version (Polymorphism)
    public abstract void displayDetails();
}
