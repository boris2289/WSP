package notifications;
import ENUMS.Urgency;
import users.User;

public class Complaint {
    private Urgency urgency;
    private Message message;

    public Complaint(Urgency urgency, Message message) {
        this.urgency = urgency;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Complaint{" +
                ", urgency=" + urgency +
                ", message=" + message +
                '}';
    }
}