//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

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
        String serializedBasic = (String)serializeMethod.invoke((Object)null, basicNotification);
        String serializedLogin = (String)serializeMethod.invoke((Object)null, loginNotification);
        String serializedPayment = (String)serializeMethod.invoke((Object)null, paymentNotification);
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
}
