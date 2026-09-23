import java.util.ArrayList;
import java.util.Scanner;

// Represents one student and their grades
class Student {
    private String name;
    private ArrayList<Double> grades = new ArrayList<>();

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addGrade(double grade) {
        grades.add(grade);
    }

    public double getAverage() {
        if (grades.isEmpty()) {
            return 0;
        }
        double total = 0;
        for (double g : grades) {
            total += g;
        }
        return total / grades.size();
    }

    // Turns the average into a classification
    public String getClassification() {
        double avg = getAverage();
        if (avg >= 70) {
            return "First";
        } else if (avg >= 60) {
            return "2:1";
        } else if (avg >= 50) {
            return "2:2";
        } else if (avg >= 40) {
            return "Third";
        } else {
            return "Fail";
        }
    }

    public String toString() {
        return name + " | Grades: " + grades + " | Average: "
                + String.format("%.1f", getAverage()) + " | " + getClassification();
    }
}

public class StudentGradeTracker {
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n--- Student Grade Tracker ---");
            System.out.println("1. Add student");
            System.out.println("2. View all students");
            System.out.println("3. Search for a student");
            System.out.println("4. Exit");

            int choice = readInt("Choose an option: ", 1, 4);

            if (choice == 1) {
                addStudent();
            } else if (choice == 2) {
                viewStudents();
            } else if (choice == 3) {
                searchStudent();
            } else {
                running = false;
                System.out.println("Goodbye!");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Student name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        Student student = new Student(name);
        int count = readInt("How many grades? ", 1, 20);

        for (int i = 1; i <= count; i++) {
            double grade = readDouble("Grade " + i + " (0-100): ", 0, 100);
            student.addGrade(grade);
        }

        students.add(student);
        System.out.println("Student added.");
    }

    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students yet.");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }

    private static void searchStudent() {
        System.out.print("Enter name to search: ");
        String search = scanner.nextLine().trim().toLowerCase();

        boolean found = false;
        for (Student s : students) {
            if (s.getName().toLowerCase().contains(search)) {
                System.out.println(s);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No student found.");
        }
    }

    // Keeps asking until the user enters a whole number in range
    private static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("Enter a number between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("That's not a valid number.");
            }
        }
    }

    // Keeps asking until the user enters a decimal number in range
    private static double readDouble(String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("Enter a number between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("That's not a valid number.");
            }
        }
    }
}
