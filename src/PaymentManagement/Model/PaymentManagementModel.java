package PaymentManagement.Model;

public class PaymentManagementModel {

    private int paymentId;
    private int userId;
    private int businessUserId;
    private double amount;

    public PaymentManagementModel(int paymentId, int userId, int businessUserId, double amount) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.businessUserId = businessUserId;
        this.amount = amount;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBusinessUserId() {
        return businessUserId;
    }

    public void setBusinessUserId(int businessUserId) {
        this.businessUserId = businessUserId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
