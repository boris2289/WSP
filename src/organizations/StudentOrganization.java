package organizations;

import abstractt.User;
import users.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentOrganization {
    private String organization;
    private User head; // The head of the organization (could be any user, like a Student or a Faculty)
    private List<Student> members; // List of student members

    // Constructor
    public StudentOrganization(String organization, User head) {
        this.organization = organization;
        this.head = head;
        this.members = new ArrayList<>();
    }

    // Getter for organization name
    public String getOrganization() {
        return organization;
    }

    // Getter for the head of the organization
    public User getHead() {
        return head;
    }

    // Getter for members of the organization
    public List<Student> getMembers() {
        return members;
    }

    // Method to add a member to the organization
    public void addMember(Student member) {
        if (!members.contains(member)) {
            members.add(member);
            System.out.println(member.getName() + " has been added to the " + organization + " organization.");
        } else {
            System.out.println(member.getName() + " is already a member of the " + organization + " organization.");
        }
    }

    // Method to remove a member from the organization
    public void removeMember(Student member) {
        if (members.contains(member)) {
            members.remove(member);
            System.out.println(member.getName() + " has been removed from the " + organization + " organization.");
        } else {
            System.out.println(member.getName() + " is not a member of the " + organization + " organization.");
        }
    }

    // Display the members of the organization
    public void displayMembers() {
        System.out.println("Members of the " + organization + " organization:");
        for (Student member : members) {
            System.out.println(member.getName());
        }
    }
}
