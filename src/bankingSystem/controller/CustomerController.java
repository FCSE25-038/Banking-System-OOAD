package bankingSystem.controller;

import bankingSystem.database.CustomerDAO;
import bankingSystem.model.Account;
import bankingSystem.model.Bank;
import bankingSystem.model.Customer;

import java.util.ArrayList;

public class CustomerController {

    private CustomerDAO customerDAO;

    public CustomerController(Bank bank) {

        this.customerDAO =
                new CustomerDAO();
    }

    public Customer findCustomer(
            String customerId) {

        if(customerId == null
                || customerId.isBlank()) {

            return null;
        }

        return customerDAO
                .findCustomer(
                        customerId);
    }

    public boolean customerExists(
            String customerId) {

        return customerDAO
                .customerExists(
                        customerId);
    }

    public ArrayList<Account> getAccounts(
            String customerId) {

        Customer customer =
                customerDAO.findCustomer(
                        customerId);

        if(customer == null) {

            return new ArrayList<>();
        }

        return customer.viewAccounts();
    }

    public String getCustomerDetails(
            String customerId) {

        Customer customer =
                customerDAO.findCustomer(
                        customerId);

        if(customer == null) {

            return "Customer Not Found";
        }

        return
                "Customer ID: "
                        + customer.getCustomerId()
                        + "\n\n"
                        + "Name: "
                        + customer.getDisplayName()
                        + "\n\n"
                        + "Email: "
                        + customer.getEmail()
                        + "\n\n"
                        + "Phone: "
                        + customer.getPhoneNumber();
    }

    public boolean changePassword(
            Customer customer,
            String currentPassword,
            String newPassword,
            String confirmPassword) {

        if(customer == null)
            return false;

        if(currentPassword == null
                || newPassword == null
                || confirmPassword == null) {

            return false;
        }

        if(!customer.getPassword()
                .equals(currentPassword)) {

            return false;
        }

        if(!newPassword
                .equals(confirmPassword)) {

            return false;
        }

        customer.setPassword(
                newPassword);


        return true;
    }

    public int getCustomerCount() {

        return customerDAO
                .getCustomerCount();
    }

    public ArrayList<Customer> getCustomers() {

        return customerDAO
                .getCustomers();
    }
}