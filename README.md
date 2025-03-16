# FinTrack - Personal Finance Tracker
## Overview
FinTrack is a JavaFX-based personal finance tracking application that allows users to manage their income and expenses efficiently. It provides a simple UI to add transactions, categorize them, and display them in a table. The application uses an SQLite database for persistent storage.

## Features Implemented
1. JavaFX UI
Built using JavaFX with a FXML-based UI layout.
Uses a StackPane and BorderPane layout for better alignment.
Includes:
Input fields for Amount, Category, and Date.
Buttons for adding Income and Expenses.
A table displaying transaction history.
2. Database Integration
Uses SQLite (finance_tracker.db) for transaction storage.
Implements a DAO pattern (TransactionDAO.java) to manage database operations.
Automatically creates the transactions table if it doesn’t exist.
3. Controller Logic (FinTrackController.java)
Handles adding income and expense transactions.
Validates user input before inserting data.
Updates the transaction table dynamically after adding new transactions.
4. Application Entry Point (FinTrackApp.java)
Loads the JavaFX UI (FinTrackUI.fxml).
Sets up the scene dimensions and centers the application on the screen.
Uses an FXML loader to manage UI components.
# Project Structure

/src
│── database/
│   ├── DatabaseHelper.java
│   ├── TransactionDAO.java
│   ├── UserDAO.java
│
│── models/
│   ├── Transactions.java
│   ├── User.java
│
│── UI/
│   ├── FinTrackApp.java
│   ├── FinTrackController.java
│   ├── FinTrackUI.fxml
│   ├── index.html (Placeholder for WebView integration)
│
│── finance_tracker.db (SQLite database)
│── FinTrack.iml
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
How to Run
Clone the Repository
sh
Copy code
git clone https://github.com/yourusername/FinTrack.git
cd FinTrack
Open in IntelliJ IDEA (Ensure JavaFX dependencies are installed).
Run FinTrackApp.java to start the application.
Technologies Used
Java 23 (OpenJDK)
JavaFX for UI
SQLite for database storage
IntelliJ IDEA for development
