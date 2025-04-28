package UserAuthentication.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {

    private static final String URL = "jdbc:sqlite:mobile_payment_system.db"; // Adjust path if needed!

    public static boolean authenticate(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // User exists with matching password
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // User not found or DB error
        return false;
    }
}
