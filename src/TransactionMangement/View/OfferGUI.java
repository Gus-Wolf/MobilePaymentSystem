package TransactionMangement.View;

import TransactionMangement.Controller.OfferService;
import TransactionMangement.Model.Offer;
import UserManagement.Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class OfferGUI extends JFrame {
    private JTextField senderField;
    private JTextField receiverField;
    private JTextField amountField;

    private OfferService offerService;

    public OfferGUI(OfferService offerService) {
        this.offerService = offerService;
        initializeUI();
    }

    private void initializeUI() {
        // Basic frame settings
        setTitle("Send Offer");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        // Input fields
        add(new JLabel("Sender ID:"));
        senderField = new JTextField();
        add(senderField);

        add(new JLabel("Receiver ID:"));
        receiverField = new JTextField();
        add(receiverField);

        add(new JLabel("Amount:"));
        amountField = new JTextField();
        add(amountField);

        // Send button
        JButton sendButton = new JButton("Send Offer");
        sendButton.addActionListener(this::handleSendOffer);
        add(sendButton);

        // Placeholder for messages
        JLabel messageLabel = new JLabel(" ");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(messageLabel);

        // Adjust layout
        setLayout(new FlowLayout());
        setLocationRelativeTo(null); // Center the frame on screen
    }

    private void handleSendOffer(ActionEvent e) {
        try {
            // Retrieve and parse user inputs
            int senderId = Integer.parseInt(senderField.getText());
            int receiverId = Integer.parseInt(receiverField.getText());
            double amount = Double.parseDouble(amountField.getText());

            // Call OfferService to send the offer
            Offer offer = offerService.sendOffer(senderId, receiverId, amount);

            // Show success message
            JOptionPane.showMessageDialog(this,
                    "Offer Sent Successfully!\n" + offer,
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

            // Clear fields after successful submission
            senderField.setText("");
            receiverField.setText("");
            amountField.setText("");
        } catch (Exception ex) {
            // Show error message
            JOptionPane.showMessageDialog(this,
                    "Error: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Launch the Offer GUI
    public static void launch(List<User> users) {
        EventQueue.invokeLater(() -> {
            OfferService offerService = new OfferService(users);
            new OfferGUI(offerService).setVisible(true);
        });
    }
}