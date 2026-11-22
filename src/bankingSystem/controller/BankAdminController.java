package bankingSystem.controller;

import bankingSystem.database.CustomerDAO;
import bankingSystem.database.UserDAO;
import bankingSystem.model.Bank;
import bankingSystem.model.Company;
import bankingSystem.model.Customer;
import bankingSystem.model.Individual;

import java.util.ArrayList;

public class BankAdminController {

    private Bank bank;

    private CustomerDAO customerDAO;

    private UserDAO userDAO;

    public BankAdminController(Bank bank) {

        this.bank = bank;

        this.customerDAO =
                new CustomerDAO();

        this.userDAO =
                new UserDAO();
    }

    public boolean registerIndividual(
            String userId,
            String username,
            String password,
            String customerId,
            String address,
            String phone,
            String email,
            String firstName,
            String surname,
            String nationalId) {

        Individual individual =
                new Individual(
                        userId,
                        username,
                        password,
                        customerId,
                        address,
                        phone,
                        email,
                        firstName,
                        surname,
                        nationalId);

        boolean userSaved =
                userDAO.saveUser(
                        individual);

        if(!userSaved) {

            return false;
        }

        return customerDAO
                .saveIndividual(
                        individual);
    }

    public boolean registerCompany(
            String userId,
            String username,
            String password,
            String customerId,
            String address,
            String phone,
            String email,
            String companyName,
            String registrationNo,
            String contactPerson) {

        Company company =
                new Company(
                        userId,
                        username,
                        password,
                        customerId,
                        address,
                        phone,
                        email,
                        companyName,
                        registrationNo,
                        contactPerson);

        boolean userSaved =
                userDAO.saveUser(
                        company);

        if(!userSaved) {

            return false;
        }

        return customerDAO
                .saveCompany(
                        company);
    }

    public ArrayList<Customer> getCustomers() {

        return customerDAO
                .getCustomers();
    }

    public Customer findCustomer(
            String customerId) {

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


    public boolean removeCustomer(
            String customerId) {

        return customerDAO
                .deleteCustomer(
                        customerId);
    }

    public int getCustomerCount() {

        return customerDAO
                .getCustomerCount();
    }

    public void processInterest() {

        bank.processInterest();
    }
}