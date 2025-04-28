import UserAuthentication.View.LoginGUI;

import java.util.List;
import java.util.Scanner;

public class AppLauncher { // <-- new cleaner name
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new LoginGUI(() -> {
                // This runs AFTER login succeeds
                openMainApp();
            });
        });
    }

    private static void openMainApp() {
        // Your old app logic starts here
        NotificationManagement.Controller.NotificationController controller = new NotificationManagement.Controller.NotificationController();
        TransactionMangement.Controller.OfferService offerService = new TransactionMangement.Controller.OfferService(
                List.of(
                        new UserManagement.Model.User(1, "Alice", 500),
                        new UserManagement.Model.User(2, "Bob", 300)
                )
        );

        boolean validChoice = false;
        Scanner scanner = new Scanner(System.in);

        while (!validChoice) {
            System.out.println("MENU:");
            System.out.println("Type 1 for Business Dashboard");
            System.out.println("Type 2 for Social Feed");
            System.out.println("Type 3 to Send Offer");

            int input = scanner.nextInt();

            switch (input) {
                case 1:
                    System.out.println("Taking you to Business Dashboard");
                    controller.setBusinessAccount(true);
                    controller.sendPaymentNotification("biz-123", 5000, true, "client-456");
                    controller.sendLoginNotification("biz-123", "192.168.1.1", "New York");
                    controller.displayHistory();
                    validChoice = true;
                    break;
                case 2:
                    System.out.println("Taking you to Social Feed");
                    controller.setBusinessAccount(false);
                    controller.sendPaymentNotification("user-789", 50, true, "friend-001");
                    controller.sendLoginNotification("user-789", "192.168.0.100", "Home");
                    controller.displayHistory();
                    validChoice = true;
                    break;
                case 3:
                    System.out.println("Send Offer Selected.");
                    System.out.println("Enter sender ID:");
                    int senderId = scanner.nextInt();
                    System.out.println("Enter receiver ID:");
                    int receiverId = scanner.nextInt();
                    System.out.println("Enter amount:");
                    double amount = scanner.nextDouble();

                    try {
                        TransactionMangement.Model.Offer offer = offerService.sendOffer(senderId, receiverId, amount);
                        System.out.println("Offer Sent Successfully!");
                        System.out.println("Offer Details: " + offer);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    validChoice = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }
    }
}
