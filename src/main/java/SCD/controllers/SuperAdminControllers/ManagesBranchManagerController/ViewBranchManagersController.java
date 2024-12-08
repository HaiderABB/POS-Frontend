package SCD.controllers.SuperAdminControllers.ManagesBranchManagerController;

import javax.swing.SwingUtilities;

import SCD.model.models.Employee;
import SCD.ui.SuperAdmin.ManageBranchManager.ViewBranchManagersPage;

public class ViewBranchManagersController {

    private ViewBranchManagersPage view;
    Employee employee;

    public ViewBranchManagersController(Employee employee) {
        this.employee = employee;
        SwingUtilities.invokeLater(() -> {
            ViewBranchManagersPage page = new ViewBranchManagersPage(employee);

            page.setVisible(true);
        });

    }

    public ViewBranchManagersController(ViewBranchManagersPage view, Employee employee) {
        this.employee = employee;
        this.view = view;
        loadSampleData();
    }

    private void loadSampleData() {
        SwingUtilities.invokeLater(() -> {
            view.getTableModel().addRow(new Object[] { "BM-0001", "John Doe", "john.doe@example.com", "BH-1234" });
            view.getTableModel().addRow(new Object[] { "BM-0002", "Jane Smith", "jane.smith@example.com", "BH-5678" });
        });
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            ViewBranchManagersPage page = new ViewBranchManagersPage(null);
            new ViewBranchManagersController(page, null);

            page.setVisible(true);

    }

    public void showPage() {
        new ViewBranchManagersPage(employee).setVisible(true);
    }
}
