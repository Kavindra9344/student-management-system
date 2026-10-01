import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final StudentManager manager = new StudentManager();

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewAll();
                case 3 -> updateStudent();
                case 4 -> deleteStudent();
                case 5 -> searchStudent();
                case 6 -> {
                    System.out.println("Bye! Data saved to students.csv");
                    return;
                }
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n===== Student Management System =====");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. Search by Name");
        System.out.println("6. Exit");
    }

    private static void addStudent() {
        int id = readInt("ID: ");
        if (manager.getStudent(id) != null) {
            System.out.println("ID already exists!");
            return;
        }
        String name = readString("Name: ");
        int age = readInt("Age: ");
        String course = readString("Course: ");
        double marks = readDouble("Marks: ");

        manager.addStudent(new Student(id, name, age, course, marks));
        System.out.println("Student added.");
    }

    private static void viewAll() {
        printTable(manager.getAllStudents());
    }

    private static void updateStudent() {
        int id = readInt("Enter ID to update: ");
        Student s = manager.getStudent(id);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.println("Leave blank to keep current value.");

        String name = readOptional("Name (" + s.getName() + "): ");
        String ageStr = readOptional("Age (" + s.getAge() + "): ");
        String course = readOptional("Course (" + s.getCourse() + "): ");
        String marksStr = readOptional("Marks (" + s.getMarks() + "): ");

        try {
            Integer age = ageStr.isEmpty() ? null : Integer.parseInt(ageStr);
            Double marks = marksStr.isEmpty() ? null : Double.parseDouble(marksStr);
            manager.updateStudent(id,
                    name.isEmpty() ? null : name,
                    age,
                    course.isEmpty() ? null : course,
                    marks);
            System.out.println("Student updated.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid number entered. Update cancelled.");
        }
    }

    private static void deleteStudent() {
        int id = readInt("Enter ID to delete: ");
        System.out.println(manager.deleteStudent(id) ? "Student deleted." : "Student not found.");
    }

    private static void searchStudent() {
        String keyword = readString("Enter name to search: ");
        printTable(manager.searchByName(keyword));
    }



    private static void printTable(List<Student> list) {
        if (list.isEmpty()) {
            System.out.println("No records found.");
            return;
        }
        String line = "+------+----------------------+-----+-----------------+--------+";
        System.out.println(line);
        System.out.println("| ID   | Name                 | Age | Course          | Marks  |");
        System.out.println(line);
        list.forEach(System.out::println);
        System.out.println(line);
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static String readString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim().replace(",", " ");
            if (!input.isEmpty()) return input;
            System.out.println("This field can't be empty.");
        }
    }

    private static String readOptional(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim().replace(",", " ");
    }
}