package organizations;

import ENUMS.TransactionType;
import abstractt.Employee;
import finance.Scholarship;
import finance.Transaction;

import java.util.List;
import java.util.Map;

import java.util.Map;
import java.util.List;

public class FinanceOffice {

    private float totalBudget;
    private Map<Employee, Float> employeeSalaries;
    private List<Transaction> transactionHistory;
    private float reservedFunds;
    private List<Scholarship> scholarships;  // New field for managing scholarships
    private List<Employee> employees;        // New field for managing employees
    private float totalScholarshipsAmount;   // New field to track total scholarships amount
    private float totalSalaryPaid;           // New field to track total salary paid

    // Constructor with additional fields
    public FinanceOffice(float totalBudget,
                         Map<Employee, Float> employeeSalaries,
                         List<Transaction> transactionHistory,
                         float reservedFunds,
                         List<Scholarship> scholarships,
                         List<Employee> employees,
                         float totalScholarshipsAmount,
                         float totalSalaryPaid) {
        this.totalBudget = totalBudget;
        this.employeeSalaries = employeeSalaries;
        this.transactionHistory = transactionHistory;
        this.reservedFunds = reservedFunds;
        this.scholarships = scholarships;
        this.employees = employees;
        this.totalScholarshipsAmount = totalScholarshipsAmount;
        this.totalSalaryPaid = totalSalaryPaid;
    }

    // Getters and setters for the new fields
    public List<Scholarship> getScholarships() {
        return scholarships;
    }

    public void setScholarships(List<Scholarship> scholarships) {
        this.scholarships = scholarships;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public float getTotalScholarshipsAmount() {
        return totalScholarshipsAmount;
    }

    public void setTotalScholarshipsAmount(float totalScholarshipsAmount) {
        this.totalScholarshipsAmount = totalScholarshipsAmount;
    }

    public float getTotalSalaryPaid() {
        return totalSalaryPaid;
    }

    public void setTotalSalaryPaid(float totalSalaryPaid) {
        this.totalSalaryPaid = totalSalaryPaid;
    }

    // Existing getter and setter for totalBudget, employeeSalaries, transactionHistory, reservedFunds
    public float getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(float totalBudget) {
        this.totalBudget = totalBudget;
    }

    public Map<Employee, Float> getEmployeeSalaries() {
        return employeeSalaries;
    }

    public void setEmployeeSalaries(Map<Employee, Float> employeeSalaries) {
        this.employeeSalaries = employeeSalaries;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(List<Transaction> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    public float getReservedFunds() {
        return reservedFunds;
    }

    public void setReservedFunds(float reservedFunds) {
        this.reservedFunds = reservedFunds;
    }

    public void logTransaction(Transaction transaction) {
        this.transactionHistory.add(transaction);
    }

    public String printGeneralStatistics() {
        return String.format("Total Budget: %.2f\n" +
                        "Total Salary Paid: %.2f\n" +
                        "Reserved Funds: %.2f\n" +
                        "Total Scholarships Amount: %.2f\n" +
                        "Number of Employees: %d\n" +
                        "Number of Scholarships: %d\n",
                totalBudget,
                totalSalaryPaid,
                reservedFunds,
                totalScholarshipsAmount,
                employees.size(),
                scholarships.size());
    }

}

