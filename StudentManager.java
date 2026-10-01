import java.io.*;
import java.util.*;

public class StudentManager {
    private static final String FILE_NAME = "students.csv";

    private final Map<Integer, Student> students = new LinkedHashMap<>();

    public StudentManager() {
        loadFromFile();
    }

    public boolean addStudent(Student s) {
        if (students.containsKey(s.getId())) return false;
        students.put(s.getId(), s);
        saveToFile();
        return true;
    }

    public Student getStudent(int id) {
        return students.get(id);
    }

    public boolean updateStudent(int id, String name, Integer age, String course, Double marks) {
        Student s = students.get(id);
        if (s == null) return false;
        if (name != null) s.setName(name);
        if (age != null) s.setAge(age);
        if (course != null) s.setCourse(course);
        if (marks != null) s.setMarks(marks);
        saveToFile();
        return true;
    }

    public boolean deleteStudent(int id) {
        if (students.remove(id) == null) return false;
        saveToFile();
        return true;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }

    public List<Student> searchByName(String keyword) {
        List<Student> result = new ArrayList<>();
        for (Student s : students.values()) {
            if (s.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(s);
            }
        }
        return result;
    }

    private void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students.values()) {
                pw.println(s.toCsv());
            }
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;
                try {
                    Student s = Student.fromCsv(line);
                    students.put(s.getId(), s);
                } catch (Exception e) {
                    System.out.println("Skipping bad line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}