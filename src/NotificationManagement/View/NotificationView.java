package NotificationManagement.View;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class NotificationView {

    public NotificationView() {
        // Constructor can be used for any required initialization
    }

    public void showNotification(String message) {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(null, message, "Notification", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}
