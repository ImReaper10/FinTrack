package database;

import models.User;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;

public class UserDAO {
    private static final String URL = "jdbc:sqlite:finance_tracker.db"; // SQLite file

    // Register a new user
    public static boolean registerUser(User user) {
        String checkUserQuery = "SELECT id FROM users WHERE email = ?";
        String insertUserQuery = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement checkStmt = conn.prepareStatement(checkUserQuery);
             PreparedStatement insertStmt = conn.prepareStatement(insertUserQuery)) {

            // Check if the user already exists
            checkStmt.setString(1, user.getEmail());
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                System.out.println("User already exists! Please log in.");
                return false;
            }

            // Hash the password before storing it
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

            // Insert new user
            insertStmt.setString(1, user.getName());
            insertStmt.setString(2, user.getEmail());
            insertStmt.setString(3, hashedPassword);
            insertStmt.executeUpdate();
            System.out.println("User registered successfully!");
            return true;

        } catch (SQLException e) {
            System.out.println("Error registering user: " + e.getMessage());
            return false;
        }
    }

    // Login a user
    public static boolean loginUser(String email, String password) {
        String query = "SELECT password FROM users WHERE email = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");

                // Verify the hashed password
                if (BCrypt.checkpw(password, storedPassword)) {
                    System.out.println("Login successful!");
                    return true;
                } else {
                    System.out.println("Incorrect password.");
                    return false;
                }
            } else {
                System.out.println(" User not found.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("❌ Error logging in: " + e.getMessage());
            return false;
        }
    }
}
