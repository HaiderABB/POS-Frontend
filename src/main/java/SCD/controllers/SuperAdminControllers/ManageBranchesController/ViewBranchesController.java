package SCD.controllers.SuperAdminControllers.ManageBranchesController;

import javax.swing.SwingUtilities;

import SCD.model.models.Employee;
import SCD.ui.SuperAdmin.ManageBranches.ViewBranchesPage;

public class ViewBranchesController {

    private ViewBranchesPage viewBranchesPage;
    Employee employee;

    public ViewBranchesController(Employee employee) {
        this.employee = employee;
        SwingUtilities.invokeLater(() -> {
            ViewBranchesPage page = new ViewBranchesPage(employee);
            page.setVisible(true);
        });

    }

    public ViewBranchesController(ViewBranchesPage viewBranchesPage, Employee employee) {
        this.employee = employee;
        this.viewBranchesPage = viewBranchesPage;

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            ViewBranchesPage page = new ViewBranchesPage(null);
            new ViewBranchesController(page, null);

            page.setVisible(true);

    }
}
