package abstractt;
import ENUMS.Role;
import ENUMS.Language;
import java.util.Date;

public class Employee extends User {
    private double salary;
    private Date hireDate;

    public Employee(String userId, String name, String email, String phoneNumber, String password, Role role, Language preferredLanguage, double salary, Date hireDate) {
        super(userId, name, email, phoneNumber, password, role, preferredLanguage);
        this.salary = salary;
        this.hireDate = hireDate;
    }

    public Employee(String userId, String name, String email, String phoneNumber, String password, double salary, Date hireDate) {
        super(userId, name, email, phoneNumber, password);
        this.salary = salary;
        this.hireDate = hireDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative.");
        }
        this.salary = salary;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        if (hireDate == null) {
            throw new IllegalArgumentException("Hire date cannot be null.");
        }
        this.hireDate = hireDate;
    }

    public double calculateAnnualSalary() {
        return salary * 12;
    }

    public void giveRaise(double percentage) {
        if (percentage < 0) {
            throw new IllegalArgumentException("Raise percentage cannot be negative.");
        }
        salary += salary * (percentage / 100);
    }

    @Override
    public String toString() {
        return String.format("Employee{id='%s', name='%s', email='%s', role=%s, language=%s, salary=%.2f, hireDate=%s}",
                getUserId(), getName(), email, role, preferredLanguage, salary, hireDate);
    }
}