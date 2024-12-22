package users;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import ENUMS.*;
import abstractt.User;
import exceptions.InvalidParticipantException;
import studyingProcess.*;

import java.util.*;

public class Researcher extends User {
    private List<ResearchPaper> researchPapers;
    private List<ResearchProject> researchProjects;
    private int hIndex;

    public Researcher(String userId, String name, String email, String phoneNumber, String password, Language preferredLanguage) {
        super(userId, name, email, phoneNumber, password, Role.RESEARCHER, preferredLanguage);
        this.researchPapers = new ArrayList<>();
        this.researchProjects = new ArrayList<>();
        this.hIndex = 0;
    }

    // Method to add a research paper
    public void addResearchPaper(ResearchPaper paper) {
        researchPapers.add(paper);
    }

    // Method to remove a research paper
    public void removeResearchPaper(ResearchPaper paper) {
        researchPapers.remove(paper);
    }

    // Method to calculate h-index
    public int calculateHIndex() {
        List<Integer> citations = new ArrayList<>();
        for (ResearchPaper paper : researchPapers) {
            citations.add(paper.getCitations());
        }

        // Sort citations in descending order
        citations.sort((a, b) -> Integer.compare(b, a));

        // Calculate h-index
        int hIndex = 0;
        for (int i = 0; i < citations.size(); i++) {
            if (citations.get(i) >= i + 1) {
                hIndex = i + 1;
            } else {
                break;
            }
        }
        this.hIndex = hIndex;
        return hIndex;
    }

    // Method to print papers with a given comparator
    public void printPapers(Comparator<ResearchPaper> comparator) {
        researchPapers.sort(comparator);
        for (ResearchPaper paper : researchPapers) {
            System.out.println(paper);
        }
    }

    // Method to join a research project
    public void joinResearchProject(ResearchProject project) throws InvalidParticipantException {
        researchProjects.add(project);
        project.addParticipant(this);
    }

    // Getter for hIndex
    public int getHIndex() {
        return hIndex;
    }

    public Collection<? extends ResearchPaper> getResearchPapers() {
        return researchPapers;
    }

    // Additional getter and setter methods, toString, etc.
}

