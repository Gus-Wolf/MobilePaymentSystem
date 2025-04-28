package UserAuthentication.View;

import UserAuthentication.Model.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private Runnable onLoginSuccess; // <-- new field

    public LoginGUI(Runnable onLoginSuccess) {
        this.onLoginSuccess = onLoginSuccess; // <-- capture the callback

        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 450);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        loginButton = new JButton("Login");
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                authenticateUser();
            }
        });

        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    private void authenticateUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (Database.authenticate(username, password)) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            this.dispose(); // Close login window
            new NotificationManagement.Controller.NotificationController();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password");
        }
    }
}
