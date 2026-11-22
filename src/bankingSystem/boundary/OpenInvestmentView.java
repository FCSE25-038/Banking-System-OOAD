package bankingSystem.boundary;

import bankingSystem.controller.AccountController;
import bankingSystem.model.Bank;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class OpenInvestmentView {

    private Bank bank;
    private AccountController accountController;

    public OpenInvestmentView(Bank bank) {
        this.bank = bank;
        this.accountController = new AccountController(bank);
    }

    public void show(Stage stage) {

        Label heading = new Label("OPEN INVESTMENT ACCOUNT");

        heading.setStyle("-fx-font-size: 20px;" + "-fx-font-weight: bold;"
        );

        TextField customerId = new TextField();

        TextField accountNumber = new TextField();

        TextField branch = new TextField();

        TextField openingDeposit = new TextField();

        customerId.setPromptText("Customer ID");

        accountNumber.setPromptText("Account Number");

        branch.setPromptText("Branch");

        openingDeposit.setPromptText("Opening Deposit");

        GridPane form = new GridPane();

        form.setPadding(new Insets(20));

        form.setHgap(10);
        form.setVgap(10);

        form.add(new Label("Customer ID"), 0, 0);

        form.add(customerId, 1, 0);

        form.add(new Label("Account Number"), 0, 1);

        form.add(accountNumber, 1, 1);

        form.add(new Label("Branch"), 0, 2);

        form.add(branch, 1, 2);

        form.add(new Label("Opening Deposit"), 0, 3);

        form.add(openingDeposit, 1, 3);

        Button createBtn = new Button("Create");

        Button clearBtn = new Button("Clear");

        Button backBtn = new Button("Back");

        createBtn.setOnAction(e -> {

            if(customerId.getText().isEmpty()
                    || accountNumber.getText().isEmpty()
                    || branch.getText().isEmpty()
                    || openingDeposit.getText().isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setContentText("All fields are required.");

                alert.showAndWait();

                return;
            }

            double deposit;

            try {

                deposit = Double.parseDouble(
                        openingDeposit.getText());

            } catch(Exception ex) {

                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setContentText(
                        "Invalid opening deposit.");

                alert.showAndWait();

                return;
            }

            boolean created =
                    accountController.openInvestmentAccount(
                            customerId.getText(),
                            accountNumber.getText(),
                            branch.getText(),
                            deposit);

            if(!created) {

                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setContentText(
                        "Customer not found.");

                alert.showAndWait();

                return;
            }

            Alert alert =
                    new Alert(
                            Alert.AlertType.INFORMATION);

            alert.setContentText(
                    "Investment Account created successfully.");

            alert.showAndWait();
        });

        clearBtn.setOnAction(e -> {

            customerId.clear();
            accountNumber.clear();
            branch.clear();
            openingDeposit.clear();
        });

        backBtn.setOnAction(e ->
                new BankAdminView(bank)
                        .show(stage));

        HBox buttons = new HBox(
                10,
                createBtn,
                clearBtn,
                backBtn);

        buttons.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();

        root.setPadding(new Insets(20));

        root.setTop(heading);
        root.setCenter(form);
        root.setBottom(buttons);

        BorderPane.setAlignment(
                heading,
                Pos.CENTER);

        Scene scene = new Scene(
                root,
                600,
                400);   scene.getStylesheets().add("bankingSystem/styles/styles.css");

        stage.setTitle("Open Investment Account");

        stage.setScene(scene);

        stage.show();
    }
}