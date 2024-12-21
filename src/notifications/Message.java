package notifications;
import users.User;
import java.util.Date;

public class Message {
    private String content;
    private User sender;
    private User receiver;
    private Date sentAt;

    public Message(String content, User sender, User receiver, Date sentAt) {
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.sentAt = sentAt;
    }

    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", sentAt=" + sentAt +
                '}';
    }
}