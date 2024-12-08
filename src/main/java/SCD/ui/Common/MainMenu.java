package SCD.ui.Common;

import SCD.controllers.CommonControllers.MainMenuController;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    public MainMenu() {
        setTitle("Main Menu");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(null);
        menuPanel.setBackground(Color.WHITE);
        menuPanel.setBounds(0, 0, 800, 600);

        JLabel logo = new JLabel(new ImageIcon("C:\\Users\\AMMAR\\Desktop\\Parhai\\SCD\\POS-Frontend\\logo5.png"));
        logo.setBounds(350, 30, 100, 100);
        menuPanel.add(logo);

        JLabel menuTitle = new JLabel("Main Menu", JLabel.CENTER);
        menuTitle.setBounds(250, 150, 300, 30);
        menuTitle.setFont(new Font("Arial", Font.BOLD, 22));
        menuTitle.setForeground(new Color(255, 102, 102));
        menuPanel.add(menuTitle);

        addRoleButton(menuPanel, "Super Admin Panel", "SM-", "Super Admin", 250, 220);
        addRoleButton(menuPanel, "Branch Manager", "BM-", "Branch Manager", 250, 290);
        addRoleButton(menuPanel, "Cashier", "CM-", "Cashier", 250, 360);
        addRoleButton(menuPanel, "Data Entry Operator", "DM-", "Data Entry Operator", 250, 430);

        add(menuPanel);
        setLocationRelativeTo(null);
    }

    private void addRoleButton(JPanel panel, String text, String prefix, String role, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 300, 50);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(255, 102, 102));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);

        button.addActionListener(e -> {
            MainMenuController.handleRoleButtonClick(this, prefix, role);
        });

        panel.add(button);
    }
}
