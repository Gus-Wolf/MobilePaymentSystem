package TransactionMangement.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OfferGUI extends JFrame {
    private JTextField senderField; // Field to enter Sender ID
    private JTextField receiverField; // Field to enter Receiver ID
    private JTextField amountField; // Field to enter Amount
    private JTable offerHistoryTable; // Table for past offers
    private DefaultTableModel tableModel; // Data model for JTable

    // Filename for saving offers
    private static final String OFFER_FILE = "offers.json";

    public OfferGUI() {
        setTitle("Send Offer");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        initializeUI();
        setVisible(true);
    }

    private void initializeUI() {
        // Top panel for the form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Form Fields
        formPanel.add(new JLabel("Sender ID:"));
        senderField = new JTextField();
        senderField.setToolTipText("Enter the ID of the sender");
        formPanel.add(senderField);

        formPanel.add(new JLabel("Receiver ID:"));
        receiverField = new JTextField();
        receiverField.setToolTipText("Enter the ID of the receiver");
        formPanel.add(receiverField);

        formPanel.add(new JLabel("Amount:"));
        amountField = new JTextField();
        amountField.setToolTipText("Enter the amount to send");
        formPanel.add(amountField);

        // Buttons for the form
        JButton sendButton = new JButton("Send Offer");
        sendButton.addActionListener(this::handleSendOffer);
        formPanel.add(sendButton);

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> clearFormFields());
        formPanel.add(clearButton);

        add(formPanel, BorderLayout.NORTH);

        // Middle panel: Offer history
        JPanel historyPanel = new JPanel(new BorderLayout());
        historyPanel.setBorder(BorderFactory.createTitledBorder("Offer History"));

        // Table to display history
        String[] columns = {"Sender ID", "Receiver ID", "Amount"};
        tableModel = new DefaultTableModel(columns, 0);
        offerHistoryTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(offerHistoryTable);
        historyPanel.add(scrollPane, BorderLayout.CENTER);
        add(historyPanel, BorderLayout.CENTER);

        // Load and display previous offers
        loadAndDisplayOffers();
    }

    private void handleSendOffer(ActionEvent e) {
        try {
            // Validate input fields
            int senderId = Integer.parseInt(senderField.getText());
            int receiverId = Integer.parseInt(receiverField.getText());
            double amount = Double.parseDouble(amountField.getText());

            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Amount must be greater than zero.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Create and save the offer
            Offer offer = new Offer(senderId, receiverId, amount);
            saveOfferToFile(offer);

            // Update the table with the new offer
            tableModel.addRow(new Object[]{senderId, receiverId, amount});
            clearFormFields();

            JOptionPane.showMessageDialog(this, "Offer sent successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Invalid input! Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving offer: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFormFields() {
        senderField.setText("");
        receiverField.setText("");
        amountField.setText("");
    }

    private void saveOfferToFile(Offer offer) throws IOException {
        List<Offer> offers = loadOffersFromFile();
        offers.add(offer);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(OFFER_FILE))) {
            oos.writeObject(offers);
        }
    }

    @SuppressWarnings("unchecked")
    private List<Offer> loadOffersFromFile() {
        File file = new File(OFFER_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Offer>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private void loadAndDisplayOffers() {
        List<Offer> offers = loadOffersFromFile();
        for (Offer offer : offers) {
            tableModel.addRow(new Object[]{offer.getSenderId(), offer.getReceiverId(), offer.getAmount()});
        }
    }

    // Inner class to represent an Offer
    private static class Offer implements Serializable {
        private final int senderId;
        private final int receiverId;
        private final double amount;

        public Offer(int senderId, int receiverId, double amount) {
            this.senderId = senderId;
            this.receiverId = receiverId;
            this.amount = amount;
        }

        public int getSenderId() {
            return senderId;
        }

        public int getReceiverId() {
            return receiverId;
        }

        public double getAmount() {
            return amount;
        }
    }

    // Main method to start the GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(OfferGUI::new);
    }
}
