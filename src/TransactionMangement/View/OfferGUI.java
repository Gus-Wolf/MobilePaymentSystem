package TransactionMangement.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OfferGUI extends JFrame {
    private JTextField senderField; // Field to enter Sender ID
    private JTextField receiverField; // Field to enter Receiver ID
    private JTextField amountField; // Field to enter Amount

    // Filename for saving offers
    private static final String OFFER_FILE = "offers.json";

    public OfferGUI() {
        initializeUI();
    }

    private void initializeUI() {
        // Set basic configuration for the frame
        setTitle("Send Offer");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10)); // GridLayout for evenly spacing items

        // Add input field for Sender ID
        add(new JLabel("Sender ID:"));
        senderField = new JTextField();
        add(senderField);

        // Add input field for Receiver ID
        add(new JLabel("Receiver ID:"));
        receiverField = new JTextField();
        add(receiverField);

        // Add input field for Amount
        add(new JLabel("Amount:"));
        amountField = new JTextField();
        add(amountField);

        // Add a "Send Offer" button
        JButton sendButton = new JButton("Send Offer");
        sendButton.addActionListener(this::handleSendOffer); // Event listener for button click
        add(sendButton);

        // Placeholder for extra layout spacing
        JLabel placeHolderLabel = new JLabel("");
        add(placeHolderLabel);

        // Format the UI
        setLocationRelativeTo(null); // Center the window on the screen
    }

    private void handleSendOffer(ActionEvent e) {
        try {
            // Retrieve and validate
            String senderText = senderField.getText();
            String receiverText = receiverField.getText();
            String amountText = amountField.getText();

            if (senderText.isEmpty() || receiverText.isEmpty() || amountText.isEmpty()) {
                throw new IllegalArgumentException("All fields must be filled.");
            }

            int senderId = Integer.parseInt(senderText);
            int receiverId = Integer.parseInt(receiverText);
            double amount = Double.parseDouble(amountText);

            if (senderId <= 0 || receiverId <= 0 || amount <= 0) {
                throw new IllegalArgumentException("Sender ID, Receiver ID, and Amount must be positive values.");
            }

            // Create a new offer
            Offer offer = new Offer(senderId, receiverId, amount);

            // Save the offer to the JSON file
            saveOfferToFile(offer);

            // Show success confirmation pop-up
            JOptionPane.showMessageDialog(this, "Offer Sent Successfully!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

            // Clear the input fields
            senderField.setText("");
            receiverField.setText("");
            amountField.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers in all fields!", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving the offer: " + ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveOfferToFile(Offer offer) throws IOException {
        List<Offer> offers = loadOffersFromFile();

        offers.add(offer);

        // Write the updated list of offers back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OFFER_FILE))) {
            writer.write("[\n");
            for (int i = 0; i < offers.size(); i++) {
                Offer o = offers.get(i);

                // JSON format
                String offerJson = String.format(
                        "  {\n    \"senderId\": %d,\n    \"receiverId\": %d,\n    \"amount\": %.2f\n  }",
                        o.getSenderId(), o.getReceiverId(), o.getAmount()
                );

                if (i < offers.size() - 1) {
                    offerJson += ",";
                }

                writer.write(offerJson + "\n");
            }
            writer.write("]");
        }
    }

    private List<Offer> loadOffersFromFile() {
        List<Offer> offers = new ArrayList<>();

        // Check if the file exists
        File file = new File(OFFER_FILE);
        if (!file.exists()) {
            return offers;
        }

        // Load offers from the file
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder jsonBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line.trim());
            }

            String json = jsonBuilder.toString();
            if (json.length() > 2) { // If the file isn't empty
                // Strip the brackets and split JSON objects
                json = json.substring(1, json.length() - 1);
                String[] offerJsons = json.split("\\},\\{");

                for (String offerJson : offerJsons) {
                    // Clean up JSON fragments
                    offerJson = offerJson.replace("{", "").replace("}", "").trim();

                    // Parse fields
                    String[] fields = offerJson.split(",");
                    int senderId = 0;
                    int receiverId = 0;
                    double amount = 0.0;

                    for (String field : fields) {
                        String[] keyValue = field.split(":");
                        String key = keyValue[0].trim().replace("\"", "");
                        String value = keyValue[1].trim();

                        switch (key) {
                            case "senderId" -> senderId = Integer.parseInt(value);
                            case "receiverId" -> receiverId = Integer.parseInt(value);
                            case "amount" -> amount = Double.parseDouble(value);
                        }
                    }

                    offers.add(new Offer(senderId, receiverId, amount));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return offers;
    }

    // Launch the Offer GUI
    public static void launch() {
        EventQueue.invokeLater(() -> new OfferGUI().setVisible(true));
    }

    // Inner class to represent an Offer
    private static class Offer {
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
}