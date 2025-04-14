package UserManagement.View;

import UserManagement.Controller.PaymentWizardController;

import javax.swing.*;
import java.awt.*;

public class Step1Panel extends JPanel {
    private JTextField senderField, receiverField;

    public Step1Panel(PaymentWizardController controller) {
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Sender Username:"));
        senderField = new JTextField();
        add(senderField);

        add(new JLabel("Receiver Username:"));
        receiverField = new JTextField();
        add(receiverField);

        add(new JLabel());
        JButton nextButton = new JButton("Next ➡");
        nextButton.addActionListener(e -> {
            String sender = senderField.getText().trim();
            String receiver = receiverField.getText().trim();
            controller.setUsernames(sender, receiver);
        });
        add(nextButton);
    }
}
