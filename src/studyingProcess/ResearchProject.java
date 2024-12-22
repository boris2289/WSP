package studyingProcess;

import abstractt.User;
import users.*;
import exceptions.InvalidParticipantException; // Import the custom exception

import java.util.ArrayList;
import java.util.List;

public class ResearchProject {
    private String topic;
    private List<ResearchPaper> publishedPapers;
    private List<Researcher> participants;

    public ResearchProject(String topic) {
        this.topic = topic;
        this.publishedPapers = new ArrayList<>();
        this.participants = new ArrayList<>();
    }

    public void addParticipant(User participant) throws InvalidParticipantException {
        if (!(participant instanceof Researcher)) {
            throw new InvalidParticipantException("Only Researcher can join the research project.");
        }
        participants.add((Researcher) participant);  // Cast the participant to Researcher
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

    public List<Researcher> getParticipants() {
        return participants;
    }

    // Getter methods for topic, published papers, etc.
}
