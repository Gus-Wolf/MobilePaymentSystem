package NotificationManagement;

import NotificationManagement.Model.Notification;
import NotificationManagement.Model.LoginNotification;
import NotificationManagement.Model.PaymentNotification;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationManager {

    private static final String NOTIFICATION_FILE_PATH = "data/notifications.txt";

    // Save a notification to the file
    public static void saveNotification(Notification notification) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOTIFICATION_FILE_PATH, true))) {
            writer.write(serialize(notification));
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error saving notification: " + e.getMessage());
        }
    }

    // Load all notifications from the file
    public static List<Notification> loadNotifications() {
        List<Notification> notifications = new ArrayList<>();

        File file = new File(NOTIFICATION_FILE_PATH);
        if (!file.exists()) {
            return notifications;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                notifications.add(deserialize(line));
            }
        } catch (IOException e) {
            System.err.println("Error loading notifications: " + e.getMessage());
        }
        return notifications;
    }

    // Convert a Notification object into a savable string format
    private static String serialize(Notification notification) {
        if (notification instanceof LoginNotification) {
            LoginNotification ln = (LoginNotification) notification;
            return "LOGIN," + ln.getUserId() + "," + ln.getIpAddress() + "," + ln.getLocation();
        } else if (notification instanceof PaymentNotification) {
            PaymentNotification pn = (PaymentNotification) notification;
            return "PAYMENT," + pn.getUserId() + "," + pn.getAmount() + "," + pn.getSenderId();
        }
        return "UNKNOWN," + notification.getUserId() + "," + notification.getContent();
    }

    // Convert a serialized string into a Notification object
    private static Notification deserialize(String data) {
        String[] parts = data.split(",");
        switch (parts[0]) {
            case "LOGIN":
                return new LoginNotification(parts[1], parts[2], parts[3]);
            case "PAYMENT":
                return new PaymentNotification(parts[1], Integer.parseInt(parts[2]),
                        Boolean.parseBoolean(parts[3]), parts[4]);
            default:
                return new Notification(parts[1]);
        }
    }
}