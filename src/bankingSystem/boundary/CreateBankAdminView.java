package bankingSystem.boundary;

import bankingSystem.controller.SystemAdminController;
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

public class CreateBankAdminView {

    private Bank bank;
    private SystemAdminController systemAdminController;

    public CreateBankAdminView(Bank bank) {
        this.bank = bank;
        this.systemAdminController =
                new SystemAdminController(bank);
    }

    public void show(Stage stage) {

        Label heading =
                new Label("CREATE BANK ADMINISTRATOR");

        heading.setStyle(
                "-fx-font-size: 20px;" +
                        "-fx-font-weight: bold;"
        );

        TextField userIdField =
                new TextField();

        TextField usernameField =
                new TextField();

        PasswordField passwordField =
                new PasswordField();

        TextField adminIdField =
                new TextField();

        TextField nameField =
                new TextField();

        userIdField.setPromptText(
                "Enter User ID");

        usernameField.setPromptText(
                "Enter Username");

        passwordField.setPromptText(
                "Enter Password");

        adminIdField.setPromptText(
                "Enter Admin ID");

        nameField.setPromptText(
                "Enter Full Name");

        GridPane form =
                new GridPane();

        form.setPadding(
                new Insets(20));

        form.setHgap(10);
        form.setVgap(10);

        form.add(new Label("User ID"), 0, 0);
        form.add(userIdField, 1, 0);

        form.add(new Label("Username"), 0, 1);
        form.add(usernameField, 1, 1);

        form.add(new Label("Password"), 0, 2);
        form.add(passwordField, 1, 2);

        form.add(new Label("Admin ID"), 0, 3);
        form.add(adminIdField, 1, 3);

        form.add(new Label("Name"), 0, 4);
        form.add(nameField, 1, 4);

        Button saveBtn =
                new Button("Save");

        Button clearBtn =
                new Button("Clear");

        Button backBtn =
                new Button("Back");

        saveBtn.setOnAction(e -> {

            boolean created =
                    systemAdminController
                            .createBankAdministrator(
                                    userIdField.getText(),
                                    usernameField.getText(),
                                    passwordField.getText(),
                                    adminIdField.getText(),
                                    nameField.getText());

            if(!created) {

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
                    "Bank Administrator created successfully.");

            alert.showAndWait();

            userIdField.clear();
            usernameField.clear();
            passwordField.clear();
            adminIdField.clear();
            nameField.clear();
        });

        clearBtn.setOnAction(e -> {

            userIdField.clear();
            usernameField.clear();
            passwordField.clear();
            adminIdField.clear();
            nameField.clear();
        });

        backBtn.setOnAction(e ->
                new SystemAdminView(bank)
                        .show(stage));

        HBox buttons =
                new HBox(
                        10,
                        saveBtn,
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
                        650,
                        450);   scene.getStylesheets().add("bankingSystem/styles/styles.css");

        stage.setTitle(
                "Create Bank Administrator");

        stage.setScene(scene);

        stage.show();
    }
}