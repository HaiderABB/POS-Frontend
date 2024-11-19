package SCD.Project;

import javax.swing.*;
import java.awt.*;

public class SplashScreenView extends JFrame {
    public SplashScreenView() {
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setUndecorated(true);
        setOpacity(1.0f);

        JPanel splashPanel = new JPanel();
        splashPanel.setBackground(new Color(255, 102, 102));
        splashPanel.setLayout(null);

        JLabel logo = new JLabel(new ImageIcon("C:\\Users\\AMMAR\\Desktop\\Parhai\\SCD\\POS-Frontend\\logo5.png"));
        logo.setBounds(350, 150, 100, 100);
        splashPanel.add(logo);

        JLabel welcomeText = new JLabel("WELCOME TO POS", JLabel.CENTER);
        welcomeText.setBounds(250, 300, 300, 50);
        welcomeText.setForeground(Color.WHITE);
        welcomeText.setFont(new Font("Arial", Font.BOLD, 24));
        splashPanel.add(welcomeText);

        add(splashPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }
}