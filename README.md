# Banking Management System

## Student Information

**Name:** Junior Tsaone Makale

**Course:** Object-Oriented Analysis and Design (OOAD)

---

## Project Description

The Banking Management System is a desktop application developed using JavaFX and MySQL. The system allows banking operations to be managed through role-based access for System Administrators, Bank Administrators, and Customers.

The application follows Object-Oriented Programming principles and implements a layered architecture consisting of:

- Boundary Layer (User Interface)
- Controller Layer (Business Logic)
- Data Access Layer (DAO)
- Model Layer (Domain Objects)

---

## Features

### User Authentication
- Login using username and password
- Role-based access control

### System Administrator
- Create System Administrators
- Create Bank Administrators
- View Users

### Bank Administrator
- Register Individual Customers
- Register Company Customers
- View Customers
- Remove Customers

### Customer
- View Customer Details
- Open Savings Accounts
- Open Investment Accounts
- Open Cheque Accounts
- View Accounts
- Deposit Funds
- Withdraw Funds
- Transfer Funds
- View Transaction History
- Change Password

---

## Technologies Used

- Java 26
- JavaFX 25
- JDBC
- MySQL
- XAMPP
- phpMyAdmin
- IntelliJ IDEA

---

## Project Structure

```text
src
└── bankingSystem
    ├── boundary
    ├── controller
    ├── database
    ├── model
    ├── styles
    ├── Main.java
    └── InterestBearing.java
```

---

## Database Tables

The application uses the following database tables:

- users
- customers
- individual_customers
- company_customers
- accounts
- bank_transactions

---

## Main Classes

### Boundary Package
- LoginView
- SystemAdminView
- BankAdminView
- CustomerView
- ViewUsersView
- ViewCustomersView
- ViewAccountsView
- TransactionHistoryView

### Controllers
- LoginController
- SystemAdminController
- BankAdminController
- CustomerController
- AccountController
- TransactionController

### Database Layer
- UserDAO
- CustomerDAO
- AccountDAO
- TransactionDAO
- DatabaseConnection

### Models
- User
- Customer
- Company
- Individual
- Account
- SavingsAccount
- InvestmentAccount
- ChequeAccount
- Transaction
- BankAdministrator
- SystemAdministrator

---

## Software Requirements

- JDK 26
- JavaFX SDK 25
- MySQL Server
- MySQL Connector/J
- IntelliJ IDEA

---

## GitHub and Codespaces

This project is maintained using Git and hosted on GitHub. The repository can be opened in GitHub Codespaces for review, validation, and assessment.

---

## Author

Junior Tsaone Makale