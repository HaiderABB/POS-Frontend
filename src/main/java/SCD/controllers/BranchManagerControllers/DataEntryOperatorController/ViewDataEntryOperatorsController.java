package SCD.controllers.BranchManagerControllers.DataEntryOperatorController;

import SCD.controllers.cache.Cache;
import SCD.model.models.Employee;
import SCD.model.service.BranchManagerService.BranchManagerService;
import SCD.model.service.Common.CommonServices;
import SCD.model.service.Json.GetResponseJSON;
import SCD.ui.BranchManager.ManageDataEntryOperator.ViewDataEntryOperatorsPage;

public class ViewDataEntryOperatorsController {

    private ViewDataEntryOperatorsPage view;
    BranchManagerService branchManagerService = new BranchManagerService();

    public ViewDataEntryOperatorsController() {

        Cache cache = new Cache();
        String emp_code = cache.getCache();
        CommonServices c = new CommonServices();
        Employee emp = c.getEmployeeByEmployeeCode(emp_code);

        GetResponseJSON<Employee> json = branchManagerService.getDataEntryOperators(emp.getBranch().getBranchCode());

        view = new ViewDataEntryOperatorsPage();
        populateTable(json);
        view.setVisible(true);

    }

    public ViewDataEntryOperatorsController(ViewDataEntryOperatorsPage view) {
        this.view = view;

    }

    void populateTable(GetResponseJSON<Employee> json) {
        view.clearTable();
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
        // scd- proj initViewDataEntryOperatorsPage page = new
        // ViewDataEntryOperatorsPage();
        // scd- proj initnew ViewDataEntryOperatorsController(page);
        // scd- proj initpage.setVisible(true);
    }
}
