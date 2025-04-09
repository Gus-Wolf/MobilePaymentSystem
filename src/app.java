import NotificationManagement.Controller.NotificationController;
public class app {
    public static void main(String[] args) {
        NotificationController controller = new NotificationController();

        // Business scenario for Notification
        controller.setBusinessAccount(true);
        controller.sendPaymentNotification("biz-123", 5000, true, "client-456");
        controller.sendLoginNotification("biz-123", "192.168.1.1", "New York");
        controller.displayHistory();

        // Personal scenario for Notification
        controller.setBusinessAccount(false);
        controller.sendPaymentNotification("user-789", 50, true, "friend-001");
        controller.sendLoginNotification("user-789", "192.168.0.100", "Home");
        controller.displayHistory();

    }
}
