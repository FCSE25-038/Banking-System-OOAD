package bankingSystem.boundary;

import bankingSystem.controller.SystemAdminController;
import bankingSystem.model.Bank;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SystemAdminView {

    private Bank bank;
    private SystemAdminController systemAdminController;

    public SystemAdminView(Bank bank) {
        this.bank = bank;
        this.systemAdminController = new SystemAdminController(bank);
    }

    public void show(Stage stage) {

        Label heading =
                new Label(
                        "SYSTEM ADMINISTRATOR");

        heading.setStyle(
                "-fx-font-size: 22px;" +
                        "-fx-font-weight: bold;"
        );

        Button createSystemAdminBtn =
                new Button(
                        "Create System Administrator");

        Button createBankAdminBtn =
                new Button(
                        "Create Bank Administrator");

        Button viewUsersBtn =
                new Button(
                        "View Users");

        Button backupBtn =
                new Button(
                        "Backup Data");

        Button restoreBtn =
                new Button(
                        "Restore Data");

        Button logoutBtn =
                new Button(
                        "Logout");

        createSystemAdminBtn.setMaxWidth(
                Double.MAX_VALUE);

        createBankAdminBtn.setMaxWidth(
                Double.MAX_VALUE);

        viewUsersBtn.setMaxWidth(
                Double.MAX_VALUE);

        backupBtn.setMaxWidth(
                Double.MAX_VALUE);

        restoreBtn.setMaxWidth(
                Double.MAX_VALUE);

        logoutBtn.setMaxWidth(
                Double.MAX_VALUE);

        createSystemAdminBtn.setOnAction(e ->
                new CreateSystemAdminView(bank)
                        .show(stage));

        createBankAdminBtn.setOnAction(e ->
                new CreateBankAdminView(bank)
                        .show(stage));

        viewUsersBtn.setOnAction(e ->
                new ViewUsersView(bank)
                        .show(stage));

        backupBtn.setOnAction(e -> {

            boolean success =
                    systemAdminController.backupData();

            Alert alert =
                    new Alert(
                            success
                                    ? Alert.AlertType.INFORMATION
                                    : Alert.AlertType.ERROR);

            alert.setTitle(
                    "Backup");

            alert.setHeaderText(null);

            alert.setContentText(
                    success
                            ? "Backup completed successfully."
                            : "Backup failed.");

            alert.showAndWait();
        });

        restoreBtn.setOnAction(e -> {

            boolean success =
                    systemAdminController.restoreData();

            Alert alert =
                    new Alert(
                            success
                                    ? Alert.AlertType.INFORMATION
                                    : Alert.AlertType.ERROR);

            alert.setTitle(
                    "Restore");

            alert.setHeaderText(null);

            alert.setContentText(
                    success
                            ? "Restore completed successfully."
                            : "Restore failed.");

            alert.showAndWait();
        });

        logoutBtn.setOnAction(e ->
                new LoginView(bank)
                        .show(stage));

        VBox menu =
                new VBox(
                        15,
                        createSystemAdminBtn,
                        createBankAdminBtn,
                        viewUsersBtn,
                        backupBtn,
                        restoreBtn,
                        logoutBtn);

        menu.setAlignment(
                Pos.CENTER);

        menu.setPadding(
                new Insets(20));

        BorderPane root =
                new BorderPane();

        root.setTop(heading);

        root.setCenter(menu);

        BorderPane.setAlignment(
                heading,
                Pos.CENTER);

        Scene scene =
                new Scene(
                        root,
                        650,
                        500);   scene.getStylesheets().add("bankingSystem/styles/styles.css");

        stage.setTitle(
                "System Administrator");

        stage.setScene(scene);

        stage.show();
    }
}