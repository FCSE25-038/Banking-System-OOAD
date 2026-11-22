package bankingSystem.database;

import bankingSystem.model.BankAdministrator;
import bankingSystem.model.SystemAdministrator;
import bankingSystem.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {

    public boolean saveUser(
            User user) {

        String sql =
                "INSERT INTO users " +
                        "(user_id, username, password, role) " +
                        "VALUES (?, ?, ?, ?)";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    user.getUserId());

            statement.setString(
                    2,
                    user.getUsername());

            statement.setString(
                    3,
                    user.getPassword());

            statement.setString(
                    4,
                    user.getRole());

            return statement.executeUpdate() > 0;

        } catch(SQLException e) {

            e.printStackTrace();
        }

        return false;
    }

    public ResultSet getUserByUsername(
            String username) {

        String sql =
                "SELECT * FROM users " +
                        "WHERE username = ?";

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(
                    1,
                    username);

            return statement.executeQuery();

        } catch(Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    public boolean usernameExists(
            String username) {

        String sql =
                "SELECT * FROM users " +
                        "WHERE username = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    username);

            ResultSet rs =
                    statement.executeQuery();

            return rs.next();

        } catch(Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteUser(
            String userId) {

        String sql =
                "DELETE FROM users " +
                        "WHERE user_id = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    userId);

            return statement.executeUpdate() > 0;

        } catch(Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    public int getUserCount() {

        String sql =
                "SELECT COUNT(*) " +
                        "FROM users";

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

        } catch(Exception e) {

            e.printStackTrace();
        }

        return 0;
    }

    public String getUserRole(
            String username) {

        String sql =
                "SELECT role " +
                        "FROM users " +
                        "WHERE username = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    username);

            ResultSet rs =
                    statement.executeQuery();

            if(rs.next()) {

                return rs.getString(
                        "role");
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<User> getUsers() {

        ArrayList<User> users =
                new ArrayList<>();

        String sql =
                "SELECT * FROM users";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet rs =
                        statement.executeQuery()
        ) {

            while(rs.next()) {

                String userId =
                        rs.getString(
                                "user_id");

                String username =
                        rs.getString(
                                "username");

                String password =
                        rs.getString(
                                "password");

                String role =
                        rs.getString(
                                "role");

                if(role != null) {

                    role = role.trim();
                }

                if(role.equalsIgnoreCase(
                        "SYSTEM_ADMIN")
                        || role.equalsIgnoreCase(
                        "System Administrator")) {

                    users.add(
                            new SystemAdministrator(
                                    userId,
                                    username,
                                    password,
                                    "System Administrator"));
                }

                else if(role.equalsIgnoreCase(
                        "BANK_ADMIN")
                        || role.equalsIgnoreCase(
                        "BANK ADMIN")
                        || role.equalsIgnoreCase(
                        "BANKADMINISTRATOR")
                        || role.equalsIgnoreCase(
                        "Bank Administrator")) {

                    users.add(
                            new BankAdministrator(
                                    userId,
                                    username,
                                    password,
                                    "BA001",
                                    "Bank Administrator"));
                }
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return users;
    }
}