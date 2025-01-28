package SCD.controllers.BranchManagerControllers;

import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import SCD.controllers.BranchManagerControllers.CashierController.AddCashierController;
import SCD.controllers.BranchManagerControllers.CashierController.DeleteCashierController;
import SCD.controllers.BranchManagerControllers.CashierController.UpdateCashierController;
import SCD.controllers.BranchManagerControllers.CashierController.ViewCashiersController;
import SCD.controllers.BranchManagerControllers.DataEntryOperatorController.AddDataEntryOperatorController;
import SCD.controllers.BranchManagerControllers.DataEntryOperatorController.DeleteDataEntryOperatorController;
import SCD.controllers.BranchManagerControllers.DataEntryOperatorController.UpdateDataEntryController;
import SCD.controllers.BranchManagerControllers.DataEntryOperatorController.ViewDataEntryOperatorsController;
import SCD.controllers.CommonControllers.MainMenuController;
import SCD.ui.BranchManager.BranchSidebar;

public class BranchSidebarController {

    private JPanel sidebarPanel;

    public BranchSidebarController() {
        sidebarPanel = new BranchSidebar();
    }

    public BranchSidebarController(JPanel sidebarPanel) {
        this.sidebarPanel = sidebarPanel;
    }

    public void openDashboard() {
        navigateTo(BranchManagerDashboardController::new);
    }

    public void openAddCashier() {
        navigateTo(AddCashierController::new);
    }

    public void openUpdateCashier() {
        navigateTo(UpdateCashierController::new);
    }

    public void openDeleteCashier() {
        navigateTo(DeleteCashierController::new);
    }

    public void openViewCashiers() {
        navigateTo(ViewCashiersController::new);
    }

    public void openAddDataEntryOperator() {
        navigateTo(AddDataEntryOperatorController::new);
    }

    public void openUpdateDataEntryOperator() {
        navigateTo(UpdateDataEntryController::new);
    }

    public void openDeleteDataEntryOperator() {
        navigateTo(DeleteDataEntryOperatorController::new);
    }

    public void openViewDataEntryOperators() {
        navigateTo(ViewDataEntryOperatorsController::new);
    }

    public void openSettings() {
        navigateTo(SettingsPageController::new);
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

            Window currentWindow = SwingUtilities.getWindowAncestor(sidebarPanel);
            if (currentWindow instanceof JFrame) {
                ((JFrame) currentWindow).dispose();
            }
            navigationTask.run();
        });
    }

    public void openViewSales() {
        navigateTo(ViewSalesController::new);
    }
}
