package bankingSystem.boundary;

import bankingSystem.controller.BankAdminController;
import bankingSystem.model.Bank;
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

public class RegisterIndividualView {

    private Bank bank;
    private BankAdminController bankAdminController;

    public RegisterIndividualView(Bank bank) {
        this.bank = bank;
        this.bankAdminController = new BankAdminController(bank);
    }

    public void show(Stage stage) {

        Label heading =
                new Label(
                        "REGISTER INDIVIDUAL CUSTOMER");

        heading.setStyle(
                "-fx-font-size: 20px;"
                        + "-fx-font-weight: bold;");

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

        TextField firstName =
                new TextField();

        TextField surname =
                new TextField();

        TextField nationalId =
                new TextField();

        userId.setPromptText(
                "User ID");

        username.setPromptText(
                "Username");

        password.setPromptText(
                "Password");

        customerId.setPromptText(
                "Customer ID");

        address.setPromptText(
                "Address");

        phone.setPromptText(
                "Phone Number");

        email.setPromptText(
                "Email Address");

        firstName.setPromptText(
                "First Name");

        surname.setPromptText(
                "Surname");

        nationalId.setPromptText(
                "National ID");

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
                new Label("First Name"),
                0,
                7);

        form.add(
                firstName,
                1,
                7);

        form.add(
                new Label("Surname"),
                0,
                8);

        form.add(
                surname,
                1,
                8);

        form.add(
                new Label("National ID"),
                0,
                9);

        form.add(
                nationalId,
                1,
                9);

        Button registerBtn =
                new Button("Register");

        Button clearBtn =
                new Button("Clear");

        Button backBtn =
                new Button("Back");

        registerBtn.setOnAction(e -> {

            boolean success =
                    bankAdminController.registerIndividual(
                            userId.getText(),
                            username.getText(),
                            password.getText(),
                            customerId.getText(),
                            address.getText(),
                            phone.getText(),
                            email.getText(),
                            firstName.getText(),
                            surname.getText(),
                            nationalId.getText());

            if(!success) {

                Alert alert =
                        new Alert(
                                Alert.AlertType.ERROR);

                alert.setTitle(
                        "Validation Error");

                alert.setHeaderText(
                        null);

                alert.setContentText(
                        "All fields are required.");

                alert.showAndWait();

                return;
            }

            Alert alert =
                    new Alert(
                            Alert.AlertType.INFORMATION);

            alert.setTitle(
                    "Success");

            alert.setHeaderText(
                    null);

            alert.setContentText(
                    "Individual customer registered successfully.");

            alert.showAndWait();

            userId.clear();
            username.clear();
            password.clear();
            customerId.clear();
            address.clear();
            phone.clear();
            email.clear();
            firstName.clear();
            surname.clear();
            nationalId.clear();
        });

        clearBtn.setOnAction(e -> {

            userId.clear();
            username.clear();
            password.clear();
            customerId.clear();
            address.clear();
            phone.clear();
            email.clear();
            firstName.clear();
            surname.clear();
            nationalId.clear();
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
                new Scene(
                        root,
                        700,
                        600); scene.getStylesheets().add("bankingSystem/styles/styles.css");

        stage.setTitle(
                "Register Individual");

        stage.setScene(scene);

        stage.show();
    }
}