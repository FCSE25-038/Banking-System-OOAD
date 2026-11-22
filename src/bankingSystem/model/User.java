package bankingSystem.model;

public abstract class User {
    private String userId;
    private String username;
    private String password;

    public User(String userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public boolean changePassword(String oldPassword, String newPassword) {
        if (this.password.equals(oldPassword)) {this.password = newPassword;return true;
        }
        return false;
    }

    public abstract String getRole();

    public String getUserId() {return userId;}

    public String getUsername() {return username;}

    public String getPassword() {return password;}

    public void setPassword(String password) {
        this.password = password;
    }
}