package NotificationManagement.View;

import NotificationManagement.Model.Notification;
import java.util.List;

public interface NotificationViewInterface {
    void showNotification(String content);
    void displayNotifications(List<Notification> notifications);
}