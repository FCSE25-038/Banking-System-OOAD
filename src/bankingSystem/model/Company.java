package bankingSystem.model;

public class Company extends Customer {

    private String companyName;
    private String registrationNo;
    private String contactPerson;

    public Company(String userId, String username, String password, String customerId, String address, String phoneNumber, String email, String companyName, String registrationNo, String contactPerson) {
        super(userId, username, password, customerId, address, phoneNumber, email);
        this.companyName = companyName;
        this.registrationNo = registrationNo;
        this.contactPerson = contactPerson;
    }

    @Override
    public String getDisplayName() {
        return companyName;
    }

    @Override
    public boolean isEligibleForCheque() {
        return false;
    }

    @Override
    public void viewDetails() {
        System.out.println("Customer ID: " + getCustomerId());
        System.out.println("Company Name: " + companyName);
        System.out.println("Registration Number: " + registrationNo);
        System.out.println("Contact Person: " + contactPerson);
        System.out.println("Address: " + getAddress());
        System.out.println("Phone Number: " + getPhoneNumber());
        System.out.println("Email: " + getEmail());
    }

    @Override
    public void updateDetails(String address, String phoneNumber, String email) {
        super.updateDetails(address, phoneNumber, email);
    }

    public String getCompanyName() {return companyName;}

    public String getRegistrationNo() {return registrationNo;}

    public String getContactPerson() {return contactPerson;}

}