
import NotificationManagement.Controller.NotificationController;
import PaymentManagement.Controller.PaymentManagementController;
import TransactionManagement.Controller.TransactionManagementController;
import UserAuthentication.UserLogin;
import UserManagement.Controller.UserController;

public class TestHarness {
    public static void main(String[] args) {
        System.out.println("Running Test Harness...");

        // Test Notification System
        NotificationController notificationController = new NotificationController();
        notificationController.sendNotification(1, "Test Notification");
        notificationController.displayNotification();

        // Test Payment System
        PaymentManagementController paymentController = new PaymentManagementController();
        boolean paymentSuccess = paymentController.processPayment("Alice", "Bob", 100.00);
        System.out.println("Payment Test Result: " + paymentSuccess);

        // Test Transaction System
        TransactionManagementController transactionController = new TransactionManagementController();
        transactionController.initiateTransaction(1001);

        // Test User Authentication
        UserLogin userLogin = new UserLogin();
        boolean authSuccess = userLogin.authenticate("user123", "password");
        System.out.println("Authentication Test Result: " + authSuccess);

        // Test User Registration
        UserController userController = new UserController();
        userController.registerUser("newUser", "newUser@example.com");

        System.out.println("Test Harness Execution Completed.");
    }
}

