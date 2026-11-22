package bankingSystem.boundary;

import bankingSystem.controller.BankAdminController;
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

public class RemoveCustomerView {

    private Bank bank;
    private BankAdminController bankAdminController;

    public RemoveCustomerView(Bank bank) {
        this.bank = bank;
        this.bankAdminController = new BankAdminController(bank);
    }

    public void show(Stage stage) {

        Label heading =
                new Label(
                        "REMOVE CUSTOMER");

        heading.setStyle(
                "-fx-font-size: 20px;" +
                        "-fx-font-weight: bold;"
        );

        TextField customerIdField =
                new TextField();

        customerIdField.setPromptText(
                "Enter Customer ID");

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
                customerIdField,
                1,
                0);

        Button removeBtn =
                new Button("Remove");

        Button clearBtn =
                new Button("Clear");

        Button backBtn =
                new Button("Back");

        removeBtn.setOnAction(e -> {

            if(customerIdField
                    .getText()
                    .trim()
                    .isEmpty()) {

                Alert alert =
                        new Alert(
                                Alert.AlertType.ERROR);

                alert.setTitle(
                        "Validation Error");

                alert.setHeaderText(
                        null);

                alert.setContentText(
                        "Please enter a Customer ID.");

                alert.showAndWait();

                return;
            }

            boolean removed =
                    bankAdminController.removeCustomer(
                            customerIdField
                                    .getText()
                                    .trim());

            if(removed) {

                Alert alert =
                        new Alert(
                                Alert.AlertType.INFORMATION);

                alert.setTitle(
                        "Success");

                alert.setHeaderText(
                        null);

                alert.setContentText(
                        "Customer removed successfully.");

                alert.showAndWait();

                customerIdField.clear();

            } else {

                Alert alert =
                        new Alert(
                                Alert.AlertType.ERROR);

                alert.setTitle(
                        "Customer Not Found");

                alert.setHeaderText(
                        null);

                alert.setContentText(
                        "No customer exists with that ID.");

                alert.showAndWait();
            }
        });

        clearBtn.setOnAction(e ->
                customerIdField.clear());

        backBtn.setOnAction(e ->
                new BankAdminView(bank)
                        .show(stage));

        HBox buttons =
                new HBox(
                        10,
                        removeBtn,
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
                        550,
                        250); scene.getStylesheets().add("bankingSystem/styles/styles.css");

        stage.setTitle(
                "Remove Customer");

        stage.setScene(scene);

        stage.show();
    }
}