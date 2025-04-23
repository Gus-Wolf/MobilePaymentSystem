package UserManagement.Model;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserCredentialsManager {

    private static final String CREDENTIALS_FILE = StorageManager.getBaseDirectory() + "/user-credentials/credentials.txt";

    // Save user credentials
    public static void saveCredentials(String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CREDENTIALS_FILE, true))) {
            writer.write(username + ":" + password);
            writer.newLine();
            System.out.println("User credentials saved for: " + username);
        } catch (IOException e) {
            System.err.println("Error saving user credentials.");
            e.printStackTrace();
        }
    }

    // Read all user credentials into a map
    public static Map<String, String> getAllCredentials() {
        Map<String, String> credentials = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CREDENTIALS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    credentials.put(parts[0], parts[1]); // username -> hashed password
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading user credentials.");
            e.printStackTrace();
        }
        return credentials;
    }

    // Validate credentials
    public static boolean validateCredentials(String username, String hashedPassword) {
        Map<String, String> credentials = getAllCredentials();
        return credentials.containsKey(username) && credentials.get(username).equals(hashedPassword);
    }
}