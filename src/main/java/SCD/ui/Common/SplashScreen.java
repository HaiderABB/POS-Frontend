package SCD.ui.Common;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SplashScreen extends JFrame {
    private JProgressBar progressBar;
    private JLabel logo;

    public SplashScreen() {
        // Frame settings
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setUndecorated(true);

        // Set custom cursor
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image cursorImage = toolkit.getImage("C:\\Users\\AMMAR\\Desktop\\Parhai\\SCD\\POS-Frontend\\cursor.png");
        Cursor customCursor = toolkit.createCustomCursor(cursorImage, new Point(0, 0), "Custom Cursor");
        setCursor(customCursor);

        // Splash Panel
        JPanel splashPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Gradient background
                Graphics2D g2d = (Graphics2D) g;
                Color startColor = new Color(255, 102, 102);
                Color endColor = new Color(255, 153, 153);
                GradientPaint gradient = new GradientPaint(0, 0, startColor, 0, getHeight(), endColor);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        splashPanel.setLayout(null);

        // Logo
        logo = new JLabel(new ImageIcon("C:\\Users\\AMMAR\\Desktop\\Parhai\\SCD\\POS-Frontend\\logo5.png"));
        logo.setBounds(350, 200, 100, 100);
        splashPanel.add(logo);

        // Welcome Text
        JLabel welcomeText = new JLabel("WELCOME TO POS", JLabel.CENTER);
        welcomeText.setBounds(250, 320, 300, 50);
        welcomeText.setForeground(Color.WHITE);
        welcomeText.setFont(new Font("Arial", Font.BOLD, 24));
        splashPanel.add(welcomeText);

        // Progress Bar
        progressBar = new JProgressBar(0, 100);
        progressBar.setBounds(250, 400, 300, 20);
        progressBar.setForeground(new Color(255, 102, 102));
        progressBar.setBackground(Color.WHITE);
        progressBar.setStringPainted(true);
        splashPanel.add(progressBar);

        // Add Panel
        add(splashPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);

        // Start animations and progress
        startAnimation();
    }

    private void startAnimation() {
        // Logo animation (move logo upwards)
        Timer logoTimer = new Timer(10, new AbstractAction() {
            int y = 200; // Initial y-position of the logo

            @Override
            public void actionPerformed(ActionEvent e) {
                y--;
                logo.setBounds(350, y, 100, 100);
                if (y <= 150) { // Stop animation when logo reaches desired position
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        logoTimer.start();

        // Progress bar animation
        Timer progressTimer = new Timer(30, new AbstractAction() {
            int progress = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                progress++;
                progressBar.setValue(progress);
                if (progress >= 100) {
                    ((Timer) e.getSource()).stop();
                    dispose();
                    new MainMenu().setVisible(true);
                }
            }
        });
        progressTimer.start();
    }

}
