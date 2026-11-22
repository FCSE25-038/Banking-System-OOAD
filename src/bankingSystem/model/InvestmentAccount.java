package bankingSystem.model;

import bankingSystem.InterestBearing;

public class InvestmentAccount extends Account implements InterestBearing {

    private static final double RATE = 0.05;
    private static final double MIN_OPENING = 500;

    public InvestmentAccount(String accountNumber, String branch, Customer owner, double openingDeposit) {
        super(accountNumber, branch, owner);
        if (openingDeposit >= MIN_OPENING) {
            deposit(openingDeposit);
        } else {
            System.out.println("Minimum opening balance is P500");
        }
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= getBalance()) {
            setBalance(getBalance() - amount);
            addTransaction(new Transaction(amount,"Withdrawal"));
            return true;
        }
        return false;
    }

    @Override
    public double calculateInterest() {return getBalance() * RATE;}

    @Override
    public void applyInterest() {
        double interest =
                calculateInterest();
        setBalance(getBalance() + interest);
        addTransaction(new Transaction(interest,"Interest"));
    }
}