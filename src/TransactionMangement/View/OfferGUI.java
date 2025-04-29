package TransactionMangement.View;

import NotificationManagement.View.NotificationView;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OfferGUI extends JFrame {
    // UI Constants
    private static final Color PRIMARY_COLOR = new Color(0, 121, 107); // Teal color
    private static final Color SECONDARY_COLOR = new Color(33, 33, 33);
    // --- FONT UPDATED HERE ---
    private static final Font HEADER_FONT = new Font("Arial", Font.BOLD, 18);
    private static final Font NORMAL_FONT = new Font("Arial", Font.PLAIN, 14);

    private JTextField senderField;
    private JTextField receiverField;
    private JTextField amountField;
    private JTable offerHistoryTable;
    private DefaultTableModel tableModel;
    private static final String OFFER_FILE = "offers.json";

    private NotificationView notificationView;


    public OfferGUI(NotificationView notificationView) {
        this.notificationView = notificationView; // store the reference
        setTitle("Send Offer");
        setSize(600, 600); // Increased size for better spacing
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        initializeUI();
        setVisible(true);
    }

    private void initializeUI() {
        // Main container with rounded panel
        RoundedPanel mainPanel = new RoundedPanel(20);
        mainPanel.setLayout(new BorderLayout(15, 15));
        mainPanel.setBackground(new Color(245, 245, 245));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        add(mainPanel);

        // Form panel with rounded border
        RoundedPanel formPanel = new RoundedPanel(15);
        formPanel.setLayout(new GridLayout(5, 2, 15, 15));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(15, PRIMARY_COLOR),
                new EmptyBorder(15, 15, 15, 15)
        ));

        // Form components with consistent styling
        addStyledLabel("Sender ID:", formPanel);
        senderField = createStyledTextField();
        formPanel.add(senderField);

        addStyledLabel("Receiver ID:", formPanel);
        receiverField = createStyledTextField();
        formPanel.add(receiverField);

        addStyledLabel("Amount:", formPanel);
        amountField = createStyledTextField();
        formPanel.add(amountField);

// ✅ Only add each button once:
        formPanel.add(createStyledButton("Send Offer", this::handleSendOffer));
        formPanel.add(createStyledButton("Clear", e -> clearFormFields()));
        formPanel.add(new JLabel()); // Still keeping the empty cell

// Create a small panel just for the Back button
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        backButtonPanel.setOpaque(false); // Make background transparent so it looks clean

        JButton backButton = createStyledButton("Back", e -> handleBack());
        backButtonPanel.add(backButton);

        formPanel.add(backButtonPanel);


        mainPanel.add(formPanel, BorderLayout.NORTH);

        // History panel with rounded border
        RoundedPanel historyPanel = new RoundedPanel(15);
        historyPanel.setLayout(new BorderLayout());
        historyPanel.setBackground(Color.WHITE);
        historyPanel.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(15, PRIMARY_COLOR),
                new EmptyBorder(15, 15, 15, 15)
        ));

        // Table setup with custom renderer
        String[] columns = {"Sender ID", "Receiver ID", "Amount"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        offerHistoryTable = new JTable(tableModel);
        offerHistoryTable.setFont(NORMAL_FONT);
        offerHistoryTable.setRowHeight(35);
        offerHistoryTable.setDefaultRenderer(Object.class, new AlternatingRowRenderer());

        JScrollPane scrollPane = new JScrollPane(offerHistoryTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        historyPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(historyPanel, BorderLayout.CENTER);

        loadAndDisplayOffers();
    }

    private void handleBack() {
        this.dispose(); // Close the current OfferGUI window

    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setFont(NORMAL_FONT);
        field.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(8, new Color(200, 200, 200)),
                new EmptyBorder(8, 12, 8, 12)
        ));
        return field;
    }

    private void addStyledLabel(String text, JPanel panel) {
        JLabel label = new JLabel(text);
        label.setFont(NORMAL_FONT.deriveFont(Font.BOLD));
        label.setForeground(SECONDARY_COLOR);
        panel.add(label);
    }

    private JButton createStyledButton(String text, java.awt.event.ActionListener listener) {
        JButton button = new JButton(text);
        button.setFont(NORMAL_FONT.deriveFont(Font.BOLD));
        button.setBackground(PRIMARY_COLOR);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(new RoundedBorder(8, PRIMARY_COLOR));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.addActionListener(listener);

        // Hover effects
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(PRIMARY_COLOR.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(PRIMARY_COLOR);
            }
        });

        return button;
    }

    // Custom table cell renderer for alternating rows
    private class AlternatingRowRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {

            JLabel label = (JLabel) super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);

            label.setFont(NORMAL_FONT);
            label.setBorder(new EmptyBorder(0, 12, 0, 12));

            if (isSelected) {
                label.setBackground(new Color(224, 242, 241));
                label.setForeground(PRIMARY_COLOR);
            } else {
                label.setBackground(row % 2 == 0 ? Color.WHITE : new Color(250, 250, 250));
                label.setForeground(SECONDARY_COLOR);
            }

            return label;
        }
    }

    // Rounded components from previous implementation
    private class RoundedPanel extends JPanel {
        private final int radius;

        public RoundedPanel(int radius) {
            this.radius = radius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    private class RoundedBorder implements Border {
        private final int radius;
        private final Color color;

        public RoundedBorder(int radius, Color color) {
            this.radius = radius;
            this.color = color;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius + 1, radius + 1, radius + 2, radius);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
        }
    }

    // Business logic methods
    private void handleSendOffer(ActionEvent e) {
        try {
            String senderUsername = senderField.getText().trim();
            String receiverUsername = receiverField.getText().trim();
            double amount = Double.parseDouble(amountField.getText().trim());

            if (senderUsername.isEmpty() || receiverUsername.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Sender and Receiver must not be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Amount must be greater than zero.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Offer offer = new Offer(senderUsername, receiverUsername, amount);
            saveOfferToFile(offer);
            tableModel.addRow(new Object[]{senderUsername, receiverUsername, amount});
            String notificationMessage = " You sent $" + String.format("%.2f", amount) + " to " + receiverUsername;
            notificationView.showNotification(notificationMessage);


            DbManagement.Controller.DatabaseConnector.insertTransaction(senderUsername, receiverUsername, amount);

            clearFormFields();
            JOptionPane.showMessageDialog(this, "Offer sent successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Invalid amount! Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
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
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Offer>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private void loadAndDisplayOffers() {
        List<Offer> offers = loadOffersFromFile();
        for (Offer offer : offers) {
            tableModel.addRow(new Object[]{offer.getSenderUsername(), offer.getReceiverUsername(), offer.getAmount()});
        }
    }


    private static class Offer implements Serializable {
        private final String senderUsername;
        private final String receiverUsername;
        private final double amount;

            public Offer(String senderUsername, String receiverUsername, double amount) {
                this.senderUsername = senderUsername;
                this.receiverUsername = receiverUsername;
                this.amount = amount;
            }

            public String getSenderUsername() {
                return senderUsername;
            }

            public String getReceiverUsername() {
                return receiverUsername;
            }

            public double getAmount() {
                return amount;
            }
        }


        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> new OfferGUI(new NotificationView()).setVisible(true));
        }
    }
