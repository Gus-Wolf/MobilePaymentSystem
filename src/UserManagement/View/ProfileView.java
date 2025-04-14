package UserManagement.View;

import UserManagement.BankLinker;
import UserManagement.BankLinkerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileView {
    public static void main(String[] args) {
        // Create frame
        JFrame frame = new JFrame("Profile Page - Mobile Payment System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        // Top panel: Profile info
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
        profilePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel nameLabel = new JLabel("Name: John Doe");
        JLabel emailLabel = new JLabel("Email: john.doe@example.com");
        profilePanel.add(nameLabel);
        profilePanel.add(emailLabel);

        // Middle panel: Link bank account button
        JPanel buttonPanel = new JPanel();
        JButton linkBankBtn = new JButton("Link Bank Account");
        buttonPanel.add(linkBankBtn);

        // Hidden panel: Bank account form (Progressive Disclosure)
        JPanel bankFormPanel = new JPanel();
        bankFormPanel.setLayout(new BoxLayout(bankFormPanel, BoxLayout.Y_AXIS));
        bankFormPanel.setBorder(BorderFactory.createTitledBorder("Bank Account Details"));
        bankFormPanel.setVisible(false); // initially hidden

        JTextField bankNameField = new JTextField(20);
        bankNameField.setMaximumSize(bankNameField.getPreferredSize());
        bankNameField.setBorder(BorderFactory.createTitledBorder("Bank Name"));

        JTextField accountNumberField = new JTextField(20);
        accountNumberField.setMaximumSize(accountNumberField.getPreferredSize());
        accountNumberField.setBorder(BorderFactory.createTitledBorder("Account Number"));

        JPasswordField routingNumberField = new JPasswordField(20);
        routingNumberField.setMaximumSize(routingNumberField.getPreferredSize());
        routingNumberField.setBorder(BorderFactory.createTitledBorder("Routing Number"));

        JButton submitBtn = new JButton("Submit");
        JLabel confirmationLabel = new JLabel();
        confirmationLabel.setVisible(false);

        bankFormPanel.add(bankNameField);
        bankFormPanel.add(accountNumberField);
        bankFormPanel.add(routingNumberField);
        bankFormPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        bankFormPanel.add(submitBtn);

        // Actions
        linkBankBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bankFormPanel.setVisible(true);
                linkBankBtn.setEnabled(false);
                frame.revalidate(); // refresh layout
            }
        });

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bankName = bankNameField.getText();
                String accNum = accountNumberField.getText();
                String routing = new String(routingNumberField.getPassword());

                BankLinker linker = BankLinkerFactory.getBankLinker(bankName);
                String result = linker.linkAccount(accNum, routing);

                confirmationLabel.setText("✅ " + result);
                confirmationLabel.setVisible(true);
                frame.revalidate();
            }
        });


        // Add panels to frame
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPanel.add(buttonPanel);
        centerPanel.add(bankFormPanel);
        centerPanel.add(confirmationLabel);

        frame.add(profilePanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);

        // Show frame
        frame.setVisible(true);
    }
}
