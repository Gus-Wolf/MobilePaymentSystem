package PaymentManagement.Model;

import java.util.HashMap;
import java.util.Map;

public class PaymentManager {
    private static PaymentManager instance;
    private final Map<String, Double> userBalances;

    // Private constructor for Singleton
    private PaymentManager() {
        userBalances = new HashMap<>();
        // Example data
        userBalances.put("userA", 100.00);
        userBalances.put("userB", 50.00);
    }

    // Singleton instance getter
    public static PaymentManager getInstance() {
        if (instance == null) {
            instance = new PaymentManager();
        }
        return instance;
    }

    public boolean processPayment(String sender, String receiver, double amount) {
        if (!userBalances.containsKey(sender) || !userBalances.containsKey(receiver)) {
            System.out.println("User not found.");
            return false;
        }

        double senderBalance = userBalances.get(sender);
        if (senderBalance < amount) {
            System.out.println("Insufficient funds.");
            return false;
        }

        // Process transaction
        userBalances.put(sender, senderBalance - amount);
        userBalances.put(receiver, userBalances.get(receiver) + amount);
        System.out.println("Payment of $" + amount + " sent from " + sender + " to " + receiver);
        return true;
    }

    public double getBalance(String username) {
        return userBalances.getOrDefault(username, 0.0);
    }

    // Optional: method to initialize users (if loading from database later)
    public void addUser(String username, double balance) {
        userBalances.put(username, balance);
    }
}
