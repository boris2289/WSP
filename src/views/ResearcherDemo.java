package views;

import studyingProcess.ResearchPaper;
import users.Manager;
import users.Researcher;
import studyingProcess.ResearchProject;
import exceptions.InvalidParticipantException;
import abstractt.User;
import ENUMS.Language;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ResearcherDemo {
    public static void main(String[] args) {
        try {
            // Создание исследователя
            Researcher researcher = new Researcher(
                    "001",
                    "John Doe",
                    "john.doe@example.com",
                    "123456789",
                    "password",
                    Language.ENGLISH
            );

            // Создание публикаций
            ResearchPaper paper1 = new ResearchPaper(
                    "AI in Medicine",
                    List.of(researcher),
                    "Journal of AI",
                    "10.1234/ai.medicine",
                    new Date(2020 - 1900, 1, 1),
                    100,
                    15
            );

            ResearchPaper paper2 = new ResearchPaper(
                    "Blockchain in Healthcare",
                    List.of(researcher),
                    "Blockchain Journal",
                    "10.1234/blockchain.healthcare",
                    new Date(2022 - 1900, 6, 15),
                    50,
                    20
            );

            ResearchPaper paper3 = new ResearchPaper(
                    "Data Science Trends",
                    List.of(researcher),
                    "Data Journal",
                    "10.1234/data.trends",
                    new Date(2019 - 1900, 11, 10),
                    200,
                    25
            );

            // Добавление публикаций исследователю
            researcher.addResearchPaper(paper1);
            researcher.addResearchPaper(paper2);
            researcher.addResearchPaper(paper3);

            // Печать публикаций
            System.out.println("Все публикации:");
            researcher.getResearchPapers().forEach(System.out::println);

            // Сортировка публикаций по дате
            System.out.println("\nПубликации, отсортированные по дате:");
            researcher.printPapers(Comparator.comparing(ResearchPaper::getPublicationDate));

            // Сортировка публикаций по цитированию
            System.out.println("\nПубликации, отсортированные по цитированию:");
            researcher.printPapers(Comparator.comparing(ResearchPaper::getCitations).reversed());

            // Расчет h-индекса
            System.out.println("\nH-индекс исследователя: " + researcher.calculateHIndex());

            // Работа с проектами
            ResearchProject project = new ResearchProject("AI in Healthcare");
            project.addParticipant(researcher);

            System.out.println("\nУчастники проекта:");
            project.getParticipants().forEach(System.out::println);

            // Попытка добавить некорректного участника
            User nonResearcher = new Manager(
                    "002",
                    "Jane Doe",
                    "jane.doe@example.com",
                    "987654321",
                    "password"
            );

            try {
                project.addParticipant(nonResearcher);
            } catch (InvalidParticipantException e) {
                System.out.println("\nОшибка: " + e.getMessage());
            }

            // Демонстрация форматов цитирования
            System.out.println("\nФормат цитирования (Plain Text):");
            System.out.println(paper1.getCitation(ENUMS.FormatType.PLAIN_TEXT));

            System.out.println("\nФормат цитирования (Bibtex):");
            System.out.println(paper1.getCitation(ENUMS.FormatType.BIBTEX));
        } catch (Exception e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
        }
    }
}
