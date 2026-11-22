package bankingSystem.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {

    private String transactionId;
    private LocalDateTime transactionDate;
    private double amount;
    private String transactionType;

    public Transaction(double amount, String transactionType) {
        this.transactionId = UUID.randomUUID().toString();
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionDate = LocalDateTime.now();
    }

    public String getTransactionId() {return transactionId;}

    public LocalDateTime getTransactionDate() {return transactionDate;}

    public double getAmount() {return amount;}

    public String getTransactionType() {return transactionType;}

    public void displayTransaction() {
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Date: " + transactionDate);
        System.out.println("Type: " + transactionType);
        System.out.println("Amount: P" + amount);
    }

    @Override
    public String toString() {
        return transactionId + " | " + transactionType + " | P" + amount + " | " + transactionDate;
    }
}