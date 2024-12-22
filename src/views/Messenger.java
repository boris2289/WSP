package views;
import ENUMS.Urgency;
import organizations.DeansOffice;
import organizations.NewsOffice;
import serialization.Loader;
import users.Student;
import users.Teacher;
import abstractt.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Messenger {
    private static final String NOTIFICATIONS = "notifications.txt";
    static HashMap<String, Teacher> teachers;
    static HashMap<String, Student> students;

    public static void main(String[] args) {
        Loader loader = new Loader();
        NewsOffice newsOffice = new NewsOffice();
        DeansOffice office = new DeansOffice();
        teachers = Loader.loadTeachersFromFile();
        students = Loader.loadStudentsFromFile();

        System.out.println("Choose option: ");
        System.out.println("1. Send complaint");
        System.out.println("2. View complains");
        System.out.println("3. Post news");
        System.out.println("4. View news");
        System.out.println("5. Leave comment");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                sendComplaint(scanner, office);
                break;
            case 2:
                office.deserializeComplaints("complaints.txt");
                break;
            case 3:
                publishNews(scanner, newsOffice);
                break;
            case 4:
                newsOffice.deserializeNews("news.txt");
                break;
            default:
                System.out.println(" ");
        }

    }

    public static void publishNews(Scanner scanner, NewsOffice newsOffice) {
        User publisher = null;

        int choice = dialogueWindow(scanner);
        switch (choice) {
            case 1:
                publisher = chooseTeacher(scanner, "Who are you?");
                break;
            case 2:
                publisher = chooseStudent(scanner, "Who are you?");
                break;
            default:
                System.out.println("Invalid choice, exiting.");
                return;
        }

        if (publisher == null) {
            System.out.println("Publisher not found.");
            return;
        }

        System.out.println("What would you like to publish?");
        String option = scanner.nextLine();

        newsOffice.createNewsPost(publisher, option);
    }

    public static void sendComplaint(Scanner scanner, DeansOffice office) {

        System.out.println("Preferred Urgency:");
        System.out.println("1. LOW");
        System.out.println("2. MEDIUM");
        System.out.println("3. HIGH");

        Urgency urg;
        System.out.print("Enter the number of your choice: ");
        int urgIndex = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера после nextInt()

        urg = switch (urgIndex) {
            case 1 -> Urgency.LOW;
            case 3 -> Urgency.HIGH;
            default -> Urgency.MEDIUM;
        };
        int choice = dialogueWindow(scanner);
        System.out.println("Enter your message: ");
        String message = scanner.nextLine();

        switch (choice) {
            case 1:
                User complaintFromTeacher = chooseTeacher(scanner, "Who are you ?");
                User complaintToStudent = chooseStudent(scanner, "Send complaint to ?");
                office.setComplaint(complaintFromTeacher, complaintToStudent, urg, message);
                break;
            case 2:
                User complaintFromStudent = chooseStudent(scanner, "Who are you ?");
                User complaintToTeacher = chooseTeacher(scanner, "Send complaint to ?");
                office.setComplaint(complaintFromStudent, complaintToTeacher, urg, message);
                break;
            default:
                System.out.println(" ");
        }
    }

    public static int dialogueWindow(Scanner scanner) {
        System.out.println("Are you a teacher or a student?");
        System.out.println("1. Teacher");
        System.out.println("2. Student");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }


    public static Teacher chooseTeacher(Scanner scanner, String text) {
        List<Teacher> t = new ArrayList<>(teachers.values()); // Преобразуем коллекцию в список
        System.out.println(text);
        for (int i = 0; i < t.size(); i++) {
            System.out.println(i + ". " + t.get(i).getName());
        }
        int teacherIndex = Integer.parseInt(scanner.nextLine());
        return t.get(teacherIndex);
    }

    public static Student chooseStudent(Scanner scanner, String text) {
        List<Student> t = new ArrayList<>(students.values()); // Преобразуем коллекцию в список
        System.out.println(text);
        for (int i = 0; i < t.size(); i++) {
            System.out.println(i + ". " + t.get(i).getName());
        }
        int studentIndex = Integer.parseInt(scanner.nextLine());
        return t.get(studentIndex);
    }

}
