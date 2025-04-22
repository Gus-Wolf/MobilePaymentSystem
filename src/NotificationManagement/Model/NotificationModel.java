package NotificationManagement.Model;

import NotificationManagement.Model.Notification;
import NotificationManagement.NotificationManager;

import java.util.ArrayList;
import java.util.List;

public class NotificationModel {
    private Notification currentNotification;

    // Store the latest notification in memory
    public void setNotification(Notification notification) {
        this.currentNotification = notification;

        // Persist the notification to file
        NotificationManager.saveNotification(notification);
    }

    public Notification getNotification() {
        return currentNotification;
    }

    // Retrieve notification content
    public String getNotificationContent() {
        return currentNotification != null ? currentNotification.getContent() : "No notification set.";
    }

    // Retrieve all saved notifications
    public List<Notification> getNotificationHistory() {
        return NotificationManager.loadNotifications();
    }
}