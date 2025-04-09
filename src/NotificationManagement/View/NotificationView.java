package NotificationManagement.View;

import NotificationManagement.Model.Notification;
import java.util.List;

public class NotificationView {
    private NotificationViewInterface viewStrategy;

    public NotificationView() {
        this.viewStrategy = new PersonalNotificationView();
    }

    public void switchToBusinessView() {
        this.viewStrategy = new BusinessNotificationView();
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
}
