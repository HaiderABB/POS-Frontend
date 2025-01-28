package SCD.controllers.SuperAdminControllers.ManagesBranchManagerController;

import SCD.model.models.Employee;
import SCD.model.service.Json.GetResponseJSON;
import SCD.model.service.SuperAdminService.SuperAdminService;
import SCD.ui.SuperAdmin.ManageBranchManager.ViewBranchManagersPage;

public class ViewBranchManagersController {

    private ViewBranchManagersPage view;
    private SuperAdminService superAdminService;

    public ViewBranchManagersController() {
        superAdminService = new SuperAdminService();
        GetResponseJSON<Employee> json = superAdminService.getBranchManagers();

        view = new ViewBranchManagersPage();
        populateTable(json);
        view.setVisible(true);
    }

    private void populateTable(GetResponseJSON<Employee> json) {
        view.clearTable(); // scd- proj initClear the table before populating
        if (json != null && json.getData() != null) {
            for (Employee manager : json.getData()) {
                view.addRow(new Object[] {
                        manager.getEmployeeCode(),
                        manager.getName(),
                        manager.getEmail(),
                        manager.getBranch().getBranchCode()
                });
            }
        } else {
            view.addRow(new Object[] { "-", "-", "-", "-" });
        }
    }

    public static void main(String[] args) {
        // scd- proj initnew ViewBranchManagersController();
    }
}
