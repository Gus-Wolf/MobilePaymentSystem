package NotificationManagement.Model;

import java.time.LocalDateTime;

public abstract class Notification {
    protected LocalDateTime time;
    protected String userId;

    public Notification(String userId) {
        this.time = LocalDateTime.now();
        this.userId = userId;
    }

    public abstract String getContent();

    public LocalDateTime getTime() {
        return time;
    }

    public String getUserId() {
        return userId;
    }

}
