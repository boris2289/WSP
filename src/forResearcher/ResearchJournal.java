package forResearcher;

import abstractt.User;
import users.Researcher;

import java.util.ArrayList;
import java.util.List;

public class ResearchJournal {
    private String journalName;
    private List<User> subscribers;
    private List<ResearchPaper> publishedPapers;

    // Constructor
    public ResearchJournal(String journalName) {
        this.journalName = journalName;
        this.subscribers = new ArrayList<>();
        this.publishedPapers = new ArrayList<>();
    }

    // Method to add a subscriber
    public void addSubscriber(User user) {
        if (!subscribers.contains(user)) {
            subscribers.add(user);
        }
    }

    // Method to remove a subscriber
    public void removeSubscriber(User user) {
        subscribers.remove(user);
    }

    // Method to publish a paper (only Researchers can publish)
    public void publishPaper(ResearchPaper paper, User user) {
        if (user instanceof Researcher) {
            publishedPapers.add(paper);
            notifySubscribers(paper);
        } else {
            System.out.println("Only researchers can publish papers.");
        }
    }

    // Method to notify subscribers about a new paper
    private void notifySubscribers(ResearchPaper paper) {
        for (User subscriber : subscribers) {
            // Notify the subscriber (you can add more logic to actually notify, e.g., via email or log)
            System.out.println("Notifying subscriber " + subscriber.getName() + " about the new paper: " + paper.getTitle());
        }
    }

    // Getters for the attributes (if needed for assertions or testing)
    public String getJournalName() {
        return journalName;
    }

    public List<User> getSubscribers() {
        return subscribers;
    }

    public List<ResearchPaper> getPublishedPapers() {
        return publishedPapers;
    }
}
