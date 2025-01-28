package SCD.controllers.SuperAdminControllers;

import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import SCD.controllers.CommonControllers.MainMenuController;
import SCD.controllers.SuperAdminControllers.ManageBranchesController.AddBranchController;
import SCD.controllers.SuperAdminControllers.ManageBranchesController.DeleteBranchController;
import SCD.controllers.SuperAdminControllers.ManageBranchesController.UpdateBranchController;
import SCD.controllers.SuperAdminControllers.ManageBranchesController.ViewBranchesController;
import SCD.controllers.SuperAdminControllers.ManagesBranchManagerController.AddBranchManagerController;
import SCD.controllers.SuperAdminControllers.ManagesBranchManagerController.DeleteBranchManagerController;
import SCD.controllers.SuperAdminControllers.ManagesBranchManagerController.UpdateBranchManagerController;
import SCD.controllers.SuperAdminControllers.ManagesBranchManagerController.ViewBranchManagersController;
import SCD.controllers.SuperAdminControllers.Reports.ViewReportsController;
import SCD.ui.SuperAdmin.Sidebar;

public class SidebarController {

    private JPanel sidebarPanel;

    public SidebarController() {
        Sidebar sideBar = new Sidebar();
    }

    public SidebarController(JPanel sidebarPanel) {
        this.sidebarPanel = sidebarPanel;
    }

    public void openDashboard() {
        navigateTo(SuperAdminDashboardController::new);
    }

    public void openAddBranchPage() {
        navigateTo(AddBranchController::new);
    }

    public void openUpdateBranchPage() {
        navigateTo(UpdateBranchController::new);
    }

    public void openDeleteBranchPage() {
        navigateTo(DeleteBranchController::new);
    }

    public void openViewBranchesPage() {
        navigateTo(ViewBranchesController::new);
    }

    public void openAddBranchManagerPage() {
        navigateTo(AddBranchManagerController::new);
    }

    public void openUpdateBranchManagerPage() {
        navigateTo(UpdateBranchManagerController::new);
    }

    public void openDeleteBranchManagerPage() {
        navigateTo(DeleteBranchManagerController::new);
    }

    public void openViewBranchManagersPage() {
        navigateTo(ViewBranchManagersController::new);
    }

    public void openReportsPage() {
        navigateTo(ViewReportsController::new);
    }

    public void openSystemSettingsPage() {
        navigateTo(SystemSettingsController::new);
    }

    public void performLogout() {

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Logout",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            for (Window window : Window.getWindows()) {
                if (window.isShowing()) {
                    window.dispose(); // scd- proj initClose the window
                }
            }
            navigateTo(MainMenuController::new);
        } else {
            return;
        }

    }

    private void navigateTo(Runnable navigationTask) {
        SwingUtilities.invokeLater(() -> {
            // scd- proj initGet the current window
            Window currentWindow = SwingUtilities.getWindowAncestor(sidebarPanel);
            if (currentWindow instanceof JFrame) {
                ((JFrame) currentWindow).dispose(); // scd- proj initDispose of the current window
            }
            // scd- proj initPass the object to the navigation task
            navigationTask.run();
            ;
        });
    }

}
