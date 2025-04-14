package PaymentManagement.Controller;

import PaymentManagement.Model.PaymentManager;
import PaymentManagement.Model.PaymentManagementModel;
import PaymentManagement.View.PaymentManagementView;

public class PaymentManagementController {
    private PaymentManagementModel model;
    private PaymentManagementView view;
    private PaymentManager paymentManager;

    public PaymentManagementController() {
        paymentManager = PaymentManager.getInstance();
        model = new PaymentManagementModel();
        view = new PaymentManagementView();
    }

    public boolean processPayment(String sender, String receiver, double amount) {
        return paymentManager.processPayment(sender, receiver, amount);
        /*System.out.println("Stub: Processing payment from " + sender + " to " + receiver);*/
    }

    public Double getUserBalance(String username) {
        return paymentManager.getBalance(username);
    }
}

