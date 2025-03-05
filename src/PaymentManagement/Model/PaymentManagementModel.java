package PaymentManagement.Model;

public class PaymentManagementModel {
    private String sender;
    private String receiver;
    private double amount;

    public boolean validatePayment() {
        System.out.println("Stub: Validating payment...");
        return true;
    }
}

