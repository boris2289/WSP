package views;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import comparators.ResearchPaperComparators;
import organizations.ResearchHolders;
import studyingProcess.ResearchPaper;
import users.Researcher;
import ENUMS.*;
import users.Teacher;
import users.User;
import java.util.Date;

class ComparatorTest {

        public static void main(String[] args) {
            ResearchHolders ResearchHolders = new ResearchHolders();

            // Create researchers and add them to the ResearchHolders
            Researcher researcher1 = new Researcher("1", "Alice", "alice@example.com", "12345", "password", Language.ENGLISH);
            Researcher researcher2 = new Researcher("2", "Bob", "bob@example.com", "67890", "password", Language.ENGLISH);

            ResearchHolders.addResearcher(researcher1);
            ResearchHolders.addResearcher(researcher2);

            LocalDate localDate = LocalDate.now();
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            User author1 = new Teacher("Adil@gmail.com", "Adil", "Adil@gmail.com", "75487845", "1234", Language.RUSSIAN, "math", 23, 434, date);
            User author2 = new Teacher("Boris@gmail.com", "Boris", "Boris@gmail.com", "75487845", "1234", Language.RUSSIAN, "math", 23, 434, date);
            User author3 = new Teacher("Assanali@gmail.com", "Assanali", "Assanali@gmail.com", "75487845", "1234", Language.RUSSIAN, "math", 23, 434, date);

            List<User> li = new ArrayList<>();
            li.add(author1);
            li.add(author2);
            li.add(author3);
            // Add some research papers to each researcher
            researcher1.addResearchPaper(new ResearchPaper("Paper A", li, "Journal A", "10.1234/abc", new Date(), 15, 10));
            researcher2.addResearchPaper(new ResearchPaper("Paper B", li, "Journal B", "10.5678/xyz", new Date(), 5, 20));

            // Print all research papers sorted by citations
            System.out.println("Research papers sorted by citations:");
            ResearchHolders.printAllResearchPapers(ResearchPaperComparators.byCitations);

            // Print all research papers sorted by date published
            System.out.println("Research papers sorted by date published:");
            ResearchHolders.printAllResearchPapers(ResearchPaperComparators.byDatePublished);

            // Print all research papers sorted by article length (pages)
            System.out.println("Research papers sorted by pages:");
            ResearchHolders.printAllResearchPapers(ResearchPaperComparators.byPages);
        }
    }
