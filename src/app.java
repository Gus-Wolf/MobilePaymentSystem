import NotificationManagement.Controller.NotificationController;
import UserAuthentication.Model.UserLogin;
import TransactionMangement.Controller.OfferService;
import TransactionMangement.Model.Offer;
import UserManagement.Model.User;

import java.util.List;
import java.util.Scanner;

public class app {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username:");
        String username = scanner.nextLine();
        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        boolean validChoice = false;

        UserLogin authentication = new UserLogin(username, password);
        NotificationController controller = new NotificationController();

        // fake data
        List<User> users = List.of(
                new User(1, "Alice", 500),
                new User(2, "Bob", 300)
        );
        OfferService offerService = new OfferService(users); // Create OfferService

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
                        Offer offer = offerService.sendOffer(senderId, receiverId, amount);
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