package bankingSystem.model;

public class ChequeAccount extends Account {

    private String employerName;

    public ChequeAccount(String accountNumber, String branch, Customer owner, String employerName) {
        super(accountNumber, branch, owner);
        this.employerName = employerName;
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

    public String getEmployerName() {return employerName;}

}