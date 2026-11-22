package bankingSystem;

import bankingSystem.boundary.LoginView;
import bankingSystem.model.Bank;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        Bank bank =
                new Bank(
                        "Banking System");

        LoginView loginView =
                new LoginView(bank);

        loginView.show(stage);
    }

    public static void main(String[] args) {

        launch(args);
    }
}


