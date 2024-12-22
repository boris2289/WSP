package views;
import ENUMS.Language;
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
        System.out.println("Choose your preffered language");
        System.out.println("1. English");
        System.out.println("2. Russian");
        System.out.println("3. Kazakh");
        int choice = scanner.nextInt();
        scanner.nextLine();
        Language language = null;
        switch (choice) {
            case 1:
                language = Language.ENGLISH;
                break;
            case 2:
                language = Language.RUSSIAN;
                break;
            case 3:
                language = Language.KAZAKH;
                break;
            default:
                break;
        }

        switch (language) {
            case ENGLISH:
                System.out.println("=== Welcome to the WSP ===");
                System.out.println("Your action?");
                System.out.println("1. Log in or Register");
                System.out.println("2. View all users");
                System.out.println("3. Remove user from platform");
                break;
            case RUSSIAN:
                System.out.println("=== Добро пожаловать в WSP ===");
                System.out.println("Ваше действие?");
                System.out.println("1. Войти или зарегистрироваться");
                System.out.println("2. Просмотреть всех пользователей");
                System.out.println("3. Удалить пользователя с платформы");
                break;
            case KAZAKH:
                System.out.println("=== WSP-ға қош келдіңіз ===");
                System.out.println("Сіздің әрекетіңіз?");
                System.out.println("1. Кіру немесе тіркелу");
                System.out.println("2. Барлық пайдаланушыларды көру");
                System.out.println("3. Пайдаланушыны платформадан алып тастау");
                break;
        }


        int actionChoice = scanner.nextInt();
        scanner.nextLine();
        switch (actionChoice) {
            case 1:
                logIntoWSP(scanner, language);
                break;

            case 2:
                Admin.viewAllUsers();
                break;

            case 3:
                Admin.removeUser(scanner, language);
                break;
        }

    }

public static void logIntoWSP(Scanner scanner, Language language) {
    if (language.equals(Language.ENGLISH)){
        System.out.println("What is your role ?");
        System.out.println("1. Student");
        System.out.println("2. Teacher");
        System.out.println("3. Course Registration Manager");
        System.out.println("4. Account Manager");
    }
    else if (language.equals(Language.RUSSIAN)){
        System.out.println("Какая у вас роль?");
        System.out.println("1. Студент");
        System.out.println("2. Преподаватель");
        System.out.println("3. Менеджер по регистрации на курсы");
        System.out.println("4. Менеджер по учетным записям");

    }
    else if (language.equals(Language.KAZAKH)){
        System.out.println("Сіздің рөліңіз қандай?");
        System.out.println("1. Студент");
        System.out.println("2. Мұғалім");
        System.out.println("3. Курстарға тіркеу менеджері");
        System.out.println("4. Есептік жазбалар менеджері");
    }

    int roleChoice = scanner.nextInt();
    scanner.nextLine();
    if (language.equals(Language.ENGLISH)){
        System.out.print("Enter your login (email): ");
    }
    else if (language.equals(Language.RUSSIAN)){
        System.out.print("Введите ваш логин (email): ");
    }
    else if (language.equals(Language.KAZAKH)){
        System.out.print("Логиніңізді енгізіңіз (email): ");
    }

    String email = scanner.nextLine();

    switch (language) {
        case ENGLISH:
            System.out.print("Enter your password: ");
            break;
        case RUSSIAN:
            System.out.print("Введите ваш пароль: ");
            break;
        case KAZAKH:
            System.out.print("Парольді енгізіңіз: ");
            break;
    }

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
        switch (language) {
            case ENGLISH:
                System.out.println("No account found. Registering a new user.");
                break;
            case RUSSIAN:
                System.out.println("Аккаунт не найден. Регистрируем нового пользователя.");
                break;
            case KAZAKH:
                System.out.println("Аккаунт табылмады. Жаңа пайдаланушыны тіркеу.");
                break;
        }

        user = Admin.registerNewUser(scanner, email, chosenRole, language);
    } else {
        switch (language) {
            case ENGLISH:
                System.out.println("Account found. Verifying password...");
                break;
            case RUSSIAN:
                System.out.println("Аккаунт найден. Проверка пароля...");
                break;
            case KAZAKH:
                System.out.println("Аккаунт табылды. Парольді тексеру...");
                break;
        }

        if (!password.equals(user.getPassword())) {
            switch (language) {
                case ENGLISH:
                    System.out.println("Login failed. Incorrect password.");
                    break;
                case RUSSIAN:
                    System.out.println("Неудачная попытка входа. Неверный пароль.");
                    break;
                case KAZAKH:
                    System.out.println("Кіру сәтсіз болды. Қате пароль.");
                    break;
            }

            return;
        }
    }

    switch (language) {
        case ENGLISH:
            System.out.println("Login successful!");
            break;
        case RUSSIAN:
            System.out.println("Вход успешен!");
            break;
        case KAZAKH:
            System.out.println("Кіру сәтті аяқталды!");
            break;
    }

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
