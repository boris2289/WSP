package organizations;

import notifications.*;
import users.*;
import ENUMS.*;
import java.io.*;
import java.util.*;

public class DeansOffice implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String COMPLAINTS = "complaints.txt";
    private HashMap<String, HashMap<String, List<Complaint>>> complaints;

    public DeansOffice() {
        this.complaints = new HashMap<>();
    }

    // Method to file a complaint from a teacher against a student
    // Метод для добавления жалобы
    public void setComplaint(User sender, User sendee, Urgency urgency, String messageContent) {
        Message message = new Message(messageContent, sender, sendee, new Date());

        String senderId = sender.getUserId();
        String sendeeId = sendee.getUserId();

        Complaint complaint = new Complaint(urgency, message);

        // Десериализуем существующие жалобы
        deserializeComplaints(COMPLAINTS);

        // Если у отправителя уже есть жалобы, добавляем новую жалобу
        if (complaints.containsKey(senderId)) {
            HashMap<String, List<Complaint>> senderComplaints = complaints.get(senderId);
            if (senderComplaints.containsKey(sendeeId)) {
                senderComplaints.get(sendeeId).add(complaint);  // Добавляем жалобу в список
            } else {
                List<Complaint> newComplaintsList = new ArrayList<>();
                newComplaintsList.add(complaint);
                senderComplaints.put(sendeeId, newComplaintsList);
            }
        } else {
            // Если у отправителя нет жалоб, создаем новый список для отправителя
            HashMap<String, List<Complaint>> newSenderComplaints = new HashMap<>();
            List<Complaint> newComplaintsList = new ArrayList<>();
            newComplaintsList.add(complaint);
            newSenderComplaints.put(sendeeId, newComplaintsList);
            complaints.put(senderId, newSenderComplaints);
        }

        // Сериализуем жалобы обратно в файл
        serializeComplaints(COMPLAINTS);
        System.out.println("Complaint filed.");
    }

    // Сериализация жалоб в файл
    public void serializeComplaints(String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(complaints);
            System.out.println("Complaints serialized to file: " + filePath);
        } catch (IOException e) {
            System.err.println("Error serializing complaints: " + e.getMessage());
        }
    }

    // Десериализация жалоб из файла
    @SuppressWarnings("unchecked")
    public void deserializeComplaints(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            complaints = (HashMap<String, HashMap<String, List<Complaint>>>) ois.readObject();

            // Iterate through the complaints structure and print its contents
            for (Map.Entry<String, HashMap<String, List<Complaint>>> entry : complaints.entrySet()) {
                String key1 = entry.getKey();
                HashMap<String, List<Complaint>> innerMap = entry.getValue();

                // Iterate through the inner map
                for (Map.Entry<String, List<Complaint>> innerEntry : innerMap.entrySet()) {
                    String key2 = innerEntry.getKey();
                    List<Complaint> complaintList = innerEntry.getValue();

                    // Print the key and each complaint in the list
                    System.out.println("Complaint from: " + key1 + ", Complaint to: " + key2);
                    for (Complaint complaint : complaintList) {
                        System.out.println("\tComplaint: " + complaint.getMessage().getContent());
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            // In case of deserialization error, initialize an empty structure
            complaints = new HashMap<>();
            System.err.println("Error deserializing complaints: " + e.getMessage());
        }
    }



    public HashMap<String, HashMap<String, List<Complaint>>> getComplaints() {
        return complaints;
    }
}
