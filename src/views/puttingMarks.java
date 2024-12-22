package views;
import ENUMS.Language;
import managers.MarkJournal;

import java.util.Scanner;

public class puttingMarks {
    public static void main(String[] args) {

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
                System.out.println("Hello, there is an implementation of MarkJournal");
                break;
            case RUSSIAN:
                System.out.println("Привет, есть реализация MarkJournal");
                break;
            case KAZAKH:
                System.out.println("Сәлем, MarkJournal-дың жүзеге асырылуы бар");
                break;
        }

        MarkJournal journal = new MarkJournal();

        switch (language) {
            case ENGLISH:
                System.out.println("Choose what you want to do: ");
                System.out.println("1. Put marks");
                System.out.println("2. View all marks");
                System.out.println("3. Generate simple statistics");
                break;
            case RUSSIAN:
                System.out.println("Выберите, что вы хотите сделать: ");
                System.out.println("1. Поставить оценки");
                System.out.println("2. Посмотреть все оценки");
                System.out.println("3. Сгенерировать простую статистику");
                break;
            case KAZAKH:
                System.out.println("Не істегіңіз келетінін таңдаңыз: ");
                System.out.println("1. Бағаларды қою");
                System.out.println("2. Барлық бағаларды көру");
                System.out.println("3. Қарапайым статистика жасау");
                break;
        }


        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                journal.chooseCourse(scanner, language);
                break;
            case 2:
                journal.viewAllMarks(language);
                break;
            case 3:
                journal.generateSimpleReport(language);
        }

    }
}
