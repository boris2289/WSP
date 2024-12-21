package organizations;

import notifications.*;
import users.*;
import ENUMS.*;
import java.io.*;
import java.util.Date;
import java.util.HashMap;

public class DeansOffice implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String COMPLAINTS = "complaints.txt";
    private HashMap<String, HashMap<String, Complaint>> complaints;
    private Teacher teacher;
    private Student student;

    public DeansOffice() {
        this.complaints = new HashMap<>();
    }

    // Method to file a complaint from a teacher against a student
    public void setComplaint(User sender, User sendee, Urgency urgency, String messageContent) {
        Message message = new Message(messageContent, sender, sendee, new Date());

        String senderId = sender.getUserId();
        String sendeeId = sendee.getUserId();

        Complaint complaint = new Complaint(urgency, message);
        if (complaints.containsKey(senderId)) {
            complaints.get(senderId).put(sendeeId, complaint);
        } else {
            HashMap<String, Complaint> sendeeComplaints = new HashMap<>();
            sendeeComplaints.put(sendeeId, complaint);
            complaints.put(senderId, sendeeComplaints);
        }
        serializeComplaints(COMPLAINTS)
        System.out.println("Complaint filed: " + complaint);
    }

    // Serialize complaints to a file
    public void serializeComplaints(String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(complaints);
            System.out.println("Complaints serialized to file: " + filePath);
        } catch (IOException e) {
            System.err.println("Error serializing complaints: " + e.getMessage());
        }
    }

    // Deserialize complaints from a file
    @SuppressWarnings("unchecked")
    public void deserializeComplaints(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            complaints = (HashMap<String, HashMap<String, Complaint>>) ois.readObject();
            System.out.println("Complaints deserialized from file: " + filePath);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error deserializing complaints: " + e.getMessage());
        }
    }

    public HashMap<String, HashMap<String, Complaint>> getComplaints() {
        return complaints;
    }
}
