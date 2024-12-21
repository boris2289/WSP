package organizations;

import studyingProcess.ResearchPaper;
import users.Researcher;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class ResearchHolders {
    private List<Researcher> researchers;

    public ResearchHolders() {
        researchers = new ArrayList<>();
    }

    // Add researcher to the university
    public void addResearcher(Researcher researcher) {
        researchers.add(researcher);
    }

    // Print all research papers sorted by a specific comparator
    public void printAllResearchPapers(Comparator<ResearchPaper> comparator) {
        List<ResearchPaper> allPapers = new ArrayList<>();

        // Collect all papers from all researchers
        for (Researcher researcher : researchers) {
            allPapers.addAll(researcher.getResearchPapers());
        }

        // Sort the papers based on the given comparator
        allPapers.sort(comparator);

        // Print all sorted papers
        for (ResearchPaper paper : allPapers) {
            System.out.println(paper);
        }
    }
}
