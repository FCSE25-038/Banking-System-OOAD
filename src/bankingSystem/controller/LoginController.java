package bankingSystem.controller;

import bankingSystem.database.UserDAO;
import bankingSystem.model.Bank;
import bankingSystem.model.BankAdministrator;
import bankingSystem.model.Customer;
import bankingSystem.model.Individual;
import bankingSystem.model.SystemAdministrator;
import bankingSystem.model.User;

import java.sql.ResultSet;

public class LoginController {

    private UserDAO userDAO;

    private User currentUser;

    private Bank bank;

    public LoginController(Bank bank) {

        this.bank = bank;

        this.userDAO =
                new UserDAO();
    }

    public User login(
            String username,
            String password) {

        if(username == null
                || username.isBlank()) {

            return null;
        }

        if(password == null
                || password.isBlank()) {

            return null;
        }

        try {

            ResultSet rs =
                    userDAO.getUserByUsername(
                            username);

            if(rs != null && rs.next()) {

                String storedPassword =
                        rs.getString(
                                "password");

                if(!storedPassword.equals(
                        password)) {

                    return null;
                }

                String userId =
                        rs.getString(
                                "user_id");

                String role =
                        rs.getString(
                                "role");

                User user =
                        createPlaceholderUser(
                                userId,
                                username,
                                storedPassword,
                                role);

                currentUser = user;

                return user;
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    private User createPlaceholderUser(
            String userId,
            String username,
            String password,
            String role) {

        if(role == null) {

            return null;
        }

        switch(role.toUpperCase()) {

            case "SYSTEM_ADMIN":
            case "SYSTEM ADMIN":
            case "SYSTEMADMINISTRATOR":
            case "SYSTEM ADMINISTRATOR":

                return new SystemAdministrator(
                        userId,
                        username,
                        password,
                        "System Administrator");

            case "BANK_ADMIN":
            case "BANK ADMIN":
            case "BANKADMINISTRATOR":
            case "BANK ADMINISTRATOR":

                return new BankAdministrator(
                        userId,
                        username,
                        password,
                        "BA001",
                        "Bank Administrator");

            case "CUSTOMER":

                return new Individual(
                        userId,
                        username,
                        password,
                        "C001",
                        "Unknown Address",
                        "00000000",
                        "customer@email.com",
                        "Customer",
                        "User",
                        "000000");

            default:

                return null;
        }
    }

    public void logout() {

        currentUser = null;
    }

    public User getCurrentUser() {

        return currentUser;
    }

    public boolean isLoggedIn() {

        return currentUser != null;
    }

    public boolean usernameExists(
            String username) {

        return userDAO.usernameExists(
                username);
    }

    public int getUserCount() {

        return userDAO.getUserCount();
    }

    public String getUserRole(
            User user) {

        if(user == null) {

            return "NONE";
        }

        if(user instanceof SystemAdministrator) {

            return "SYSTEM_ADMIN";
        }

        if(user instanceof BankAdministrator) {

            return "BANK_ADMIN";
        }

        if(user instanceof Customer) {

            return "CUSTOMER";
        }

        return "UNKNOWN";
    }

    public boolean isCustomer() {

        return currentUser instanceof Customer;
    }

    public boolean isBankAdmin() {

        return currentUser instanceof BankAdministrator;
    }

    public boolean isSystemAdmin() {

        return currentUser instanceof SystemAdministrator;
    }
}