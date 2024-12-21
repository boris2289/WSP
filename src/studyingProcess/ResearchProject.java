package studyingProcess;
import users.*;

import java.util.ArrayList;
import java.util.List;

import java.util.List;
import java.util.ArrayList;

public class ResearchProject {
    private String topic;
    private List<ResearchPaper> publishedPapers;
    private List<Researcher> participants;

    public ResearchProject(String topic) {
        this.topic = topic;
        this.publishedPapers = new ArrayList<>();
        this.participants = new ArrayList<>();
    }

    public void addParticipant(Researcher participant) {
        participants.add(participant);
    }

    public void removeParticipant(Researcher participant) {
        participants.remove(participant);
    }

    public void addResearchPaper(ResearchPaper paper) {
        publishedPapers.add(paper);
    }

    public void removeResearchPaper(ResearchPaper paper) {
        publishedPapers.remove(paper);
    }

    // Getter methods for topic, published papers, etc.
}

