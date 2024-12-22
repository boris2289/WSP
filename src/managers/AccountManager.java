package managers;

import ENUMS.FeeStatus;
import ENUMS.Role;
import abstractt.Employee;
import users.Student;
import finance.StudentFee;
import finance.Scholarship;
import abstractt.Employee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountManager extends Employee {
    private List<StudentFee> studentFees;       // List of student fees
    private List<Scholarship> scholarships;    // List of scholarships

    // Constructor
    public AccountManager(String userId, String name, String email, String phoneNumber, String password, double salary, Date hireDate) {
        super(userId, name, email, phoneNumber, password, salary, hireDate, Role.ACCOUNT_MANAGER);
        this.studentFees = new ArrayList<>();
        this.scholarships = new ArrayList<>();
    }

    // Accept payment of a student fee
    public void acceptPayingFee(StudentFee fee) {
        if (!studentFees.contains(fee)) {
            System.out.println("Fee not found in the list.");
            return;
        }
        fee.setStatus(FeeStatus.PAID);
        System.out.println("Fee for student marked as paid.");
    }

    // Block a student's Workspace Portal (WSP)
    public void blockWSP(Student student) {
        student.setBlocked();
        System.out.println("Workspace Portal (WSP) for student " + student.getName() + " has been blocked.");
    }

    // Send a scholarship to a student
    public void sendScholarshipToStudent(Student student) {
        for (Scholarship scholarship : scholarships) {
            if (!scholarship.isSent() && scholarship.getStudent().equals(student)) {
                scholarship.setSent(true);
                System.out.println("Scholarship sent to student " + student.getName() + ".");
                return;
            }
        }
        System.out.println("No pending scholarships for student " + student.getName() + ".");
    }

    public List<StudentFee> viewUnpaidStudentFees(FeeStatus status) {
        List<StudentFee> unpaidFees = new ArrayList<>();
        for (StudentFee fee : studentFees) {
            if (fee.getStatus() == status) {
                unpaidFees.add(fee);
            }
        }
        return unpaidFees;
    }

    public List<Scholarship> viewUnsentScholarships() {
        List<Scholarship> unsentScholarships = new ArrayList<>();
        for (Scholarship scholarship : scholarships) {
            if (!scholarship.isSent()) {
                unsentScholarships.add(scholarship);
            }
        }
        return unsentScholarships;
    }

    // Add a student fee
    public void addStudentFee(StudentFee fee) {
        studentFees.add(fee);
        System.out.println("Student fee for " + fee.getStudent().getName() + " added.");
    }

    // Add a scholarship
    public void addScholarship(Scholarship scholarship) {
        scholarships.add(scholarship);
        System.out.println("Scholarship for " + scholarship.getStudent().getName() + " added.");
    }
}
