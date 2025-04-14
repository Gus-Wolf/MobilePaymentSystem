package NotificationManagement.Model;

public class NotificationModel {
    private Notification currentNotification;

    public void setNotification(Notification notification) {
        this.currentNotification = notification;
    }

    public Notification getNotification() {
        return currentNotification;
    }

    public String getNotificationContent() {
        return currentNotification != null ? currentNotification.getContent() : "No notification set.";
    }
}
