package SCD.controllers.SuperAdminControllers;

import SCD.controllers.CommonControllers.MainMenuController;
import SCD.controllers.SuperAdminControllers.ManageBranchesController.AddBranchController;
import SCD.controllers.SuperAdminControllers.ManageBranchesController.DeleteBranchController;
import SCD.controllers.SuperAdminControllers.ManageBranchesController.UpdateBranchController;
import SCD.controllers.SuperAdminControllers.ManageBranchesController.ViewBranchesController;
import SCD.controllers.SuperAdminControllers.ManagesBranchManagerController.AddBranchManagerController;
import SCD.controllers.SuperAdminControllers.ManagesBranchManagerController.DeleteBranchManagerController;
import SCD.controllers.SuperAdminControllers.ManagesBranchManagerController.UpdateBranchManagerController;
import SCD.controllers.SuperAdminControllers.ManagesBranchManagerController.ViewBranchManagersController;

import javax.swing.*;
import java.awt.*;

public class SidebarController {

    private final JPanel sidebarPanel;

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

    public void openSystemSettingsPage() {
        navigateTo(SystemSettingsController::new);
    }

    public void performLogout() {
        navigateTo(MainMenuController::new);

    }

    private void navigateTo(Runnable navigationTask) {
        SwingUtilities.invokeLater(() -> {

            Window currentWindow = SwingUtilities.getWindowAncestor(sidebarPanel);
            if (currentWindow instanceof JFrame) {
                ((JFrame) currentWindow).dispose();
            }
            navigationTask.run();
        });
    }

    public void openViewReportsPage() {
    }
}
