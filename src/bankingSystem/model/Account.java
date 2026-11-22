package bankingSystem.model;

import java.util.*;

public abstract class Account {
    private String accountNumber;
    private double balance;
    private String branch;
    private Customer owner;
    private ArrayList<Transaction> transactions;

    public Account(String accountNumber, String branch, Customer owner) {
        this.accountNumber = accountNumber;
        this.branch = branch;
        this.owner = owner;
        balance = 0.0;
        transactions = new ArrayList<>();
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add(new Transaction( amount, "Deposit" ));
        }
    }

    public abstract boolean withdraw(double amount);

    public void displayBalance() {
        System.out.println("Current Balance: P" + balance);
    }

    public ArrayList<Transaction> getTransactionHistory() {return transactions;}

    protected void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public String getAccountNumber() {return accountNumber;}

    public double getBalance() {return balance;}

    public String getBranch() {return branch;}

    public Customer getOwner() {return owner;}

    protected void setBalance(double balance) {
        this.balance = balance;
    }
}