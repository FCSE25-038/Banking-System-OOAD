package bankingSystem.controller;

import bankingSystem.database.AccountDAO;
import bankingSystem.database.CustomerDAO;
import bankingSystem.database.TransactionDAO;
import bankingSystem.model.Bank;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class AccountController {

    private AccountDAO accountDAO;

    private CustomerDAO customerDAO;

    private TransactionDAO transactionDAO;

    public AccountController(Bank bank) {

        this.accountDAO =
                new AccountDAO();

        this.customerDAO =
                new CustomerDAO();

        this.transactionDAO =
                new TransactionDAO();
    }

    public boolean openSavingsAccount(
            String customerId,
            String accountNumber,
            String branch) {

        if(!customerDAO.customerExists(
                customerId)) {

            return false;
        }

        return accountDAO.saveAccount(
                accountNumber,
                customerId,
                branch,
                0.0,
                "Savings");
    }

    public boolean openInvestmentAccount(
            String customerId,
            String accountNumber,
            String branch,
            double openingDeposit) {

        if(!customerDAO.customerExists(
                customerId)) {

            return false;
        }

        return accountDAO.saveAccount(
                accountNumber,
                customerId,
                branch,
                openingDeposit,
                "Investment");
    }

    public boolean openChequeAccount(
            String customerId,
            String accountNumber,
            String branch,
            String employerName) {

        if(!customerDAO.customerExists(
                customerId)) {

            return false;
        }

        return accountDAO.saveAccount(
                accountNumber,
                customerId,
                branch,
                0.0,
                "Cheque");
    }

    public boolean deposit(
            String customerId,
            String accountNumber,
            double amount) {

        if(amount <= 0) {

            return false;
        }

        if(!accountDAO.accountExists(
                accountNumber)) {

            return false;
        }

        double balance =
                accountDAO.getBalance(
                        accountNumber);

        boolean updated =
                accountDAO.updateBalance(
                        accountNumber,
                        balance + amount);

        if(updated) {

            transactionDAO.saveTransaction(
                    UUID.randomUUID()
                            .toString(),
                    accountNumber,
                    "Deposit",
                    amount,
                    Timestamp.valueOf(
                            LocalDateTime.now()));
        }

        return updated;
    }

    public boolean withdraw(
            String customerId,
            String accountNumber,
            double amount) {

        if(amount <= 0) {

            return false;
        }

        if(!accountDAO.accountExists(
                accountNumber)) {

            return false;
        }

        double balance =
                accountDAO.getBalance(
                        accountNumber);

        if(balance < amount) {

            return false;
        }

        boolean updated =
                accountDAO.updateBalance(
                        accountNumber,
                        balance - amount);

        if(updated) {

            transactionDAO.saveTransaction(
                    UUID.randomUUID()
                            .toString(),
                    accountNumber,
                    "Withdrawal",
                    amount,
                    Timestamp.valueOf(
                            LocalDateTime.now()));
        }

        return updated;
    }

    public boolean transfer(
            String fromCustomerId,
            String fromAccountNumber,
            String toCustomerId,
            String toAccountNumber,
            double amount) {

        if(amount <= 0) {

            return false;
        }

        if(!accountDAO.accountExists(
                fromAccountNumber)) {

            return false;
        }

        if(!accountDAO.accountExists(
                toAccountNumber)) {

            return false;
        }

        double sourceBalance =
                accountDAO.getBalance(
                        fromAccountNumber);

        if(sourceBalance < amount) {

            return false;
        }

        double destinationBalance =
                accountDAO.getBalance(
                        toAccountNumber);

        boolean sourceUpdated =
                accountDAO.updateBalance(
                        fromAccountNumber,
                        sourceBalance - amount);

        if(!sourceUpdated) {

            return false;
        }

        boolean destinationUpdated =
                accountDAO.updateBalance(
                        toAccountNumber,
                        destinationBalance + amount);

        if(!destinationUpdated) {

            return false;
        }

        transactionDAO.saveTransaction(
                UUID.randomUUID()
                        .toString(),
                fromAccountNumber,
                "Transfer Out",
                amount,
                Timestamp.valueOf(
                        LocalDateTime.now()));

        transactionDAO.saveTransaction(
                UUID.randomUUID()
                        .toString(),
                toAccountNumber,
                "Transfer In",
                amount,
                Timestamp.valueOf(
                        LocalDateTime.now()));

        return true;
    }

    public boolean accountExists(
            String accountNumber) {

        return accountDAO.accountExists(
                accountNumber);
    }

    public double getBalance(
            String accountNumber) {

        return accountDAO.getBalance(
                accountNumber);
    }

    public ArrayList<String> getAccounts(
            String customerId) {

        return accountDAO
                .getAccountsByCustomer(
                        customerId);
    }

    public int getAccountCount() {

        return accountDAO
                .getAccountCount();
    }

    public String getAccountType(
            String accountNumber) {

        return accountDAO
                .getAccountType(
                        accountNumber);
    }
}