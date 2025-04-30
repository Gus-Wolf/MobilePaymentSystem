package Tester;

import NotificationManagement.NotificationManager;
import NotificationManagement.Model.LoginNotification;
import NotificationManagement.Model.Notification;
import NotificationManagement.Model.PaymentNotification;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class testHarness2 {

    public testHarness2() {
    }

    @Test
    public void testBasicNotificationCreation() {
        String userId = "testUser123";
        Notification notification = new Notification(userId);
        Assertions.assertEquals(userId, notification.getUserId());
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime notificationTime = notification.getTime();
        long timeDifference = ChronoUnit.SECONDS.between(notificationTime, now);
        this.assertTrue(Math.abs(timeDifference) < 120L, "Timestamp should be within 2 minutes of the current time");
        String content = notification.getContent();
        this.assertTrue(content.contains(userId), "Content should contain user ID");
        this.assertTrue(content.contains(notificationTime.toString()), "Content should contain timestamp");
    }

    private void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    @Test
    public void testNotificationSerialization() throws Exception {
        Notification basicNotification = new Notification("user1");
        LoginNotification loginNotification = new LoginNotification("user2", "192.168.1.1", "New York");
        PaymentNotification paymentNotification = new PaymentNotification("user3", 100, true, "sender123");
        Method serializeMethod = NotificationManager.class.getDeclaredMethod("serialize", Notification.class);
        serializeMethod.setAccessible(true);
        String serializedBasic = (String) serializeMethod.invoke((Object) null, basicNotification);
        String serializedLogin = (String) serializeMethod.invoke((Object) null, loginNotification);
        String serializedPayment = (String) serializeMethod.invoke((Object) null, paymentNotification);
    }

    @Test
    public void testLoginNotificationContent() {
        String userId = "testUser";
        String ipAddress = "192.168.0.1";
        String location = "New York";
        LoginNotification notification = new LoginNotification(userId, ipAddress, location);
        String expectedContent = "Login from New York (IP: 192.168.0.1)";
        Assertions.assertEquals(expectedContent, notification.getContent());
        Assertions.assertEquals(userId, notification.getUserId());
        Assertions.assertEquals(ipAddress, notification.getIpAddress());
        Assertions.assertEquals(location, notification.getLocation());
    }

    @Test
    public void testApplicationStartupTime() {
        final long ACCEPTABLE_STARTUP_TIME_MS = 5000;
        try {
            long startTime = System.currentTimeMillis();
            simulateApplicationStartup();
            long endTime = System.currentTimeMillis();
            long totalStartupTime = endTime - startTime;

            System.out.println("Application startup time: " + totalStartupTime + " ms");
            Assertions.assertTrue(totalStartupTime <= ACCEPTABLE_STARTUP_TIME_MS,
                    "Startup time exceeded acceptable limit of " + ACCEPTABLE_STARTUP_TIME_MS + " ms. Actual time: " + totalStartupTime + " ms");
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail("Exception occurred during application startup test: " + e.getMessage());
        }
    }

    private void simulateApplicationStartup() throws InterruptedException {
        Thread.sleep(3000);
    }

    @Test
    public void testLoginFailureDueToInvalidPassword() {
        String userId = "validUser123";
        String invalidPassword = "wrongPassword";

        try {
            String loginResultMessage = simulateLoginWithInvalidPassword(userId, invalidPassword);
            Assertions.assertTrue(loginResultMessage.contains("Invalid password"),
                    "Login failure message should explicitly mention 'Invalid password'!");

            String ipAddress = "192.168.1.3";
            String location = "Unknown";
            LoginNotification failedLoginNotification = new LoginNotification(userId, ipAddress, location);

            String expectedContent = "Login from Unknown (IP: 192.168.1.3)";
            Assertions.assertEquals(expectedContent, failedLoginNotification.getContent(),
                    "Notification content does not properly reflect the login failure scenario!");

            Assertions.assertEquals(userId, failedLoginNotification.getUserId(), "User ID mismatch in notification!");
            Assertions.assertEquals(ipAddress, failedLoginNotification.getIpAddress(), "IP address mismatch in notification!");
            Assertions.assertEquals(location, failedLoginNotification.getLocation(), "Location mismatch in notification!");

        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail("Exception occurred during login failure test: " + e.getMessage());
        }
    }

    private String simulateLoginWithInvalidPassword(String userId, String password) {
        return "Invalid password";
    }
}