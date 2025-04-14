package UserManagement.Controller;

import PaymentManagement.Controller.PaymentManagementController;
import UserManagement.View.*;

import javax.swing.*;
import java.awt.*;

public class PaymentWizardController {
    private PaymentWizardFrame frame;
    private Step1Panel step1;
    private Step2Panel step2;
    private Step3Panel step3;
    private PaymentManagementController paymentController;

    private String sender;
    private String receiver;
    private double amount;

    public PaymentWizardController() {
        frame = new PaymentWizardFrame();
        paymentController = new PaymentManagementController();

        step1 = new Step1Panel(this);
        step2 = new Step2Panel(this);
        step3 = new Step3Panel(this);

        frame.add(step1, "Step1");
        frame.add(step2, "Step2");
        frame.add(step3, "Step3");

        frame.setVisible(true);
        showStep("Step1");
    }

    public void showStep(String stepName) {
        CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
        cl.show(frame.getContentPane(), stepName);
    }

    public void setUsernames(String sender, String receiver) {
        this.sender = sender;
        this.receiver = receiver;
        showStep("Step2");
    }

    public void setAmount(double amount) {
        this.amount = amount;
        step3.setConfirmationDetails(sender, receiver, amount);
        showStep("Step3");
    }

    public void completePayment() {
        boolean success = paymentController.processPayment(sender, receiver, amount);
        if (success) {
            JOptionPane.showMessageDialog(frame, "Payment successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, "Payment failed. Check usernames or balance.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        frame.dispose();
    }
}
