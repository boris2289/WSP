package views;
import java.time.LocalDate;

import managers.AccountManager;
import managers.CourseRegistrationManager;
import users.Admin;
import users.GraduateStudent;
import users.Student;
import users.Teacher;
import abstractt.User;
import ENUMS.Language;
import ENUMS.Role;
import serialization.SerializationUtil;
import studyingProcess.Course;
import java.time.ZoneId;
import java.util.Date;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class RegistrationProcess {

    public static void main(String[] args) {
        Admin admin = new Admin();
        Admin.loadAllInformation();

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Welcome to the WSP ===");
        System.out.println("What is your role ?");
        System.out.println("1. Student");
        System.out.println("2. Teacher");
        System.out.println("3. Course Registration Manager");
        System.out.println("4. Account Manager");

        System.out.print("Enter your login (email): ");
        String email = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        int choice = scanner.nextInt();
        Role chosenRole = null;
        User user = null;
        switch (choice) {
            case 1:
                user = Admin.getStudents().get(email);
                chosenRole = Role.STUDENT;
                break;

            case 2:
                user = Admin.getTeachers().get(email);
                chosenRole = Role.TEACHER;
                break;

            case 3:
                user = Admin.getCoursesRegistrationManagers().get(email);
                chosenRole = Role.COURSE_REGISTRATION_MANAGER;

            case 4:
                user = Admin.getAccountManagers().get(email);
                chosenRole = Role.ACCOUNT_MANAGER;
        }


        if (user == null) {
            System.out.println("No account found. Registering a new user.");
            user = registerNewUser(scanner, email, chosenRole);
        } else {
            System.out.println("Account found. Verifying password...");
            if (!password.equals(user.getPassword())) {
                System.out.println("Login failed. Incorrect password.");
                return;
            }
        }

        System.out.println("Login successful!");
        System.out.println(user);

        assignRolePrivileges(user);


        saveStudents();
        saveTeachers();
    }

    private static User registerNewUser(Scanner scanner, String email, Role role) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter your password: ");
        String password = scanner.nextLine(); 

        System.out.print("Enter your phone number: ");
        String phoneNumber = scanner.nextLine();

        Language language = Admin.chooseLanguage(scanner);

        User newUser;
        if (role == Role.STUDENT) {
            System.out.print("Enter your major: ");
            String major = scanner.nextLine();

            newUser = new Student(email, name, email, phoneNumber,password, language, major, "None", 0.0);
            students.put(email, (Student) newUser);
        } else {
            System.out.print("Enter your academic title: ");
            String title = scanner.nextLine();

            System.out.print("Enter your years of experience: ");
            int experience = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter your salary: ");
            int salary = Integer.parseInt(scanner.nextLine());

            LocalDate localDate = LocalDate.now();
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            System.out.print("Your hiring date: " + date + '\n');

            newUser = new Teacher(email, name, email, phoneNumber,password, language, title, experience, salary, date);
            teachers.put(email, (Teacher) newUser);
        }

        return newUser;
    }

    private static Role chooseRole(Scanner scanner) {
        System.out.println("Choose a role:");
        System.out.println("1. STUDENT");
        System.out.println("2. TEACHER");

        while (true) {
            System.out.print("Enter the number of your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    return Role.STUDENT;
                case 2:
                    return Role.TEACHER;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    private static void assignRolePrivileges(User user) {
        if (user instanceof Student) {
            System.out.println("Welcome, Student! Access granted to courses, grades, and student materials.");
        } else if (user instanceof Teacher) {
            System.out.println("Welcome, Teacher! Access granted to teaching materials and course management.");
        } else {
            System.out.println("Welcome! Access granted based on your role.");
        }
    }


    private static void saveStudents() {
        try (FileOutputStream fileOut = new FileOutputStream(STUDENTS_FILE)) {
            SerializationUtil.serializeObject(students, fileOut);
        } catch (IOException e) {
            System.err.println("Error saving student data: " + e.getMessage());
        }
    }


    private static void saveTeachers() {
        try (FileOutputStream fileOut = new FileOutputStream(TEACHERS_FILE)) {
            SerializationUtil.serializeObject(teachers, fileOut);
        } catch (IOException e) {
            System.err.println("Error saving teacher data: " + e.getMessage());
        }
    }


}
