package NotificationManagement.View;

import NotificationManagement.Model.Notification;
import TransactionMangement.View.OfferGUI;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class NotificationView {
    private static final String SEND_OFFER_LABEL = "Send Offer";

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

    public NotificationView() {
        // Default view is set to Personal
        this.viewStrategy = new PersonalNotificationView(notificationListModel);

        // Initialize GUI
        initializeGUI();
    }

    private void initializeGUI() {
        frame = new JFrame("Notification Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Main Panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Notification Display
        notificationListModel = new DefaultListModel<>();
        notificationList = new JList<>(notificationListModel);
        JScrollPane scrollPane = new JScrollPane(notificationList);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Changing Views
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        switchToBusinessButton = new JButton("Business View");
        switchToPersonalButton = new JButton("Personal View");
        buttonPanel.add(switchToBusinessButton);
        buttonPanel.add(switchToPersonalButton);
        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        // Adding Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        notificationInput = new JTextField(30);
        addNotificationButton = new JButton("Add Notification");
        inputPanel.add(notificationInput);
        inputPanel.add(addNotificationButton);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        // Adding "Send Offer" Button
        JButton sendOfferButton = createSendOfferButton();
        mainPanel.add(sendOfferButton, BorderLayout.EAST);

        setupEventListeners();

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JButton createSendOfferButton() {
        JButton sendOfferButton = new JButton(SEND_OFFER_LABEL);
        sendOfferButton.addActionListener(e -> launchOfferGUI());
        return sendOfferButton;
    }

    private void launchOfferGUI() {
        OfferGUI.launch();
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
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter notification content!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void switchToBusinessView() {
        this.viewStrategy = new BusinessNotificationView(notificationListModel);
        JOptionPane.showMessageDialog(frame, "Switched to Business View.");
    }

    public void switchToPersonalView() {
        this.viewStrategy = new PersonalNotificationView(notificationListModel);
        JOptionPane.showMessageDialog(frame, "Switched to Personal View.");
    }

    public void showNotification(String content) {
        this.viewStrategy.showNotification(content);
    }

    public void displayNotifications(List<Notification> notifications) {
        this.viewStrategy.displayNotifications(notifications);
    }
}