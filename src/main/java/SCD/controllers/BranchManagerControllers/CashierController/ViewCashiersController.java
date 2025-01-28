package SCD.controllers.BranchManagerControllers.CashierController;

import SCD.controllers.cache.Cache;
import SCD.model.models.Employee;
import SCD.model.service.BranchManagerService.BranchManagerService;
import SCD.model.service.Common.CommonServices;
import SCD.model.service.Json.GetResponseJSON;
import SCD.ui.BranchManager.ManageCashier.ViewCashiersPage;

public class ViewCashiersController {

    private ViewCashiersPage view;
    BranchManagerService branchManagerService = new BranchManagerService();

    public ViewCashiersController() {
        Cache cache = new Cache();
        String emp_code = cache.getCache();
        CommonServices c = new CommonServices();
        Employee emp = c.getEmployeeByEmployeeCode(emp_code);

        GetResponseJSON<Employee> json = branchManagerService.getCashiers(emp.getBranch().getBranchCode());

        view = new ViewCashiersPage();
        populateTable(json);
        view.setVisible(true);

    }

    public ViewCashiersController(ViewCashiersPage view) {
        this.view = view;

    }

    void populateTable(GetResponseJSON<Employee> json) {
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
        ViewCashiersPage page = new ViewCashiersPage();
        new ViewCashiersController(page);
        page.setVisible(true);
    }
}
