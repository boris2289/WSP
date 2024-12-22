package views;
import ENUMS.Language;
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

        Scanner scanner = new Scanner(System.in);
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
                System.out.println("Choose option: ");
                System.out.println("1. Send complaint");
                System.out.println("2. View complains");
                System.out.println("3. Post news");
                System.out.println("4. View news");
                System.out.println("5. Leave comment");
                break;
            case RUSSIAN:
                System.out.println("Выберите опцию: ");
                System.out.println("1. Отправить жалобу");
                System.out.println("2. Просмотр жалоб");
                System.out.println("3. Разместить новость");
                System.out.println("4. Просмотр новостей");
                System.out.println("5. Оставить комментарий");
                break;
            case KAZAKH:
                System.out.println("Опция таңдаңыз: ");
                System.out.println("1. Шағым жіберу");
                System.out.println("2. Шағымдарды көру");
                System.out.println("3. Жаңалық жариялау");
                System.out.println("4. Жаңалықтарды көру");
                System.out.println("5. Пікір қалдыру");
                break;
        }


        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                sendComplaint(scanner, office, language);
                break;
            case 2:
                office.deserializeComplaints("complaints.txt");
                break;
            case 3:
                publishNews(scanner, newsOffice, language);
                break;
            case 4:
                newsOffice.deserializeNewsPosts("news.txt", language); // Use the correct method name
                break;
                case 5:
                    leaveComment(scanner, newsOffice, language);
                    break;

            default:
                System.out.println("Invalid option. Please try again.");
        }

    }

    public static void publishNews(Scanner scanner, NewsOffice newsOffice, Language language) {
        User publisher = null;

        int choice = dialogueWindow(scanner, language);
        switch (choice) {
            case 1:
                switch (language) {
                    case ENGLISH:
                        publisher = chooseTeacher(scanner, "Who are you?");
                        break;
                    case RUSSIAN:
                        publisher = chooseTeacher(scanner, "Кто вы?");
                        break;
                    case KAZAKH:
                        publisher = chooseTeacher(scanner, "Сіз кімсіз?");
                        break;
                }

                break;
            case 2:
                switch (language) {
                    case ENGLISH:
                        publisher = chooseStudent(scanner, "Who are you?");
                        break;
                    case RUSSIAN:
                        publisher = chooseStudent(scanner, "Кто вы?");
                        break;
                    case KAZAKH:
                        publisher = chooseStudent(scanner, "Сіз кімсіз?");
                        break;
                }

                break;
            default:
                System.out.println("Invalid choice, exiting.");
                return;
        }

        if (publisher == null) {
            System.out.println("Publisher not found.");
            return;
        }

        switch (language) {
            case ENGLISH:
                System.out.println("What would you like to publish?");
                break;
            case RUSSIAN:
                System.out.println("Что бы вы хотели опубликовать?");
                break;
            case KAZAKH:
                System.out.println("Не жариялағыңыз келеді?");
                break;
        }

        String option = scanner.nextLine();

        newsOffice.createNewsPost(publisher, option, language);
    }

    public static void leaveComment(Scanner scanner, NewsOffice newsOffice, Language language) {
        User publisher = null;

        int choice = dialogueWindow(scanner, language);
        switch (choice) {
            case 1:
                switch (language) {
                    case ENGLISH:
                        publisher = chooseTeacher(scanner, "Who are you?");
                        break;
                    case RUSSIAN:
                        publisher = chooseTeacher(scanner, "Кто вы?");
                        break;
                    case KAZAKH:
                        publisher = chooseTeacher(scanner, "Сіз кімсіз?");
                        break;
                }

                break;
            case 2:
                switch (language) {
                    case ENGLISH:
                        publisher = chooseStudent(scanner, "Who are you?");
                        break;
                    case RUSSIAN:
                        publisher = chooseStudent(scanner, "Кто вы?");
                        break;
                    case KAZAKH:
                        publisher = chooseStudent(scanner, "Сіз кімсіз?");
                        break;
                }

                break;
            default:
                System.out.println("Invalid choice, exiting.");
                return;
        }
        if (publisher == null) {
            System.out.println("Publisher not found.");
            return;
        }
        newsOffice.leaveComment(scanner, publisher, newsOffice, language);
    }

    public static void sendComplaint(Scanner scanner, DeansOffice office, Language language) {

        switch (language) {
            case ENGLISH:
                System.out.println("Preferred Urgency:");
                System.out.println("1. LOW");
                System.out.println("2. MEDIUM");
                System.out.println("3. HIGH");
                break;
            case RUSSIAN:
                System.out.println("Предпочитаемая срочность:");
                System.out.println("1. НИЗКАЯ");
                System.out.println("2. СРЕДНЯЯ");
                System.out.println("3. ВЫСОКАЯ");
                break;
            case KAZAKH:
                System.out.println("Қалаулы шұғылдық:");
                System.out.println("1. ТӨМЕН");
                System.out.println("2. ОРТАША");
                System.out.println("3. ЖОҒАРЫ");
                break;
        }


        Urgency urg;
        switch (language) {
            case ENGLISH:
                System.out.print("Enter the number of your choice: ");
                break;
            case RUSSIAN:
                System.out.print("Введите номер вашего выбора: ");
                break;
            case KAZAKH:
                System.out.print("Таңдауыңыздың нөмірін енгізіңіз: ");
                break;
        }

        int urgIndex = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера после nextInt()

        urg = switch (urgIndex) {
            case 1 -> Urgency.LOW;
            case 3 -> Urgency.HIGH;
            default -> Urgency.MEDIUM;
        };
        int choice = dialogueWindow(scanner, language);
        switch (language) {
            case ENGLISH:
                System.out.println("Enter your message: ");
                break;
            case RUSSIAN:
                System.out.println("Введите ваше сообщение: ");
                break;
            case KAZAKH:
                System.out.println("Хабарламаңызды енгізіңіз: ");
                break;
        }

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

    public static int dialogueWindow(Scanner scanner, Language language) {
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
                System.out.println("Сіз мұғалімсіз бе, әлде студент пе?");
                System.out.println("1. Мұғалім");
                System.out.println("2. Студент");
                System.out.print("Таңдауыңызды енгізіңіз: ");
                break;
        }


        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }


    public static Teacher chooseTeacher(Scanner scanner, String text) {
        List<Teacher> t = new ArrayList<>(teachers.values());
        System.out.println(text);
        for (int i = 0; i < t.size(); i++) {
            System.out.println(i + ". " + t.get(i).getName());
        }
        int teacherIndex = Integer.parseInt(scanner.nextLine());
        return t.get(teacherIndex);
    }

    public static Student chooseStudent(Scanner scanner, String text) {
        List<Student> t = new ArrayList<>(students.values());
        System.out.println(text);
        for (int i = 0; i < t.size(); i++) {
            System.out.println(i + ". " + t.get(i).getName());
        }
        int studentIndex = Integer.parseInt(scanner.nextLine());
        return t.get(studentIndex);
    }

}
