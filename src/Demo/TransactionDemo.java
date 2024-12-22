package Demo;

import ENUMS.TransactionType;
import abstractt.Employee;
import finance.Scholarship;
import finance.Transaction;
import organizations.FinanceOffice;
import users.Student;
import ENUMS.*;

import java.util.*;

public class TransactionDemo {

    public static void main(String[] args) {


        Employee employee1 = new Employee("EMP001", "John Doe", "john.doe@example.com", "555-0001", "password123", 3000.0, new Date(), Role.NOBODY);
        Employee employee2 = new Employee("EMP002", "Jane Smith", "jane.smith@example.com", "555-0002", "password456", 3500.0, new Date(), Role.NOBODY);

        Map<Employee, Float> employeeSalaries = new HashMap<>();
        employeeSalaries.put(employee1, (float) employee1.getSalary());
        employeeSalaries.put(employee2,(float) employee2.getSalary());



        Student student1 = new Student("ST001", "Alice Johnson", "alice.johnson@example.com","13234", "alicePass123");
        Student student2 = new Student("ST002", "Bob Smith", "bob.smith@example.com", "13234", "bobPass456");

        Scholarship scholarship1 = new Scholarship(student1, 300F,ScholarshipType.MERIT_BASED, false);
        Scholarship scholarship2 = new Scholarship(student2, 500F,ScholarshipType.NEED_BASED, false);

        List<Scholarship> scholarships = new ArrayList<>();
        scholarships.add(scholarship1);
        scholarships.add(scholarship2);

        Transaction transaction1 = new Transaction("T001", TransactionType.SCHOLARSHIP_AWARDED, 500.0f, new Date(), "Paid scholarship to student Alice.");
        Transaction transaction2 = new Transaction("T002", TransactionType.SALARY_PAID, 3000.0f, new Date(), "Paid salary to employee John.");
        Transaction transaction3 = new Transaction("T003", TransactionType.FUNDS_ADDED, 2000.0f, new Date(), "Added funds to budget.");
        Transaction transaction4 = new Transaction("T004", TransactionType.FUNDS_DEDUCTED, 1500.0f, new Date(), "Deducted funds for operational expenses.");

        List<Transaction> transactionHistory = new ArrayList<>();
        transactionHistory.add(transaction1);
        transactionHistory.add(transaction2);
        transactionHistory.add(transaction3);
        transactionHistory.add(transaction4);

        // Step 2: Create a FinanceOffice to manage transactions
        float totalBudget = 100000.0f;
        float reservedFunds = 20000.0f;
        float totalScholarshipsAmount = 800.0f;
        float totalSalaryPaid = 6500.0f;

        FinanceOffice financeOffice = new FinanceOffice(
                totalBudget,
                employeeSalaries,
                transactionHistory,
                reservedFunds,
                scholarships,
                Arrays.asList(employee1, employee2),
                totalScholarshipsAmount,
                totalSalaryPaid
        );

        // Step 3: Transaction Examples
        // 1. Paying salary to employees
        Transaction salaryTransaction1 = new Transaction(
                "TX001",
                TransactionType.SALARY_PAID,
                3000.0f,
                new Date(),
                "Salary payment for employee John Doe"
        );
        Transaction salaryTransaction2 = new Transaction(
                "TX002",
                TransactionType.SALARY_PAID,
                3500.0f,
                new Date(),
                "Salary payment for employee Jane Smith"
        );

        // 2. Adding funds to the office account
        Transaction addFundsTransaction = new Transaction(
                "TX003",
                TransactionType.FUNDS_ADDED,
                10000.0f,
                new Date(),
                "Funds added to office account"
        );

        // 3. Deducting funds from the office account (e.g., to pay for an expense)
        Transaction deductFundsTransaction = new Transaction(
                "TX004",
                TransactionType.FUNDS_DEDUCTED,
                2000.0f,
                new Date(),
                "Funds deducted for office maintenance"
        );

        // 4. Awarding scholarship to students
        Transaction scholarshipTransaction1 = new Transaction(
                "TX005",
                TransactionType.SCHOLARSHIP_AWARDED,
                500.0f,
                new Date(),
                "Scholarship awarded to Alice Johnson for merit"
        );
        Transaction scholarshipTransaction2 = new Transaction(
                "TX006",
                TransactionType.SCHOLARSHIP_AWARDED,
                300.0f,
                new Date(),
                "Scholarship awarded to Bob Smith for need"
        );

        // 5. Adjusting employee salary
        Transaction adjustSalaryTransaction = new Transaction(
                "TX007",
                TransactionType.SALARY_ADJUSTED,
                4000.0f,
                new Date(),
                "Salary adjusted for John Doe due to performance review"
        );

        // Step 4: Simulate Finance Office Transaction Logging
        financeOffice.logTransaction(salaryTransaction1);
        financeOffice.logTransaction(salaryTransaction2);
        financeOffice.logTransaction(addFundsTransaction);
        financeOffice.logTransaction(deductFundsTransaction);
        financeOffice.logTransaction(scholarshipTransaction1);
        financeOffice.logTransaction(scholarshipTransaction2);
        financeOffice.logTransaction(adjustSalaryTransaction);

        // Step 5: Display all transactions
        List<Transaction> allTransactions = financeOffice.getTransactionHistory();
        System.out.println("All Transactions:");
        for (Transaction transaction : allTransactions) {
            System.out.println(transaction.getSummary());
        }

        // Step 6: Print General Statistics
        System.out.println("\nGeneral Statistics:");
        System.out.println(financeOffice.printGeneralStatistics());
    }
}
