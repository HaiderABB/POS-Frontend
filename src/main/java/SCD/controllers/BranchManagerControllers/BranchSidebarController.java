package SCD.controllers.BranchManagerControllers;

import SCD.ui.BranchManager.BranchManagerDashboard;
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

    private final JPanel sidebarPanel;

    public BranchSidebarController(JPanel sidebarPanel) {
        this.sidebarPanel = sidebarPanel;
    }

    public void openDashboard() {
        navigateToPage(new BranchManagerDashboard());
    }

    public void openAddCashier() {
        navigateToPage(new AddCashierPage());
    }

    public void openUpdateCashier() {
        navigateToPage(new UpdateCashierPage());
    }

    public void openDeleteCashier() {
        navigateToPage(new DeleteCashierPage());
    }

    public void openViewCashiers() {
        navigateToPage(new ViewCashiersPage());
    }

    public void openAddDataEntryOperator() {
        navigateToPage(new AddDataEntryOperatorPage());
    }

    public void openUpdateDataEntryOperator() {
        navigateToPage(new UpdateDataEntryPage());
    }

    public void openDeleteDataEntryOperator() {
        navigateToPage(new DeleteDataEntryOperatorPage());
    }

    public void openViewDataEntryOperators() {
        navigateToPage(new ViewDataEntryOperatorsPage());
    }

    public void openSettings() {
        navigateToPage(new SettingsPage());
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

            page.setLocationRelativeTo(null);
            page.setVisible(true);
        });
    }

}
