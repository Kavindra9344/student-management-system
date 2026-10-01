public class Student {
    private int id;
    private String name;
    private int age;
    private String course;
    private double marks;

    public Student(int id, String name, int age, String course, double marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
        this.marks = marks;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getCourse() { return course; }
    public double getMarks() { return marks; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setCourse(String course) { this.course = course; }
    public void setMarks(double marks) { this.marks = marks; }


    public String toCsv() {
        return id + "," + name + "," + age + "," + course + "," + marks;
    }

    public static Student fromCsv(String line) {
        String[] p = line.split(",");
        return new Student(
                Integer.parseInt(p[0].trim()),
                p[1].trim(),
                Integer.parseInt(p[2].trim()),
                p[3].trim(),
                Double.parseDouble(p[4].trim()));
    }

    @Override
    public String toString() {
        return String.format("| %-4d | %-20s | %-3d | %-15s | %-6.2f |",
                id, name, age, course, marks);
    }
}