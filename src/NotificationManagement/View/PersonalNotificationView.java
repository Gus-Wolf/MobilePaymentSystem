package NotificationManagement.View;

import NotificationManagement.Model.Notification;
import NotificationManagement.Model.PaymentNotification;
import java.util.List;

public class PersonalNotificationView implements NotificationViewInterface {
    @Override
    public void showNotification(String content) {
        System.out.println("[PERSONAL NOTIFICATION] " + content);
    }

    // Displays a colorful feed of user payments
    @Override
    public void displayNotifications(List<Notification> notifications) {
        System.out.println("\n=== SOCIAL FEED ===");
        System.out.println("Friends Tab | Payments Feed");
        notifications.forEach(n -> {
            if(n instanceof PaymentNotification) {
                PaymentNotification pn = (PaymentNotification) n;
                System.out.println(pn.getSenderId() + " sent $" + pn.getAmount() +
                        " - \uD83D\uDCAC Comment | New Like");
            } else {
                System.out.println(n.getContent());
            }
        });
    }
}
