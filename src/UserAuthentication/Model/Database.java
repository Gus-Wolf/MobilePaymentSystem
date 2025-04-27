package UserAuthentication.Model;

import java.sql.*;

public class Database {
    // JDBC connection details
    private static final String URL = "jdbc:mysql://localhost:3306/app_database"; // Your database URL
    private static final String USER = "root"; // Your MySQL username
    private static final String PASSWORD = "password"; // Your MySQL password

    // Load MySQL JDBC driver (if not already loaded by your JVM)
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Method to authenticate a user against the database
    public static boolean authenticate(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);  // For now, we're using plain text. Use hashed passwords in real apps.

            ResultSet rs = stmt.executeQuery();

            return rs.next();  // If a result is returned, the user is authenticated

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
