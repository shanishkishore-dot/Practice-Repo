package student;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManager manager = new StudentManager();
        int choice;

        do {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. view All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. update Student by ID ");
            System.out.println("5. Delete Student by ID");
            System.out.println("6.Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine().trim());


            switch (choice) {
                case 1:
                    System.out.println("Enter Student ID");
                    int id = Integer.parseInt(sc.nextLine().trim());
                    if (manager.findStudent(id) != null) {
                        System.out.println("A Student with this ID already exists!");
                        break;
                    }
                    System.out.println("Enter Student Name:");
                    String name = sc.nextLine().trim();
                    System.out.println("Enter Student age:");
                    int age = Integer.parseInt(sc.nextLine().trim());
                    System.out.println("Enter Student marks:");
                    double marks = Double.parseDouble(sc.nextLine().trim());
                    manager.addStudent(new Student(id, name, age, marks));
                    break;
                case 2:
                    manager.viewStudents();
                    break;
                case 3:
                    System.out.print("Enter Student ID to search: ");
                    int searchId = Integer.parseInt(sc.nextLine().trim());
                    Student found = manager.findStudent(searchId);
                    if (found != null) {
                        found.display();
                    } else {
                        System.out.println("Student not found");
                    }
                    break;
                case 4:
                    System.out.print("Enter ID to update: ");
                    int uid = Integer.parseInt(sc.nextLine().trim());
                    if (manager.findStudent(uid) == null) {
                        System.out.println(" Student not found");
                        break;
                    }
                    System.out.println("Enter new Name:");
                    String newName = sc.nextLine().trim();
                    System.out.println("Enter new Age:");
                    int newAge = Integer.parseInt(sc.nextLine().trim());
                    System.out.println("Enter new Marks:");
                    double newMarks = Double.parseDouble(sc.nextLine().trim());
                    manager.updateStudent(uid, newName, newAge, newMarks);

                    break;

                case 5:
                    System.out.print("Enter ID to delete: ");
                    int did = Integer.parseInt(sc.nextLine().trim());
                    manager.deleteStudent(did);
                    break;

                case 6:
                    System.out.println("GoodBye!");
                    break;
                default:
                    System.out.println("Invalid choice. try again");
            }
        } catch(NumberFormatException e){
            System.out.println("Please enter a valid number ");
            choice = 0;
        }
    } while (choice !=6);
    sc.close();

    }
}
