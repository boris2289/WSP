package views;
import managers.MarkJournal;

import java.util.Scanner;

public class puttingMarks {
    public static void main(String[] args) {
        System.out.println("Hello, there is an implementation of MarkJournal");
        MarkJournal journal = new MarkJournal();

        System.out.println("Choose what you want to do: ");
        System.out.println("1. Put marks");
        System.out.println("2. View all marks");
        System.out.println("3. Generate simple statistics");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                journal.chooseCourse(scanner);
                break;
                case 2:
                    journal.viewAllMarks();
                    break;
                    case 3:
                        journal.generateSimpleReport();
        }

    }
}
