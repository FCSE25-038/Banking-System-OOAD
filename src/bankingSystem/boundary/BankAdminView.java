package bankingSystem.boundary;

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

public class BankAdminView {
    private Bank bank;
    public BankAdminView(Bank bank) {this.bank = bank;}

    public void show(Stage stage) {
        Label heading = new Label("BANK ADMINISTRATOR");
        heading.setStyle("-fx-font-size: 22px;" + "-fx-font-weight: bold;");
        Button registerIndividualBtn = new Button("Register Individual Customer");
        Button registerCompanyBtn = new Button("Register Company Customer");
        Button openSavingsBtn = new Button("Open Savings Account");
        Button openInvestmentBtn = new Button("Open Investment Account");
        Button openChequeBtn = new Button("Open Cheque Account");
        Button viewCustomersBtn = new Button("View Customers");
        Button removeCustomerBtn = new Button("Remove Customer");
        Button processInterestBtn = new Button("Process Interest");
        Button logoutBtn = new Button("Logout");

        registerIndividualBtn.setMaxWidth(Double.MAX_VALUE);
        registerCompanyBtn.setMaxWidth(Double.MAX_VALUE);

        openSavingsBtn.setMaxWidth(Double.MAX_VALUE);
        openInvestmentBtn.setMaxWidth(Double.MAX_VALUE);
        openChequeBtn.setMaxWidth(Double.MAX_VALUE);

        viewCustomersBtn.setMaxWidth(Double.MAX_VALUE);

        removeCustomerBtn.setMaxWidth(Double.MAX_VALUE);

        processInterestBtn.setMaxWidth(Double.MAX_VALUE);

        logoutBtn.setMaxWidth(Double.MAX_VALUE);

        registerIndividualBtn.setOnAction(e ->
                new RegisterIndividualView(bank)
                        .show(stage));

        registerCompanyBtn.setOnAction(e ->
                new RegisterCompanyView(bank)
                        .show(stage));

        openSavingsBtn.setOnAction(e ->
                new OpenSavingsView(bank)
                        .show(stage));

        openInvestmentBtn.setOnAction(e ->
                new OpenInvestmentView(bank)
                        .show(stage));

        openChequeBtn.setOnAction(e ->
                new OpenChequeView(bank)
                        .show(stage));

        viewCustomersBtn.setOnAction(e ->
                new ViewCustomersView(bank)
                        .show(stage));

        removeCustomerBtn.setOnAction(e ->
                new RemoveCustomerView(bank)
                        .show(stage));

        processInterestBtn.setOnAction(e -> {
            bank.processInterest();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Interest Processing");

            alert.setHeaderText(null);

            alert.setContentText("Interest processed successfully.");

            alert.showAndWait();
        });

        logoutBtn.setOnAction(e ->
                new LoginView(bank)
                        .show(stage));

        VBox menu = new VBox(12, registerIndividualBtn, registerCompanyBtn, openSavingsBtn, openInvestmentBtn, openChequeBtn, viewCustomersBtn, removeCustomerBtn, processInterestBtn, logoutBtn);

        menu.setSpacing(12);

        menu.setAlignment(Pos.CENTER);

        menu.setPadding(new Insets(20));

        BorderPane root = new BorderPane();

        root.setTop(heading);

        root.setCenter(menu);

        BorderPane.setAlignment(heading, Pos.CENTER);

        Scene scene = new Scene(root, 750, 600); scene.getStylesheets().add("bankingSystem/styles/styles.css");

        stage.setTitle("Bank Administrator");

        stage.setScene(scene);

        stage.show();
    }
}