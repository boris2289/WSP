package finance;

import ENUMS.TransactionType;

import java.time.LocalDate;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Transaction {

    // Attributes
    private String transactionId;
    private TransactionType type;
    private float amount;
    private Date date;
    private String description;

    // Constructor
    public Transaction(String transactionId, TransactionType type, float amount, Date date, String description) {
        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public Transaction(String transactionId, TransactionType type, float amount) {
        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = localDate.atStartOfDay();
        this.date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        this.description = "None";
    }

    // Getter and Setter Methods
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Method to get a summary of the transaction
    public String getSummary() {
        return "Transaction ID: " + transactionId +
                "\nType: " + type +
                "\nAmount: " + amount +
                "\nDate: " + date.toString() +
                "\nDescription: " + description;
    }
}
