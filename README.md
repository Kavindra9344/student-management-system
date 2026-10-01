# Student Management System 

A console-based Java application to manage student records. Built to practice core Java, OOP, the Collections Framework and file handling.

## Features

- Add a new student (with duplicate ID check)
- View all students in a formatted table
- Update student details (press Enter to keep the current value)
- Delete a student by ID
- Search students by name
- Data is saved automatically to `students.csv` and reloaded on startup
- Input validation for numbers and empty fields

## Concepts Used

| Concept | Where |
|---|---|
| OOP (encapsulation, separation of concerns) | `Student`, `StudentManager`, `Main` |
| Collections Framework | `LinkedHashMap<Integer, Student>`, `ArrayList` |
| File handling | `BufferedReader`, `FileReader`, `PrintWriter`, `FileWriter` |
| Exception handling | `try-with-resources`, `NumberFormatException`, `IOException` |

## Project Structure

```
student-management-system/
├── Main.java            # CLI menu and user input
├── Student.java         # Student model
├── StudentManager.java  # CRUD logic and file persistence
├── students.csv         # Data file
└── README.md
```

## How to Run

**Requirements:** JDK 14 or above

```
javac *.java
java Main
```

Or open the project in IntelliJ / Eclipse and run `Main.java`.

## Sample Output

```
===== Student Management System =====
1. Add Student
2. View All Students
3. Update Student
4. Delete Student
5. Search by Name
6. Exit
Enter choice: 2
+------+----------------------+-----+-----------------+--------+
| ID   | Name                 | Age | Course          | Marks  |
+------+----------------------+-----+-----------------+--------+
| 101  | Kavindra R           | 21  | Backend Dev     | 75.00  |
+------+----------------------+-----+-----------------+--------+
```

## Author

Kavindra R
