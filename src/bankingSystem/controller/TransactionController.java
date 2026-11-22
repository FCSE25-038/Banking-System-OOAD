package bankingSystem.controller;

import bankingSystem.database.TransactionDAO;
import bankingSystem.model.Account;
import bankingSystem.model.Transaction;

import java.util.ArrayList;

public class TransactionController {

    private TransactionDAO transactionDAO;

    public TransactionController() {

        this.transactionDAO =
                new TransactionDAO();
    }

    public ArrayList<Transaction> getTransactions(
            Account account) {

        if(account == null) {

            return new ArrayList<>();
        }

        return transactionDAO
                .getTransactionHistory(
                        account.getAccountNumber());
    }

    public int getTransactionCount(
            Account account) {

        if(account == null) {

            return 0;
        }

        return transactionDAO
                .getTransactionHistory(
                        account.getAccountNumber())
                .size();
    }

    public Transaction findTransaction(
            Account account,
            String transactionId) {

        if(account == null
                || transactionId == null
                || transactionId.isBlank()) {

            return null;
        }

        ArrayList<Transaction> transactions =
                transactionDAO.getTransactionHistory(
                        account.getAccountNumber());

        for(Transaction transaction :
                transactions) {

            if(transaction
                    .getTransactionId()
                    .equalsIgnoreCase(
                            transactionId)) {

                return transaction;
            }
        }

        return null;
    }

    public boolean transactionExists(
            Account account,
            String transactionId) {

        return findTransaction(
                account,
                transactionId) != null;
    }

    public double getTotalTransactionAmount(
            Account account) {

        if(account == null) {

            return 0;
        }

        return transactionDAO
                .getTotalTransactions(
                        account.getAccountNumber());
    }

    public ArrayList<Transaction> getDeposits(
            Account account) {

        ArrayList<Transaction> deposits =
                new ArrayList<>();

        if(account == null) {

            return deposits;
        }

        ArrayList<Transaction> transactions =
                transactionDAO.getTransactionHistory(
                        account.getAccountNumber());

        for(Transaction transaction :
                transactions) {

            if(transaction
                    .getTransactionType()
                    .equalsIgnoreCase(
                            "Deposit")) {

                deposits.add(
                        transaction);
            }
        }

        return deposits;
    }

    public ArrayList<Transaction> getWithdrawals(
            Account account) {

        ArrayList<Transaction> withdrawals =
                new ArrayList<>();

        if(account == null) {

            return withdrawals;
        }

        ArrayList<Transaction> transactions =
                transactionDAO.getTransactionHistory(
                        account.getAccountNumber());

        for(Transaction transaction :
                transactions) {

            if(transaction
                    .getTransactionType()
                    .equalsIgnoreCase(
                            "Withdrawal")) {

                withdrawals.add(
                        transaction);
            }
        }

        return withdrawals;
    }
}