package SCD.controllers.SuperAdminControllers;

import SCD.ui.SuperAdmin.*;
import SCD.ui.SuperAdmin.ManageBranchManager.*;
import SCD.ui.SuperAdmin.ManageBranches.*;
import SCD.ui.SuperAdmin.ViewReportsPage;

import javax.swing.*;
import java.awt.*;

public class SidebarController {

    private final JPanel sidebarPanel;

    public SidebarController(JPanel sidebarPanel) {
        this.sidebarPanel = sidebarPanel;
    }

    public void openDashboard() {
        navigateToPage(new SuperAdminDashboard());
    }

    public void openAddBranchPage() {
        navigateToPage(new AddBranchPage());
    }

    public void openUpdateBranchPage() {
        navigateToPage(new UpdateBranchPage());
    }

    public void openDeleteBranchPage() {
        navigateToPage(new DeleteBranchPage());
    }

    public void openViewBranchesPage() {
        navigateToPage(new ViewBranchesPage());
    }

    public void openAddBranchManagerPage() {
        navigateToPage(new AddBranchManagerPage());
    }

    public void openUpdateBranchManagerPage() {
        navigateToPage(new UpdateBranchManagerPage());
    }

    public void openDeleteBranchManagerPage() {
        navigateToPage(new DeleteBranchManagerPage());
    }

    public void openViewBranchManagersPage() {
        navigateToPage(new ViewBranchManagersPage());
    }

    public void openViewReportsPage() {
        navigateToPage(new ViewReportsPage());
    }

    public void openSystemSettingsPage() {
        navigateToPage(new SystemSettingsPage());
    }

    public void performLogout() {
        JOptionPane.showMessageDialog(sidebarPanel, "Logging out...");
        System.exit(0);
    }

    private void navigateToPage(JFrame page) {
        SwingUtilities.invokeLater(() -> {
            Window currentWindow = SwingUtilities.getWindowAncestor(sidebarPanel);

            if (currentWindow instanceof JFrame) {
                ((JFrame) currentWindow).dispose();
            }

            page.setLocationRelativeTo(null); // Center the new frame
            page.setVisible(true);
        });
    }


}