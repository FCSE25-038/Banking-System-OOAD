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

public class CreateSystemAdminView {

    private Bank bank;
    private SystemAdminController systemAdminController;

    public CreateSystemAdminView(Bank bank) {
        this.bank = bank;
        this.systemAdminController =
                new SystemAdminController(bank);
    }

    public void show(Stage stage) {

        Label heading =
                new Label("CREATE SYSTEM ADMINISTRATOR");

        heading.setStyle(
                "-fx-font-size: 20px;" +
                        "-fx-font-weight: bold;");

        TextField userIdField =
                new TextField();

        TextField usernameField =
                new TextField();

        PasswordField passwordField =
                new PasswordField();

        TextField nameField =
                new TextField();

        userIdField.setPromptText(
                "Enter User ID");

        usernameField.setPromptText(
                "Enter Username");

        passwordField.setPromptText(
                "Enter Password");

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

        form.add(new Label("Name"), 0, 3);
        form.add(nameField, 1, 3);

        Button saveBtn =
                new Button("Save");

        Button clearBtn =
                new Button("Clear");

        Button backBtn =
                new Button("Back");

        saveBtn.setOnAction(e -> {

            boolean created =
                    systemAdminController
                            .createSystemAdministrator(
                                    userIdField.getText(),
                                    usernameField.getText(),
                                    passwordField.getText(),
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
                    "System Administrator created successfully.");

            alert.showAndWait();

            userIdField.clear();
            usernameField.clear();
            passwordField.clear();
            nameField.clear();
        });

        clearBtn.setOnAction(e -> {

            userIdField.clear();
            usernameField.clear();
            passwordField.clear();
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
                        600,
                        400); scene.getStylesheets().add("bankingSystem/styles/styles.css");

        stage.setTitle(
                "Create System Administrator");

        stage.setScene(scene);

        stage.show();
    }
}