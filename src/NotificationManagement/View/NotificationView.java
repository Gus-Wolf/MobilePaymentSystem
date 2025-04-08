package NotificationManagement.View;

import NotificationManagement.Model.Notification;
import NotificationManagement.Model.PaymentNotification;

import java.util.List;

public class NotificationView {
    /**
     *
     * @param content is shown on the page for the user to see
     */
    private NotificationViewInterface viewStrategy;

    public NotificationView() {
        this.viewStrategy = new PersonalNotificationView();
    }



    public void switchToPersonalView() {
        this.viewStrategy = new PersonalNotificationView();
    }

    public void showNotification(String content) {
        viewStrategy.showNotification(content);
    }

    public void displayNotifications(List<Notification> notifications) {
        viewStrategy.displayNotifications(notifications);
    }
    public void showNotification(String content) {
        System.out.println("Stub: Showing notification - " + content);
    }


    public interface NotificationViewInterface {
        void showNotification(String content);
        void displayNotifications(List<Notification> notifications);
    }

    public class PersonalNotificationView implements NotificationViewInterface {
        @Override
        public void showNotification(String content) {
            System.out.println("[PERSONAL NOTIFICATION] " + content);
        }

        @Override
        public void displayNotifications(List<Notification> notifications) {
            System.out.println("\n=== SOCIAL FEED ===");
            System.out.println("Friends Tab | Payments Feed");
            notifications.forEach(n -> {
                if(n instanceof PaymentNotification) {
                    PaymentNotification pn = (PaymentNotification) n;
                    System.out.println(pn.getSenderId() + " sent $" + pn.getAmount() +
                            " - \uD83D\uDCAC Comment | ❤️ Like");
                } else {
                    System.out.println(n.getContent());
                }
            });
        }
    }

}
