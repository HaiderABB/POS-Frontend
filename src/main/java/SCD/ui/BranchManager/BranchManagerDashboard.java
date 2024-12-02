package SCD.ui.BranchManager;

import SCD.ui.Common.NavBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BranchManagerDashboard extends JFrame {

    private BranchSidebar sidebar;
    private JPanel mainContent;
    private NavBar navBar;

    public BranchManagerDashboard() {
        setTitle("Branch Manager Dashboard");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        sidebar = new BranchSidebar();
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);
        navBar = new NavBar();
        contentPanel.add(navBar, BorderLayout.NORTH);

        // Set initial NavBar title
        navBar.setTitle("Branch Manager Dashboard");

        mainContent = createMainContent();
        contentPanel.add(mainContent, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    private JPanel createMainContent() {
        JPanel mainContent = new JPanel(new GridLayout(2, 2, 20, 20));
        mainContent.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainContent.setBackground(Color.WHITE);

        mainContent.add(createCard("Manage Cashiers", "C:\\Users\\AMMAR\\Desktop\\icons\\cashier.png", this::openManageCashiersPage));
        mainContent.add(createCard("Manage Data Entry Operators", "C:\\Users\\AMMAR\\Desktop\\icons\\dataentry.png", this::openManageDataEntryOperatorsPage));
        mainContent.add(createCard("Settings", "C:\\Users\\AMMAR\\Desktop\\icons\\settings.png", this::openSettingsPage));

        return mainContent;
    }

    private JButton createCard(String title, String iconPath, ActionListener action) {
        JButton button = new JButton(title, new ImageIcon(iconPath));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setFocusPainted(false);
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        button.addActionListener(action);
        return button;
    }

    private void openManageCashiersPage(ActionEvent e) {
        navigateToPage(new ManageCashiersPage());
    }

    private void openManageDataEntryOperatorsPage(ActionEvent e) {
        navigateToPage(new ManageDataEntryOperatorsPage());
    }

    private void openSettingsPage(ActionEvent e) {
        navigateToPage(new SettingsPage());
    }

    private void navigateToPage(JFrame page) {
        SwingUtilities.invokeLater(() -> {
            page.setVisible(true);
            dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BranchManagerDashboard dashboard = new BranchManagerDashboard();
            dashboard.setVisible(true);
        });
    }
}
