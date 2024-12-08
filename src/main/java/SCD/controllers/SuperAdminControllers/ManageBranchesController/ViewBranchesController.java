package SCD.controllers.SuperAdminControllers.ManageBranchesController;

import SCD.ui.SuperAdmin.ManageBranches.ViewBranchesPage;

import javax.swing.*;

public class ViewBranchesController {

    private ViewBranchesPage viewBranchesPage;

    public ViewBranchesController(ViewBranchesPage viewBranchesPage) {
        this.viewBranchesPage = viewBranchesPage;
        loadSampleData();
    }

    private void loadSampleData() {
        viewBranchesPage.addRow(new Object[]{1, "Main Branch", "New York", "0321-1234567", "123 Street", true});
        viewBranchesPage.addRow(new Object[]{2, "Second Branch", "Los Angeles", "0321-7654321", "456 Avenue", false});
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ViewBranchesPage page = new ViewBranchesPage();
            new ViewBranchesController(page);
            page.setVisible(true);
        });
    }
}
