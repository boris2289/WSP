package Demo;

import ENUMS.Language;
import abstractt.User;
import forResearcher.ResearchPaper;
import users.Researcher;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ResearchJournalDemo {
    public static void main(String[] args) {
        // Create Researcher authors
        Researcher author1 = new Researcher("R001", "Alice Johnson", "alice.johnson@example.com", "122", "1", Language.ENGLISH);
        Researcher author2 = new Researcher("R002", "Alice Johnson", "alice.johnson@example.com","122", "1", Language.ENGLISH);

        // Create a list of authors
        List<User> authors = new ArrayList<>();
        authors.add(author1);
        authors.add(author2);

        // Create a research paper
        Date publicationDate = new Date(); // Current date
        ResearchPaper paper = new ResearchPaper(
                "New Discoveries in AI",       // title
                authors,                      // authors
                "Journal of Advanced Research", // journal
                "10.1234/ja123456",           // DOI (example)
                publicationDate,              // publication date
                150,                          // citations
                12                            // pages
        );

        // Print out some paper details
        System.out.println("Paper Title: " + paper.getTitle());
        System.out.println("Authors: ");
        for (User author : paper.getAuthors()) {
            System.out.println("- " + author.getName());
        }
        System.out.println("Journal: " + paper.getJournal());
        System.out.println("DOI: " + paper.getDoi());
        System.out.println("Published on: " + paper.getPublicationDate());
        System.out.println("Citations: " + paper.getCitations());
        System.out.println("Pages: " + paper.getPages());
    }
}
