package Demo;

import finance.Scholarship;
import finance.StudentFee;
import managers.AccountManager;
import users.Student;
import ENUMS.FeeStatus;
import ENUMS.ScholarshipType;

import java.util.Date;

public class AccountManagerDemo {
    public static void main(String[] args) {
        // Step 1: Create an Account Manager
        AccountManager manager = new AccountManager(
                "AM001",
                "Emma Green",
                "emma.green@example.com",
                "555-0001",
                "securePassword",
                5000.0,
                new Date()
        );

        // Step 2: Create Students
        Student student1 = new Student(
                "ST001",
                "Alice Johnson",
                "alice.johnson@example.com",
                "555-1234",
                "alicePass123"
        );
        Student student2 = new Student(
                "ST002",
                "Bob Smith",
                "bob.smith@example.com",
                "555-5678",
                "bobPass456"
        );

        // Step 3: Add Student Fees
        StudentFee fee1 = new StudentFee(student1, 1000.0, FeeStatus.PENDING, new Date());
        StudentFee fee2 = new StudentFee(student2, 1500.0, FeeStatus.PAID, new Date());
        manager.addStudentFee(fee1);
        manager.addStudentFee(fee2);

        // Step 4: Add Scholarships
        Scholarship scholarship1 = new Scholarship(student1, 500.0, ScholarshipType.MERIT_BASED, false);
        Scholarship scholarship2 = new Scholarship(student2, 300.0, ScholarshipType.NEED_BASED, false);
        manager.addScholarship(scholarship1);
        manager.addScholarship(scholarship2);

        // Step 5: Accept Fee Payment
        System.out.println("\nAccepting fee payment for Alice...");
        manager.acceptPayingFee(fee1);

        // Step 6: Block Workspace for Student
        System.out.println("\nBlocking workspace for Bob...");
        manager.blockWSP(student2);

        // Step 7: Send Scholarship to Student
        System.out.println("\nSending scholarship to Alice...");
        manager.sendScholarshipToStudent(student1);


        System.out.println("\nUnpaid student fees:");
        manager.viewUnpaidStudentFees(FeeStatus.PENDING)
                .forEach(fee -> System.out.println(fee.getStudent().getName()));

        System.out.println("\nUnsent scholarships:");
        manager.viewUnsentScholarships()
                .forEach(scholarship -> System.out.println(scholarship.getStudent().getName()));

    }
}
