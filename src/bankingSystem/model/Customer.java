package bankingSystem.model;

import java.util.ArrayList;

public abstract class Customer extends User {
    private String customerId;
    private String address;
    private String phoneNumber;
    private String email;
    private ArrayList<Account> accounts;

    public Customer(String userId, String username, String password, String customerId, String address, String phoneNumber, String email) {
        super(userId, username, password);
        this.customerId = customerId;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        accounts = new ArrayList<>();
    }

    public void updateDetails(String address, String phoneNumber, String email) {
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getAddress() {return address;}

    public String getPhoneNumber() {return phoneNumber;}

    public String getEmail() {return email;}

    public abstract void viewDetails();

    public void addAccount(Account account) {accounts.add(account);}

    public void removeAccount(Account account) {accounts.remove(account);}

    public ArrayList<Account> viewAccounts() {return accounts;}

    public String getCustomerId() {return customerId;}

    public abstract String getDisplayName();

    public abstract boolean isEligibleForCheque();

    @Override
    public String getRole() {
        return "Customer";
    }
}