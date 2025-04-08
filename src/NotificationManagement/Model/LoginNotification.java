package NotificationManagement.Model;

public class LoginNotification extends Notification {
    private String ipAddress;
    private String location;

    public LoginNotification(String userId, String ipAddress, String location) {
        super(userId);
        this.ipAddress = ipAddress;
        this.location = location;
    }

    @Override
    public String getContent() {
        return "Login from " + location + " (IP: " + ipAddress + ")";
    }

    // Getters
    public String getIpAddress() { return ipAddress; }
    public String getLocation() { return location; }
}
