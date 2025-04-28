package NotificationManagement.View;

import NotificationManagement.Model.Notification;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;

public class BusinessNotificationView implements NotificationViewInterface {
    // UI Constants
    private static final Color PRIMARY_COLOR = new Color(0, 121, 107); // Teal color for business
    private static final Color SECONDARY_COLOR = new Color(33, 33, 33);

    // Use native system fonts
    private static final Font HEADER_FONT = new Font("SansSerif", Font.BOLD, 18);
    private static final Font NORMAL_FONT = new Font("SansSerif", Font.PLAIN, 14);

    private JPanel mainPanel;
    private DefaultListModel<String> notificationListModel;
    private JList<String> notificationList;
    private JScrollPane scrollPane;

    public BusinessNotificationView() {
        notificationListModel = new DefaultListModel<>();
        initializeUI();
    }

    private void initializeUI() {
        mainPanel = new RoundedPanel(20);
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(245, 245, 245));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        notificationList = new JList<>(notificationListModel);
        notificationList.setCellRenderer(new BusinessNotificationCellRenderer());
        notificationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        notificationList.setFont(NORMAL_FONT);

        scrollPane = new JScrollPane(notificationList);
        scrollPane.setBorder(createStyledBorder("Business Notifications"));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setCorner(JScrollPane.LOWER_RIGHT_CORNER, new RoundedPanel(20));

        mainPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private Border createStyledBorder(String title) {
        Border roundedBorder = new RoundedBorder(15, new Color(200, 200, 200));
        TitledBorder titledBorder = BorderFactory.createTitledBorder(
                roundedBorder,
                title
        );
        titledBorder.setTitleFont(HEADER_FONT);
        titledBorder.setTitleColor(PRIMARY_COLOR);
        return titledBorder;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void showNotification(String content) {
        notificationListModel.addElement("[BUSINESS] " + content);
    }

    @Override
    public void displayNotifications(List<Notification> notifications) {
        notificationListModel.clear();
        for (Notification notification : notifications) {
            notificationListModel.addElement("[BUSINESS] " + notification.getContent());
        }
    }

    private class BusinessNotificationCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(
                JList<?> list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {

            JLabel label = (JLabel) super.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus);

            label.setFont(NORMAL_FONT);

            int padding = 12;
            Border emptyBorder = new EmptyBorder(padding, padding, padding, padding);
            Border roundedBorder = new RoundedBorder(12,
                    isSelected ? PRIMARY_COLOR : new Color(220, 220, 220));
            label.setBorder(BorderFactory.createCompoundBorder(roundedBorder, emptyBorder));

            label.setText("💼 " + value.toString());

            if (isSelected) {
                label.setBackground(new Color(224, 242, 241));
                label.setForeground(PRIMARY_COLOR);
            } else {
                label.setBackground(index % 2 == 0 ? Color.WHITE : new Color(250, 250, 250));
                label.setForeground(SECONDARY_COLOR);
            }

            return label;
        }
    }

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
