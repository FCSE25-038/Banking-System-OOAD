package bankingSystem.boundary;

import bankingSystem.database.AccountDAO;
import bankingSystem.model.Account;
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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ViewAccountsView {

    private Customer customer;
    private AccountDAO accountDAO;

    public ViewAccountsView(Customer customer) {

        this.customer = customer;

        this.accountDAO =
                new AccountDAO();
    }

    public void show(Stage stage) {

        Label heading =
                new Label(
                        "CUSTOMER ACCOUNTS");

        heading.setStyle(
                "-fx-font-size: 20px;" +
                        "-fx-font-weight: bold;"
        );

        TableView<Account> table =
                new TableView<>();

        TableColumn<Account,String> accountNoColumn =
                new TableColumn<>(
                        "Account Number");

        accountNoColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "accountNumber"));

        accountNoColumn.setPrefWidth(
                200);

        TableColumn<Account,String> branchColumn =
                new TableColumn<>(
                        "Branch");

        branchColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "branch"));

        branchColumn.setPrefWidth(
                200);

        TableColumn<Account,Double> balanceColumn =
                new TableColumn<>(
                        "Balance");

        balanceColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "balance"));

        balanceColumn.setPrefWidth(
                200);

        table.getColumns().addAll(
                accountNoColumn,
                branchColumn,
                balanceColumn);

        table.setItems(
                FXCollections.observableArrayList(
                        accountDAO.getCustomerAccounts(
                                customer)));

        Button refreshBtn =
                new Button(
                        "Refresh");

        Button backBtn =
                new Button(
                        "Back");

        refreshBtn.setOnAction(e -> {

            table.setItems(
                    FXCollections.observableArrayList(
                            accountDAO.getCustomerAccounts(
                                    customer)));
        });

        backBtn.setOnAction(e ->
                stage.close());

        HBox buttons =
                new HBox(
                        10,
                        refreshBtn,
                        backBtn);

        buttons.setAlignment(
                Pos.CENTER);

        BorderPane root =
                new BorderPane();

        root.setPadding(
                new Insets(15));

        root.setTop(
                heading);

        root.setCenter(
                table);

        root.setBottom(
                buttons);

        BorderPane.setAlignment(
                heading,
                Pos.CENTER);

        Scene scene =
                new Scene(
                        root,
                        700,
                        500); scene.getStylesheets().add("bankingSystem/styles/styles.css");

        stage.setTitle(
                "View Accounts");

        stage.setScene(
                scene);

        stage.show();
    }
}