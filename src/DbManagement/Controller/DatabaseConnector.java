package DbManagement.Controller;

import java.sql.*;

import static javax.management.remote.JMXConnectorFactory.connect;

public class DatabaseConnector {

    private static final String URL = "jdbc:sqlite:C:/Users/zachc/Documents/GitHub/MobilePaymentSystem/mobile_payment_system.db";

    public static void main(String[] args) {
        createUsersTable();
        createBankAccountsTable();
    }

    public static void createUsersTable() {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "username TEXT NOT NULL," +
                    "email TEXT," +
                    "password TEXT NOT NULL" +
                    ");";
            stmt.execute(sql);
            System.out.println("✅ Table 'users' created or already exists.");

        } catch (SQLException e) {
            System.out.println("❌ Failed to create 'users' table!");
            e.printStackTrace();
        }
    }

    public static void createBankAccountsTable() {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS bank_accounts (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "username TEXT NOT NULL," +
                    "bank_name TEXT NOT NULL," +
                    "account_number TEXT NOT NULL," +
                    "routing_number TEXT NOT NULL" +
                    ");";
            stmt.execute(sql);
            System.out.println("✅ Table 'bank_accounts' created or already exists.");

        } catch (SQLException e) {
            System.out.println("❌ Failed to create 'bank_accounts' table!");
            e.printStackTrace();
        }
    }

    public static boolean authenticate(String username, String password) {
        // SQLite query to fetch user data based on the provided username and password
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set input values in the PreparedStatement
            pstmt.setString(1, username);  // Set username parameter
            pstmt.setString(2, password);  // Set password parameter

            // Execute the query and get the result set
            ResultSet rs = pstmt.executeQuery();

            // If there’s a record in the database matching the input username and password, authentication is successful
            if (rs.next()) {
                System.out.println("Authentication successful for user: " + username);
                return true;  // User authenticated successfully
            } else {
                System.out.println("Authentication failed for user: " + username);
                return false;  // Authentication failed
            }
        } catch (SQLException e) {
            System.out.println("Error during authentication: " + e.getMessage());
            return false;  // Return false in case of error
        }
    }

    private static Connection connect() {
        try {
            // Establish the connection to SQLite
            Connection connection = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");
            return connection;
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database: " + e.getMessage());
            return null;
        }
    }
}

