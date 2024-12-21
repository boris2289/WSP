package views;

import users.Manager;
import users.Teacher;
import users.Student;
import studyingProcess.Course;
import studyingProcess.CourseRegistrationManager;
import serialization.SerializationUtil;
import java.io.*;
import java.util.*;

public class CourseAdder {
    private static HashMap<String, Teacher> teachers;
    private static HashMap<String, Student> students;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CourseRegistrationManager manager = new CourseRegistrationManager();
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
}
