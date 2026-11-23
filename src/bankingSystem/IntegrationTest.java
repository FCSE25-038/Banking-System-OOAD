package bankingSystem;

import bankingSystem.controller.AccountController;
import bankingSystem.controller.BankAdminController;
import bankingSystem.controller.LoginController;
import bankingSystem.model.Bank;
import bankingSystem.model.User;

public class IntegrationTest {

    public static void main(String[] args) {

        System.out.println(
                "BANKING SYSTEM INTEGRATION TEST");

        Bank bank =
                new Bank(
                        "Banking System");

        LoginController loginController =
                new LoginController(bank);

        BankAdminController bankAdminController =
                new BankAdminController(bank);

        AccountController accountController =
                new AccountController(bank);

        long id =
                System.currentTimeMillis();

        String customerId1 =
                "C" + id;

        String customerId2 =
                "C" + (id + 1);

        String userId1 =
                "U" + id;

        String userId2 =
                "U" + (id + 1);

        String account1 =
                "A" + id;

        String account2 =
                "A" + (id + 1);

        System.out.println(
                "\nTEST 1 : LOGIN");

        User admin =
                loginController.login(
                        "admin1",
                        "1st admin");

        if(admin != null) {

            System.out.println(
                    "PASS : Login successful");
        }
        else {

            System.out.println(
                    "FAIL : Login failed");
        }

        System.out.println(
                "\nTEST 2 : CUSTOMER REGISTRATION");

        boolean customerCreated =
                bankAdminController
                        .registerIndividual(
                                userId1,
                                "john" + id,
                                "1234",
                                customerId1,
                                "Francistown",
                                "71234567",
                                "john@gmail.com",
                                "John",
                                "Smith",
                                "11111111");

        System.out.println(
                customerCreated
                        ? "PASS : Customer registered"
                        : "FAIL : Customer registration");

        System.out.println(
                "\nTEST 3 : OPEN ACCOUNT");

        boolean accountCreated =
                accountController
                        .openSavingsAccount(
                                customerId1,
                                account1,
                                "Francistown");

        System.out.println(
                accountCreated
                        ? "PASS : Account opened"
                        : "FAIL : Account opening");

        System.out.println(
                "\nTEST 4 : DEPOSIT");

        boolean deposit =
                accountController.deposit(
                        customerId1,
                        account1,
                        1000);

        System.out.println(
                deposit
                        ? "PASS : Deposit"
                        : "FAIL : Deposit");

        System.out.println(
                "\nTEST 5 : WITHDRAW");

        boolean withdraw =
                accountController.withdraw(
                        customerId1,
                        account1,
                        200);

        System.out.println(
                withdraw
                        ? "PASS : Withdrawal"
                        : "FAIL : Withdrawal");

        System.out.println(
                "\nTEST 6 : SECOND CUSTOMER");

        bankAdminController
                .registerIndividual(
                        userId2,
                        "mary" + id,
                        "1234",
                        customerId2,
                        "Gaborone",
                        "72345678",
                        "mary@gmail.com",
                        "Mary",
                        "Jones",
                        "22222222");

        accountController
                .openSavingsAccount(
                        customerId2,
                        account2,
                        "Gaborone");

        System.out.println(
                "\nTEST 7 : TRANSFER");

        boolean transfer =
                accountController.transfer(
                        customerId1,
                        account1,
                        customerId2,
                        account2,
                        300);

        System.out.println(
                transfer
                        ? "PASS : Transfer"
                        : "FAIL : Transfer");

        System.out.println(
                "INTEGRATION TEST COMPLETE");

        System.out.println(
                "Verify Database Tables:");

        System.out.println(
                "users");

        System.out.println(
                "customers");

        System.out.println(
                "individual_customers");

        System.out.println(
                "accounts");

        System.out.println(
                "transactions");

    }
}