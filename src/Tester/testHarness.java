/* package Tester;

import NotificationManagement.Controller.NotificationController;
import PaymentManagement.Controller.PaymentManagementController;
import TransactionMangement.Controller.TransactionManagementController;
import UserAuthentication.Model.UserLogin;
import UserManagement.Controller.UserController;

public class testHarness {
    public static void main(String[] args) {
        System.out.println("Running Test Harness...");

        // Test Notification System
        NotificationController notificationController = new NotificationController();
        //notificationController.sendNotification(1, "Test Notification");
        //notificationController.displayNotification();

        // Test Payment System
        PaymentManagementController paymentController = new PaymentManagementController();
        boolean paymentSuccess = paymentController.processPayment("Alice", "Bob", 100.00);
        System.out.println("Payment Test Result: " + paymentSuccess);

        // Test Transaction System
        TransactionManagementController transactionController = new TransactionManagementController();
        transactionController.processPayment("Gus", "Ethan", 100.23);

        // Test User Authentication
        UserLogin userLogin = new UserLogin("user2", "12345");
        boolean authSuccess = userLogin.validateLogin("user123", "password");
        System.out.println("Authentication Test Result: " + authSuccess);

        // Test User Registration
        UserController userController = new UserController();
        userController.createUser("newUser");

        System.out.println("Test Harness Execution Completed.");
    }
}
*/
