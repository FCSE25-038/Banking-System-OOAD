package bankingSystem.model;

import bankingSystem.InterestBearing;

public class SavingsAccount extends Account implements InterestBearing {

    private static final double RATE = 0.0005;

    public SavingsAccount(String accountNumber, String branch, Customer owner) {
        super(accountNumber, branch, owner);
    }

    @Override
    public boolean withdraw(double amount) {
        System.out.println("Withdrawals are not permitted.");
        return false;
    }

    @Override
    public double calculateInterest() {return getBalance() * RATE;}

    @Override
    public void applyInterest() {double interest = calculateInterest();
        setBalance(getBalance() + interest);
        addTransaction(
                new Transaction(interest,"Interest"));
    }
}

