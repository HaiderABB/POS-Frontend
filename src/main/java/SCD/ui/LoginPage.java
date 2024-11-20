package SCD.ui;

import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginPage() {
        setTitle("Login");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setBounds(0, 0, 800, 600);

        JLabel logo = new JLabel(new ImageIcon("C:\\Users\\AMMAR\\Desktop\\Parhai\\SCD\\POS-Frontend\\logo5.png"));
        logo.setBounds(350, 50, 100, 100);
        loginPanel.add(logo);

        JLabel welcomeText = new JLabel("Welcome Back,", JLabel.CENTER);
        welcomeText.setBounds(250, 180, 300, 30);
        welcomeText.setFont(new Font("Arial", Font.BOLD, 22));
        loginPanel.add(welcomeText);

        JLabel signInText = new JLabel("Sign in to continue", JLabel.CENTER);
        signInText.setBounds(250, 220, 300, 20);
        signInText.setFont(new Font("Arial", Font.PLAIN, 16));
        signInText.setForeground(Color.GRAY);
        loginPanel.add(signInText);

        usernameField = new JTextField();
        usernameField.setBounds(250, 260, 300, 50);
        usernameField.setBorder(BorderFactory.createTitledBorder("Username"));
        loginPanel.add(usernameField);

        passwordField = new JPasswordField();
        passwordField.setBounds(250, 330, 300, 50);
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));
        loginPanel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(250, 400, 300, 50);
        loginButton.setBackground(new Color(255, 102, 102));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        loginPanel.add(loginButton);

        JLabel forgotPassword = new JLabel("Forgot Password?", JLabel.RIGHT);
        forgotPassword.setBounds(400, 460, 150, 20);
        forgotPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        forgotPassword.setForeground(Color.GRAY);
        loginPanel.add(forgotPassword);

        add(loginPanel);
        setLocationRelativeTo(null);
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public JButton getLoginButton() {
        return loginButton;
    }
}