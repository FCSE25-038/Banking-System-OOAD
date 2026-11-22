package bankingSystem.boundary;

import bankingSystem.controller.BankAdminController;
import bankingSystem.model.Bank;
import bankingSystem.model.Customer;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewCustomersView {

    private Bank bank;
    private BankAdminController bankAdminController;

    public ViewCustomersView(Bank bank) {
        this.bank = bank;
        this.bankAdminController = new BankAdminController(bank);
    }

    public void show(Stage stage) {

        Label heading =
                new Label("CUSTOMERS");

        heading.setStyle(
                "-fx-font-size: 20px;" +
                        "-fx-font-weight: bold;"
        );

        TableView<Customer> table =
                new TableView<>();

        TableColumn<Customer, String> idColumn =
                new TableColumn<>("Customer ID");

        idColumn.setCellValueFactory(
                new PropertyValueFactory<>("customerId"));

        idColumn.setPrefWidth(150);

        TableColumn<Customer, String> nameColumn =
                new TableColumn<>("Customer Name");

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("displayName"));

        nameColumn.setPrefWidth(250);

        TableColumn<Customer, String> emailColumn =
                new TableColumn<>("Email");

        emailColumn.setCellValueFactory(
                new PropertyValueFactory<>("email"));

        emailColumn.setPrefWidth(220);

        TableColumn<Customer, String> phoneColumn =
                new TableColumn<>("Phone Number");

        phoneColumn.setCellValueFactory(
                new PropertyValueFactory<>("phoneNumber"));

        phoneColumn.setPrefWidth(180);

        table.getColumns().addAll(
                idColumn,
                nameColumn,
                emailColumn,
                phoneColumn);

        table.setItems(
                FXCollections.observableArrayList(
                        bankAdminController.getCustomers()));

        Button refreshBtn =
                new Button("Refresh");

        Button backBtn =
                new Button("Back");

        refreshBtn.setOnAction(e -> {

            table.setItems(
                    FXCollections.observableArrayList(
                            bankAdminController.getCustomers()));
        });

        backBtn.setOnAction(e ->
                new BankAdminView(bank)
                        .show(stage));

        VBox buttons =
                new VBox(
                        10,
                        refreshBtn,
                        backBtn);

        buttons.setAlignment(
                Pos.CENTER);

        BorderPane root =
                new BorderPane();

        root.setPadding(
                new Insets(15));

        root.setTop(heading);
        root.setCenter(table);
        root.setRight(buttons);

        BorderPane.setAlignment(
                heading,
                Pos.CENTER);

        Scene scene =
                new Scene(
                        root,
                        900,
                        500); scene.getStylesheets().add("bankingSystem/styles/styles.css");

        stage.setTitle(
                "View Customers");

        stage.setScene(scene);
        stage.show();
    }
}