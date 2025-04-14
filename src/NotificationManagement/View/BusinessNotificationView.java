package NotificationManagement.View;

import NotificationManagement.Model.Notification;
import NotificationManagement.Model.PaymentNotification;
import java.util.List;

public class BusinessNotificationView implements NotificationViewInterface {
    @Override
    public void showNotification(String content) {
        System.out.println("[BUSINESS ALERT] " + content);
    }

    // Displays a colorful dashboard of notifications
    @Override
    public void displayNotifications(List<Notification> notifications) {
        System.out.println("\n=== BUSINESS DASHBOARD ===");
        for(int i = 0; i < notifications.size(); i++) {
            Notification n = notifications.get(i);
            String color = (i % 2 == 0) ? "WHITE" : "LIGHT_GRAY";

            if(n instanceof PaymentNotification) {
                System.out.println("\u001B[34m" + n.getContent() + " (" + color + " ROW)\u001B[0m");
            } else {
                System.out.println(n.getContent() + " (" + color + " ROW)");
            }
        }
    }
}