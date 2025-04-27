package NotificationManagement.View;

import NotificationManagement.Model.Notification;

import javax.swing.*;
import java.util.List;

public class PersonalNotificationView implements NotificationViewInterface {
    private final DefaultListModel<String> notifications;

    public PersonalNotificationView(DefaultListModel<String> notifications) {
        this.notifications = notifications;
    }

    @Override
    public void showNotification(String content) {
        notifications.addElement("[PERSONAL NOTIFICATION] " + content);
    }

    @Override
    public void displayNotifications(List<Notification> notificationsList) {
        notifications.clear();
        for (Notification n : notificationsList) {
            notifications.addElement("[PERSONAL] " + n.getContent());
        }
    }
}