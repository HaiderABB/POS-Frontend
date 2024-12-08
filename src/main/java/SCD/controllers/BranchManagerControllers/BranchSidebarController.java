package SCD.controllers.BranchManagerControllers;

import SCD.controllers.BranchManagerControllers.CashierController.AddCashierController;
import SCD.controllers.BranchManagerControllers.CashierController.UpdateCashierController;
import SCD.controllers.BranchManagerControllers.CashierController.ViewCashiersController;
import SCD.controllers.BranchManagerControllers.DataEntryOperatorController.AddDataEntryOperatorController;
import SCD.controllers.BranchManagerControllers.DataEntryOperatorController.DeleteDataEntryOperatorController;
import SCD.controllers.BranchManagerControllers.DataEntryOperatorController.UpdateDataEntryController;
import SCD.controllers.BranchManagerControllers.DataEntryOperatorController.ViewDataEntryOperatorsController;
import SCD.ui.BranchManager.BranchManagerDashboard;
import SCD.ui.BranchManager.BranchSidebar;
import SCD.ui.BranchManager.ManageCashier.AddCashierPage;
import SCD.ui.BranchManager.ManageCashier.DeleteCashierPage;
import SCD.ui.BranchManager.ManageCashier.UpdateCashierPage;
import SCD.ui.BranchManager.ManageCashier.ViewCashiersPage;
import SCD.ui.BranchManager.ManageDataEntryOperator.AddDataEntryOperatorPage;
import SCD.ui.BranchManager.ManageDataEntryOperator.DeleteDataEntryOperatorPage;
import SCD.ui.BranchManager.ManageDataEntryOperator.UpdateDataEntryPage;
import SCD.ui.BranchManager.ManageDataEntryOperator.ViewDataEntryOperatorsPage;
import SCD.ui.BranchManager.SettingsPage;

import javax.swing.*;
import java.awt.*;

public class BranchSidebarController {

    private JPanel sidebarPanel;

    public BranchSidebarController() {
        BranchSidebar branchSidebar = new BranchSidebar();
    }

    public BranchSidebarController(JPanel sidebarPanel) {
        this.sidebarPanel = sidebarPanel;
    }

    public void openDashboard() {
        navigateTo(BranchManagerDashboardController::new);
    }

    public void openAddCashier() {
        navigateTo( AddCashierController::new);
    }

    public void openUpdateCashier() {
        navigateTo( UpdateCashierController::new);
    }

    public void openDeleteCashier() {
        navigateTo( UpdateCashierController::new);
    }

    public void openViewCashiers() {
        navigateTo(ViewCashiersController::new);
    }

    public void openAddDataEntryOperator() {
        navigateTo( AddDataEntryOperatorController::new);
    }

    public void openUpdateDataEntryOperator() {
        navigateTo( UpdateDataEntryController::new);
    }

    public void openDeleteDataEntryOperator() {
        navigateTo( DeleteDataEntryOperatorController::new);
    }

    public void openViewDataEntryOperators() {
        navigateTo(ViewDataEntryOperatorsController::new);
    }

    public void openSettings() {
        navigateTo(SettingsPageController::new);
    }

    public void performLogout() {
        JOptionPane.showMessageDialog(sidebarPanel, "Logging out...");
        System.exit(0);
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

}
