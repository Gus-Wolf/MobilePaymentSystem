package NotificationManagement.View;

import NotificationManagement.Model.Notification;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BusinessNotificationView implements NotificationViewInterface {
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JList<String> notificationList;
    private DefaultListModel<String> notificationListModel;
    private JButton refreshButton;
    private JButton clearButton;

    public BusinessNotificationView() {
        initializeUI();
    }

    private void initializeUI() {
        // Main panel setup
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240)); // Soft gray background

        // Header section
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(70, 130, 180)); // Steel blue header
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        titleLabel = new JLabel("Business Notifications");
        titleLabel.setFont(new Font("Sans Serif", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Notifications section
        notificationListModel = new DefaultListModel<>();
        notificationList = new JList<>(notificationListModel);
        notificationList.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        notificationList.setBackground(Color.WHITE);
        notificationList.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        notificationList.setSelectionBackground(new Color(220, 240, 255)); // Soft blue selection
        notificationList.setSelectionForeground(Color.DARK_GRAY);

        JScrollPane scrollPane = new JScrollPane(notificationList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Recent Notifications"));

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Action buttons
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        actionPanel.setBackground(new Color(240, 240, 240));

        refreshButton = new JButton("Refresh Notifications");
        refreshButton.setFont(new Font("Sans Serif", Font.BOLD, 14));
        refreshButton.setPreferredSize(new Dimension(180, 40));
        refreshButton.setBackground(new Color(70, 130, 180));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFocusPainted(false);
        refreshButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        refreshButton.addActionListener(e -> refreshNotifications());

        clearButton = new JButton("Clear Notifications");
        clearButton.setFont(new Font("Sans Serif", Font.BOLD, 14));
        clearButton.setPreferredSize(new Dimension(180, 40));
        clearButton.setBackground(new Color(220, 20, 60)); // Crimson button
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);
        clearButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        clearButton.addActionListener(e -> clearNotifications());

        actionPanel.add(refreshButton);
        actionPanel.add(clearButton);

        mainPanel.add(actionPanel, BorderLayout.SOUTH);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void showNotification(String content) {
        // Add notification to the list
        notificationListModel.addElement(content);
    }

    @Override
    public void displayNotifications(List<Notification> notifications) {
        notificationListModel.clear();
        for (Notification notification : notifications) {
            notificationListModel.addElement(notification.getContent());
        }
    }

    private void refreshNotifications() {
        JOptionPane.showMessageDialog(mainPanel, "Notifications refreshed!", "Information", JOptionPane.INFORMATION_MESSAGE);

        notificationListModel.clear();
        List<Notification> updatedNotifications = NotificationFetcher.fetchUpdatedNotifications();
        for (Notification notification : updatedNotifications) {
            notificationListModel.addElement(notification.getContent());
        }
    }

    private void clearNotifications() {
        notificationListModel.clear();
        JOptionPane.showMessageDialog(mainPanel, "All notifications cleared!", "Information", JOptionPane.INFORMATION_MESSAGE);
    }
}

class NotificationFetcher {

    public static List<Notification> fetchUpdatedNotifications() {
        // Mock implementation: This should be replaced with actual fetching logic
        return List.of(
            new Notification("user1"),
            new Notification("user2"),
            new Notification("user3")
        );
    }
}
