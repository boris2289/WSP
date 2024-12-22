package users;
import ENUMS.Language;
import ENUMS.Role;
import abstractt.User;
import managers.AccountManager;
import managers.CourseRegistrationManager;
import serialization.SerializationUtil;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class Admin {

    private static final String STUDENTS_FILE = "students.txt";
    private static final String TEACHERS_FILE = "teachers.txt";
    private static final String ACCOUNT_MANAGER_FILE = "account_managers.txt";
    private static final String COURSE_REGISTRATION_MANAGERS_FILE  = "registration_managers.txt";
    private static HashMap<String, Student> students = new HashMap<>();
    private static HashMap<String, Teacher> teachers = new HashMap<>();
    private static HashMap<String, AccountManager> accountManagers = new HashMap<>();
    private static HashMap<String, CourseRegistrationManager> coursesRegistrationManagers = new HashMap<>();

    public static void loadAllInformation(){
        loadStudents();
        loadTeachers();
        loadAccountManagers();
        loadCoursesRegistrationManagers();

    }



    public static User registerNewUser(Scanner scanner, String email, Role role) {
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
        }

        else if (role == Role.TEACHER) {
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
        else {
            System.out.print("Enter your years of experience: ");
            int experience = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter your salary: ");
            int salary = Integer.parseInt(scanner.nextLine());

            LocalDate localDate = LocalDate.now();
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            System.out.print("Your hiring date: " + date + '\n');

            if (role == Role.ACCOUNT_MANAGER) {
                newUser = new AccountManager(email, name, email, phoneNumber, password, salary, date);
                accountManagers.put(email, (AccountManager) newUser);
            } else {
                newUser = new CourseRegistrationManager(email, name, email, phoneNumber, password);
                coursesRegistrationManagers.put(email, (CourseRegistrationManager) newUser);
            }
        }

        return newUser;
    }


    public static Language chooseLanguage(Scanner scanner) {
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

    private static void loadAccountManagers() {
        try (FileInputStream fileIn = new FileInputStream(ACCOUNT_MANAGER_FILE)) {
            Object obj = SerializationUtil.deserializeObject(fileIn);
            if (obj instanceof HashMap<?, ?>) {
                accountManagers = (HashMap<String, AccountManager>) obj;
            } else {
                System.err.println("Unexpected data format in accountManagers file. Starting fresh.");
                accountManagers = new HashMap<>();
            }
        } catch (FileNotFoundException e) {
            System.out.println("No previous teacher data found. Starting fresh.");
            accountManagers = new HashMap<>();
        } catch (IOException e) {
            System.err.println("Error loading teacher data: " + e.getMessage());
            accountManagers = new HashMap<>();
        }
    }

    private static void loadCoursesRegistrationManagers() {
        try (FileInputStream fileIn = new FileInputStream(COURSE_REGISTRATION_MANAGERS_FILE)) {
            Object obj = SerializationUtil.deserializeObject(fileIn);
            if (obj instanceof HashMap<?, ?>) {
                coursesRegistrationManagers = (HashMap<String, CourseRegistrationManager>) obj;
            } else {
                System.err.println("Unexpected data format in coursesRegistrationManagers file. Starting fresh.");
                coursesRegistrationManagers = new HashMap<>();
            }
        } catch (FileNotFoundException e) {
            System.out.println("No previous teacher data found. Starting fresh.");
            coursesRegistrationManagers = new HashMap<>();
        } catch (IOException e) {
            System.err.println("Error loading teacher data: " + e.getMessage());
            coursesRegistrationManagers = new HashMap<>();
        }
    }

    public static HashMap<String, Student> getStudents() {
        return students;
    }
    public static HashMap<String, Teacher> getTeachers() {
        return teachers;
    }
    public static HashMap<String, AccountManager> getAccountManagers() {
        return accountManagers;
    }
    public static HashMap<String, CourseRegistrationManager> getCoursesRegistrationManagers() {
        return coursesRegistrationManagers;
    }


    public static void saveStudents() {
        try (FileOutputStream fileOut = new FileOutputStream(STUDENTS_FILE)) {
            SerializationUtil.serializeObject(students, fileOut);
        } catch (IOException e) {
            System.err.println("Error saving student data: " + e.getMessage());
        }
    }


    public static void saveTeachers() {
        try (FileOutputStream fileOut = new FileOutputStream(TEACHERS_FILE)) {
            SerializationUtil.serializeObject(teachers, fileOut);
        } catch (IOException e) {
            System.err.println("Error saving teacher data: " + e.getMessage());
        }
    }

    public static void saveAccountManagers() {
        try (FileOutputStream fileOut = new FileOutputStream(ACCOUNT_MANAGER_FILE)) {
            SerializationUtil.serializeObject(accountManagers, fileOut);
        } catch (IOException e) {
            System.err.println("Error saving accountManagers data: " + e.getMessage());
        }
    }
    public static void saveCoursesRegistrationManagers() {
        try (FileOutputStream fileOut = new FileOutputStream(COURSE_REGISTRATION_MANAGERS_FILE)) {
            SerializationUtil.serializeObject(coursesRegistrationManagers, fileOut);
        } catch (IOException e) {
            System.err.println("Error saving coursesRegistrationManagers data: " + e.getMessage());
        }
    }

}
