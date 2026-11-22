package bankingSystem.boundary;

import bankingSystem.model.Bank;
import bankingSystem.model.Company;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class RegisterCompanyView {

    private Bank bank;

    public RegisterCompanyView(Bank bank) {
        this.bank = bank;
    }

    public void show(Stage stage) {

        Label heading =
                new Label(
                        "REGISTER COMPANY CUSTOMER");

        heading.setStyle(
                "-fx-font-size: 20px;" +
                        "-fx-font-weight: bold;"
        );

        TextField userId =
                new TextField();

        TextField username =
                new TextField();

        PasswordField password =
                new PasswordField();

        TextField customerId =
                new TextField();

        TextField address =
                new TextField();

        TextField phone =
                new TextField();

        TextField email =
                new TextField();

        TextField companyName =
                new TextField();

        TextField registrationNo =
                new TextField();

        TextField contactPerson =
                new TextField();

        GridPane form =
                new GridPane();

        form.setPadding(
                new Insets(20));

        form.setHgap(10);
        form.setVgap(10);

        form.add(
                new Label("User ID"),
                0,
                0);

        form.add(
                userId,
                1,
                0);

        form.add(
                new Label("Username"),
                0,
                1);

        form.add(
                username,
                1,
                1);

        form.add(
                new Label("Password"),
                0,
                2);

        form.add(
                password,
                1,
                2);

        form.add(
                new Label("Customer ID"),
                0,
                3);

        form.add(
                customerId,
                1,
                3);

        form.add(
                new Label("Address"),
                0,
                4);

        form.add(
                address,
                1,
                4);

        form.add(
                new Label("Phone"),
                0,
                5);

        form.add(
                phone,
                1,
                5);

        form.add(
                new Label("Email"),
                0,
                6);

        form.add(
                email,
                1,
                6);

        form.add(
                new Label("Company Name"),
                0,
                7);

        form.add(
                companyName,
                1,
                7);

        form.add(
                new Label("Registration No"),
                0,
                8);

        form.add(
                registrationNo,
                1,
                8);

        form.add(
                new Label("Contact Person"),
                0,
                9);

        form.add(
                contactPerson,
                1,
                9);

        Button registerBtn =
                new Button("Register");

        Button clearBtn =
                new Button("Clear");

        Button backBtn =
                new Button("Back");

        registerBtn.setOnAction(e -> {

            if(userId.getText().isEmpty()
                    || username.getText().isEmpty()
                    || password.getText().isEmpty()
                    || customerId.getText().isEmpty()
                    || address.getText().isEmpty()
                    || phone.getText().isEmpty()
                    || email.getText().isEmpty()
                    || companyName.getText().isEmpty()
                    || registrationNo.getText().isEmpty()
                    || contactPerson.getText().isEmpty()) {

                Alert alert =
                        new Alert(
                                Alert.AlertType.ERROR);

                alert.setContentText(
                        "All fields are required.");

                alert.showAndWait();

                return;
            }

            Company company =
                    new Company(
                            userId.getText(),
                            username.getText(),
                            password.getText(),
                            customerId.getText(),
                            address.getText(),
                            phone.getText(),
                            email.getText(),
                            companyName.getText(),
                            registrationNo.getText(),
                            contactPerson.getText());

            bank.addUser(company);

            Alert alert =
                    new Alert(
                            Alert.AlertType.INFORMATION);

            alert.setContentText(
                    "Company registered successfully.");

            alert.showAndWait();
        });

        clearBtn.setOnAction(e -> {

            userId.clear();
            username.clear();
            password.clear();
            customerId.clear();
            address.clear();
            phone.clear();
            email.clear();
            companyName.clear();
            registrationNo.clear();
            contactPerson.clear();
        });

        backBtn.setOnAction(e ->
                new BankAdminView(bank)
                        .show(stage));

        HBox buttons =
                new HBox(
                        10,
                        registerBtn,
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
                new Scene(root,
                        700,
                        600);  scene.getStylesheets().add("bankingSystem/styles/styles.css");

        stage.setTitle(
                "Register Company");

        stage.setScene(scene);

        stage.show();
    }
}