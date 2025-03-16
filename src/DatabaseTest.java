import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseTest {
    public static void main(String[] args){
        String url = "jdbc:sqlite:finance_tracker.db";

        try(Connection conn = DriverManager.getConnection(url)){
            if (conn!= null){

                System.out.println("Connected to SQLite Successfully");
            }
        } catch (SQLException e){
            System.out.println("Connection Failed:c"+ e.getMessage());
        }
    }
}
