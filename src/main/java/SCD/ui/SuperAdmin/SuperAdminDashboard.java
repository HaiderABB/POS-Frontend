package SCD.ui.SuperAdmin;

import SCD.ui.Common.NavBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SuperAdminDashboard extends JFrame {

    private Sidebar sidebar;
    private NavBar navBar;
    private JPanel mainContent;
    private boolean isSidebarVisible = true;

    public SuperAdminDashboard() {
        setTitle("Super Admin Dashboard");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        sidebar = new Sidebar();
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        navBar = new NavBar();
        contentPanel.add(navBar, BorderLayout.NORTH);

        navBar.setTitle("Dashboard");

        mainContent = createMainContent();
        contentPanel.add(mainContent, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }



    private JPanel createMainContent() {
        JPanel mainContent = new JPanel(new GridLayout(2, 2, 20, 20));
        mainContent.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainContent.setBackground(Color.WHITE);

        mainContent.add(createCard("Manage Users", "C:\\Users\\AMMAR\\Desktop\\icons\\users.png", this::openManageUsersPage));
        mainContent.add(createCard("Manage Branches", "C:\\Users\\AMMAR\\Desktop\\icons\\branches.png", this::openManageBranchesPage));
        mainContent.add(createCard("View Reports", "C:\\Users\\AMMAR\\Desktop\\icons\\reports.png", this::openViewReportsPage));
        mainContent.add(createCard("System Settings", "C:\\Users\\AMMAR\\Desktop\\icons\\settings.png", this::openSystemSettingsPage));

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

    private void openManageUsersPage(ActionEvent e) {
        navBar.setTitle("Manage Users");
        navigateToPage(new ManageUsersPage());
    }

    private void openManageBranchesPage(ActionEvent e) {
        navBar.setTitle("Manage Branches");
        navigateToPage(new ManageBranchesPage());
    }

    private void openViewReportsPage(ActionEvent e) {
        navBar.setTitle("View Reports");
        navigateToPage(new ViewReportsPage());
    }

    private void openSystemSettingsPage(ActionEvent e) {
        navBar.setTitle("System Settings");
        navigateToPage(new SystemSettingsPage());
    }

    private void navigateToPage(JFrame page) {
        SwingUtilities.invokeLater(() -> {
            page.setVisible(true);
            dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SuperAdminDashboard dashboard = new SuperAdminDashboard();
            dashboard.setVisible(true);
        });
    }
}
