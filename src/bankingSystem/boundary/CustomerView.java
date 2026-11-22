package bankingSystem.boundary;

import bankingSystem.controller.AccountController;
import bankingSystem.controller.CustomerController;
import bankingSystem.controller.TransactionController;
import bankingSystem.database.AccountDAO;
import bankingSystem.model.Account;
import bankingSystem.model.Bank;
import bankingSystem.model.Customer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class CustomerView {

    private final Bank bank;

    private final CustomerController customerController;

    private final AccountController accountController;

    private final TransactionController transactionController;

    private final AccountDAO accountDAO;

    public CustomerView(Bank bank) {

        this.bank = bank;

        this.customerController =
                new CustomerController(bank);

        this.accountController =
                new AccountController(bank);

        this.transactionController =
                new TransactionController();

        this.accountDAO =
                new AccountDAO();
    }

    public void show(Stage stage) {

        Label heading =
                new Label("CUSTOMER PORTAL");

        heading.setStyle(
                "-fx-font-size: 22px;" +
                        "-fx-font-weight: bold;"
        );

        TextField customerIdField =
                new TextField();

        customerIdField.setPromptText(
                "Customer ID");

        TextField accountNumberField =
                new TextField();

        accountNumberField.setPromptText(
                "Account Number");

        TextField destinationCustomerField =
                new TextField();

        destinationCustomerField.setPromptText(
                "Destination Customer ID");

        TextField destinationAccountField =
                new TextField();

        destinationAccountField.setPromptText(
                "Destination Account Number");

        TextField amountField =
                new TextField();

        amountField.setPromptText(
                "Amount");

        TextArea outputArea =
                new TextArea();

        outputArea.setEditable(false);

        outputArea.setPrefHeight(180);

        Button viewDetailsBtn =
                new Button("View Details");

        Button viewAccountsBtn =
                new Button("View Accounts");

        Button depositBtn =
                new Button("Deposit");

        Button withdrawBtn =
                new Button("Withdraw");

        Button transferBtn =
                new Button("Transfer");

        Button transactionsBtn =
                new Button("Transaction History");

        Button changePasswordBtn =
                new Button("Change Password");

        Button clearBtn =
                new Button("Clear");

        Button logoutBtn =
                new Button("Logout");

        viewDetailsBtn.setOnAction(e -> {

            outputArea.setText(
                    customerController
                            .getCustomerDetails(
                                    customerIdField.getText()));
        });

        viewAccountsBtn.setOnAction(e -> {

            Customer customer =
                    customerController
                            .findCustomer(
                                    customerIdField.getText());

            if(customer == null) {

                showError(
                        "Customer not found.");

                return;
            }

            new ViewAccountsView(customer)
                    .show(new Stage());
        });

        depositBtn.setOnAction(e -> {

            try {

                boolean success =
                        accountController.deposit(
                                customerIdField.getText(),
                                accountNumberField.getText(),
                                Double.parseDouble(
                                        amountField.getText()));

                if(success) {

                    showMessage(
                            "Deposit successful.");
                }
                else {

                    showError(
                            "Deposit failed.");
                }

            } catch(Exception ex) {

                showError(
                        "Enter a valid amount.");
            }
        });

        withdrawBtn.setOnAction(e -> {

            try {

                boolean success =
                        accountController.withdraw(
                                customerIdField.getText(),
                                accountNumberField.getText(),
                                Double.parseDouble(
                                        amountField.getText()));

                if(success) {

                    showMessage(
                            "Withdrawal successful.");
                }
                else {

                    showError(
                            "Withdrawal failed.");
                }

            } catch(Exception ex) {

                showError(
                        "Enter a valid amount.");
            }
        });

        transferBtn.setOnAction(e -> {

            try {

                boolean success =
                        accountController.transfer(
                                customerIdField.getText(),
                                accountNumberField.getText(),
                                destinationCustomerField.getText(),
                                destinationAccountField.getText(),
                                Double.parseDouble(
                                        amountField.getText()));

                if(success) {

                    showMessage(
                            "Transfer successful.");
                }
                else {

                    showError(
                            "Transfer failed.");
                }

            } catch(Exception ex) {

                showError(
                        "Invalid transfer details.");
            }
        });

        transactionsBtn.setOnAction(e -> {

            Customer customer =
                    customerController
                            .findCustomer(
                                    customerIdField.getText());

            if(customer == null) {

                showError(
                        "Customer not found.");

                return;
            }

            ArrayList<Account> accounts =
                    accountDAO.getCustomerAccounts(
                            customer);

            if(accounts.isEmpty()) {

                showError(
                        "Customer has no accounts.");

                return;
            }

            String accountNumber =
                    accountNumberField.getText()
                            .trim();

            if(accountNumber.isEmpty()) {

                showError(
                        "Enter an account number.");

                return;
            }

            Account selectedAccount =
                    null;

            for(Account account : accounts) {

                if(account.getAccountNumber()
                        .equalsIgnoreCase(
                                accountNumber)) {

                    selectedAccount =
                            account;

                    break;
                }
            }

            if(selectedAccount == null) {

                showError(
                        "Account not found.");

                return;
            }

            new TransactionHistoryView(
                    selectedAccount)
                    .show(
                            new Stage());
        });

        changePasswordBtn.setOnAction(e -> {

            Customer customer =
                    customerController
                            .findCustomer(
                                    customerIdField.getText());

            if(customer == null) {

                showError(
                        "Customer not found.");

                return;
            }

            new ChangePasswordView(
                    customer,
                    bank)
                    .show(new Stage());
        });

        clearBtn.setOnAction(e -> {

            customerIdField.clear();
            accountNumberField.clear();
            destinationCustomerField.clear();
            destinationAccountField.clear();
            amountField.clear();
            outputArea.clear();
        });

        logoutBtn.setOnAction(e ->
                new LoginView(bank)
                        .show(stage));

        GridPane form =
                new GridPane();

        form.setHgap(10);
        form.setVgap(10);

        form.add(new Label("Customer ID"),0,0);
        form.add(customerIdField,1,0);

        form.add(new Label("Account Number"),0,1);
        form.add(accountNumberField,1,1);

        form.add(new Label("Destination Customer"),0,2);
        form.add(destinationCustomerField,1,2);

        form.add(new Label("Destination Account"),0,3);
        form.add(destinationAccountField,1,3);

        form.add(new Label("Amount"),0,4);
        form.add(amountField,1,4);

        HBox row1 =
                new HBox(
                        10,
                        viewDetailsBtn,
                        viewAccountsBtn);

        HBox row2 =
                new HBox(
                        10,
                        depositBtn,
                        withdrawBtn,
                        transferBtn);

        HBox row3 =
                new HBox(
                        10,
                        transactionsBtn,
                        changePasswordBtn);

        HBox row4 =
                new HBox(
                        10,
                        clearBtn,
                        logoutBtn);

        row1.setAlignment(Pos.CENTER);
        row2.setAlignment(Pos.CENTER);
        row3.setAlignment(Pos.CENTER);
        row4.setAlignment(Pos.CENTER);

        VBox center =
                new VBox(
                        15,
                        form,
                        row1,
                        row2,
                        row3,
                        outputArea,
                        row4);

        center.setPadding(
                new Insets(20));

        center.setAlignment(
                Pos.CENTER);

        BorderPane root =
                new BorderPane();

        root.setTop(heading);
        root.setCenter(center);

        BorderPane.setAlignment(
                heading,
                Pos.CENTER);

        Scene scene =
                new Scene(
                        root,
                        1000,
                        700);   scene.getStylesheets().add("bankingSystem/styles/styles.css");

        stage.setTitle(
                "Customer Portal");

        stage.setScene(scene);

        stage.show();
    }

    private void showMessage(
            String message) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION);

        alert.setHeaderText(null);

        alert.setContentText(message);

        alert.showAndWait();
    }

    private void showError(
            String message) {

        Alert alert =
                new Alert(
                        Alert.AlertType.ERROR);

        alert.setHeaderText(null);

        alert.setContentText(message);

        alert.showAndWait();
    }
}