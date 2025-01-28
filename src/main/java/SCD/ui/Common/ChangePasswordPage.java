package SCD.ui.Common;

import javax.swing.*;
import java.awt.*;

public class ChangePasswordPage extends JFrame implements Props {

    private final JPasswordField newPasswordField;
    private final JPasswordField confirmPasswordField;
    private final JButton changePasswordButton;

    public ChangePasswordPage() {
        setTitle("Change Password");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JPanel changePasswordPanel = new JPanel();
        changePasswordPanel.setLayout(null);
        changePasswordPanel.setBackground(Props.fg);
        changePasswordPanel.setBounds(0, 0, 800, 600);

        JLabel title = new JLabel("Change Password", JLabel.CENTER);
        title.setBounds(250, 150, 300, 30);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        changePasswordPanel.add(title);

        newPasswordField = new JPasswordField();
        newPasswordField.setBounds(250, 200, 300, 50);
        newPasswordField.setBorder(BorderFactory.createTitledBorder("New Password"));
        changePasswordPanel.add(newPasswordField);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(250, 270, 300, 50);
        confirmPasswordField.setBorder(BorderFactory.createTitledBorder("Confirm Password"));
        changePasswordPanel.add(confirmPasswordField);

        changePasswordButton = new JButton("Change Password");
        changePasswordButton.setBounds(250, 340, 300, 50);
        changePasswordButton.setBackground(Props.bg);
        changePasswordButton.setForeground(Props.fg);
        changePasswordButton.setFont(new Font("Arial", Font.BOLD, 18));
        changePasswordPanel.add(changePasswordButton);

        add(changePasswordPanel);
        setLocationRelativeTo(null);
    }

    public String getNewPassword() {
        return new String(newPasswordField.getPassword());
    }

    public String getConfirmPassword() {
        return new String(confirmPasswordField.getPassword());
    }

    public JButton getChangePasswordButton() {
        return changePasswordButton;
    }
}
