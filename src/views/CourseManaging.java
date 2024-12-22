package views;

import users.Teacher;
import users.Student;
import managers.CourseRegistrationManager;

import java.util.*;

public class CourseManaging {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CourseRegistrationManager manager = new CourseRegistrationManager("123", "Boris", "boris@gmail", "454523", "12345");

        System.out.println("Remove or add course?");
        System.out.println("1. Add");
        System.out.println("2. Remove");
        System.out.println("3. View information");

        int selected = Integer.parseInt(scanner.nextLine());

        switch (selected) {
            case 1:
                toAdd(scanner, manager);
                break;
            case 2:
                toRemove(scanner, manager);
                break;
            case 3:
                manager.printAllInfo();
        }
    }


    public static void toAdd(Scanner scanner, CourseRegistrationManager manager) {
        HashMap<String, Teacher> teachers = manager.getTeachers();
        HashMap<String, Student> students = manager.getStudents();

        System.out.println("Are you a teacher or a student?");
        System.out.println("1. Teacher");
        System.out.println("2. Student");
        System.out.print("Enter your choice: ");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                manager.handleTeacherActions(scanner, teachers);
                break;
            case 2:
                manager.handleStudentActions(scanner, students);
                break;
            default:
                System.out.println("Invalid choice. Please restart the application.");
        }
    }

    public static void toRemove(Scanner scanner, CourseRegistrationManager manager) {
        manager.removeCourse(scanner);
    }
}
