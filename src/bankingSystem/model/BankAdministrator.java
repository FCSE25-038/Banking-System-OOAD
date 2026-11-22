package bankingSystem.model;

public class BankAdministrator extends User {

    private String adminId;
    private String name;

    public BankAdministrator(String userId, String username, String password, String adminId, String name) {
        super(userId, username, password);
        this.adminId = adminId;
        this.name = name;
    }

    public void registerCustomer(Bank bank, Customer customer) {
        bank.addCustomer(customer);
    }

    public void openAccount(Customer customer, Account account) {
        customer.addAccount(account);
    }

    public void updateCustomerDetails(Customer customer, String address, String phone, String email) {
        customer.updateDetails(address, phone, email);
    }

    public void viewCustomerDetails(Customer customer) {
        System.out.println(customer.getDisplayName());
    }

    @Override
    public String getRole() {
        return "BANK_ADMIN";
    }
}