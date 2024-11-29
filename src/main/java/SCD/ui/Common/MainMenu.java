package SCD.ui.Common;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu extends JFrame {
    private JButton superAdminButton;
    private JButton adminButton;
    private JButton cashierButton;
    private JButton dataEntryButton;

    public MainMenu() {
        // Frame settings
        setTitle("Main Menu");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Panel for the Main Menu
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(null);
        menuPanel.setBackground(Color.WHITE);
        menuPanel.setBounds(0, 0, 800, 600);

        // Logo or Icon at the Top
        JLabel logo = new JLabel(new ImageIcon("C:\\Users\\AMMAR\\Desktop\\Parhai\\SCD\\POS-Frontend\\logo5.png"));
        logo.setBounds(350, 30, 100, 100); // Centered at the top
        menuPanel.add(logo);

        // Welcome Text
        JLabel menuTitle = new JLabel("Main Menu", JLabel.CENTER);
        menuTitle.setBounds(250, 150, 300, 30); // Centered
        menuTitle.setFont(new Font("Arial", Font.BOLD, 22));
        menuTitle.setForeground(new Color(255, 102, 102));
        menuPanel.add(menuTitle);

        // Buttons
        superAdminButton = createStyledButton("Super Admin Panel", 250, 220);
        menuPanel.add(superAdminButton);

        adminButton = createStyledButton("Admin/Branch Manager", 250, 290);
        menuPanel.add(adminButton);

        cashierButton = createStyledButton("Cashier", 250, 360);
        menuPanel.add(cashierButton);

        dataEntryButton = createStyledButton("Data Entry Operator", 250, 430);
        menuPanel.add(dataEntryButton);

        // Add the panel to the frame
        add(menuPanel);
        setLocationRelativeTo(null);
    }

    private JButton createStyledButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 300, 50);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(255, 102, 102));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);

        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(200, 50, 50)); // Darker red on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(255, 102, 102)); // Original color
            }
        });

        return button;
    }

    // Getters for the buttons
    public JButton getSuperAdminButton() {
        return superAdminButton;
    }

    public JButton getAdminButton() {
        return adminButton;
    }

    public JButton getCashierButton() {
        return cashierButton;
    }

    public JButton getDataEntryButton() {
        return dataEntryButton;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.setVisible(true);
        });
    }
}
