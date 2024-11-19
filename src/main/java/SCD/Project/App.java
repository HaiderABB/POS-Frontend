package SCD.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

 class LoginPage extends JFrame {

    public static void main(String[] args) {
        // Create the Splash Screen
        JFrame splashScreen = new JFrame();
        splashScreen.setSize(800, 600); // Desktop ratio
        splashScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        splashScreen.setLayout(new BorderLayout());
        splashScreen.setUndecorated(true);

        // Splash Screen Content
        JPanel splashPanel = new JPanel();
        splashPanel.setBackground(new Color(255, 102, 102)); // Gradient-like red-pink shade
        splashPanel.setLayout(null);

        JLabel logo = new JLabel(new ImageIcon("C:\\Users\\AMMAR\\Desktop\\Parhai\\SCD\\POS-Frontend\\logo5.png")); // Add basketball icon
        logo.setBounds(350, 150, 100, 100); // Centered on desktop dimensions
        splashPanel.add(logo);

        JLabel welcomeText = new JLabel("WELCOME TO POS", JLabel.CENTER);
        welcomeText.setBounds(250, 300, 300, 50); // Center-aligned
        welcomeText.setForeground(Color.WHITE);
        welcomeText.setFont(new Font("Arial", Font.BOLD, 24)); // Larger font for desktop
        splashPanel.add(welcomeText);

        splashScreen.add(splashPanel, BorderLayout.CENTER);
        splashScreen.setLocationRelativeTo(null); // Center the window
        splashScreen.setVisible(true);

        // Create a Timer to close the splash screen after 3 seconds
        Timer timer = new Timer(3000, (ActionEvent e) -> {
            splashScreen.dispose(); // Close splash screen
            createLoginPage(); // Open login page
        });

        timer.setRepeats(false); // Ensure the timer only runs once
        timer.start();
    }

    public static void createLoginPage() {
        // Create the Login Frame
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setSize(800, 600); // Desktop ratio
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLayout(null);

        // Panel for Login Page
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setBounds(0, 0, 800, 600);

        // Icon
        JLabel logo = new JLabel(new ImageIcon("C:\\Users\\AMMAR\\Desktop\\Parhai\\SCD\\POS-Frontend\\logo5.png"));
        logo.setBounds(350, 50, 100, 100); // Adjust for desktop ratio
        loginPanel.add(logo);

        // Welcome Text
        JLabel welcomeText = new JLabel("Welcome Back,", JLabel.CENTER);
        welcomeText.setBounds(250, 180, 300, 30); // Centered
        welcomeText.setFont(new Font("Arial", Font.BOLD, 22));
        loginPanel.add(welcomeText);

        JLabel signInText = new JLabel("Sign in to continue", JLabel.CENTER);
        signInText.setBounds(250, 220, 300, 20);
        signInText.setFont(new Font("Arial", Font.PLAIN, 16));
        signInText.setForeground(Color.GRAY);
        loginPanel.add(signInText);

        // Mobile Input
        JTextField usernameField = new JTextField();
        usernameField.setBounds(250, 260, 300, 50); // Center-aligned
        usernameField.setBorder(BorderFactory.createTitledBorder("Username"));
        loginPanel.add(usernameField);

        // Password Input
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(250, 330, 300, 50); // Center-aligned
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));
        loginPanel.add(passwordField);

        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(250, 400, 300, 50); // Center-aligned
        loginButton.setBackground(new Color(255, 102, 102));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        loginPanel.add(loginButton);

        // Forgot Password
        JLabel forgotPassword = new JLabel("Forgot Password?", JLabel.RIGHT);
        forgotPassword.setBounds(400, 460, 150, 20); // Adjusted for desktop
        forgotPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        forgotPassword.setForeground(Color.GRAY);
        loginPanel.add(forgotPassword);

        // New User Sign-up
        JLabel signUpText = new JLabel("New user? Signup");
        signUpText.setBounds(250, 500, 300, 20); // Centered
        signUpText.setFont(new Font("Arial", Font.PLAIN, 14));
        signUpText.setForeground(new Color(255, 102, 102));
        signUpText.setHorizontalAlignment(SwingConstants.CENTER);
        loginPanel.add(signUpText);

        loginFrame.add(loginPanel);
        loginFrame.setLocationRelativeTo(null); // Center the window
        loginFrame.setVisible(true);
    }
}
