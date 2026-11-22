package bankingSystem.database;

import bankingSystem.model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class TransactionDAO {

    public boolean saveTransaction(
            String transactionId,
            String accountNumber,
            String transactionType,
            double amount,
            Timestamp transactionDate) {

        String sql =
                "INSERT INTO bank_transactions " +
                        "(transaction_id, account_number, transaction_type, amount, transaction_date) " +
                        "VALUES (?, ?, ?, ?, ?)";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, transactionId);
            statement.setString(2, accountNumber);
            statement.setString(3, transactionType);
            statement.setDouble(4, amount);
            statement.setTimestamp(5, transactionDate);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }

    public ArrayList<Transaction> getTransactionHistory(
            String accountNumber) {

        ArrayList<Transaction> transactions =
                new ArrayList<>();

        String sql =
                "SELECT * FROM bank_transactions " +
                        "WHERE account_number = ? " +
                        "ORDER BY transaction_date DESC";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    accountNumber);

            ResultSet rs =
                    statement.executeQuery();

            while (rs.next()) {

                Transaction transaction =
                        new Transaction(
                                rs.getDouble(
                                        "amount"),
                                rs.getString(
                                        "transaction_type"));

                transactions.add(
                        transaction);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return transactions;
    }

    public boolean deleteTransaction(
            String transactionId) {

        String sql =
                "DELETE FROM bank_transactions " +
                        "WHERE transaction_id = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    transactionId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }

    public boolean transactionExists(
            String transactionId) {

        String sql =
                "SELECT * FROM bank_transactions " +
                        "WHERE transaction_id = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    transactionId);

            ResultSet rs =
                    statement.executeQuery();

            return rs.next();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }

    public int getTransactionCount() {

        String sql =
                "SELECT COUNT(*) " +
                        "FROM bank_transactions";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet rs =
                        statement.executeQuery()
        ) {

            if (rs.next()) {

                return rs.getInt(1);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return 0;
    }

    public int getTransactionCount(
            String accountNumber) {

        String sql =
                "SELECT COUNT(*) " +
                        "FROM bank_transactions " +
                        "WHERE account_number = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    accountNumber);

            ResultSet rs =
                    statement.executeQuery();

            if (rs.next()) {

                return rs.getInt(1);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return 0;
    }

    public double getTotalTransactions(
            String accountNumber) {

        String sql =
                "SELECT SUM(amount) " +
                        "FROM bank_transactions " +
                        "WHERE account_number = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    accountNumber);

            ResultSet rs =
                    statement.executeQuery();

            if (rs.next()) {

                return rs.getDouble(1);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return 0;
    }

    public ArrayList<Transaction> getDeposits(
            String accountNumber) {

        ArrayList<Transaction> deposits =
                new ArrayList<>();

        String sql =
                "SELECT * FROM bank_transactions " +
                        "WHERE account_number = ? " +
                        "AND transaction_type = 'Deposit'";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    accountNumber);

            ResultSet rs =
                    statement.executeQuery();

            while (rs.next()) {

                deposits.add(
                        new Transaction(
                                rs.getDouble(
                                        "amount"),
                                rs.getString(
                                        "transaction_type")));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return deposits;
    }

    public ArrayList<Transaction> getWithdrawals(
            String accountNumber) {

        ArrayList<Transaction> withdrawals =
                new ArrayList<>();

        String sql =
                "SELECT * FROM bank_transactions " +
                        "WHERE account_number = ? " +
                        "AND transaction_type = 'Withdrawal'";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    accountNumber);

            ResultSet rs =
                    statement.executeQuery();

            while (rs.next()) {

                withdrawals.add(
                        new Transaction(
                                rs.getDouble(
                                        "amount"),
                                rs.getString(
                                        "transaction_type")));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return withdrawals;
    }
}