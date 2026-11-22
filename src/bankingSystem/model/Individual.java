package bankingSystem.model;

public class Individual extends Customer {

    private String firstName;
    private String surname;
    private String nationalId;

    public Individual (String userId, String username, String password, String customerId, String address, String phoneNumber, String email, String firstName, String surname, String nationalId) {
        super(userId, username, password, customerId, address, phoneNumber, email);
        this.firstName = firstName;
        this.surname = surname;
        this.nationalId = nationalId;
    }

    @Override
    public String getDisplayName() {
        return firstName + " " + surname;
    }

    @Override
    public boolean isEligibleForCheque() {
        return true;
    }

    @Override
    public void viewDetails() {

        System.out.println("Customer ID: " + getCustomerId());
        System.out.println("Name: " + getDisplayName());
        System.out.println("National ID: " + nationalId);
        System.out.println("Address: " + getAddress());
        System.out.println("Phone Number: " + getPhoneNumber());
        System.out.println("Email: " + getEmail());
    }

    public void updateDetails(String address,
                              String phoneNumber,
                              String email) {

        super.updateDetails(
                address,
                phoneNumber,
                email);
    }
    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }
    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }
}