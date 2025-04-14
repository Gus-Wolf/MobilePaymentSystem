package UserManagement.View;

import UserManagement.Controller.PaymentWizardController;

import javax.swing.*;
import java.awt.*;

public class Step3Panel extends JPanel {
    private JLabel confirmLabel;

    public Step3Panel(PaymentWizardController controller) {
        setLayout(new BorderLayout());

        confirmLabel = new JLabel("", SwingConstants.CENTER);
        add(confirmLabel, BorderLayout.CENTER);

        // Back + Finish Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton backButton = new JButton("⬅ Back");
        backButton.addActionListener(e -> controller.showStep("Step2"));

        JButton finishButton = new JButton("Confirm & Send");
        finishButton.addActionListener(e -> controller.completePayment());

        buttonPanel.add(backButton);
        buttonPanel.add(finishButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void setConfirmationDetails(String sender, String receiver, double amount) {
        confirmLabel.setText("<html><div style='text-align: center;'>Send $" + amount + "<br>from " + sender + "<br>to " + receiver + "?</div></html>");
    }
}
