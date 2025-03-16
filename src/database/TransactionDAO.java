package database;
import models.Transactions;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {
    private static final String URL = "jdbc:sqlite:finance_tracker.db";

    public static boolean addTransaction( Transactions transaction){
        String query = "INSERT INTO transactions (userId, amount, category, date, type, description) VALUES (?, ?, ?, ?, ?, ?)";
        try(Connection conn = DriverManager.getConnection(URL);
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, transaction.getUserId());
            stmt.setDouble(2, transaction.getAmount());
            stmt.setString(3, transaction.getCategory());
            stmt.setString(4, transaction.getDate());
            stmt.setString(5, transaction.getType());
            stmt.setString(6, transaction.getDescription());

            stmt.executeUpdate();
            System.out.println("Transaction added successfully!");
            return true;


        }catch (SQLException e){
            System.out.println("Error adding Transaction"+ e.getMessage());
            return false;
        }
    }
    public static List<Transactions> getTransactions(int userId){
        String query = "SELECT * FROM transactions WHERE user_id = ?";
        List<Transactions> transactions = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(URL);
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1,userId);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Transactions transactions1 = new Transactions(
                        rs.getInt("userId"), rs.getDouble("amount"), rs.getString("category"), rs.getString("date"), rs.getString("type"), rs.getString("description"));
                transactions1.setId(rs.getInt("id"));
                transactions.add(transactions1);


            }
        }catch (SQLException e){
            System.out.println("Error retrieving transactions"+ e.getMessage());

        }
        return transactions;
    }

}
