package UserManagement.View;

import UserManagement.BankLinker;
import UserManagement.BankLinkerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileView extends JFrame {

    public ProfileView() {
        setTitle("Profile Page - Mobile Payment System");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null); // Center window
        setLayout(new BorderLayout(10, 10));

        // Top panel: Profile info
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
        profilePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = new JLabel("Name: John Doe"); // <--- Hardcoded here
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 18));

        profilePanel.add(nameLabel);
        profilePanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Middle panel: Link bank account button
        JPanel buttonPanel = new JPanel();
        JButton linkBankBtn = new JButton("Link Bank Account");
        buttonPanel.add(linkBankBtn);

        // Hidden panel: Bank account form
        JPanel bankFormPanel = new JPanel();
        bankFormPanel.setLayout(new BoxLayout(bankFormPanel, BoxLayout.Y_AXIS));
        bankFormPanel.setBorder(BorderFactory.createTitledBorder("Bank Account Details"));
        bankFormPanel.setVisible(false);

        JTextField bankNameField = new JTextField(20);
        bankNameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        bankNameField.setBorder(BorderFactory.createTitledBorder("Bank Name"));

        JTextField accountNumberField = new JTextField(20);
        accountNumberField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        accountNumberField.setBorder(BorderFactory.createTitledBorder("Account Number"));

        JPasswordField routingNumberField = new JPasswordField(20);
        routingNumberField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        routingNumberField.setBorder(BorderFactory.createTitledBorder("Routing Number"));

        JButton submitBtn = new JButton("Submit");
        JLabel confirmationLabel = new JLabel();
        confirmationLabel.setVisible(false);

        bankFormPanel.add(bankNameField);
        bankFormPanel.add(accountNumberField);
        bankFormPanel.add(routingNumberField);
        bankFormPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        bankFormPanel.add(submitBtn);

        // Button Actions
        linkBankBtn.addActionListener(e -> {
            bankFormPanel.setVisible(true);
            linkBankBtn.setEnabled(false);
            revalidate(); // Refresh layout
        });

        submitBtn.addActionListener(e -> {
            String bankName = bankNameField.getText();
            String accNum = accountNumberField.getText();
            String routing = new String(routingNumberField.getPassword());

            BankLinker linker = BankLinkerFactory.getBankLinker(bankName);
            String result = linker.linkAccount(accNum, routing);

            confirmationLabel.setText("✅ " + result);
            confirmationLabel.setVisible(true);
            revalidate();
        });

        // Add panels
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        centerPanel.add(buttonPanel);
        centerPanel.add(bankFormPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(confirmationLabel);

        add(profilePanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ProfileView::new);
    }
}
