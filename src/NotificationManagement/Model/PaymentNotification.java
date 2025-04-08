package NotificationManagement.Model;

public class PaymentNotification extends Notification {
    private int amount;
    private boolean paymentSuccess;
    private String senderId;

    public PaymentNotification(String userId, int amount, boolean paymentSuccess, String senderId) {
        super(userId);
        this.amount = amount;
        this.paymentSuccess = paymentSuccess;
        this.senderId = senderId;
    }

    @Override
    public String getContent() {
        return paymentSuccess ?
                "Payment of $" + amount + " from " + senderId + " succeeded" :
                "Payment of $" + amount + " from " + senderId + " failed";
    }

    // Getters
    public int getAmount() { return amount; }
    public boolean isPaymentSuccess() { return paymentSuccess; }
    public String getSenderId() { return senderId; }
}
