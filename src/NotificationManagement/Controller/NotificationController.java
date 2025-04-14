 package NotificationManagement.Controller;

import NotificationManagement.Model.*;
import NotificationManagement.View.NotificationView;
import java.util.ArrayList;
import java.util.List;

public class NotificationController {
    private final NotificationModel model = new NotificationModel() {

    };
    private final NotificationView view = new NotificationView();
    private final List<Notification> history = new ArrayList<>();

    public void sendPaymentNotification(String userId, int amount, boolean success, String sender) {
        Notification n = NotificationProduction.createNotification(
                NotificationProduction.NotificationType.PAYMENT,
                userId,
                amount,
                success,
                sender
        );
        updateSystem(n);
    }

    public void sendLoginNotification(String userId, String ip, String location) {
        Notification n = NotificationProduction.createNotification(
                NotificationProduction.NotificationType.LOGIN,
                userId,
                ip,
                location
        );
        updateSystem(n);
    }

    public void sendAuthFailureNotification(String userId, String ip, String location, String reason) {
        Notification n = NotificationProduction.createNotification(
                NotificationProduction.NotificationType.AUTH_FAILURE,
                userId,
                ip,
                location,
                reason
        );
        updateSystem(n);
    }
//Change
    private void updateSystem(Notification notification) {
        model.setNotification(notification);
        history.add(notification);
        view.showNotification(notification.getContent());
    }

    public void displayHistory() {
        view.displayNotifications(history);
    }

    public void setBusinessAccount(boolean isBusiness) {
        if(isBusiness) {
            view.switchToBusinessView();
        } else {
            view.switchToPersonalView();
        }
    }
}