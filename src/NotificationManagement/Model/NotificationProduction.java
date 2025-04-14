package NotificationManagement.Model;

public class NotificationProduction {
    public enum NotificationType {
        PAYMENT, LOGIN, AUTH_FAILURE
    }

    public static Notification createNotification(NotificationType type, String userId, Object... params) {
        switch(type) {
            case PAYMENT:
                return new PaymentNotification(
                        userId,
                        (int) params[0],
                        (boolean) params[1],
                        (String) params[2]
                );
            case LOGIN:
                return new LoginNotification(
                        userId,
                        (String) params[0],
                        (String) params[1]
                );
            default:
                throw new IllegalArgumentException("Invalid notification type");
        }
    }
}