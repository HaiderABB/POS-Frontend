package SCD.ui.SuperAdmin;

import SCD.ui.Common.ButtonFactory;
import SCD.ui.SuperAdmin.ManageBranchManager.AddBranchManagerPage;
import SCD.ui.SuperAdmin.ManageBranchManager.DeleteBranchManagerPage;
import SCD.ui.SuperAdmin.ManageBranchManager.ViewBranchManagersPage;
import SCD.ui.SuperAdmin.ManageBranches.AddBranchPage;
import SCD.ui.SuperAdmin.ManageBranches.DeleteBranchPage;
import SCD.ui.SuperAdmin.ManageBranches.ViewBranchesPage;


import javax.swing.*;
import java.awt.*;

public class Sidebar extends JPanel {

    public Sidebar() {
        setPreferredSize(new Dimension(240, 800));
        setBackground(new Color(255, 102, 102));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel logo = new JLabel(new ImageIcon("path/to/logo.png")); // Replace with the actual path to your logo
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(logo);

        add(createDashboardButton());

        add(createAddBranchButton());
        add(createDeleteBranchButton());
        add(createViewBranchesButton());
        add(createAddBranchManagerButton());
        add(createDeleteBranchManagerButton());
        add(createViewBranchManagersButton());
        add(createViewReportsButton());
        add(createSystemSettingsButton());
        add(createLogoutButton());
    }

    private JButton createDashboardButton() {
        JButton button = ButtonFactory.createStyledButton("Dashboard");
        button.addActionListener(e -> openDashboard());
        return button;
    }


    private JButton createAddBranchButton() {
        JButton button = ButtonFactory.createStyledButton("Add Branch");
        button.addActionListener(e -> openAddBranchPage());
        return button;
    }

    private JButton createDeleteBranchButton() {
        JButton button = ButtonFactory.createStyledButton("Delete Branch");
        button.addActionListener(e -> openDeleteBranchPage());
        return button;
    }

    private JButton createViewBranchesButton() {
        JButton button = ButtonFactory.createStyledButton("View Branches");
        button.addActionListener(e -> openViewBranchesPage());
        return button;
    }

    private JButton createAddBranchManagerButton() {
        JButton button = ButtonFactory.createStyledButton("Add Branch Manager");
        button.addActionListener(e -> navigateToPage(new AddBranchManagerPage()));
        return button;
    }

    private JButton createDeleteBranchManagerButton() {
        JButton button = ButtonFactory.createStyledButton("Delete Branch Manager");
        button.addActionListener(e -> navigateToPage(new DeleteBranchManagerPage()));
        return button;
    }

    private JButton createViewBranchManagersButton() {
        JButton button = ButtonFactory.createStyledButton("View Branch Managers");
        button.addActionListener(e -> navigateToPage(new ViewBranchManagersPage()));
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

    private void openAddBranchPage() {
        navigateToPage(new AddBranchPage());
    }

    private void openDeleteBranchPage() {
        navigateToPage(new DeleteBranchPage());
    }

    private void openViewBranchesPage() {
        navigateToPage(new ViewBranchesPage());
    }

    private void openAddBranchManagerPage() {
        navigateToPage(new AddBranchManagerPage());
    }

    private void openDeleteBranchManagerPage() {
        navigateToPage(new DeleteBranchManagerPage());
    }

    private void openViewBranchManagersPage() {
        navigateToPage(new ViewBranchManagersPage());
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
