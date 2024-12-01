package SCD.ui;

import SCD.controllers.LoginPageController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SuperAdminLoginPage extends LoginPage {

    private JPasswordField passwordField;
    private JButton togglePasswordVisibility;

    public SuperAdminLoginPage() {
        setTitle("Super Admin Login");

        // Customize the login panel for Super Admin
        JLabel superAdminHeading = new JLabel("Super Admin Login", JLabel.CENTER);
        superAdminHeading.setBounds(800, 150, 300, 30); // Initially outside the frame (for animation)
        superAdminHeading.setFont(new Font("Arial", Font.BOLD, 26));
        superAdminHeading.setForeground(new Color(255, 102, 102));
        getContentPane().add(superAdminHeading);

        JLabel instructionText = new JLabel("Please enter your credentials", JLabel.CENTER);
        instructionText.setBounds(250, 190, 300, 20);
        instructionText.setFont(new Font("Arial", Font.PLAIN, 16));
        instructionText.setForeground(Color.GRAY);
        getContentPane().add(instructionText);

        // Override Password Field with a toggle feature
        passwordField = new JPasswordField();
        passwordField.setBounds(250, 330, 300, 50); // Center-aligned
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));
        passwordField.setEchoChar('*'); // Hide password characters by default
        getContentPane().add(passwordField);

        // Toggle Password Visibility Button
        togglePasswordVisibility = new JButton("Show");
        togglePasswordVisibility.setBounds(560, 350, 70, 30);
        togglePasswordVisibility.setFocusPainted(false);
        togglePasswordVisibility.setBackground(Color.LIGHT_GRAY);
        togglePasswordVisibility.setFont(new Font("Arial", Font.PLAIN, 12));
        getContentPane().add(togglePasswordVisibility);

        // Event Listener for toggling password visibility
        togglePasswordVisibility.addActionListener(e -> {
            if (String.valueOf(passwordField.getEchoChar()).equals("*")) {
                passwordField.setEchoChar((char) 0); // Show password
                togglePasswordVisibility.setText("Hide");
            } else {
                passwordField.setEchoChar('*'); // Hide password
                togglePasswordVisibility.setText("Show");
            }
        });

        // Start heading animation
        animateHeading(superAdminHeading);
    }

    private void animateHeading(JLabel heading) {
        Timer animationTimer = new Timer(10, new AbstractAction() {
            int x = 800; // Starting x-position (off-screen)
            @Override
            public void actionPerformed(ActionEvent e) {
                x -= 10; // Move heading to the left
                heading.setBounds(x, 150, 300, 30);
                if (x <= 250) { // Stop animation when heading reaches final position
                    heading.setBounds(250, 150, 300, 30);
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        animationTimer.start();
    }

    public static void main(String[] args) {
        UserModel model = new UserModel("", "");
        SuperAdminLoginPage view = new SuperAdminLoginPage();
        LoginPageController controller = new LoginPageController(model, view);
        view.setVisible(true);
    }
}
