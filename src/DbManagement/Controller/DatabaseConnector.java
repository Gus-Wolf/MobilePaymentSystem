package DbManagement.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:mobile_payment_system.db"; // database file in your project folder

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("✅ Connected to SQLite database!");

                // Create tables if not exist
                try (Statement stmt = conn.createStatement()) {
                    String sql = "CREATE TABLE IF NOT EXISTS users (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "username TEXT NOT NULL," +
                            "email TEXT," +
                            "password TEXT NOT NULL" +
                            ");";
                    stmt.execute(sql);

                    System.out.println("✅ Table 'users' created or already exists.");
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Connection failed!");
            e.printStackTrace();//so I can push again
        }
    }
}


