
package student;

import java.io.*;
import java.util.ArrayList;

public class StudentManager {
 public StudentManager() {
     loadFromFile();
 }
    private static final String FILE_NAME = "students.txt";

    private ArrayList<Student> students = new ArrayList<>();

    // Method to add a student
    public void addStudent(Student student) {
        students.add(student);
        saveToFile();
        System.out.println("Student added successfully!");
    }

    // Method to view all students
    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        for (Student student : students) {
            student.display();
            System.out.println("_______________");
        }
    }

    // Method to find a student by ID
    public Student findStudent(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    // View student records
    public void viewStudent() {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        for (Student student : students) {
            student.display();
        }
    }

    // Update student
    public void updateStudent(int id, String name, int age, double marks) {
        Student student = findStudent(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        student.setName(name);
        student.setAge(age);
        student.setMarks(marks);

        saveToFile();
        System.out.println("Student updated successfully!");
    }

    // Delete student
    public void deleteStudent(int id) {
        Student student = findStudent(id);

        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        students.remove(student);
        saveToFile();
        System.out.println("Student deleted successfully!");
    }

    // Save records to file
    public void saveToFile() {
        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (Student student : students) {
                writer.write(
                        student.getId() + "," +
                                student.getName() + "," +
                                student.getAge() + "," +
                                student.getMarks()
                );
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving records: " + e.getMessage());
        }
    }

    // Load records from file
    public void loadFromFile() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                try {
                    String[] data = line.split(",");

                    if (data.length != 4) {
                        continue;
                    }

                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    int age = Integer.parseInt(data[2]);
                    double marks = Double.parseDouble(data[3]);

                    if (findStudent(id) == null) {
                        students.add(new Student(id, name, age, marks));
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid student record.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading records: " + e.getMessage());
        }
    }
}