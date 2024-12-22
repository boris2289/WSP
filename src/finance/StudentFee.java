package finance;

import users.Student;
import ENUMS.FeeStatus;

import java.util.Date;

public class StudentFee {
    private Student student;
    private double amount;
    private FeeStatus status;
    private Date dueDate;

    public StudentFee(Student student, double amount, FeeStatus status, Date dueDate) {
        this.student = student;
        this.amount = amount;
        this.status = status;
        this.dueDate = dueDate;
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

    public FeeStatus getStatus() {
        return status;
    }

    public void setStatus(FeeStatus status) {
        this.status = status;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
