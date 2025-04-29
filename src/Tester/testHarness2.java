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
    public void testNotificationSerializationDeserialization() throws Exception {
        Notification basicNotification = new Notification("user1");
        LoginNotification loginNotification = new LoginNotification("user2", "192.168.1.1", "New York");
        PaymentNotification paymentNotification = new PaymentNotification("user3", 100, true, "sender123");
        Method serializeMethod = NotificationManager.class.getDeclaredMethod("serialize", Notification.class);
        Method deserializeMethod = NotificationManager.class.getDeclaredMethod("deserialize", String.class);
        serializeMethod.setAccessible(true);
        deserializeMethod.setAccessible(true);
        String serializedBasic = (String)serializeMethod.invoke((Object)null, basicNotification);
        Notification deserializedBasic = (Notification)deserializeMethod.invoke((Object)null, serializedBasic);
        Assertions.assertEquals(basicNotification.getUserId(), deserializedBasic.getUserId());
        Assertions.assertEquals(basicNotification.getTime(), deserializedBasic.getTime());
        Assertions.assertEquals(basicNotification.getContent(), deserializedBasic.getContent());
        String serializedLogin = (String)serializeMethod.invoke((Object)null, loginNotification);
        LoginNotification deserializedLogin = (LoginNotification)deserializeMethod.invoke((Object)null, serializedLogin);
        Assertions.assertEquals(loginNotification.getUserId(), deserializedLogin.getUserId());
        Assertions.assertEquals(loginNotification.getIpAddress(), deserializedLogin.getIpAddress());
        Assertions.assertEquals(loginNotification.getLocation(), deserializedLogin.getLocation());
        Assertions.assertEquals(loginNotification.getContent(), deserializedLogin.getContent());
        String serializedPayment = (String)serializeMethod.invoke((Object)null, paymentNotification);
        PaymentNotification deserializedPayment = (PaymentNotification)deserializeMethod.invoke((Object)null, serializedPayment);
        Assertions.assertEquals(paymentNotification.getUserId(), deserializedPayment.getUserId());
        Assertions.assertEquals(paymentNotification.getAmount(), deserializedPayment.getAmount());
        Assertions.assertEquals(paymentNotification.isPaymentSuccess(), deserializedPayment.isPaymentSuccess());
        Assertions.assertEquals(paymentNotification.getSenderId(), deserializedPayment.getSenderId());
        Assertions.assertEquals(paymentNotification.getContent(), deserializedPayment.getContent());
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
