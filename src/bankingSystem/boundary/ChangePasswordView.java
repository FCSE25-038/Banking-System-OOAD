package bankingSystem.boundary;

import bankingSystem.controller.CustomerController;
import bankingSystem.model.Bank;
import bankingSystem.model.Customer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ChangePasswordView {

    private Customer customer;
    private CustomerController customerController;

    public ChangePasswordView(Customer customer, Bank bank) {
        this.customer = customer;
        this.customerController = new CustomerController(bank);
    }

    public void show(Stage stage) {

        Label heading =
                new Label(
                        "CHANGE PASSWORD");

        heading.setStyle(
                "-fx-font-size: 20px;" +
                        "-fx-font-weight: bold;"
        );

        PasswordField currentPasswordField =
                new PasswordField();

        PasswordField newPasswordField =
                new PasswordField();

        PasswordField confirmPasswordField =
                new PasswordField();

        currentPasswordField.setPromptText(
                "Current Password");

        newPasswordField.setPromptText(
                "New Password");

        confirmPasswordField.setPromptText(
                "Confirm New Password");

        GridPane form =
                new GridPane();

        form.setPadding(
                new Insets(20));

        form.setHgap(10);
        form.setVgap(10);

        form.add(
                new Label("Current Password"),
                0,
                0);

        form.add(
                currentPasswordField,
                1,
                0);

        form.add(
                new Label("New Password"),
                0,
                1);

        form.add(
                newPasswordField,
                1,
                1);

        form.add(
                new Label("Confirm Password"),
                0,
                2);

        form.add(
                confirmPasswordField,
                1,
                2);

        Button changeBtn =
                new Button(
                        "Change Password");

        Button clearBtn =
                new Button(
                        "Clear");

        Button backBtn =
                new Button(
                        "Back");

        changeBtn.setOnAction(e -> {

            if(currentPasswordField.getText().isEmpty()
                    || newPasswordField.getText().isEmpty()
                    || confirmPasswordField.getText().isEmpty()) {

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

            boolean changed =
                    customerController.changePassword(
                            customer,
                            currentPasswordField.getText(),
                            newPasswordField.getText(),
                            confirmPasswordField.getText());

            if(!changed) {

                Alert alert =
                        new Alert(
                                Alert.AlertType.ERROR);

                alert.setTitle(
                        "Password Error");

                alert.setHeaderText(
                        null);

                alert.setContentText(
                        "Current password is incorrect or passwords do not match.");

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
                    "Password changed successfully.");

            alert.showAndWait();

            currentPasswordField.clear();
            newPasswordField.clear();
            confirmPasswordField.clear();
        });

        clearBtn.setOnAction(e -> {

            currentPasswordField.clear();
            newPasswordField.clear();
            confirmPasswordField.clear();
        });

        backBtn.setOnAction(e ->
                stage.close());

        HBox buttons =
                new HBox(
                        10,
                        changeBtn,
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
                        350);     scene.getStylesheets().add("bankingSystem/styles/styles.css");

        stage.setTitle(
                "Change Password");

        stage.setScene(scene);

        stage.show();
    }
}