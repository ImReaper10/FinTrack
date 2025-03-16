package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {
    private static final String URL = "jdbc:sqlite:finance_tracker.db";

    public static Connection connect(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(URL);
            System.out.println("Connected to SQLite Database successfully");

        }catch(SQLException e){
            System.out.println("Database Connection Failed" + e.getMessage());

        }
        return conn;
    }

    public static void createTables() {
        String usersTable = "CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "email TEXT UNIQUE NOT NULL, " +
                "password TEXT NOT NULL" +
                ");";

        String transactionsTable = "CREATE TABLE IF NOT EXISTS transactions (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "user_id INTEGER NOT NULL, " +
                "amount REAL NOT NULL, " +
                "category TEXT NOT NULL, " +
                "date TEXT NOT NULL, " +
                "type TEXT CHECK (type IN ('income', 'expense')), " +
                "description TEXT, " +
                "FOREIGN KEY (user_id) REFERENCES users(id)" +
                ");";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(usersTable);
            stmt.execute(transactionsTable);
            System.out.println("✅ Database tables created successfully.");
        } catch (SQLException e) {
            System.out.println("❌ Error creating tables: " + e.getMessage());
        }
    }


    public static void main(String[] args){
        connect();
        createTables();
    }
}
