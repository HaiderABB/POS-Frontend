package SCD.controllers.SuperAdminControllers.ManagesBranchManagerController;

import SCD.ui.SuperAdmin.ManageBranchManager.ViewBranchManagersPage;

import javax.swing.*;

public class ViewBranchManagersController {

    private ViewBranchManagersPage view;
    public ViewBranchManagersController() {
        SwingUtilities.invokeLater(() -> {
            ViewBranchManagersPage page = new ViewBranchManagersPage();

            page.setVisible(true);
        });

    }

    public ViewBranchManagersController(ViewBranchManagersPage view) {
        this.view = view;
        loadSampleData();
    }

    private void loadSampleData() {
        SwingUtilities.invokeLater(() -> {
            view.getTableModel().addRow(new Object[]{"BM-0001", "John Doe", "john.doe@example.com", "BH-1234"});
            view.getTableModel().addRow(new Object[]{"BM-0002", "Jane Smith", "jane.smith@example.com", "BH-5678"});
        });
    }

    public static void main(String[] args) {

            ViewBranchManagersPage page = new ViewBranchManagersPage();
            new ViewBranchManagersController(page);
            page.setVisible(true);

    }

    public void showPage() {
        new ViewBranchManagersPage().setVisible(true);
    }
}
