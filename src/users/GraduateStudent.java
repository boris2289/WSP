package users;


import ENUMS.Language;
import forResearcher.ResearchPaper;
import ENUMS.DegreeType;
import java.util.ArrayList;
import java.util.List;

public class GraduateStudent extends Student {
    private DegreeType degreeType; // Master or PhD
    private List<ResearchPaper> researchPapers; // List of research papers
    private Researcher researchSupervisor; // Assigned research supervisor

    // Constructor
    public GraduateStudent(String id, String name, String email, String phone, String password, DegreeType degreeType, Language preferredLanguage, String major, String minor, double GPA) {
        super(id, name, email, phone, password, preferredLanguage, major, minor, GPA);
        this.degreeType = degreeType;
        this.researchPapers = new ArrayList<>();
    }

    // Submit a research paper
    public void submitResearchPaper(ResearchPaper paper) {
        researchPapers.add(paper);
        System.out.println("Research paper titled '" + paper.getTitle() + "' submitted.");
    }

    // Add a research supervisor
    public void addResearchSupervisor(Researcher researchSupervisor) {
        this.researchSupervisor = researchSupervisor;
        System.out.println("Research supervisor " + researchSupervisor.getName() + " assigned.");
    }

    // View current research supervisor
    public Researcher viewResearchSupervisor() {
        if (researchSupervisor == null) {
            System.out.println("No research supervisor assigned.");
            return null;
        }
        return researchSupervisor;
    }

    // Getters and setters
    public DegreeType getDegreeType() {
        return degreeType;
    }

    public void setDegreeType(DegreeType degreeType) {
        this.degreeType = degreeType;
    }

    public List<ResearchPaper> getResearchPapers() {
        return researchPapers;
    }

    public void setResearchPapers(List<ResearchPaper> researchPapers) {
        this.researchPapers = researchPapers;
    }
}

