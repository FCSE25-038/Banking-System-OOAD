package bankingSystem.boundary;

import bankingSystem.controller.TransactionController;
import bankingSystem.model.Account;
import bankingSystem.model.Transaction;
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

public class TransactionHistoryView {

    private Account account;
    private TransactionController transactionController;

    public TransactionHistoryView(Account account) {
        this.account = account;
        this.transactionController = new TransactionController();
    }

    public void show(Stage stage) {

        Label heading =
                new Label(
                        "TRANSACTION HISTORY");

        heading.setStyle(
                "-fx-font-size: 20px;" +
                        "-fx-font-weight: bold;"
        );

        TableView<Transaction> table =
                new TableView<>();

        TableColumn<Transaction, String> idColumn =
                new TableColumn<>(
                        "Transaction ID");

        idColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "transactionId"));

        idColumn.setPrefWidth(220);

        TableColumn<Transaction, String> typeColumn =
                new TableColumn<>(
                        "Type");

        typeColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "transactionType"));

        typeColumn.setPrefWidth(150);

        TableColumn<Transaction, Double> amountColumn =
                new TableColumn<>(
                        "Amount");

        amountColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "amount"));

        amountColumn.setPrefWidth(150);

        TableColumn<Transaction, Object> dateColumn =
                new TableColumn<>(
                        "Date");

        dateColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "transactionDate"));

        dateColumn.setPrefWidth(250);

        table.getColumns().addAll(
                idColumn,
                typeColumn,
                amountColumn,
                dateColumn);

        table.setItems(
                FXCollections.observableArrayList(
                        transactionController.getTransactions(
                                account)));

        Button refreshBtn =
                new Button(
                        "Refresh");

        Button backBtn =
                new Button(
                        "Back");

        refreshBtn.setOnAction(e -> {

            table.setItems(
                    FXCollections.observableArrayList(
                            transactionController.getTransactions(
                                    account)));
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

        root.setTop(heading);

        root.setCenter(table);

        root.setBottom(buttons);

        BorderPane.setAlignment(
                heading,
                Pos.CENTER);

        Scene scene =
                new Scene(
                        root,
                        850,
                        500);

        stage.setTitle(
                "Transaction History");

        stage.setScene(scene);

        stage.show();
    }
}