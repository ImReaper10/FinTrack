package UI;

import database.TransactionDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Transactions;

import java.time.LocalDate;
import java.util.List;

public class FinTrackController {
    @FXML private TextField amountField;
    @FXML private TextField categoryField;
    @FXML private TextField dateField;
    @FXML private Label messageLabel;
    @FXML private TableView<Transactions> transactionsTable;
    @FXML private TableColumn<Transactions, String> dateColumn;
    @FXML private TableColumn<Transactions, String> categoryColumn;
    @FXML private TableColumn<Transactions, Double> amountColumn;
    @FXML private TableColumn<Transactions, String> typeColumn;

    private final int userId = 1; // Placeholder user ID

    @FXML
    private void initialize() {
        dateColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDate()));
        categoryColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCategory()));
        amountColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getAmount()));
        typeColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getType()));

        loadTransactions();
    }

    @FXML
    private void handleAddIncome() {
        addTransaction("income");
    }

    @FXML
    private void handleAddExpense() {
        addTransaction("expense");
    }

    private void addTransaction(String type) {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String category = categoryField.getText();
            String date = dateField.getText();

            if (category.isEmpty() || date.isEmpty()) {
                messageLabel.setText("Please fill all fields.");
                return;
            }

            Transactions transaction = new Transactions(userId, amount, category, date, type, "");
            TransactionDAO.addTransaction(transaction);

            messageLabel.setText("Transaction added successfully.");
            loadTransactions(); // Refresh table
            amountField.clear();
            categoryField.clear();
            dateField.clear();

        } catch (NumberFormatException e) {
            messageLabel.setText("Invalid amount.");
        }
    }

    private void loadTransactions() {
        List<Transactions> transactions = TransactionDAO.getTransactions(userId);
        ObservableList<Transactions> transactionList = FXCollections.observableArrayList(transactions);
        transactionsTable.setItems(transactionList);
    }
}
