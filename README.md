# 📚 Library Management System — Java OOP Assignment

## Project Structure

```
LibraryManagementSystem/
├── .vscode/
│   ├── launch.json       ← VS Code run config (press F5 to run)
│   └── settings.json     ← Java source/output path settings
├── src/
│   └── library/
│       ├── model/
│       │   ├── Person.java       ← Abstract parent class
│       │   ├── Book.java         ← Book with encapsulation
│       │   ├── Student.java      ← Extends Person
│       │   └── Librarian.java    ← Extends Person
│       ├── operations/
│       │   ├── LibraryOperations.java  ← Interface (Abstraction)
│       │   └── Library.java            ← Implements LibraryOperations
│       ├── exception/
│       │   ├── BookNotFoundException.java
│       │   ├── BookAlreadyIssuedException.java
│       │   ├── BorrowLimitExceededException.java
│       │   ├── StudentNotFoundException.java
│       │   └── UnauthorizedReturnException.java
│       └── main/
│           └── Main.java         ← Entry point with interactive menu
└── out/                          ← Compiled .class files (auto-generated)
```

---

## OOP Concepts Used (For Your Viva)

| Concept        | Where Used |
|----------------|-----------|
| **Class & Object** | Book, Student, Librarian, Library, Person — all are classes. Objects are created in Main.java |
| **Encapsulation** | All fields in Book, Student, Librarian are `private`. Accessed via getters/setters |
| **Inheritance** | `Student extends Person`, `Librarian extends Person` |
| **Polymorphism** | `displayDetails()` is abstract in Person, overridden differently in Student and Librarian |
| **Abstraction** | `Person` is abstract. `LibraryOperations` is an interface implemented by `Library` |
| **Constructor** | Every class has a constructor: `Book(id, name, author)`, `Student(id, name, dept)` etc. |
| **Exception Handling** | 5 custom exceptions with try-catch in Main.java and Library.java |

---

## How to Set Up in VS Code

### Step 1 — Install VS Code
Download from https://code.visualstudio.com

### Step 2 — Install the Java Extension Pack
1. Open VS Code
2. Press `Ctrl+Shift+X` (Extensions)
3. Search: **Extension Pack for Java**
4. Click Install (by Microsoft)

This installs: Language Support, Debugger, Test Runner, Maven, IntelliSense.

### Step 3 — Install Java JDK (if not installed)
- Download JDK 17 or 21 from https://adoptium.net
- Install it, then restart VS Code
- Check: open Terminal → type `java --version`

### Step 4 — Open the Project
1. Open VS Code
2. `File → Open Folder` → select the `LibraryManagementSystem` folder
3. VS Code will auto-detect the Java project

### Step 5 — Run the Program

**Option A — Easy way (F5):**
- Open `src/library/main/Main.java`
- Press `F5` or click the ▶ Run button at the top right

**Option B — Terminal:**
```bash
# From inside LibraryManagementSystem folder:

# Compile
javac -d out src/library/exception/*.java src/library/model/*.java src/library/operations/*.java src/library/main/*.java

# Run
java -cp out library.main.Main
```

---

## Sample Test Flow

The program loads 5 books and 3 students automatically on startup.

Try this sequence:
1. Choose `2` → View all books (see IDs: 101–105)
2. Choose `4` → Issue book: Book ID `101`, Student ID `201`
3. Choose `6` → View student `201` — see the borrowed book listed
4. Choose `4` → Try issuing `101` again → see "already issued" error
5. Choose `4` → Issue 3 books to student `201` → try a 4th → see borrow limit error
6. Choose `5` → Return book `101` for student `201`
7. Choose `7` → Search by author: `Robert C. Martin`

---

## Fine Calculation (Optional Feature — Already Implemented)
- Loan period: **14 days**
- Fine: **Rs. 2 per extra day**
- Fine is displayed automatically on book return if overdue

---

## Compile Order (Important!)
Always compile in this order to avoid dependency errors:
1. `exception/` (no dependencies)
2. `model/` (depends on nothing)
3. `operations/` (depends on model + exception)
4. `main/` (depends on everything)

The single `javac` command above handles this automatically.
