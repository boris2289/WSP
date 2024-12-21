package notifications;
import ENUMS.Urgency;
import users.User;

import java.io.Serializable;

public class Complaint implements Serializable {
    private static final long serialVersionUID = 4736946618480307593L;
    private Urgency urgency;
    private Message message;

    public Complaint(Urgency urgency, Message message) {
        this.urgency = urgency;
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Complaint{" +
                ", urgency=" + urgency +
                ", message=" + message +
                '}';
    }
}