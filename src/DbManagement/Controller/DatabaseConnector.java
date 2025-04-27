package DbManagement.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {

    private static final String URL = "jdbc:sqlite:mobile_payment_system.db"; // database file

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
}

