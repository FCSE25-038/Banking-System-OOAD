package bankingSystem.boundary;

import bankingSystem.controller.LoginController;
import bankingSystem.model.Bank;
import bankingSystem.model.BankAdministrator;
import bankingSystem.model.Customer;
import bankingSystem.model.SystemAdministrator;
import bankingSystem.model.User;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView {

    private Bank bank;
    private LoginController loginController;

    public LoginView(Bank bank) {
        this.bank = bank;
        this.loginController = new LoginController(bank);
    }

    public void show(Stage stage) {

        Label title = new Label("BANKING MANAGEMENT SYSTEM");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginBtn = new Button("Login");
        Button exitBtn = new Button("Exit");

        loginBtn.setOnAction(e -> {

            String username = usernameField.getText();
            String password = passwordField.getText();

            User user =
                    loginController.login(
                            username,
                            password);

            if(user != null) {

                if(user instanceof SystemAdministrator) {

                    new SystemAdminView(bank)
                            .show(stage);

                } else if(user instanceof BankAdministrator) {

                    new BankAdminView(bank)
                            .show(stage);

                } else if(user instanceof Customer) {

                    new CustomerView(bank)
                            .show(stage);
                }

            } else {

                Alert alert =
                        new Alert(
                                Alert.AlertType.ERROR);

                alert.setTitle(
                        "Login Failed");

                alert.setHeaderText(
                        null);

                alert.setContentText(
                        "Invalid username or password.");

                alert.showAndWait();
            }
        });

        exitBtn.setOnAction(e ->
                stage.close());

        VBox root = new VBox(
                15,
                title,
                usernameField,
                passwordField,
                loginBtn,
                exitBtn);

        root.setAlignment(
                Pos.CENTER);

        Scene scene = new Scene(
                root,
                500,
                400); scene.getStylesheets().add("bankingSystem/styles/styles.css");

        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
}