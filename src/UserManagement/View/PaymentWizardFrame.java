package UserManagement.View;

import javax.swing.*;
import java.awt.*;

public class PaymentWizardFrame extends JFrame {
    public PaymentWizardFrame() {
        setTitle("Peer-to-Peer Payment Wizard");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new CardLayout());
    }
}
