package NotificationManagement.View;

import NotificationManagement.Model.Notification;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class PersonalNotificationView implements NotificationViewInterface {
    // UI Constants
    private static final Color PRIMARY_COLOR = new Color(63, 81, 181); // Indigo
    private static final Color SECONDARY_COLOR = new Color(33, 33, 33);
    private static final Font NORMAL_FONT = new Font("SansSerif", Font.PLAIN, 14);

    private final DefaultListModel<String> notifications;

    public PersonalNotificationView(DefaultListModel<String> notifications) {
        this.notifications = notifications;
    }

    @Override
    public void showNotification(String content) {
        notifications.addElement("[PERSONAL] " + content);
    }

    @Override
    public void displayNotifications(List<Notification> notificationsList) {
        notifications.clear();
        for (Notification n : notificationsList) {
            notifications.addElement("[PERSONAL] " + n.getContent());
        }
    }

    // Rounded border implementation to be compatible with the NotificationView class
    public static class RoundedBorder implements Border {
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
