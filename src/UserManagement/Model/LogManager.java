package UserManagement.Model;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogManager {

    private static final String NOTIFICATION_LOG_FILE = StorageManager.getBaseDirectory() + "/logs/notifications/notification_log.txt";
    private static final String TRANSACTION_LOG_FILE = StorageManager.getBaseDirectory() + "/logs/transactions/transaction_log.txt";

    // Log a notification
    public static void logNotification(String notification) {
        logToFile(NOTIFICATION_LOG_FILE, notification);
    }

    // Log a transaction
    public static void logTransaction(String transaction) {
        logToFile(TRANSACTION_LOG_FILE, transaction);
    }

    // Sends created log to the proper directory
    private static void logToFile(String filepath, String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath, true))) {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            writer.write("[" + timestamp + "] " + message);
            writer.newLine();
            System.out.println("Log entry added: " + message);
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + filepath);
            e.printStackTrace();
        }
    }
}