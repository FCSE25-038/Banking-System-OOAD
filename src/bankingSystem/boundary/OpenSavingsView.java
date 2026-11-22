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

public class OpenSavingsView {

    private Bank bank;
    private AccountController accountController;

    public OpenSavingsView(Bank bank) {
        this.bank = bank;
        this.accountController = new AccountController(bank);
    }

    public void show(Stage stage) {

        Label heading =
                new Label(
                        "OPEN SAVINGS ACCOUNT");

        heading.setStyle(
                "-fx-font-size: 20px;" +
                        "-fx-font-weight: bold;"
        );

        TextField customerId =
                new TextField();

        TextField accountNumber =
                new TextField();

        TextField branch =
                new TextField();

        customerId.setPromptText(
                "Customer ID");

        accountNumber.setPromptText(
                "Account Number");

        branch.setPromptText(
                "Branch");

        GridPane form =
                new GridPane();

        form.setPadding(
                new Insets(20));

        form.setHgap(10);
        form.setVgap(10);

        form.add(
                new Label("Customer ID"),
                0,
                0);

        form.add(
                customerId,
                1,
                0);

        form.add(
                new Label("Account Number"),
                0,
                1);

        form.add(
                accountNumber,
                1,
                1);

        form.add(
                new Label("Branch"),
                0,
                2);

        form.add(
                branch,
                1,
                2);

        Button createBtn =
                new Button("Create");

        Button clearBtn =
                new Button("Clear");

        Button backBtn =
                new Button("Back");

        createBtn.setOnAction(e -> {

            if(customerId.getText().isEmpty()
                    || accountNumber.getText().isEmpty()
                    || branch.getText().isEmpty()) {

                Alert alert =
                        new Alert(
                                Alert.AlertType.ERROR);

                alert.setContentText(
                        "All fields are required.");

                alert.showAndWait();

                return;
            }

            boolean created =
                    accountController.openSavingsAccount(
                            customerId.getText(),
                            accountNumber.getText(),
                            branch.getText());

            if(!created) {

                Alert alert =
                        new Alert(
                                Alert.AlertType.ERROR);

                alert.setContentText(
                        "Customer not found.");

                alert.showAndWait();

                return;
            }

            Alert alert =
                    new Alert(
                            Alert.AlertType.INFORMATION);

            alert.setContentText(
                    "Savings Account created successfully.");

            alert.showAndWait();
        });

        clearBtn.setOnAction(e -> {

            customerId.clear();
            accountNumber.clear();
            branch.clear();
        });

        backBtn.setOnAction(e ->
                new BankAdminView(bank)
                        .show(stage));

        HBox buttons =
                new HBox(
                        10,
                        createBtn,
                        clearBtn,
                        backBtn);

        buttons.setAlignment(
                Pos.CENTER);

        BorderPane root =
                new BorderPane();

        root.setPadding(
                new Insets(20));

        root.setTop(heading);
        root.setCenter(form);
        root.setBottom(buttons);

        BorderPane.setAlignment(
                heading,
                Pos.CENTER);

        Scene scene =
                new Scene(
                        root,
                        600,
                        350);  scene.getStylesheets().add("bankingSystem/styles/styles.css");

        stage.setTitle(
                "Open Savings Account");

        stage.setScene(scene);

        stage.show();
    }
}