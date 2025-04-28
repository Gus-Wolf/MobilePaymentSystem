package TransactionMangement.Model;

public class Offer {
    private int id;
    private int senderId;
    private int receiverId;
    private double amount;
    private String status;

    // Getters and setters
    public Offer(int id, int senderId, int receiverId, double amount, String status) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
        this.status = status;
    }

    // Overriding toString for debugging
    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                '}';
    }
}
