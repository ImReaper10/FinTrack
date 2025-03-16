import database.UserDAO;
import models.Transactions;
import models.User;
import java.util.List;
import java.util.Scanner;
import database.TransactionDAO;
import database.UserDAO;

public class Main {
    private static int loggedInUserId = -1;

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("\n=== FinTrack - User Authentication ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Add Transaction");
            System.out.println("4. View Transactions");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if(choice==1){
                System.out.print("Enter Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Email: ");
                String email = scanner.nextLine();
                System.out.print("Enter Password:");
                String password = scanner.nextLine();

                User newUser = new User(name,email,password);
                UserDAO.registerUser(newUser);
            } else if (choice==2) {
                System.out.print("Enter Email: ");
                String email = scanner.nextLine();
                System.out.print("Enter Password:");
                String password = scanner.nextLine();
                UserDAO.loginUser(email,password);

            } else if (choice == 3 && loggedInUserId != -1) {
                // Add Transaction
                System.out.print("Enter amount: ");
                double amount = scanner.nextDouble();
                scanner.nextLine();
                System.out.print("Enter category: ");
                String category = scanner.nextLine();
                System.out.print("Enter date (MM-DD-YYYY): ");
                String date = scanner.nextLine();
                System.out.print("Enter type (income/expense): ");
                String type = scanner.nextLine();
                System.out.print("Enter description: ");
                String description = scanner.nextLine();

                Transactions transaction = new Transactions(loggedInUserId, amount, category, date, type, description);
                TransactionDAO.addTransaction(transaction);

            } else if (choice == 4) {
                List<Transactions> transactions = TransactionDAO.getTransactions(loggedInUserId);
                transactions.forEach(t -> System.out.println(t.getCategory() + ": $" + t.getAmount()));
            }

            else if(choice ==5){
                System.out.println("Exiting");
                break;
            }
            else{
                System.out.println("Invalid Choice. Try Again");
            }
        }
        scanner.close();
    }
}
