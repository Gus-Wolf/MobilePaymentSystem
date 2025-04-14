package UserManagement.View;

import UserManagement.Controller.PaymentWizardController;

import javax.swing.*;
import java.awt.*;

public class Step2Panel extends JPanel {
    private JTextField amountField;

    public Step2Panel(PaymentWizardController controller) {
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Amount to Send:"));
        amountField = new JTextField();
        add(amountField);

        // Back button
        JButton backButton = new JButton("⬅ Back");
        backButton.addActionListener(e -> controller.showStep("Step1"));

        // Next button
        JButton nextButton = new JButton("Next ➡");
        nextButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText().trim());
                controller.setAmount(amount);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
            }
        });

        add(backButton);
        add(nextButton);
    }
}
