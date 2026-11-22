package bankingSystem.model;

import bankingSystem.InterestBearing;

import java.util.ArrayList;

public class Bank {

    private String bankName;
    private ArrayList<User> users;

    public Bank(String bankName) {
        this.bankName = bankName;
        users = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {users.add(customer);}

    public void addUser(User user) {users.add(user);}

    public Customer findCustomer(String customerId) {
        for (User user : users) {
            if (user instanceof Customer) {
                Customer customer = (Customer) user;
                if (customer.getCustomerId()
                        .equals(customerId)) {
                    return customer;
                }
            }
        }
        return null;
    }

    public boolean removeCustomer(String customerId) {
        Customer customer = findCustomer(customerId);
        if (customer != null) {
            users.remove(customer);
            return true;
        }
        return false;
    }

    public User authenticate(String username, String password) {
        for (User user : users) {
            if (user.authenticate(username, password)) {
                return user;
            }
        }
        return null;
    }

    public void processInterest() {
        for (User user : users) {
            if (user instanceof Customer) {
                Customer customer = (Customer) user;
        for (Account account : customer.viewAccounts()) {
            if (account instanceof InterestBearing) {
                InterestBearing interestbearin = (InterestBearing) account;
                interestbearin.applyInterest();
                    }
                }
            }
        }
    }
    public ArrayList<Customer> getCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        for(User user : users) {
            if(user instanceof Customer) {customers.add((Customer) user);}
        }
        return customers;}

    public ArrayList<User> getUsers() {return users;}

    public String getBankName() {return bankName;}


}