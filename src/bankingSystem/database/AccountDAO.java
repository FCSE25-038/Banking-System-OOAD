package bankingSystem.database;

import bankingSystem.model.Account;
import bankingSystem.model.ChequeAccount;
import bankingSystem.model.Customer;
import bankingSystem.model.InvestmentAccount;
import bankingSystem.model.SavingsAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountDAO {

    public boolean saveAccount(
            String accountNumber,
            String customerId,
            String branch,
            double balance,
            String accountType) {

        String sql =
                "INSERT INTO accounts " +
                        "(account_number, customer_id, branch, balance, account_type) " +
                        "VALUES (?, ?, ?, ?, ?)";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, accountNumber);
            statement.setString(2, customerId);
            statement.setString(3, branch);
            statement.setDouble(4, balance);
            statement.setString(5, accountType);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }

    public boolean accountExists(
            String accountNumber) {

        String sql =
                "SELECT * FROM accounts " +
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

            return rs.next();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }

    public String findAccount(
            String accountNumber) {

        String sql =
                "SELECT account_number " +
                        "FROM accounts " +
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

                return rs.getString(
                        "account_number");
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }

    public String getAccountType(
            String accountNumber) {

        String sql =
                "SELECT account_type " +
                        "FROM accounts " +
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

                return rs.getString(
                        "account_type");
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }

    public double getBalance(
            String accountNumber) {

        String sql =
                "SELECT balance " +
                        "FROM accounts " +
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

                return rs.getDouble(
                        "balance");
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return 0;
    }

    public boolean updateBalance(
            String accountNumber,
            double newBalance) {

        String sql =
                "UPDATE accounts " +
                        "SET balance = ? " +
                        "WHERE account_number = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setDouble(
                    1,
                    newBalance);

            statement.setString(
                    2,
                    accountNumber);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }

    public ArrayList<String> getAccountsByCustomer(
            String customerId) {

        ArrayList<String> accounts =
                new ArrayList<>();

        String sql =
                "SELECT account_number " +
                        "FROM accounts " +
                        "WHERE customer_id = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    customerId);

            ResultSet rs =
                    statement.executeQuery();

            while (rs.next()) {

                accounts.add(
                        rs.getString(
                                "account_number"));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return accounts;
    }

    public ArrayList<Account> getCustomerAccounts(
            Customer customer) {

        ArrayList<Account> accounts =
                new ArrayList<>();

        String sql =
                "SELECT * FROM accounts " +
                        "WHERE customer_id = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    customer.getCustomerId());

            ResultSet rs =
                    statement.executeQuery();

            while (rs.next()) {

                String accountNumber =
                        rs.getString(
                                "account_number");

                String branch =
                        rs.getString(
                                "branch");

                String type =
                        rs.getString(
                                "account_type");

                Account account = null;

                if(type.equalsIgnoreCase(
                        "Savings")) {

                    account =
                            new SavingsAccount(
                                    accountNumber,
                                    branch,
                                    customer);
                }

                else if(type.equalsIgnoreCase(
                        "Investment")) {

                    account =
                            new InvestmentAccount(
                                    accountNumber,
                                    branch,
                                    customer,
                                    rs.getDouble(
                                            "balance"));
                }

                else if(type.equalsIgnoreCase(
                        "Cheque")) {

                    account =
                            new ChequeAccount(
                                    accountNumber,
                                    branch,
                                    customer,
                                    "");
                }

                if(account != null) {

                    accounts.add(
                            account);
                }
            }

        } catch(SQLException e) {

            e.printStackTrace();
        }

        return accounts;
    }

    public boolean deleteAccount(
            String accountNumber) {

        String sql =
                "DELETE FROM accounts " +
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

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }

    public int getAccountCount() {

        String sql =
                "SELECT COUNT(*) " +
                        "FROM accounts";

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
}