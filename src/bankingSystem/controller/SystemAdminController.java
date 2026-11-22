package bankingSystem.controller;

import bankingSystem.database.UserDAO;
import bankingSystem.model.Bank;
import bankingSystem.model.BankAdministrator;
import bankingSystem.model.SystemAdministrator;
import bankingSystem.model.User;

import java.sql.ResultSet;
import java.util.ArrayList;

public class SystemAdminController {

    private Bank bank;

    private UserDAO userDAO;

    public SystemAdminController(Bank bank) {

        this.bank = bank;

        this.userDAO =
                new UserDAO();
    }

    public boolean createSystemAdministrator(
            String userId,
            String username,
            String password,
            String name) {

        if(userId == null || userId.isBlank())
            return false;

        if(username == null || username.isBlank())
            return false;

        if(password == null || password.isBlank())
            return false;

        if(name == null || name.isBlank())
            return false;

        SystemAdministrator admin =
                new SystemAdministrator(
                        userId,
                        username,
                        password,
                        name);

        return userDAO.saveUser(admin);
    }

    public boolean createBankAdministrator(
            String userId,
            String username,
            String password,
            String adminId,
            String name) {

        if(userId == null || userId.isBlank())
            return false;

        if(username == null || username.isBlank())
            return false;

        if(password == null || password.isBlank())
            return false;

        if(adminId == null || adminId.isBlank())
            return false;

        if(name == null || name.isBlank())
            return false;

        BankAdministrator admin =
                new BankAdministrator(
                        userId,
                        username,
                        password,
                        adminId,
                        name);

        return userDAO.saveUser(admin);
    }

    public ArrayList<User> getUsers() {

        return userDAO.getUsers();
    }

    public ResultSet findUser(
            String username) {

        return userDAO.getUserByUsername(
                username);
    }

    public boolean userExists(
            String username) {

        return userDAO.usernameExists(
                username);
    }

    public int getUserCount() {

        return userDAO.getUserCount();
    }

    public boolean backupData() {

        return true;
    }

    public boolean restoreData() {

        return true;
    }
}