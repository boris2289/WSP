package views;
import java.util.*;

import studyingProcess.ResearchPaper;
import users.Researcher;
import ENUMS.*;

class ResearcherTest {

    public static void main(String[] args) {
        // Run all test cases
        testAddAndRemoveResearchPapers();
        testCalculateHIndex();
        testPrintPapersSortedByCitations();
        testPrintPapersSortedByDate();
        testPrintPapersSortedByPages();
    }

    // Test case for adding and removing research papers
    public static void testAddAndRemoveResearchPapers() {
        System.out.println("Running testAddAndRemoveResearchPapers...");

        Researcher researcher = new Researcher("1", "John Doe", "john@example.com", "1234567890", "password", Language.ENGLISH);
        ResearchPaper paper1 = new ResearchPaper("Paper 1", new ArrayList<>(), "Journal 1", "1234", new Date(2023, 5, 1), 10, 15);
        ResearchPaper paper2 = new ResearchPaper("Paper 2", new ArrayList<>(), "Journal 2", "5678", new Date(2022, 8, 3), 20, 10);

        researcher.addResearchPaper(paper1);
        researcher.addResearchPaper(paper2);

        System.out.println("Papers after adding:");
        researcher.printPapers((a, b) -> 0); // Just print as is for now

        researcher.removeResearchPaper(paper1);
        System.out.println("Papers after removing Paper 1:");
        researcher.printPapers((a, b) -> 0); // Print remaining papers

        System.out.println();
    }

    // Test case for calculating the h-index
    public static void testCalculateHIndex() {
        System.out.println("Running testCalculateHIndex...");

        Researcher researcher = new Researcher("1", "John Doe", "john@example.com", "1234567890", "password", Language.ENGLISH);
        ResearchPaper paper1 = new ResearchPaper("Paper 1", new ArrayList<>(), "Journal 1", "1234", new Date(2023, 5, 1), 10, 15);
        ResearchPaper paper2 = new ResearchPaper("Paper 2", new ArrayList<>(), "Journal 2", "5678", new Date(2022, 8, 3), 20, 10);
        ResearchPaper paper3 = new ResearchPaper("Paper 3", new ArrayList<>(), "Journal 3", "9876", new Date(2021, 10, 5), 5, 20);

        researcher.addResearchPaper(paper1);
        researcher.addResearchPaper(paper2);
        researcher.addResearchPaper(paper3);

        int hIndex = researcher.calculateHIndex();
        System.out.println("Calculated h-index: " + hIndex); // Expected: 2 (papers with citations 20 and 10)

        System.out.println();
    }

    // Test case for printing papers sorted by citations
    public static void testPrintPapersSortedByCitations() {
        System.out.println("Running testPrintPapersSortedByCitations...");

        Researcher researcher = new Researcher("1", "John Doe", "john@example.com", "1234567890", "password", Language.ENGLISH);
        ResearchPaper paper1 = new ResearchPaper("Paper 1", new ArrayList<>(), "Journal 1", "1234", new Date(2023, 5, 1), 10, 15);
        ResearchPaper paper2 = new ResearchPaper("Paper 2", new ArrayList<>(), "Journal 2", "5678", new Date(2022, 8, 3), 20, 10);
        ResearchPaper paper3 = new ResearchPaper("Paper 3", new ArrayList<>(), "Journal 3", "9876", new Date(2021, 10, 5), 5, 20);

        researcher.addResearchPaper(paper1);
        researcher.addResearchPaper(paper2);
        researcher.addResearchPaper(paper3);

        System.out.println("Papers sorted by citations (descending):");
        researcher.printPapers((a, b) -> Integer.compare(b.getCitations(), a.getCitations())); // Sorting by citations
        System.out.println();
    }

    // Test case for printing papers sorted by publication date
    public static void testPrintPapersSortedByDate() {
        System.out.println("Running testPrintPapersSortedByDate...");

        Researcher researcher = new Researcher("1", "John Doe", "john@example.com", "1234567890", "password", Language.ENGLISH);
        ResearchPaper paper1 = new ResearchPaper("Paper 1", new ArrayList<>(), "Journal 1", "1234", new Date(2023, 5, 1), 10, 15);
        ResearchPaper paper2 = new ResearchPaper("Paper 2", new ArrayList<>(), "Journal 2", "5678", new Date(2022, 8, 3), 20, 10);
        ResearchPaper paper3 = new ResearchPaper("Paper 3", new ArrayList<>(), "Journal 3", "9876", new Date(2021, 10, 5), 5, 20);

        researcher.addResearchPaper(paper1);
        researcher.addResearchPaper(paper2);
        researcher.addResearchPaper(paper3);

        System.out.println("Papers sorted by publication date:");
        researcher.printPapers((a, b) -> b.getPublicationDate().compareTo(a.getPublicationDate())); // Sorting by publication date
        System.out.println();
    }

    // Test case for printing papers sorted by pages (article length)
    public static void testPrintPapersSortedByPages() {
        System.out.println("Running testPrintPapersSortedByPages...");

        Researcher researcher = new Researcher("1", "John Doe", "john@example.com", "1234567890", "password", Language.ENGLISH);
        ResearchPaper paper1 = new ResearchPaper("Paper 1", new ArrayList<>(), "Journal 1", "1234", new Date(2023, 5, 1), 10, 15);
        ResearchPaper paper2 = new ResearchPaper("Paper 2", new ArrayList<>(), "Journal 2", "5678", new Date(2022, 8, 3), 20, 10);
        ResearchPaper paper3 = new ResearchPaper("Paper 3", new ArrayList<>(), "Journal 3", "9876", new Date(2021, 10, 5), 5, 20);

        researcher.addResearchPaper(paper1);
        researcher.addResearchPaper(paper2);
        researcher.addResearchPaper(paper3);

        System.out.println("Papers sorted by article length (pages):");
        researcher.printPapers((a, b) -> Integer.compare(b.getPages(), a.getPages())); // Sorting by pages
        System.out.println();
    }
}
