package views;
import managers.AccountManager;
import managers.CourseRegistrationManager;
import users.Admin;
import users.Student;
import users.Teacher;
import abstractt.User;
import ENUMS.Role;
import java.util.Scanner;

public class RegistrationProcess {

    public static void main(String[] args) {
        Admin.loadAllInformation();

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Welcome to the WSP ===");
        System.out.println("What is your role ?");
        System.out.println("1. Student");
        System.out.println("2. Teacher");
        System.out.println("3. Course Registration Manager");
        System.out.println("4. Account Manager");

        int roleChoice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter your login (email): ");
        String email = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        Role chosenRole = null;
        User user = null;
        switch (roleChoice) {
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
                break;

            case 4:
                user = Admin.getAccountManagers().get(email);
                chosenRole = Role.ACCOUNT_MANAGER;
                break;
        }


        if (user == null) {
            System.out.println("No account found. Registering a new user.");
            user = Admin.registerNewUser(scanner, email, chosenRole);
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
    }


    private static void assignRolePrivileges(User user) {
        if (user instanceof Student) {
            System.out.println("Welcome, Student! Access granted to courses, grades, and student materials.");
        } else if (user instanceof Teacher) {
            System.out.println("Welcome, Teacher! Access granted to teaching materials and course management.");
        } else if (user instanceof AccountManager) {
            System.out.println("Welcome, AccountManager! Access granted to accounts management.");
        } else if (user instanceof CourseRegistrationManager) {
            System.out.println("Welcome, CourseRegistrationManager! Access granted to courses management.");
        } else {
            System.out.println("Welcome! Access granted based on your role.");
        }

        Admin.saveStudents();
        Admin.saveTeachers();
        Admin.saveCoursesRegistrationManagers();
        Admin.saveAccountManagers();
    }



}
