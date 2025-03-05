package PaymentManagement.Controller;

import PaymentManagement.Model.PaymentManagementModel;
import PaymentManagement.View.PaymentManagementView;

public class PaymentManagementController {
    private PaymentManagementModel model;
    private PaymentManagementView view;

    public PaymentManagementController() {
        model = new PaymentManagementModel();
        view = new PaymentManagementView();
    }

    public boolean processPayment(String sender, String receiver, double amount) {
        System.out.println("Stub: Processing payment from " + sender + " to " + receiver);
        return true; // Placeholder response
    }
}

