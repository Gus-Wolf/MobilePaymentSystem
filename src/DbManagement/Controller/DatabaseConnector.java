
package DbManagement.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class DatabaseConnector {

    private static final String URL = "jdbc:sqlite:mobile_payment_system2.db"; // <-- make sure path is right!

    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("✅ SQLite JDBC driver loaded!");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Failed to load SQLite JDBC driver!");
            e.printStackTrace();
        }

        createUsersTable();
        createBankAccountsTable();
        createTransactionsTable();
        insertDefaultUsers();
    }


    public static void createUsersTable() {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "username TEXT NOT NULL UNIQUE," +
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

    public static void addBalanceColumnIfMissing() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:mobile_payment_system.db");
             Statement stmt = conn.createStatement()) {

            String sql = "ALTER TABLE bank_accounts ADD COLUMN balance REAL;";
            stmt.execute(sql);
            System.out.println("✅ 'balance' column added to 'bank_accounts' table.");

        } catch (SQLException e) {
            if (e.getMessage().contains("duplicate column name")) {
                System.out.println("⚠️ 'balance' column already exists.");
            } else {
                System.out.println("❌ Failed to add 'balance' column!");
                e.printStackTrace();
            }
        }
    }

    public static void createTransactionsTable() {
        String sql = "CREATE TABLE IF NOT EXISTS transactions (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "sender TEXT NOT NULL," +
                "receiver TEXT NOT NULL," +
                "amount REAL NOT NULL," +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("✅ Table 'transactions' created or already exists.");
        } catch (SQLException e) {
            System.out.println("❌ Failed to create 'transactions' table!");
            e.printStackTrace();
        }
    }

    public static void insertTransaction(String senderUsername, String receiverUsername, double amount) {
        String sql = "INSERT INTO transactions (sender, receiver, amount) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, senderUsername);
            pstmt.setString(2, receiverUsername);
            pstmt.setDouble(3, amount);

            pstmt.executeUpdate();
            System.out.println("✅ Transaction saved: " + senderUsername + " ➔ " + receiverUsername + " ($" + amount + ")");

        } catch (SQLException e) {
            System.out.println("❌ Error saving transaction: " + e.getMessage());
        }
    }



    public static void insertDefaultUsers() {
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT OR IGNORE INTO users (username, email, password) VALUES (?, ?, ?)")) {

            // Insert Gus
            pstmt.setString(1, "Gus");
            pstmt.setString(2, "gus@example.com");
            pstmt.setString(3, "1234");
            pstmt.executeUpdate();

            // Insert Juan
            pstmt.setString(1, "Juan");
            pstmt.setString(2, "juan@example.com");
            pstmt.setString(3, "1234");
            pstmt.executeUpdate();

            // Insert Zach
            pstmt.setString(1, "Zach");
            pstmt.setString(2, "zach@example.com");
            pstmt.setString(3, "1234");
            pstmt.executeUpdate();

            // Insert Ethan
            pstmt.setString(1, "Ethan");
            pstmt.setString(2, "ethan@example.com");
            pstmt.setString(3, "1234");
            pstmt.executeUpdate();

            System.out.println("✅ Default users inserted successfully.");

        } catch (SQLException e) {
            System.out.println("❌ Failed to insert default users!");
            e.printStackTrace();
        }
    }
}
