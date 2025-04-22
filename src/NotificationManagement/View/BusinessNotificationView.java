package NotificationManagement.View;

import NotificationManagement.Model.Notification;
import NotificationManagement.Model.PaymentNotification;

import javax.swing.*;
import java.util.List;

public class BusinessNotificationView implements NotificationViewInterface {
    private final DefaultListModel<String> notifications;

    public BusinessNotificationView(DefaultListModel<String> notifications) {
        this.notifications = notifications;
    }

    @Override
    public void showNotification(String content) {
        notifications.addElement("[BUSINESS ALERT] " + content);
    }

    @Override
    public void displayNotifications(List<Notification> notificationsList) {
        notifications.clear();
        for (int i = 0; i < notificationsList.size(); i++) {
            Notification n = notificationsList.get(i);
            String color = (i % 2 == 0) ? "WHITE" : "LIGHT_GRAY";
            if (n instanceof PaymentNotification) {
                notifications.addElement("[PAYMENT] " + n.getContent() + " (" + color + " ROW)");
            } else {
                notifications.addElement(n.getContent() + " (" + color + " ROW)");
            }
        }
    }
}