package bankingSystem.model;

public class SystemAdministrator extends User {

    private String name;

    public SystemAdministrator(String userId, String username, String password, String name) {
        super(userId, username, password);
        this.name = name;
    }

    public void manageUserAccounts() {
        System.out.println("Managing accounts...");
    }

    public void resetPassword(User user, String oldPassword, String newPassword) {
        user.changePassword(oldPassword, newPassword);
    }

    public void backupData() {
        System.out.println("Backup completed.");
    }

    public void restoreData() {
        System.out.println("Data restored.");
    }

    public void updateSystemSettings() {
        System.out.println("Settings updated.");
    }

    @Override
    public String getRole() {
        return "SYSTEM_ADMIN";
    }
}
