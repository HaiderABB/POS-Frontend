package SCD.controllers.SuperAdminControllers;

import java.awt.Window;
import java.util.function.Consumer;

import javax.swing.JFrame;
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
import SCD.model.models.Employee;
import SCD.ui.SuperAdmin.Sidebar;

public class SidebarController {

    private JPanel sidebarPanel;

    public SidebarController() {
        Sidebar sideBar = new Sidebar(employee);
    }

    Employee employee;

    public SidebarController(JPanel sidebarPanel, Employee employee) {
        this.sidebarPanel = sidebarPanel;
        this.employee = employee;
    }

    public void openDashboard() {
        navigateTo(SuperAdminDashboardController::new, employee);
    }

    public void openAddBranchPage() {
        navigateTo(AddBranchController::new, employee);
    }

    public void openUpdateBranchPage() {
        navigateTo(UpdateBranchController::new, employee);
    }

    public void openDeleteBranchPage() {
        navigateTo(DeleteBranchController::new, employee);
    }

    public void openViewBranchesPage() {
        navigateTo(ViewBranchesController::new, employee);
    }

    public void openAddBranchManagerPage() {
        navigateTo(AddBranchManagerController::new, employee);
    }

    public void openUpdateBranchManagerPage() {
        navigateTo(UpdateBranchManagerController::new, employee);
    }

    public void openDeleteBranchManagerPage() {
        navigateTo(DeleteBranchManagerController::new, employee);
    }

    public void openViewBranchManagersPage() {
        navigateTo(ViewBranchManagersController::new, employee);
    }

    public void openSystemSettingsPage() {
        navigateTo(SystemSettingsController::new, employee);
    }

    public void performLogout() {
        navigateTo(MainMenuController::new, null);

    }

    private void navigateTo(Consumer<Employee> navigationTask, Employee employee) {
        SwingUtilities.invokeLater(() -> {
            // Get the current window
            Window currentWindow = SwingUtilities.getWindowAncestor(sidebarPanel);
            if (currentWindow instanceof JFrame) {
                ((JFrame) currentWindow).dispose(); // Dispose of the current window
            }
            // Pass the object to the navigation task
            navigationTask.accept(employee);
        });
    }

    public void openViewReportsPage() {
    }
}
