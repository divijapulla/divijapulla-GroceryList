import Util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class GroceryMain {

    public static void main(String[] args) {
        GroceryDAO groceryDAO = new GroceryDAO();
        databaseSetup();

        boolean active = true;
        Scanner inputScanner = new Scanner(System.in);

        while (active) {
            System.out.println("\nWhat would you like to do?" +
                    "\n 1: Try to add a grocery item." +
                    "\n 2: See all the groceries." +
                    "\n 3: Quit");
            int inputSelection = inputScanner.nextInt();
            inputScanner.nextLine(); // consume newline

            switch (inputSelection) {
                case 1:
                    System.out.println("What grocery would you like to add?");
                    String grocery = inputScanner.nextLine();
                    groceryDAO.addGrocery(grocery);
                    break;
                case 2:
                    System.out.println("Here are all the groceries:");
                    System.out.println(groceryDAO.getAllGroceries());
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    active = false;
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }

        inputScanner.close();
    }

    public static void databaseSetup() {
        try {
            Connection conn = ConnectionUtil.getConnection();
            PreparedStatement ps1 = conn.prepareStatement("DROP TABLE IF EXISTS grocery");
            ps1.executeUpdate();
            PreparedStatement ps2 = conn.prepareStatement("CREATE TABLE grocery (grocery_name VARCHAR(255))");
            ps2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
