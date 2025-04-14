 import NotificationManagement.Controller.NotificationController;
 import UserAuthentication.UserLogin;
 import java.util.Scanner;

 import java.sql.SQLOutput;

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

        while (!validChoice) {
            System.out.println("MENU:");
            System.out.println("Type 1 for Business Dashboard");
            System.out.println("Type 2 for Social Feed");
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
                    // Social feed
                    controller.setBusinessAccount(false);
                    controller.sendPaymentNotification("user-789", 50, true, "friend-001");
                    controller.sendLoginNotification("user-789", "192.168.0.100", "Home");
                    controller.displayHistory();
                    validChoice = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        }




    }
}
