package finance;

import users.Student;
import ENUMS.ScholarshipType;

public class Scholarship {
    private Student student;
    private double amount;
    private ScholarshipType scholarshipType;
    private boolean isSent;

    public Scholarship(Student student, double amount, ScholarshipType scholarshipType, boolean isSent) {
        this.student = student;
        this.amount = amount;
        this.scholarshipType = scholarshipType;
        this.isSent = isSent;
    }
    public Scholarship(Student student, double amount, ScholarshipType scholarshipType) {
        this.student = student;
        this.amount = amount;
        this.scholarshipType = scholarshipType;
        this.isSent = false;
    }

    // Getters and setters
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public ScholarshipType getScholarshipType() {
        return scholarshipType;
    }

    public void setScholarshipType(ScholarshipType scholarshipType) {
        this.scholarshipType = scholarshipType;
    }

    public boolean isSent() {
        return isSent;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }
}
