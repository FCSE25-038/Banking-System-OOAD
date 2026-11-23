package bankingSystem.database;

import bankingSystem.model.Company;
import bankingSystem.model.Customer;
import bankingSystem.model.Individual;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAO {

    public boolean saveIndividual(
            Individual individual) {

        String customerSql =
                "INSERT INTO customers " +
                        "(customer_id, user_id, address, phone, email) " +
                        "VALUES (?, ?, ?, ?, ?)";

        String individualSql =
                "INSERT INTO individual_customers " +
                        "(customer_id, first_name, surname, national_id) " +
                        "VALUES (?, ?, ?, ?)";

        try (
                Connection connection =
                        DatabaseConnection.getConnection()
        ) {

            connection.setAutoCommit(false);

            PreparedStatement customerStatement =
                    connection.prepareStatement(
                            customerSql);

            customerStatement.setString(
                    1,
                    individual.getCustomerId());

            customerStatement.setString(
                    2,
                    individual.getUserId());

            customerStatement.setString(
                    3,
                    individual.getAddress());

            customerStatement.setString(
                    4,
                    individual.getPhoneNumber());

            customerStatement.setString(
                    5,
                    individual.getEmail());

            customerStatement.executeUpdate();

            PreparedStatement individualStatement =
                    connection.prepareStatement(
                            individualSql);

            individualStatement.setString(
                    1,
                    individual.getCustomerId());

            individualStatement.setString(
                    2,
                    individual.getFirstName());

            individualStatement.setString(
                    3,
                    individual.getSurname());

            individualStatement.setString(
                    4,
                    individual.getNationalId());

            individualStatement.executeUpdate();

            connection.commit();

            return true;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    public boolean saveCompany(
            Company company) {

        String customerSql =
                "INSERT INTO customers " +
                        "(customer_id, user_id, address, phone, email) " +
                        "VALUES (?, ?, ?, ?, ?)";

        String companySql =
                "INSERT INTO company_customers " +
                        "(customer_id, company_name, registration_no, contact_person) " +
                        "VALUES (?, ?, ?, ?)";

        try (
                Connection connection =
                        DatabaseConnection.getConnection()
        ) {

            connection.setAutoCommit(false);

            PreparedStatement customerStatement =
                    connection.prepareStatement(
                            customerSql);

            customerStatement.setString(
                    1,
                    company.getCustomerId());

            customerStatement.setString(
                    2,
                    company.getUserId());

            customerStatement.setString(
                    3,
                    company.getAddress());

            customerStatement.setString(
                    4,
                    company.getPhoneNumber());

            customerStatement.setString(
                    5,
                    company.getEmail());

            customerStatement.executeUpdate();

            PreparedStatement companyStatement =
                    connection.prepareStatement(
                            companySql);

            companyStatement.setString(
                    1,
                    company.getCustomerId());

            companyStatement.setString(
                    2,
                    company.getCompanyName());

            companyStatement.setString(
                    3,
                    company.getRegistrationNo());

            companyStatement.setString(
                    4,
                    company.getContactPerson());

            companyStatement.executeUpdate();

            connection.commit();

            return true;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    public Customer findCustomer(
            String customerId) {

        String sql =
                "SELECT * " +
                        "FROM customers c " +
                        "LEFT JOIN individual_customers i " +
                        "ON c.customer_id = i.customer_id " +
                        "LEFT JOIN company_customers co " +
                        "ON c.customer_id = co.customer_id " +
                        "WHERE c.customer_id = ?";

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

            if(rs.next()) {

                String userId =
                        rs.getString("user_id");

                String address =
                        rs.getString("address");

                String phone =
                        rs.getString("phone");

                String email =
                        rs.getString("email");

                String firstName =
                        rs.getString("first_name");

                if(firstName != null) {

                    return new Individual(
                            userId,
                            "",
                            "",
                            customerId,
                            address,
                            phone,
                            email,
                            firstName,
                            rs.getString("surname"),
                            rs.getString("national_id"));
                }

                return new Company(
                        userId,
                        "",
                        "",
                        customerId,
                        address,
                        phone,
                        email,
                        rs.getString("company_name"),
                        rs.getString("registration_no"),
                        rs.getString("contact_person"));
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Customer> getCustomers() {

        ArrayList<Customer> customers =
                new ArrayList<>();

        String sql =
                "SELECT * " +
                        "FROM customers c " +
                        "LEFT JOIN individual_customers i " +
                        "ON c.customer_id = i.customer_id " +
                        "LEFT JOIN company_customers co " +
                        "ON c.customer_id = co.customer_id";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet rs =
                        statement.executeQuery()
        ) {

            while(rs.next()) {

                String customerId =
                        rs.getString("customer_id");

                String userId =
                        rs.getString("user_id");

                String address =
                        rs.getString("address");

                String phone =
                        rs.getString("phone");

                String email =
                        rs.getString("email");

                String firstName =
                        rs.getString("first_name");

                if(firstName != null) {

                    customers.add(
                            new Individual(
                                    userId,
                                    "",
                                    "",
                                    customerId,
                                    address,
                                    phone,
                                    email,
                                    firstName,
                                    rs.getString("surname"),
                                    rs.getString("national_id")));
                }
                else {

                    customers.add(
                            new Company(
                                    userId,
                                    "",
                                    "",
                                    customerId,
                                    address,
                                    phone,
                                    email,
                                    rs.getString("company_name"),
                                    rs.getString("registration_no"),
                                    rs.getString("contact_person")));
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return customers;
    }

    public boolean customerExists(
            String customerId) {

        return findCustomer(
                customerId) != null;
    }

    public boolean deleteCustomer(
            String customerId) {

        try (
                Connection connection =
                        DatabaseConnection.getConnection()
        ) {

            connection.setAutoCommit(false);

            try {

                String deleteTransactions =
                        "DELETE bt FROM bank_transactions bt " +
                                "INNER JOIN accounts a " +
                                "ON bt.account_number = a.account_number " +
                                "WHERE a.customer_id = ?";

                try (
                        PreparedStatement statement =
                                connection.prepareStatement(
                                        deleteTransactions)
                ) {

                    statement.setString(
                            1,
                            customerId);

                    statement.executeUpdate();
                }

                String deleteAccounts =
                        "DELETE FROM accounts " +
                                "WHERE customer_id = ?";

                try (
                        PreparedStatement statement =
                                connection.prepareStatement(
                                        deleteAccounts)
                ) {

                    statement.setString(
                            1,
                            customerId);

                    statement.executeUpdate();
                }

                String deleteIndividuals =
                        "DELETE FROM individual_customers " +
                                "WHERE customer_id = ?";

                try (
                        PreparedStatement statement =
                                connection.prepareStatement(
                                        deleteIndividuals)
                ) {

                    statement.setString(
                            1,
                            customerId);

                    statement.executeUpdate();
                }

                String deleteCompanies =
                        "DELETE FROM company_customers " +
                                "WHERE customer_id = ?";

                try (
                        PreparedStatement statement =
                                connection.prepareStatement(
                                        deleteCompanies)
                ) {

                    statement.setString(
                            1,
                            customerId);

                    statement.executeUpdate();
                }

                String deleteCustomer =
                        "DELETE FROM customers " +
                                "WHERE customer_id = ?";

                try (
                        PreparedStatement statement =
                                connection.prepareStatement(
                                        deleteCustomer)
                ) {

                    statement.setString(
                            1,
                            customerId);

                    int rows =
                            statement.executeUpdate();

                    connection.commit();

                    return rows > 0;
                }

            } catch (SQLException e) {

                connection.rollback();

                throw e;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }

    public int getCustomerCount() {

        String sql =
                "SELECT COUNT(*) FROM customers";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet rs =
                        statement.executeQuery()
        ) {

            if(rs.next()) {

                return rs.getInt(1);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return 0;
    }
}