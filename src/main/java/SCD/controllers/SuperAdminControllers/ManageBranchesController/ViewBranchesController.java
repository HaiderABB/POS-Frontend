package SCD.controllers.SuperAdminControllers.ManageBranchesController;

import SCD.model.models.Branch;
import SCD.model.service.Json.GetResponseJSON;
import SCD.model.service.SuperAdminService.SuperAdminService;
import SCD.ui.SuperAdmin.ManageBranches.ViewBranchesPage;

public class ViewBranchesController {

    private ViewBranchesPage viewBranchesPage;
    private SuperAdminService superAdminService;

    public ViewBranchesController() {
        superAdminService = new SuperAdminService();
        GetResponseJSON<Branch> json = superAdminService.getBranches();

        viewBranchesPage = new ViewBranchesPage();
        clearTable();
        populateTable(json);
        viewBranchesPage.setVisible(true);
    }

    private void clearTable() {
        viewBranchesPage.getTableModel().setRowCount(0); // scd- proj initClear any existing rows in the table
    }

    private void populateTable(GetResponseJSON<Branch> json) {
        if (json != null && json.getData() != null) {
            for (Branch branch : json.getData()) {
                viewBranchesPage.addRow(new Object[] {
                        branch.getBranchCode(),
                        branch.getName(),
                        branch.getCity(),
                        branch.getPhone(),
                        branch.getAddress(),
                        branch.isActive() ? "Yes" : "No"
                });
            }
        } else {
            viewBranchesPage.addRow(new Object[] { "-", "-", "-", "-", "-", "-" });
        }
    }

    public static void main(String[] args) {
        // scd- proj initnew ViewBranchesController();
    }
}
