package TransactionMangement.Controller;

/**
 * This controller is responsible for handling transactions between users.
 */
public class TransactionManagementController {

    /**
     * Processes a payment transaction between two users.
     *
     * @param userFrom the username of the user who is initiating the payment
     * @param userTo   the username of the user who will receive the payment
     * @param amount   the amount to be transferred
     */
    public void processPayment(String userFrom, String userTo, double amount) {
        // Log details of the payment process
        System.out.println("Processing payment from user " + userFrom + " to user " + userTo + " of amount: $" + amount);
        // Add further implementation here
    }
}
