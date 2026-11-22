package bankingSystem.boundary;

import bankingSystem.controller.SystemAdminController;
import bankingSystem.model.Bank;
import bankingSystem.model.User;
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

public class ViewUsersView {

    private Bank bank;
    private SystemAdminController systemAdminController;

    public ViewUsersView(Bank bank) {
        this.bank = bank;
        this.systemAdminController =
                new SystemAdminController(bank);
    }

    public void show(Stage stage) {

        Label heading =
                new Label("SYSTEM USERS");

        heading.setStyle(
                "-fx-font-size: 22px;" +
                        "-fx-font-weight: bold;"
        );

        TableView<User> table =
                new TableView<>();

        TableColumn<User, String> userIdColumn =
                new TableColumn<>("User ID");

        userIdColumn.setCellValueFactory(
                new PropertyValueFactory<>("userId"));

        userIdColumn.setPrefWidth(180);

        TableColumn<User, String> usernameColumn =
                new TableColumn<>("Username");

        usernameColumn.setCellValueFactory(
                new PropertyValueFactory<>("username"));

        usernameColumn.setPrefWidth(200);

        TableColumn<User, String> roleColumn =
                new TableColumn<>("Role");

        roleColumn.setCellValueFactory(
                new PropertyValueFactory<>("role"));

        roleColumn.setPrefWidth(250);

        table.getColumns().addAll(
                userIdColumn,
                usernameColumn,
                roleColumn
        );

        table.setItems(
                FXCollections.observableArrayList(
                        systemAdminController.getUsers()
                )
        );

        Button refreshBtn =
                new Button("Refresh");

        Button backBtn =
                new Button("Back");

        refreshBtn.setOnAction(e -> {

            table.setItems(
                    FXCollections.observableArrayList(
                            systemAdminController.getUsers()
                    )
            );
        });

        backBtn.setOnAction(e ->
                new SystemAdminView(bank)
                        .show(stage));

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
                        700,
                        500); scene.getStylesheets().add("bankingSystem/styles/styles.css");

        stage.setTitle(
                "View Users");

        stage.setScene(scene);

        stage.show();
    }
}