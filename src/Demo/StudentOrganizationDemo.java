package Demo;

import ENUMS.Language;
import abstractt.User;
import organizations.StudentOrganization;
import users.Student;

public class StudentOrganizationDemo {
    public static void main(String[] args) {
        // Create students
        Student student1 = new Student("ST001", "Alice Johnson", "alice.johnson@example.com", "53453","hellop", Language.KAZAKH, "math", "ads", 3.8);
        Student student2 = new Student("ST002", "Bob Smith", "bob.smith@example.com", "45435","hellop", Language.KAZAKH, "math", "ads",  3.6);
        Student student3 = new Student("ST003", "Charlie Brown", "charlie.brown@example.com", "34343","hellop", Language.KAZAKH, "math", "ads", 3.9);

        // Create a user (could be a head of the organization)
        User head = new Student("ST003", "Charlie Brown", "charlie.brown@example.com", "34343","hellop", Language.KAZAKH, "math", "ads", 3.9);

        // Create an organization and assign a head
        StudentOrganization org = new StudentOrganization("Tech Enthusiasts Club", head);

        // Add members to the organization
        org.addMember(student1);
        org.addMember(student2);

        // Display the members
        org.displayMembers();

        // Remove a member
        org.removeMember(student2);
        org.displayMembers();

        // Try to remove a non-existing member
        org.removeMember(student3);
    }
}