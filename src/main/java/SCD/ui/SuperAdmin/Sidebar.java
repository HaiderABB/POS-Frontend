package SCD.ui.SuperAdmin;

import SCD.ui.Common.ButtonFactory;
import SCD.ui.SuperAdmin.ManageBranchesPage;
import SCD.ui.SuperAdmin.ManageUsersPage;
import SCD.ui.SuperAdmin.SystemSettingsPage;
import SCD.ui.SuperAdmin.ViewReportsPage;

import javax.swing.*;
import java.awt.*;

public class Sidebar extends JPanel {

    public Sidebar() {
        setPreferredSize(new Dimension(200, 700));
        setBackground(new Color(255, 102, 102));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel logo = new JLabel(new ImageIcon("path/to/logo.png")); // Replace with the actual path to your logo
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(logo);


        add(createDashboardButton());
        add(createManageUsersButton());
        add(createManageBranchesButton());
        add(createViewReportsButton());
        add(createSystemSettingsButton());
        add(createLogoutButton());
    }

    private JButton createDashboardButton() {
        JButton button = ButtonFactory.createStyledButton("Dashboard");
        button.addActionListener(e -> openDashboard());
        return button;
    }

    private JButton createManageUsersButton() {
        JButton button = ButtonFactory.createStyledButton("Manage Users");
        button.addActionListener(e -> openManageUsersPage());
        return button;
    }

    private JButton createManageBranchesButton() {
        JButton button = ButtonFactory.createStyledButton("Manage Branches");
        button.addActionListener(e -> openManageBranchesPage());
        return button;
    }

    private JButton createViewReportsButton() {
        JButton button = ButtonFactory.createStyledButton("View Reports");
        button.addActionListener(e -> openViewReportsPage());
        return button;
    }

    private JButton createSystemSettingsButton() {
        JButton button = ButtonFactory.createStyledButton("System Settings");
        button.addActionListener(e -> openSystemSettingsPage());
        return button;
    }

    private JButton createLogoutButton() {
        JButton button = ButtonFactory.createStyledButton("Logout");
        button.addActionListener(e -> performLogout());
        return button;
    }


    private void openDashboard() {
       navigateToPage(new SuperAdminDashboard());

    }

    private void openManageUsersPage() {
        navigateToPage(new ManageUsersPage());
    }

    private void openManageBranchesPage() {
        navigateToPage(new ManageBranchesPage());
    }

    private void openViewReportsPage() {
        navigateToPage(new ViewReportsPage());
    }

    private void openSystemSettingsPage() {
        navigateToPage(new SystemSettingsPage());
    }

    private void navigateToPage(JFrame page) {
        SwingUtilities.invokeLater(() -> {

            Window topLevelWindow = SwingUtilities.getWindowAncestor(this);
            if (topLevelWindow instanceof JFrame) {
                ((JFrame) topLevelWindow).dispose();
            }
            page.setVisible(true);
        });
    }


    private void performLogout() {
        JOptionPane.showMessageDialog(this, "Logging out...");
        System.exit(0);
    }
}
