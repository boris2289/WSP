package views;

import ENUMS.Language;
import users.Teacher;
import users.Student;
import managers.CourseRegistrationManager;

import java.util.*;

public class CourseManaging {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CourseRegistrationManager manager = new CourseRegistrationManager("123", "Boris", "boris@gmail", "454523", "12345");


        System.out.println("Choose your preffered language");
        System.out.println("1. English");
        System.out.println("2. Russian");
        System.out.println("3. Kazakh");
        int choiceLang = scanner.nextInt();
        scanner.nextLine();
        Language language = null;
        switch (choiceLang) {
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
                System.out.println("Remove or add course?");
                System.out.println("1. Add");
                System.out.println("2. Remove");
                System.out.println("3. View information");
                break;
            case RUSSIAN:
                System.out.println("Удалить или добавить курс?");
                System.out.println("1. Добавить");
                System.out.println("2. Удалить");
                System.out.println("3. Просмотреть информацию");
                break;
            case KAZAKH:
                System.out.println("Курсты жою немесе қосу?");
                System.out.println("1. Қосу");
                System.out.println("2. Жою");
                System.out.println("3. Ақпаратты көру");
                break;
        }


        int selected = Integer.parseInt(scanner.nextLine());

        switch (selected) {
            case 1:
                toAdd(scanner, manager, language);
                break;
            case 2:
                toRemove(scanner, manager, language);
                break;
            case 3:
                manager.printAllInfo();
        }
    }


    public static void toAdd(Scanner scanner, CourseRegistrationManager manager, Language language) {
        HashMap<String, Teacher> teachers = manager.getTeachers();
        HashMap<String, Student> students = manager.getStudents();

        switch (language) {
            case ENGLISH:
                System.out.println("Are you a teacher or a student?");
                System.out.println("1. Teacher");
                System.out.println("2. Student");
                System.out.print("Enter your choice: ");
                break;
            case RUSSIAN:
                System.out.println("Вы учитель или студент?");
                System.out.println("1. Учитель");
                System.out.println("2. Студент");
                System.out.print("Введите ваш выбор: ");
                break;
            case KAZAKH:
                System.out.println("Сіз мұғалімсіз бе, әлде студентсіз бе?");
                System.out.println("1. Мұғалім");
                System.out.println("2. Студент");
                System.out.print("Таңдауыңызды енгізіңіз: ");
                break;
        }


        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                manager.handleTeacherActions(scanner, teachers, language);
                break;
            case 2:
                manager.handleStudentActions(scanner, students, language);
                break;
            default:
                System.out.println("Invalid choice. Please restart the application.");
        }
    }

    public static void toRemove(Scanner scanner, CourseRegistrationManager manager, Language language) {
        manager.removeCourse(scanner, language);
    }
}
