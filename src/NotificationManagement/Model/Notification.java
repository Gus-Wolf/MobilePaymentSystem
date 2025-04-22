package NotificationManagement.Model;

import java.time.LocalDateTime;

public class Notification {
    protected LocalDateTime time;
    protected String userId;

    public Notification(String userId) {
        this.time = LocalDateTime.now();
        this.userId = userId;
    }

    //Change
    public String getContent() {
        return userId + time.toString();
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getUserId() {
        return userId;
    }

}
