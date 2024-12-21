package views;
import java.time.LocalDate;

import users.Student;
import users.Teacher;
import users.User;
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

    private static final String STUDENTS_FILE = "students.txt";
    private static final String TEACHERS_FILE = "teachers.txt";
    private static final String COURSES_FILE = "courses.txt";
    private static HashMap<String, Student> students = new HashMap<>();
    private static HashMap<String, Teacher> teachers = new HashMap<>();
    private static HashMap<String, Course> courses = new HashMap<>();

    public static void main(String[] args) {
        loadStudents();
        loadTeachers();
        loadCourses();

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Welcome to the WSP ===");
        System.out.print("Enter your login (email): ");
        String email = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        User user = students.get(email);
        if (user == null) {
            user = teachers.get(email);
        }

        if (user == null) {
            System.out.println("No account found. Registering a new user.");
            user = registerNewUser(scanner, email);
        } else {
            System.out.println("Account found. Verifying password...");
            if (!password.equals(user.getPassword())) { // Simplified password check
                System.out.println("Login failed. Incorrect password.");
                return;
            }
        }

        System.out.println("Login successful!");
        System.out.println(user);

        assignRolePrivileges(user);

//        if (user instanceof Student) {
//            System.out.println("Redirecting to course registration...");
//            registerForCourses((Student) user);
//        }

        saveStudents();
        saveTeachers();
    }

    private static User registerNewUser(Scanner scanner, String email) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter your password: ");
        String password = scanner.nextLine(); 

        System.out.print("Enter your phone number: ");
        String phoneNumber = scanner.nextLine();

        Role role = chooseRole(scanner);

        Language language = chooseLanguage(scanner);

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

    private static Language chooseLanguage(Scanner scanner) {
        System.out.println("Preferred language:");
        System.out.println("1. ENGLISH");
        System.out.println("2. RUSSIAN");
        System.out.println("3. KAZAKH");

        while (true) {
            System.out.print("Enter the number of your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    return Language.ENGLISH;
                case 2:
                    return Language.RUSSIAN;
                case 3:
                    return Language.KAZAKH;
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

    private static void loadStudents() {
        try (FileInputStream fileIn = new FileInputStream(STUDENTS_FILE)) {
            Object obj = SerializationUtil.deserializeObject(fileIn);
            if (obj instanceof HashMap<?, ?>) {
                students = (HashMap<String, Student>) obj;
            } else {
                System.err.println("Unexpected data format in students file. Starting fresh.");
                students = new HashMap<>();
            }
        } catch (FileNotFoundException e) {
            System.out.println("No previous student data found. Starting fresh.");
            students = new HashMap<>();
        } catch (IOException e) {
            System.err.println("Error loading student data: " + e.getMessage());
            students = new HashMap<>();
        }
    }

    private static void saveStudents() {
        try (FileOutputStream fileOut = new FileOutputStream(STUDENTS_FILE)) {
            SerializationUtil.serializeObject(students, fileOut);
        } catch (IOException e) {
            System.err.println("Error saving student data: " + e.getMessage());
        }
    }

    private static void loadTeachers() {
        try (FileInputStream fileIn = new FileInputStream(TEACHERS_FILE)) {
            Object obj = SerializationUtil.deserializeObject(fileIn);
            if (obj instanceof HashMap<?, ?>) {
                teachers = (HashMap<String, Teacher>) obj;
            } else {
                System.err.println("Unexpected data format in teachers file. Starting fresh.");
                teachers = new HashMap<>();
            }
        } catch (FileNotFoundException e) {
            System.out.println("No previous teacher data found. Starting fresh.");
            teachers = new HashMap<>();
        } catch (IOException e) {
            System.err.println("Error loading teacher data: " + e.getMessage());
            teachers = new HashMap<>();
        }
    }

    private static void saveTeachers() {
        try (FileOutputStream fileOut = new FileOutputStream(TEACHERS_FILE)) {
            SerializationUtil.serializeObject(teachers, fileOut);
        } catch (IOException e) {
            System.err.println("Error saving teacher data: " + e.getMessage());
        }
    }

    private static void loadCourses() {
        try (FileInputStream fileIn = new FileInputStream(COURSES_FILE)) {
            Object obj = SerializationUtil.deserializeObject(fileIn);
            if (obj instanceof HashMap<?, ?>) {
                courses = (HashMap<String, Course>) obj;
            } else {
                System.err.println("Unexpected data format in courses file. Starting fresh.");
                courses = new HashMap<>();
            }
        } catch (FileNotFoundException e) {
            System.out.println("No previous course data found. Starting fresh.");
            courses = new HashMap<>();
        } catch (IOException e) {
            System.err.println("Error loading course data: " + e.getMessage());
            courses = new HashMap<>();
        }
    }

    private static void registerForCourses(Student student) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Available courses:");

        for (Course course : courses.values()) {
            System.out.println(course);
        }

        System.out.print("Select a course by entering its ID: ");
        String courseId = scanner.nextLine();

        Course selectedCourse = courses.get(courseId);
        if (selectedCourse != null) {
            student.enrollInCourse(selectedCourse);
            System.out.println("Course " + selectedCourse.getCourseName() + " registered successfully.");
        } else {
            System.out.println("Invalid course ID. Please try again.");
        }
    }
}
