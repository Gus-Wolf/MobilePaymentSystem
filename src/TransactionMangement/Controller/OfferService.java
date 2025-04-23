package TransactionMangement.Controller;

import TransactionMangement.Model.Offer;
import UserManagement.Model.User;

import java.util.ArrayList;
import java.util.List;

public class OfferService {
    private List<User> users;
    private List<Offer> offers;

    public OfferService(List<User> users) {
        this.users = users;
        this.offers = new ArrayList<>();
    }

    public Offer sendOffer(int senderId, int receiverId, double amount) {
        // Validate
        User sender = findUserById(senderId);
        User receiver = findUserById(receiverId);

        if (sender == null || receiver == null) {
            throw new IllegalArgumentException("Sender or receiver does not exist");
        }

        // Validate amt
        if (amount <= 0 || sender.getBalance() < amount) {
            throw new IllegalArgumentException("Invalid amount or insufficient balance");
        }

        // Take balance from sending party
        sender.setBalance(sender.getBalance() - amount);

        // Create and save the offer
        Offer offer = new Offer(offers.size() + 1, senderId, receiverId, amount, "pending");
        offers.add(offer);
        return offer;
    }

    private User findUserById(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }
}