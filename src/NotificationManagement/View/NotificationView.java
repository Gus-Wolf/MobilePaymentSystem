package NotificationManagement.View;

import NotificationManagement.Model.Notification;
import TransactionMangement.View.OfferGUI;


import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.List;

public class NotificationView {
    // Constants for UI appearance
    private static final Color PRIMARY_COLOR = new Color(63, 81, 181); // Indigo
    private static final Color ACCENT_COLOR = new Color(255, 64, 129);  // Pink
    private static final Color BG_COLOR = new Color(245, 245, 245);     // Light gray
    private static final Color TEXT_COLOR = new Color(33, 33, 33);      // Dark gray
    private static final Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 20);
    private static final Font BUTTON_FONT = new Font("SansSerif", Font.BOLD, 14);
    private static final Font REGULAR_FONT = new Font("SansSerif", Font.PLAIN, 14);
    private static final String SEND_OFFER_LABEL = "Send Offer";
    private static final int BORDER_RADIUS = 8;

    private NotificationViewInterface viewStrategy;

    // GUI components
    private JFrame frame;
    private JPanel mainPanel;
    private JList<String> notificationList;
    private DefaultListModel<String> notificationListModel;
    private JButton switchToBusinessButton;
    private JButton switchToPersonalButton;
    private JTextField notificationInput;
    private JButton addNotificationButton;
    private JLabel titleLabel;
    private JLabel statusLabel;

    public NotificationView() {
        // Default view is set to Personal
        notificationListModel = new DefaultListModel<>();
        this.viewStrategy = new PersonalNotificationView(notificationListModel);

        // Initialize GUI
        initializeGUI();
    }

    private void initializeGUI() {
        try {
            // Set system look and feel for better integration
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame = new JFrame("Notification Center");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setMinimumSize(new Dimension(650, 450));
        frame.setLocationRelativeTo(null); // Center on screen

        // Main Panel with Border Layout
        mainPanel = new RoundedPanel(15);
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBackground(BG_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Add header panel
        mainPanel.add(createHeaderPanel(), BorderLayout.NORTH);

        // Add content panel (center)
        mainPanel.add(createContentPanel(), BorderLayout.CENTER);

        // Add input panel (south)
        mainPanel.add(createInputPanel(), BorderLayout.SOUTH);

        // Create status bar
        statusLabel = new JLabel("Ready");
        statusLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
        statusLabel.setForeground(new Color(120, 120, 120));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        mainPanel.add(statusLabel, BorderLayout.SOUTH);

        setupEventListeners();

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new RoundedPanel(10);
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setOpaque(false);

        // Title
        titleLabel = new JLabel("Notification Center");
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(PRIMARY_COLOR);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        headerPanel.add(titleLabel, BorderLayout.WEST);

        // View toggle panel
        JPanel viewTogglePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        viewTogglePanel.setOpaque(false);

        switchToPersonalButton = createStyledButton("Personal View", PRIMARY_COLOR);
        switchToBusinessButton = createStyledButton("Business View", new Color(100, 100, 100));

        viewTogglePanel.add(switchToPersonalButton);
        viewTogglePanel.add(switchToBusinessButton);
        headerPanel.add(viewTogglePanel, BorderLayout.EAST);

        return headerPanel;
    }

    private JPanel createContentPanel() {
        JPanel contentPanel = new RoundedPanel(10);
        contentPanel.setLayout(new BorderLayout(10, 10));
        contentPanel.setOpaque(false);

        // Create notification list with styled scroll pane
        notificationList = new JList<>(notificationListModel);
        notificationList.setFont(REGULAR_FONT);
        notificationList.setBackground(Color.WHITE);
        notificationList.setCellRenderer(new NotificationCellRenderer());
        notificationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(notificationList);
        scrollPane.setBorder(createRoundedBorder("Notifications", Color.LIGHT_GRAY));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setCorner(JScrollPane.LOWER_RIGHT_CORNER, new RoundedPanel(10));

        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // Actions panel on the right
        JPanel actionsPanel = new RoundedPanel(10);
        actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.Y_AXIS));
        actionsPanel.setOpaque(false);
        actionsPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        JButton sendOfferButton = createStyledButton("Send Offer", ACCENT_COLOR);
        JButton refreshButton = createStyledButton("Refresh", PRIMARY_COLOR);
        JButton clearButton = createStyledButton("Clear All", new Color(200, 75, 75));
        JButton profileButton = createStyledButton("Profile", new Color(100, 181, 246)); // Light blue color


        sendOfferButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        refreshButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        clearButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        profileButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        sendOfferButton.setMaximumSize(new Dimension(150, 40));
        refreshButton.setMaximumSize(new Dimension(150, 40));
        clearButton.setMaximumSize(new Dimension(150, 40));
        profileButton.setMaximumSize(new Dimension(150, 40));

        sendOfferButton.addActionListener(e -> launchOfferGUI());
        refreshButton.addActionListener(e -> refreshNotifications());
        clearButton.addActionListener(e -> clearNotifications());
        profileButton.addActionListener(e -> openProfileView());

        actionsPanel.add(Box.createVerticalStrut(5));
        actionsPanel.add(sendOfferButton);
        actionsPanel.add(Box.createVerticalStrut(15));
        actionsPanel.add(refreshButton);
        actionsPanel.add(Box.createVerticalStrut(15));
        actionsPanel.add(clearButton);
        actionsPanel.add(Box.createVerticalStrut(15));
        actionsPanel.add(profileButton);
        actionsPanel.add(Box.createVerticalGlue());


        // Only add if screen is wide enough
        if (Toolkit.getDefaultToolkit().getScreenSize().width > 1024) {
            contentPanel.add(actionsPanel, BorderLayout.EAST);
        }

        return contentPanel;
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new RoundedPanel(10);
        inputPanel.setLayout(new BorderLayout(10, 0));
        inputPanel.setOpaque(false);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        // Create styled text field with rounded border
        notificationInput = new JTextField();
        notificationInput.setFont(REGULAR_FONT);
        notificationInput.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(8, new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));

        // Create add button
        addNotificationButton = createStyledButton("Add Notification", PRIMARY_COLOR);

        inputPanel.add(notificationInput, BorderLayout.CENTER);
        inputPanel.add(addNotificationButton, BorderLayout.EAST);

        return inputPanel;
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 10, 10);
                g2.dispose();
                super.paintComponent(g);
            }
        };

        button.setFont(BUTTON_FONT);
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(150, 35));

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(darken(color, 0.1f));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });

        return button;
    }

    private Border createRoundedBorder(String title, Color color) {
        RoundedBorder roundedBorder = new RoundedBorder(10, color);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(
                roundedBorder,
                title
        );
        titledBorder.setTitleFont(new Font("SansSerif", Font.BOLD, 14));
        titledBorder.setTitleColor(PRIMARY_COLOR);
        return titledBorder;
    }

    private Color darken(Color color, float fraction) {
        int red = Math.max(0, Math.round(color.getRed() * (1 - fraction)));
        int green = Math.max(0, Math.round(color.getGreen() * (1 - fraction)));
        int blue = Math.max(0, Math.round(color.getBlue() * (1 - fraction)));
        return new Color(red, green, blue);
    }

    private void setupEventListeners() {
        // Switch to Business View
        switchToBusinessButton.addActionListener(e -> switchToBusinessView());

        // Switch to Personal View
        switchToPersonalButton.addActionListener(e -> switchToPersonalView());

        // Add a new notification
        addNotificationButton.addActionListener(e -> {
            String content = notificationInput.getText();
            if (!content.isEmpty()) {
                showNotification(content);
                notificationInput.setText("");
                statusLabel.setText("Notification added successfully");
            } else {
                statusLabel.setText("Please enter notification content");
                notificationInput.setBorder(BorderFactory.createCompoundBorder(
                        new RoundedBorder(8, new Color(255, 100, 100)),
                        BorderFactory.createEmptyBorder(8, 8, 8, 8)
                ));

                // Reset border after delay
                Timer timer = new Timer(2000, event -> {
                    notificationInput.setBorder(BorderFactory.createCompoundBorder(
                            new RoundedBorder(8, new Color(200, 200, 200)),
                            BorderFactory.createEmptyBorder(8, 8, 8, 8)
                    ));
                });
                timer.setRepeats(false);
                timer.start();
            }
        });

        // Add Enter key listener to text field
        notificationInput.addActionListener(e -> {
            String content = notificationInput.getText();
            if (!content.isEmpty()) {
                showNotification(content);
                notificationInput.setText("");
                statusLabel.setText("Notification added successfully");
            }
        });
    }

    private void refreshNotifications() {
        List<Notification> updatedNotifications = NotificationFetcherUser.fetchUpdatedNotifications();
        displayNotifications(updatedNotifications);
        statusLabel.setText("Notifications refreshed");
    }

    private void clearNotifications() {
        notificationListModel.clear();
        statusLabel.setText("All notifications cleared");
    }

    public void switchToBusinessView() {
        // Update UI appearance for Business view
        this.viewStrategy = new BusinessNotificationView();
        titleLabel.setText("Business Notification Center");

        // Update button states
        switchToBusinessButton.setBackground(PRIMARY_COLOR);
        switchToPersonalButton.setBackground(new Color(100, 100, 100));

        statusLabel.setText("Switched to Business View");
    }
    private void openProfileView() {
        SwingUtilities.invokeLater(() -> {
            UserManagement.View.ProfileView.main(new String[]{});
        });
        statusLabel.setText("Opened Profile Page");
    }

    public void switchToPersonalView() {
        // Update UI appearance for Personal view
        this.viewStrategy = new PersonalNotificationView(notificationListModel);
        titleLabel.setText("Personal Notification Center");

        // Update button states
        switchToPersonalButton.setBackground(PRIMARY_COLOR);
        switchToBusinessButton.setBackground(new Color(100, 100, 100));

        statusLabel.setText("Switched to Personal View");
    }

    private void launchOfferGUI() {
        new OfferGUI(this);
        statusLabel.setText("Offer GUI launched");
    }

    public void showNotification(String content) {
        this.viewStrategy.showNotification(content);
    }

    public void displayNotifications(List<Notification> notifications) {
        this.viewStrategy.displayNotifications(notifications);
    }

    // Custom cell renderer for notifications
    private class NotificationCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(
                JList<?> list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {

            JLabel label = (JLabel) super.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus);

            // Style the cell
            int padding = 8;
            Border emptyBorder = new EmptyBorder(padding, padding + 2, padding, padding);
            Border roundedBorder = new RoundedBorder(8,
                    isSelected ? PRIMARY_COLOR : new Color(220, 220, 220));
            label.setBorder(BorderFactory.createCompoundBorder(roundedBorder, emptyBorder));
            label.setFont(REGULAR_FONT);

            // If icon not found, use text indicator
            String text = value.toString();
            if (text.contains("[PERSONAL]") || text.contains("[PERSONAL NOTIFICATION]")) {
                label.setText("🔔 " + text);
            } else {
                label.setText("💼 " + text);
            }

            // Set background colors
            if (isSelected) {
                label.setBackground(new Color(232, 240, 254));
                label.setForeground(PRIMARY_COLOR);
            } else {
                label.setBackground(index % 2 == 0 ? Color.WHITE : new Color(250, 250, 250));
                label.setForeground(TEXT_COLOR);
            }

            return label;
        }
    }

    // Rounded panel implementation
    private class RoundedPanel extends JPanel {
        private int radius;

        public RoundedPanel(int radius) {
            super();
            this.radius = radius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    // Rounded border implementation
    private class RoundedBorder implements Border {
        private int radius;
        private Color color;

        public RoundedBorder(int radius, Color color) {
            this.radius = radius;
            this.color = color;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius+1, radius+1, radius+2, radius);
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
            g2.drawRoundRect(x, y, width-1, height-1, radius, radius);
            g2.dispose();
        }
    }
}

class NotificationFetcherUser {
    public static List<Notification> fetchUpdatedNotifications() {
        // Mock implementation: This should be replaced with actual fetching logic
        return List.of(
                new Notification("user1"),
                new Notification("user2"),
                new Notification("user3")
        );
    }
}
