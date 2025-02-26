package NotificationManagement.Controller;

import java.util.ArrayList;
import java.util.List;

public class NotificationController {

    private List<String> notifications;

    public NotificationController() {
        this.notifications = new ArrayList<>();
    }

    public List<String> getNotifications() {
        return notifications;
    }

    public void addNotification(String notification) {
        this.notifications.add(notification);
    }
}
